package br.gov.to.sefaz.util.pdf.handler;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * Implementação default de um {@link ExpressionReplaceHandler}, simplesmente chama o {@link TextExpressionHandler}
 * para resolver as outras e expressões contidas no texto do corpo da expressão.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 18/08/2016 17:39:00
 */
public class DefaultExpressionReplaceHandler implements ExpressionReplaceHandler {

    @Override
    public String getName() {
        return StringUtils.EMPTY;
    }

    @Override
    public String buildReplacement(TextExpressionHandler textExpressionHandler, String expressionParams,
            String expressionBody, Map<String, Object> contextParams) {
        return textExpressionHandler.handleLogicalExpressions(expressionBody, contextParams);
    }

}
