package br.gov.to.sefaz.arr.persistence.entity;

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

/**
 * Entidade referente a tabela SEFAZ_ARR.TA_DARE_ORIGEM_RECEITA do Banco de Dados.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 28/09/2016 15:46:00
 */
@Entity
@Table(name = "TA_DARE_ORIGEM_RECEITA", schema = "SEFAZ_ARR")
@IdClass(DareOrigemReceitaPK.class)
public class DareOrigemReceita extends AbstractEntity<DareOrigemReceitaPK> {

    private static final long serialVersionUID = -1319955696174840038L;

    @Id
    @Column(name = "ID_RECEITA")
    private Integer idReceita;

    @Id
    @Column(name = "ID_ORIGEM_DEBITO")
    private Integer idOrigemDebito;

    @JoinColumn(name = "ID_RECEITA", referencedColumnName = "ID_RECEITA",
            insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Receitas receitas;

    @Override
    public DareOrigemReceitaPK getId() {
        return new DareOrigemReceitaPK(idReceita, idOrigemDebito);
    }

    public Integer getIdReceita() {
        return idReceita;
    }

    public void setIdReceita(Integer idReceita) {
        this.idReceita = idReceita;
    }

    public Integer getIdOrigemDebito() {
        return idOrigemDebito;
    }

    public void setIdOrigemDebito(Integer idOrigemDebito) {
        this.idOrigemDebito = idOrigemDebito;
    }

    public Receitas getReceitas() {
        return receitas;
    }

    public void setReceitas(Receitas receitas) {
        this.receitas = receitas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DareOrigemReceita that = (DareOrigemReceita) o;
        return Objects.equals(idReceita, that.idReceita)
                && Objects.equals(idOrigemDebito, that.idOrigemDebito);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idReceita, idOrigemDebito);
    }

    @Override
    public String toString() {
        return "DareOrigemReceita{"
                + "idReceita=" + idReceita
                + ", idOrigemDebito=" + idOrigemDebito
                + ", receitas=" + receitas
                + '}';
    }
}
