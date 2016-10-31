package br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare;

import br.gov.to.sefaz.arr.persistence.entity.Receitas;
import br.gov.to.sefaz.arr.persistence.entity.ReceitasTaxas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 * View Bean de Débitos Diversos referente ao painel Dados do Pagamento do Gerar DARE-e.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 31/08/2016 15:06:00
 */
public class DebitoDiversoViewBean {

    private Long idDocumento;
    @NotNull(message = "#{arr_msg['arr.par.dare.debitoDiversoViewBean.receita.empty']}")
    private Integer idReceita;
    private Integer idSubCodigo;
    private Integer quantidadeTse;
    private BigDecimal valorUnitarioTse;
    private BigDecimal valorTse;
    @NotNull(message = "#{arr_msg['arr.par.dare.debitoDiversoViewBean.periodoReferencia.empty']}")
    private Integer periodoReferencia;
    private BigDecimal valorImposto;
    private BigDecimal valorMulta;
    private BigDecimal valorJuros;
    @DecimalMin(value = "0.01",
            message = "#{arr_msg['arr.par.dare.debitoDiversoViewBean.totalRecolher.greaterThanZero']}")
    @DecimalMax(value = "999999999.99",
            message = "#{arr_msg['arr.par.dare.dareEPagamento.totalRecolher.limit']}")
    private BigDecimal totalRecolher;
    private String informacoesComplementares;
    private Collection<Receitas> receitas;
    private Collection<ReceitasTaxas> receitasTaxas;

    public DebitoDiversoViewBean() {
        this.receitas = new ArrayList<>();
        this.receitasTaxas = new ArrayList<>();
    }

    public Long getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Long idDocumento) {
        this.idDocumento = idDocumento;
    }

    public Integer getIdReceita() {
        return idReceita;
    }

    public void setIdReceita(Integer idReceita) {
        this.idReceita = idReceita;
    }

    public Integer getIdSubCodigo() {
        return idSubCodigo;
    }

    public void setIdSubCodigo(Integer idSubCodigo) {
        this.idSubCodigo = idSubCodigo;
    }

    public Integer getQuantidadeTse() {
        return quantidadeTse;
    }

    public void setQuantidadeTse(Integer quantidadeTse) {
        this.quantidadeTse = quantidadeTse;
    }

    public Integer getPeriodoReferencia() {
        return periodoReferencia;
    }

    public void setPeriodoReferencia(Integer periodoReferencia) {
        this.periodoReferencia = periodoReferencia;
    }

    public BigDecimal getValorUnitarioTse() {
        return valorUnitarioTse;
    }

    public void setValorUnitarioTse(BigDecimal valorUnitarioTse) {
        this.valorUnitarioTse = valorUnitarioTse;
    }

    public BigDecimal getValorTse() {
        return valorTse;
    }

    public void setValorTse(BigDecimal valorTse) {
        this.valorTse = valorTse;
    }

    public BigDecimal getValorImposto() {
        return valorImposto;
    }

    public void setValorImposto(BigDecimal valorImposto) {
        this.valorImposto = valorImposto;
    }

    public BigDecimal getValorMulta() {
        return valorMulta;
    }

    public void setValorMulta(BigDecimal valorMulta) {
        this.valorMulta = valorMulta;
    }

    public BigDecimal getValorJuros() {
        return valorJuros;
    }

    public void setValorJuros(BigDecimal valorJuros) {
        this.valorJuros = valorJuros;
    }

    public BigDecimal getTotalRecolher() {
        return totalRecolher;
    }

    public void setTotalRecolher(BigDecimal totalRecolher) {
        this.totalRecolher = totalRecolher;
    }

    public String getInformacoesComplementares() {
        return informacoesComplementares;
    }

    public void setInformacoesComplementares(String informacoesComplementares) {
        this.informacoesComplementares = informacoesComplementares;
    }

    public Collection<Receitas> getReceitas() {
        return receitas;
    }

    public void setReceitas(Collection<Receitas> receitas) {
        this.receitas = receitas;
    }

    public Collection<ReceitasTaxas> getReceitasTaxas() {
        return receitasTaxas;
    }

    public void setReceitasTaxas(Collection<ReceitasTaxas> receitasTaxas) {
        this.receitasTaxas = receitasTaxas;
    }
}
