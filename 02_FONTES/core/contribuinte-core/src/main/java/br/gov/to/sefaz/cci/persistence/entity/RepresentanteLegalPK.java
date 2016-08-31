package br.gov.to.sefaz.cci.persistence.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * PK da entidade {@link br.gov.to.sefaz.cci.persistence.entity.RepresentanteLegal}.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 30/08/2016 17:44:00
 */
public class RepresentanteLegalPK implements Serializable {

    private static final long serialVersionUID = -5369170656622572544L;

    private String numCpfRepresentante;

    private String numBaseCnpj;

    private LocalDate dataInicioMandato;

    public RepresentanteLegalPK() {
        // Construtor para inicialização por reflexão.
    }

    public RepresentanteLegalPK(String numCpfRepresentante, String numBaseCnpj, LocalDate dataInicioMandato) {
        this.numCpfRepresentante = numCpfRepresentante;
        this.numBaseCnpj = numBaseCnpj;
        this.dataInicioMandato = dataInicioMandato;
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
        RepresentanteLegalPK that = (RepresentanteLegalPK) o;
        return Objects.equals(numCpfRepresentante, that.numCpfRepresentante)
                && Objects.equals(numBaseCnpj, that.numBaseCnpj)
                && Objects.equals(dataInicioMandato, that.dataInicioMandato);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numCpfRepresentante, numBaseCnpj, dataInicioMandato);
    }

    @Override
    public String toString() {
        return "RepresentanteLegalPK{"
                + "numCpfRepresentante='" + numCpfRepresentante + '\''
                + ", numBaseCnpj='" + numBaseCnpj + '\''
                + ", dataInicioMandato=" + dataInicioMandato
                + '}';
    }
}
