package br.gov.to.sefaz.persistence.query.builder.sql.where;

import br.gov.to.sefaz.persistence.query.builder.QueryStructureBuilder;
import br.gov.to.sefaz.persistence.query.structure.domain.Value;
import br.gov.to.sefaz.persistence.query.structure.select.SelectStructure;
import br.gov.to.sefaz.persistence.query.structure.where.Comparison;
import br.gov.to.sefaz.persistence.query.structure.where.ComparisonOperator;
import br.gov.to.sefaz.persistence.query.structure.where.ConditionStructure;
import br.gov.to.sefaz.persistence.query.structure.where.ConditionsStructure;
import br.gov.to.sefaz.persistence.query.structure.where.JunctionOperator;
import br.gov.to.sefaz.util.application.ApplicationUtil;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static br.gov.to.sefaz.persistence.query.structure.where.ComparisonOperator.BETWEEN;
import static br.gov.to.sefaz.persistence.query.structure.where.ComparisonOperator.DIFFERENT;
import static br.gov.to.sefaz.persistence.query.structure.where.ComparisonOperator.EQUAL;
import static br.gov.to.sefaz.persistence.query.structure.where.ComparisonOperator.EXISTS;
import static br.gov.to.sefaz.persistence.query.structure.where.ComparisonOperator.GREATER;
import static br.gov.to.sefaz.persistence.query.structure.where.ComparisonOperator.GREATER_EQUAL;
import static br.gov.to.sefaz.persistence.query.structure.where.ComparisonOperator.IN;
import static br.gov.to.sefaz.persistence.query.structure.where.ComparisonOperator.LESS;
import static br.gov.to.sefaz.persistence.query.structure.where.ComparisonOperator.LESS_EQUAL;
import static br.gov.to.sefaz.persistence.query.structure.where.ComparisonOperator.LIKE;

/**
 * Classe abstrata responsável por métodos de condicionais do QueryBuilder.
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 30/06/2016 10:01:00
 * @param <T> Entidade base a ser consultada.
 * @param <J> Entidade base a ser consultada.
 * @param <W> Entidade base a ser consultada.
 */
@SuppressWarnings({"PMD.TooManyMethods", "PMD.ShortMethodName"})
public abstract class AbstractWhereBuilder
        <W extends AbstractWhereBuilder<W, T, J>, T, J extends AbstractJunctionBuilder<W, T>>
        implements QueryStructureBuilder<T, ConditionsStructure> {

    private final QueryStructureBuilder<T, ?> root;
    private final J junction;
    private ConditionsStructure structure;
    private final Set<ConditionModifier> modifiers;

    public AbstractWhereBuilder(QueryStructureBuilder<T, ?> root, Function<W, J> junctionBuilderSupplier) {
        this.root = root;
        this.junction = junctionBuilderSupplier.apply(getThis());
        this.modifiers = new HashSet<>();
    }

    /**
     * Implementa a condicional NOT como funcionalidade.
     * A operação NOT nega o resultado do Atributo que aplicará
     * a condição desta operação.
     *
     * @return retorna a operação com condição negativa.
     */
    public W not() {
        modifiers.add(ConditionModifier.NOT);
        return getThis();
    }

    /**
     * Implementa a condicional OPT como funcionalidade.
     * A operação OPT ignora as variáveis que não serão executadas
     * na query do SQL.
     *
     * @return retorna a consulta ignorando as variáveis com a condicional OPT.
     */
    public W opt() {
        modifiers.add(ConditionModifier.OPTIONAL);
        return getThis();
    }

    /**
     * Implementa a condicional ISNULL como funcionalidade.
     * A operação ISNULL verifica se valor do Atributo contém
     * valor nulo.
     *
     * @param column variavel que será valor comparado.
     * @return retorna a montagem da condição com o parâmetro informado na operação.
     */
    public J isNull(String column) {
        return addComparison(new Comparison(Value.ofColumn(column), ComparisonOperator.IS_NULL));
    }

    /**
     * Implementa a condicional BETWEEN como funcionalidade.
     * A operação BETWEEN verifica se contém valores entre os dois
     * parâmetros informado na condicional.
     *
     * @param column coluna que terá seu valor comparado.
     * @param valueStart variavel que informa um valor inicial.
     * @param valueEnd variavel que informa um valor final.
     * @return retorna a condição montada com os valores informados nos parâmetros da operação.
     */
    public J between(String column, Object valueStart, Object valueEnd) {
        return between(Value.ofColumn(column), Value.ofParam(valueStart), Value.ofParam(valueEnd));
    }

    /**
     * Implementa a condicional BETWEEN como funcionalidade.
     * A operação BETWEEN verifica se contém valores no intervalo entre
     * os dois parâmetros informado na condicional.
     *
     * @param value variável que terá seu valor comparado.
     * @param valueStart variavel que informa um valor inicial.
     * @param valueEnd variavel que informa um valor final.
     * @return retorna a condição montada com os valores informados nos parâmetros da operação.
     */
    public J between(Value value, Value valueStart, Value valueEnd) {
        return addComparison(new Comparison(value, BETWEEN, valueStart, valueEnd));
    }

    /**
     * Implementa a condicional BETWEEN como funcionalidade.
     * A operação BETWEEN verifica se contém valores no intervalo entre
     * os duas colunas informada na condicional.
     *
     * @param value variável que terá seu valor comparado.
     * @param columnStart coluna que informa um valor inicial.
     * @param columnEnd coluna que informa um valor final.
     * @return retorna a condição montada com os valores informados nos parâmetros da operação.
     */
    public J betweenColumns(Object value, String columnStart, String columnEnd) {
        return between(Value.ofParam(value), Value.ofColumn(columnStart), Value.ofColumn(columnEnd));
    }

    /**
     * Implementa a condicional IN como funcionalidade.
     * A operação IN verifica se contém valores agrupados em uma lista
     * informada na condicional.
     *
     * @param column coluna que terá seu valor.
     * @param values valores que serão informados na operação.
     * @return retorna a condição montada com os valores em uma lista aplicando a operação condicional.
     */
    public J in(String column, Object... values) {
        return addComparison(new Comparison(Value.ofColumn(column), IN,
                Arrays.stream(values).map(Value::ofParam).collect(Collectors.toList())));
    }

    /**
     * Implementa a condicional IN como funcionalidade.
     * A operação IN verifica se contém valores agrupados em uma lista
     * informada na condicional.
     *
     * @param column coluna que terá seu valor.
     * @param values valores que serão informados na operação.
     * @return retorna a condição montada com os valores em uma lista aplicando a operação condicional.
     */
    public J in(String column, Iterable<Object> values) {
        return addComparison(new Comparison(Value.ofColumn(column), IN,
                StreamSupport.stream(values.spliterator(), false).map(Value::ofParam).collect(Collectors.toList())));
    }

    /**
     * Implementa a condicional IN como funcionalidade.
     * A operação IN verifica se contém valores agrupados em uma lista
     * informada na condicional.
     *
     * @param column coluna que terá seu valor.
     * @param values valores que serão informados na operação.
     * @return retorna a condição montada com os valores em uma lista aplicando a operação condicional.
     */
    public J in(String column, Value... values) {
        return addComparison(new Comparison(Value.ofColumn(column), IN,
                Arrays.stream(values).collect(Collectors.toList())));
    }

    /**
     * Implementa a condicional IN como funcionalidade.
     * A operação IN verifica se contém valores agrupados em uma lista
     * informada na condicional.
     *
     * @param column coluna que terá seu valor.
     * @param subquery contém uma query montada para ser executada.
     * @return retorna a condição montada com os valores em uma lista aplicando a operação condicional.
     * */
    public J in(String column, QueryStructureBuilder<? extends QueryStructureBuilder<?, SelectStructure>, ?> subquery) {
        return addComparison(new Comparison(Value.ofColumn(column), IN,
                subquery.getRoot().build()));
    }

    /**
     * Implementa a condicional EXISTS como funcionalidade.
     * A operação EXISTS verifica se existe um valor
     * para consulta informada.
     *
     * @param subquery contém uma query montada para ser executada.
     * @return retorna a consulta montada com a condição informada
     */
    public J exists(QueryStructureBuilder<? extends QueryStructureBuilder<?, SelectStructure>, ?> subquery) {
        return addComparison(new Comparison(Value.ofNull(), EXISTS, subquery.getRoot().build()));
    }

    /**
     * Implementa a condicional LIKE como funcionalidade.
     * A operação LIKE verifica se parâmetro está contido
     * em alguma parte da sentença que será consulta.
     *
     * @param column coluna que será informada.
     * @param value variável que terá seu valor comparado.
     * @return retorna a montagem da operação na consulta.
     */
    public J like(String column, Object value) {
        return manualLike("lower(" + column + ")",
                (StringUtils.isEmpty(value) ? value : "%" + value.toString()
                        .toLowerCase(ApplicationUtil.LOCALE) + "%"));
    }

    /**
     * Implementa a condicional LIKE como funcionalidade.
     * A operação LIKE verifica se parâmetro é igual
     * ao conteúdo no repositório.
     *
     * @param column coluna que será informada.
     * @param value variável que terá seu valor comparado.
     * @return retorna a montagem da operação na consulta.
     */
    public J manualLike(String column, Object value) {
        return addComparison(new Comparison(Value.ofColumn(column), LIKE, Value.ofParam(value)));
    }

    /**
     * Implementa a condicional LESSTHAN como funcionalidade.
     * A operação LESSTHAN verifica se operação é Menor que parâmetro
     * informado
     *
     * @param column coluna que será informada.
     * @param value variável que terá seu valor comparado.
     * @return retorna a montagem da operação na consulta.
     */
    public J lessThan(String column, Object value) {
        return addComparison(new Comparison(Value.ofColumn(column), LESS, Value.ofParam(value)));
    }

    /**
     * Implementa a condicional LESSTHANCOLUMNS como funcionalidade.
     * A operação LESSTHANCOLUMNS  verifica se operação é Menor que os parâmetros
     * das colunas informadas.
     *
     * @param leftColumn coluna a esquerda que será informada.
     * @param rightColumn coluna a direita que será informada.
     * @return retorna a montagem da operação na consulta.
     */
    public J lessThanColumns(String leftColumn, String rightColumn) {
        return addComparison(new Comparison(Value.ofColumn(leftColumn), LESS,
                Value.ofColumn(rightColumn)));
    }

    /**
     * Implementa a condicional LESSEQUALTHAN como funcionalidade.
     * A operação LESSEQUALTHAN verifica se operação é Menor ou Igual aos parâmetros
     * informados na coluna e valor.
     *
     * @param column coluna que será informada.
     * @param value variável que terá seu valor comparado.
     * @return retorna a montagem da operação na consulta.
     */
    public J lessEqualThan(String column, Object value) {
        return addComparison(new Comparison(Value.ofColumn(column), LESS_EQUAL, Value.ofParam(value)));
    }

    /**
     * Implementa a condicional LESSEQUALTHANCOLUMNS como funcionalidade.
     * A operação LESSEQUALTHANCOLUMNS verifica se operação é Menor ou Igual aos parâmetros
     * informados nas colunas.
     *
     * @param leftColumn coluna a esquerda que será informada.
     * @param rightColumn coluna a direita que será informada.
     * @return retorna a montagem da operação na consulta.
     */
    public J lessEqualThanColumns(String leftColumn, String rightColumn) {
        return addComparison(new Comparison(Value.ofColumn(leftColumn), LESS_EQUAL,
                Value.ofColumn(rightColumn)));
    }

    /**
     * Implementa a condicional GREATERTHAN como funcionalidade.
     * A operação GREATERTHAN  compara se o parâmetro da coluna é maior
     * que o parâmetro do valor informado.
     *
     * @param column coluna que será informada.
     * @param value variável que terá seu valor comparado.
     * @return retorna a montagem da operação na consulta.
     */
    public J greaterThan(String column, Object value) {
        return addComparison(new Comparison(Value.ofColumn(column), GREATER, Value.ofParam(value)));
    }

    /**
     * Implementa a condicional GREATERTHANCOLUMNS como funcionalidade.
     * A operação GREATERTHANCOLUMNS  compara se o parâmetro da coluna da esquerda é maior
     * que o parâmetro da coluna da direita.
     *
     * @param leftColumn coluna a esquerda que será informada.
     * @param rightColumn coluna a direita que será informada.
     * @return retorna a montagem da operação na consulta.
     */
    public J greaterThanColumns(String leftColumn, String rightColumn) {
        return addComparison(new Comparison(Value.ofColumn(leftColumn), GREATER,
                Value.ofColumn(rightColumn)));
    }

    /**
     * Implementa a condicional GREATEREQUALTHAN como funcionalidade.
     * A operação GREATEREQUALTHAN  compara se o parâmetro da coluna é maior
     * ou igual que parâmetro do valor informado.
     *
     * @param column coluna que será informada.
     * @param value variável que terá seu valor comparado.
     * @return retorna a montagem da operação na consulta.
     */
    public J greaterEqualThan(String column, Object value) {
        return addComparison(new Comparison(Value.ofColumn(column), GREATER_EQUAL, Value.ofParam(value)));
    }

    /**
     * Implementa a condicional GREATEREQUALTHANCOLUMNS como funcionalidade.
     * A operação GREATEREQUALTHANCOLUMNS compara se o parâmetro da coluna da esquerda é maior
     * ou Igual que o parâmetro da coluna da direita.
     *
     * @param leftColumn coluna a esquerda que será informada.
     * @param rightColumn coluna a direita que será informada.
     * @return retorna a montagem da operação na consulta.
     */
    public J greaterEqualThanColumns(String leftColumn, String rightColumn) {
        return addComparison(new Comparison(Value.ofColumn(leftColumn), GREATER_EQUAL,
                Value.ofColumn(rightColumn)));
    }

    /**
     * Implementa a condicional DIFFERENT como funcionalidade.
     * A operação DIFFERENT  compara se o parâmetro da coluna é diferente
     * ao parâmetro do valor informado.
     *
     * @param column coluna que será informada.
     * @param value variável que terá seu valor comparado.
     * @return retorna a montagem da operação na consulta.
     */
    public J different(String column, Object value) {
        return addComparison(new Comparison(Value.ofColumn(column), DIFFERENT, Value.ofParam(value)));
    }

    /**
     * Implementa a condicional DIFFERENTCOLUMN como funcionalidade.
     * A operação DIFFERENTCOLUMN compara se o parâmetro da coluna da esquerda é diferente
     * ao parâmetro da coluna da direita.
     *
     * @param leftColumn coluna a esquerda que será informada.
     * @param rightColumn coluna a direita que será informada.
     * @return retorna a montagem da operação na consulta.
     */
    public J differentColumns(String leftColumn, String rightColumn) {
        return addComparison(new Comparison(Value.ofColumn(leftColumn), DIFFERENT,
                Value.ofColumn(rightColumn)));
    }

    /**
     * Implementa a condicional EQUAL como funcionalidade.
     * A operação EQUAL compara se o parâmetro da coluna é igual
     * ao parâmetro do valor informado.
     *
     * @param column coluna que será informada.
     * @param value variável que terá seu valor comparado.
     * @return retorna a montagem da operação na consulta.
     */
    public J equal(String column, Object value) {
        return addComparison(new Comparison(Value.ofColumn(column), EQUAL, Value.ofParam(value)));
    }

    /**
     * Implementa a condicional EQUALCOLUMNS como funcionalidade.
     * A operação EQUALCOLUMNS compara se o parâmetro da coluna da esquerda é igual
     * ao parâmetro da coluna da direita.
     *
     * @param leftColumn coluna a esquerda que será informada.
     * @param rightColumn coluna a direita que será informada.
     * @return retorna a montagem da operação na consulta.
     */
    public J equalColumns(String leftColumn, String rightColumn) {
        return addComparison(new Comparison(Value.ofColumn(leftColumn), EQUAL, Value.ofColumn(rightColumn)));
    }

    /**
     * Implementa CONDITION como funcionalidade.
     * A operação CONDITION monta a estrutura da sentença das operações
     * na consulta.
     *
     * @param handler informa consulta da query.
     * @return retorna a montagem da consulta.
     */
    public J condition(WhereHandler handler) {
        WhereBuilder<T> whereBuilder = new WhereBuilder<>(this);
        handler.handle(whereBuilder);
        return addCondition(new ConditionStructure(junction.build(), whereBuilder.build(), modifiers));
    }

    private J addComparison(Comparison comparision) {
        return addCondition(new ConditionStructure(junction.build(), comparision, modifiers));
    }

    private J addCondition(ConditionStructure condition) {
        Optional<JunctionOperator> nextOperator = junction.build();

        if (nextOperator.isPresent()) {
            structure.addCondition(condition);
        } else {
            structure = new ConditionsStructure(condition);
        }

        modifiers.clear();
        return junction;
    }

    /**
     * Método responsável por retornar o próprio objeto da
     * classe.
     *
     * @return retorna a Entidade.
     */
    @SuppressWarnings("unchecked")
    private W getThis() {
        return (W) this;
    }

    /**
     * Método responsável por retornar o parâmetro
     * getRoot.
     *
     * @return retorna a Entidade.
     */
    @Override
    public T getRoot() {
        return root.getRoot();
    }

    /**
     * Método responsável por retornar o parâmetro
     * structure.
     *
     * @return retorna a Entidade.
     */
    @Override
    public ConditionsStructure build() {
        return structure;
    }
}