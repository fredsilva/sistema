package br.gov.to.sefaz.seg.business.authentication.service.impl;

import br.gov.to.sefaz.business.service.validation.ValidationSuite;
import br.gov.to.sefaz.exception.BusinessException;
import br.gov.to.sefaz.seg.business.authentication.domain.LoginDto;
import br.gov.to.sefaz.seg.business.authentication.domain.ResetPasswordDto;
import br.gov.to.sefaz.seg.business.authentication.domain.SecurityErrorCodeType;
import br.gov.to.sefaz.seg.business.authentication.domain.UsuarioSistemaAuthentication;
import br.gov.to.sefaz.seg.business.authentication.factory.AuthenticationFactory;
import br.gov.to.sefaz.seg.business.authentication.provider.LdapProvider;
import br.gov.to.sefaz.seg.business.authentication.service.LoginSistemaService;
import br.gov.to.sefaz.seg.business.authentication.service.PasswordMaxTriesException;
import br.gov.to.sefaz.seg.business.authentication.service.SecurityException;
import br.gov.to.sefaz.seg.business.authentication.service.WrongPasswordTriesService;
import br.gov.to.sefaz.seg.business.gestao.service.HistoricoLoginSistemaService;
import br.gov.to.sefaz.seg.business.gestao.service.UsuarioSistemaService;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Implementação do serviço de Login do Sistema.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 14/05/2016 13:33:38
 */
@Service
public class LoginSistemaServiceImpl implements LoginSistemaService {

    public static final String RESET_PASSWORD_CONTEXT = "RESET_PASSWORD";

    private final UsuarioSistemaService usuarioSistemaService;
    private final LdapProvider ldapProvider;
    private final WrongPasswordTriesService triesService;
    private final HistoricoLoginSistemaService historicoLoginSistemaService;
    private final AuthenticationFactory authenticationFactory;

    @Autowired
    public LoginSistemaServiceImpl(
            UsuarioSistemaService usuarioSistemaService, LdapProvider ldapProvider,
            WrongPasswordTriesService triesService, HistoricoLoginSistemaService historicoLoginSistemaService,
            AuthenticationFactory authenticationFactory) {
        this.usuarioSistemaService = usuarioSistemaService;
        this.ldapProvider = ldapProvider;
        this.triesService = triesService;
        this.historicoLoginSistemaService = historicoLoginSistemaService;
        this.authenticationFactory = authenticationFactory;
    }

    @Override
    public UsuarioSistemaAuthentication authenticate(@ValidationSuite LoginDto dto) throws SecurityException {
        UsuarioSistema usuarioSistema = getUserToLogin(dto.getCpf());

        try {
            ldapProvider.authenticate(dto.getCpf(), dto.getPasswd());
        } catch (SecurityException e) {
            if (!Objects.isNull(e.getErrorCode()) && SecurityErrorCodeType.AUTENTICATION.equals(e.getErrorCode())) {
                throw registerUserFailure(e, dto.getCpf());
            } else {
                throw e;
            }
        }

        return authenticateAndRegister(usuarioSistema, dto.getPasswd());
    }

    @Override
    public UsuarioSistemaAuthentication certAuthenticate(String username) throws SecurityException {
        UsuarioSistema usuarioSistema = getUserToLogin(username);
        String emptyPassword = StringUtils.EMPTY;
        UsuarioSistemaAuthentication auth = authenticateAndRegister(usuarioSistema, emptyPassword);
        auth.setAuthenticatedByCert(true);
        return authenticateAndRegister(usuarioSistema, emptyPassword);
    }

    @Override
    public void resetPassword(@ValidationSuite(context = RESET_PASSWORD_CONTEXT) ResetPasswordDto dto) {
        UsuarioSistema usuarioSistema = getUserToLogin(dto.getCpf());
        usuarioSistemaService.resetPassword(usuarioSistema);
    }

    /**
     * Realiza o login do ususario e registra o no historico de logins.
     *
     * @param usuarioSistema ususario que sera authenticado na sessão
     * @param password       senha do usuario
     * @return o dados do usuario que foram colocados na sessão
     * @see HistoricoLoginSistemaService#saveHistoricoLoginSistema(UsuarioSistema)
     */
    private UsuarioSistemaAuthentication authenticateAndRegister(UsuarioSistema usuarioSistema, String password) {
        triesService.clearTries(usuarioSistema.getCpfUsuario());
        historicoLoginSistemaService.saveHistoricoLoginSistema(usuarioSistema);
        UsuarioSistemaAuthentication authentication = authenticationFactory.create(password, usuarioSistema);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return authentication;
    }

    /**
     * Busca o usuario sistema, e realiza todos os tratamentos de permissão de login do ususario.
     * Se possivel desbloqueia ele em caso de a data e hora de desbloqueio ser menor do que a data e hora atual.
     * Após retornar o usuuario sistema, executa todas as valodações para realizar o login.
     * Antes de retornar realiza as validações de {@link UsuarioSistema} de contexto de login.
     *
     * @param username cpf do ususario
     * @return o ususario sistema já atualizado
     * @see UsuarioSistemaService#validateLogin(UsuarioSistema)
     */
    private UsuarioSistema getUserToLogin(String username) {
        UsuarioSistema usuarioSistema = usuarioSistemaService.findOne(username);

        if (Objects.isNull(usuarioSistema)) {
            throw new BusinessException(SourceBundle.getMessage(MessageUtil.SEG, "login.cpf.inexixtente"));
        }

        if (usuarioSistema.getEstaBloqueado()
                && usuarioSistema.getDataDesbloqueio().isBefore(LocalDateTime.now())) {
            usuarioSistemaService.unblockUser(usuarioSistema.getCpfUsuario());
            usuarioSistema.setEstaBloqueado(false);
            usuarioSistema.setDataDesbloqueio(null);
        }

        usuarioSistemaService.validateLogin(usuarioSistema);

        return usuarioSistema;
    }

    /**
     * Registra a falha de authenticação do usuario, a cada {@value WrongPasswordTriesService#MAX_TRIES} bloqueia o
     * usuario na base de dados.
     *
     * @param e   exceção gerada pela falha de autenticação
     * @param cpf cpf do usuario que tentou se autenticar
     * @return Exceção com uma mensagem legivel sobre a tentativa de acesso mal sucedida.
     */
    private BusinessException registerUserFailure(SecurityException e, String cpf) {
        String message;
        try {
            triesService.incrementTries(cpf);
            message = SourceBundle.getMessage(MessageUtil.SEG, "login.cpf.senha.invalidos");
        } catch (PasswordMaxTriesException e1) {
            usuarioSistemaService.blockUser(cpf);
            triesService.clearTries(cpf);
            message = SourceBundle.getMessage(MessageUtil.SEG, "login.usuario.bloqueado");
        }

        throw new BusinessException(message, e);
    }

}