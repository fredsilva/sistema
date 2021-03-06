package br.gov.to.sefaz.seg.business.authentication.facade;

import br.gov.to.sefaz.seg.business.authentication.domain.RoleGroupKey;
import br.gov.to.sefaz.seg.persistence.entity.CorreioContribuinte;
import br.gov.to.sefaz.seg.persistence.entity.ModuloSistema;
import br.gov.to.sefaz.seg.persistence.entity.SmsContribuinte;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Fachada para operações do default template.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 23/06/2016 17:30:00
 */
public interface DefaultTemplateFacade {

    /**
     * Busca todos os modulos do sistema.
     * @return todos os modulos do sistema
     */
    Collection<ModuloSistema> findAllModuloSistema();

    /**
     * Retorna as roles que o usuario tem permissão de acesso.
     *
     * @return roles do usuario
     */
    Collection<String> getUserRoles();

    /**
     * Retorna o ID do procurado atual para setar o nome dele em tela.
     * @return id do procurado.
     */
    Long getActiveProcurado();

    /**
     * Verifica se o usuario possui apenas um perfil.
     *
     * @return true se o usuario possui apenas um perfil
     */
    boolean hasOnlyOneProfile();

    /**
     * Verifica se o usuario possui algum perfil ativo na sessão.
     *
     * @return true se o usuario possui algum perfil ativo na sessão
     */
    boolean hasActiveProfile();

    /**
     * Retorna o grupo de roles ativo do usuario.
     *
     * @return chave para o grupo de roles
     */
    Optional<RoleGroupKey> getActiveGroup();

    /**
     * Retorna o nome do usuario.
     *
     * @return nome do usuario
     */
    String getUserName();

    /**
     * Retorna o todos os perfis que o usuario tem permissão de acesso.
     *
     * @return perfis do usuario
     */
    List<RoleGroupKey> getPerfisSistema();

    /**
     * Retorna todos os procurados que o usuário pode atuar em nome.
     * @return procurados.
     */
    List<RoleGroupKey> getProcuradoresSistema();

    /**
     * Retorna o usuário do sistema.
     */
    UsuarioSistema getUsuarioSistema();

    /**
     * Ativa as permissões de acesso de um perfil.
     *
     * @param profileId identificação do grupo
     * @see AuthenticatedUserHandler#setActiveRoleGroup(RoleGroupType, Long)
     */
    void setActiveProfile(Long profileId);

    /**
     * Ativa as permissões de acesso de uma procuração.
     * @param procuracaoId identificação do grupo.
     * @see AuthenticatedUserHandler#setActiveRoleGroup(RoleGroupType, Long)
     */
    void setActiveProcuracao(Long procuracaoId);

    /**
     * Invalida a sessão atual.
     */
    void invalidateSession();

    /**
     * Verifica se o usuario se logou via Certificado Digital.
     *
     * @return true se o usuario está logado por certificado digital.
     */
    boolean isAuthenticatedByCert();

    /**
     * Busca os últimos emails enviados para o usuário informado. O número de emails retornados está definido em
     * regra de negócio.
     *
     * @param usuarioSistema Usuário logado
     * @return Lista contendo objetos do tipo {@link CorreioContribuinte}
     */
    List<CorreioContribuinte> findLastSentEmailsForUser(UsuarioSistema usuarioSistema);

    /**
     * Busca os últimos SMSs enviados para o usuário informado. O número de SMSs retornados está definido em regra de
     * negócio.
     *
     * @param usuarioSistema {@link UsuarioSistema}  logado
     * @return Lista contendo objetos do tipo {@link SmsContribuinte}.
     */
    List<SmsContribuinte> findLastSentSMSsForUser(UsuarioSistema usuarioSistema);

    /**
     * Obtém o tamanho máximo de caracteres a serem exibidos na pré-visualização do conteúdo da mensagem de e-mail
     * enviada aos contribuintes.
     *
     * @return Número de caracteres da mensagem de pré-visualização, conforme definidos na regra de negócio.
     */
    int getMessagePreviewLength();

    /**
     * Busca todas mensagens enviadas para o usuário informado. O número de emails retornados está
     * definido em regra de negócio.
     *
     * @param usuarioSistema {@link UsuarioSistema}  logado
     * @return Lista contendo objetos do tipo {@link CorreioContribuinte}
     */
    Collection<CorreioContribuinte> findAllSentEmailsForLoggedUser(UsuarioSistema usuarioSistema);

    /**
     * Busca todas as notificações SMSs enviadas para o usuário informado. O número de notificações retornadas está
     * definido em regra de negócio.
     *
     * @param usuarioSistema {@link UsuarioSistema} logado
     * @return Lista contendo objetos do tipo {@link SmsContribuinte}
     */
    Collection<SmsContribuinte> findAllSentSMSsForUser(UsuarioSistema usuarioSistema);
}
