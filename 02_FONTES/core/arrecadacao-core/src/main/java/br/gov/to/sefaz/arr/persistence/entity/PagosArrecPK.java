package br.gov.to.sefaz.arr.persistence.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Entidade referente a primary key da tabela SEFAZ_ARR.TA_PAGOS_ARREC do Banco de Dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 21/06/2016 18:25:42
 */
public class PagosArrecPK implements Serializable {

    private static final long serialVersionUID = -5135316521570842408L;

    private Long idBdarTpar;
    private Integer ordemLote;

    public PagosArrecPK() {
        // Construtor para inicialização por reflexão.
    }

    public PagosArrecPK(Long idBdarTpar, Integer ordemLote) {
        this.idBdarTpar = idBdarTpar;
        this.ordemLote = ordemLote;
    }

    public Long getIdBdarTpar() {
        return idBdarTpar;
    }

    public void setIdBdarTpar(Long idBdarTpar) {
        this.idBdarTpar = idBdarTpar;
    }

    public Integer getOrdemLote() {
        return ordemLote;
    }

    public void setOrdemLote(Integer ordemLote) {
        this.ordemLote = ordemLote;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PagosArrecPK that = (PagosArrecPK) o;
        return Objects.equals(idBdarTpar, that.idBdarTpar)
                && Objects.equals(ordemLote, that.ordemLote);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBdarTpar, ordemLote);
    }

    @Override
    public String toString() {
        return "PagosArrecPK{"
                + "idBdarTpar=" + idBdarTpar
                + ", ordemLote=" + ordemLote
                + '}';
    }
}
