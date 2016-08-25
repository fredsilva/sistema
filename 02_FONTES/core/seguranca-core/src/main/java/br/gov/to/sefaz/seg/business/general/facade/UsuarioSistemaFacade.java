package br.gov.to.sefaz.seg.business.general.facade;

import br.gov.to.sefaz.business.facade.CrudFacade;
import br.gov.to.sefaz.cat.persistence.entity.Estado;
import br.gov.to.sefaz.cat.persistence.entity.Municipio;
import br.gov.to.sefaz.seg.business.general.service.filter.UsuarioSistemaFilter;
import br.gov.to.sefaz.seg.persistence.entity.TipoUsuario;
import br.gov.to.sefaz.seg.persistence.entity.UnidadeOrganizacional;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;

import java.util.Collection;
import java.util.List;

/**
 * Contrato de acesso do serviço de UsuarioSistema.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 13/06/2016 11:33:00
 */
public interface UsuarioSistemaFacade extends CrudFacade<UsuarioSistema, String> {

    /**
     * Filtro da tela de UsuarioSistema.
     *
     * @param filter Objeto para ser usado no filtro.
     * @return a lista que foi encontrada através do filtro.
     */
    List<UsuarioSistema> find(UsuarioSistemaFilter filter);

    /**
     * Busca lista de todos os Tipos de Usuários.
     *
     * @return lista de TipoUsuario.
     */
    Collection<TipoUsuario> findAllTipoUsuario();

    /**
     * Busca lista de todos os estados cadastrados.
     *
     * @return lista de Estados.
     */
    Collection<Estado> findAllEstados();

    /**
     * Retorna uma lista de {@link Municipio} de um determinado {@link Estado#unidadeFederacao}.
     *
     * @param uf federativa do estado, exemplo TO
     * @return uma lista com os municípios do estado
     */
    Collection<Municipio> findMunicipiosByUF(String uf);

    /**
     * Busca a unidade Organizacional do Usuario Sistema.
     *
     * @return {@link UsuarioSistema}
     */
    UnidadeOrganizacional findUnidadeOrganizacional();
}