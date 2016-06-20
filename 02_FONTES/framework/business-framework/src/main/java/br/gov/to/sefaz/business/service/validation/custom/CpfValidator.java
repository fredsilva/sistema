package br.gov.to.sefaz.business.service.validation.custom;

import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormat;
import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Componente de validação de CPF.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 07/06/2016 13:31:12
 */
@SuppressWarnings("PMD")
public class CpfValidator implements ConstraintValidator<Cpf, Object> {

    @Override
    public void initialize(Cpf arg0) {
        // Este validator não tem inicialização.
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintContext) {

        if (Objects.isNull(object) || !validateLength(object.toString())) {
            return true;
        }

        String cpf;
        if (object instanceof Long) {
            try {
                cpf = getString((Long) object);
            } catch (NumberFormatException e) {
                return false;
            }
        } else if (object instanceof String) {
            cpf = (String) object;
        } else {
            return false;
        }

        CpfValidatorHandler handler = new CpfValidatorHandler();
        return handler.validateDigits(cpf);
    }

    private String getString(Long cnpj) {
        DecimalFormat df = new DecimalFormat("00000000000");
        return df.format(cnpj);
    }

    private boolean validateLength(String cpf) {
        boolean valid = true;

        if (StringUtils.isEmpty(cpf) || cpf.length() != 11) {
            valid = false;
        }

        return valid;
    }

}
