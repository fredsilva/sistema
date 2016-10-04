package br.gov.to.sefaz.arr.persistence.entity;

import br.gov.to.sefaz.persistence.entity.AbstractEntity;

import java.util.Collection;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entidade que representa os dados da tabela SEFAZ_ARR.TA_DELEGACIA_AGENCIAS.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 20/05/2016 15:18:05
 */
@Entity
@Table(name = "TA_DELEGACIA_AGENCIAS", schema = "SEFAZ_ARR")
@IdClass(DelegaciaAgenciasPK.class)
public class DelegaciaAgencias extends AbstractEntity<DelegaciaAgenciasPK> {

    private static final long serialVersionUID = -7905346266301701566L;

    @Id
    @NotNull
    @Column(name = "ID_UNIDADE_DELEGACIA")
    private Integer idUnidadeDelegacia;
    @Id
    @NotNull
    @Column(name = "ID_DELEGACIA")
    private Integer idDelegacia;
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "DESCRICAO")
    private String descricao;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "delegaciaAgencias")
    private Collection<PedidoAreas> pedidoAreasCollection;

    @JoinColumn(name = "ID_DELEGACIA", referencedColumnName = "ID_DELEGACIA", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Delegacias delegacias;

    public DelegaciaAgencias() {
        this.delegacias = new Delegacias();
        // Construtor para inicialização por reflexão.
    }

    public DelegaciaAgencias(Integer idUnidadeDelegacia, Integer idDelegacia, String descricao,
            Collection<PedidoAreas> pedidoAreasCollection, Delegacias delegacias) {
        this.idUnidadeDelegacia = idUnidadeDelegacia;
        this.idDelegacia = idDelegacia;
        this.descricao = descricao;
        this.pedidoAreasCollection = pedidoAreasCollection;
        this.delegacias = delegacias;
    }

    @Override
    public DelegaciaAgenciasPK getId() {
        return new DelegaciaAgenciasPK(getIdUnidadeDelegacia(), getIdDelegacia());
    }

    public Integer getIdUnidadeDelegacia() {
        return idUnidadeDelegacia;
    }

    public void setIdUnidadeDelegacia(Integer idUnidadeDelegacia) {
        this.idUnidadeDelegacia = idUnidadeDelegacia;
    }

    public Integer getIdDelegacia() {
        return idDelegacia;
    }

    /**
     * Seta delegacia.
     * @param idDelegacia id da delegacia.
     */
    public void setIdDelegacia(Integer idDelegacia) {
        this.idDelegacia = idDelegacia;
        this.delegacias.setIdDelegacia(idDelegacia);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Collection<PedidoAreas> getPedidoAreasCollection() {
        return pedidoAreasCollection;
    }

    public void setPedidoAreasCollection(Collection<PedidoAreas> pedidoAreasCollection) {
        this.pedidoAreasCollection = pedidoAreasCollection;
    }

    public Delegacias getDelegacias() {
        return delegacias;
    }

    public void setDelegacias(Delegacias delegacias) {
        this.delegacias = delegacias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DelegaciaAgencias that = (DelegaciaAgencias) o;
        return Objects.equals(idUnidadeDelegacia, that.idUnidadeDelegacia)
                && Objects.equals(idDelegacia, that.idDelegacia)
                && Objects.equals(descricao, that.descricao)
                && Objects.equals(pedidoAreasCollection, that.pedidoAreasCollection)
                && Objects.equals(delegacias, that.delegacias);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUnidadeDelegacia, idDelegacia, descricao, pedidoAreasCollection, delegacias);
    }

    @Override
    public String toString() {
        return "DelegaciaAgencias{"
                + "idUnidadeDelegacia=" + idUnidadeDelegacia
                + ", idDelegacia=" + idDelegacia
                + ", descricao='" + descricao + '\''
                + ", pedidoAreasCollection=" + pedidoAreasCollection
                + ", delegacias=" + delegacias
                + '}';
    }
}

/*

 */