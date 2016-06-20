package br.gov.to.sefaz.seg.template.managedbean;

import br.gov.to.sefaz.seg.business.authentication.domain.RoleGroupKey;
import br.gov.to.sefaz.seg.business.authentication.domain.RoleGroupType;
import br.gov.to.sefaz.seg.business.authentication.handler.AuthenticatedUserHandler;
import br.gov.to.sefaz.seg.business.gestao.facade.ModuloSistemaFacade;
import br.gov.to.sefaz.seg.persistence.entity.ModuloSistema;
import br.gov.to.sefaz.seg.template.managedbean.viewbean.MenuViewBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.faces.bean.ManagedBean;

/**
 * Managed Bean responsavel pelas funcionalidades básicas da tela.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 13/06/2016 10:21:00
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "session")
@ManagedBean(name = "defaultTemplateMB")
public class DefaultTemplateMB {

    private final ModuloSistemaFacade moduloSistemaFacade;
    private Collection<ModuloSistema> allModulos;

    @Autowired
    public DefaultTemplateMB(ModuloSistemaFacade moduloSistemaFacade) {
        this.moduloSistemaFacade = moduloSistemaFacade;
    }

    /**
     * Monta e retorna toda a estrutura de menus do sistema que o usuario tem permissões de acessar.
     *
     * @return menus do sistema que o usuario tem permissões de acessar
     */
    public Collection<MenuViewBean> getModulosMenu() {
        if (allModulos == null) {
            allModulos = this.moduloSistemaFacade.findAll();
        }

        List<MenuViewBean> modulosMenu = new ArrayList<>();
        for (ModuloSistema sysModulo : allModulos) {
            MenuViewBean sysModuloMenu = new MenuViewBean(sysModulo.getDescricaoModuloSistema(), sysModulo.getId());

            sysModulo.getAplicacaoModulos().forEach(appModulo -> {
                List<MenuViewBean> opsAplicacao = appModulo.getOpcoesAplicacao().stream()
                        .filter(oa -> AuthenticatedUserHandler.getAuthorities().stream()
                                .anyMatch(o -> o.getAuthority().equals("ROLE_" + oa.getId())))
                        .map(oa -> new MenuViewBean(oa.getDescripcaoOpcao(), oa.getId(), oa.getOpcaoUrl()))
                        .collect(Collectors.toList());

                if (!opsAplicacao.isEmpty()) {
                    sysModuloMenu.addChild(
                            new MenuViewBean(appModulo.getDescricaoAplicacaoModulo(), appModulo.getId(), opsAplicacao));
                }
            });

            if (!sysModuloMenu.getChilds().isEmpty()) {
                modulosMenu.add(sysModuloMenu);
            }
        }

        Collections.sort(modulosMenu);

        return modulosMenu;
    }

    /**
     * Verifica se o usuario possui apenas um perfil.
     *
     * @return true se o usuario possui apenas um perfil
     */
    public boolean hasOnlyOneProfile() {
        return AuthenticatedUserHandler.getGroupsByType(RoleGroupType.PERFIL).size() == 1;
    }

    /**
     * Verifica se o usuario possui algum perfil ativo na sessão.
     *
     * @return true se o usuario possui algum perfil ativo na sessão
     */
    public boolean hasActiveProfile() {
        return AuthenticatedUserHandler.getActiveGroup().isPresent();
    }

    /**
     * Retorna o nome e o perfil ativo do usuario.
     *
     * @return formato: nome (perfil)
     */
    public String getUserName() {
        Optional<RoleGroupKey> activeGroup = AuthenticatedUserHandler.getActiveGroup();
        if (activeGroup.isPresent()) {
            return AuthenticatedUserHandler.getNome() + " (" + activeGroup.get().getDescription() + ")";
        }

        return AuthenticatedUserHandler.getNome();
    }

    public List<RoleGroupKey> getPerfisSistema() {
        return AuthenticatedUserHandler.getGroupsByType(RoleGroupType.PERFIL);
    }

    /**
     * Ativa as permissões de acesso de um perfil.
     *
     * @see AuthenticatedUserHandler#setActiveRoleGroup(RoleGroupType, Long)
     * @param id identificação do grupo
     */
    public void setActiveProfile(Long id) {
        AuthenticatedUserHandler.setActiveRoleGroup(RoleGroupType.PERFIL, id);
    }
}