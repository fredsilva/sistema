package br.gov.to.sefaz.arr.parametros.persistence.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * * Chave composta da entidade {@link br.gov.to.sefaz.arr.parametros.persistence.entity.ConveniosReceitas}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 19/05/2016 18:40:00
 */
public class ConveniosReceitasPK implements Serializable {

    private static final long serialVersionUID = 5732902648291054035L;

    private Integer idReceita;
    private Long idConvenio;

    public ConveniosReceitasPK() {
        // Construtor para inicialização por reflexão.
    }

    public ConveniosReceitasPK(Integer idReceita, Long idConvenio) {
        this.idReceita = idReceita;
        this.idConvenio = idConvenio;
    }

    public Integer getIdReceita() {
        return idReceita;
    }

    public void setIdReceita(Integer idReceita) {
        this.idReceita = idReceita;
    }

    public Long getIdConvenio() {
        return idConvenio;
    }

    public void setIdConvenio(Long idConvenio) {
        this.idConvenio = idConvenio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ConveniosReceitasPK that = (ConveniosReceitasPK) o;
        return Objects.equals(idReceita, that.idReceita)
                && Objects.equals(idConvenio, that.idConvenio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idReceita, idConvenio);
    }

    @Override
    public String toString() {
        return "ConveniosReceitasPK{"
                + "idReceita=" + idReceita
                + ", idConvenio=" + idConvenio
                + '}';
    }
}
