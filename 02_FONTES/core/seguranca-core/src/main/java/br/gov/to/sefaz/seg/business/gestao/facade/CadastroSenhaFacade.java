package br.gov.to.sefaz.seg.business.gestao.facade;

import br.gov.to.sefaz.business.facade.CrudFacade;
import br.gov.to.sefaz.par.gestao.persistence.entity.Logradouro;
import br.gov.to.sefaz.seg.business.gestao.service.filter.CadastroSenhaFilter;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;

import java.util.Collection;

/**
 * Contrato de acesso do serviço de Cadastro de Senha.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 28/06/2016 16:08:00
 *
 */
public interface CadastroSenhaFacade extends CrudFacade<UsuarioSistema, String> {

    /**
     * Filtro da tela de UsuarioSistema.
     *
     * @param filter Objeto para ser usado no filtro.
     * @return a lista que foi encontrada através do filtro.
     */
    Collection<UsuarioSistema> find(CadastroSenhaFilter filter);

    /**
     * Reseta a senha do usuário.
     * @param usuarioSistema usuario que terá a senha resetada.
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