package br.gov.to.sefaz.business.service.validation.custom;

import org.apache.commons.lang3.StringUtils;

import java.util.InputMismatchException;

/**
 * Componente para validação de CPF.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 07/06/2016 15:25:00
 */
public class CpfValidatorHandler {

    /**
     * Valida a quantidade de dígitos do cpf.
     *
     * @param cpf cpf a ser validado
     * @return ture para cpf válido, false para cpf inválido
     */
    public boolean validateLength(String cpf) {
        return StringUtils.trimToEmpty(cpf).length() == 11;
    }

    /**
     * Valida os dígitos verificdores do cpf.
     *
     * @param cpf cpf a ser validado
     * @return ture para cpf válido, false para cpf inválido
     */
    public boolean validateDigits(String cpf) {
        boolean valid = false;

        char dig10;
        char dig11;

        try {
            // Calculo do 1o. Digito Verificador
            dig10 = calculateDigit(10, 10, cpf);
            // Calculo do 2o. Digito Verificador
            dig11 = calculateDigit(11, 11, cpf);
        } catch (InputMismatchException e) {
            return valid;
        }

        // Verifica se os digitos calculados conferem com os digitos informados.
        if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10))) {
            valid = true;
        }

        return valid;
    }

    private char calculateDigit(int digito, int peso, String cpf) {
        int num;
        int sm = 0;

        for (int i = 0; i < digito - 1; i++) {
            // converte o i-esimo caractere do CPF em um numero:
            // por exemplo, transforma o caractere '0' no inteiro 0
            // (48 eh a posicao de '0' na tabela ASCII)
            num = cpf.charAt(i) - 48;
            sm = sm + (num * peso);
            peso = peso - 1;
        }

        int r = 11 - (sm % 11);
        if ((r == 10) || (r == 11)) {
            return '0';
        } else {
            // converte no respectivo caractere numerico
            return (char) (r + 48);
        }
    }
}
