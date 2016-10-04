package br.gov.to.sefaz.seg.persistence.entity;

import br.gov.to.sefaz.persistence.entity.AbstractEntity;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateTimeConverter;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Entidade referente a tabela SEFAZ_SEG.TA_CORREIO_CONTRIBUINTE do Banco de Dados.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 13/05/2016 10:30:37
 */
@Entity
@Table(name = "TA_CORREIO_CONTRIBUINTE", schema = "SEFAZ_SEG")
public class CorreioContribuinte extends AbstractEntity<Long> {

    private static final long serialVersionUID = -2415482443943915238L;

    @Id
    @SequenceGenerator(name = "SQ_CORREIO_CONTRIBUINTE", schema = "SEFAZ_SEG",
            sequenceName = "SQ_CORREIO_CONTRIBUINTE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CORREIO_CONTRIBUINTE")
    @Column(name = "IDENTIFICACAO_CORREIO_ENVIADO")
    private Long identificacaoCorreioEnviado;

    @NotEmpty(message = "#{seg_msg['correioContribuinte.cpfDestinatario.obrigatorio']}")
    @Column(name = "CPF_DESTINATARIO")
    private String cpfDestinatario;

    @NotEmpty(message = "#{seg_msg['correioContribuinte.correioEletronico.obrigatorio']}")
    @Column(name = "CORREIO_ELETRONICO")
    private String correioEletronico;

    @NotNull(message = "#{seg_msg['correioContribuinte.dataEnvio.obrigatorio']}")
    @Column(name = "DATA_ENVIO")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime dataEnvio;

    @NotEmpty(message = "#{seg_msg['correioContribuinte.assunto.obrigatorio']}")
    @Column(name = "ASSUNTO")
    private String assunto;

    @NotNull(message = "#{seg_msg['correioContribuinte.conteudo.obrigatorio']}")
    @Basic(fetch = FetchType.EAGER)
    @Lob
    @Column(name = "CONTEUDO")
    private String conteudo;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "ANEXO")
    private byte[] anexo;

    @JoinColumn(name = "CPF_DESTINATARIO", referencedColumnName = "CPF_USUARIO",
            insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UsuarioSistema destinatario;

    public CorreioContribuinte() {
        // Construtor para inicialização por reflexão.
    }

    public CorreioContribuinte(String cpfDestinatario, String correioEletronico, LocalDateTime dataEnvio,
            String assunto, String conteudo) {
        this.cpfDestinatario = cpfDestinatario;
        this.correioEletronico = correioEletronico;
        this.dataEnvio = dataEnvio;
        this.assunto = assunto;
        this.conteudo = conteudo;
    }

    @Override
    public Long getId() {
        return identificacaoCorreioEnviado;
    }

    public Long getIdentificacaoCorreioEnviado() {
        return identificacaoCorreioEnviado;
    }

    public void setIdentificacaoCorreioEnviado(Long identificacaoCorreioEnviado) {
        this.identificacaoCorreioEnviado = identificacaoCorreioEnviado;
    }

    public String getCpfDestinatario() {
        return cpfDestinatario;
    }

    public void setCpfDestinatario(String cpfDestinatario) {
        this.cpfDestinatario = cpfDestinatario;
    }

    public String getCorreioEletronico() {
        return correioEletronico;
    }

    public void setCorreioEletronico(String correioEletronico) {
        this.correioEletronico = correioEletronico;
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

    public byte[] getAnexo() {
        return anexo;
    }

    public void setAnexo(byte[] anexo) {
        this.anexo = anexo;
    }

    public UsuarioSistema getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(UsuarioSistema destinatario) {
        this.destinatario = destinatario;
    }

    @Override
    public String toString() {
        return "CorreioContribuinte [identificacaoCorreioEnviado=" + identificacaoCorreioEnviado
                + ", correioEletronico=" + correioEletronico + ", dataEnvio=" + dataEnvio + ", assunto=" + assunto
                + ", conteudo=" + conteudo + ", anexo=" + Arrays.toString(anexo)
                + ", destinatario=" + destinatario + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificacaoCorreioEnviado, correioEletronico, dataEnvio, assunto, conteudo, destinatario);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CorreioContribuinte that = (CorreioContribuinte) obj;
        return Objects.equals(identificacaoCorreioEnviado, that.identificacaoCorreioEnviado)
                && Objects.equals(correioEletronico, that.correioEletronico)
                && Objects.equals(dataEnvio, that.dataEnvio) && Objects.equals(assunto, that.assunto)
                && Objects.equals(conteudo, that.conteudo) && Objects.equals(destinatario, that.destinatario);
    }

}
