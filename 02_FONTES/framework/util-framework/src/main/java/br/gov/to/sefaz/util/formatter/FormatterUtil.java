package br.gov.to.sefaz.util.formatter;

import br.gov.to.sefaz.util.application.ApplicationUtil;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Classe util para realização de formatação de valores.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 01/08/2016 14:09:00
 */
public class FormatterUtil {

    public static final String DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm";

    public static final String DATE_FORMAT = "dd/MM/yyyy";

    /**
     * Formata uma data em dd/mm/aaaa.
     *
     * @param date data a ser formatada
     * @return data formatada
     */
    public static String formatDate(LocalDate date) {
        return formatDate(DATE_FORMAT, date);
    }

    /**
     * Formata uma data em um formato desejado.
     *
     * @param format Formato da data desejado
     * @param date data a ser formatada
     * @return data formatada
     */
    public static String formatDate(String format, LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return date.format(formatter);
    }

    /**
     * Formata uma data hora em dd/mm/aaaa hh:mm.
     *
     * @param date data hora a ser formatada
     * @return data hora formatada
     */
    public static String formatDateTime(LocalDateTime date) {
        return formatDateTime(DATE_TIME_FORMAT, date);
    }

    /**
     * Formata um data hora  de acordo com o formato desejado.
     *
     * @param format Formato da data hora  desejado
     * @param date data hora a ser formatada
     * @return data hora  formatada
     */
    public static String formatDateTime(String format, LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return date.format(formatter);
    }

    /**
     * Formata o cep.
     *
     * @param cep CEP não formatado, sem máscara
     * @return CEP formatado, com máscara
     */
    public static String formatCep(String cep) {
        return cep.replaceAll("(\\d{5})(\\d{3})", "$1-$2");
    }

    /**
     * Formata um cpf.
     *
     * @param cpf CPF não formatado, sem máscara
     * @return CPF formatado, com máscara
     */
    public static String formatCpf(String cpf) {
        return cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }

    /**
     * Formata um cnpj.
     *
     * @param cnpj CNPJ não formatado, sem máscara
     * @return CNPJ formatado, com máscara
     */
    public static String formatCnpj(String cnpj) {
        return cnpj.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
    }

    /**
     * Converte um número em uma String formatada.
     * Se o número tiver decimais, formata para sempre mostrar os dígitos.
     *
     * @param value valor do número a ser formatado
     * @return Valor formatado
     */
    public static String formatNumber(Number value) {
        NumberFormat numberFormat = DecimalFormat.getInstance(ApplicationUtil.LOCALE);

        if (value instanceof Double || value instanceof Float || value instanceof BigDecimal) {
            numberFormat.setMinimumFractionDigits(2);
        }

        return numberFormat.format(value);
    }

}
