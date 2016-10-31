package br.gov.to.sefaz.util.barcode;

import br.gov.to.sefaz.util.barcode.domain.OrigemGeracaoBarraEnum;
import br.gov.to.sefaz.util.barcode.domain.OrigemSistemaEnum;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Classe para manipulação, geração e calculos relacionados a codigos de barras.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 23/08/2016 16:20:00
 */
public class BarCodeHandler {

    private static final int DIVISOR = 10;
    private static final int FIELD_SIZE = 11;
    private static final String BLOCK_PATTERN = "(\\d{4})(\\d{4})(\\d{3})(\\d)";
    private static final String BLOCK_MASK = "$1 $2 $3 $4";
    private static final String ID_PRODUTO = "8";
    private static final String ID_SEGUIMENTO = "5";
    private static final String VERSAO_DARE = "5";
    private static final String CAMPOS_LIVRES = "00000000";
    private static final int VALOR_TOTAL_PAD_SIZE = 11;
    private static final char PAD_CHAR = '0';
    private static final int JULIAN_DATE_SIZE = 3;

    /**
     * Formata e adiciona o digito verificador a um campo de um codigo de barras no formato 0000 0000 000 0,
     * onde o 12º digito do campo formatado é o digito de verificação de acordo com o algoritmo de Luhn.
     *
     * @param code     codigo de barras
     * @param fieldNth indice ordinal do campo (inicia em 1)
     * @return o campo formatado em 0000 0000 000 0, incluindo o digito verificador
     * @see #getField(String, int)
     * @see #calculateLuhnCheckDigit(String)
     */
    public static String formatField(String code, int fieldNth) {
        String field = getField(code, fieldNth);
        field += calculateLuhnCheckDigit(field);

        return field.replaceAll(BLOCK_PATTERN, BLOCK_MASK);
    }

    /**
     * Retorna um campo do codigo de barras passado por parametro que estiver na ordem (iniciada em 1) passada por
     * parametro. Um campo do codigo de barras é definido a cada {@value FIELD_SIZE} digitos, ou seja dado o codigo
     * 84630000002471800790011213298216807162160806  o primeiro campo será 84630000002 o segundo 47180079001 e
     * assim por diante.
     *
     * @param code     codigo de barras
     * @param fieldNth indice ordinal do campo (inicia em 1)
     * @return um campo de {@value FIELD_SIZE} digitos do codigo de barras
     */
    public static String getField(String code, int fieldNth) {
        fieldNth -= 1;
        int beginIdx = fieldNth * FIELD_SIZE;

        return code.substring(beginIdx, beginIdx + FIELD_SIZE);
    }

    /**
     * Calcula o digito verificador de um codigo de acordo com algoritmo de luhn (luhn algorithm).
     *
     * @param code codigo para o qual o digito verificador irá ser gerado
     * @return digito verificador do codigo
     */
    public static int calculateLuhnCheckDigit(String code) {
        char[] digits = code.toCharArray();
        int sum = 0;
        int digit;

        // itera pela ordem inversa dos digitos do codigo
        // i = indice do array de digitos em ordem inversa [length, ..., 0]
        // j = indice da iteração [0, ..., length]
        for (int i = digits.length - 1, j = 1; i > -1; i--, j++) {
            digit = Character.getNumericValue(digits[i]);
            // multiplica os números em posições impares, da direita para a esquerda, por 2
            digit = digit * ((j % 2) + 1);
            // calcula o digital root do resultado. Ex: 18 = 9 (1+8), 11 = 2 (1+1), 7 = 7
            digit = (1 + (digit - 1) % 9);
            // soma os digital roots
            sum += digit;
        }

        int module = sum % DIVISOR;
        return (module == 0) ? 0 : DIVISOR - module;
    }

    /**
     * Monta o código de barras do Dare.
     *
     * @param nossoNumero        identificador nosso número do Dare.
     * @param valorTotal         valor total do Dare.
     * @param dataVencimento     data de vencimento do Dare.
     * @param origemSistema      {@link br.gov.to.sefaz.util.barcode.domain.OrigemSistemaEnum} origem do Sistema que
     *                           está solicitando a emissão do DARE(1 – SIAT ou 2-DARE-e).
     * @param origemGeracaoBarra {@link br.gov.to.sefaz.util.barcode.domain.OrigemGeracaoBarraEnum} origem da Geração
     *                           da BARRA(6 – DARE, 8 GNRE).
     * @return o código de barras do Dare.
     */
    public static String buildBarCode(String nossoNumero, BigDecimal valorTotal, LocalDate dataVencimento,
            OrigemSistemaEnum origemSistema, OrigemGeracaoBarraEnum origemGeracaoBarra) {
        String idValor = origemGeracaoBarra.getIdValor();
        String valorEfetivo = getValorEfetivo(valorTotal);
        String idOrgao = origemGeracaoBarra.getCodOrgao();
        String sistemaEmissor = origemSistema.getCodigo();
        String dataVencimentoJuliana = convertToJulianDate(dataVencimento);
        StringBuilder codBarras = new StringBuilder().append(ID_PRODUTO).append(ID_SEGUIMENTO).append(idValor)
                .append(valorEfetivo).append(idOrgao).append(VERSAO_DARE).append(sistemaEmissor)
                .append(dataVencimentoJuliana).append(nossoNumero).append(CAMPOS_LIVRES);
        int digitoVerificador = calculateLuhnCheckDigit(codBarras.toString());

        codBarras.insert(3, digitoVerificador);

        return codBarras.toString();
    }

    /**
     * Converte um valor {@link BigDecimal} em {@link String} de acordo com a regra abaixo.
     * O sistema formata o valor do total a recolher:
     * Multiplicando por 100 o valor a recolher.
     * Preenche os demais dígitos com zero a esquerda até atingir o total de 11 posições.
     *
     * @param valorTotal valor total a recolher.
     * @return valor total a recolher formatado.
     */
    private static String getValorEfetivo(BigDecimal valorTotal) {

        valorTotal = valorTotal.multiply(new BigDecimal(100)).setScale(0, RoundingMode.CEILING);

        return StringUtils.leftPad(valorTotal.toString(), VALOR_TOTAL_PAD_SIZE, PAD_CHAR);
    }

    /**
     * Converte a data para data juliana, porém sempre com o ano atual, conforme a regra abaixo.
     * O sistema calcula a Data juliana com base na Data de Vencimento e o Ano Atual, da seguinte forma:
     * Converte o Ano Atual para os últimos 2 dígitos do ano.
     * Busca a Data de vencimento converte para data juliana com 3 dígitos.
     *
     * @param dataVencimento data a ser convertida para data juliana.
     * @return data juliana.
     */
    private static String convertToJulianDate(LocalDate dataVencimento) {
        String daysOfYear = StringUtils.leftPad(String.valueOf(dataVencimento.getDayOfYear()),
                JULIAN_DATE_SIZE, PAD_CHAR);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy");
        String year = LocalDate.now().format(formatter);

        return year + daysOfYear;
    }

}
