package br.gov.to.sefaz.seg.business.authentication.service.validator;

import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.seg.business.authentication.domain.ResetPasswordDto;
import br.gov.to.sefaz.seg.business.authentication.service.impl.LoginSistemaServiceImpl;
import br.gov.to.sefaz.seg.persistence.repository.UsuarioSistemaRepository;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Valida se o e-mail e o cpf informados são do mesmo usuário do sistema.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 22/06/2016 09:55:00
 */
@Component
public class ResetPasswordEmailFromCpfValidator implements ServiceValidator<ResetPasswordDto> {

    private final UsuarioSistemaRepository repository;

    @Autowired
    public ResetPasswordEmailFromCpfValidator(UsuarioSistemaRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean support(Class<?> clazz, String context) {
        return clazz.equals(ResetPasswordDto.class) && LoginSistemaServiceImpl.RESET_PASSWORD_CONTEXT.equals(context);
    }

    @Override
    public Set<CustomViolation> validate(ResetPasswordDto target) {
        Set<CustomViolation> customViolations = new HashSet<>();

        if (!repository.existsByCpfAndEmail(target.getCpf(), target.getEmail())) {
            String message = SourceBundle.getMessage(MessageUtil.SEG, "reset.password.email.informado.error");

            customViolations.add(new CustomViolation(message));
        }

        return customViolations;
    }

}
