package br.gov.to.sefaz.arr.persistence.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Entidade referente a primary key da tabela SEFAZ_ARR.TA_RECEITAS_TAXAS do Banco de Dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 19/05/2016 09:57:47
 */
public class ReceitasTaxasPK implements Serializable {

    private static final long serialVersionUID = 1085177943792974534L;

    private Integer idSubcodigo;
    private Integer idReceita;

    public ReceitasTaxasPK() {
        // Construtor para inicialização por reflexão.
    }

    public ReceitasTaxasPK(Integer idSubcodigo, Integer idReceita) {
        this.idSubcodigo = idSubcodigo;
        this.idReceita = idReceita;
    }

    public Integer getIdSubcodigo() {
        return idSubcodigo;
    }

    public void setIdSubcodigo(Integer idSubcodigo) {
        this.idSubcodigo = idSubcodigo;
    }

    public Integer getIdReceita() {
        return idReceita;
    }

    public void setIdReceita(Integer idReceita) {
        this.idReceita = idReceita;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReceitasTaxasPK that = (ReceitasTaxasPK) o;
        return Objects.equals(idSubcodigo, that.idSubcodigo)
                && Objects.equals(idReceita, that.idReceita);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSubcodigo, idReceita);
    }

    @Override
    public String toString() {
        return "ReceitasTaxasPK{"
                + "idSubcodigo=" + idSubcodigo
                + ", idReceita=" + idReceita
                + '}';
    }
}
