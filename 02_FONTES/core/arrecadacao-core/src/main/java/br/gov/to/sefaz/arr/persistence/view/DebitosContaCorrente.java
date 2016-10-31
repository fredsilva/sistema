package br.gov.to.sefaz.arr.persistence.view;

import br.gov.to.sefaz.arr.dare.enums.SituacaoContaCorrenteEnum;
import br.gov.to.sefaz.arr.persistence.converter.OrigemDebitoEnumConverter;
import br.gov.to.sefaz.arr.persistence.converter.SituacaoContaCorrenteEnumConverter;
import br.gov.to.sefaz.arr.persistence.converter.TipoPessoaEnumConverter;
import br.gov.to.sefaz.arr.persistence.enums.OrigemDebitoEnum;
import br.gov.to.sefaz.arr.persistence.enums.TipoPessoaEnum;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * View referente a SEFAZ_ARR.VW_DEBITOS_CONTA_CORRENTE da base de dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 01/09/2016 14:49:42
 */
@Entity
@Table(name = "VW_DEBITOS_CONTA_CORRENTE", schema = "SEFAZ_ARR")
@SuppressWarnings({"PMD.GodClass", "PMD.TooManyFields"})
public class DebitosContaCorrente implements Serializable {

    private static final long serialVersionUID = 1972045406155994447L;

    @Id
    @Column(name = "ID_CONTA_CORRENTE")
    private Long idContaCorrente;

    @Convert(converter = OrigemDebitoEnumConverter.class)
    @Column(name = "TIPO_CONTA")
    private OrigemDebitoEnum tipoConta;

    @Convert(converter = TipoPessoaEnumConverter.class)
    @Column(name = "TIPO_PESSOA")
    private TipoPessoaEnum tipoPessoa;

    @Column(name = "ID_PESSOA")
    private Long idPessoa;

    @Column(name = "RENAVAM")
    private Long renavam;

    @Column(name = "DOCUMENTO")
    private Long documento;

    @Column(name = "RECEITA")
    private Integer receita;

    @Column(name = "PERIODO_REFERENCIA")
    private Integer periodoReferencia;

    @Column(name = "DATA_VENCIMENTO")
    private LocalDate dataVencimento;

    @Column(name = "VALOR_IMPOSTO")
    private BigDecimal valorImposto;

    @Column(name = "VALOR_CORRECAO_MONET")
    private BigDecimal valorCorrecaoMonet;

    @Column(name = "VALOR_MULTA")
    private BigDecimal valorMulta;

    @Column(name = "VALOR_REDUCAO_MULTA")
    private BigDecimal valorReducaoMulta;

    @Column(name = "VALOR_MULTA_FISCAL")
    private BigDecimal valorMultaFiscal;

    @Column(name = "VALOR_REDUCAO_MULTA_FISCAL")
    private BigDecimal valorReducaoMultaFiscal;

    @Column(name = "VALOR_JUROS")
    private BigDecimal valorJuros;

    @Column(name = "VALOR_REDUCAO_JUROS")
    private BigDecimal valorReducaoJuros;

    @Column(name = "VALOR_TOTAL_DEVIDO")
    private BigDecimal valorTotalDevido;

    @Convert(converter = SituacaoContaCorrenteEnumConverter.class)
    @Column(name = "SITUACAO")
    private SituacaoContaCorrenteEnum situacao;

    @Transient
    private Integer idSubcodigo;

    @Transient
    private String informacaoComplementar;

    public Long getIdContaCorrente() {
        return idContaCorrente;
    }

    public void setIdContaCorrente(Long idContaCorrente) {
        this.idContaCorrente = idContaCorrente;
    }

    public OrigemDebitoEnum getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(OrigemDebitoEnum tipoConta) {
        this.tipoConta = tipoConta;
    }

    public TipoPessoaEnum getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(TipoPessoaEnum tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public Long getRenavam() {
        return renavam;
    }

    public void setRenavam(Long renavam) {
        this.renavam = renavam;
    }

    public Long getDocumento() {
        return documento;
    }

    public void setDocumento(Long documento) {
        this.documento = documento;
    }

    public Integer getReceita() {
        return receita;
    }

    public void setReceita(Integer receita) {
        this.receita = receita;
    }

    public Integer getPeriodoReferencia() {
        return periodoReferencia;
    }

    public void setPeriodoReferencia(Integer periodoReferencia) {
        this.periodoReferencia = periodoReferencia;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public BigDecimal getValorImposto() {
        return valorImposto;
    }

    public void setValorImposto(BigDecimal valorImposto) {
        this.valorImposto = valorImposto;
    }

    public BigDecimal getValorCorrecaoMonet() {
        return valorCorrecaoMonet;
    }

    public void setValorCorrecaoMonet(BigDecimal valorCorrecaoMonet) {
        this.valorCorrecaoMonet = valorCorrecaoMonet;
    }

    public BigDecimal getValorMulta() {
        return valorMulta;
    }

    public void setValorMulta(BigDecimal valorMulta) {
        this.valorMulta = valorMulta;
    }

    public BigDecimal getValorReducaoMulta() {
        return valorReducaoMulta;
    }

    public void setValorReducaoMulta(BigDecimal valorReducaoMulta) {
        this.valorReducaoMulta = valorReducaoMulta;
    }

    public BigDecimal getValorMultaFiscal() {
        return valorMultaFiscal;
    }

    public void setValorMultaFiscal(BigDecimal valorMultaFiscal) {
        this.valorMultaFiscal = valorMultaFiscal;
    }

    public BigDecimal getValorReducaoMultaFiscal() {
        return valorReducaoMultaFiscal;
    }

    public void setValorReducaoMultaFiscal(BigDecimal valorReducaoMultaFiscal) {
        this.valorReducaoMultaFiscal = valorReducaoMultaFiscal;
    }

    public BigDecimal getValorJuros() {
        return valorJuros;
    }

    public void setValorJuros(BigDecimal valorJuros) {
        this.valorJuros = valorJuros;
    }

    public BigDecimal getValorReducaoJuros() {
        return valorReducaoJuros;
    }

    public void setValorReducaoJuros(BigDecimal valorReducaoJuros) {
        this.valorReducaoJuros = valorReducaoJuros;
    }

    public BigDecimal getValorTotalDevido() {
        return valorTotalDevido;
    }

    public void setValorTotalDevido(BigDecimal valorTotalDevido) {
        this.valorTotalDevido = valorTotalDevido;
    }

    public Integer getIdSubcodigo() {
        return idSubcodigo;
    }

    public void setIdSubcodigo(Integer idSubcodigo) {
        this.idSubcodigo = idSubcodigo;
    }

    public String getInformacaoComplementar() {
        return informacaoComplementar;
    }

    public void setInformacaoComplementar(String informacaoComplementar) {
        this.informacaoComplementar = informacaoComplementar;
    }

    public SituacaoContaCorrenteEnum getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoContaCorrenteEnum situacao) {
        this.situacao = situacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DebitosContaCorrente that = (DebitosContaCorrente) o;
        return Objects.equals(idContaCorrente, that.idContaCorrente)
                && Objects.equals(tipoConta, that.tipoConta)
                && Objects.equals(tipoPessoa, that.tipoPessoa)
                && Objects.equals(idPessoa, that.idPessoa)
                && Objects.equals(renavam, that.renavam)
                && Objects.equals(documento, that.documento)
                && Objects.equals(receita, that.receita)
                && Objects.equals(periodoReferencia, that.periodoReferencia)
                && Objects.equals(dataVencimento, that.dataVencimento)
                && Objects.equals(valorImposto, that.valorImposto)
                && Objects.equals(valorCorrecaoMonet, that.valorCorrecaoMonet)
                && Objects.equals(valorMulta, that.valorMulta)
                && Objects.equals(valorReducaoMulta, that.valorReducaoMulta)
                && Objects.equals(valorMultaFiscal, that.valorMultaFiscal)
                && Objects.equals(valorReducaoMultaFiscal, that.valorReducaoMultaFiscal)
                && Objects.equals(valorJuros, that.valorJuros)
                && Objects.equals(valorReducaoJuros, that.valorReducaoJuros)
                && Objects.equals(valorTotalDevido, that.valorTotalDevido)
                && Objects.equals(situacao, that.situacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idContaCorrente, tipoConta, tipoPessoa, idPessoa, renavam, documento, receita,
                periodoReferencia, dataVencimento, valorImposto, valorCorrecaoMonet, valorMulta, valorReducaoMulta,
                valorMultaFiscal, valorReducaoMultaFiscal, valorJuros, valorReducaoJuros, valorTotalDevido, situacao);
    }

    @Override
    public String toString() {
        return "DebitosContaCorrente{"
                + "idContaCorrente=" + idContaCorrente
                + ", tipoConta=" + tipoConta
                + ", tipoPessoa=" + tipoPessoa
                + ", idPessoa=" + idPessoa
                + ", renavam=" + renavam
                + ", documento=" + documento
                + ", receita=" + receita
                + ", periodoReferencia=" + periodoReferencia
                + ", dataVencimento=" + dataVencimento
                + ", valorImposto=" + valorImposto
                + ", valorCorrecaoMonet=" + valorCorrecaoMonet
                + ", valorMulta=" + valorMulta
                + ", valorReducaoMulta=" + valorReducaoMulta
                + ", valorMultaFiscal=" + valorMultaFiscal
                + ", valorReducaoMultaFiscal=" + valorReducaoMultaFiscal
                + ", valorJuros=" + valorJuros
                + ", valorReducaoJuros=" + valorReducaoJuros
                + ", valorTotalDevido=" + valorTotalDevido
                + ", situacao=" + situacao
                + '}';
    }
}
