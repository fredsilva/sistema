package br.gov.to.sefaz.arr.persistence.entity;

import br.gov.to.sefaz.arr.persistence.converter.SituacaoConciliacaoConverter;
import br.gov.to.sefaz.arr.persistence.enums.SituacaoConciliacaoEnum;
import br.gov.to.sefaz.persistence.entity.AbstractEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import static org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateTimeConverter;

/**
 * Entidade que representa os dados da tabela SEFAZ_ARR.TA_RESUMO_STR.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 21/06/2016 18:25:42
 */
@Entity
@Table(name = "TA_RESUMO_STR", schema = "SEFAZ_ARR")
@IdClass(ResumoStrPK.class)
public class ResumoStr extends AbstractEntity<ResumoStrPK> {

    private static final long serialVersionUID = -4444077428506112973L;

    @Convert(converter = LocalDateTimeConverter.class)
    @Column(name = "DATA_ARRECADACAO")
    private LocalDateTime dataArrecadacao;

    @Id
    @NotNull
    @Column(name = "ID_BANCO")
    private Integer idBanco;

    @Id
    @NotNull
    @Column(name = "ID_CONVENIO")
    private Long idConvenio;

    @Column(name = "QUANTIDADE_RECEPCIONAD_PARCIAL")
    private Long quantidadeRecepcionadParcial;

    @Column(name = "VALOR_RECEPCIONADO_PARCIAL")
    private BigDecimal valorRecepcionadoParcial;

    @Column(name = "QUANTIDADE_RECEPCIONADA")
    private Long quantidadeRecepcionada;

    @Column(name = "VALOR_RECEPCIONADO")
    private BigDecimal valorRecepcionado;

    @Column(name = "QUANTIDADE_ARRECADADO_PARCIAL")
    private Long quantidadeArrecadadoParcial;

    @Column(name = "VALOR_ARRECADADO_PARCIAL")
    private BigDecimal valorArrecadadoParcial;

    @Column(name = "QUANTIDADE_ARRECADADA")
    private Long quantidadeArrecadada;

    @Column(name = "VALOR_ARRECADADO")
    private BigDecimal valorArrecadado;

    @Convert(converter = LocalDateTimeConverter.class)
    @Column(name = "DATA_CONSOLIDADO")
    private LocalDateTime dataConsolidado;

    @Column(name = "VALOR_LANCAMENTO_STR")
    private BigDecimal valorLancamentoStr;

    @NotNull
    @Convert(converter = SituacaoConciliacaoConverter.class)
    @Column(name = "SITUACAO")
    private SituacaoConciliacaoEnum situacao;

    @NotNull
    @Convert(converter = LocalDateTimeConverter.class)
    @Column(name = "DATA_PROCESSAMENTO")
    private LocalDateTime dataProcessamento;

    @Override
    public ResumoStrPK getId() {
        return new ResumoStrPK(getDataArrecadacao(), getIdBanco(), getIdConvenio());
    }

    public LocalDateTime getDataArrecadacao() {
        return dataArrecadacao;
    }

    public void setDataArrecadacao(LocalDateTime dataArrecadacao) {
        this.dataArrecadacao = dataArrecadacao;
    }

    public Integer getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(Integer idBanco) {
        this.idBanco = idBanco;
    }

    public Long getIdConvenio() {
        return idConvenio;
    }

    public void setIdConvenio(Long idConvenio) {
        this.idConvenio = idConvenio;
    }

    public Long getQuantidadeRecepcionadParcial() {
        return quantidadeRecepcionadParcial;
    }

    public void setQuantidadeRecepcionadParcial(Long quantidadeRecepcionadParcial) {
        this.quantidadeRecepcionadParcial = quantidadeRecepcionadParcial;
    }

    public BigDecimal getValorRecepcionadoParcial() {
        return valorRecepcionadoParcial;
    }

    public void setValorRecepcionadoParcial(BigDecimal valorRecepcionadoParcial) {
        this.valorRecepcionadoParcial = valorRecepcionadoParcial;
    }

    public Long getQuantidadeRecepcionada() {
        return quantidadeRecepcionada;
    }

    public void setQuantidadeRecepcionada(Long quantidadeRecepcionada) {
        this.quantidadeRecepcionada = quantidadeRecepcionada;
    }

    public BigDecimal getValorRecepcionado() {
        return valorRecepcionado;
    }

    public void setValorRecepcionado(BigDecimal valorRecepcionado) {
        this.valorRecepcionado = valorRecepcionado;
    }

    public Long getQuantidadeArrecadadoParcial() {
        return quantidadeArrecadadoParcial;
    }

    public void setQuantidadeArrecadadoParcial(Long quantidadeArrecadadoParcial) {
        this.quantidadeArrecadadoParcial = quantidadeArrecadadoParcial;
    }

    public BigDecimal getValorArrecadadoParcial() {
        return valorArrecadadoParcial;
    }

    public void setValorArrecadadoParcial(BigDecimal valorArrecadadoParcial) {
        this.valorArrecadadoParcial = valorArrecadadoParcial;
    }

    public Long getQuantidadeArrecadada() {
        return quantidadeArrecadada;
    }

    public void setQuantidadeArrecadada(Long quantidadeArrecadada) {
        this.quantidadeArrecadada = quantidadeArrecadada;
    }

    public BigDecimal getValorArrecadado() {
        return valorArrecadado;
    }

    public void setValorArrecadado(BigDecimal valorArrecadado) {
        this.valorArrecadado = valorArrecadado;
    }

    public LocalDateTime getDataConsolidado() {
        return dataConsolidado;
    }

    public void setDataConsolidado(LocalDateTime dataConsolidado) {
        this.dataConsolidado = dataConsolidado;
    }

    public BigDecimal getValorLancamentoStr() {
        return valorLancamentoStr;
    }

    public void setValorLancamentoStr(BigDecimal valorLancamentoStr) {
        this.valorLancamentoStr = valorLancamentoStr;
    }

    public SituacaoConciliacaoEnum getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoConciliacaoEnum situacao) {
        this.situacao = situacao;
    }

    public LocalDateTime getDataProcessamento() {
        return dataProcessamento;
    }

    public void setDataProcessamento(LocalDateTime dataProcessamento) {
        this.dataProcessamento = dataProcessamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ResumoStr resumoStr = (ResumoStr) o;
        return Objects.equals(dataArrecadacao, resumoStr.dataArrecadacao)
                && Objects.equals(idBanco, resumoStr.idBanco)
                && Objects.equals(idConvenio, resumoStr.idConvenio)
                && Objects.equals(quantidadeRecepcionadParcial, resumoStr.quantidadeRecepcionadParcial)
                && Objects.equals(valorRecepcionadoParcial, resumoStr.valorRecepcionadoParcial)
                && Objects.equals(quantidadeRecepcionada, resumoStr.quantidadeRecepcionada)
                && Objects.equals(valorRecepcionado, resumoStr.valorRecepcionado)
                && Objects.equals(quantidadeArrecadadoParcial, resumoStr.quantidadeArrecadadoParcial)
                && Objects.equals(valorArrecadadoParcial, resumoStr.valorArrecadadoParcial)
                && Objects.equals(quantidadeArrecadada, resumoStr.quantidadeArrecadada)
                && Objects.equals(valorArrecadado, resumoStr.valorArrecadado)
                && Objects.equals(dataConsolidado, resumoStr.dataConsolidado)
                && Objects.equals(valorLancamentoStr, resumoStr.valorLancamentoStr)
                && situacao == resumoStr.situacao
                && Objects.equals(dataProcessamento, resumoStr.dataProcessamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataArrecadacao, idBanco, idConvenio, quantidadeRecepcionadParcial,
                valorRecepcionadoParcial, quantidadeRecepcionada, valorRecepcionado, quantidadeArrecadadoParcial,
                valorArrecadadoParcial, quantidadeArrecadada, valorArrecadado, dataConsolidado, valorLancamentoStr,
                situacao, dataProcessamento);
    }

    @Override
    public String toString() {
        return "ResumoStr{"
                + "dataArrecadacao=" + dataArrecadacao
                + ", idBanco=" + idBanco
                + ", idConvenio=" + idConvenio
                + ", quantidadeRecepcionadParcial=" + quantidadeRecepcionadParcial
                + ", valorRecepcionadoParcial=" + valorRecepcionadoParcial
                + ", quantidadeRecepcionada=" + quantidadeRecepcionada
                + ", valorRecepcionado=" + valorRecepcionado
                + ", quantidadeArrecadadoParcial=" + quantidadeArrecadadoParcial
                + ", valorArrecadadoParcial=" + valorArrecadadoParcial
                + ", quantidadeArrecadada=" + quantidadeArrecadada
                + ", valorArrecadado=" + valorArrecadado
                + ", dataConsolidado=" + dataConsolidado
                + ", valorLancamentoStr=" + valorLancamentoStr
                + ", situacao=" + situacao
                + ", dataProcessamento=" + dataProcessamento
                + '}';
    }
}
