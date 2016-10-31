package br.gov.to.sefaz.cci.persistence.entity;

import br.gov.to.sefaz.persistence.entity.AbstractEntity;

import java.util.Collection;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entidade de Porte Empresa referente à Tabela SEFAZ_CCI.TA_PORTE_EMPRESA da base de dados.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 30/08/2016 11:10:00
 */
@Entity
@Table(name = "TA_PORTE_EMPRESA", schema = "SEFAZ_CCI")
public class PorteEmpresa extends AbstractEntity<Integer> {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Column(name = "COD_PORTE_EMPRESA")
    private Integer codPorteEmpresa;

    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "TXT_PORTE_EMPRESA")
    private String txtPorteEmpresa;

    @OneToMany(mappedBy = "codPorte")
    private Collection<PessoaJuridica> pessoaJuridicaCollection;

    @Override
    public Integer getId() {
        return codPorteEmpresa;
    }

    public Integer getCodPorteEmpresa() {
        return codPorteEmpresa;
    }

    public void setCodPorteEmpresa(Integer codPorteEmpresa) {
        this.codPorteEmpresa = codPorteEmpresa;
    }

    public String getTxtPorteEmpresa() {
        return txtPorteEmpresa;
    }

    public void setTxtPorteEmpresa(String txtPorteEmpresa) {
        this.txtPorteEmpresa = txtPorteEmpresa;
    }

    public Collection<PessoaJuridica> getPessoaJuridicaCollection() {
        return pessoaJuridicaCollection;
    }

    public void setPessoaJuridicaCollection(Collection<PessoaJuridica> pessoaJuridicaCollection) {
        this.pessoaJuridicaCollection = pessoaJuridicaCollection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PorteEmpresa that = (PorteEmpresa) o;
        return Objects.equals(codPorteEmpresa, that.codPorteEmpresa)
                && Objects.equals(txtPorteEmpresa, that.txtPorteEmpresa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codPorteEmpresa, txtPorteEmpresa);
    }

    @Override
    public String toString() {
        return "PorteEmpresa{"
                + "codPorteEmpresa=" + codPorteEmpresa
                + ", txtPorteEmpresa='" + txtPorteEmpresa + '\''
                + ", pessoaJuridicaCollection=" + pessoaJuridicaCollection
                + '}';
    }
}
