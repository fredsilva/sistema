package br.gov.to.sefaz.util.pdf.handler;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * <p>Implementação de um {@link ExpressionReplaceHandler} para resolução da expressão logica {@value EXP_NAME}.
 * Consiste na exibição ou não do texto corpo da expressão dependendo se o parametro da expressão passado existe no
 * mapa de parametros de contexto retorna true. O valor do parametro pode ser negado iniciando o parametro da expressão
 * com {@value NEGATION_SIGN}.</p>
 * {@code
 * Ex 1:
 * #{if:paramName}
 *      corpo da expressão ${any}
 * #{:if}
 * }
 * <p>Se existir um parâmetro com o nome "paramName" no mapa de parametros de contexto e ele retornar true
 * (do tipo {@link Boolean}) o texto "     corpo da expressão ${any}" será tratado e retornado, caso contrario será
 * retornado uma {@link String} vazia.</p>
 * {@code
 * Ex 2:
 * #{if:!paramName}
 *      corpo da expressão
 * #{:if}
 * }
 * <p>Se NÃO existir um parâmetro com o nome "paramName" no mapa de parametros de contexto OU ele NÃO retornar true
 * (do tipo {@link Boolean}) o texto "     corpo da expressão ${any}" será tratado e retornado, caso contrario será
 * retornado uma {@link String} vazia.</p>
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 18/08/2016 16:30:00
 */
public class IfExpressionReplaceHandler implements ExpressionReplaceHandler {

    private static final String EXP_NAME = "if";
    private static final String NEGATION_SIGN = "!";

    @Override
    public String getName() {
        return EXP_NAME;
    }

    @Override
    public String buildReplacement(TextExpressionHandler textExpressionHandler, String expressionParams,
            String expressionBody, Map<String, Object> contextParams) {
        expressionParams = expressionParams.trim();
        String paramName = expressionParams.replace(NEGATION_SIGN, StringUtils.EMPTY);

        Object paramValue = contextParams.get(paramName);
        boolean condition = paramValue instanceof Boolean && (Boolean) paramValue;

        condition ^= expressionParams.startsWith(NEGATION_SIGN);

        if (condition) {
            return textExpressionHandler.handleLogicalExpressions(expressionBody, contextParams);
        }

        return StringUtils.EMPTY;
    }
}
