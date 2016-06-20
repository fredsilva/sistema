package br.gov.to.sefaz.seg.persistence.entity;

import br.gov.to.sefaz.persistence.entity.AbstractEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Entidade referente a tabela SEFAZ_SEG.TA_USUARIO_PERFIL do Banco de Dados.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 13/05/2016 10:30:37
 */
@Entity
@Table(name = "TA_USUARIO_PERFIL", schema = "SEFAZ_SEG")
public class UsuarioPerfil extends AbstractEntity<Long> {

    private static final long serialVersionUID = 8401087571441586038L;

    @Id
    @NotNull
    @Column(name = "IDENTIFICACAO_USUARIO_PERFIL")
    private Long identificacaoUsuarioPerfil;

    @NotNull
    @Column(name = "SITUACAO_PERFIL")
    private Character situacaoPerfil;

    @NotNull
    @Column(name = "CPF_USUARIO")
    private String cpfUsuario;

    @NotNull
    @Column(name = "IDENTIFICACAO_PERFIL")
    private Long identificacaoPerfil;

    @OneToMany
    @JoinColumn(name = "IDENTIFICACAO_PERFIL", referencedColumnName = "IDENTIFICACAO_PERFIL",
            insertable = false, updatable = false)
    @Fetch(FetchMode.JOIN)
    private Set<PerfilSistema> perfisSistema;

    public UsuarioPerfil() {
        // Construtor para inicialização por reflexão.
    }

    public UsuarioPerfil(Long identificacaoUsuarioPerfil, Character situacaoPerfil, String cpfUsuario,
            Long identificacaoPerfil) {
        this.identificacaoUsuarioPerfil = identificacaoUsuarioPerfil;
        this.situacaoPerfil = situacaoPerfil;
        this.cpfUsuario = cpfUsuario;
        this.identificacaoPerfil = identificacaoPerfil;
    }

    @Override
    public Long getId() {
        return identificacaoUsuarioPerfil;
    }

    public Long getIdentificacaoUsuarioPerfil() {
        return identificacaoUsuarioPerfil;
    }

    public void setIdentificacaoUsuarioPerfil(Long identificacaoUsuarioPerfil) {
        this.identificacaoUsuarioPerfil = identificacaoUsuarioPerfil;
    }

    public Character getSituacaoPerfil() {
        return situacaoPerfil;
    }

    public void setSituacaoPerfil(Character situacaoPerfil) {
        this.situacaoPerfil = situacaoPerfil;
    }

    public String getCpfUsuario() {
        return cpfUsuario;
    }

    public Long getIdentificacaoPerfil() {
        return identificacaoPerfil;
    }

    public Set<PerfilSistema> getPerfisSistema() {
        return perfisSistema;
    }

    public void setPerfisSistema(Set<PerfilSistema> perfisSistema) {
        this.perfisSistema = perfisSistema;
    }

    public void setIdentificacaoPerfil(Long identificacaoPerfil) {
        this.identificacaoPerfil = identificacaoPerfil;
    }

    public void setCpfUsuario(String cpfUsuario) {
        this.cpfUsuario = cpfUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UsuarioPerfil that = (UsuarioPerfil) o;
        return Objects.equals(identificacaoUsuarioPerfil, that.identificacaoUsuarioPerfil)
                && Objects.equals(situacaoPerfil, that.situacaoPerfil)
                && Objects.equals(cpfUsuario, that.cpfUsuario)
                && Objects.equals(identificacaoPerfil, that.identificacaoPerfil);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificacaoUsuarioPerfil, situacaoPerfil, cpfUsuario, identificacaoPerfil);
    }

    @Override
    public String toString() {
        return "UsuarioPerfil{"
                + "identificacaoUsuarioPerfil=" + identificacaoUsuarioPerfil
                + ", situacaoPerfil=" + situacaoPerfil
                + ", cpfUsuario='" + cpfUsuario + '\''
                + ", identificacaoPerfil=" + identificacaoPerfil
                + '}';
    }
}
