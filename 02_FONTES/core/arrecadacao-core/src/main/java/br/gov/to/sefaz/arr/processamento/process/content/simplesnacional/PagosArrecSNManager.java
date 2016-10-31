package br.gov.to.sefaz.arr.processamento.process.content.simplesnacional;

import br.gov.to.sefaz.arr.persistence.entity.ArquivoDetalhePagos;
import br.gov.to.sefaz.arr.persistence.entity.PagosArrec;
import br.gov.to.sefaz.arr.persistence.enums.FormaPagamentoEnum;
import br.gov.to.sefaz.arr.persistence.enums.SituacaoDareEnum;
import br.gov.to.sefaz.arr.persistence.enums.TipoCodigoBarraEnum;
import br.gov.to.sefaz.arr.persistence.enums.TipoPessoaEnum;
import br.gov.to.sefaz.arr.processamento.domain.detalhe.simplesnacional.FileDetalheSN;
import br.gov.to.sefaz.arr.processamento.domain.str.TipoRejeicaoEnum;
import br.gov.to.sefaz.arr.processamento.exception.ProcessFileDetalheException;
import br.gov.to.sefaz.arr.processamento.service.PagosArrecService;
import br.gov.to.sefaz.cci.business.service.ContribuinteIcmsService;
import br.gov.to.sefaz.cci.persistence.entity.ContribuinteIcms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Processa o pagamento de uma arrecadação Simples Nacional, obtida através de um arquivo em sua linha de detalhe.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 19/07/2016 18:01:00
 */
@Component
public class PagosArrecSNManager {

    private static final BigDecimal VALOR_CORRECAO_MONETARIA = BigDecimal.ZERO;
    private static final TipoCodigoBarraEnum TIPO_DARE = TipoCodigoBarraEnum.SIMPLES_NACIONAL;
    private static final FormaPagamentoEnum ORIGEM_DARE = FormaPagamentoEnum.INTERNET;
    private static final SituacaoDareEnum SITUACAO_DARE = SituacaoDareEnum.OK;
    private static final Integer ID_RECEITA = 107;
    private static final Long ID_REPASSE = null;

    private final PagosArrecService pagosArrecService;
    private final ContribuinteIcmsService contribuinteIcmsService;

    @Autowired
    public PagosArrecSNManager(PagosArrecService pagosArrecService, ContribuinteIcmsService contribuinteIcmsService) {
        this.pagosArrecService = pagosArrecService;
        this.contribuinteIcmsService = contribuinteIcmsService;
    }

    /**
     * Cria e salva um {@link br.gov.to.sefaz.arr.persistence.entity.PagosArrec} conforme os parâmtros
     * fornecidos.
     *
     * @param idBdarTpar    código de identificação do TPAR da entidade
     *                      {@link br.gov.to.sefaz.arr.persistence.entity.LotesPagosArrec}.
     * @param detalhePagos  linha do arquivo que está sendo processada, referente a
     *                      entidade {@link br.gov.to.sefaz.arr.persistence.entity.ArquivoDetalhePagos}.
     * @param fileDetalheSN objeto que contém as informações obtidas através da leitura da linha de detalhe do
     *                      arquivo, referente a
     *                      {@link br.gov.to.sefaz.arr.processamento.domain.detalhe.simplesnacional.FileDetalheSN}.
     * @return a entidade persistida na base de dados em
     * {@link br.gov.to.sefaz.arr.persistence.entity.PagosArrec}.
     */
    public PagosArrec createAndSavePagosSN(Long idBdarTpar, ArquivoDetalhePagos detalhePagos,
            FileDetalheSN fileDetalheSN) {
        int ordemLote = pagosArrecService.getLastOrdemLoteTpar(idBdarTpar);
        ordemLote++;
        String cnpjContribuinte = fileDetalheSN.getCnpjContribuinte();
        Long idPessoa = Long.valueOf(cnpjContribuinte);
        TipoPessoaEnum tipoPessoa = TipoPessoaEnum.CNPJ;

        boolean existsContribuinte = contribuinteIcmsService.existsWithCnpj(cnpjContribuinte);

        if (existsContribuinte) {
            ContribuinteIcms contribuinteIcms = contribuinteIcmsService.findFirstContribuinteWithCnpj(cnpjContribuinte);

            if (Objects.isNull(contribuinteIcms)) {
                throw new ProcessFileDetalheException(TipoRejeicaoEnum.CNPJ_N_LOCALIZADO.getCode(), fileDetalheSN);
            } else {
                idPessoa = Long.valueOf(contribuinteIcms.getNumInscricaoEstadual());
                tipoPessoa = TipoPessoaEnum.INSCRICAO;
            }
        }

        LocalDateTime dataPagamento = detalhePagos.getDataPagamento();
        String numeroAutenticacao = detalhePagos.getNumeroNsu();
        Long idDetalheArquivo = detalhePagos.getIdDetalheArquivo();
        LocalDateTime dataPagoBanco = LocalDateTime.now();

        BigDecimal valorTaxa = BigDecimal.ZERO;
        BigDecimal valorPrincipal = fileDetalheSN.getValorPrincipal();
        BigDecimal valorMulta = fileDetalheSN.getValorMulta();
        BigDecimal valorJuros = fileDetalheSN.getValorJuros();
        BigDecimal valorTotal = valorPrincipal.add(valorMulta).add(valorJuros);

        PagosArrec pagosArrec = new PagosArrec(idBdarTpar, ordemLote, ID_RECEITA, ID_REPASSE, idDetalheArquivo,
                idPessoa, tipoPessoa, dataPagamento, valorTotal, valorPrincipal, valorMulta,
                valorJuros, VALOR_CORRECAO_MONETARIA, valorTaxa, TIPO_DARE, ORIGEM_DARE,
                SITUACAO_DARE, dataPagoBanco, numeroAutenticacao);
        pagosArrec.setDataVencimentoInformado(fileDetalheSN.getDataVencimento());

        return pagosArrecService.save(pagosArrec);
    }
}
