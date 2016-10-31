package br.gov.to.sefaz.seg.business.gestao.service;

import br.gov.to.sefaz.business.service.CrudService;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioPerfil;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;

import java.util.Collection;
import java.util.Set;

/**
 * Contrato de acesso do serviço de Perfis de Usuários.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 04/07/2016 16:37:00
 */
public interface UsuarioPerfilService extends CrudService<UsuarioPerfil, Long> {

    /**
     * Monta a String dos nomes de perfis do usuário, separados por vírgula.
     * @param allUsuarioSistema lista de {@link UsuarioSistema} que serão montado os perfis.
     */
    void buildProfileString(Collection<UsuarioSistema> allUsuarioSistema);

    /**
     * Busca todos os perfis pelo CPF do usuário.
     * @param cpfUsuario cpf do usuário.
     * @return Lista de {@link UsuarioPerfil}
     */
    Collection<UsuarioPerfil> getAllUsuarioPerfilByCpf(String cpfUsuario);

    /**
     * Remove todos os PerfilPapel com o Id do Perfil.
     * @param id identificação do Perfil.
     */
    void deleteAllWithPerfilId(Long id);

    /**
     * Busca todos os {@link br.gov.to.sefaz.seg.persistence.entity.UsuarioPerfil} pelo ID do perfil.
     * @param identificacaoPerfil identificação do {@link br.gov.to.sefaz.seg.persistence.entity.PerfilSistema}.
     * @return Lista de UsuarioPerfil.
     */
    Collection<UsuarioPerfil> findAllUsuariosPerfilByPerfilId(Long identificacaoPerfil);

    /**
     * Busca todos os perfis do usuário.
     * @param cpfUsuario cpf do usuário.
     * @return lista de {@link UsuarioPerfil}.
     */
    Set<UsuarioPerfil> findAllPerfilById(String cpfUsuario);

    /**
     * Remove todos os {@link UsuarioPerfil} do usuário.
     * @param cpfUsuario cpf do usuário.
     */
    void deleteUsuarioPerfilByUsuario(String cpfUsuario);

    /**
     * Atualiza {@link UsuarioPerfil} de acordo com o CPF do usuário.
     * @param usuarioPerfil lista de perfis do Usuário.
     * @param cpfUsuario cpf do usuário.
     */
    void updateAtribuirUsuarioPerfil(Set<UsuarioPerfil> usuarioPerfil, String cpfUsuario);
}
