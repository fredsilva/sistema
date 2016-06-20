package br.gov.to.sefaz.business.service.validation.custom;

import org.apache.commons.lang3.StringUtils;

import java.util.InputMismatchException;

/**
 * Componente de validação de CNPJ.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 07/06/2016 13:30:15
 */
@SuppressWarnings("PMD")
public class CnpjValidatorHandler {

    /**
     * Valida a quantidade de dígitos do cnpj.
     *
     * @param cnpj cnpj a ser validado
     * @return ture para cnpj válido, false para cnpj inválido
     */
    public boolean validateLength(String cnpj) {
        boolean valid = true;

        if (!StringUtils.isEmpty(cnpj) && cnpj.length() != 14) {
            valid = false;
        }

        return valid;
    }

    /**
     * Valida os dígitos verificdores do cnpj.
     *
     * @param cnpj cnpj a ser validado
     * @return ture para cnpj válido, false para cnpj inválido
     */
    public boolean validateDigits(String cnpj) {
        boolean valid = false;

        char dig13;
        char dig14;

        try {
            // Calculo do 1o. Digito Verificador
            dig13 = calculateDigit(13, 2, cnpj);
            // Calculo do 2o. Digito Verificador
            dig14 = calculateDigit(14, 2, cnpj);
        } catch (InputMismatchException e) {
            return valid;
        }

        // Verifica se os digitos calculados conferem com os digitos informados.
        if ((dig13 == cnpj.charAt(12)) && (dig14 == cnpj.charAt(13))) {
            valid = true;
        }

        return valid;
    }

    private char calculateDigit(int digito, int peso, String cnpj) {
        int num;
        int sm = 0;

        peso = 2;
        for (int i = digito - 2; i >= 0; i--) {
            // converte o i-ésimo caractere do CNPJ em um número:
            // por exemplo, transforma o caractere '0' no inteiro 0
            // (48 eh a posição de '0' na tabela ASCII)
            num = cnpj.charAt(i) - 48;
            sm = sm + (num * peso);
            peso = peso + 1;
            if (peso == 10) {
                peso = 2;
            }
        }

        int r = sm % 11;
        if ((r == 0) || (r == 1)) {
            return '0';
        } else {
            return (char) ((11 - r) + 48);
        }
    }

}
