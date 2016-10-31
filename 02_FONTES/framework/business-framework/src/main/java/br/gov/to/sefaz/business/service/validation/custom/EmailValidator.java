package br.gov.to.sefaz.business.service.validation.custom;

import java.util.Objects;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Componente de validação de Email.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 07/06/2016 13:31:12
 */
class EmailValidator implements ConstraintValidator<Email, Object> {

    @Override
    public void initialize(Email arg0) {
        // Este validator não tem inicialização.
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintContext) {
        return Objects.isNull(object) || Pattern.matches("([A-Za-z0-9\\._-]+@[A-Za-z0-9\\._-]+\\.[A-Za-z]{2,4})|",
                object.toString());
    }

}
