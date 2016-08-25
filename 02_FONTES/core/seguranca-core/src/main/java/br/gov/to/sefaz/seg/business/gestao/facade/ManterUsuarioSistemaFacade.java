package br.gov.to.sefaz.seg.business.gestao.facade;

import br.gov.to.sefaz.business.facade.CrudFacade;
import br.gov.to.sefaz.par.gestao.persistence.entity.Estado;
import br.gov.to.sefaz.par.gestao.persistence.entity.Logradouro;
import br.gov.to.sefaz.par.gestao.persistence.entity.Municipio;
import br.gov.to.sefaz.seg.business.gestao.service.filter.ManterUsuarioSistemaFilter;
import br.gov.to.sefaz.seg.persistence.domain.TipoUsuario;
import br.gov.to.sefaz.seg.persistence.entity.PostoTrabalho;
import br.gov.to.sefaz.seg.persistence.entity.UnidadeOrganizacional;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;

import java.util.Collection;

/**
 * Contrato de acesso do serviço de Cadastro de Senha.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 28/06/2016 16:08:00
 *
 */
public interface ManterUsuarioSistemaFacade extends CrudFacade<UsuarioSistema, String> {

    /**
     * Filtro da tela de UsuarioSistema.
     *
     * @param filter Objeto para ser usado no filtro.
     * @return a lista que foi encontrada através do filtro.
     */
    Collection<UsuarioSistema> find(ManterUsuarioSistemaFilter filter);

    /**
     * Reseta a senha do usuário.
     * @param usuarioSistema usuario que terá a senha resetada.
     */
    void resetPassword(UsuarioSistema usuarioSistema);

    /**
     * Método utilizado para autorizar um usuario com solicitação a utilizar o Sistema.
     * @param usuarioSistema usuário a ser autorizado.
     */
    UsuarioSistema toggleUserStatus(UsuarioSistema usuarioSistema);

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
     * Busca a lista de {@link Estado}.
     * @return lista de estados.
     */
    Collection<Estado> findAllEstados();

    /**
     * Busca a lista de {@link Municipio}.
     * @param s Código do estado.
     * @return lista de municípios referentes ao estado.
     */
    Collection<Municipio> findMunicipiosByUF(String s);

    /**
     * Busca a lista de {@link TipoUsuario}.
     * @return Lista de Tipo de Usuário.
     */
    Collection<TipoUsuario> loadTipoUsuario();

    /**
     * Atualiza o usuário.
     * @param dto Usuário a ser atualizado.
     * @return Usuário atualizado.
     */
    UsuarioSistema updateUser(UsuarioSistema dto);

    /**
     * Lista para o combo aninhado de {@link PostoTrabalho}.
     * @param identificUnidOrganizac identificação da {@link UnidadeOrganizacional} referente aos postos.
     * @return Lista de Posto Trabalho.
     */
    Collection<PostoTrabalho> findAllPostoTrabalhoByUnidadeOrganizacional(Long identificUnidOrganizac);

    /**
     * Salva um novo {@link UsuarioSistema}.
     * @param usuarioSistema Usuário a ser salvo.
     * @return Usuário salvo.
     */
    UsuarioSistema saveUsuarioSistema(UsuarioSistema usuarioSistema);

    /**
     * Busca a lista de {@link Logradouro}.
     * @return lista de logradouros.
     */
    Collection<Logradouro> findAllLogradouros();
}