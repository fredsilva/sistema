package br.gov.to.sefaz.arr.persistence.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Entidade referente a primary key da tabela SEFAZ_ARR.TA_LOTES_PAGOS do Banco de Dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 21/06/2016 18:25:42
 */
public class LotesPagosPK implements Serializable {

    private static final long serialVersionUID = -1709211697861482120L;

    private Long idBdar;
    private Long idTpar;

    public LotesPagosPK() {
        // Construtor para inicialização por reflexão.
    }

    public LotesPagosPK(Long idBdar, Long idTpar) {
        this.idBdar = idBdar;
        this.idTpar = idTpar;
    }

    public Long getIdBdar() {
        return idBdar;
    }

    public void setIdBdar(Long idBdar) {
        this.idBdar = idBdar;
    }

    public Long getIdTpar() {
        return idTpar;
    }

    public void setIdTpar(Long idTpar) {
        this.idTpar = idTpar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LotesPagosPK that = (LotesPagosPK) o;
        return Objects.equals(idBdar, that.idBdar)
                && Objects.equals(idTpar, that.idTpar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBdar, idTpar);
    }

    @Override
    public String toString() {
        return "LotesPagosPK{"
                + "idBdar=" + idBdar
                + ", idTpar=" + idTpar
                + '}';
    }
}
