package br.gov.to.sefaz.arr.parametros.persistence.entity;

import br.gov.to.sefaz.persistence.entity.AbstractEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entidade referente a tabela SEFAZ_ARR.TA_ARQUIVO_RECEPCAO do Banco de Dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 28/04/2016 17:48:00
 */
@Entity
@Table(name = "TA_ARQUIVO_RECEPCAO", schema = "SEFAZ_ARR")
@SuppressWarnings("PMD.TooManyFields")
public class ArquivoRecepcao extends AbstractEntity<Long> {

    private static final long serialVersionUID = -6917165011320850797L;

    @Id
    @NotNull
    @Column(name = "ID_ARQUIVOS", nullable = false)
    private Long idArquivos;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOME_ARQUIVO", nullable = false, length = 100)
    private String nomeArquivo;

    @NotNull
    @Column(name = "TIPO_ARQUIVO", nullable = false)
    private Integer tipoArquivo;

    @NotNull
    @Column(name = "CARACTERISTICA_ARQUIVO", nullable = false)
    private Integer caracteristicaArquivo;

    @NotNull
    @Column(name = "DATA_ARQUIVO", nullable = false)
    private LocalDateTime dataArquivo;

    @Size(max = 255)
    @Column(name = "HEADER_ARQUIVO", length = 255)
    private String headerArquivo;

    @Size(max = 255)
    @Column(name = "TRAILER_ARQUIVO", length = 255)
    private String trailerArquivo;

    @Column(name = "DATA_PROCESSAMENTO")
    private LocalDateTime dataProcessamento;

    @NotNull
    @Column(name = "SITUACAO", nullable = false)
    private Integer situacao;

    @NotNull
    @Column(name = "QUANTIDADE_DOCS", nullable = false)
    private Long quantidadeDocs;

    @NotNull
    @Column(name = "VALOR_TOTAL", nullable = false, precision = 14, scale = 2)
    private BigDecimal valorTotal;

    @NotNull
    @Lob
    @Column(name = "ARQUIVO", nullable = false)
    private byte[] arquivo;

    @Column(name = "QUANTIDADE_REJEITADA")
    private Long quantidadeRejeitada;

    @Column(name = "VALOR_REJEITADO", precision = 14, scale = 2)
    private BigDecimal valorRejeitado;

    @Column(name = "TIPO_REJEICAO")
    private Integer tipoRejeicao;

    @JoinColumn(name = "ID_BANCO", referencedColumnName = "ID_BANCO", nullable = false)
    @ManyToOne(optional = false)
    private Bancos bancos;

    @JoinColumn(name = "ID_CONVENIO", referencedColumnName = "ID_CONVENIO", nullable = false)
    @ManyToOne(optional = false)
    private ConveniosArrec conveniosArrecadacao;

    public ArquivoRecepcao() {
        // Construtor para inicialização por reflexão.
    }

    public ArquivoRecepcao(Long idArquivos, String nomeArquivo, Integer tipoArquivo, Integer caracteristicaArquivo,
            LocalDateTime dataArquivo, String headerArquivo, String trailerArquivo, LocalDateTime dataProcessamento,
            Integer situacao, Long quantidadeDocs, BigDecimal valorTotal, byte[] arquivo,
            Long quantidadeRejeitada, BigDecimal valorRejeitado, Integer tipoRejeicao, Bancos bancos,
            ConveniosArrec conveniosArrecadacao) {
        super();
        this.idArquivos = idArquivos;
        this.nomeArquivo = nomeArquivo;
        this.tipoArquivo = tipoArquivo;
        this.caracteristicaArquivo = caracteristicaArquivo;
        this.dataArquivo = dataArquivo;
        this.headerArquivo = headerArquivo;
        this.trailerArquivo = trailerArquivo;
        this.dataProcessamento = dataProcessamento;
        this.situacao = situacao;
        this.quantidadeDocs = quantidadeDocs;
        this.valorTotal = valorTotal;
        this.arquivo = arquivo;
        this.quantidadeRejeitada = quantidadeRejeitada;
        this.valorRejeitado = valorRejeitado;
        this.tipoRejeicao = tipoRejeicao;
        this.bancos = bancos;
        this.conveniosArrecadacao = conveniosArrecadacao;
    }

    @Override
    public Long getId() {
        return this.idArquivos;
    }

    public Long getIdArquivos() {
        return idArquivos;
    }

    public void setIdArquivos(Long idArquivos) {
        this.idArquivos = idArquivos;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public Integer getTipoArquivo() {
        return tipoArquivo;
    }

    public void setTipoArquivo(Integer tipoArquivo) {
        this.tipoArquivo = tipoArquivo;
    }

    public Integer getCaracteristicaArquivo() {
        return caracteristicaArquivo;
    }

    public void setCaracteristicaArquivo(Integer caracteristicaArquivo) {
        this.caracteristicaArquivo = caracteristicaArquivo;
    }

    public LocalDateTime getDataArquivo() {
        return dataArquivo;
    }

    public void setDataArquivo(LocalDateTime dataArquivo) {
        this.dataArquivo = dataArquivo;
    }

    public String getHeaderArquivo() {
        return headerArquivo;
    }

    public void setHeaderArquivo(String headerArquivo) {
        this.headerArquivo = headerArquivo;
    }

    public String getTrailerArquivo() {
        return trailerArquivo;
    }

    public void setTrailerArquivo(String trailerArquivo) {
        this.trailerArquivo = trailerArquivo;
    }

    public LocalDateTime getDataProcessamento() {
        return dataProcessamento;
    }

    public void setDataProcessamento(LocalDateTime dataProcessamento) {
        this.dataProcessamento = dataProcessamento;
    }

    public Integer getSituacao() {
        return situacao;
    }

    public void setSituacao(Integer situacao) {
        this.situacao = situacao;
    }

    public Long getQuantidadeDocs() {
        return quantidadeDocs;
    }

    public void setQuantidadeDocs(Long quantidadeDocs) {
        this.quantidadeDocs = quantidadeDocs;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }

    public Long getQuantidadeRejeitada() {
        return quantidadeRejeitada;
    }

    public void setQuantidadeRejeitada(Long quantidadeRejeitada) {
        this.quantidadeRejeitada = quantidadeRejeitada;
    }

    public BigDecimal getValorRejeitado() {
        return valorRejeitado;
    }

    public void setValorRejeitado(BigDecimal valorRejeitado) {
        this.valorRejeitado = valorRejeitado;
    }

    public Integer getTipoRejeicao() {
        return tipoRejeicao;
    }

    public void setTipoRejeicao(Integer tipoRejeicao) {
        this.tipoRejeicao = tipoRejeicao;
    }

    public Bancos getBancos() {
        return bancos;
    }

    public void setBancos(Bancos bancos) {
        this.bancos = bancos;
    }

    public ConveniosArrec getConveniosArrecadacao() {
        return conveniosArrecadacao;
    }

    public void setConveniosArrecadacao(ConveniosArrec conveniosArrecadacao) {
        this.conveniosArrecadacao = conveniosArrecadacao;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ArquivoRecepcao that = (ArquivoRecepcao) obj;
        return Objects.equals(tipoArquivo, that.tipoArquivo)
                && Objects.equals(caracteristicaArquivo, that.caracteristicaArquivo)
                && Objects.equals(situacao, that.situacao) && quantidadeDocs == that.quantidadeDocs
                && Objects.equals(idArquivos, that.idArquivos) && Objects.equals(nomeArquivo, that.nomeArquivo)
                && Objects.equals(dataArquivo, that.dataArquivo) && Objects.equals(headerArquivo, that.headerArquivo)
                && Objects.equals(trailerArquivo, that.trailerArquivo)
                && Objects.equals(dataProcessamento, that.dataProcessamento)
                && Objects.equals(valorTotal, that.valorTotal) && Objects.equals(arquivo, that.arquivo)
                && Objects.equals(quantidadeRejeitada, that.quantidadeRejeitada)
                && Objects.equals(valorRejeitado, that.valorRejeitado)
                && Objects.equals(tipoRejeicao, that.tipoRejeicao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idArquivos, nomeArquivo, tipoArquivo, caracteristicaArquivo, dataArquivo, headerArquivo,
                trailerArquivo, dataProcessamento, situacao, quantidadeDocs, valorTotal, arquivo, quantidadeRejeitada,
                valorRejeitado, tipoRejeicao);
    }

    @Override
    public String toString() {
        return "ArquivoRecepcao{" + "idArquivos=" + idArquivos + ", nomeArquivo='" + nomeArquivo + '\''
                + ", tipoArquivo=" + tipoArquivo + ", caracteristicaArquivo=" + caracteristicaArquivo + ", dataArquivo="
                + dataArquivo + ", headerArquivo='" + headerArquivo + '\'' + ", trailerArquivo='" + trailerArquivo
                + '\'' + ", dataProcessamento=" + dataProcessamento + ", situacao=" + situacao + ", quantidadeDocs="
                + quantidadeDocs + ", valorTotal=" + valorTotal + ", arquivo=" + arquivo + ", quantidadeRejeitada="
                + quantidadeRejeitada + ", valorRejeitado=" + valorRejeitado + ", tipoRejeicao=" + tipoRejeicao + '}';
    }

}