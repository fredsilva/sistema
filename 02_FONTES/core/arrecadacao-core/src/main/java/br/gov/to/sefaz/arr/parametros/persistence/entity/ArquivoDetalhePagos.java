package br.gov.to.sefaz.arr.parametros.persistence.entity;

import br.gov.to.sefaz.persistence.entity.AbstractEntity;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateTimeConverter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entidade referente a tabela SEFAZ_ARR.TA_ARQUIVO_DETALHE_PAGOS do Banco de Dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 28/04/2016 17:48:00
 */
@Entity
@Table(name = "TA_ARQUIVO_DETALHE_PAGOS", schema = "SEFAZ_ARR")
public class ArquivoDetalhePagos extends AbstractEntity<Long> {

    private static final long serialVersionUID = -1732904941435309267L;

    @Id
    @NotNull
    @Column(name = "ID_DETALHE_ARQUIVO", nullable = false)
    private Long idDetalheArquivo;

    @NotNull
    @Column(name = "NUMERO_LINHA", nullable = false)
    private Integer numeroLinha;

    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "NUMERO_NSU", nullable = false, length = 25)
    private String numeroNsu;

    @NotNull
    @Column(name = "DATA_PAGAMENTO", nullable = false)
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime dataPagamento;

    @NotNull
    @Column(name = "VALOR_BARRA", nullable = false, precision = 14, scale = 2)
    private BigDecimal valorBarra;

    @NotNull
    @Size(min = 1, max = 44)
    @Column(name = "CODIGO_BARRA", nullable = false, length = 44)
    private String codigoBarra;

    @NotNull
    @Column(name = "VALOR_PAGAMENTO", nullable = false, precision = 14, scale = 2)
    private BigDecimal valorPagamento;

    @NotNull
    @Column(name = "VALOR_TARIFA_CONVENIO", nullable = false, precision = 6, scale = 2)
    private BigDecimal valorTarifaConvenio;

    @NotNull
    @Column(name = "FORMA_PAGAMENTO", nullable = false)
    private Integer formaPagamento;

    @Column(name = "ERRO_LINHA")
    private Integer erroLinha;

    @NotNull
    @Column(name = "VALOR_AUTENTICADO", nullable = false, precision = 14, scale = 2)
    private BigDecimal valorAutenticado;

    @JoinColumn(name = "ID_ARQUIVOS", referencedColumnName = "ID_ARQUIVOS", nullable = false)
    @ManyToOne(optional = false)
    private ArquivoRecepcao arquivoRecepcao;

    public ArquivoDetalhePagos() {
        // Construtor para inicialização por reflexão.
    }

    public ArquivoDetalhePagos(Long idDetalheArquivo) {
        this.idDetalheArquivo = idDetalheArquivo;
    }

    public ArquivoDetalhePagos(Long idDetalheArquivo, Integer numeroLinha, String numeroNsu,
            LocalDateTime dataPagamento, BigDecimal valorBarra, String codigoBarra, BigDecimal valorPagamento,
            BigDecimal valorTarifaConvenio, Integer formaPagamento, BigDecimal valorAutenticado) {
        this.idDetalheArquivo = idDetalheArquivo;
        this.numeroLinha = numeroLinha;
        this.numeroNsu = numeroNsu;
        this.dataPagamento = dataPagamento;
        this.valorBarra = valorBarra;
        this.codigoBarra = codigoBarra;
        this.valorPagamento = valorPagamento;
        this.valorTarifaConvenio = valorTarifaConvenio;
        this.formaPagamento = formaPagamento;
        this.valorAutenticado = valorAutenticado;
    }

    @Override
    public Long getId() {
        return idDetalheArquivo;
    }

    public Long getIdDetalheArquivo() {
        return idDetalheArquivo;
    }

    public void setIdDetalheArquivo(Long idDetalheArquivo) {
        this.idDetalheArquivo = idDetalheArquivo;
    }

    public Integer getNumeroLinha() {
        return numeroLinha;
    }

    public void setNumeroLinha(Integer numeroLinha) {
        this.numeroLinha = numeroLinha;
    }

    public String getNumeroNsu() {
        return numeroNsu;
    }

    public void setNumeroNsu(String numeroNsu) {
        this.numeroNsu = numeroNsu;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public BigDecimal getValorBarra() {
        return valorBarra;
    }

    public void setValorBarra(BigDecimal valorBarra) {
        this.valorBarra = valorBarra;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public BigDecimal getValorPagamento() {
        return valorPagamento;
    }

    public void setValorPagamento(BigDecimal valorPagamento) {
        this.valorPagamento = valorPagamento;
    }

    public BigDecimal getValorTarifaConvenio() {
        return valorTarifaConvenio;
    }

    public void setValorTarifaConvenio(BigDecimal valorTarifaConvenio) {
        this.valorTarifaConvenio = valorTarifaConvenio;
    }

    public Integer getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(Integer formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Integer getErroLinha() {
        return erroLinha;
    }

    public void setErroLinha(Integer erroLinha) {
        this.erroLinha = erroLinha;
    }

    public BigDecimal getValorAutenticado() {
        return valorAutenticado;
    }

    public void setValorAutenticado(BigDecimal valorAutenticado) {
        this.valorAutenticado = valorAutenticado;
    }

    public ArquivoRecepcao getArquivoRecepcao() {
        return arquivoRecepcao;
    }

    public void setArquivoRecepcao(ArquivoRecepcao arquivoRecepcao) {
        this.arquivoRecepcao = arquivoRecepcao;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ArquivoDetalhePagos that = (ArquivoDetalhePagos) obj;
        return numeroLinha == that.numeroLinha && Objects.equals(formaPagamento, that.formaPagamento)
                && Objects.equals(erroLinha, that.erroLinha) && Objects.equals(idDetalheArquivo, that.idDetalheArquivo)
                && Objects.equals(numeroNsu, that.numeroNsu) && Objects.equals(dataPagamento, that.dataPagamento)
                && Objects.equals(valorBarra, that.valorBarra) && Objects.equals(codigoBarra, that.codigoBarra)
                && Objects.equals(valorPagamento, that.valorPagamento)
                && Objects.equals(valorTarifaConvenio, that.valorTarifaConvenio)
                && Objects.equals(valorAutenticado, that.valorAutenticado)
                && Objects.equals(arquivoRecepcao, that.arquivoRecepcao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDetalheArquivo, numeroLinha, numeroNsu, dataPagamento, valorBarra, codigoBarra,
                valorPagamento, valorTarifaConvenio, formaPagamento, erroLinha, valorAutenticado, arquivoRecepcao);
    }

    @Override
    public String toString() {
        return "ArquivoDetalhePagos{" + "idDetalheArquivo=" + idDetalheArquivo + ", numeroLinha=" + numeroLinha
                + ", numeroNsu='" + numeroNsu + '\'' + ", dataPagamento=" + dataPagamento + ", valorBarra=" + valorBarra
                + ", codigoBarra='" + codigoBarra + '\'' + ", valorPagamento=" + valorPagamento
                + ", valorTarifaConvenio=" + valorTarifaConvenio + ", formaPagamento=" + formaPagamento + ", erroLinha="
                + erroLinha + ", valorAutenticado=" + valorAutenticado + '}';
    }
}
