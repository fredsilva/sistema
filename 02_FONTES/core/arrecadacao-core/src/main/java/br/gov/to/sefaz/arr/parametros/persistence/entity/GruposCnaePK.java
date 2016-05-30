package br.gov.to.sefaz.arr.parametros.persistence.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Chave composta da entidade {@link GruposCnae}.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 14/05/2016 13:35:00
 */
public class GruposCnaePK implements Serializable {

    private static final long serialVersionUID = 2294092059184210355L;

    private Integer idGrupoCnae;
    private String cnaeFiscal;

    public GruposCnaePK() {
        // Construtor para inicialização por reflexão.
    }

    public GruposCnaePK(
            Integer idGrupoCnae, String cnaeFiscal) {
        this.idGrupoCnae = idGrupoCnae;
        this.cnaeFiscal = cnaeFiscal;
    }

    public Integer getIdGrupoCnae() {
        return idGrupoCnae;
    }

    public void setIdGrupoCnae(Integer idGrupoCnae) {
        this.idGrupoCnae = idGrupoCnae;
    }

    public String getCnaeFiscal() {
        return cnaeFiscal;
    }

    public void setCnaeFiscal(String cnaeFiscal) {
        this.cnaeFiscal = cnaeFiscal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GruposCnaePK that = (GruposCnaePK) o;
        return Objects.equals(idGrupoCnae, that.idGrupoCnae)
                && Objects.equals(cnaeFiscal, that.cnaeFiscal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idGrupoCnae, cnaeFiscal);
    }

    @Override
    public String toString() {
        return "GruposCnaePK{"
                + "idGrupoCnae=" + idGrupoCnae
                + ", cnaeFiscal=" + cnaeFiscal
                + '}';
    }
}
