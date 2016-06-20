package br.gov.to.sefaz.business.service.validation.custom;

import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormat;
import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Constraint Validator de CNPJ. Valida se quantidade de caracteres está correta e se os dígitos verificadores estão ok.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 25/05/2016 08:30:14
 */
public class CnpjValidator implements ConstraintValidator<Cnpj, Object> {

    @Override
    public void initialize(Cnpj arg0) {
        // Este validator não tem inicialização.
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintContext) {
        boolean isvalid = validateEmpty(object);
        if (!isvalid) {
            return true;
        }

        String cnpj;
        if (object instanceof Long) {
            try {
                cnpj = getString((Long) object);
            } catch (NumberFormatException e) {
                return false;
            }
        } else if (object instanceof String) {
            cnpj = (String) object;
        } else {
            return false;
        }

        CnpjValidatorHandler handler = new CnpjValidatorHandler();
        return isvalid && handler.validateLength(cnpj) && handler.validateDigits(cnpj);
    }

    private boolean validateEmpty(Object object) {
        boolean valid = true;
        if (object instanceof String) {
            if (Objects.isNull(object) || StringUtils.isEmpty(object.toString())) {
                valid = false;
            }
        } else if (Objects.isNull(object)) {
            valid = false;
        }
        return valid;
    }

    private String getString(Long cnpj) {
        DecimalFormat df = new DecimalFormat("00000000000000");
        return df.format(cnpj);
    }

}
