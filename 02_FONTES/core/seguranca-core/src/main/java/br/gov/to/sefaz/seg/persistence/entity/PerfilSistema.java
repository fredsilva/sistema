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
import javax.validation.constraints.Size;

/**
 * Entidade referente a tabela SEFAZ_SEG.TA_PERFIL_SISTEMA do Banco de Dados.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 13/05/2016 10:30:37
 */
@Entity
@Table(name = "TA_PERFIL_SISTEMA", schema = "SEFAZ_SEG")
public class PerfilSistema extends AbstractEntity<Long> {

    private static final long serialVersionUID = 5477245168795013108L;

    @Id
    @NotNull
    @Column(name = "IDENTIFICACAO_PERFIL")
    private Long identificacaoPerfil;

    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOME_PERFIL")
    private String nomePerfil;

    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "DESCRICAO_PERFIL")
    private String descricaoPerfil;

    @OneToMany
    @JoinColumn(name = "IDENTIFICACAO_PERFIL", insertable = false, updatable = false)
    @Fetch(FetchMode.JOIN)
    private Set<PerfilPapel> perfilPapel;

    public PerfilSistema() {
        // Construtor para inicialização por reflexão.
    }

    public PerfilSistema(Long identificacaoPerfil, String nomePerfil, String descricaoPerfil) {
        this.identificacaoPerfil = identificacaoPerfil;
        this.nomePerfil = nomePerfil;
        this.descricaoPerfil = descricaoPerfil;
    }

    @Override
    public Long getId() {
        return identificacaoPerfil;
    }

    public Long getIdentificacaoPerfil() {
        return identificacaoPerfil;
    }

    public void setIdentificacaoPerfil(Long identificacaoPerfil) {
        this.identificacaoPerfil = identificacaoPerfil;
    }

    public String getNomePerfil() {
        return nomePerfil;
    }

    public void setNomePerfil(String nomePerfil) {
        this.nomePerfil = nomePerfil;
    }

    public String getDescricaoPerfil() {
        return descricaoPerfil;
    }

    public void setDescricaoPerfil(String descricaoPerfil) {
        this.descricaoPerfil = descricaoPerfil;
    }

    public Set<PerfilPapel> getPerfilPapel() {
        return perfilPapel;
    }

    public void setPerfilPapel(Set<PerfilPapel> perfilPapel) {
        this.perfilPapel = perfilPapel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PerfilSistema that = (PerfilSistema) o;
        return Objects.equals(identificacaoPerfil, that.identificacaoPerfil)
                && Objects.equals(nomePerfil, that.nomePerfil)
                && Objects.equals(descricaoPerfil, that.descricaoPerfil);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificacaoPerfil, nomePerfil, descricaoPerfil);
    }

    @Override
    public String toString() {
        return "PerfilSistema{"
                + "identificacaoPerfil=" + identificacaoPerfil
                + ", nomePerfil='" + nomePerfil + '\''
                + ", descricaoPerfil='" + descricaoPerfil + '\''
                + '}';
    }
}
