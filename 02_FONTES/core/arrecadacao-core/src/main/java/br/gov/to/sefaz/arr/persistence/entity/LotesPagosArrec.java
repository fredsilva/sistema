package br.gov.to.sefaz.arr.persistence.entity;

import br.gov.to.sefaz.arr.persistence.converter.EstadoLoteEnumConverter;
import br.gov.to.sefaz.arr.persistence.converter.TipoLotePagosArrecEnumConverter;
import br.gov.to.sefaz.arr.persistence.enums.EstadoLoteEnum;
import br.gov.to.sefaz.arr.persistence.enums.TipoLotePagosEnum;
import br.gov.to.sefaz.persistence.entity.AbstractEntity;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateTimeConverter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Entidade referente a tabela SEFAZ_ARR.TA_LOTES_PAGOS_ARREC do Banco de Dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 02/05/2016 17:15:52
 */
@Entity
@Table(name = "TA_LOTES_PAGOS_ARREC", schema = "SEFAZ_ARR")
public class LotesPagosArrec extends AbstractEntity<Long> {

    private static final long serialVersionUID = -328753640955565432L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_LOTES_PAGOS_ARREC")
    @SequenceGenerator(name = "SQ_LOTES_PAGOS_ARREC", schema = "SEFAZ_ARR", sequenceName = "SQ_LOTES_PAGOS_ARREC",
            allocationSize = 1)
    @Column(name = "ID_BDAR_TPAR")
    private Long idBdarTpar;

    @NotNull
    @Convert(converter = LocalDateTimeConverter.class)
    @Column(name = "DATA_PROCESSAMENTO")
    private LocalDateTime dataProcessamento;

    @NotNull
    @Convert(converter = TipoLotePagosArrecEnumConverter.class)
    @Column(name = "TIPO")
    private TipoLotePagosEnum tipo;

    @NotNull
    @Convert(converter = EstadoLoteEnumConverter.class)
    @Column(name = "ESTADO_LOTE")
    private EstadoLoteEnum estadoLote;

    @NotNull
    @Column(name = "DATA_RECEPCAO")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime dataRecepcao;

    @NotNull
    @Column(name = "ID_CONVENIO")
    private Long idConvenio;

    @NotNull
    @Column(name = "ID_BANCO")
    private Integer idBanco;

    @NotNull
    @Column(name = "ID_AGENCIA")
    private Integer idAgencia;

    @NotNull
    @Column(name = "QUANTIDADE_RECEPCIONADO")
    private Integer quantidadeRecepcionado;

    @NotNull
    @Column(name = "VALOR_RECEPCIONADO")
    private BigDecimal valorRecepcionado;

    @NotNull
    @Column(name = "QUANTIDADE_DOCS")
    private Integer quantidadeDocs;

    @NotNull
    @Column(name = "VALOR_LOTE")
    private BigDecimal valorLote;

    @NotNull
    @Column(name = "QUANTIDADE_ERROS")
    private Integer quantidadeErros;

    @NotNull
    @Column(name = "VALOR_ERROS")
    private BigDecimal valorErros;

    public LotesPagosArrec() {
        // Construtor para inicialização por reflexão.
    }

    public LotesPagosArrec(LocalDateTime dataProcessamento, TipoLotePagosEnum tipo, EstadoLoteEnum estadoLote,
            LocalDateTime dataRecepcao, Long idConvenio, Integer idBanco, Integer idAgencia,
            Integer quantidadeRecepcionado, BigDecimal valorRecepcionado, Integer quantidadeDocs,
            BigDecimal valorLote, Integer quantidadeErros, BigDecimal valorErros) {
        super();
        this.dataProcessamento = dataProcessamento;
        this.tipo = tipo;
        this.estadoLote = estadoLote;
        this.dataRecepcao = dataRecepcao;
        this.idConvenio = idConvenio;
        this.idBanco = idBanco;
        this.idAgencia = idAgencia;
        this.quantidadeRecepcionado = quantidadeRecepcionado;
        this.valorRecepcionado = valorRecepcionado;
        this.quantidadeDocs = quantidadeDocs;
        this.valorLote = valorLote;
        this.quantidadeErros = quantidadeErros;
        this.valorErros = valorErros;
    }

    @Override
    public Long getId() {
        return idBdarTpar;
    }

    public Long getIdBdarTpar() {
        return idBdarTpar;
    }

    public void setIdBdarTpar(Long idBdarTpar) {
        this.idBdarTpar = idBdarTpar;
    }

    public LocalDateTime getDataProcessamento() {
        return dataProcessamento;
    }

    public void setDataProcessamento(LocalDateTime dataProcessamento) {
        this.dataProcessamento = dataProcessamento;
    }

    public TipoLotePagosEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoLotePagosEnum tipo) {
        this.tipo = tipo;
    }

    public EstadoLoteEnum getEstadoLote() {
        return estadoLote;
    }

    public void setEstadoLote(EstadoLoteEnum estadoLote) {
        this.estadoLote = estadoLote;
    }

    public LocalDateTime getDataRecepcao() {
        return dataRecepcao;
    }

    public void setDataRecepcao(LocalDateTime dataRecepcao) {
        this.dataRecepcao = dataRecepcao;
    }

    public Long getIdConvenio() {
        return idConvenio;
    }

    public void setIdConvenio(Long idConvenio) {
        this.idConvenio = idConvenio;
    }

    public Integer getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(Integer idBanco) {
        this.idBanco = idBanco;
    }

    public Integer getIdAgencia() {
        return idAgencia;
    }

    public void setIdAgencia(Integer idAgencia) {
        this.idAgencia = idAgencia;
    }

    public Integer getQuantidadeRecepcionado() {
        return quantidadeRecepcionado;
    }

    public void setQuantidadeRecepcionado(Integer quantidadeRecepcionado) {
        this.quantidadeRecepcionado = quantidadeRecepcionado;
    }

    public BigDecimal getValorRecepcionado() {
        return valorRecepcionado;
    }

    public void setValorRecepcionado(BigDecimal valorRecepcionado) {
        this.valorRecepcionado = valorRecepcionado;
    }

    public Integer getQuantidadeDocs() {
        return quantidadeDocs;
    }

    public void setQuantidadeDocs(Integer quantidadeDocs) {
        this.quantidadeDocs = quantidadeDocs;
    }

    public BigDecimal getValorLote() {
        return valorLote;
    }

    public void setValorLote(BigDecimal valorLote) {
        this.valorLote = valorLote;
    }

    public Integer getQuantidadeErros() {
        return quantidadeErros;
    }

    public void setQuantidadeErros(Integer quantidadeErros) {
        this.quantidadeErros = quantidadeErros;
    }

    public BigDecimal getValorErros() {
        return valorErros;
    }

    public void setValorErros(BigDecimal valorErros) {
        this.valorErros = valorErros;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LotesPagosArrec that = (LotesPagosArrec) obj;
        return Objects.equals(idConvenio, that.idConvenio) && Objects.equals(idBdarTpar, that.idBdarTpar)
                && Objects.equals(dataProcessamento, that.dataProcessamento) && Objects.equals(tipo, that.tipo)
                && Objects.equals(estadoLote, that.estadoLote) && Objects.equals(dataRecepcao, that.dataRecepcao)
                && Objects.equals(idBanco, that.idBanco) && Objects.equals(idAgencia, that.idAgencia)
                && Objects.equals(quantidadeRecepcionado, that.quantidadeRecepcionado)
                && Objects.equals(valorRecepcionado, that.valorRecepcionado)
                && Objects.equals(quantidadeDocs, that.quantidadeDocs) && Objects.equals(valorLote, that.valorLote)
                && Objects.equals(quantidadeErros, that.quantidadeErros) && Objects.equals(valorErros, that.valorErros);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBdarTpar, dataProcessamento, tipo, estadoLote, dataRecepcao, idConvenio, idBanco,
                idAgencia, quantidadeRecepcionado, valorRecepcionado, quantidadeDocs, valorLote, quantidadeErros,
                valorErros);
    }

    @Override
    public String toString() {
        return "LotesPagosArrec{" + "idBdarTpar=" + idBdarTpar + ", dataProcessamento=" + dataProcessamento
                + ", tipo=" + tipo + ", estadoLote=" + estadoLote + ", dataRecepcao=" + dataRecepcao + ", idConvenio="
                + idConvenio + ", idBanco=" + idBanco + ", idAgencia=" + idAgencia + ", quantidadeRecepcionado="
                + quantidadeRecepcionado + ", valorRecepcionado=" + valorRecepcionado + ", quantidadeDocs="
                + quantidadeDocs + ", valorLote=" + valorLote + ", quantidadeErros=" + quantidadeErros + ", valorErros="
                + valorErros + '}';
    }
}
