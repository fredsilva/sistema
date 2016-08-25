package br.gov.to.sefaz.arr.persistence.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * * Entidade referente a primary key da tabela SEFAZ_ARR.TA_DARE_DETALHE do Banco de Dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 22/06/2016 10:57:41
 */
public class DareDetalhePK implements Serializable {

    private static final long serialVersionUID = -7738675085434242107L;

    private Long idNossoNumeroDare;
    private Integer idSeqDareDetalhe;

    public DareDetalhePK() {
        // Construtor para inicialização por reflexão.
    }

    public DareDetalhePK(long idNossoNumeroDare, int idSeqDareDetalhe) {
        this.idNossoNumeroDare = idNossoNumeroDare;
        this.idSeqDareDetalhe = idSeqDareDetalhe;
    }

    public Long getIdNossoNumeroDare() {
        return idNossoNumeroDare;
    }

    public void setIdNossoNumeroDare(Long idNossoNumeroDare) {
        this.idNossoNumeroDare = idNossoNumeroDare;
    }

    public Integer getIdSeqDareDetalhe() {
        return idSeqDareDetalhe;
    }

    public void setIdSeqDareDetalhe(Integer idSeqDareDetalhe) {
        this.idSeqDareDetalhe = idSeqDareDetalhe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DareDetalhePK that = (DareDetalhePK) o;
        return Objects.equals(idNossoNumeroDare, that.idNossoNumeroDare)
                && Objects.equals(idSeqDareDetalhe, that.idSeqDareDetalhe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idNossoNumeroDare, idSeqDareDetalhe);
    }

    @Override
    public String toString() {
        return "DareDetalhePK{"
                + "idNossoNumeroDare=" + idNossoNumeroDare
                + ", idSeqDareDetalhe=" + idSeqDareDetalhe
                + '}';
    }
}
