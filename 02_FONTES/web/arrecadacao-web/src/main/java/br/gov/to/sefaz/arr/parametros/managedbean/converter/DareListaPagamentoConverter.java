package br.gov.to.sefaz.arr.parametros.managedbean.converter;

import br.gov.to.sefaz.arr.dare.enums.ValorMultaEnum;
import br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEPagamento;
import br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DebitoDiversoViewBean;
import br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DebitoIcmsFreteViewBean;
import br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DebitoPagamentoParcialViewBean;
import br.gov.to.sefaz.arr.persistence.view.DebitosContaCorrente;
import br.gov.to.sefaz.arr.persistence.view.NotaAvulsa;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import static java.math.BigDecimal.ZERO;
import static org.apache.commons.lang3.math.NumberUtils.DOUBLE_ZERO;
import static org.apache.commons.lang3.math.NumberUtils.LONG_ZERO;

/**
 * Conversor de Debitos a pagar para {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEPagamento}
 * que será inserido na lista de Pagamentos do DARE-e.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 27/09/2016 17:29:00
 */
@Component
public class DareListaPagamentoConverter {
    private static final String ZERO_PERCENT = "0%";
    private static final String COMMA_DELIMITER = ", ";

    /**
     * Converte um lista de {@link br.gov.to.sefaz.arr.persistence.view.DebitosContaCorrente} em uma lista de
     * {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEPagamento}.
     *
     * @param debitosContaCorrentes  debitos que serão adicionados ao
     *                               {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEPagamento}.
     * @param informacaoComplementar texto de observação informado pelo usuário
     * @return lista de {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEPagamento} para DARE-e.
     */
    public List<DareEPagamento> convertDebitosToDareEPagamento(List<DebitosContaCorrente> debitosContaCorrentes,
            String informacaoComplementar) {
        return debitosContaCorrentes.stream()
                .map(debito -> new DareEPagamento(debito.getDocumento(), debito.getPeriodoReferencia(),
                        debito.getDataVencimento(), debito.getReceita(), debito.getIdSubcodigo(),
                        informacaoComplementar, debito.getValorImposto(), debito.getValorMulta(),
                        debito.getValorJuros(), debito.getValorCorrecaoMonet(), null, debito.getValorReducaoMulta(),
                        debito.getValorReducaoJuros(), debito.getValorTotalDevido(), debito.getIdContaCorrente(),
                        Boolean.FALSE))
                .collect(Collectors.toList());
    }

    /**
     * Converte um lista de {@link br.gov.to.sefaz.arr.persistence.view.NotaAvulsa} em uma lista de
     * {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEPagamento}.
     *
     * @param notaAvulsas            notas avulsas que serão adicionadas ao
     *                               {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEPagamento}.
     * @param informacaoComplementar texto de observação informado pelo usuário
     * @return lista de {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEPagamento} para DARE-e.
     */
    public List<DareEPagamento> convertNotaAvulsaToDareEPagamento(List<NotaAvulsa> notaAvulsas,
            String informacaoComplementar) {
        int periodoReferencia = createPeriodoReferencia();

        return notaAvulsas.stream()
                .map(notaAvulsa -> {
                    DareEPagamento dareEPagamento = new DareEPagamento(notaAvulsa.getIdNfa(), periodoReferencia,
                            LocalDate.now(), notaAvulsa.getIdReceita(), notaAvulsa.getIdReceitaSubcodigo(),
                            informacaoComplementar, notaAvulsa.getValorDevido(), ZERO, ZERO, ZERO,
                            notaAvulsa.getValorTse(), ZERO, ZERO, notaAvulsa.getValorDevido()
                            .add(notaAvulsa.getValorTse()), notaAvulsa.getIdNfa(), Boolean.FALSE);
                    dareEPagamento.setIdReceitaTaxa(notaAvulsa.getIdReceitaTaxa());
                    return dareEPagamento;
                })
                .collect(Collectors.toList());
    }

    /**
     * Converte um {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DebitoPagamentoParcialViewBean}
     * em um {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEPagamento}.
     *
     * @param debitoPagamentoParcial debito com pagamento parcial que será convertido para
     *                               {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEPagamento}.
     * @return {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEPagamento} para DARE-e.
     */
    public DareEPagamento convertPagamentoParcialToDareEPagamento(DebitoPagamentoParcialViewBean
            debitoPagamentoParcial) {
        DebitosContaCorrente debitosContaCorrente = debitoPagamentoParcial.getDebitosContaCorrente()
                .stream()
                .filter(dcc -> dcc.getReceita().equals(debitoPagamentoParcial.getIdReceita()))
                .findFirst()
                .orElse(null);

        return new DareEPagamento(debitoPagamentoParcial.getIdDocumento(),
                debitoPagamentoParcial.getPeriodoReferencia(), debitosContaCorrente.getDataVencimento(),
                debitoPagamentoParcial.getIdReceita(), null, debitoPagamentoParcial.getInformacaoComplementar(),
                debitoPagamentoParcial.getValorImposto(), debitoPagamentoParcial.getValorMulta(),
                debitoPagamentoParcial.getValorJuros(), debitoPagamentoParcial.getValorAtlzMonetaria(), ZERO,
                debitoPagamentoParcial.getValorReducaoMulta(), debitoPagamentoParcial.getValorReducaoJuros(),
                debitoPagamentoParcial.getTotalRecolher(), debitosContaCorrente.getIdContaCorrente(),
                debitoPagamentoParcial.getIsValorPagarDiferente());
    }

    /**
     * Converte um {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DebitoIcmsFreteViewBean}
     * em um {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEPagamento}.
     *
     * @param debitoIcmsFrete debito com pagamento ICMS frete que será convertido para
     *                        {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEPagamento}.
     * @return {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEPagamento} para DARE-e.
     */
    public DareEPagamento convertDebitoIcmsFreteToDareEPagamento(DebitoIcmsFreteViewBean debitoIcmsFrete) {
        int periodoReferencia = createPeriodoReferencia();
        LocalDate dataVencimento = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());

        return new DareEPagamento(debitoIcmsFrete.getNotaFiscal(), periodoReferencia, dataVencimento,
                170, null, getInformacoesComplementares(debitoIcmsFrete),
                debitoIcmsFrete.getValorImposto(), debitoIcmsFrete.getValorMulta(),
                ZERO, ZERO, ZERO, ZERO, ZERO, debitoIcmsFrete.getValorTotalFrete(), null, Boolean.FALSE);
    }

    /**
     * Converte um {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DebitoDiversoViewBean}
     * em um {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEPagamento}.
     *
     * @param debitoDiversoViewBean debito diverso que será convertido para
     *                              {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEPagamento}.
     * @return {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEPagamento} para DARE-e.
     */
    public DareEPagamento convertDebitoDiversoToDareEPagamento(DebitoDiversoViewBean debitoDiversoViewBean) {
        LocalDate dataVencimento = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        BigDecimal valorImposto = getValorImpostoFrom(debitoDiversoViewBean);

        return new DareEPagamento(debitoDiversoViewBean.getIdDocumento(), debitoDiversoViewBean.getPeriodoReferencia(),
                dataVencimento, debitoDiversoViewBean.getIdReceita(), debitoDiversoViewBean.getIdSubCodigo(),
                debitoDiversoViewBean.getInformacoesComplementares(), valorImposto,
                Optional.ofNullable(debitoDiversoViewBean.getValorMulta()).orElse(ZERO),
                Optional.ofNullable(debitoDiversoViewBean.getValorJuros()).orElse(ZERO),
                ZERO, ZERO, ZERO, ZERO, debitoDiversoViewBean.getTotalRecolher(), null, Boolean.FALSE);
    }

    private BigDecimal getValorImpostoFrom(DebitoDiversoViewBean debitoDiversoViewBean) {
        if (Objects.nonNull(debitoDiversoViewBean.getIdSubCodigo())) {
            return debitoDiversoViewBean.getValorTse();
        } else {
            return debitoDiversoViewBean.getValorImposto();
        }
    }

    private String getInformacoesComplementares(DebitoIcmsFreteViewBean debitoIcmsFrete) {
        String observacao = debitoIcmsFrete.getObservacoes();

        StringJoiner joiner = new StringJoiner(COMMA_DELIMITER);
        joiner.add(debitoIcmsFrete.getUfOrigem())
                .add(debitoIcmsFrete.getUfDestino())
                .add(debitoIcmsFrete.getNomeMunicipioOrigem())
                .add(debitoIcmsFrete.getNomeMunicipioDestino())
                .add(Optional.ofNullable(debitoIcmsFrete.getDistancia())
                        .orElse(DOUBLE_ZERO).toString())
                .add(Optional.ofNullable(debitoIcmsFrete.getPeso())
                        .orElse(DOUBLE_ZERO).toString())
                .add(Optional.ofNullable(debitoIcmsFrete.getNotaFiscal())
                        .orElse(LONG_ZERO).toString())
                .add(Optional.ofNullable(debitoIcmsFrete.getAliquota().getLabel())
                        .orElse(ZERO_PERCENT))
                .add(Optional.ofNullable(debitoIcmsFrete.getMulta().getLabel())
                        .orElse(ValorMultaEnum.ZERO_PORCENTO.getLabel()))
                .add(Optional.ofNullable(debitoIcmsFrete.getValorImposto())
                        .orElse(ZERO).toString())
                .add(Optional.ofNullable(debitoIcmsFrete.getValorMulta())
                        .orElse(ZERO).toString())
                .add(Optional.ofNullable(debitoIcmsFrete.getValorTotalFrete())
                        .orElse(ZERO).toString())
                .add(observacao);

        return joiner.toString();
    }

    private int createPeriodoReferencia() {
        LocalDate localDate = LocalDate.now();
        String year = String.valueOf(localDate.getYear());
        String month = String.valueOf(localDate.getMonthValue());
        return Integer.valueOf(year.concat(month));
    }
}
