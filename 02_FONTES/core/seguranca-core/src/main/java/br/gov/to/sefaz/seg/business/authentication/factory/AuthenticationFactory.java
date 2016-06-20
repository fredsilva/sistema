package br.gov.to.sefaz.seg.business.authentication.factory;

import br.gov.to.sefaz.seg.business.authentication.domain.RoleGroupKey;
import br.gov.to.sefaz.seg.business.authentication.domain.RoleGroupManager;
import br.gov.to.sefaz.seg.business.authentication.domain.RoleGroupType;
import br.gov.to.sefaz.seg.business.authentication.domain.UsuarioSistemaAuthentication;
import br.gov.to.sefaz.seg.persistence.entity.PerfilPapel;
import br.gov.to.sefaz.seg.persistence.entity.PerfilSistema;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioPerfil;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;
import br.gov.to.sefaz.seg.persistence.repository.UsuarioPerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Factory para criação de usuário sistema authenticado.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 08/06/2016 10:54:00
 */
@Component
public class AuthenticationFactory {

    private final UsuarioPerfilRepository usuarioPerfilRepository;

    @Autowired
    public AuthenticationFactory(UsuarioPerfilRepository usuarioPerfilRepository) {
        this.usuarioPerfilRepository = usuarioPerfilRepository;
    }

    /**
     * Cria um usuário sistema autenticado.
     *
     * @param password       senha do usuário
     * @param usuarioSistema entidade com os detalhes do usuário
     * @return dados refentes ao usuario autenticado e a própria autenticação
     * @see br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema
     * @see org.springframework.security.core.Authentication
     */
    public UsuarioSistemaAuthentication create(String password, UsuarioSistema usuarioSistema) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        String requestIp = attr.getRequest().getRemoteAddr();
        RoleGroupManager roleGroupManager = new RoleGroupManager();

        Set<UsuarioPerfil> usuarioPerfis = usuarioPerfilRepository
                .findAllByUsuarioSistema(usuarioSistema.getCpfUsuario());
        List<PerfilSistema> perfis = usuarioPerfis.stream()
                .flatMap(up -> up.getPerfisSistema().stream()).collect(Collectors.toList());
        fillRoleGroupManager(roleGroupManager, perfis);

        return new UsuarioSistemaAuthentication(password, requestIp, roleGroupManager, usuarioSistema);
    }

    private void fillRoleGroupManager(RoleGroupManager roleGroupManager, List<PerfilSistema> perfis) {
        Map<RoleGroupKey, List<String>> groups = perfis.stream().collect(Collectors.toMap(
                this::createRoleGroupKey, this::extractRoles));

        groups.entrySet()
                .forEach(g -> roleGroupManager.addRoles(g.getKey(), g.getValue()));

        if (perfis.size() == 1) {
            roleGroupManager.setActiveGroup(RoleGroupType.PERFIL, perfis.get(0).getId());
        }
    }

    private RoleGroupKey createRoleGroupKey(PerfilSistema perfilSistema) {
        return new RoleGroupKey(RoleGroupType.PERFIL, perfilSistema.getId(),
                perfilSistema.getDescricaoPerfil());
    }

    private  List<String> extractRoles(PerfilSistema perfilSistema) {
        return perfilSistema.getPerfilPapel().stream()
                .map(PerfilPapel::getPapelSistema)
                .flatMap(papelSistema -> papelSistema.getPapelOpcao().stream())
                .map(papelOpcao -> papelOpcao.getOpcaoAplicacao().getId().toString())
                .collect(Collectors.toList());
    }
}