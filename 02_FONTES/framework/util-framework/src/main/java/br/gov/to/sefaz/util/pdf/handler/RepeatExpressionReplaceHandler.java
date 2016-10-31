package br.gov.to.sefaz.util.pdf.handler;

import br.gov.to.sefaz.exception.UnhandledException;
import br.gov.to.sefaz.util.reflection.ReflectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * <p>Implementação de um {@link ExpressionReplaceHandler} para resolução da expressão logica {@value EXP_NAME}.
 * Consiste na iteração de uma {@link Collection}e a utilização dos parametros do item em cada passada do loop
 * através de um alias.</p>
 * {@code
 * Ex 1:
 * #{repeat:collectionOfItems:alias}
 * - esse é o item ${alias.id}: ${alias.description}
 * #{:repeat}
 * }
 * <p> Vai iterar sobre a {@code collectionOfItems} e a cada passada do loop vai atribuir o valor do item a um
 * parametro chamado {@code collectionOfItems}. Sendo assim se no exemplo acima {@code collectionOfItems} tivesse dois
 * items com os atributos [{@code id = 5, description = "bem legal"}] e [{@code id = 10, , description = "empolgante"}]
 * o resultado final seria:</p>
 * {@code
 * - esse é o item 5: bem legal
 * - esse é o item 10: empolgante
 * }
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 18/08/2016 16:30:00
 */
public class RepeatExpressionReplaceHandler implements ExpressionReplaceHandler {

    private static final String EXP_NAME = "repeat";
    private static final String PARAMS_SEPARATOR = ":";

    @Override
    public String getName() {
        return EXP_NAME;
    }

    @Override
    @SuppressWarnings("unchecked") // já é feito um try catch
    public String buildReplacement(TextExpressionHandler textExpressionHandler, String expressionParams,
            String expressionBody, Map<String, Object> contextParams) {
        String[] params = expressionParams.split(PARAMS_SEPARATOR);
        String collectionName = params[0].trim();
        String alias = params[1].trim();
        Collection<Object> collection;

        try {
            collection = (Collection<Object>) contextParams.get(collectionName);
        } catch (ClassCastException e) {
            throw new UnhandledException("The '" + collectionName
                    + "' parameter needs to be a collection for repeat", e);
        }

        if (!Objects.isNull(collection) && !collection.isEmpty()) {
            StringBuilder handledBody = new StringBuilder();

            for (Object item : collection) {
                Map<String, Object> itemParams = ReflectionUtils.objToMap(item, alias);

                contextParams.putAll(itemParams);

                String body = textExpressionHandler.handleLogicalExpressions(expressionBody, contextParams);
                handledBody.append(textExpressionHandler.handleStaticExpressions(body, contextParams));

                contextParams.keySet().removeAll(itemParams.keySet());
            }

            return handledBody.toString();
        } else {
            return StringUtils.EMPTY;
        }
    }
}
