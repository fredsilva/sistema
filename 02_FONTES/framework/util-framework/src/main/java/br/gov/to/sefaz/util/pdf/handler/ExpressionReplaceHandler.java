package br.gov.to.sefaz.util.pdf.handler;

import java.util.Map;

/**
 * Interface para a criação de um tratador de expressões logicas de um texto, recebe o texto puro dentro do contexto da
 * expressão (body) trata todas as variaveis ou expressões de sua responsabilidade e se necessário chama o
 * {@link TextExpressionHandler} para resolução de outras expressões. Ao fim da execução retorna o seu bloco de texto
 * totalmente tratado para a substituição no texto pai.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 18/08/2016 10:42:00
 */
public interface ExpressionReplaceHandler {

    /**
     * Nome da expressão logica ao qual se responsabiliza pelo tratamento (ex: if, repeat, ...).
     *
     * @return o nome da expressão
     */
    String getName();

    /**
     * Método responsavel pelo tratamento e resolução de todas as expressões contidas no {@code expressionBody}, se
     * necessário deve chamar o {@code textExpressionHandler} para resolução de expressões que não sejam de sua
     * responsabilidade. Ao final deve ser retornado o {@code expressionBody} totalmente tratado, ou seja sem
     * expressões logicas (ex: #{expName:expParams}) ou estaticas (ex: ${paramName}), para que seja feita a
     * substituição do {@code expressionBody} pelo retorno (texto tratado) no texto pai.
     *
     * @see TextExpressionHandler
     * @param textExpressionHandler tratador de textos agrega manipuladores de expressões logicas e estaticas e
     *                              gerencia suas chamadas, deve ser utilizado para resolução de expressões que não
     *                              sejam de responsabilidade desta classe
     * @param expressionParams parâmetros necessários para a resolução da expressão logica, pode conter mais de um
     *                         parâmetro na mesma {@link String} porém o parser é de responsabilidade desta classe
     * @param expressionBody texto corpo da expressão, que deve ser tratado e retornado depois dos devidos tratamentos
     * @param contextParams mapa de parametros que extão no contexto desta execução, se necessário pode ser alterado
     *                      antes de chamar o {@code textExpressionHandler} ou retornar o texto tratado, porém
     *                      um cuidado deve ser tomado para que não fique "lixo" ao final da execução pois estes
     *                      parametros são compartilhados com outros {@link ExpressionReplaceHandler}
     *
     * @return {@code expressionBody} devidamente tratado e sem expressões logicas e estaticas, para a substituição no
     *          texto pai
     */
    String buildReplacement(TextExpressionHandler textExpressionHandler, String expressionParams,
            String expressionBody, Map<String, Object> contextParams);
}
