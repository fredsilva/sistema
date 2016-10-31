package br.gov.to.sefaz.arr.processamento.creator;

import br.gov.to.sefaz.arr.dare.service.DareDetalheService;
import br.gov.to.sefaz.arr.persistence.entity.ArquivoDetalhePagos;
import br.gov.to.sefaz.arr.persistence.entity.Dare;
import br.gov.to.sefaz.arr.persistence.entity.DareDetalhe;
import br.gov.to.sefaz.arr.persistence.entity.PagosArrec;
import br.gov.to.sefaz.arr.persistence.enums.FormaPagamentoEnum;
import br.gov.to.sefaz.arr.persistence.enums.SituacaoDareEnum;
import br.gov.to.sefaz.arr.persistence.enums.TipoCodigoBarraEnum;
import br.gov.to.sefaz.arr.persistence.enums.TipoPessoaEnum;
import br.gov.to.sefaz.arr.processamento.domain.detalhe.arrecadacao.FileDetalheArrec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Utilitário para facilitar a criação da entidade {@link br.gov.to.sefaz.arr.persistence.entity.PagosArrec}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 20/07/2016 16:28:00
 */
@SuppressWarnings("PMD")
@Component
public class PagosArrecCreator {

    private static final FormaPagamentoEnum ORIGEM_DARE = FormaPagamentoEnum.INTERNET;
    private static final SituacaoDareEnum SITUACAO_DARE = SituacaoDareEnum.OK;
    private static final Long ID_REPASSE = null;

    private final DareDetalheService dareDetalheService;

    @Autowired
    public PagosArrecCreator(DareDetalheService dareDetalheService) {
        this.dareDetalheService = dareDetalheService;
    }

    /**
     * Cria uma coleção de {@link br.gov.to.sefaz.arr.persistence.entity.PagosArrec} conforme o código de
     * barras de cada linha do arquivo processado.
     *
     * @param idBdarTpar       identificação do TPAR a ser vinculado com o pagamento
     * @param detalheArquivo   contém informações para a criação do
     *                         {@link br.gov.to.sefaz.arr.persistence.entity.PagosArrec}
     * @param fileDetalheArrec contém informações da linha corrente do arquivo processado
     * @param ordemLote        ordem do lote a ser processado o pagamento
     * @param tipoDare         informação do tipo de código de barras
     * @param dare             {@link br.gov.to.sefaz.arr.persistence.entity.Dare}
     *                         ao qual o pagamento pertence
     * @return uma coleção de {@link br.gov.to.sefaz.arr.persistence.entity.PagosArrec} conforme os
     *     parâmetros fornecidos
     */
    public Collection<PagosArrec> createPagosArrecCollection(Long idBdarTpar, ArquivoDetalhePagos detalheArquivo,
            FileDetalheArrec fileDetalheArrec, int ordemLote, TipoCodigoBarraEnum tipoDare, Dare dare) {
        Collection<PagosArrec> pagosArrecList = new ArrayList<>();

        LocalDateTime dataTransacao = fileDetalheArrec.getDataTransacao();
        List<DareDetalhe> dareDetalhes = dareDetalheService.findAllByNossoNumero(dare.getIdNossoNumeroDare());

        if (dareDetalhes.isEmpty() || Objects.isNull(dareDetalhes)) {
            pagosArrecList.add(createPagosArrecNull(idBdarTpar, detalheArquivo, ordemLote, tipoDare,
                    dataTransacao));
        } else {
            pagosArrecList.addAll(dareDetalhes
                    .stream()
                    .map(dareDetalhe ->
                            createPagosArrec(idBdarTpar, detalheArquivo, dataTransacao, ordemLote,
                                    tipoDare, dare, dareDetalhe))
                    .collect(Collectors.toList()));
        }

        return pagosArrecList;
    }

    private PagosArrec createPagosArrecNull(Long idBdarTpar, ArquivoDetalhePagos detalheArquivo, int ordemLote,
            TipoCodigoBarraEnum tipoDare, LocalDateTime dataTransacao) {
        Long idPessoa = 9999999999L;
        TipoPessoaEnum tipoPessoaEnum = TipoPessoaEnum.CPF;
        int idReceita = 9998;
        int idSubcodigo = 0;
        long documento = 0L;
        int numeroParcela = 0;
        int idMunicipio = 0;
        BigDecimal valorTotal = BigDecimal.ZERO;
        BigDecimal valorPrincipal = BigDecimal.ZERO;
        BigDecimal valorMulta = BigDecimal.ZERO;
        BigDecimal valorJuros = BigDecimal.ZERO;
        BigDecimal valorCorrecaoMonetaria = BigDecimal.ZERO;
        BigDecimal valorTaxa = BigDecimal.ZERO;
        LocalDateTime dataPagamento = detalheArquivo.getDataPagamento();
        Integer year = dataPagamento.getYear();
        Integer monthValue = dataPagamento.getMonthValue();
        int periodoReferencia = Integer.valueOf(year.toString().concat(monthValue.toString()));
        FormaPagamentoEnum origemDare = FormaPagamentoEnum.OUTROS;
        SituacaoDareEnum situacaoDare = SituacaoDareEnum.ERRO;
        String numeroSequencial = detalheArquivo.getNumeroNsu();
        Long idDetalheArquivo = detalheArquivo.getIdDetalheArquivo();

        PagosArrec pagosArrec = new PagosArrec(idBdarTpar, ordemLote, idReceita, ID_REPASSE, idDetalheArquivo,
                idPessoa, tipoPessoaEnum, dataPagamento, valorTotal, valorPrincipal, valorMulta,
                valorJuros, valorCorrecaoMonetaria, valorTaxa, tipoDare, origemDare, situacaoDare,
                dataTransacao, numeroSequencial);

        pagosArrec.setIdMunicipioDare(idMunicipio);
        pagosArrec.setNumeroParcela(numeroParcela);
        pagosArrec.setDocumento(documento);
        pagosArrec.setIdSubcodigo(idSubcodigo);
        pagosArrec.setPeriodoReferencia(periodoReferencia);

        return pagosArrec;
    }

    private PagosArrec createPagosArrec(Long idBdarTpar, ArquivoDetalhePagos arquivoDetalhePagos,
            LocalDateTime dataTransacao, int ordemLote, TipoCodigoBarraEnum tipoDare, Dare dare,
            DareDetalhe dareDetalhe) {
        Long idPessoa = dare.getIdPessoa();
        Long idPessoaReferenciada = dareDetalhe.getIdPessoaReferenciada();

        if (!Objects.isNull(idPessoaReferenciada)) {
            idPessoa = idPessoaReferenciada;
        }

        Long idDetalheArquivo = arquivoDetalhePagos.getIdDetalheArquivo();
        LocalDateTime dataPagamento = arquivoDetalhePagos.getDataPagamento();
        String numeroSequencial = arquivoDetalhePagos.getNumeroNsu();

        BigDecimal valorTotal = Objects.isNull(dareDetalhe.getValorTotal()) ? BigDecimal.ZERO
                : dareDetalhe.getValorTotal();
        BigDecimal valorImposto = Objects.isNull(dareDetalhe.getValorImposto()) ? BigDecimal.ZERO
                : dareDetalhe.getValorImposto();
        BigDecimal valorMulta = Objects.isNull(dareDetalhe.getValorMulta()) ? BigDecimal.ZERO
                : dareDetalhe.getValorMulta();
        BigDecimal valorJuros = Objects.isNull(dareDetalhe.getValorJuros()) ? BigDecimal.ZERO
                : dareDetalhe.getValorJuros();
        BigDecimal valorCorrecaoMonetaria = Objects.isNull(dareDetalhe.getValorCorrecaoMonetaria())
                ? BigDecimal.ZERO : dareDetalhe.getValorCorrecaoMonetaria();
        BigDecimal valorTaxa = Objects.isNull(dareDetalhe.getValorTaxa()) ? BigDecimal.ZERO
                : dareDetalhe.getValorTaxa();

        PagosArrec pagosArrec = new PagosArrec(idBdarTpar, ordemLote, dareDetalhe.getIdReceita(), ID_REPASSE,
                idDetalheArquivo, idPessoa, dare.getTipoPessoa(),
                dataPagamento, valorTotal, valorImposto, valorMulta, valorJuros, valorCorrecaoMonetaria,
                valorTaxa, tipoDare, ORIGEM_DARE, SITUACAO_DARE, dataTransacao, numeroSequencial);
        pagosArrec.setIdMunicipioDare(dareDetalhe.getIdMunicipio());
        pagosArrec.setDareDetalhe(dareDetalhe);

        return pagosArrec;
    }
}
