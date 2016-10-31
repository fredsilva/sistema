package br.gov.to.sefaz.seg.business.authentication.domain;

import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * POJO com informações referentes a um usuário sistema authenticado.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 08/06/2016 10:27:00
 */
public class UsuarioSistemaAuthentication implements Authentication {

    private static final long serialVersionUID = -8261933602513622817L;

    private final String password;
    private final String ip;
    private final RoleGroupManager roleGroupManager;
    private final UsuarioSistema usuarioSistema;
    private boolean authenticated;
    private boolean authenticatedByCert;

    public UsuarioSistemaAuthentication(String password, String ip, RoleGroupManager roleGroupManager,
            UsuarioSistema usuarioSistema) {
        this.password = password;
        this.ip = ip;
        this.usuarioSistema = usuarioSistema;
        this.authenticated = true;
        this.authenticatedByCert = false;
        this.roleGroupManager = roleGroupManager;
    }

    /**
     * {@inheritDoc}
     * Retorna as roles de acesso do usuario, com o prefixo "ROLE_". Para pegar sem o prefixo pegue direto
     * do {@link #getRoleGroupManager()}.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roleGroupManager.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toSet());
    }

    public RoleGroupManager getRoleGroupManager() {
        return roleGroupManager;
    }

    @Override
    public String getCredentials() {
        return password;
    }

    @Override
    public String getDetails() {
        return ip;
    }

    @Override
    public UsuarioSistema getPrincipal() {
        return usuarioSistema;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return getPrincipal().getCpfUsuario();
    }

    public boolean isAuthenticatedByCert() {
        return authenticatedByCert;
    }

    public void setAuthenticatedByCert(boolean authenticatedByCert) {
        this.authenticatedByCert = authenticatedByCert;
    }
}