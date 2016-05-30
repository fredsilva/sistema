package br.gov.to.sefaz.business.service.validation.custom;

import java.text.DecimalFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Constraint Validator de CNPJ. Valida se quantidade de caracteres está correta e se os dígitos verificadores estão ok.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 25/05/2016 08:30:14
 */
@SuppressWarnings("PMD")
public class CnpjValidator implements ConstraintValidator<Cnpj, Long> {

    @Override
    public void initialize(Cnpj arg0) {
        // Este validator não tem inicialização.
    }

    @Override
    public boolean isValid(Long object, ConstraintValidatorContext constraintContext) {
        boolean isvalid = validateEmpty(object);
        try {
            return isvalid && validateLength(object) && validateDigits(object);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean validateEmpty(Long object) {
        if (object == null) {
            return false;
        } else {
            return true;
        }
    }

    private boolean validateLength(Long object) {
        if (object.toString().length() > 14) {
            return false;
        } else {
            return true;
        }
    }

    private boolean validateDigits(Long object) {

        DecimalFormat df = new DecimalFormat("00000000000000");
        String cnpj = df.format(object);

        int soma = 0;

        if (Long.valueOf(cnpj.trim()).equals(Long.valueOf("0"))) {
            return false;
        }

        char[] charCnpj = cnpj.toCharArray();

        /* Primeira parte */
        for (int i = 0; i < 4; i++) {
            if (charCnpj[i] - 48 >= 0 && charCnpj[i] - 48 <= 9) {
                soma += (charCnpj[i] - 48) * (6 - (i + 1));
            }
        }

        for (int i = 0; i < 8; i++) {
            if (charCnpj[i + 4] - 48 >= 0 && charCnpj[i + 4] - 48 <= 9) {
                soma += (charCnpj[i + 4] - 48) * (10 - (i + 1));
            }
        }

        int dig = 11 - (soma % 11);
        String cnpjCalc = cnpj.substring(0, 12);
        cnpjCalc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);

        /* Segunda parte */
        soma = 0;
        for (int i = 0; i < 5; i++) {
            if (charCnpj[i] - 48 >= 0 && charCnpj[i] - 48 <= 9) {
                soma += (charCnpj[i] - 48) * (7 - (i + 1));
            }
        }

        for (int i = 0; i < 8; i++) {
            if (charCnpj[i + 5] - 48 >= 0 && charCnpj[i + 5] - 48 <= 9) {
                soma += (charCnpj[i + 5] - 48) * (10 - (i + 1));
            }
        }

        dig = 11 - (soma % 11);
        cnpjCalc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);
        return cnpj.equals(cnpjCalc.trim());
    }

}
