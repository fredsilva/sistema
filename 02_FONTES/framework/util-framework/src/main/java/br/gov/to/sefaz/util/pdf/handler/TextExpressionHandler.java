package br.gov.to.sefaz.util.pdf.handler;

import br.gov.to.sefaz.util.formatter.FormatterUtil;
import org.apache.commons.lang3.text.StrSubstitutor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * <p>Classe para o tratamento de expressões logicas e estaticas de um texto.</p>
 *
 * @see #handleLogicalExpressions(CharSequence, Map)
 * @see #handleStaticExpressions(CharSequence, Map)
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 18/08/2016 14:55:00
 */
public class TextExpressionHandler {

    private static final String LOGICAL_EXPRESSION_PATTERN = "(#\\{)(:?)(\\w+)(:?)(.*?)(})";
    private static final int EXPRESSION_CLOSE_GROUP = 2;
    private static final int EXPRESSION_NAME_GROUP = 3;
    private static final int EXPRESSION_OPEN_GROUP = 4;
    private static final int EXPRESSION_PARAMS_GROUP = 5;
    private final Map<String, ExpressionReplaceHandler> replaceHandlers;
    private final DefaultExpressionReplaceHandler defaultExpressionReplaceHandler;

    /**
     * <p>Cria uma instancia da classe. Por default consegue resolver as seguintes expressões logicas:</p>
     * <ul>
     *     <li>{@link RepeatExpressionReplaceHandler}</li>
     *     <li>{@link IfExpressionReplaceHandler}</li>
     * </ul>
     */
    public TextExpressionHandler() {
        List<ExpressionReplaceHandler> replaceHandlers = new ArrayList<>();
        replaceHandlers.add(new RepeatExpressionReplaceHandler());
        replaceHandlers.add(new IfExpressionReplaceHandler());

        this.replaceHandlers = replaceHandlers.stream()
                .collect(Collectors.toMap(
                    ExpressionReplaceHandler::getName,
                    handler -> handler));
        this.defaultExpressionReplaceHandler = new DefaultExpressionReplaceHandler();
    }

    /**
     * Método recursivo (pde ser chamado dentro de um {@link ExpressionReplaceHandler}) para o tratamento de
     * expressões logicas e estáticas em um texto..
     *
     * @see #handleLogicalExpressions(CharSequence, Map)
     * @see #handleStaticExpressions(CharSequence, Map)
     * @param text texto de entrada, à ser manipulado
     * @param contextParams mapa de parametros a ser utilizado pelas expressões
     * @return texto tratado e sem nenhuma tag de expressão
     */
    public String handleText(String text, Map<String, Object> contextParams) {
        text = handleLogicalExpressions(text, contextParams);

        return handleStaticExpressions(text, contextParams);
    }

    /**
     * <p>Realiza a manipulação e tratamento de expressões logicas, extrai nome da expressão e chama o
     * {@link ExpressionReplaceHandler} ao qual o {@link ExpressionReplaceHandler#getName()} for igual ao </p>
     * <p><strong>Expressões Lógicas:</strong> são definidas pela expressão regular
     * "{@value LOGICAL_EXPRESSION_PATTERN}", os grupos da expressão representam:</p>
     * <ul>
     * <li><strong>$1</strong> marcas para sinalização de inicio de uma tag de uma expressão logica</li>
     * <li><strong>$2</strong> marca sinalizando que é a tag abertura de uma expressão logica</li>
     * <li><strong>$3</strong> nome da expressão logica</li>
     * <li><strong>$4</strong> marca sinalizando que é a tag abertura de uma expressão logica</li>
     * <li><strong>$5</strong> marcas para sinalização de fim de uma tag de uma expressão logica</li>
     * </ul>
     * <p> Simplificando, a aparencia de uma expressão logica deve fica da seguinte forma:
     * {@code #{expressionName:expressionParams}expressionBody{:expressionName}}. Uma expressão logica serve para
     * realizar operações de manipulação de um pedaço de texto, tais como if e repeat (iterar sobre uma coleção)</p>
     *
     * @param text bloco de texto a ser manipulado
     * @param contextParams parametros otilizados pelos {@link ExpressionReplaceHandler} para tratar o texto
     * @return texto de entrada devidamente tratado em sem expressões logicas e estaticas
     */
    public String handleLogicalExpressions(CharSequence text, Map<String, Object> contextParams) {
        Matcher matcher = Pattern.compile(LOGICAL_EXPRESSION_PATTERN).matcher(text);
        StringBuilder handledText = new StringBuilder(text);
        LogicalExpressionFinderContext finderBean = new LogicalExpressionFinderContext();

        while (matcher.find()) {
            if (hasOpenMark(matcher)) {
                finderBean.increaseOpensCount();
                handleFirstOpenMarkIfNeeded(matcher, finderBean);
            }

            if (hasCloseMark(matcher)) {
                finderBean.increaseClosesCount();
                handleWantedClosureIfNeedded(contextParams, matcher, handledText, finderBean);
            }
        }

        return handledText.toString();
    }

    private void handleWantedClosureIfNeedded(Map<String, Object> contextParams, Matcher matcher,
            StringBuilder handledText, LogicalExpressionFinderContext finderBean) {
        if (finderBean.isWantedClosure()) {
            if (!finderBean.isExpressionName(matcher.group(EXPRESSION_NAME_GROUP))) {
                throw new IllegalArgumentException(
                        "Expressão mal formada. Expressão '" + finderBean.getExpressionName()
                        + "' foi aberta {indice: " + finderBean.getOrigOuterStartIdx()
                        + "}, mas uma expressão '" + finderBean.getExpressionName()
                        + "' foi fechada em seu lugar {indice: " + finderBean.getOrigOuterStartIdx());
            }

            finderBean.sumInnerEndIdx(matcher.start());
            finderBean.sumOuterEndIdx(matcher.end());

            String expressionBody = handledText
                    .substring(finderBean.getInnerStartIdx(), finderBean.getInnerEndIdx());

            String replacement = replaceHandlers
                    .getOrDefault(finderBean.getExpressionName(), defaultExpressionReplaceHandler)
                    .buildReplacement(this, finderBean.getExpressionParams(), expressionBody, contextParams);

            handledText.replace(finderBean.getOuterStartIdx(), finderBean.getOuterEndIdx(), replacement);

            finderBean.setToNextFind(replacement.length());
        }
    }

    private void handleFirstOpenMarkIfNeeded(Matcher matcher, LogicalExpressionFinderContext finderBean) {
        if (finderBean.isFirstOpen()) {
            finderBean.setExpressionName(matcher.group(EXPRESSION_NAME_GROUP));
            finderBean.setExpressionParams(matcher.group(EXPRESSION_PARAMS_GROUP));
            finderBean.sumOuterStartIdx(matcher.start());
            finderBean.sumInnerStartIdx(matcher.end());
        }
    }

    private boolean hasCloseMark(Matcher matcher) {
        return !matcher.group(EXPRESSION_CLOSE_GROUP).isEmpty();
    }

    private boolean hasOpenMark(Matcher matcher) {
        return !matcher.group(EXPRESSION_OPEN_GROUP).isEmpty();
    }

    /**
     * <p><strong>Expressões Estaticas:</strong> são definidas e resolvidas pelo utilitario {@link StrSubstitutor}.
     * Elas são utilizadas para simplesmente fazer um replace de uma tag de aparencia {@code ${paramName}} pelo valor
     * atribuido ao parametro de nome "paramName".</p>
     * <p>Obs: Antes de executar {@link StrSubstitutor#replace(Object, Map)} realiza as formatações do metodo
     * {@link #formatSimpleParams}</p>
     *
     * @param text texto a ser tratado
     * @param params parametros para substituição das expressões
     * @return texto com todas as expressões encontradas no mapa de {@code param} substituidas
     */
    public String handleStaticExpressions(CharSequence text, Map<String, Object> params) {
        formatSimpleParams(params);
        return StrSubstitutor.replace(text, params);
    }

    private static void formatSimpleParams(Map<String, Object> params) {
        for (Map.Entry<String, Object> obj : params.entrySet()) {
            if (Objects.isNull(obj.getValue())) {
                obj.setValue(EMPTY);
            } else if (obj.getValue() instanceof LocalDate) {
                obj.setValue(FormatterUtil.formatDate((LocalDate) obj.getValue()));
            } else if (obj.getValue() instanceof LocalDateTime) {
                obj.setValue(FormatterUtil.formatDateTime((LocalDateTime) obj.getValue()));
            } else if (obj.getValue() instanceof Double
                    || obj.getValue() instanceof Float
                    || obj.getValue() instanceof BigDecimal) {
                obj.setValue(FormatterUtil.formatNumber((Number) obj.getValue()));
            }
        }
    }

}
