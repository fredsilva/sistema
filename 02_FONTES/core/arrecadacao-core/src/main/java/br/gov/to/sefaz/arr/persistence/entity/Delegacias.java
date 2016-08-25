package br.gov.to.sefaz.arr.persistence.entity;

import br.gov.to.sefaz.persistence.entity.AbstractEntity;

import java.util.Collection;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entidade que representa os dados da tabela SEFAZ_ARR.TA_DELEGACIAS.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 20/05/2016 15:18:04
 */
@Entity
@Table(name = "TA_DELEGACIAS", schema = "SEFAZ_ARR")
public class Delegacias extends AbstractEntity<Integer> {

    private static final long serialVersionUID = 4708304147213547700L;

    @Id
    @NotNull
    @Column(name = "ID_DELEGACIA")
    private Integer idDelegacia;
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "DESCRICAO")
    private String descricao;
    @NotNull
    @Column(name = "SITUACAO")
    private Integer situacao;
    @NotNull
    @Column(name = "TIPO")
    private Integer tipo;
    @Column(name = "DELEGACIA_HIERARQUICA")
    private Integer delegaciaHierarquica;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "delegacias")
    private Collection<DelegaciaAgencias> delegaciaAgenciasCollection;

    public Delegacias() {
        // Construtor para inicialização por reflexão.
    }

    public Delegacias(Integer idDelegacia, String descricao, Integer situacao, Integer tipo,
            Integer delegaciaHierarquica, Collection<DelegaciaAgencias> delegaciaAgenciasCollection) {
        this.idDelegacia = idDelegacia;
        this.descricao = descricao;
        this.situacao = situacao;
        this.tipo = tipo;
        this.delegaciaHierarquica = delegaciaHierarquica;
        this.delegaciaAgenciasCollection = delegaciaAgenciasCollection;
    }

    @Override
    public Integer getId() {
        return getIdDelegacia();
    }

    public Integer getIdDelegacia() {
        return idDelegacia;
    }

    public void setIdDelegacia(Integer idDelegacia) {
        this.idDelegacia = idDelegacia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getSituacao() {
        return situacao;
    }

    public void setSituacao(Integer situacao) {
        this.situacao = situacao;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getDelegaciaHierarquica() {
        return delegaciaHierarquica;
    }

    public void setDelegaciaHierarquica(Integer delegaciaHierarquica) {
        this.delegaciaHierarquica = delegaciaHierarquica;
    }

    public Collection<DelegaciaAgencias> getDelegaciaAgenciasCollection() {
        return delegaciaAgenciasCollection;
    }

    public void setDelegaciaAgenciasCollection(Collection<DelegaciaAgencias> delegaciaAgenciasCollection) {
        this.delegaciaAgenciasCollection = delegaciaAgenciasCollection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Delegacias that = (Delegacias) o;
        return Objects.equals(idDelegacia, that.idDelegacia)
                && Objects.equals(descricao, that.descricao)
                && Objects.equals(situacao, that.situacao)
                && Objects.equals(tipo, that.tipo)
                && Objects.equals(delegaciaHierarquica, that.delegaciaHierarquica)
                && Objects.equals(delegaciaAgenciasCollection, that.delegaciaAgenciasCollection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDelegacia, descricao, situacao,tipo, delegaciaHierarquica, delegaciaAgenciasCollection);
    }

    @Override
    public String toString() {
        return "Delegacias{"
                + "idDelegacia=" + idDelegacia
                + ", descricao='" + descricao + '\''
                + ", situacao=" + situacao
                + ", tipo=" + tipo
                + ", delegaciaHierarquica=" + delegaciaHierarquica
                + ", delegaciaAgenciasCollection=" + delegaciaAgenciasCollection
                + '}';
    }
}
