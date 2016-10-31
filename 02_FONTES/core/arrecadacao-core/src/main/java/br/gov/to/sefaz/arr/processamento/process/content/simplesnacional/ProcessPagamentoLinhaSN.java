package br.gov.to.sefaz.arr.processamento.process.content.simplesnacional;

import br.gov.to.sefaz.arr.persistence.entity.ArquivoDetalhePagos;
import br.gov.to.sefaz.arr.persistence.entity.ArquivoRecepcao;
import br.gov.to.sefaz.arr.persistence.entity.LotesPagosArrec;
import br.gov.to.sefaz.arr.persistence.entity.PagosArrec;
import br.gov.to.sefaz.arr.processamento.domain.detalhe.simplesnacional.FileDetalheSN;
import br.gov.to.sefaz.arr.processamento.process.content.util.PagamentoLinhaUtil;
import br.gov.to.sefaz.arr.processamento.service.ArquivoRecepcaoService;
import br.gov.to.sefaz.exception.file.ProcessFileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Serviço para processar a linha de pagamento do arquivo de Simples Nacional.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 19/07/2016 16:18:00
 */
@Component
@SuppressWarnings({"PMD.AvoidCatchingGenericException", "PMD.PreserveStackTrace"})
public class ProcessPagamentoLinhaSN {

    private final PagosArrecSNManager arrecSNManager;
    private final PagamentoLinhaUtil pagamentoLinhaUtil;
    private final ArquivoRecepcaoService arquivoRecepcaoService;

    @Autowired
    public ProcessPagamentoLinhaSN(PagosArrecSNManager arrecSNManager, PagamentoLinhaUtil pagamentoLinhaUtil,
            ArquivoRecepcaoService arquivoRecepcaoService) {
        this.arrecSNManager = arrecSNManager;
        this.pagamentoLinhaUtil = pagamentoLinhaUtil;
        this.arquivoRecepcaoService = arquivoRecepcaoService;
    }

    /**
     * Processa um pagamento conforme a linha do arquivo.
     *
     * @param arquivoDetalhePagos contém as informações do pagamento necessárias para o processamento de um pagamento
     * @param fileDetalheSN       contém as informações da linha do arquivo Simples Nacional
     *                            o qual está sendo processado
     */
    public void processPagamento(ArquivoDetalhePagos arquivoDetalhePagos, FileDetalheSN fileDetalheSN) {
        LotesPagosArrec bdar = pagamentoLinhaUtil.getBdar(arquivoDetalhePagos);
        LotesPagosArrec tpar = pagamentoLinhaUtil.getTpar(arquivoDetalhePagos, bdar);

        try {
            PagosArrec pagosArrec = arrecSNManager.createAndSavePagosSN(tpar.getIdBdarTpar(), arquivoDetalhePagos,
                    fileDetalheSN);

            BigDecimal valorTotal = pagosArrec.getValorTotal();
            pagamentoLinhaUtil.updateBdarAndTpar(bdar, tpar, valorTotal);
            updateArquivoRecepcao(arquivoDetalhePagos, valorTotal);
            pagamentoLinhaUtil.verifyOrdemLoteFromTpar(arquivoDetalhePagos, bdar, tpar, pagosArrec);
        } catch (ProcessFileException e) {
            BigDecimal valorPrincipal = fileDetalheSN.getValorPrincipal();
            BigDecimal valorMulta = fileDetalheSN.getValorMulta();
            BigDecimal valorJuros = fileDetalheSN.getValorJuros();
            BigDecimal valorTotal = valorPrincipal.add(valorMulta).add(valorJuros);

            pagamentoLinhaUtil.updateErrorBdarAndTpar(bdar, tpar, valorTotal);
            updateArquivoRecepcaoErro(arquivoDetalhePagos, valorTotal);

            throw new ProcessFileException(e.getCodigoRejeicao());
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

    private void updateArquivoRecepcao(ArquivoDetalhePagos arquivoDetalhePagos, BigDecimal valorTotal) {
        ArquivoRecepcao arquivoRecepcao = arquivoRecepcaoService.findOne(arquivoDetalhePagos.getIdArquivos());
        arquivoRecepcao.setValorTotal(arquivoRecepcao.getValorTotal().add(valorTotal));
        arquivoRecepcao.setQuantidadeDocs(arquivoRecepcao.getQuantidadeDocs() + 1);
        arquivoRecepcaoService.update(arquivoRecepcao);
    }


}
