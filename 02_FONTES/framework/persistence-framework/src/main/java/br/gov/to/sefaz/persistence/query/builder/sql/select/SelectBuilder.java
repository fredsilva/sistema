package br.gov.to.sefaz.persistence.query.builder.sql.select;

import br.gov.to.sefaz.persistence.query.builder.QueryStructureBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.select.groupby.GroupByBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.select.join.JoinBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.select.orderby.OrderByBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.select.signature.Selectable;
import br.gov.to.sefaz.persistence.query.builder.sql.select.where.SelectWhereBuilder;
import br.gov.to.sefaz.persistence.query.parser.domain.QueryLanguages;
import br.gov.to.sefaz.persistence.query.structure.domain.Alias;
import br.gov.to.sefaz.persistence.query.structure.domain.OptionalQuery;
import br.gov.to.sefaz.persistence.query.structure.select.SelectStructure;
import br.gov.to.sefaz.persistence.query.structure.select.join.JoinType;
import br.gov.to.sefaz.persistence.query.structure.select.orderby.Order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Classe responsável por conter os métodos de comando de Select em SQL do QueryBuilder.
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 30/06/2016 10:00:00
 */
public class SelectBuilder implements QueryStructureBuilder<SelectBuilder, SelectStructure>,
        Selectable<SelectBuilder, JoinBuilder, GroupByBuilder, OrderByBuilder, SelectWhereBuilder> {

    private final Optional<SelectBuilder> fromSelect;
    private final String fromTable;
    private final Optional<String> fromAlias;
    private final List<Alias<String>> columnsList;
    private final List<JoinBuilder> joins;
    private Optional<SelectWhereBuilder> whereBuilder;
    private Optional<GroupByBuilder> groupByBuilder;
    private Optional<OrderByBuilder> orderByBuilder;

    public SelectBuilder(String table) {
        this(Optional.empty(), table, Optional.empty());
    }

    public SelectBuilder(String table, String alias) {
        this(Optional.empty(), table, Optional.of(alias));
    }

    public SelectBuilder(QueryStructureBuilder<SelectBuilder, ?> select) {
        this(Optional.of(select.getRoot()), "", Optional.empty());
    }

    public SelectBuilder(QueryStructureBuilder<SelectBuilder, ?> select, String alias) {
        this(Optional.of(select.getRoot()), "", Optional.of(alias));
    }

    private SelectBuilder(Optional<SelectBuilder> fromSelect, String fromTable, Optional<String> fromAlias) {
        this.fromSelect = fromSelect;
        this.fromTable = fromTable;
        this.fromAlias = fromAlias;
        this.columnsList = new ArrayList<>();
        this.joins = new ArrayList<>();
        this.whereBuilder = Optional.empty();
        this.orderByBuilder = Optional.empty();
        this.groupByBuilder = Optional.empty();
    }

    @Override
    public SelectBuilder column(String field, String alias) {
        this.columnsList.add(new Alias<>(field, alias));
        return this;
    }

    @Override
    public SelectBuilder columns(String... columns) {
        this.columnsList.addAll(Arrays.stream(columns).map(Alias::new).collect(Collectors.toList()));
        return this;
    }

    @Override
    public JoinBuilder join(Alias<String> alias, JoinType type) {
        JoinBuilder joinBuilder = new JoinBuilder(this, alias, type);
        joins.add(joinBuilder);
        return joinBuilder;
    }

    @Override
    public JoinBuilder innerJoin(String table) {
        return join(new Alias<>(table), JoinType.INNER);
    }

    @Override
    public JoinBuilder innerJoin(String table, String alias) {
        return join(new Alias<>(table, alias), JoinType.INNER);
    }

    @Override
    public JoinBuilder leftJoin(String table) {
        return join(new Alias<>(table), JoinType.LEFT);
    }

    @Override
    public JoinBuilder leftJoin(String table, String alias) {
        return join(new Alias<>(table, alias), JoinType.LEFT);
    }

    @Override
    public JoinBuilder rightJoin(String table) {
        return join(new Alias<>(table), JoinType.RIGHT);
    }

    @Override
    public JoinBuilder rightJoin(String table, String alias) {
        return join(new Alias<>(table, alias), JoinType.RIGHT);
    }

    @Override
    public JoinBuilder fullJoin(String table) {
        return join(new Alias<>(table), JoinType.FULL);
    }

    @Override
    public JoinBuilder fullJoin(String table, String alias) {
        return join(new Alias<>(table, alias), JoinType.FULL);
    }

    @Override
    public SelectWhereBuilder where() {
        if (!whereBuilder.isPresent()) {
            whereBuilder = Optional.of(new SelectWhereBuilder(this));
        }
        return whereBuilder.get();
    }

    @Override
    public GroupByBuilder groupBy(String... columns) {
        if (!groupByBuilder.isPresent()) {
            groupByBuilder = Optional.of(new GroupByBuilder(this, columns));
        }
        return groupByBuilder.get();
    }

    @Override
    public OrderByBuilder orderBy(String field, Order order) {
        if (!orderByBuilder.isPresent()) {
            orderByBuilder = Optional.of(new OrderByBuilder(this, field, order));
        }
        return orderByBuilder.get();
    }

    @Override
    public OrderByBuilder orderBy(String field, String... andFields) {
        OrderByBuilder orderByBuilder = orderBy(field, Order.ASC);
        for (String andField : andFields) {
            orderByBuilder = orderByBuilder.andBy(andField, Order.ASC);
        }

        return orderByBuilder;
    }

    public String getFrom() {
        return fromAlias.orElse(fromTable);
    }

    @Override
    public SelectBuilder getRoot() {
        return this;
    }

    @Override
    public SelectStructure build() {
        OptionalQuery<String> optionalQuery;
        if (fromSelect.isPresent()) {
            optionalQuery = new OptionalQuery<>(fromSelect.get().build());
        } else {
            optionalQuery = new OptionalQuery<>(fromTable);
        }

        Alias<OptionalQuery<String>> from;
        if (fromAlias.isPresent()) {
            from = new Alias<>(optionalQuery, fromAlias.get());
        } else {
            from = new Alias<>(optionalQuery);
        }

        SelectStructure selectStructure = new SelectStructure(QueryLanguages.SQL, from);
        if (columnsList.isEmpty()) {
            columnsList.add(new Alias<>("*"));
        }

        selectStructure.addColumns(columnsList);
        selectStructure.addJoins(joins.stream().map(JoinBuilder::build).collect(Collectors.toList()));
        whereBuilder.ifPresent(wb -> selectStructure.setWhere(wb.build()));
        groupByBuilder.ifPresent(gbb -> selectStructure.setGroupBy(gbb.build()));
        orderByBuilder.ifPresent(obb -> selectStructure.setOrderBy(obb.build()));

        return selectStructure;
    }
}
