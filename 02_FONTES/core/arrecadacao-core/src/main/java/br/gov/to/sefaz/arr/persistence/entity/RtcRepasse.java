package br.gov.to.sefaz.arr.persistence.entity;


import br.gov.to.sefaz.persistence.entity.AbstractEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
 * Entidade que representa os dados da tabela SEFAZ_ARR.TA_RTC_REPASSE.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 22/06/2016 10:57:41
 */
@Entity
@Table(name = "TA_RTC_REPASSE", schema = "SEFAZ_ARR")
public class RtcRepasse extends AbstractEntity<Long> {

    private static final long serialVersionUID = -7436811184759407160L;

    @Id
    @NotNull
    @Column(name = "ID_REPASSE")
    private Long idRepasse;

    @NotNull
    @Column(name = "TIPO")
    private Integer tipo;

    @NotNull
    @Column(name = "DATA_INICIO")
    private LocalDate dataInicio;

    @NotNull
    @Column(name = "DATA_FINAL")
    private LocalDate dataFinal;

    @NotNull
    @Column(name = "VALOR_TOTAL_ARRECADADO")
    private BigDecimal valorTotalArrecadado;

    @NotNull
    @Column(name = "VALOR_TOTAL_AJUSTES")
    private BigDecimal valorTotalAjustes;

    @Transient
    private Collection<PagosArrec> pagosArrecCollection;

    @Override
    public Long getId() {
        return getIdRepasse();
    }

    public Long getIdRepasse() {
        return idRepasse;
    }

    public void setIdRepasse(Long idRepasse) {
        this.idRepasse = idRepasse;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public BigDecimal getValorTotalArrecadado() {
        return valorTotalArrecadado;
    }

    public void setValorTotalArrecadado(BigDecimal valorTotalArrecadado) {
        this.valorTotalArrecadado = valorTotalArrecadado;
    }

    public BigDecimal getValorTotalAjustes() {
        return valorTotalAjustes;
    }

    public void setValorTotalAjustes(BigDecimal valorTotalAjustes) {
        this.valorTotalAjustes = valorTotalAjustes;
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
        RtcRepasse that = (RtcRepasse) o;
        return Objects.equals(idRepasse, that.idRepasse)
                && Objects.equals(tipo, that.tipo)
                && Objects.equals(dataInicio, that.dataInicio)
                && Objects.equals(dataFinal, that.dataFinal)
                && Objects.equals(valorTotalArrecadado, that.valorTotalArrecadado)
                && Objects.equals(valorTotalAjustes, that.valorTotalAjustes)
                && Objects.equals(pagosArrecCollection, that.pagosArrecCollection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRepasse, tipo, dataInicio, dataFinal, valorTotalArrecadado, valorTotalAjustes,
                pagosArrecCollection);
    }

    @Override
    public String toString() {
        return "RtcRepasse{"
                + "idRepasse=" + idRepasse
                + ", tipo=" + tipo
                + ", dataInicio=" + dataInicio
                + ", dataFinal=" + dataFinal
                + ", valorTotalArrecadado=" + valorTotalArrecadado
                + ", valorTotalAjustes=" + valorTotalAjustes
                + ", pagosArrecCollection=" + pagosArrecCollection
                + '}';
    }
}
