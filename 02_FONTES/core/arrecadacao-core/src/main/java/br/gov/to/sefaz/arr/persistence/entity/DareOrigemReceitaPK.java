package br.gov.to.sefaz.arr.persistence.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Chave composta da entidade {@link DareOrigemReceita}.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 28/09/2016 15:43:00
 */
public class DareOrigemReceitaPK implements Serializable {

    private static final long serialVersionUID = 4471341100193916161L;

    private Integer idReceita;
    private Integer idOrigemDebito;

    public DareOrigemReceitaPK() {
        //Construtor para inicialização por reflexão.
    }

    public DareOrigemReceitaPK(Integer idReceita, Integer idOrigemDebito) {
        this.idReceita = idReceita;
        this.idOrigemDebito = idOrigemDebito;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DareOrigemReceitaPK that = (DareOrigemReceitaPK) o;
        return Objects.equals(idReceita, that.idReceita)
                && Objects.equals(idOrigemDebito, that.idOrigemDebito);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idReceita, idOrigemDebito);
    }

    @Override
    public String toString() {
        return "DareOrigemReceitaPK{"
                + "idReceita=" + idReceita
                + ", idOrigemDebito=" + idOrigemDebito
                + '}';
    }
}