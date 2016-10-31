package br.gov.to.sefaz.persistence.query.builder.sql.select.where;

import br.gov.to.sefaz.persistence.query.builder.sql.select.SelectBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.select.groupby.GroupByBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.select.orderby.OrderByBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.where.AbstractJunctionBuilder;
import br.gov.to.sefaz.persistence.query.structure.select.orderby.Order;

/**
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 30/06/2016 10:01:00
 */
public class SelectJunctionBuilder extends AbstractJunctionBuilder<SelectWhereBuilder, SelectBuilder> {

    public SelectJunctionBuilder(SelectWhereBuilder root) {
        super(root);
    }

    public GroupByBuilder groupBy(String... columns) {
        return getRoot().groupBy(columns);
    }

    public OrderByBuilder orderBy(String field, Order order) {
        return getRoot().orderBy(field, order);
    }

    public OrderByBuilder orderBy(String field, String... andFields) {
        return getRoot().orderBy(field, andFields);
    }
}