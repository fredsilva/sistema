package br.gov.to.sefaz.arr.parametros.persistence.entity;

import br.gov.to.sefaz.persistence.entity.AbstractEntity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Entidade que representa a SEFAZ_ARR.TA_GRUPOS_CNAE.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 14/05/2016 12:59:00
 */
@Entity
@Table(name = "TA_GRUPOS_CNAE", schema = "SEFAZ_ARR")
@IdClass(GruposCnaePK.class)
public class GruposCnae extends AbstractEntity<GruposCnaePK> {

    private static final long serialVersionUID = 3164246548952042294L;

    @Id
    @NotNull(message = "#{arr_msg['parametros.gruposCnae.idGrupoCnae.obrigatorio']}")
    @Column(name = "ID_GRUPO_CNAE")
    private Integer idGrupoCnae;

    @Id
    @NotNull(message = "#{arr_msg['parametros.gruposCnae.cnaeFiscal.obrigatorio']}")
    @Column(name = "CNAE_FISCAL")
    private String cnaeFiscal;

    public GruposCnae() {
        // Construtor para inicialização por reflexão.
    }

    public GruposCnae(
            Integer idGrupoCnae, String cnaeFiscal) {
        this.idGrupoCnae = idGrupoCnae;
        this.cnaeFiscal = cnaeFiscal;
    }

    @Override
    public GruposCnaePK getId() {
        return new GruposCnaePK(getIdGrupoCnae(), getCnaeFiscal());
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
        GruposCnae that = (GruposCnae) o;
        return Objects.equals(idGrupoCnae, that.idGrupoCnae)
                && Objects.equals(cnaeFiscal, that.cnaeFiscal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idGrupoCnae, cnaeFiscal);
    }
}
