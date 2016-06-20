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
 * Entidade referente a tabela SEFAZ_SEG.TA_PAPEL_SISTEMA do Banco de Dados.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 13/05/2016 10:30:37
 */
@Entity
@Table(name = "TA_PAPEL_SISTEMA", schema = "SEFAZ_SEG")
public class PapelSistema extends AbstractEntity<Long> {

    private static final long serialVersionUID = 3619773195182690829L;

    @Id
    @NotNull
    @Column(name = "IDENTIFICACAO_PAPEL")
    private Long identificacaoPapel;

    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOME_PAPEL")
    private String nomePapel;

    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "DESCRICAO_PAPEL")
    private String descricaoPapel;

    @OneToMany
    @JoinColumn(name = "IDENTIFICACAO_PAPEL", referencedColumnName = "IDENTIFICACAO_PAPEL",
            insertable = false, updatable = false)
    @Fetch(FetchMode.JOIN)
    private Set<PapelOpcao> papelOpcao;

    public PapelSistema() {
        // Construtor para inicialização por reflexão.
    }

    public PapelSistema(Long identificacaoPapel, String nomePapel, String descricaoPapel) {
        this.identificacaoPapel = identificacaoPapel;
        this.nomePapel = nomePapel;
        this.descricaoPapel = descricaoPapel;
    }

    @Override
    public Long getId() {
        return identificacaoPapel;
    }

    public Long getIdentificacaoPapel() {
        return identificacaoPapel;
    }

    public void setIdentificacaoPapel(Long identificacaoPapel) {
        this.identificacaoPapel = identificacaoPapel;
    }

    public String getNomePapel() {
        return nomePapel;
    }

    public void setNomePapel(String nomePapel) {
        this.nomePapel = nomePapel;
    }

    public String getDescricaoPapel() {
        return descricaoPapel;
    }

    public void setDescricaoPapel(String descricaoPapel) {
        this.descricaoPapel = descricaoPapel;
    }

    public Set<PapelOpcao> getPapelOpcao() {
        return papelOpcao;
    }

    public void setPapelOpcao(Set<PapelOpcao> papelOpcao) {
        this.papelOpcao = papelOpcao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PapelSistema that = (PapelSistema) o;
        return Objects.equals(identificacaoPapel, that.identificacaoPapel)
                && Objects.equals(nomePapel, that.nomePapel)
                && Objects.equals(descricaoPapel, that.descricaoPapel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificacaoPapel, nomePapel, descricaoPapel);
    }

    @Override
    public String toString() {
        return "PapelSistema{"
                + "identificacaoPapel=" + identificacaoPapel
                + ", nomePapel='" + nomePapel + '\''
                + ", descricaoPapel='" + descricaoPapel + '\''
                + '}';
    }
}
