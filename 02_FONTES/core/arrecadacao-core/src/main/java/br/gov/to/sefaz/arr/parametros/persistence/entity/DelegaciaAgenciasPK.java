package br.gov.to.sefaz.arr.parametros.persistence.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Chave composta da entidade {@link DelegaciaAgencias}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 20/05/2016 15:18:05
 */
public class DelegaciaAgenciasPK implements Serializable {

    private static final long serialVersionUID = -5646160577319112969L;

    private Integer idUnidadeDelegacia;
    private Integer idDelegacia;

    public DelegaciaAgenciasPK() {
        // Construtor para inicialização por reflexão.
    }

    public DelegaciaAgenciasPK(Integer idUnidadeDelegacia, Integer idDelegacia) {
        this.idUnidadeDelegacia = idUnidadeDelegacia;
        this.idDelegacia = idDelegacia;
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

    public void setIdDelegacia(Integer idDelegacia) {
        this.idDelegacia = idDelegacia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DelegaciaAgenciasPK that = (DelegaciaAgenciasPK) o;
        return Objects.equals(idUnidadeDelegacia, that.idUnidadeDelegacia)
                && Objects.equals(idDelegacia, that.idDelegacia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUnidadeDelegacia, idDelegacia);
    }

    @Override
    public String toString() {
        return "DelegaciaAgenciasPK{"
                + "idUnidadeDelegacia=" + idUnidadeDelegacia
                + ", idDelegacia=" + idDelegacia
                + '}';
    }
}
