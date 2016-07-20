package br.gov.to.sefaz.seg.business.authentication.handler;

import br.gov.to.sefaz.seg.business.authentication.domain.RoleGroupKey;
import br.gov.to.sefaz.seg.business.authentication.domain.RoleGroupManager;
import br.gov.to.sefaz.seg.business.authentication.domain.RoleGroupType;
import br.gov.to.sefaz.seg.business.authentication.domain.UsuarioSistemaAuthentication;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Handler para manipulação de dados do ussuário logado authenticado na sessão atual.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 06/06/2016 09:34:00
 */
public class AuthenticatedUserHandler {

    private static UsuarioSistemaAuthentication getAuthentication() {
        if (isAuthenticated()) {
            return (UsuarioSistemaAuthentication) SecurityContextHolder.getContext().getAuthentication();
        } else {
            throw new IllegalStateException("Não tem usuário autenticado nesta sessão!");
        }
    }

    /**
     * Verifica se o usuário está autenticado.
     *
     * @return true ou false
     */
    public static boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return authentication instanceof UsuarioSistemaAuthentication
                && authentication.isAuthenticated();
    }

    /**
     * Retorna o {@link UsuarioSistema} autenticado na sessão atual.
     *
     * @return usuario autenticado
     * @throws IllegalStateException caso não tenha usuário authenticado na sessão ou não tenha uma sessão
     */
    public static UsuarioSistema getUsuarioSistema() {
        return getAuthentication().getPrincipal();
    }

    /**
     * Retorna o cpf do usuario autenticado na sessão atual.
     *
     * @return cpf do usuario autenticado
     */
    public static String getCpf() {
        return getUsuarioSistema().getCpfUsuario();
    }

    /**
     * Retorna o nome do usuario autenticado na sessão atual.
     *
     * @return nome do usuario autenticado
     * @throws IllegalStateException caso não tenha usuário authenticado na sessão ou não tenha uma sessão
     */
    public static String getNome() {
        return getUsuarioSistema().getNomeCompletoUsuario();
    }

    /**
     * Retorna as permissões de acesso do usuario autenticado na sessão.
     *
     * @return usuario autenticado
     * @throws IllegalStateException caso não tenha usuário authenticado na sessão ou não tenha uma sessão
     */
    public static Collection<? extends GrantedAuthority> getAuthorities() {
        return getAuthentication().getAuthorities();
    }

    /**
     * Ativa um grupo de permissões de acesso.
     *
     * @param type tipo do grupo de roles
     * @param id identificação do grupo
     * @see RoleGroupManager#setActiveGroup(RoleGroupType, Long)
     */
    public static void setActiveRoleGroup(RoleGroupType type, Long id) {
        getAuthentication().getRoleGroupManager().setActiveGroup(type, id);
    }

    /**
     * Filtra e retorna todos os grupos de roles do usuario a partir de um {@link RoleGroupType}.
     *
     * @param type tipo dos grupos retornados
     * @return grupos filtrados pelo type
     */
    public static List<RoleGroupKey> getGroupsByType(RoleGroupType type) {
        return getAuthentication().getRoleGroupManager().getKeys().stream()
                .filter(gk -> gk.getType() == type)
                .sorted()
                .collect(Collectors.toList());
    }

    public static Optional<RoleGroupKey> getActiveGroup() {
        return getAuthentication().getRoleGroupManager().getActiveGroup();
    }
}
