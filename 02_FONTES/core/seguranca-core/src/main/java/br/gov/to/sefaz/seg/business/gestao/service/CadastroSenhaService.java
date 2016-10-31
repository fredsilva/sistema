package br.gov.to.sefaz.seg.business.gestao.service;

import br.gov.to.sefaz.business.service.CrudService;
import br.gov.to.sefaz.par.gestao.persistence.entity.Logradouro;
import br.gov.to.sefaz.seg.business.gestao.facade.CadastroSenhaFacade;
import br.gov.to.sefaz.seg.business.gestao.service.filter.CadastroSenhaFilter;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;

import java.util.Collection;

/**
 * Contrato de acesso do serviço de {@link CadastroSenhaFacade}.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 13/06/2016 11:33:00
 */
public interface CadastroSenhaService extends CrudService<UsuarioSistema, String> {

    /**
     * Busca todos os {@link UsuarioSistema} cadastrados.
     *
     * @param filter filtros da busca
     * @return lista de UnidadeOrganizacional ativos
     */
    Collection<UsuarioSistema> findAll(CadastroSenhaFilter filter);

    /**
     * Reseta a senha do usuário.
     * @param usuarioSistema usuário que terá a senha resetada.
     */
    void resetPassword(UsuarioSistema usuarioSistema);

    /**
     * Método utilizado para autorizar um usuario com solicitação a utilizar o Sistema.
     * @param usuarioSistema usuário a ser autorizado.
     */
    void authorizeUser(UsuarioSistema usuarioSistema);

    /**
     * Busca um único UsuarioSistema através do seu CPF.
     * @param cpfUsuario do usuário
     * @return {@link UsuarioSistema}
     */
    UsuarioSistema findOneUsuarioSistema(String cpfUsuario);

    /**
     * Busca todos os {@link Logradouro}.
     * @return lista de Logradouros.
     */
    Collection<Logradouro> findAllLogradouros();
}
