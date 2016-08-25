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
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 30/06/2016 10:01:00
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

    public W not() {
        modifiers.add(ConditionModifier.NOT);
        return getThis();
    }

    public W opt() {
        modifiers.add(ConditionModifier.OPTIONAL);
        return getThis();
    }

    public J isNull(String column) {
        return addComparison(new Comparison(Value.ofColumn(column), ComparisonOperator.IS_NULL));
    }

    public J between(String column, Object valueStart, Object valueEnd) {
        return between(Value.ofColumn(column), Value.ofParam(valueStart), Value.ofParam(valueEnd));
    }

    public J between(Value value, Value valueStart, Value valueEnd) {
        return addComparison(new Comparison(value, BETWEEN, valueStart, valueEnd));
    }

    public J betweenColumns(Object value, String columnStart, String columnEnd) {
        return between(Value.ofParam(value), Value.ofColumn(columnStart), Value.ofColumn(columnEnd));
    }

    public J in(String column, Object... values) {
        return addComparison(new Comparison(Value.ofColumn(column), IN,
                Arrays.stream(values).map(Value::ofParam).collect(Collectors.toList())));
    }

    public J in(String column, Iterable<Object> values) {
        return addComparison(new Comparison(Value.ofColumn(column), IN,
                StreamSupport.stream(values.spliterator(), false).map(Value::ofParam).collect(Collectors.toList())));
    }

    public J in(String column, Value... values) {
        return addComparison(new Comparison(Value.ofColumn(column), IN,
                Arrays.stream(values).collect(Collectors.toList())));
    }

    public J in(String column, QueryStructureBuilder<? extends QueryStructureBuilder<?, SelectStructure>, ?> subquery) {
        return addComparison(new Comparison(Value.ofColumn(column), IN,
                subquery.getRoot().build()));
    }

    public J exists(QueryStructureBuilder<? extends QueryStructureBuilder<?, SelectStructure>, ?> subquery) {
        return addComparison(new Comparison(Value.ofNull(), EXISTS, subquery.getRoot().build()));
    }

    public J like(String column, Object value) {
        return manualLike("lower(" + column + ")",
                (StringUtils.isEmpty(value) ? value : "%" + value.toString()
                        .toLowerCase(ApplicationUtil.LOCALE) + "%"));
    }

    public J manualLike(String column, Object value) {
        return addComparison(new Comparison(Value.ofColumn(column), LIKE, Value.ofParam(value)));
    }

    public J lessThan(String column, Object value) {
        return addComparison(new Comparison(Value.ofColumn(column), LESS, Value.ofParam(value)));
    }

    public J lessThanColumns(String leftColumn, String rightColumn) {
        return addComparison(new Comparison(Value.ofColumn(leftColumn), LESS,
                Value.ofColumn(rightColumn)));
    }

    public J lessEqualThan(String column, Object value) {
        return addComparison(new Comparison(Value.ofColumn(column), LESS_EQUAL, Value.ofParam(value)));
    }

    public J lessEqualThanColumns(String leftColumn, String rightColumn) {
        return addComparison(new Comparison(Value.ofColumn(leftColumn), LESS_EQUAL,
                Value.ofColumn(rightColumn)));
    }

    public J greaterThan(String column, Object value) {
        return addComparison(new Comparison(Value.ofColumn(column), GREATER, Value.ofParam(value)));
    }

    public J greaterThanColumns(String leftColumn, String rightColumn) {
        return addComparison(new Comparison(Value.ofColumn(leftColumn), GREATER,
                Value.ofColumn(rightColumn)));
    }

    public J greaterEqualThan(String column, Object value) {
        return addComparison(new Comparison(Value.ofColumn(column), GREATER_EQUAL, Value.ofParam(value)));
    }

    public J greaterEqualThanColumns(String leftColumn, String rightColumn) {
        return addComparison(new Comparison(Value.ofColumn(leftColumn), GREATER_EQUAL,
                Value.ofColumn(rightColumn)));
    }

    public J different(String column, Object value) {
        return addComparison(new Comparison(Value.ofColumn(column), DIFFERENT, Value.ofParam(value)));
    }

    public J differentColumns(String leftColumn, String rightColumn) {
        return addComparison(new Comparison(Value.ofColumn(leftColumn), DIFFERENT,
                Value.ofColumn(rightColumn)));
    }

    public J equal(String column, Object value) {
        return addComparison(new Comparison(Value.ofColumn(column), EQUAL, Value.ofParam(value)));
    }

    public J equalColumns(String leftColumn, String rightColumn) {
        return addComparison(new Comparison(Value.ofColumn(leftColumn), EQUAL, Value.ofColumn(rightColumn)));
    }

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

    @SuppressWarnings("unchecked")
    private W getThis() {
        return (W) this;
    }

    @Override
    public T getRoot() {
        return root.getRoot();
    }

    @Override
    public ConditionsStructure build() {
        return structure;
    }
}