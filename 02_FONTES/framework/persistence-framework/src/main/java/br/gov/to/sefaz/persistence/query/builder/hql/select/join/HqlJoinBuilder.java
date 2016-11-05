package br.gov.to.sefaz.persistence.query.builder.hql.select.join;

import br.gov.to.sefaz.persistence.query.builder.QueryStructureBuilder;
import br.gov.to.sefaz.persistence.query.builder.hql.select.HqlSelectBuilder;
import br.gov.to.sefaz.persistence.query.builder.hql.select.groupby.HqlGroupByBuilder;
import br.gov.to.sefaz.persistence.query.builder.hql.select.orderby.HqlOrderByBuilder;
import br.gov.to.sefaz.persistence.query.builder.hql.select.signature.HqlConditionable;
import br.gov.to.sefaz.persistence.query.builder.hql.select.signature.HqlGroupable;
import br.gov.to.sefaz.persistence.query.builder.hql.select.signature.HqlJoinable;
import br.gov.to.sefaz.persistence.query.builder.hql.select.signature.HqlOrderable;
import br.gov.to.sefaz.persistence.query.builder.hql.select.where.HqlSelectJunctionBuilder;
import br.gov.to.sefaz.persistence.query.builder.hql.select.where.HqlSelectWhereBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.where.WhereBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.where.WhereHandler;
import br.gov.to.sefaz.persistence.query.structure.domain.Alias;
import br.gov.to.sefaz.persistence.query.structure.select.join.JoinStructure;
import br.gov.to.sefaz.persistence.query.structure.select.join.JoinType;
import br.gov.to.sefaz.persistence.query.structure.select.orderby.Order;

import java.util.Optional;

/**
 * Classe responsável por construir a estrutura do Join para consultas HQL.
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 01/08/2016 10:44:00
 */
@SuppressWarnings("PMD.TooManyMethods")
public class HqlJoinBuilder implements QueryStructureBuilder<HqlSelectBuilder, JoinStructure>,
        HqlJoinable<HqlJoinBuilder>, HqlGroupable<HqlGroupByBuilder>, HqlOrderable<HqlOrderByBuilder>,
        HqlConditionable<HqlSelectWhereBuilder> {

    private final HqlSelectBuilder parent;
    private final Alias<String> table;
    private final JoinType type;
    private Optional<WhereBuilder<HqlSelectBuilder>> onBuilder;

    public HqlJoinBuilder(HqlSelectBuilder parent, Alias<String> table, JoinType type) {
        this.parent = parent;
        this.table = table;
        this.type = type;
        this.onBuilder = Optional.empty();
    }

    /**
     * Método responsável por executar a condição With <code>onHandler</code>.
     *
     * @param onHandler informa a campo que manipula a sentença.
     *
     * @return retornar a montagem da execução da condição With.
     */
    public HqlSelectBuilder with(WhereHandler onHandler) {
        onBuilder =  Optional.of(new WhereBuilder<>(parent));
        onHandler.handle(onBuilder.get());
        return getRoot();
    }

    @Override
    public HqlSelectBuilder getRoot() {
        return parent.getRoot();
    }

    @Override
    public HqlJoinBuilder join(Alias<String> alias, JoinType type) {
        return parent.join(alias, type);
    }

    @Override
    public HqlJoinBuilder innerJoin(String table) {
        return parent.innerJoin(table);
    }

    @Override
    public HqlJoinBuilder innerJoin(String table, String alias) {
        return parent.innerJoin(table, alias);
    }

    @Override
    public HqlJoinBuilder leftJoin(String table) {
        return parent.leftJoin(table);
    }

    @Override
    public HqlJoinBuilder leftJoin(String table, String alias) {
        return parent.leftJoin(table, alias);
    }

    @Override
    public HqlJoinBuilder rightJoin(String table) {
        return parent.rightJoin(table);
    }

    @Override
    public HqlJoinBuilder rightJoin(String table, String alias) {
        return parent.rightJoin(table, alias);
    }

    @Override
    public HqlJoinBuilder fullJoin(String table) {
        return parent.fullJoin(table);
    }

    @Override
    public HqlJoinBuilder fullJoin(String table, String alias) {
        return parent.fullJoin(table, alias);
    }

    @Override
    public HqlJoinBuilder joinFetch(Alias<String> alias, JoinType type) {
        return parent.joinFetch(alias, type);
    }

    @Override
    public HqlJoinBuilder innerJoinFetch(String table) {
        return parent.innerJoinFetch(table);
    }

    @Override
    public HqlJoinBuilder innerJoinFetch(String table, String alias) {
        return parent.innerJoinFetch(table, alias);
    }

    @Override
    public HqlJoinBuilder leftJoinFetch(String field) {
        return parent.leftJoinFetch(field);
    }

    @Override
    public HqlJoinBuilder leftJoinFetch(String field, String alias) {
        return parent.leftJoinFetch(field, alias);
    }

    @Override
    public HqlJoinBuilder rightJoinFetch(String field) {
        return parent.rightJoinFetch(field);
    }

    @Override
    public HqlJoinBuilder rightJoinFetch(String field, String alias) {
        return parent.rightJoinFetch(field, alias);
    }

    @Override
    public HqlJoinBuilder fullJoinFetch(String field) {
        return parent.fullJoinFetch(field);
    }

    @Override
    public HqlJoinBuilder fullJoinFetch(String field, String alias) {
        return parent.fullJoinFetch(field, alias);
    }

    @Override
    public JoinStructure build() {
        if (onBuilder.isPresent()) {
            return new JoinStructure(table, type, onBuilder.get().build());
        }

        return new JoinStructure(table, type);
    }

    @Override
    public HqlGroupByBuilder groupBy(String... columns) {
        return parent.groupBy(columns);
    }

    @Override
    public HqlSelectWhereBuilder where() {
        return parent.where();
    }

    @Override
    public HqlSelectJunctionBuilder whereId(Object id) {
        return parent.whereId(id);
    }

    @Override
    public HqlOrderByBuilder orderBy(String field, Order order) {
        return parent.orderBy(field, order);
    }

    @Override
    public HqlOrderByBuilder orderBy(String field, String... andFields) {
        return getRoot().orderBy(field, andFields);
    }

    @Override
    public HqlOrderByBuilder orderById() {
        return parent.orderById();
    }
}
