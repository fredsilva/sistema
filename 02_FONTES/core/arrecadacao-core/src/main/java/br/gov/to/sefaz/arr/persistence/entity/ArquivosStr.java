package br.gov.to.sefaz.arr.persistence.entity;

import br.gov.to.sefaz.arr.persistence.converter.SituacaoProcessamentoStrEnumConverter;
import br.gov.to.sefaz.arr.persistence.converter.TipoReceitaStrEnumConverter;
import br.gov.to.sefaz.arr.persistence.converter.TipoRecolhimentoStrEnumConverter;
import br.gov.to.sefaz.arr.persistence.enums.TipoReceitaStrEnum;
import br.gov.to.sefaz.arr.persistence.enums.TipoRecolhimentoStrEnum;
import br.gov.to.sefaz.arr.processamento.domain.str.SituacaoProcessamentoStrEnum;
import br.gov.to.sefaz.persistence.entity.AbstractEntity;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateConverter;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateTimeConverter;

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
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ARQUIVOS_STR")
    @SequenceGenerator(name = "SQ_ARQUIVOS_STR", schema = "SEFAZ_ARR", sequenceName = "SQ_ARQUIVOS_STR",
            allocationSize = 1)
    @Column(name = "ID_ARQUIVO_STR")
    private Long idArquivoStr;

    @Convert(converter = LocalDateConverter.class)
    @Column(name = "DATA_RECEPCAO")
    private LocalDate dataRecepcao;

    @Size(max = 20)
    @Column(name = "NUMERO_CONTROLE_STR", length = 20)
    private String numeroControleStr;

    @Convert(converter = LocalDateConverter.class)
    @Column(name = "DATA_ARRECADACAO")
    private LocalDate dataArrecadacao;

    @Column(name = "ID_BANCO_DEBITADO")
    private Integer idBancoDebitado;

    @Column(name = "ID_BANCO_CREDITADO")
    private Integer idBancoCreditado;

    @Column(name = "ID_AGENCIA_CREDITADA")
    private Integer idAgenciaCreditada;

    @Size(max = 15)
    @Column(name = "CONTA_CREDITADA", length = 15)
    private String contaCreditada;

    @Convert(converter = TipoReceitaStrEnumConverter.class)
    @Column(name = "TIPO_RECEITA")
    private TipoReceitaStrEnum tipoReceita;

    @Convert(converter = TipoRecolhimentoStrEnumConverter.class)
    @Column(name = "TIPO_RECOLHIMENTO")
    private TipoRecolhimentoStrEnum tipoRecolhimento;

    @Column(name = "VALOR_TOTAL_LANCAMENTO", precision = 14, scale = 2)
    private BigDecimal valorTotalLancamento;

    @Column(name = "DATA_BACEN")
    @Convert(converter = LocalDateConverter.class)
    private LocalDate dataBacen;

    @Convert(converter = LocalDateConverter.class)
    @Column(name = "DATA_MOVIMENTO")
    private LocalDate dataMovimento;

    @Size(max = 500)
    @Column(name = "HISTORICO_MOVIMENTO", length = 500)
    private String historicoMovimento;

    @Convert(converter = SituacaoProcessamentoStrEnumConverter.class)
    @Column(name = "SITUACAO")
    private SituacaoProcessamentoStrEnum situacao;

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

    public ArquivosStr(Long idArquivoStr, LocalDate dataRecepcao, String numeroControleStr, LocalDate dataArrecadacao,
            Integer idBancoDebitado, Integer idBancoCreditado, Integer idAgenciaCreditada, String contaCreditada,
            TipoReceitaStrEnum tipoReceita, TipoRecolhimentoStrEnum tipoRecolhimento, BigDecimal valorTotalLancamento,
            LocalDate dataBacen, LocalDate dataMovimento, String historicoMovimento, SituacaoProcessamentoStrEnum
            situacao, byte[] arquivoStr, LocalDateTime dataProcessamento, Long usuarioLancamento) {
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

    public LocalDate getDataRecepcao() {
        return dataRecepcao;
    }

    public void setDataRecepcao(LocalDate dataRecepcao) {
        this.dataRecepcao = dataRecepcao;
    }

    public String getNumeroControleStr() {
        return numeroControleStr;
    }

    public void setNumeroControleStr(String numeroControleStr) {
        this.numeroControleStr = numeroControleStr;
    }

    public LocalDate getDataArrecadacao() {
        return dataArrecadacao;
    }

    public void setDataArrecadacao(LocalDate dataArrecadacao) {
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

    public TipoReceitaStrEnum getTipoReceita() {
        return tipoReceita;
    }

    public void setTipoReceita(TipoReceitaStrEnum tipoReceita) {
        this.tipoReceita = tipoReceita;
    }

    public TipoRecolhimentoStrEnum getTipoRecolhimento() {
        return tipoRecolhimento;
    }

    public void setTipoRecolhimento(TipoRecolhimentoStrEnum tipoRecolhimento) {
        this.tipoRecolhimento = tipoRecolhimento;
    }

    public BigDecimal getValorTotalLancamento() {
        return valorTotalLancamento;
    }

    public void setValorTotalLancamento(BigDecimal valorTotalLancamento) {
        this.valorTotalLancamento = valorTotalLancamento;
    }

    public LocalDate getDataBacen() {
        return dataBacen;
    }

    public void setDataBacen(LocalDate dataBacen) {
        this.dataBacen = dataBacen;
    }

    public LocalDate getDataMovimento() {
        return dataMovimento;
    }

    public void setDataMovimento(LocalDate dataMovimento) {
        this.dataMovimento = dataMovimento;
    }

    public String getHistoricoMovimento() {
        return historicoMovimento;
    }

    public void setHistoricoMovimento(String historicoMovimento) {
        this.historicoMovimento = historicoMovimento;
    }

    public SituacaoProcessamentoStrEnum getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoProcessamentoStrEnum situacao) {
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
        return Objects.equals(idBancoDebitado, that.idBancoDebitado)
                && Objects.equals(idBancoCreditado, that.idBancoCreditado)
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
                && Arrays.equals(arquivoStr, that.arquivoStr)
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
        return "ArquivosStrService{" + "idArquivoStr=" + idArquivoStr + ", dataRecepcao=" + dataRecepcao
                + ", numeroControleStr='" + numeroControleStr + '\'' + ", dataArrecadacao=" + dataArrecadacao
                + ", idBancoDebitado=" + idBancoDebitado + ", idBancoCreditado=" + idBancoCreditado
                + ", idAgenciaCreditada=" + idAgenciaCreditada + ", contaCreditada='" + contaCreditada + '\''
                + ", tipoReceita=" + tipoReceita + ", tipoRecolhimento='" + tipoRecolhimento + '\''
                + ", valorTotalLancamento=" + valorTotalLancamento + ", dataBacen=" + dataBacen + ", dataMovimento="
                + dataMovimento + ", historicoMovimento='" + historicoMovimento + '\'' + ", situacao=" + situacao
                + ", arquivoStr=" + Arrays.toString(arquivoStr) + ", dataProcessamento="
                + dataProcessamento + ", usuarioLancamento="
                + usuarioLancamento + '}';
    }
}
