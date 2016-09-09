package br.gov.to.sefaz.seg.persistence.entity;

import br.gov.to.sefaz.persistence.entity.AbstractEntity;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entidade referente a tabela SEFAZ_SEG.TA_SMS_CONTRIBUINTE do Banco de Dados.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 13/05/2016 10:30:37
 */
@Entity
@Table(name = "TA_SMS_CONTRIBUINTE", schema = "SEFAZ_SEG")
public class SmsContribuinte extends AbstractEntity<Long> {

    private static final long serialVersionUID = 2362003670955686419L;

    @Id
    @NotNull
    @Column(name = "IDENTIFICACAO_SMS_ENVIADO")
    private Long identificacaoSmsEnviado;

    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "NUMERO_TELEFONE")
    private String numeroTelefone;

    @NotNull
    @Column(name = "DATA_ENVIO")
    private LocalDateTime dataEnvio;

    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "ASSUNTO")
    private String assunto;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "CONTEUDO")
    private String conteudo;

    @JoinColumn(name = "CPF_DESTINATARIO", referencedColumnName = "CPF_USUARIO")
    @ManyToOne(optional = false)
    private UsuarioSistema destinatario;

    public SmsContribuinte() {
        // Construtor para inicialização por reflexão.
    }

    public SmsContribuinte(Long identificacaoSmsEnviado, String numeroTelefone, LocalDateTime dataEnvio, String assunto,
            String conteudo, UsuarioSistema destinatario) {
        this.identificacaoSmsEnviado = identificacaoSmsEnviado;
        this.numeroTelefone = numeroTelefone;
        this.dataEnvio = dataEnvio;
        this.assunto = assunto;
        this.conteudo = conteudo;
        this.destinatario = destinatario;
    }

    @Override
    public Long getId() {
        return identificacaoSmsEnviado;
    }

    public Long getIdentificacaoSmsEnviado() {
        return identificacaoSmsEnviado;
    }

    public void setIdentificacaoSmsEnviado(Long identificacaoSmsEnviado) {
        this.identificacaoSmsEnviado = identificacaoSmsEnviado;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public void setNumeroTelefone(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDateTime dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public UsuarioSistema getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(UsuarioSistema destinatario) {
        this.destinatario = destinatario;
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificacaoSmsEnviado, numeroTelefone, dataEnvio, assunto, conteudo, destinatario);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SmsContribuinte that = (SmsContribuinte) obj;
        return Objects.equals(this.identificacaoSmsEnviado, that.identificacaoSmsEnviado)
                && Objects.equals(this.numeroTelefone, that.numeroTelefone)
                && Objects.equals(this.dataEnvio, that.dataEnvio) && Objects.equals(this.assunto, that.assunto)
                && Objects.equals(this.conteudo, that.conteudo) && Objects.equals(this.destinatario, that.destinatario);
    }

    @Override
    public String toString() {
        return "SmsContribuinte [identificacaoSmsEnviado=" + identificacaoSmsEnviado + ", numeroTelefone="
                + numeroTelefone + ", dataEnvio=" + dataEnvio + ", assunto=" + assunto + ", conteudo=" + conteudo
                + ", destinatario=" + destinatario + "]";
    }

}
