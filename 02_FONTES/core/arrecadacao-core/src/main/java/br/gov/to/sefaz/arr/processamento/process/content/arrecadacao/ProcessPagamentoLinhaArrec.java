package br.gov.to.sefaz.arr.processamento.process.content.arrecadacao;

import br.gov.to.sefaz.arr.persistence.entity.ArquivoDetalhePagos;
import br.gov.to.sefaz.arr.persistence.entity.ArquivoRecepcao;
import br.gov.to.sefaz.arr.persistence.entity.LotesPagosArrec;
import br.gov.to.sefaz.arr.persistence.entity.PagosArrec;
import br.gov.to.sefaz.arr.persistence.enums.EstadoLoteEnum;
import br.gov.to.sefaz.arr.processamento.domain.detalhe.arrecadacao.FileDetalheArrec;
import br.gov.to.sefaz.arr.processamento.domain.header.TipoArquivoEnum;
import br.gov.to.sefaz.arr.processamento.exception.ProcessFileDetalheException;
import br.gov.to.sefaz.arr.processamento.process.content.util.PagamentoLinhaUtil;
import br.gov.to.sefaz.arr.processamento.service.ArquivoRecepcaoService;
import br.gov.to.sefaz.exception.file.ProcessFileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;

/**
 * Serviço para processar a linha de pagamento do arquivo de arrecadação.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 20/07/2016 11:01:00
 */
@Component
@SuppressWarnings({"PMD.AvoidCatchingGenericException", "PMD.PreserveStackTrace"})
public class ProcessPagamentoLinhaArrec {

    private final PagamentoLinhaUtil pagamentoLinhaUtil;
    private final PagosArrecManager pagosArrecManager;
    private final ArquivoRecepcaoService arquivoRecepcaoService;

    @Autowired
    public ProcessPagamentoLinhaArrec(PagamentoLinhaUtil pagamentoLinhaUtil, PagosArrecManager pagosArrecManager,
            ArquivoRecepcaoService arquivoRecepcaoService) {
        this.pagamentoLinhaUtil = pagamentoLinhaUtil;
        this.pagosArrecManager = pagosArrecManager;
        this.arquivoRecepcaoService = arquivoRecepcaoService;
    }

    /**
     * Processa um pagamento conforme a linha do arquivo.
     *
     * @param arquivoDetalhePagos contém as informações do pagamento necessárias para o processamento de um pagamento
     * @param fileDetalheArrec    contém as informações da linha do arquivo, o qual está sendo processado
     */
    public void processPagamento(ArquivoDetalhePagos arquivoDetalhePagos, FileDetalheArrec fileDetalheArrec) {
        LotesPagosArrec bdar = pagamentoLinhaUtil.getBdar(arquivoDetalhePagos);
        LotesPagosArrec tpar = pagamentoLinhaUtil.getTpar(arquivoDetalhePagos, bdar);

        try {
            Collection<PagosArrec> pagosArrecList = pagosArrecManager.createAndSavePagosArrec(tpar.getIdBdarTpar(),
                    arquivoDetalhePagos, fileDetalheArrec);

            pagosArrecList.forEach(pagosArrec -> {
                ArquivoRecepcao arquivoRecepcao = arquivoRecepcaoService.findOne(arquivoDetalhePagos.getIdArquivos());
                TipoArquivoEnum tipoArquivoEnum = arquivoRecepcao.getCaracteristicaArquivo();
                if (tipoArquivoEnum.equals(TipoArquivoEnum.CONSOLIDADO)) {
                    tpar.setEstadoLote(EstadoLoteEnum.FECHADO);
                    bdar.setEstadoLote(EstadoLoteEnum.FECHADO);
                }
                pagamentoLinhaUtil.updateBdarAndTpar(bdar, tpar, pagosArrec.getValorTotal());
                updateArquivoRecepcao(arquivoRecepcao, pagosArrec.getValorTotal());

                pagamentoLinhaUtil.verifyOrdemLoteFromTpar(arquivoDetalhePagos, bdar, tpar, pagosArrec);
            });
        } catch (ProcessFileException e) {
            BigDecimal valorPagamento = arquivoDetalhePagos.getValorPagamento();

            pagamentoLinhaUtil.updateErrorBdarAndTpar(bdar, tpar, valorPagamento);
            updateArquivoRecepcaoErro(arquivoDetalhePagos, valorPagamento);

            throw new ProcessFileDetalheException(e.getCodigoRejeicao(), fileDetalheArrec);
        }
    }

    private void updateArquivoRecepcaoErro(ArquivoDetalhePagos arquivoDetalhePagos, BigDecimal valorTotal) {
        ArquivoRecepcao arquivoRecepcao = arquivoRecepcaoService.findOne(arquivoDetalhePagos.getIdArquivos());
        BigDecimal valorRejeitado = Objects.isNull(arquivoRecepcao.getValorRejeitado())
                ? BigDecimal.ZERO : arquivoRecepcao.getValorRejeitado();
        arquivoRecepcao.setValorRejeitado(valorRejeitado.add(valorTotal));
        long quantRejeitada = Objects.isNull(arquivoRecepcao.getQuantidadeRejeitada())
                ? 0 : arquivoRecepcao.getQuantidadeRejeitada();
        arquivoRecepcao.setQuantidadeRejeitada(quantRejeitada + 1);
        arquivoRecepcaoService.update(arquivoRecepcao);
    }

    private void updateArquivoRecepcao(ArquivoRecepcao arquivoRecepcao, BigDecimal valorTotal) {
        arquivoRecepcao.setValorTotal(arquivoRecepcao.getValorTotal().add(valorTotal));
        arquivoRecepcao.setQuantidadeDocs(arquivoRecepcao.getQuantidadeDocs() + 1);
        arquivoRecepcaoService.update(arquivoRecepcao);
    }
}
