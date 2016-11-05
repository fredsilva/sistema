package br.gov.to.sefaz.persistence.query.builder.hql.select;

import br.gov.to.sefaz.persistence.query.builder.ParamsBuilder;
import br.gov.to.sefaz.persistence.query.builder.QueryStructureBuilder;
import br.gov.to.sefaz.persistence.query.builder.hql.handler.EntityHandler;
import br.gov.to.sefaz.persistence.query.builder.hql.select.groupby.HqlGroupByBuilder;
import br.gov.to.sefaz.persistence.query.builder.hql.select.join.HqlJoinBuilder;
import br.gov.to.sefaz.persistence.query.builder.hql.select.orderby.HqlOrderByBuilder;
import br.gov.to.sefaz.persistence.query.builder.hql.select.signature.HqlSelectable;
import br.gov.to.sefaz.persistence.query.builder.hql.select.where.HqlSelectJunctionBuilder;
import br.gov.to.sefaz.persistence.query.builder.hql.select.where.HqlSelectWhereBuilder;
import br.gov.to.sefaz.persistence.query.parser.domain.QueryLanguages;
import br.gov.to.sefaz.persistence.query.structure.domain.Alias;
import br.gov.to.sefaz.persistence.query.structure.domain.OptionalQuery;
import br.gov.to.sefaz.persistence.query.structure.select.SelectStructure;
import br.gov.to.sefaz.persistence.query.structure.select.join.JoinType;
import br.gov.to.sefaz.persistence.query.structure.select.orderby.Order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Classe responsável por conter os métodos de execução
 * de Delete em HQL do QueryBuilder.
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 07/07/2016 11:48:00
 */
@SuppressWarnings("PMD.TooManyMethods")
public class HqlSelectBuilder implements QueryStructureBuilder<HqlSelectBuilder, SelectStructure>,
        HqlSelectable<HqlSelectBuilder, HqlJoinBuilder, HqlGroupByBuilder, HqlOrderByBuilder, HqlSelectWhereBuilder> {

    private final Class<?> entityClass;
    private final Alias<String> from;
    private final List<Alias<String>> columnsList;
    private final List<HqlJoinBuilder> joins;
    private Optional<HqlSelectWhereBuilder> whereBuilder;
    private Optional<HqlGroupByBuilder> groupByBuilder;
    private Optional<HqlOrderByBuilder> orderByBuilder;

    public HqlSelectBuilder(Class<?> entityClass) {
        this(entityClass, new Alias<>(EntityHandler.getName(entityClass)));
    }

    public HqlSelectBuilder(Class<?> entityClass, String alias) {
        this(entityClass, new Alias<>(EntityHandler.getName(entityClass), alias));
    }

    private HqlSelectBuilder(Class<?> entityClass, Alias<String> from) {
        this.entityClass = entityClass;
        this.from = from;
        this.columnsList = new ArrayList<>();
        this.joins = new ArrayList<>();
        this.whereBuilder = Optional.empty();
        this.groupByBuilder = Optional.empty();
        this.orderByBuilder = Optional.empty();
    }

    @Override
    public HqlSelectBuilder column(String field, String alias) {
        this.columnsList.add(new Alias<>(field, alias));
        return this;
    }

    @Override
    public HqlSelectBuilder columns(String... columns) {
        this.columnsList.addAll(Arrays.stream(columns).map(Alias::new).collect(Collectors.toList()));
        return this;
    }

    @Override
    public HqlJoinBuilder join(Alias<String> alias, JoinType type) {
        HqlJoinBuilder joinBuilder = new HqlJoinBuilder(this, alias, type);
        joins.add(joinBuilder);
        return joinBuilder;
    }

    @Override
    public HqlJoinBuilder innerJoin(String field) {
        return join(new Alias<>(field), JoinType.INNER);
    }

    @Override
    public HqlJoinBuilder innerJoin(String field, String alias) {
        return join(new Alias<>(field, alias), JoinType.INNER);
    }

    @Override
    public HqlJoinBuilder leftJoin(String field) {
        return join(new Alias<>(field), JoinType.LEFT);
    }

    @Override
    public HqlJoinBuilder leftJoin(String field, String alias) {
        return join(new Alias<>(field, alias), JoinType.LEFT);
    }

    @Override
    public HqlJoinBuilder rightJoin(String field) {
        return join(new Alias<>(field), JoinType.RIGHT);
    }

    @Override
    public HqlJoinBuilder rightJoin(String field, String alias) {
        return join(new Alias<>(field, alias), JoinType.RIGHT);
    }

    @Override
    public HqlJoinBuilder fullJoin(String field) {
        return join(new Alias<>(field), JoinType.FULL);
    }

    @Override
    public HqlJoinBuilder fullJoin(String field, String alias) {
        return join(new Alias<>(field, alias), JoinType.FULL);
    }

    @Override
    public HqlJoinBuilder joinFetch(Alias<String> alias, JoinType type) {
        Alias<String> fetchAlias = new Alias<>("FETCH " + alias.getValue(), alias.getAlias());
        HqlJoinBuilder joinBuilder = new HqlJoinBuilder(this, fetchAlias, type);
        joins.add(joinBuilder);
        return joinBuilder;
    }

    @Override
    public HqlJoinBuilder innerJoinFetch(String field) {
        return joinFetch(new Alias<>(field), JoinType.INNER);
    }

    @Override
    public HqlJoinBuilder innerJoinFetch(String field, String alias) {
        return joinFetch(new Alias<>(field, alias), JoinType.INNER);
    }

    @Override
    public HqlJoinBuilder leftJoinFetch(String field) {
        return joinFetch(new Alias<>(field), JoinType.LEFT);
    }

    @Override
    public HqlJoinBuilder leftJoinFetch(String field, String alias) {
        return joinFetch(new Alias<>(field, alias), JoinType.LEFT);
    }

    @Override
    public HqlJoinBuilder rightJoinFetch(String field) {
        return joinFetch(new Alias<>(field), JoinType.RIGHT);
    }

    @Override
    public HqlJoinBuilder rightJoinFetch(String field, String alias) {
        return joinFetch(new Alias<>(field, alias), JoinType.RIGHT);
    }

    @Override
    public HqlJoinBuilder fullJoinFetch(String field) {
        return joinFetch(new Alias<>(field), JoinType.FULL);
    }

    @Override
    public HqlJoinBuilder fullJoinFetch(String field, String alias) {
        return joinFetch(new Alias<>(field, alias), JoinType.FULL);
    }

    /**
     * Método responsável por executar o comando Where do HQL.
     *
     * @return retornar a montagem do comando de execução Where do HQL.
     */
    public HqlSelectWhereBuilder where() {
        if (!whereBuilder.isPresent()) {
            whereBuilder = Optional.of(new HqlSelectWhereBuilder(this));
        }
        return whereBuilder.get();
    }

    /**
     * Método responsável por executar o comando Where do HQL informa o parâmetro
     * <code>id</code> para a clausula de condição do where.
     *
     * @param id chave que consulta a entidade.
     *
     * @return retornar a montagem da execução do comando Where do HQL.
     */
    @Override
    public HqlSelectJunctionBuilder whereId(Object id) {
        HqlSelectWhereBuilder where = where();
        ParamsBuilder idParams = EntityHandler.getIdParams(entityClass, id);
        HqlSelectJunctionBuilder junctionBuilder = null;

        for (Map.Entry<String, Object> entry : idParams.toMap().entrySet()) {
            String column = getFrom(entry.getKey());
            if (junctionBuilder == null) {
                junctionBuilder = where.equal(column, entry.getValue());
            } else {
                junctionBuilder = junctionBuilder.and().equal(column, entry.getValue());
            }
        }

        return junctionBuilder;
    }

    @Override
    public HqlGroupByBuilder groupBy(String... columns) {
        if (!groupByBuilder.isPresent()) {
            groupByBuilder = Optional.of(new HqlGroupByBuilder(this, columns));
        }
        return groupByBuilder.get();
    }

    @Override
    public HqlOrderByBuilder orderBy(String field, Order order) {
        if (!orderByBuilder.isPresent()) {
            orderByBuilder = Optional.of(new HqlOrderByBuilder(this, field, order));
        }
        return orderByBuilder.get();
    }

    @Override
    public HqlOrderByBuilder orderBy(String field, String... andFields) {
        HqlOrderByBuilder hqlOrderByBuilder = orderBy(field, Order.ASC);
        for (String andField : andFields) {
            hqlOrderByBuilder = hqlOrderByBuilder.andBy(andField, Order.ASC);
        }

        return hqlOrderByBuilder;
    }

    @Override
    public HqlOrderByBuilder orderById() {
        HqlOrderByBuilder hqlOrderByBuilder = null;
        for (String field : EntityHandler.getIdFields(entityClass)) {
            field = getFrom(field);
            if (hqlOrderByBuilder == null) {
                hqlOrderByBuilder = orderBy(field, Order.ASC);
            } else {
                hqlOrderByBuilder = hqlOrderByBuilder.andBy(field, Order.ASC);
            }
        }

        return hqlOrderByBuilder;
    }

    /**
     * Método responsável por executar a cláusula From na sentença da consulta.
     *
     * @param field informa a campo do parâmetro.
     *
     * @return retornar a montagem da execução do comando From.
     */
    public String getFrom(String field) {
        return from.getIfAlias(a -> a + ".") + field;
    }

    @Override
    public HqlSelectBuilder getRoot() {
        return this;
    }

    @Override
    public SelectStructure build() {
        OptionalQuery<String> optionalQuery = new OptionalQuery<>(from.getValue());
        Alias<OptionalQuery<String>> structureFrom;

        if (from.hasAlias()) {
            structureFrom = new Alias<>(optionalQuery, from.getAlias());
        } else {
            structureFrom = new Alias<>(optionalQuery);
        }

        SelectStructure selectStructure = new SelectStructure(QueryLanguages.HQL, structureFrom);
        if (columnsList.isEmpty() && from.hasAlias()) {
            selectStructure.addColumn(new Alias<>(from.getAlias()));
        } else {
            selectStructure.addColumns(columnsList);
        }

        selectStructure.addJoins(joins.stream()
                .map(HqlJoinBuilder::build)
                .collect(Collectors.toList()));

        whereBuilder.ifPresent(wb -> selectStructure.setWhere(wb.build()));
        groupByBuilder.ifPresent(gbb -> selectStructure.setGroupBy(gbb.build()));
        orderByBuilder.ifPresent(obb -> selectStructure.setOrderBy(obb.build()));

        return selectStructure;
    }
}
