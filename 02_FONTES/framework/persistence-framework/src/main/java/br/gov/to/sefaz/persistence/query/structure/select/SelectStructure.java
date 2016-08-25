package br.gov.to.sefaz.persistence.query.structure.select;

import br.gov.to.sefaz.persistence.query.structure.domain.Alias;
import br.gov.to.sefaz.persistence.query.structure.domain.OptionalQuery;
import br.gov.to.sefaz.persistence.query.structure.select.groupby.GroupByStructure;
import br.gov.to.sefaz.persistence.query.structure.select.join.JoinStructure;
import br.gov.to.sefaz.persistence.query.structure.select.orderby.OrderByStructure;
import br.gov.to.sefaz.persistence.query.structure.where.ConditionsStructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 28/06/2016 15:41:00
 */
public class SelectStructure {

    private final String queryLanguage;
    private final Alias<OptionalQuery<String>> from;
    private final List<Alias<String>> columns;
    private final List<JoinStructure> joins;
    private Optional<ConditionsStructure> where;
    private Optional<GroupByStructure> groupBy;
    private Optional<OrderByStructure> orderBy;

    public SelectStructure(String queryLanguage, Alias<OptionalQuery<String>> from) {
        this.queryLanguage = queryLanguage;
        this.from = from;
        this.columns = new ArrayList<>();
        this.joins = new ArrayList<>();
        this.where = Optional.empty();
        this.groupBy = Optional.empty();
        this.orderBy = Optional.empty();
    }

    public String getQueryLanguage() {
        return queryLanguage;
    }

    public void addColumn(Alias<String> alias) {
        this.columns.add(alias);
    }

    public void addColumns(Collection<Alias<String>> aliases) {
        this.columns.addAll(aliases);
    }

    public void addJoin(JoinStructure join) {
        this.joins.add(join);
    }

    public void addJoins(Collection<JoinStructure> joins) {
        this.joins.addAll(joins);
    }

    public void setWhere(ConditionsStructure where) {
        this.where = Optional.of(where);
    }

    public void setGroupBy(GroupByStructure groupBy) {
        this.groupBy = Optional.of(groupBy);
    }

    public void setOrderBy(OrderByStructure orderBy) {
        this.orderBy = Optional.of(orderBy);
    }

    public Alias<OptionalQuery<String>> getFrom() {
        return from;
    }

    public List<Alias<String>> getColumns() {
        return columns;
    }

    public List<JoinStructure> getJoins() {
        return joins;
    }

    public Optional<ConditionsStructure> getWhere() {
        return where;
    }

    public Optional<GroupByStructure> getGroupBy() {
        return groupBy;
    }

    public Optional<OrderByStructure> getOrderBy() {
        return orderBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SelectStructure that = (SelectStructure) o;
        return Objects.equals(queryLanguage, that.queryLanguage)
                && Objects.equals(from, that.from)
                && Objects.equals(columns, that.columns)
                && Objects.equals(joins, that.joins)
                && Objects.equals(where, that.where)
                && Objects.equals(groupBy, that.groupBy)
                && Objects.equals(orderBy, that.orderBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(queryLanguage, from, columns, joins, where, groupBy, orderBy);
    }

    @Override
    public String toString() {
        return "SelectStructure{"
                + "queryLanguage='" + queryLanguage + '\''
                + ", from=" + from
                + ", columns=" + columns
                + ", joins=" + joins
                + ", where=" + where
                + ", groupBy=" + groupBy
                + ", orderBy=" + orderBy
                + '}';
    }
}