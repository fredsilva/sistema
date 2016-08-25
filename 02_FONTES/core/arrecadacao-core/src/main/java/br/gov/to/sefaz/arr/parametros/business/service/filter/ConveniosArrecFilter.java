package br.gov.to.sefaz.arr.parametros.business.service.filter;

import br.gov.to.sefaz.arr.persistence.enums.TipoCodigoBarraEnum;
import br.gov.to.sefaz.arr.persistence.enums.TipoConvenioEnum;

import java.util.Objects;

/**
 * POJO para representar os campos de filtro para pesquisa de
 * {@link br.gov.to.sefaz.arr.persistence.entity.ConveniosArrec} na base de dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 16/05/2016 11:32:00
 */
public class ConveniosArrecFilter {

    private Long idConvenio;
    private String descricaoConvenio;
    private TipoCodigoBarraEnum tipoBarra;
    private TipoConvenioEnum tipoConvenio;
    private Integer idBanco;

    public Long getIdConvenio() {
        return idConvenio;
    }

    public void setIdConvenio(Long idConvenio) {
        this.idConvenio = idConvenio;
    }

    public String getDescricaoConvenio() {
        return descricaoConvenio;
    }

    public void setDescricaoConvenio(String descricaoConvenio) {
        this.descricaoConvenio = descricaoConvenio;
    }

    public TipoCodigoBarraEnum getTipoBarra() {
        return tipoBarra;
    }

    public void setTipoBarra(TipoCodigoBarraEnum tipoBarra) {
        this.tipoBarra = tipoBarra;
    }

    public TipoConvenioEnum getTipoConvenio() {
        return tipoConvenio;
    }

    public void setTipoConvenio(TipoConvenioEnum tipoConvenio) {
        this.tipoConvenio = tipoConvenio;
    }

    public Integer getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(Integer idBanco) {
        this.idBanco = idBanco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ConveniosArrecFilter that = (ConveniosArrecFilter) o;
        return Objects.equals(idConvenio, that.idConvenio)
                && Objects.equals(descricaoConvenio, that.descricaoConvenio)
                && tipoBarra == that.tipoBarra
                && tipoConvenio == that.tipoConvenio
                && Objects.equals(idBanco, that.idBanco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idConvenio, descricaoConvenio, tipoBarra, tipoConvenio, idBanco);
    }

    @Override
    public String toString() {
        return "ConveniosArrecFilter{"
                + "idConvenio=" + idConvenio
                + ", descricaoConvenio='" + descricaoConvenio + '\''
                + ", tipoBarra=" + tipoBarra
                + ", tipoConvenio=" + tipoConvenio
                + ", idBanco=" + idBanco
                + '}';
    }
}
