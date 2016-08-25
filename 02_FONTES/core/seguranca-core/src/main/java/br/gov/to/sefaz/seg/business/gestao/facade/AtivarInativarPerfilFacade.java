package br.gov.to.sefaz.seg.business.gestao.facade;

import br.gov.to.sefaz.business.facade.CrudFacade;
import br.gov.to.sefaz.seg.business.gestao.service.filter.AtivarInativarPerfilFilter;
import br.gov.to.sefaz.seg.persistence.entity.PostoTrabalho;
import br.gov.to.sefaz.seg.persistence.entity.UnidadeOrganizacional;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioPerfil;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;

import java.util.Collection;
import java.util.List;

/**
 * Contrato de acesso do serviço de Bancos.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 13/06/2016 11:33:00
 */
public interface AtivarInativarPerfilFacade extends CrudFacade<UsuarioPerfil, Long> {

    /**
     * Filtro da tela de AtivarInativarPerfil.
     * @param filter Objeto para ser usado no filtro.
     * @return a lista que foi encontrada através do filtro.
     */
    Collection<UsuarioSistema> find(AtivarInativarPerfilFilter filter);

    /**
     * Busca todos os postos de trabalho para a combo da view.
     * @return lista de {@link PostoTrabalho}
     */
    Collection<PostoTrabalho> findAllPostoTrabalho();

    /**
     * Busca todas as {@link UnidadeOrganizacional} para a combo da view.
     * @return lista de {@link UnidadeOrganizacional}
     */
    Collection<UnidadeOrganizacional> findAllUnidadeOrganizacional();

    /**
     * Busca todos os perfis do usuário.
     * @param usuarioSistema usuário.
     * @return Lista de {@link UsuarioPerfil}
     */
    Collection<UsuarioPerfil> loadAllUsuarioPerfil(UsuarioSistema usuarioSistema);

    /**
     * Monta a String dos nomes de perfis do usuário, separados por vírgula.
     * @param usuarioSistemas lista de {@link UsuarioSistema} que serão montado os perfis.
     */
    void buildProfileString(Collection<UsuarioSistema> usuarioSistemas);

    /**
     * Atualiza a lista de perfis, alterando seus status de acordo com as opções que o usuário escolheu em tela.
     * @param usuarioPerfilList lista de {@link UsuarioPerfil}.
     */
    void updatePerfilUsuarioList(Collection<UsuarioPerfil> usuarioPerfilList);

    /**
     * Busca todos os {@link UsuarioSistema} para montar a tabela.
     * @param filter filtro passado em tela.
     * @return lista de {@link UsuarioSistema}.
     */
    List<UsuarioSistema> findAllUsuarioSistema(AtivarInativarPerfilFilter filter);

    /**
     * Lista para o combo aninhado de {@link PostoTrabalho}.
     * @param identificUnidOrganizac identificação da {@link UnidadeOrganizacional} referente aos postos.
     * @return Lista de Posto Trabalho.
     */
    Collection<PostoTrabalho> findAllPostoTrabalhoByUnidadeOrganizacional(Long identificUnidOrganizac);
}

