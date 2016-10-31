package br.gov.to.sefaz.cci.persistence.entity;

import br.gov.to.sefaz.persistence.entity.AbstractEntity;

import java.time.LocalDate;
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
import javax.validation.constraints.Size;

/**
 * Entidade de Representante Legal referente à Tabela SEFAZ_CCI.TA_REPRESENTANTE_LEGAL da base de dados.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 30/08/2016 17:48:00
 */
@Entity
@Table(name = "TA_REPRESENTANTE_LEGAL", schema = "SEFAZ_CCI")
@IdClass(RepresentanteLegalPK.class)
public class RepresentanteLegal extends AbstractEntity<RepresentanteLegalPK> {

    private static final long serialVersionUID = -2205320572893228694L;

    @Id
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "NUM_CPF_REPRESENTANTE")
    private String numCpfRepresentante;

    @Id
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "NUM_BASE_CNPJ")
    private String numBaseCnpj;

    @Id
    @NotNull
    @Column(name = "DATA_INICIO_MANDATO")
    private LocalDate dataInicioMandato;

    @Column(name = "DATA_FINAL_MANDATO")
    private LocalDate dataFinalMandato;

    @NotNull
    @Column(name = "COD_TIPO_REPRESENTANTE_LEGAL")
    private Long codTipoRepresentanteLegal;

    @NotNull
    @Column(name = "IND_ORIGEM_INFORMACAO")
    private Character indOrigemInformacao;

    @JoinColumn(name = "NUM_BASE_CNPJ", referencedColumnName = "NUM_BASE_CNPJ", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PessoaJuridica pessoaJuridica;

    @Override
    public RepresentanteLegalPK getId() {
        return new RepresentanteLegalPK(numCpfRepresentante, numBaseCnpj, dataFinalMandato);
    }

    public LocalDate getDataFinalMandato() {
        return dataFinalMandato;
    }

    public void setDataFinalMandato(LocalDate dataFinalMandato) {
        this.dataFinalMandato = dataFinalMandato;
    }

    public long getCodTipoRepresentanteLegal() {
        return codTipoRepresentanteLegal;
    }

    public void setCodTipoRepresentanteLegal(Long codTipoRepresentanteLegal) {
        this.codTipoRepresentanteLegal = codTipoRepresentanteLegal;
    }

    public Character getIndOrigemInformacao() {
        return indOrigemInformacao;
    }

    public void setIndOrigemInformacao(Character indOrigemInformacao) {
        this.indOrigemInformacao = indOrigemInformacao;
    }

    public PessoaJuridica getPessoaJuridica() {
        return pessoaJuridica;
    }

    public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
        this.pessoaJuridica = pessoaJuridica;
    }

    public String getNumCpfRepresentante() {
        return numCpfRepresentante;
    }

    public void setNumCpfRepresentante(String numCpfRepresentante) {
        this.numCpfRepresentante = numCpfRepresentante;
    }

    public String getNumBaseCnpj() {
        return numBaseCnpj;
    }

    public void setNumBaseCnpj(String numBaseCnpj) {
        this.numBaseCnpj = numBaseCnpj;
    }

    public LocalDate getDataInicioMandato() {
        return dataInicioMandato;
    }

    public void setDataInicioMandato(LocalDate dataInicioMandato) {
        this.dataInicioMandato = dataInicioMandato;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RepresentanteLegal that = (RepresentanteLegal) o;
        return Objects.equals(numCpfRepresentante, that.numCpfRepresentante)
                && Objects.equals(numBaseCnpj, that.numBaseCnpj)
                && Objects.equals(dataInicioMandato, that.dataInicioMandato)
                && Objects.equals(dataFinalMandato, that.dataFinalMandato)
                && Objects.equals(codTipoRepresentanteLegal, that.codTipoRepresentanteLegal)
                && Objects.equals(indOrigemInformacao, that.indOrigemInformacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numCpfRepresentante, numBaseCnpj,
                dataInicioMandato, dataFinalMandato,
                codTipoRepresentanteLegal, indOrigemInformacao);
    }

    @Override
    public String toString() {
        return "RepresentanteLegal{"
                + "numCpfRepresentante='" + numCpfRepresentante + '\''
                + ", numBaseCnpj='" + numBaseCnpj + '\''
                + ", dataInicioMandato=" + dataInicioMandato
                + ", dataFinalMandato=" + dataFinalMandato
                + ", codTipoRepresentanteLegal=" + codTipoRepresentanteLegal
                + ", indOrigemInformacao=" + indOrigemInformacao
                + ", pessoaJuridica=" + pessoaJuridica
                + '}';
    }
}
