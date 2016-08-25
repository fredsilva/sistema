package br.gov.to.sefaz.seg.business.authentication.facade.impl;

import br.gov.to.sefaz.seg.business.authentication.domain.RoleGroupKey;
import br.gov.to.sefaz.seg.business.authentication.domain.RoleGroupType;
import br.gov.to.sefaz.seg.business.authentication.facade.DefaultTemplateFacade;
import br.gov.to.sefaz.seg.business.authentication.handler.AuthenticatedUserHandler;
import br.gov.to.sefaz.seg.business.gestao.service.ModuloSistemaService;
import br.gov.to.sefaz.seg.persistence.entity.ModuloSistema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Implementação default de um {@link DefaultTemplateFacade}.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 23/06/2016 17:30:00
 */
@Component
public class DefaultTemplateFacadeImpl implements DefaultTemplateFacade {

    private final ModuloSistemaService moduloSistemaService;

    @Autowired
    public DefaultTemplateFacadeImpl(ModuloSistemaService moduloSistemaService) {
        this.moduloSistemaService = moduloSistemaService;
    }

    @Override
    public Collection<ModuloSistema> findAllModuloSistema() {
        return moduloSistemaService.findAll();
    }

    @Override
    public Collection<String> getUserRoles() {
        return AuthenticatedUserHandler.getUserRoles();
    }

    @Override
    public Long getActiveProcurado() {
        return AuthenticatedUserHandler.getActiveGroup().orElse(null).getId();
    }

    @Override
    public boolean hasOnlyOneProfile() {
        return AuthenticatedUserHandler.getGroupsByType(RoleGroupType.PERFIL).size() == 1;
    }

    @Override
    public boolean hasActiveProfile() {
        return AuthenticatedUserHandler.getActiveGroup().isPresent();
    }

    @Override
    public Optional<RoleGroupKey> getActiveGroup() {
        return AuthenticatedUserHandler.getActiveGroup();
    }

    @Override
    public String getUserName() {
        return AuthenticatedUserHandler.getNome();
    }

    @Override
    public List<RoleGroupKey> getPerfisSistema() {
        return AuthenticatedUserHandler.getGroupsByType(RoleGroupType.PERFIL);
    }

    @Override
    public List<RoleGroupKey> getProcuradoresSistema() {
        return AuthenticatedUserHandler.getGroupsByType(RoleGroupType.PROCURACAO);
    }

    @Override
    public void setActiveProfile(Long profileId) {
        AuthenticatedUserHandler.setActiveRoleGroup(RoleGroupType.PERFIL, profileId);
    }

    @Override
    public void setActiveProcuracao(Long procuracaoId) {
        AuthenticatedUserHandler.setActiveRoleGroup(RoleGroupType.PROCURACAO, procuracaoId);
    }

    @Override
    public void invalidateSession() {
        AuthenticatedUserHandler.invalidateSession();
    }

    @Override
    public boolean isAuthenticatedByCert() {
        return AuthenticatedUserHandler.isAuthenticatedByCert();
    }
}
