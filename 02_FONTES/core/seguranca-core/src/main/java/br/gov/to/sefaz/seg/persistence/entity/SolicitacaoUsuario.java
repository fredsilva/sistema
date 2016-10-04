package br.gov.to.sefaz.seg.persistence.entity;

import br.gov.to.sefaz.persistence.entity.AbstractEntity;
import br.gov.to.sefaz.seg.persistence.converter.SituacaoSolicitacaoEnumConverter;
import br.gov.to.sefaz.seg.persistence.enums.SituacaoSolicitacaoEnum;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CNPJ;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entidade referente a tabela SEFAZ_SEG.TA_SOLICITACAO_USUARIO do Banco de Dados.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 13/05/2016 10:30:37
 */
@Entity
@Table(name = "TA_SOLICITACAO_USUARIO", schema = "SEFAZ_SEG")
public class SolicitacaoUsuario extends AbstractEntity<Long> {

    private static final long serialVersionUID = -4907743764166416720L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_solicitacao_usuario")
    @SequenceGenerator(name = "sq_solicitacao_usuario", schema = "SEFAZ_SEG",
            sequenceName = "sq_solicitacao_usuario", allocationSize = 1)
    @Max(value = 9999999999L, message = "#{seg_msg['seg.gestao.solicitacaoUsuario.identificacaoSolicitacao.maximo']}")
    @Column(name = "IDENTIFICACAO_SOLICITACAO")
    private Long identificacaoSolicitacao;

    @CNPJ(message = "#{seg_msg['seg.gestao.solicitacaoUsuario.identificacaoSolicitacao.cnpj.incorreto']}")
    @Size(max = 30, message = "#{seg_msg['seg.solicitacaoUsuario.cnpj.tamanho']}")
    @Column(name = "CNPJ_NEGOCIO")
    private String cnpjNegocio;

    @Size(max = 30, message = "#{seg_msg['seg.solicitacaoUsuario.inscricaoEstadual.tamanho']}")
    @Column(name = "INSCRICAO_ESTADUAL_NEGOCIO")
    private String inscricaoEstadualNegocio;

    @NotNull
    @Convert(converter = SituacaoSolicitacaoEnumConverter.class)
    @Column(name = "SITUACAO_SOLICITACAO")
    private SituacaoSolicitacaoEnum situacaoSolicitacao = SituacaoSolicitacaoEnum.PENDENTE;

    @NotEmpty(message = "#{seg_msg['usuarioSistema.cpfUsuario.obrigatorio']}")
    @Size(max = 11, message = "#{seg_msg['usuarioSistema.cpfUsuario.tamanho']}")
    @Column(name = "CPF")
    private String cpfUsuario;

    @JoinColumn(name = "CPF", referencedColumnName = "CPF_USUARIO", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UsuarioSistema usuarioSistema;

    public SolicitacaoUsuario() {
        // Construtor para inicialização por reflexão.
    }

    public SolicitacaoUsuario(Long identificacaoSolicitacao, String cnpjNegocio, String inscricaoEstadualNegocio,
            SituacaoSolicitacaoEnum situacaoSolicitacao, UsuarioSistema usuarioSistema) {
        this.identificacaoSolicitacao = identificacaoSolicitacao;
        this.cnpjNegocio = cnpjNegocio;
        this.inscricaoEstadualNegocio = inscricaoEstadualNegocio;
        this.situacaoSolicitacao = situacaoSolicitacao;
        this.usuarioSistema = usuarioSistema;
    }

    @Override
    public Long getId() {
        return identificacaoSolicitacao;
    }

    public Long getIdentificacaoSolicitacao() {
        return identificacaoSolicitacao;
    }

    public void setIdentificacaoSolicitacao(Long identificacaoSolicitacao) {
        this.identificacaoSolicitacao = identificacaoSolicitacao;
    }

    public String getCnpjNegocio() {
        return cnpjNegocio;
    }

    public void setCnpjNegocio(String cnpjNegocio) {
        this.cnpjNegocio = cnpjNegocio;
    }

    public String getInscricaoEstadualNegocio() {
        return inscricaoEstadualNegocio;
    }

    public void setInscricaoEstadualNegocio(String inscricaoEstadualNegocio) {
        this.inscricaoEstadualNegocio = inscricaoEstadualNegocio;
    }

    public SituacaoSolicitacaoEnum getSituacaoSolicitacao() {
        return situacaoSolicitacao;
    }

    public void setSituacaoSolicitacao(SituacaoSolicitacaoEnum situacaoSolicitacao) {
        this.situacaoSolicitacao = situacaoSolicitacao;
    }

    public UsuarioSistema getUsuarioSistema() {
        return usuarioSistema;
    }

    public void setUsuarioSistema(UsuarioSistema usuarioSistema) {
        this.usuarioSistema = usuarioSistema;
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificacaoSolicitacao, cnpjNegocio, inscricaoEstadualNegocio, situacaoSolicitacao,
                usuarioSistema);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SolicitacaoUsuario that = (SolicitacaoUsuario) obj;
        return Objects.equals(this.identificacaoSolicitacao, that.identificacaoSolicitacao)
                && Objects.equals(this.cnpjNegocio, that.cnpjNegocio)
                && Objects.equals(this.inscricaoEstadualNegocio, that.inscricaoEstadualNegocio)
                && Objects.equals(this.situacaoSolicitacao, that.situacaoSolicitacao)
                && Objects.equals(this.usuarioSistema, that.usuarioSistema);
    }

    @Override
    public String toString() {
        return "SolicitacaoUsuario [identificacaoSolicitacao=" + identificacaoSolicitacao + ", cnpjNegocio="
                + cnpjNegocio + ", inscricaoEstadualNegocio=" + inscricaoEstadualNegocio + ", situacaoSolicitacao="
                + situacaoSolicitacao + ", usuarioSistema=" + usuarioSistema + "]";
    }

    public String getCpfUsuario() {
        return cpfUsuario;
    }

    public void setCpfUsuario(String cpfUsuario) {
        this.cpfUsuario = cpfUsuario;
    }
}
