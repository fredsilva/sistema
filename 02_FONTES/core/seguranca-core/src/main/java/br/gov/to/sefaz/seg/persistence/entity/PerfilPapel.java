package br.gov.to.sefaz.seg.persistence.entity;

import br.gov.to.sefaz.persistence.entity.AbstractEntity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Entidade referente a tabela SEFAZ_SEG.TA_PERFIL_PAPEL do Banco de Dados.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 13/05/2016 10:30:37
 */
@Entity
@Table(name = "TA_PERFIL_PAPEL", schema = "SEFAZ_SEG")
@IdClass(value = PerfilPapelPK.class)
public class PerfilPapel extends AbstractEntity<PerfilPapelPK> {

    private static final long serialVersionUID = 3753668526556735107L;

    @Id
    @NotNull
    @Column(name = "IDENTIFICACAO_PERFIL")
    private Long identificacaoPerfil;

    @Id
    @NotNull
    @Column(name = "IDENTIFICACAO_PAPEL")
    private Long identificacaoPapel;

    @JoinColumn(name = "IDENTIFICACAO_PAPEL", referencedColumnName = "IDENTIFICACAO_PAPEL",
            insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PapelSistema papelSistema;

    @JoinColumn(name = "IDENTIFICACAO_PERFIL", referencedColumnName = "IDENTIFICACAO_PERFIL",
            insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PerfilSistema perfilSistema;

    public PerfilPapel() {
        // Construtor para inicialização por reflexão.
    }

    public PerfilPapel(Long identificacaoPerfil, Long identificacaoPapel) {
        this.identificacaoPerfil = identificacaoPerfil;
        this.identificacaoPapel = identificacaoPapel;
    }

    @Override
    public PerfilPapelPK getId() {
        return new PerfilPapelPK(identificacaoPerfil, identificacaoPapel);
    }

    public Long getIdentificacaoPerfil() {
        return identificacaoPerfil;
    }

    public void setIdentificacaoPerfil(Long identificacaoPerfil) {
        this.identificacaoPerfil = identificacaoPerfil;
    }

    public Long getIdentificacaoPapel() {
        return identificacaoPapel;
    }

    public void setIdentificacaoPapel(Long identificacaoPapel) {
        this.identificacaoPapel = identificacaoPapel;
    }

    public PapelSistema getPapelSistema() {
        return papelSistema;
    }

    public void setPapelSistema(PapelSistema papelSistema) {
        this.papelSistema = papelSistema;
    }

    public PerfilSistema getPerfilSistema() {
        return perfilSistema;
    }

    public void setPerfilSistema(PerfilSistema perfilSistema) {
        this.perfilSistema = perfilSistema;
    }

    public String getNomePapel() {
        return Objects.isNull(papelSistema) ? "" : papelSistema.getNomePapel();
    }

    public String getDescricaoPapel() {
        return Objects.isNull(papelSistema) ? "" : papelSistema.getDescricaoPapel();
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificacaoPerfil, identificacaoPapel);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PerfilPapel that = (PerfilPapel) obj;
        return Objects.equals(this.identificacaoPerfil, that.identificacaoPerfil)
                && Objects.equals(this.identificacaoPapel, that.identificacaoPapel);
    }

    @Override
    public String toString() {
        return "PerfilPapel [identificacaoPerfil=" + identificacaoPerfil + ", identificacaoPapel=" + identificacaoPapel
                + "]";
    }

}
