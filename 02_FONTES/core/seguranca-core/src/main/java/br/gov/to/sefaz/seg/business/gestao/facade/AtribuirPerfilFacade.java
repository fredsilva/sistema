package br.gov.to.sefaz.seg.business.gestao.facade;

import br.gov.to.sefaz.business.facade.CrudFacade;
import br.gov.to.sefaz.seg.business.gestao.service.filter.AtribuirPerfilFilter;
import br.gov.to.sefaz.seg.persistence.domain.TipoUsuario;
import br.gov.to.sefaz.seg.persistence.entity.PerfilSistema;
import br.gov.to.sefaz.seg.persistence.entity.PostoTrabalho;
import br.gov.to.sefaz.seg.persistence.entity.UnidadeOrganizacional;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;

import java.util.Collection;

/**
 * Fachada da tela de atribuição de perfil.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 02/08/2016 16:59:00
 *
 */
public interface AtribuirPerfilFacade extends CrudFacade<UsuarioSistema, String> {


    /**
     * Filtro da tela de UsuarioSistema.
     *
     * @param filter Objeto para ser usado no filtro.
     * @return a lista que foi encontrada através do filtro.
     */
    Collection<UsuarioSistema> find(AtribuirPerfilFilter filter);

    /**
     * Busca um único UsuarioSistema através do seu CPF.
     * @param cpfUsuario do usuário
     * @return {@link UsuarioSistema}
     */
    UsuarioSistema findOneUsuarioSistema(String cpfUsuario);

    /**
     * Busca lista de todas as {@link UnidadeOrganizacional}.
     * @return lista de UnidadeOrganizacional.
     */
    Collection<UnidadeOrganizacional> findAllUnidadeOrganizacional();

    /**
     * Busca lista de todos os {@link PostoTrabalho}.
     * @return lista de posto de trabalho.
     */
    Collection<PostoTrabalho> findAllPostoTrabalho();

    /**
     * Busca a lista de {@link br.gov.to.sefaz.seg.persistence.domain.TipoUsuario}.
     * @return Lista de Tipo de Usuário.
     */
    Collection<TipoUsuario> loadTipoUsuario();

    /**
     * Atualiza o usuário.
     * @param dto Usuário a ser atualizado.
     * @return Usuário atualizado.
     */
    UsuarioSistema updateUsuarioPerfil(UsuarioSistema dto);

    /**
     * Lista para o combo aninhado de {@link PostoTrabalho}.
     * @param identificUnidOrganizac identificação da {@link UnidadeOrganizacional} referente aos postos.
     * @return Lista de Posto Trabalho.
     */
    Collection<PostoTrabalho> findAllPostoTrabalhoByUnidadeOrganizacional(Long identificUnidOrganizac);

    /**
     * Busca todos os PerfilSistema cadastrados.
     * @return lista de PerfilSistema.
     */
    Collection<PerfilSistema> findAllPerfilSistema();
}