package br.gov.to.sefaz.seg.persistence.entity;

import br.gov.to.sefaz.persistence.entity.AbstractEntity;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateTimeConverter;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entidade referente a tabela SEFAZ_SEG.TA_ERRO_PROCESSO_AUDITORIA do Banco de Dados.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 13/05/2016 10:30:37
 */
@Entity
@Table(name = "TA_ERRO_PROCESSO_AUDITORIA", schema = "SEFAZ_SEG")
public class ErroProcessoAuditoria extends AbstractEntity<Long> {

    private static final long serialVersionUID = -3365775774067225892L;

    @Id
    @NotNull
    @Column(name = "IDENTIFICACAO_ERRO_LOG")
    private Long identificacaoErroLog;

    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOME_PROGRAMA")
    private String nomePrograma;

    @NotNull
    @Column(name = "CODIGO_ERRO_SQL")
    private Long codigoErroSql;

    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "ERRO_MENSAGEM_SQL")
    private String erroMensagemSql;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ERRO_MENSAGEM")
    private String erroMensagem;

    @NotNull
    @Column(name = "DATA_ERRO")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime dataErro;

    public ErroProcessoAuditoria() {
        // Construtor para inicialização por reflexão.
    }

    public ErroProcessoAuditoria(Long identificacaoErroLog, String nomePrograma, Long codigoErroSql,
            String erroMensagemSql, String erroMensagem, LocalDateTime dataErro) {
        this.identificacaoErroLog = identificacaoErroLog;
        this.nomePrograma = nomePrograma;
        this.codigoErroSql = codigoErroSql;
        this.erroMensagemSql = erroMensagemSql;
        this.erroMensagem = erroMensagem;
        this.dataErro = dataErro;
    }

    @Override
    public Long getId() {
        return identificacaoErroLog;
    }

    public Long getIdentificacaoErroLog() {
        return identificacaoErroLog;
    }

    public void setIdentificacaoErroLog(Long identificacaoErroLog) {
        this.identificacaoErroLog = identificacaoErroLog;
    }

    public String getNomePrograma() {
        return nomePrograma;
    }

    public void setNomePrograma(String nomePrograma) {
        this.nomePrograma = nomePrograma;
    }

    public Long getCodigoErroSql() {
        return codigoErroSql;
    }

    public void setCodigoErroSql(Long codigoErroSql) {
        this.codigoErroSql = codigoErroSql;
    }

    public String getErroMensagemSql() {
        return erroMensagemSql;
    }

    public void setErroMensagemSql(String erroMensagemSql) {
        this.erroMensagemSql = erroMensagemSql;
    }

    public String getErroMensagem() {
        return erroMensagem;
    }

    public void setErroMensagem(String erroMensagem) {
        this.erroMensagem = erroMensagem;
    }

    public LocalDateTime getDataErro() {
        return dataErro;
    }

    public void setDataErro(LocalDateTime dataErro) {
        this.dataErro = dataErro;
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificacaoErroLog, nomePrograma, codigoErroSql, erroMensagemSql, erroMensagem, dataErro);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ErroProcessoAuditoria that = (ErroProcessoAuditoria) obj;
        return Objects.equals(this.identificacaoErroLog, that.identificacaoErroLog)
                && Objects.equals(this.nomePrograma, that.nomePrograma)
                && Objects.equals(this.codigoErroSql, that.codigoErroSql)
                && Objects.equals(this.erroMensagemSql, that.erroMensagemSql)
                && Objects.equals(this.erroMensagem, that.erroMensagem) && Objects.equals(this.dataErro, that.dataErro);
    }

    @Override
    public String toString() {
        return "ErroProcessoAuditoria [identificacaoErroLog=" + identificacaoErroLog + ", nomePrograma=" + nomePrograma
                + ", codigoErroSql=" + codigoErroSql + ", erroMensagemSql=" + erroMensagemSql + ", erroMensagem="
                + erroMensagem + ", dataErro=" + dataErro + "]";
    }

}
