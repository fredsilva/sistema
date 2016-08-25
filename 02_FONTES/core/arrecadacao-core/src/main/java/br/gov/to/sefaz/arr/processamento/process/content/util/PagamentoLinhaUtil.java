package br.gov.to.sefaz.arr.processamento.process.content.util;

import br.gov.to.sefaz.arr.persistence.entity.ArquivoDetalhePagos;
import br.gov.to.sefaz.arr.persistence.entity.ArquivoRecepcao;
import br.gov.to.sefaz.arr.persistence.entity.ConveniosArrec;
import br.gov.to.sefaz.arr.persistence.entity.LotesPagosArrec;
import br.gov.to.sefaz.arr.persistence.entity.PagosArrec;
import br.gov.to.sefaz.arr.persistence.enums.EstadoLoteEnum;
import br.gov.to.sefaz.arr.processamento.service.ArquivoRecepcaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Utilitário para auxiliar o processo de pagamento da linha processada.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 19/07/2016 10:29:00
 */
@Component
public class PagamentoLinhaUtil {

    private final LotesPagosManager lotesPagosManager;
    private final ArquivoRecepcaoService arquivoRecepcaoService;

    @Autowired
    public PagamentoLinhaUtil(LotesPagosManager lotesPagosManager, ArquivoRecepcaoService arquivoRecepcaoService) {
        this.lotesPagosManager = lotesPagosManager;
        this.arquivoRecepcaoService = arquivoRecepcaoService;
    }

    /**
     * Busca um BDAR para o {@link br.gov.to.sefaz.arr.persistence.entity.ArquivoDetalhePagos} fornecido.
     * Se não encontrar, um novo BDAR será criado.
     *
     * @param arquivoDetalhePagos contém as informações necessárias para buscar ou criar um BDAR
     * @return um BDAR para a linha que está sendo processada
     */
    public LotesPagosArrec getBdar(ArquivoDetalhePagos arquivoDetalhePagos) {
        ArquivoRecepcao arquivoRecepcao = arquivoRecepcaoService.findOne(arquivoDetalhePagos.getIdArquivos());

        ConveniosArrec conveniosArrecadacao = arquivoRecepcao.getConveniosArrecadacao();
        Long codigoConvenio = conveniosArrecadacao.getIdConvenio();
        Integer codigoBanco = arquivoRecepcao.getBancos().getIdBanco();
        Integer codigoAgencia = conveniosArrecadacao.getBancoAgencias().getIdAgencia();
        LocalDateTime dataPagamento = arquivoDetalhePagos.getDataPagamento();

        return lotesPagosManager.getBdar(codigoConvenio, codigoBanco, codigoAgencia, dataPagamento);
    }

    /**
     * Busca um TPAR para o {@link br.gov.to.sefaz.arr.persistence.entity.ArquivoDetalhePagos} e BDAR
     * fornecidos. Se não encontrar, um novo TPAR será criado.
     *
     * @param arquivoDetalhePagos contém as informações necessárias para buscar ou criar um TPAR
     * @param bdar                através do BDAR cria-se o relacionamento com a entidade
     *                            {@link br.gov.to.sefaz.arr.persistence.entity.LotesPagos}
     * @return um TPAR para a linha que está sendo processada
     */
    public LotesPagosArrec getTpar(ArquivoDetalhePagos arquivoDetalhePagos, LotesPagosArrec bdar) {
        ArquivoRecepcao arquivoRecepcao = arquivoRecepcaoService.findOne(arquivoDetalhePagos.getIdArquivos());

        ConveniosArrec conveniosArrecadacao = arquivoRecepcao.getConveniosArrecadacao();
        Long codigoConvenio = conveniosArrecadacao.getIdConvenio();
        Integer codigoBanco = arquivoRecepcao.getBancos().getIdBanco();
        Integer codigoAgencia = conveniosArrecadacao.getBancoAgencias().getIdAgencia();
        LocalDateTime dataPagamento = arquivoDetalhePagos.getDataPagamento();

        return lotesPagosManager.getTpar(codigoConvenio, codigoBanco, codigoAgencia, dataPagamento, bdar);
    }

    /**
     * Atualiza os valores e quantidade do TPAR e BDAR da linha processada.
     *
     * @param bdar       BDAR a ser atualizado
     * @param tpar       TPAR a ser atualizado
     * @param valorTotal contém ao valor total da linha processada
     */
    public void updateBdarAndTpar(LotesPagosArrec bdar, LotesPagosArrec tpar, BigDecimal valorTotal) {
        lotesPagosManager.updateQuantidadeAndValor(valorTotal, tpar);
        lotesPagosManager.updateQuantidadeAndValor(valorTotal, bdar);
    }

    /**
     * Atualiza os valores e quantidade do TPAR e BDAR da linha processada com ERROS.
     *
     * @param bdar       BDAR a ser atualizado
     * @param tpar       TPAR a ser atualizado
     * @param valorTotal contém ao valor total da linha processada
     */
    public void updateErrorBdarAndTpar(LotesPagosArrec bdar, LotesPagosArrec tpar, BigDecimal valorTotal) {
        lotesPagosManager.updateQuantidadeAndValorError(valorTotal, tpar);
        lotesPagosManager.updateQuantidadeAndValorError(valorTotal, bdar);
    }

    /**
     * Verifica se a ordem do lote criada chegou a 999 para o mesmo TPAR, se sim este TPAR é fechado
     * e criado um novo. Se não continua com o mesmo TPAR até atingir o lote 999.
     *
     * @param arquivoDetalhePagos contém informações necessárias para a criação de um TPAR
     * @param bdar                através do BDAR cria-se o relacionamento com a entidade
     *                            {@link br.gov.to.sefaz.arr.persistence.entity.LotesPagos}
     * @param tpar                TPAR a ser fechado, caso a ordem de lote seja 999.
     * @param pagosArrec          contém a ordem do lote para o pagamento processado.
     */
    public void verifyOrdemLoteFromTpar(ArquivoDetalhePagos arquivoDetalhePagos, LotesPagosArrec bdar,
            LotesPagosArrec tpar, PagosArrec pagosArrec) {
        if (pagosArrec.getOrdemLote() >= 999) {
            lotesPagosManager.updateEstado(tpar, EstadoLoteEnum.FECHADO);

            ArquivoRecepcao arquivoRecepcao = arquivoRecepcaoService.findOne(arquivoDetalhePagos.getIdArquivos());
            ConveniosArrec conveniosArrec = arquivoRecepcao.getConveniosArrecadacao();
            Long codigoConvenio = conveniosArrec.getIdConvenio();
            Integer codigoBanco = arquivoRecepcao.getBancos().getIdBanco();
            Integer codigoAgencia = conveniosArrec.getBancoAgencias().getIdAgencia();
            LocalDateTime dataPagamento = arquivoDetalhePagos.getDataPagamento();
            lotesPagosManager.createAndSaveTpar(codigoConvenio, codigoBanco, codigoAgencia, dataPagamento, bdar);
        }
    }
}
