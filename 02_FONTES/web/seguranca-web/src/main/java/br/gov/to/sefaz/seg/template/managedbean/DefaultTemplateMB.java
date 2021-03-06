package br.gov.to.sefaz.seg.template.managedbean;

import br.gov.to.sefaz.presentation.managedbean.PathResolverMB;
import br.gov.to.sefaz.seg.business.authentication.domain.RoleGroupKey;
import br.gov.to.sefaz.seg.business.authentication.domain.RoleGroupType;
import br.gov.to.sefaz.seg.business.authentication.facade.DefaultTemplateFacade;
import br.gov.to.sefaz.seg.business.authentication.handler.AuthenticatedUserHandler;
import br.gov.to.sefaz.seg.persistence.entity.AplicacaoModulo;
import br.gov.to.sefaz.seg.persistence.entity.CorreioContribuinte;
import br.gov.to.sefaz.seg.persistence.entity.ModuloSistema;
import br.gov.to.sefaz.seg.persistence.entity.OpcaoAplicacao;
import br.gov.to.sefaz.seg.persistence.entity.SmsContribuinte;
import br.gov.to.sefaz.seg.template.managedbean.viewbean.MenuViewBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.IOException;
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

    private static final String REGEX_REPLACE_HTML_TAGS = "\\<.*?>";
    private static final String BLANK_STRING = " ";

    private final DefaultTemplateFacade facade;
    private final PathResolverMB pathResolver;
    private Collection<ModuloSistema> allModulos;

    @Autowired
    public DefaultTemplateMB(PathResolverMB pathResolver, DefaultTemplateFacade facade) {
        this.pathResolver = pathResolver;
        this.facade = facade;
    }

    /**
     * Monta e retorna toda a estrutura de menus do sistema que o usuario tem permissões de acessar.
     *
     * @return menus do sistema que o usuario tem permissões de acessar
     */
    public Collection<MenuViewBean> getModulosMenu() {
        Collection<ModuloSistema> allModulos = getAllModulos();

        Collection<String> userRoles = facade.getUserRoles();
        List<MenuViewBean> modulosMenu = new ArrayList<>();

        for (ModuloSistema sysModulo : allModulos) {
            MenuViewBean sysModuloMenu = createMenuViewBean(sysModulo);

            sysModulo.getAplicacaoModulos().forEach(appModulo -> {
                List<MenuViewBean> opsAplicacao = appModulo.getOpcoesAplicacao().stream()
                        .filter(oa -> userRoles.stream().anyMatch(s -> s.equals(oa.getId().toString())))
                        .map(this::createMenuViewBean)
                        .collect(Collectors.toList());

                if (!opsAplicacao.isEmpty()) {
                    sysModuloMenu.addChild(createMenuViewBean(appModulo, opsAplicacao));
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
        return facade.hasOnlyOneProfile();
    }

    /**
     * Verifica se o usuario possui algum perfil ativo na sessão.
     *
     * @return true se o usuario possui algum perfil ativo na sessão
     */
    public boolean hasActiveProfile() {
        return facade.hasActiveProfile();
    }

    /**
     * Verifica se o usuario se logou via Certificado Digital.
     *
     * @return true se o usuario está logado por certificado digital.
     */
    public boolean isAuthenticatedByCert() {
        return facade.isAuthenticatedByCert();
    }

    /**
     * Retorna o nome e o perfil ativo do usuario.
     *
     * @return formato: nome (perfil)
     */
    public String getUserName() {
        Optional<RoleGroupKey> activeGroup = facade.getActiveGroup();
        if (activeGroup.map(gk -> gk.getType() == RoleGroupType.PERFIL).orElse(false)) {
            return facade.getUserName() + " (" + activeGroup.get().getDescription() + ")";
        }

        return facade.getUserName();
    }

    /**
     * Retorna o nome e o procurado ativo do usuario.
     *
     * @return formato: nome procurado (cpf)
     */
    public String getProcuracaoUserName() {
        Optional<RoleGroupKey> activeGroup = facade.getActiveGroup();
        if (activeGroup.map(gk -> gk.getType() == RoleGroupType.PROCURACAO).orElse(false)) {
            return activeGroup.filter(roleGroupKey -> roleGroupKey.getId().equals(facade.getActiveProcurado())
                    && roleGroupKey.getType().equals(RoleGroupType.PROCURACAO))
                    .map(RoleGroupKey::getDescription).get();
        }

        return StringUtils.EMPTY;
    }

    public boolean isProcuracao() {
        return AuthenticatedUserHandler.getActiveGroup()
                .map(rgk -> rgk.getType() == RoleGroupType.PROCURACAO)
                .orElse(Boolean.FALSE);
    }

    public List<RoleGroupKey> getPerfisSistema() {
        return facade.getPerfisSistema();
    }

    public List<RoleGroupKey> getProcuradoresSistema() {
        return facade.getProcuradoresSistema();
    }

    /**
     * Ativa as permissões de acesso de um perfil.
     *
     * @param id identificação do grupo
     * @see AuthenticatedUserHandler#setActiveRoleGroup(RoleGroupType, Long)
     */
    public void setActiveProfile(Long id) throws IOException {
        facade.setActiveProfile(id);
        pathResolver.redirectToHome();
    }

    /**
     * Ativa as permissões de acesso de um procurador.
     *
     * @param id identificação do grupo
     * @see AuthenticatedUserHandler#setActiveRoleGroup(RoleGroupType, Long)
     */
    public void setActiveProcurador(Long id) throws IOException {
        facade.setActiveProcuracao(id);
        pathResolver.redirectToHome();
    }

    /**
     * Invalida a sessão atual.
     */
    public void invalidateSession() {
        facade.invalidateSession();
    }

    public String getThisAjudaContent() {
        return getActiveOpcao().map(OpcaoAplicacao::getAjudaOpcao).orElse(StringUtils.EMPTY);
    }

    public String getThisDescricaoOpcao() {
        return getActiveOpcao().map(OpcaoAplicacao::getDescripcaoOpcao).orElse(StringUtils.EMPTY);
    }

    public Optional<OpcaoAplicacao> getActiveOpcao() {
        return getAllModulos().stream()
                .map(ModuloSistema::getAplicacaoModulos).flatMap(Collection::stream)
                .map(AplicacaoModulo::getOpcoesAplicacao).flatMap(Collection::stream)
                .filter(opcao -> pathResolver.isActualProtectedView(opcao.getOpcaoUrl()))
                .findFirst();
    }

    private Collection<ModuloSistema> getAllModulos() {
        if (allModulos == null) {
            allModulos = facade.findAllModuloSistema();
        }
        return allModulos;
    }

    /**
     * Busca todos emails enviados para usuário logado.
     *
     * @return @return Lista contendo objetos do tipo
     * {@link br.gov.to.sefaz.seg.persistence.entity.CorreioContribuinte}.
     */
    public Collection<CorreioContribuinte> getAllSentEmailsForLoggedUser() {
        return facade.findAllSentEmailsForLoggedUser(facade.getUsuarioSistema());
    }

    /**
     * Busca todos SMSs enviados para usuário logado.
     *
     * @return Lista contendo objetos do tipo {@link br.gov.to.sefaz.seg.persistence.entity.SmsContribuinte}.
     */
    public Collection<SmsContribuinte> getAllSentSMSsForLoggedUser() {
        return facade.findAllSentSMSsForUser(facade.getUsuarioSistema());
    }

    /**
     * Busca últimos emails enviados para usuário logado. O número de emails retornados está definido em regra de
     * negócio.
     *
     * @return @return Lista contendo objetos do tipo
     * {@link br.gov.to.sefaz.seg.persistence.entity.CorreioContribuinte}
     */
    public List<CorreioContribuinte> getLastSentEmailsForLoggedUser() {
        return facade.findLastSentEmailsForUser(facade.getUsuarioSistema());
    }

    /**
     * Obtém o preview do conteúdo do e-mail enviado para o usuário logado. O preview do conteúdo terá um número
     * máximo de caracteres, conforme definido em regra de negócio. Além disso, as tags html serão removidas do
     * conteúdo.
     *
     * @param correioContribuinte Item de correio enviado para o contribuinte.
     * @return Texto plano, sem formatação html e limitado ao número de caracteres definidos na regra de negócio.
     */
    public String getSentEmailContentPreview(CorreioContribuinte correioContribuinte) {
        String conteudo = StringUtils.trimToEmpty(correioContribuinte.getConteudo());
        return StringUtils.abbreviate(conteudo.replaceAll(REGEX_REPLACE_HTML_TAGS, BLANK_STRING),
                facade.getMessagePreviewLength());
    }

    /**
     * Busca últimos SMSs enviados para usuário logado. O número de SMSs retornados está definido em regra de negócio.
     *
     * @return Lista contendo objetos do tipo {@link br.gov.to.sefaz.seg.persistence.entity.SmsContribuinte}
     */
    public List<SmsContribuinte> getLastSentSMSsForLoggedUser() {
        return facade.findLastSentSMSsForUser(facade.getUsuarioSistema());
    }

    private MenuViewBean createMenuViewBean(ModuloSistema sysModulo) {
        return new MenuViewBean(sysModulo.getDescricaoModuloSistema(), sysModulo.getId());
    }

    private MenuViewBean createMenuViewBean(AplicacaoModulo appModulo, List<MenuViewBean> opsAplicacao) {
        return new MenuViewBean(appModulo.getDescricaoAplicacaoModulo(), appModulo.getId(), opsAplicacao);
    }

    private MenuViewBean createMenuViewBean(OpcaoAplicacao opcaoAplicacao) {
        return new MenuViewBean(opcaoAplicacao.getDescripcaoOpcao(), opcaoAplicacao.getId(),
                opcaoAplicacao.getOpcaoUrl());
    }
}
