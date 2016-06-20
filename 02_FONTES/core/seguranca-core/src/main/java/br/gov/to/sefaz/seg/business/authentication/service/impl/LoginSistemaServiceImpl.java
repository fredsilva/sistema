package br.gov.to.sefaz.seg.business.authentication.service.impl;

import br.gov.to.sefaz.business.service.validation.CustomValidationException;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.exception.BusinessException;
import br.gov.to.sefaz.seg.business.authentication.domain.LoginDto;
import br.gov.to.sefaz.seg.business.authentication.domain.ResetPasswordDto;
import br.gov.to.sefaz.seg.business.authentication.domain.UsuarioSistemaAuthentication;
import br.gov.to.sefaz.seg.business.authentication.factory.AuthenticationFactory;
import br.gov.to.sefaz.seg.business.authentication.handler.LdapHandler;
import br.gov.to.sefaz.seg.business.authentication.provider.LdapProvider;
import br.gov.to.sefaz.seg.business.authentication.service.LoginSistemaService;
import br.gov.to.sefaz.seg.business.authentication.service.PasswordMaxTriesException;
import br.gov.to.sefaz.seg.business.authentication.service.WrongPasswordTriesService;
import br.gov.to.sefaz.seg.business.gestao.service.HistoricoLoginSistemaService;
import br.gov.to.sefaz.seg.business.gestao.service.UsuarioSistemaService;
import br.gov.to.sefaz.seg.exception.SecurityException;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;
import br.gov.to.sefaz.util.mail.MailSenderService;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * Implementação do serviço de Login do Sistema.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 14/05/2016 13:33:38
 */
@Service
public class LoginSistemaServiceImpl implements LoginSistemaService {

    private final UsuarioSistemaService usuarioSistemaService;
    private final MailSenderService mailSenderService;
    private final LdapProvider ldapProvider;
    private final WrongPasswordTriesService triesService;
    private final HistoricoLoginSistemaService historicoLoginSistemaService;
    private final AuthenticationFactory authenticationFactory;

    @Autowired
    public LoginSistemaServiceImpl(
            UsuarioSistemaService usuarioSistemaService, LdapProvider ldapProvider,
            MailSenderService mailSenderService, WrongPasswordTriesService triesService,
            HistoricoLoginSistemaService historicoLoginSistemaService, AuthenticationFactory authenticationFactory) {
        this.usuarioSistemaService = usuarioSistemaService;
        this.mailSenderService = mailSenderService;
        this.ldapProvider = ldapProvider;
        this.triesService = triesService;
        this.historicoLoginSistemaService = historicoLoginSistemaService;
        this.authenticationFactory = authenticationFactory;
    }

    @Override
    @SuppressWarnings("PMD")
    public UsuarioSistemaAuthentication authenticate(LoginDto dto) throws SecurityException {
        validate(dto);
        UsuarioSistema usuarioSistema = getUserIfUnblocked(dto.getCpf());

        try {
            ldapProvider.authenticate(dto.getCpf(), dto.getPasswd());
        } catch (SecurityException e) {
            throw registerUserFailure(e, dto.getCpf());
        }

        return authenticateAndRegister(usuarioSistema, dto.getPasswd());
    }

    @Override
    public UsuarioSistemaAuthentication certAuthenticate(String username) throws SecurityException {
        UsuarioSistema usuarioSistema = getUserIfUnblocked(username);
        String emptyPassword = StringUtils.EMPTY;

        UsuarioSistemaAuthentication authentication = authenticateAndRegister(usuarioSistema, emptyPassword);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return authentication;
    }

    @Override
    public void resetPassword(ResetPasswordDto dto) {
        validate(dto);
        UsuarioSistema usuarioSistema = getUserIfUnblocked(dto.getCpf());
        validateEmailUsuario(dto, usuarioSistema);
        String novaSenha = LdapHandler.getRandomPassword(8);

        try {
            ldapProvider.resetPassword(dto.getCpf(), novaSenha);
            mailSenderService.sendMail(SourceBundle.getMessage(MessageUtil.SEG, "reset.password.email.subject"),
                    getMailBodyResetPasswdMessage(usuarioSistema, novaSenha), true, dto.getEmail());
        } catch (SecurityException e) {
            String message = SourceBundle.getMessage(MessageUtil.SEG, "reset.password.ldap.error");
            throw new BusinessException(message, e);
        } catch (MessagingException e) {
            String message = SourceBundle.getMessage(MessageUtil.SEG, "reset.password.enviar.email");
            throw new BusinessException(message, e);
        }

        usuarioSistema.setAlterarSenhaProximoAcesso(Boolean.TRUE);
        usuarioSistemaService.save(usuarioSistema);
    }

    private UsuarioSistemaAuthentication authenticateAndRegister(UsuarioSistema usuarioSistema, String password) {
        triesService.clearTries(usuarioSistema.getCpfUsuario());
        historicoLoginSistemaService.saveHistoricoLoginSistema(usuarioSistema);
        return authenticationFactory.create(password, usuarioSistema);
    }

    /**
     * Busca o ususario sistema no banco de dados e se possivel desbloqueia ele em caso de a data e hora de desbloqueio
     * ser menor do que a data e hora atual. Após retornar o usuuario sistema, executa todas as valodações para realizar
     * o login.
     *
     * @param username cpf do ususario
     * @return o ususario sistema já atualizado
     */
    private UsuarioSistema getUserIfUnblocked(String username) {
        UsuarioSistema usuarioSistema = usuarioSistemaService.findOne(username);

        if (Objects.isNull(usuarioSistema)) {
            throw new BusinessException(SourceBundle.getMessage(MessageUtil.SEG, "login.cpf.inexixtente"));
        }

        usuarioSistemaService.validate(usuarioSistema);

        if (usuarioSistema.getEstaBloqueado()
                && usuarioSistema.getDataDesbloqueio().isBefore(LocalDateTime.now())) {
            usuarioSistemaService.unblockUser(usuarioSistema.getCpfUsuario());
            usuarioSistema.setEstaBloqueado(false);
            usuarioSistema.setDataDesbloqueio(null);
        }

        return usuarioSistema;
    }

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

    private void validateEmailUsuario(ResetPasswordDto dto, UsuarioSistema usuario) throws BusinessException {
        if (!dto.getEmail().equals(usuario.getCorreioEletronico())) {
            String message = SourceBundle.getMessage(MessageUtil.SEG, "reset.password.email.informado.error");
            throw new BusinessException(message);
        }
    }

    private void validate(Object dto) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<CustomViolation> customViolations = validator.validate(dto).stream()
                .map(constraintViolation -> new CustomViolation(
                        SourceBundle.getMessageByExpression(constraintViolation.getMessage())))
                .collect(Collectors.toSet());

        if (!Objects.isNull(customViolations) && !customViolations.isEmpty()) {
            throw new CustomValidationException(customViolations);
        }
    }

    @SuppressWarnings("PMD")
    private String getMailBodyResetPasswdMessage(UsuarioSistema usuario, String newPassword) {
        StringBuilder emailBody = new StringBuilder();
        emailBody.append(SourceBundle.getMessage(MessageUtil.SEG, "reset.password.email.body.title",
                usuario.getNomeCompletoUsuario()))
                .append("<br /><br />")
                .append(SourceBundle.getMessage(MessageUtil.SEG, "reset.password.email.body.description", newPassword))
                .append("<br />")
                .append(SourceBundle.getMessage(MessageUtil.SEG, "reset.password.email.body.ass"));
        return emailBody.toString();
    }

}