package br.gov.to.sefaz.arr.persistence.entity;

import br.gov.to.sefaz.persistence.entity.AbstractEntity;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entidade que representa os dados da tabela SEFAZ_ARR.TA_DARE_DETALHE.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 22/06/2016 10:57:41
 */
@SuppressWarnings("PMD")
@Entity
@Table(name = "TA_DARE_DETALHE", schema = "SEFAZ_ARR")
@IdClass(DareDetalhePK.class)
public class DareDetalhe extends AbstractEntity<DareDetalhePK> {

    private static final long serialVersionUID = -8549793202091736110L;

    @Id
    @NotNull
    @Column(name = "ID_NOSSO_NUMERO_DARE")
    private Long idNossoNumeroDare;

    @Id
    @NotNull
    @Column(name = "ID_SEQ_DARE_DETALHE")
    private Integer idSeqDareDetalhe;

    @NotNull
    @Column(name = "ID_RECEITA")
    private Integer idReceita;

    @Column(name = "ID_SUBCODIGO")
    private Integer idSubcodigo;

    @Size(max = 1000)
    @Column(name = "OBSERVACAO")
    private String observacao;

    @NotNull
    @Column(name = "PERIODO_REFERENCIA")
    private Integer periodoReferencia;

    @Column(name = "NUMERO_DOCUMENTO")
    private Long numeroDocumento;

    @Column(name = "NUMERO_PARCELA")
    private Integer numeroParcela;

    @Column(name = "VALOR_IMPOSTO")
    private BigDecimal valorImposto;

    @Column(name = "VALOR_CORRECAO_MONETARIA")
    private BigDecimal valorCorrecaoMonetaria;

    @Column(name = "VALOR_MULTA")
    private BigDecimal valorMulta;

    @Column(name = "VALOR_REDUCAO_MULTA")
    private BigDecimal valorReducaoMulta;

    @Column(name = "VALOR_JUROS")
    private BigDecimal valorJuros;

    @Column(name = "VALOR_REDUCAO_JUROS")
    private BigDecimal valorReducaoJuros;

    @Column(name = "VALOR_TAXA")
    private BigDecimal valorTaxa;

    @NotNull
    @Column(name = "VALOR_TOTAL")
    private BigDecimal valorTotal;

    @Column(name = "INFORMADO_CONTRIBUINTE")
    private Integer informadoContribuinte;

    @Column(name = "ID_PESSOA_REFERENCIADA")
    private Long idPessoaReferenciada;

    @Column(name = "TIPO_PESSOA_REFERENCIADA")
    private Integer tipoPessoaReferenciada;

    @Column(name = "ID_CONTA_CORRENTE")
    private Long idContaCorrente;

    @Column(name = "ID_MUNICIPIO")
    private Integer idMunicipio;

    @JoinColumn(name = "ID_NOSSO_NUMERO_DARE", referencedColumnName = "ID_NOSSO_NUMERO_DARE",
            insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Dare dare;

    @Transient
    private Collection<PagosArrec> pagosArrecCollection;

    @Override
    public DareDetalhePK getId() {
        return new DareDetalhePK();
    }

    public Long getIdNossoNumeroDare() {
        return idNossoNumeroDare;
    }

    public void setIdNossoNumeroDare(Long idNossoNumeroDare) {
        this.idNossoNumeroDare = idNossoNumeroDare;
    }

    public Integer getIdSeqDareDetalhe() {
        return idSeqDareDetalhe;
    }

    public void setIdSeqDareDetalhe(Integer idSeqDareDetalhe) {
        this.idSeqDareDetalhe = idSeqDareDetalhe;
    }

    public Integer getIdReceita() {
        return idReceita;
    }

    public void setIdReceita(Integer idReceita) {
        this.idReceita = idReceita;
    }

    public Integer getIdSubcodigo() {
        return idSubcodigo;
    }

    public void setIdSubcodigo(Integer idSubcodigo) {
        this.idSubcodigo = idSubcodigo;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Integer getPeriodoReferencia() {
        return periodoReferencia;
    }

    public void setPeriodoReferencia(Integer periodoReferencia) {
        this.periodoReferencia = periodoReferencia;
    }

    public Long getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(Long numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public Integer getNumeroParcela() {
        return numeroParcela;
    }

    public void setNumeroParcela(Integer numeroParcela) {
        this.numeroParcela = numeroParcela;
    }

    public BigDecimal getValorImposto() {
        return valorImposto;
    }

    public void setValorImposto(BigDecimal valorImposto) {
        this.valorImposto = valorImposto;
    }

    public BigDecimal getValorCorrecaoMonetaria() {
        return valorCorrecaoMonetaria;
    }

    public void setValorCorrecaoMonetaria(BigDecimal valorCorrecaoMonetaria) {
        this.valorCorrecaoMonetaria = valorCorrecaoMonetaria;
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

    public BigDecimal getValorTaxa() {
        return valorTaxa;
    }

    public void setValorTaxa(BigDecimal valorTaxa) {
        this.valorTaxa = valorTaxa;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Integer getInformadoContribuinte() {
        return informadoContribuinte;
    }

    public void setInformadoContribuinte(Integer informadoContribuinte) {
        this.informadoContribuinte = informadoContribuinte;
    }

    public Long getIdPessoaReferenciada() {
        return idPessoaReferenciada;
    }

    public void setIdPessoaReferenciada(Long idPessoaReferenciada) {
        this.idPessoaReferenciada = idPessoaReferenciada;
    }

    public Integer getTipoPessoaReferenciada() {
        return tipoPessoaReferenciada;
    }

    public void setTipoPessoaReferenciada(Integer tipoPessoaReferenciada) {
        this.tipoPessoaReferenciada = tipoPessoaReferenciada;
    }

    public Long getIdContaCorrente() {
        return idContaCorrente;
    }

    public void setIdContaCorrente(Long idContaCorrente) {
        this.idContaCorrente = idContaCorrente;
    }

    public Integer getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public Dare getDare() {
        return dare;
    }

    public void setDare(Dare dare) {
        this.dare = dare;
    }

    public Collection<PagosArrec> getPagosArrecCollection() {
        return pagosArrecCollection;
    }

    public void setPagosArrecCollection(Collection<PagosArrec> pagosArrecCollection) {
        this.pagosArrecCollection = pagosArrecCollection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DareDetalhe that = (DareDetalhe) o;
        return Objects.equals(idReceita, that.idReceita)
                && Objects.equals(idNossoNumeroDare, that.idNossoNumeroDare)
                && Objects.equals(idSeqDareDetalhe, that.idSeqDareDetalhe)
                && Objects.equals(idSubcodigo, that.idSubcodigo)
                && Objects.equals(observacao, that.observacao)
                && Objects.equals(periodoReferencia, that.periodoReferencia)
                && Objects.equals(numeroDocumento, that.numeroDocumento)
                && Objects.equals(numeroParcela, that.numeroParcela)
                && Objects.equals(valorImposto, that.valorImposto)
                && Objects.equals(valorCorrecaoMonetaria, that.valorCorrecaoMonetaria)
                && Objects.equals(valorMulta, that.valorMulta)
                && Objects.equals(valorReducaoMulta, that.valorReducaoMulta)
                && Objects.equals(valorJuros, that.valorJuros)
                && Objects.equals(valorReducaoJuros, that.valorReducaoJuros)
                && Objects.equals(valorTaxa, that.valorTaxa)
                && Objects.equals(valorTotal, that.valorTotal)
                && Objects.equals(informadoContribuinte, that.informadoContribuinte)
                && Objects.equals(idPessoaReferenciada, that.idPessoaReferenciada)
                && Objects.equals(tipoPessoaReferenciada, that.tipoPessoaReferenciada)
                && Objects.equals(idContaCorrente, that.idContaCorrente)
                && Objects.equals(idMunicipio, that.idMunicipio)
                && Objects.equals(dare, that.dare)
                && Objects.equals(pagosArrecCollection, that.pagosArrecCollection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idNossoNumeroDare, idSeqDareDetalhe, idReceita, idSubcodigo, observacao, periodoReferencia,
                numeroDocumento, numeroParcela, valorImposto, valorCorrecaoMonetaria, valorMulta, valorReducaoMulta,
                valorJuros, valorReducaoJuros, valorTaxa, valorTotal, informadoContribuinte, idPessoaReferenciada,
                tipoPessoaReferenciada, idContaCorrente, idMunicipio, dare, pagosArrecCollection);
    }

    @Override
    public String toString() {
        return "DareDetalhe{"
                + "idNossoNumeroDare=" + idNossoNumeroDare
                + ", idSeqDareDetalhe=" + idSeqDareDetalhe
                + ", idReceita=" + idReceita
                + ", idSubcodigo=" + idSubcodigo
                + ", observacao='" + observacao + '\''
                + ", periodoReferencia=" + periodoReferencia
                + ", numeroDocumento=" + numeroDocumento
                + ", numeroParcela=" + numeroParcela
                + ", valorImposto=" + valorImposto
                + ", valorCorrecaoMonetaria=" + valorCorrecaoMonetaria
                + ", valorMulta=" + valorMulta
                + ", valorReducaoMulta=" + valorReducaoMulta
                + ", valorJuros=" + valorJuros
                + ", valorReducaoJuros=" + valorReducaoJuros
                + ", valorTaxa=" + valorTaxa
                + ", valorTotal=" + valorTotal
                + ", informadoContribuinte=" + informadoContribuinte
                + ", idPessoaReferenciada=" + idPessoaReferenciada
                + ", tipoPessoaReferenciada=" + tipoPessoaReferenciada
                + ", idContaCorrente=" + idContaCorrente
                + ", idMunicipio=" + idMunicipio
                + ", dare=" + dare
                + ", pagosArrecCollection=" + pagosArrecCollection
                + '}';
    }
}
