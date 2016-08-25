package br.gov.to.sefaz.util.xml;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Classe util para conversao de dados.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 08/07/2016 10:00:00
 */
public class ConverterUtil {

    /**
     * Converte uma String que contém somente números em BigDecimal, conforme o número de dígitos desejados para o
     * valor decimal.
     *
     * @param value          String númerica que será utilizada para conversão
     * @param numberOfDigits número de digitos para as casa decimais
     * @return um BigDecimal conforme o valor da String e seu número de digitos para o decimal
     */
    public static BigDecimal convertBigDecimal(String value, int numberOfDigits) {
        String decimalValue = new StringBuilder(value).insert(value.length() - numberOfDigits, ".").toString();

        return new BigDecimal(decimalValue);
    }

    /**
     * Converte um valor {@link String} para um valor {@link Date}.
     *
     * @param date   dado que será convertido.
     * @param format formato da data.
     * @return valor convertido.
     * @throws ParseException lança exceção caso o formato do dado esteja diferente.
     */
    public static LocalDate convertLocalDate(String date, String format) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

        return LocalDate.parse(date, formatter);
    }

}
