package br.gov.to.sefaz.seg.persistence.entity;

import br.gov.to.sefaz.persistence.entity.AbstractEntity;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_perfil_sistema")
    @SequenceGenerator(name = "sq_perfil_sistema", schema = "SEFAZ_SEG",
            sequenceName = "sq_perfil_sistema",
            allocationSize = 1)
    @Column(name = "IDENTIFICACAO_PERFIL")
    private Long identificacaoPerfil;

    @NotEmpty(message = "#{seg_msg['seg.gestao.perfilSistema.nomePerfilSistema.vazio']}")
    @Size(max = 30, message = "#{seg_msg['seg.gestao.perfilSistema.nomePerfilSistema.maximo']}")
    @Column(name = "NOME_PERFIL")
    private String nomePerfil;

    @NotEmpty(message = "#{seg_msg['seg.gestao.perfilSistema.descricaoPerfilSistema.vazio']}")
    @Size(max = 120, message = "#{seg_msg['seg.gestao.perfilSistema.descricaoPerfilSistema.maximo']}")
    @Column(name = "DESCRICAO_PERFIL")
    private String descricaoPerfil;

    @OneToMany(mappedBy = "perfilSistema", fetch = FetchType.LAZY)
    private Set<PerfilPapel> perfilPapel;

    @OneToMany(mappedBy = "perfisSistema", fetch = FetchType.LAZY)
    private Set<UsuarioPerfil> usuarioPerfil;

    @Transient
    private Long totalUsuarios;

    @Transient
    private Long totalPapeis;

    public PerfilSistema() {
        // Construtor para inicialização por reflexão.
        perfilPapel = new HashSet<>();
    }

    public PerfilSistema(Long identificacaoPerfil, String nomePerfil, String descricaoPerfil) {
        this.identificacaoPerfil = identificacaoPerfil;
        this.nomePerfil = nomePerfil;
        this.descricaoPerfil = descricaoPerfil;
    }

    public PerfilSistema(Long identificacaoPerfil, String nomePerfil, String descricaoPerfil, Long totalUsuarios,
            Long totalPapeis) {
        this.identificacaoPerfil = identificacaoPerfil;
        this.nomePerfil = nomePerfil;
        this.descricaoPerfil = descricaoPerfil;
        this.totalUsuarios = totalUsuarios;
        this.totalPapeis = totalPapeis;
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

    public Set<UsuarioPerfil> getUsuarioPerfil() {
        return usuarioPerfil;
    }

    public void setUsuarioPerfil(Set<UsuarioPerfil> usuarioPerfil) {
        this.usuarioPerfil = usuarioPerfil;
    }

    public Long getTotalUsuarios() {
        return totalUsuarios;
    }

    public void setTotalUsuarios(Long totalUsuarios) {
        this.totalUsuarios = totalUsuarios;
    }

    public Long getTotalPapeis() {
        return totalPapeis;
    }

    public void setTotalPapeis(Long totalPapeis) {
        this.totalPapeis = totalPapeis;
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
