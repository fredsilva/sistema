package br.gov.to.sefaz.arr.persistence.entity;

import br.gov.to.sefaz.persistence.entity.AbstractEntity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Entidade que representa os dados da tabela SEFAZ_ARR.TA_LOTES_PAGOS.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 21/06/2016 18:25:42
 */
@Entity
@Table(name = "TA_LOTES_PAGOS", schema = "SEFAZ_ARR")
@IdClass(LotesPagosPK.class)
public class LotesPagos extends AbstractEntity<LotesPagosPK> {

    private static final long serialVersionUID = -3068327493480132489L;

    @Id
    @NotNull
    @Column(name = "ID_BDDAR")
    private Long idBdar;

    @Id
    @NotNull
    @Column(name = "ID_TPAR")
    private Long idTpar;

    public LotesPagos() {
        // Construtor para inicialização por reflexão.
    }

    public LotesPagos(Long idBdar, Long idTpar) {
        this.idBdar = idBdar;
        this.idTpar = idTpar;
    }

    @Override
    public LotesPagosPK getId() {
        return new LotesPagosPK();
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
        LotesPagos that = (LotesPagos) o;
        return Objects.equals(idBdar, that.idBdar)
                && Objects.equals(idTpar, that.idTpar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBdar, idTpar);
    }

    @Override
    public String toString() {
        return "LotesPagos{"
                + "idBdar=" + idBdar
                + ", idTpar=" + idTpar
                + '}';
    }
}
