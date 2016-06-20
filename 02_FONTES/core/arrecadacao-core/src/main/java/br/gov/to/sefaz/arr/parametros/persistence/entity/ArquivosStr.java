package br.gov.to.sefaz.arr.parametros.persistence.entity;

import br.gov.to.sefaz.persistence.entity.AbstractEntity;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateTimeConverter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entidade referente a tabela SEFAZ_ARR.TA_ARQUIVOS_STR do Banco de Dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 28/04/2016 17:48:00
 */
@Entity
@Table(name = "TA_ARQUIVOS_STR", schema = "SEFAZ_ARR")
@SuppressWarnings("PMD")
public class ArquivosStr extends AbstractEntity<Long> {

    private static final long serialVersionUID = -6405776997435685568L;

    @Id
    @NotNull
    @Column(name = "ID_ARQUIVO_STR", nullable = false)
    private Long idArquivoStr;

    @NotNull
    @Column(name = "DATA_RECEPCAO", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataRecepcao;

    @Size(max = 20)
    @Column(name = "NUMERO_CONTROLE_STR", length = 20)
    private String numeroControleStr;

    @NotNull
    @Column(name = "DATA_ARRECADACAO", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataArrecadacao;

    @NotNull
    @Column(name = "ID_BANCO_DEBITADO", nullable = false)
    private Integer idBancoDebitado;

    @NotNull
    @Column(name = "ID_BANCO_CREDITADO", nullable = false)
    private Integer idBancoCreditado;

    @Column(name = "ID_AGENCIA_CREDITADA")
    private Integer idAgenciaCreditada;

    @Size(max = 15)
    @Column(name = "CONTA_CREDITADA", length = 15)
    private String contaCreditada;

    @NotNull
    @Column(name = "TIPO_RECEITA", nullable = false)
    private Integer tipoReceita;

    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "TIPO_RECOLHIMENTO", nullable = false, length = 1)
    private String tipoRecolhimento;

    @NotNull
    @Column(name = "VALOR_TOTAL_LANCAMENTO", nullable = false, precision = 14, scale = 2)
    private BigDecimal valorTotalLancamento;

    @Column(name = "DATA_BACEN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataBacen;

    @Column(name = "DATA_MOVIMENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataMovimento;

    @Size(max = 500)
    @Column(name = "HISTORICO_MOVIMENTO", length = 500)
    private String historicoMovimento;

    @NotNull
    @Column(name = "SITUACAO", nullable = false)
    private Integer situacao;

    @Lob
    @Column(name = "ARQUIVO_STR")
    private byte[] arquivoStr;

    @Column(name = "DATA_PROCESSAMENTO")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime dataProcessamento;

    @Column(name = "USUARIO_LANCAMENTO")
    private Long usuarioLancamento;

    public ArquivosStr() {
        // Construtor para inicialização por reflexão.
    }

    public ArquivosStr(Long idArquivoStr, Date dataRecepcao, String numeroControleStr, Date dataArrecadacao,
            Integer idBancoDebitado, Integer idBancoCreditado, Integer idAgenciaCreditada, String contaCreditada,
            Integer tipoReceita, String tipoRecolhimento, BigDecimal valorTotalLancamento, Date dataBacen,
            Date dataMovimento, String historicoMovimento, Integer situacao, byte[] arquivoStr,
            LocalDateTime dataProcessamento, Long usuarioLancamento) {
        super();
        this.idArquivoStr = idArquivoStr;
        this.dataRecepcao = dataRecepcao;
        this.numeroControleStr = numeroControleStr;
        this.dataArrecadacao = dataArrecadacao;
        this.idBancoDebitado = idBancoDebitado;
        this.idBancoCreditado = idBancoCreditado;
        this.idAgenciaCreditada = idAgenciaCreditada;
        this.contaCreditada = contaCreditada;
        this.tipoReceita = tipoReceita;
        this.tipoRecolhimento = tipoRecolhimento;
        this.valorTotalLancamento = valorTotalLancamento;
        this.dataBacen = dataBacen;
        this.dataMovimento = dataMovimento;
        this.historicoMovimento = historicoMovimento;
        this.situacao = situacao;
        this.arquivoStr = arquivoStr;
        this.dataProcessamento = dataProcessamento;
        this.usuarioLancamento = usuarioLancamento;
    }

    @Override
    public Long getId() {
        return idArquivoStr;
    }

    public Long getIdArquivoStr() {
        return idArquivoStr;
    }

    public void setIdArquivoStr(Long idArquivoStr) {
        this.idArquivoStr = idArquivoStr;
    }

    public Date getDataRecepcao() {
        return dataRecepcao;
    }

    public void setDataRecepcao(Date dataRecepcao) {
        this.dataRecepcao = dataRecepcao;
    }

    public String getNumeroControleStr() {
        return numeroControleStr;
    }

    public void setNumeroControleStr(String numeroControleStr) {
        this.numeroControleStr = numeroControleStr;
    }

    public Date getDataArrecadacao() {
        return dataArrecadacao;
    }

    public void setDataArrecadacao(Date dataArrecadacao) {
        this.dataArrecadacao = dataArrecadacao;
    }

    public Integer getIdBancoDebitado() {
        return idBancoDebitado;
    }

    public void setIdBancoDebitado(Integer idBancoDebitado) {
        this.idBancoDebitado = idBancoDebitado;
    }

    public Integer getIdBancoCreditado() {
        return idBancoCreditado;
    }

    public void setIdBancoCreditado(Integer idBancoCreditado) {
        this.idBancoCreditado = idBancoCreditado;
    }

    public Integer getIdAgenciaCreditada() {
        return idAgenciaCreditada;
    }

    public void setIdAgenciaCreditada(Integer idAgenciaCreditada) {
        this.idAgenciaCreditada = idAgenciaCreditada;
    }

    public String getContaCreditada() {
        return contaCreditada;
    }

    public void setContaCreditada(String contaCreditada) {
        this.contaCreditada = contaCreditada;
    }

    public Integer getTipoReceita() {
        return tipoReceita;
    }

    public void setTipoReceita(Integer tipoReceita) {
        this.tipoReceita = tipoReceita;
    }

    public String getTipoRecolhimento() {
        return tipoRecolhimento;
    }

    public void setTipoRecolhimento(String tipoRecolhimento) {
        this.tipoRecolhimento = tipoRecolhimento;
    }

    public BigDecimal getValorTotalLancamento() {
        return valorTotalLancamento;
    }

    public void setValorTotalLancamento(BigDecimal valorTotalLancamento) {
        this.valorTotalLancamento = valorTotalLancamento;
    }

    public Date getDataBacen() {
        return dataBacen;
    }

    public void setDataBacen(Date dataBacen) {
        this.dataBacen = dataBacen;
    }

    public Date getDataMovimento() {
        return dataMovimento;
    }

    public void setDataMovimento(Date dataMovimento) {
        this.dataMovimento = dataMovimento;
    }

    public String getHistoricoMovimento() {
        return historicoMovimento;
    }

    public void setHistoricoMovimento(String historicoMovimento) {
        this.historicoMovimento = historicoMovimento;
    }

    public Integer getSituacao() {
        return situacao;
    }

    public void setSituacao(Integer situacao) {
        this.situacao = situacao;
    }

    public byte[] getArquivoStr() {
        return arquivoStr;
    }

    public void setArquivoStr(byte[] arquivoStr) {
        this.arquivoStr = arquivoStr;
    }

    public LocalDateTime getDataProcessamento() {
        return dataProcessamento;
    }

    public void setDataProcessamento(LocalDateTime dataProcessamento) {
        this.dataProcessamento = dataProcessamento;
    }

    public Long getUsuarioLancamento() {
        return usuarioLancamento;
    }

    public void setUsuarioLancamento(Long usuarioLancamento) {
        this.usuarioLancamento = usuarioLancamento;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ArquivosStr that = (ArquivosStr) obj;
        return idBancoDebitado == that.idBancoDebitado && idBancoCreditado == that.idBancoCreditado
                && tipoReceita == that.tipoReceita && situacao == that.situacao
                && Objects.equals(idArquivoStr, that.idArquivoStr) && Objects.equals(dataRecepcao, that.dataRecepcao)
                && Objects.equals(numeroControleStr, that.numeroControleStr)
                && Objects.equals(dataArrecadacao, that.dataArrecadacao)
                && Objects.equals(idAgenciaCreditada, that.idAgenciaCreditada)
                && Objects.equals(contaCreditada, that.contaCreditada)
                && Objects.equals(tipoRecolhimento, that.tipoRecolhimento)
                && Objects.equals(valorTotalLancamento, that.valorTotalLancamento)
                && Objects.equals(dataBacen, that.dataBacen) && Objects.equals(dataMovimento, that.dataMovimento)
                && Objects.equals(historicoMovimento, that.historicoMovimento)
                && Objects.equals(arquivoStr, that.arquivoStr)
                && Objects.equals(dataProcessamento, that.dataProcessamento)
                && Objects.equals(usuarioLancamento, that.usuarioLancamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idArquivoStr, dataRecepcao, numeroControleStr, dataArrecadacao, idBancoDebitado,
                idBancoCreditado, idAgenciaCreditada, contaCreditada, tipoReceita, tipoRecolhimento,
                valorTotalLancamento, dataBacen, dataMovimento, historicoMovimento, situacao, arquivoStr,
                dataProcessamento, usuarioLancamento);
    }

    @Override
    public String toString() {
        return "ArquivosStr{" + "idArquivoStr=" + idArquivoStr + ", dataRecepcao=" + dataRecepcao
                + ", numeroControleStr='" + numeroControleStr + '\'' + ", dataArrecadacao=" + dataArrecadacao
                + ", idBancoDebitado=" + idBancoDebitado + ", idBancoCreditado=" + idBancoCreditado
                + ", idAgenciaCreditada=" + idAgenciaCreditada + ", contaCreditada='" + contaCreditada + '\''
                + ", tipoReceita=" + tipoReceita + ", tipoRecolhimento='" + tipoRecolhimento + '\''
                + ", valorTotalLancamento=" + valorTotalLancamento + ", dataBacen=" + dataBacen + ", dataMovimento="
                + dataMovimento + ", historicoMovimento='" + historicoMovimento + '\'' + ", situacao=" + situacao
                + ", arquivoStr=" + arquivoStr + ", dataProcessamento=" + dataProcessamento + ", usuarioLancamento="
                + usuarioLancamento + '}';
    }
}
