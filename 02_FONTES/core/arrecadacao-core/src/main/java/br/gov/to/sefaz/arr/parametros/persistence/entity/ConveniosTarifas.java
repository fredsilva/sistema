package br.gov.to.sefaz.arr.parametros.persistence.entity;

import br.gov.to.sefaz.arr.parametros.persistence.converter.FormaPagamentoEnumConverter;
import br.gov.to.sefaz.arr.parametros.persistence.enums.FormaPagamentoEnum;
import br.gov.to.sefaz.persistence.entity.AbstractEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;


/**
 * Entidade referente a tabela SEFAZ_ARR.TA_CONVENIOS_TARIFAS do Banco de Dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 05/05/2016 11:15:04
 */
@Entity
@Table(name = "TA_CONVENIOS_TARIFAS", schema = "SEFAZ_ARR")
public class ConveniosTarifas extends AbstractEntity<Integer> {

    private static final long serialVersionUID = 663963460030238191L;

    @Id
    @SequenceGenerator(name = "SQ_CONVENIOS_TARIFAS", schema = "SEFAZ_ARR", sequenceName = "SQ_CONVENIOS_TARIFAS",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CONVENIOS_TARIFAS")
    @Column(name = "ID_TARIFA")
    private Integer idTarifa;

    @NotNull(message = "#{arr_msg['parametros.conveniosTarifa.formaPagamento.obrigatorio']}")
    @Convert(converter = FormaPagamentoEnumConverter.class)
    @Column(name = "FORMA_PAGAMENTO")
    private FormaPagamentoEnum formaPagamento;

    @NotNull(message = "#{arr_msg['parametros.conveniosTarifa.dataInicio.obrigatorio']}")
    @Column(name = "DATA_INICIO")
    private LocalDate dataInicio;

    @Column(name = "DATA_FIM")
    private LocalDate dataFim;

    @NotNull(message = "#{arr_msg['parametros.conveniosTarifa.valor.obrigatorio']}")
    @Digits(integer = 5, fraction = 2, message = "#{arr_msg['parametros.conveniosTarifa.valor.digitos']}")
    @DecimalMin(value = "0.01", message = "#{arr_msg['parametros.conveniosTarifa.valor.minimo']}")
    @Column(name = "VALOR")
    private BigDecimal valor;

    @JoinColumns(
            @JoinColumn(name = "ID_CONVENIO", referencedColumnName = "ID_CONVENIO", nullable = false,
                    insertable = false, updatable = false))
    @ManyToOne(optional = false)
    private ConveniosArrec conveniosArrec;

    @NotNull(message = "#{arr_msg['parametros.conveniosTarifa.idConvenio.obrigatorio']}")
    @Column(name = "ID_CONVENIO")
    private Long idConveniosArrec;

    public ConveniosTarifas() {
        conveniosArrec = new ConveniosArrec();
    }

    public ConveniosTarifas(Integer idTarifa) {
        this.idTarifa = idTarifa;
    }

    public ConveniosTarifas(Integer idTarifa, FormaPagamentoEnum formaPagamento, LocalDate dataInicio,
            BigDecimal valor) {
        this.idTarifa = idTarifa;
        this.formaPagamento = formaPagamento;
        this.dataInicio = dataInicio;
        this.valor = valor;
    }

    public Integer getIdTarifa() {
        return idTarifa;
    }

    public void setIdTarifa(Integer idTarifa) {
        this.idTarifa = idTarifa;
    }

    public FormaPagamentoEnum getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamentoEnum formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public ConveniosArrec getConveniosArrec() {
        return conveniosArrec;
    }

    public void setConveniosArrec(ConveniosArrec conveniosArrec) {
        this.conveniosArrec = conveniosArrec;
    }

    public Long getIdConveniosArrec() {
        return idConveniosArrec;
    }

    public void setIdConveniosArrec(Long idConveniosArrec) {
        this.idConveniosArrec = idConveniosArrec;
    }

    @Override
    public Integer getId() {
        return idTarifa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ConveniosTarifas that = (ConveniosTarifas) o;
        return Objects.equals(idTarifa, that.idTarifa)
                && formaPagamento == that.formaPagamento
                && Objects.equals(dataInicio, that.dataInicio)
                && Objects.equals(dataFim, that.dataFim)
                && Objects.equals(valor, that.valor)
                && Objects.equals(conveniosArrec, that.conveniosArrec)
                && Objects.equals(idConveniosArrec, that.idConveniosArrec);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTarifa, formaPagamento, dataInicio, dataFim, valor, conveniosArrec, idConveniosArrec);
    }

    @Override
    public String toString() {
        return "ConveniosTarifas{"
                + "idTarifa=" + idTarifa
                + ", formaPagamento=" + formaPagamento
                + ", dataInicio=" + dataInicio
                + ", dataFim=" + dataFim
                + ", valor=" + valor
                + ", conveniosArrec=" + conveniosArrec
                + ", idConveniosArrec=" + idConveniosArrec
                + '}';
    }
}
