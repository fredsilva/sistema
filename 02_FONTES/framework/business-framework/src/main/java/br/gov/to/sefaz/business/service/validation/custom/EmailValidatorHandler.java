package br.gov.to.sefaz.business.service.validation.custom;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Componente para validação de um endereço de email.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 24/10/2016 14:32:00
 */
public class EmailValidatorHandler {

    /**
     * Verifica se um email é válido.
     *
     * @param email endereço de email a ser validado
     * @return true caso esteja de acordo com as regras definidas a um endereço de email.
     */
    public static boolean isValid(String email) {
        return Objects.isNull(email)
                || Pattern.matches("([A-Za-z0-9\\._-]+@[A-Za-z0-9\\._-]+\\.[A-Za-z]{2,4})|", email);
    }
}
