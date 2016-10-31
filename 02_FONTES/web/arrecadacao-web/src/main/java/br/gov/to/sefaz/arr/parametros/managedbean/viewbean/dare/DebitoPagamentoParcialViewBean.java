package br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare;

import br.gov.to.sefaz.arr.persistence.view.DebitosContaCorrente;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 * View Bean de Débitos com Pagemento Parcial referente ao painel Dados do Pagamento do Gerar DARE-e.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 31/08/2016 14:34:00
 */
public class DebitoPagamentoParcialViewBean {

    private List<DebitosContaCorrente> debitosContaCorrente;
    private Long idContaCorrente;

    @NotNull(message = "#{arr_msg['arr.par.dareEletronicoConsolidado.dadoPagamento.documento.empty']}")
    private Long idDocumento;

    @NotNull(message = "#{arr_msg['arr.par.dareEletronicoConsolidado.dadoPagamento.receita.empty']}")
    private Integer idReceita;

    private Integer periodoReferencia;
    private Boolean isValorPagarDiferente;
    private BigDecimal valorImposto;
    private BigDecimal valorMulta;
    private BigDecimal valorJuros;
    private BigDecimal valorAtlzMonetaria;
    private BigDecimal valorReducaoMulta;
    private BigDecimal valorReducaoJuros;
    private BigDecimal valorPagar;
    @DecimalMin(value = "0.01",
            message = "#{arr_msg['arr.par.dare.debitoPagamentoParcialViewBean.totalRecolher.greaterThanZero']}")
    @DecimalMax(value = "999999999.99",
            message = "#{arr_msg['arr.par.dare.dareEPagamento.totalRecolher.limit']}")
    private BigDecimal totalRecolher;
    private String informacaoComplementar;

    public List<DebitosContaCorrente> getDebitosContaCorrente() {
        return debitosContaCorrente;
    }

    public void setDebitosContaCorrente(List<DebitosContaCorrente> debitosContaCorrente) {
        this.debitosContaCorrente = debitosContaCorrente;
    }

    public Long getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Long idDocumento) {
        this.idDocumento = idDocumento;
    }

    public Long getIdContaCorrente() {
        return idContaCorrente;
    }

    public void setIdContaCorrente(Long idContaCorrente) {
        this.idContaCorrente = idContaCorrente;
    }

    public Integer getIdReceita() {
        return idReceita;
    }

    public void setIdReceita(Integer idReceita) {
        this.idReceita = idReceita;
    }

    public Integer getPeriodoReferencia() {
        return periodoReferencia;
    }

    public void setPeriodoReferencia(Integer periodoReferencia) {
        this.periodoReferencia = periodoReferencia;
    }

    public Boolean getIsValorPagarDiferente() {
        return isValorPagarDiferente;
    }

    public void setIsValorPagarDiferente(Boolean isvalorPagardiferente) {
        this.isValorPagarDiferente = isvalorPagardiferente;
    }

    public BigDecimal getValorImposto() {
        return Optional.ofNullable(valorImposto).orElse(BigDecimal.ZERO);
    }

    public void setValorImposto(BigDecimal valorImposto) {
        this.valorImposto = valorImposto;
    }

    public BigDecimal getValorMulta() {
        return Optional.ofNullable(valorMulta).orElse(BigDecimal.ZERO);
    }

    public void setValorMulta(BigDecimal valorMulta) {
        this.valorMulta = valorMulta;
    }

    public BigDecimal getValorJuros() {
        return Optional.ofNullable(valorJuros).orElse(BigDecimal.ZERO);
    }

    public void setValorJuros(BigDecimal valorJuros) {
        this.valorJuros = valorJuros;
    }

    public BigDecimal getValorAtlzMonetaria() {
        return Optional.ofNullable(valorAtlzMonetaria).orElse(BigDecimal.ZERO);
    }

    public void setValorAtlzMonetaria(BigDecimal valorAtlzMonetaria) {
        this.valorAtlzMonetaria = valorAtlzMonetaria;
    }

    public BigDecimal getValorReducaoMulta() {
        return Optional.ofNullable(valorReducaoMulta).orElse(BigDecimal.ZERO);
    }

    public void setValorReducaoMulta(BigDecimal valorReducaoMulta) {
        this.valorReducaoMulta = valorReducaoMulta;
    }

    public BigDecimal getValorReducaoJuros() {
        return Optional.ofNullable(valorReducaoJuros).orElse(BigDecimal.ZERO);
    }

    public void setValorReducaoJuros(BigDecimal valorReducaoJuros) {
        this.valorReducaoJuros = valorReducaoJuros;
    }

    public BigDecimal getValorPagar() {
        return Optional.ofNullable(valorPagar).orElse(BigDecimal.ZERO);
    }

    public void setValorPagar(BigDecimal valorPagar) {
        this.valorPagar = valorPagar;
    }

    public BigDecimal getTotalRecolher() {
        return Optional.ofNullable(totalRecolher).orElse(BigDecimal.ZERO);
    }

    public void setTotalRecolher(BigDecimal totalRecolher) {
        this.totalRecolher = totalRecolher;
    }

    public String getInformacaoComplementar() {
        return informacaoComplementar;
    }

    public void setInformacaoComplementar(String informacaoComplementar) {
        this.informacaoComplementar = informacaoComplementar;
    }

    public Boolean getValorPagarDiferente() {
        return isValorPagarDiferente;
    }

    public void setValorPagarDiferente(Boolean valorPagarDiferente) {
        isValorPagarDiferente = valorPagarDiferente;
    }
}
