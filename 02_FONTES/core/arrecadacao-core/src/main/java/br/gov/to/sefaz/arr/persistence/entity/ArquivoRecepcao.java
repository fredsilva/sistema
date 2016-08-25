package br.gov.to.sefaz.arr.persistence.entity;

import br.gov.to.sefaz.arr.persistence.converter.FileTypeEnumConverter;
import br.gov.to.sefaz.arr.persistence.converter.SituacaoArquivoEnumConverter;
import br.gov.to.sefaz.arr.persistence.converter.TipoArquivoEnumConverter;
import br.gov.to.sefaz.arr.persistence.enums.SituacaoArquivoEnum;
import br.gov.to.sefaz.arr.processamento.domain.header.TipoArquivoEnum;
import br.gov.to.sefaz.arr.processamento.type.FileTypeEnum;
import br.gov.to.sefaz.persistence.entity.AbstractEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import static org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateConverter;

/**
 * Entidade referente a tabela SEFAZ_ARR.TA_ARQUIVO_RECEPCAO do Banco de Dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 28/04/2016 17:48:00
 */
@Entity
@Table(name = "TA_ARQUIVO_RECEPCAO", schema = "SEFAZ_ARR")
@SuppressWarnings("PMD")
public class ArquivoRecepcao extends AbstractEntity<Long> {

    private static final long serialVersionUID = -6917165011320850797L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ARQUIVO_RECEPCAO")
    @SequenceGenerator(name = "SQ_ARQUIVO_RECEPCAO", schema = "SEFAZ_ARR", sequenceName = "SQ_ARQUIVO_RECEPCAO",
            allocationSize = 1)
    @Column(name = "ID_ARQUIVOS")
    private Long idArquivos;

    @Column(name = "ID_BANCO")
    private Integer idBanco;

    @Column(name = "ID_CONVENIO")
    private Long idConvenio;

    @Size(min = 1, max = 100)
    @Column(name = "NOME_ARQUIVO", length = 100)
    private String nomeArquivo;

    @Convert(converter = FileTypeEnumConverter.class)
    @Column(name = "TIPO_ARQUIVO")
    private FileTypeEnum tipoArquivo;

    @Convert(converter = TipoArquivoEnumConverter.class)
    @Column(name = "CARACTERISTICA_ARQUIVO")
    private TipoArquivoEnum caracteristicaArquivo;

    @Convert(converter = LocalDateConverter.class)
    @Column(name = "DATA_ARQUIVO")
    private LocalDate dataArquivo;

    @Size(max = 255)
    @Column(name = "HEADER_ARQUIVO")
    private String headerArquivo;

    @Size(max = 255)
    @Column(name = "TRAILER_ARQUIVO")
    private String trailerArquivo;

    @Column(name = "DATA_PROCESSAMENTO")
    private LocalDateTime dataProcessamento;

    @Convert(converter = SituacaoArquivoEnumConverter.class)
    @Column(name = "SITUACAO")
    private SituacaoArquivoEnum situacao;

    @Column(name = "QUANTIDADE_DOCS")
    private Long quantidadeDocs;

    @Column(name = "VALOR_TOTAL", precision = 14, scale = 2)
    private BigDecimal valorTotal;

    @Lob
    @Column(name = "ARQUIVO", nullable = false)
    private byte[] arquivo;

    @Column(name = "QUANTIDADE_REJEITADA")
    private Long quantidadeRejeitada;

    @Column(name = "VALOR_REJEITADO", precision = 14, scale = 2)
    private BigDecimal valorRejeitado;

    @Column(name = "ID_CODIGO_REJEICAO")
    private Integer codigoRejeicao;

    @Column(name = "SEQUENCIAL_NSA")
    private Long sequencialNsa;

    @ManyToOne
    @JoinColumn(name = "ID_BANCO", referencedColumnName = "ID_BANCO", insertable = false, updatable = false)
    private Bancos bancos;

    @JoinColumn(name = "ID_CONVENIO", referencedColumnName = "ID_CONVENIO", insertable = false, updatable = false)
    @ManyToOne
    private ConveniosArrec conveniosArrecadacao;

    public ArquivoRecepcao() {
        // Construtor para inicialização por reflexão.
    }

    public ArquivoRecepcao(String nomeArquivo, FileTypeEnum tipoArquivo, TipoArquivoEnum caracteristicaArquivo,
            LocalDate dataArquivo, String headerArquivo, SituacaoArquivoEnum situacao, Long quantidadeDocs,
            BigDecimal valorTotal, byte[] arquivo, Long sequencialNsa,
            Integer idBanco, Long idConvenio) {
        this.nomeArquivo = nomeArquivo;
        this.tipoArquivo = tipoArquivo;
        this.caracteristicaArquivo = caracteristicaArquivo;
        this.dataArquivo = dataArquivo;
        this.headerArquivo = headerArquivo;
        this.situacao = situacao;
        this.quantidadeDocs = quantidadeDocs;
        this.valorTotal = valorTotal;
        this.arquivo = arquivo;
        this.sequencialNsa = sequencialNsa;
        this.idBanco = idBanco;
        this.idConvenio = idConvenio;
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

    public FileTypeEnum getTipoArquivo() {
        return tipoArquivo;
    }

    public void setTipoArquivo(FileTypeEnum tipoArquivo) {
        this.tipoArquivo = tipoArquivo;
    }

    public TipoArquivoEnum getCaracteristicaArquivo() {
        return caracteristicaArquivo;
    }

    public void setCaracteristicaArquivo(TipoArquivoEnum caracteristicaArquivo) {
        this.caracteristicaArquivo = caracteristicaArquivo;
    }

    public Long getIdConvenio() {
        return idConvenio;
    }

    public void setIdConvenio(Long idConvenio) {
        this.idConvenio = idConvenio;
    }

    public LocalDate getDataArquivo() {
        return dataArquivo;
    }

    public void setDataArquivo(LocalDate dataArquivo) {
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

    public SituacaoArquivoEnum getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoArquivoEnum situacao) {
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

    public Integer getCodigoRejeicao() {
        return codigoRejeicao;
    }

    public void setCodigoRejeicao(Integer codigoRejeicao) {
        this.codigoRejeicao = codigoRejeicao;
    }

    public Long getSequencialNsa() {
        return sequencialNsa;
    }

    public void setSequencialNsa(Long sequencialNsa) {
        this.sequencialNsa = sequencialNsa;
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

    public Integer getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(Integer idBanco) {
        this.idBanco = idBanco;
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
                && Objects.equals(situacao, that.situacao) && Objects.equals(quantidadeDocs, that.quantidadeDocs)
                && Objects.equals(idArquivos, that.idArquivos) && Objects.equals(nomeArquivo, that.nomeArquivo)
                && Objects.equals(dataArquivo, that.dataArquivo) && Objects.equals(headerArquivo, that.headerArquivo)
                && Objects.equals(trailerArquivo, that.trailerArquivo)
                && Objects.equals(dataProcessamento, that.dataProcessamento)
                && Objects.equals(valorTotal, that.valorTotal) && Arrays.equals(arquivo, that.arquivo)
                && Objects.equals(quantidadeRejeitada, that.quantidadeRejeitada)
                && Objects.equals(valorRejeitado, that.valorRejeitado)
                && Objects.equals(codigoRejeicao, that.codigoRejeicao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idArquivos, nomeArquivo, tipoArquivo, caracteristicaArquivo, dataArquivo, headerArquivo,
                trailerArquivo, dataProcessamento, situacao, quantidadeDocs, valorTotal, arquivo, quantidadeRejeitada,
                valorRejeitado, codigoRejeicao);
    }

    @Override
    public String toString() {
        return "ArquivoRecepcao{" + "idArquivos=" + idArquivos + ", nomeArquivo='" + nomeArquivo + '\''
                + ", tipoArquivo=" + tipoArquivo + ", caracteristicaArquivo=" + caracteristicaArquivo + ", dataArquivo="
                + dataArquivo + ", headerArquivo='" + headerArquivo + '\'' + ", trailerArquivo='" + trailerArquivo
                + '\'' + ", dataProcessamento=" + dataProcessamento + ", situacao=" + situacao + ", quantidadeDocs="
                + quantidadeDocs + ", valorTotal=" + valorTotal + ", arquivo=" + Arrays.toString(arquivo)
                + ", quantidadeRejeitada=" + quantidadeRejeitada + ", valorRejeitado=" + valorRejeitado
                + ", codigoRejeicao=" + codigoRejeicao + '}';
    }

}