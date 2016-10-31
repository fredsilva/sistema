package br.gov.to.sefaz.seg.business.gestao.service.validator;

import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.seg.business.authentication.domain.ChangePasswordDto;
import br.gov.to.sefaz.seg.business.gestao.service.impl.UsuarioSistemaServiceImpl;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Valida a novaSenha do usuário.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 07/07/2016 09:55:00
 */
@Component
public class ChangePasswordNovaSenhaValidator implements ServiceValidator<ChangePasswordDto> {

    private static final String PASSWD_PATTERN = "(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d]{8,20}";


    @Override
    public boolean support(Class<?> clazz, String context) {
        return clazz.equals(ChangePasswordDto.class) && UsuarioSistemaServiceImpl.CHANGE_PASSWD_CONTEXT.equals(context);
    }

    @Override
    public Set<CustomViolation> validate(ChangePasswordDto target) {
        Set<CustomViolation> customViolations = new HashSet<>();

        if (!target.getNovaSenha().equals(target.getConfirmarNovaSenha())) {
            String message = SourceBundle.getMessage(MessageUtil.SEG, "change.password.confirmarSenha.validator");
            customViolations.add(new CustomViolation(message));
        }

        if (!validateNovaSenhaPattern(target.getNovaSenha())) {
            String message = SourceBundle.getMessage(MessageUtil.SEG, "change.password.novaSenha.validator");
            customViolations.add(new CustomViolation(message));
        }

        return customViolations;
    }

    private Boolean validateNovaSenhaPattern(String conteudoValores) {
        return conteudoValores.matches(PASSWD_PATTERN);
    }

}
