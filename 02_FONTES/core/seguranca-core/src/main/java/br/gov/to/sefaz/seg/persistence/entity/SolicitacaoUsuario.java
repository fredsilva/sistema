package br.gov.to.sefaz.seg.persistence.entity;

import br.gov.to.sefaz.persistence.entity.AbstractEntity;

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
    @NotNull
    @Column(name = "IDENTIFICACAO_SOLICITACAO")
    private Long identificacaoSolicitacao;

    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "CNPJ_NEGOCIO")
    private String cnpjNegocio;

    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "INSCRICAO_ESTADUAL_NEGOCIO")
    private String inscricaoEstadualNegocio;

    @NotNull
    @Column(name = "SITUACAO_SOLICITACAO")
    private Character situacaoSolicitacao;

    @JoinColumn(name = "CPF", referencedColumnName = "CPF_USUARIO")
    @ManyToOne(optional = false)
    private UsuarioSistema usuarioSistema;

    public SolicitacaoUsuario() {
        // Construtor para inicialização por reflexão.
    }

    public SolicitacaoUsuario(Long identificacaoSolicitacao, String cnpjNegocio, String inscricaoEstadualNegocio,
            Character situacaoSolicitacao, UsuarioSistema usuarioSistema) {
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

    public Character getSituacaoSolicitacao() {
        return situacaoSolicitacao;
    }

    public void setSituacaoSolicitacao(Character situacaoSolicitacao) {
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

}
