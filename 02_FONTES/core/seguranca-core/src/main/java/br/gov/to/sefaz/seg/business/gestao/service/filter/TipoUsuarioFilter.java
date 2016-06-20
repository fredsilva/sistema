package br.gov.to.sefaz.seg.business.gestao.service.filter;

/**
 * Filtro para a classe {@link br.gov.to.sefaz.seg.persistence.entity.UnidadeOrganizacional}.
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 08/06/2016 17:40:00
 */
public class TipoUsuarioFilter {

    private String descricaoTipoUsuario;

    public String getDescricaoTipoUsuario() {
        return descricaoTipoUsuario;
    }

    public void setDescricaoTipoUsuario(String descricaoTipoUsuario) {
        this.descricaoTipoUsuario = descricaoTipoUsuario;
    }
}
