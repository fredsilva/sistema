package br.gov.to.sefaz.seg.business.authentication.facade.impl;

import br.gov.to.sefaz.seg.business.authentication.domain.RoleGroupKey;
import br.gov.to.sefaz.seg.business.authentication.domain.RoleGroupType;
import br.gov.to.sefaz.seg.business.authentication.facade.DefaultTemplateFacade;
import br.gov.to.sefaz.seg.business.authentication.handler.AuthenticatedUserHandler;
import br.gov.to.sefaz.seg.business.gestao.service.CorreioContribuinteService;
import br.gov.to.sefaz.seg.business.gestao.service.ModuloSistemaService;
import br.gov.to.sefaz.seg.business.gestao.service.SmsContribuinteService;
import br.gov.to.sefaz.seg.persistence.entity.CorreioContribuinte;
import br.gov.to.sefaz.seg.persistence.entity.ModuloSistema;
import br.gov.to.sefaz.seg.persistence.entity.SmsContribuinte;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;
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
    private final CorreioContribuinteService correioContribuinteService;
    private final SmsContribuinteService smsContribuinteService;

    @Autowired
    public DefaultTemplateFacadeImpl(ModuloSistemaService moduloSistemaService,
                                     CorreioContribuinteService correioContribuinteService,
                                     SmsContribuinteService smsContribuinteService) {

        this.moduloSistemaService = moduloSistemaService;
        this.correioContribuinteService = correioContribuinteService;
        this.smsContribuinteService = smsContribuinteService;
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
    public UsuarioSistema getUsuarioSistema() {
        return AuthenticatedUserHandler.getUsuarioSistema();
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

    @Override
    public List<CorreioContribuinte> findLastSentEmailsForUser(UsuarioSistema usuarioSistema) {
        return correioContribuinteService.findLastSentEmailsForUser(usuarioSistema);
    }

    @Override
    public List<SmsContribuinte> findLastSentSMSsForUser(UsuarioSistema usuarioSistema) {
        return smsContribuinteService.findLastSentSMSsForUser(usuarioSistema);
    }

    @Override
    public int getMessagePreviewLength() {
        return correioContribuinteService.getMessagePreviewLength();
    }

    @Override
    public Collection<CorreioContribuinte> findAllSentEmailsForLoggedUser(UsuarioSistema usuarioSistema) {
        //TODO: Implamentar com o usuário quando for respondido as dúvidas junto com o cliente
        return correioContribuinteService.findAll();
    }

    @Override
    public Collection<SmsContribuinte> findAllSentSMSsForUser(UsuarioSistema usuarioSistema) {
        //TODO: Implamentar com o usuário quando for respondido as dúvidas junto com o cliente
        return smsContribuinteService.findAll();
    }
}
