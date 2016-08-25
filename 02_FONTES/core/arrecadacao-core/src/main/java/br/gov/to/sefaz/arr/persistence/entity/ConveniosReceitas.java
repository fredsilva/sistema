package br.gov.to.sefaz.arr.persistence.entity;

import br.gov.to.sefaz.persistence.entity.AbstractEntity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * Entidade que representa a SEFAZ_ARR.TA_RECEITAS_CONVENIOS.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 19/05/2016 18:34:00
 */
@Entity
@Table(name = "TA_RECEITAS_CONVENIOS", schema = "SEFAZ_ARR")
@IdClass(ConveniosReceitasPK.class)
public class ConveniosReceitas extends AbstractEntity<ConveniosReceitasPK> {

    private static final long serialVersionUID = 5980656593423572154L;

    @Id
    @NotNull(message = "#{arr_msg['parametros.receitas.idReceita.obrigatorio']}")
    @Max(value = 9999, message = "#{arr_msg['parametros.receitas.idReceita.maximo']}")
    @Column(name = "ID_RECEITA")
    private Integer idReceita;

    @Id
    @NotNull(message = "#{arr_msg['parametros.conveniosArrec.idConvenio.obrigatorio']}")
    @Max(value = 9999999999L, message = "#{arr_msg['parametros.conveniosArrec.idConvenio.maximo']}")
    @Column(name = "ID_CONVENIO", nullable = false)
    private Long idConvenio;

    public ConveniosReceitas() {
        // Construtor para inicialização por reflexão.
    }

    public ConveniosReceitas(Integer idReceita, Long idConvenio) {
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
        ConveniosReceitas that = (ConveniosReceitas) o;
        return Objects.equals(idReceita, that.idReceita)
                && Objects.equals(idConvenio, that.idConvenio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idReceita, idConvenio);
    }

    @Override
    public String toString() {
        return "ConveniosReceitas{"
                + "idReceita=" + idReceita
                + ", idConvenio=" + idConvenio
                + '}';
    }

    @Override
    public ConveniosReceitasPK getId() {
        return new ConveniosReceitasPK(idReceita, idConvenio);
    }
}
