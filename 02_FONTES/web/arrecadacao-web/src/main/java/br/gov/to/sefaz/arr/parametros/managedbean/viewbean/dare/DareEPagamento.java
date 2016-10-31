package br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare;

import br.gov.to.sefaz.util.formatter.FormatterUtil;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Classe que representa um valor da lista de pagamentos adicionados no DARE.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 27/09/2016 14:38:00
 */
@SuppressWarnings("PMD.TooManyFields")
public class DareEPagamento {

    private Integer id;
    private Long documento;
    private Integer periodoAno;
    private LocalDate dataVencimento;
    private Integer receita;
    private Integer idReceitaTaxa;
    private Integer idSubcodigo;
    private String informacaoComplementar;
    private BigDecimal valorImposto;
    private BigDecimal valorMulta;
    private BigDecimal valorJuros;
    private BigDecimal valorCorrecaoMonetaria;
    private BigDecimal valorTse;
    private BigDecimal valorReducaoMulta;
    private BigDecimal valorReducaoJuros;
    private BigDecimal valorTotal;
    private Long idContaCorrente;
    private Boolean informadoContribuinte;
    private Boolean selected;

    public DareEPagamento(Long documento, Integer periodoAno, LocalDate dataVencimento, Integer receita,
            Integer idSubcodigo, String informacaoComplementar,
            BigDecimal valorImposto, BigDecimal valorMulta,
            BigDecimal valorJuros, BigDecimal valorCorrecaoMonetaria,
            BigDecimal valorTse, BigDecimal valorReducaoMulta,
            BigDecimal valorReducaoJuros, BigDecimal valorTotal, Long idContaCorrente, Boolean informadoContribuinte) {
        this.documento = documento;
        this.periodoAno = periodoAno;
        this.dataVencimento = dataVencimento;
        this.receita = receita;
        this.idSubcodigo = idSubcodigo;
        this.informacaoComplementar = informacaoComplementar;
        this.valorImposto = valorImposto;
        this.valorMulta = valorMulta;
        this.valorJuros = valorJuros;
        this.valorCorrecaoMonetaria = valorCorrecaoMonetaria;
        this.valorTse = valorTse;
        this.valorReducaoMulta = valorReducaoMulta;
        this.valorReducaoJuros = valorReducaoJuros;
        this.valorTotal = valorTotal;
        this.idContaCorrente = idContaCorrente;
        this.informadoContribuinte = informadoContribuinte;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getDocumento() {
        return documento;
    }

    public void setDocumento(Long documento) {
        this.documento = documento;
    }

    public Integer getPeriodoAno() {
        return periodoAno;
    }

    public void setPeriodoAno(Integer periodoAno) {
        this.periodoAno = periodoAno;
    }

    public String getPeriodoAnoFormatted() {
        return FormatterUtil.formatPeriodoMesAno(periodoAno);
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Integer getReceita() {
        return receita;
    }

    public void setReceita(Integer receita) {
        this.receita = receita;
    }

    public Integer getIdReceitaTaxa() {
        return idReceitaTaxa;
    }

    public void setIdReceitaTaxa(Integer idReceitaTaxa) {
        this.idReceitaTaxa = idReceitaTaxa;
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

    public BigDecimal getValorCorrecaoMonetaria() {
        return valorCorrecaoMonetaria;
    }

    public void setValorCorrecaoMonetaria(BigDecimal valorCorrecaoMonetaria) {
        this.valorCorrecaoMonetaria = valorCorrecaoMonetaria;
    }

    public BigDecimal getValorTse() {
        return valorTse;
    }

    public void setValorTse(BigDecimal valorTse) {
        this.valorTse = valorTse;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getValorReducaoMulta() {
        return valorReducaoMulta;
    }

    public void setValorReducaoMulta(BigDecimal valorReducaoMulta) {
        this.valorReducaoMulta = valorReducaoMulta;
    }

    public BigDecimal getValorReducaoJuros() {
        return valorReducaoJuros;
    }

    public void setValorReducaoJuros(BigDecimal valorReducaoJuros) {
        this.valorReducaoJuros = valorReducaoJuros;
    }

    public String getInformacaoComplementar() {
        return informacaoComplementar;
    }

    public void setInformacaoComplementar(String informacaoComplementar) {
        this.informacaoComplementar = informacaoComplementar;
    }

    public Integer getIdSubcodigo() {
        return idSubcodigo;
    }

    public void setIdSubcodigo(Integer idSubcodigo) {
        this.idSubcodigo = idSubcodigo;
    }

    public Long getIdContaCorrente() {
        return idContaCorrente;
    }

    public void setIdContaCorrente(Long idContaCorrente) {
        this.idContaCorrente = idContaCorrente;
    }

    public BigDecimal getValorReducao() {
        return valorReducaoJuros.add(valorReducaoMulta);
    }

    public Boolean getInformadoContribuinte() {
        return informadoContribuinte;
    }

    public void setInformadoContribuinte(Boolean informadoContribuinte) {
        this.informadoContribuinte = informadoContribuinte;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}
