package br.gov.to.sefaz.arr.parametros.business.service.filter;

/**
 * Objeto para envio de dados para filtros de busca para o Managed Bean de Tipo de Grupos Cnaes.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 13/05/2016 17:03:00
 */
public class TipoGruposCnaesFilter {

    private Integer idGrupoCnae;
    private String descricaoGrupo;
    private String cnaeFiscal;

    public Integer getIdGrupoCnae() {
        return idGrupoCnae;
    }

    public void setIdGrupoCnae(Integer idGrupoCnae) {
        this.idGrupoCnae = idGrupoCnae;
    }

    public String getDescricaoGrupo() {
        return descricaoGrupo;
    }

    public void setDescricaoGrupo(String descricaoGrupo) {
        this.descricaoGrupo = descricaoGrupo;
    }

    public String getCnaeFiscal() {
        return cnaeFiscal;
    }

    public void setCnaeFiscal(String cnaeFiscal) {
        this.cnaeFiscal = cnaeFiscal;
    }
}
