package br.gov.to.sefaz.persistence.query.builder.sql.select.groupby;

import br.gov.to.sefaz.persistence.query.builder.sql.select.SelectBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.select.orderby.OrderByBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.select.signature.Orderable;
import br.gov.to.sefaz.persistence.query.builder.sql.where.AbstractWhereBuilder;
import br.gov.to.sefaz.persistence.query.structure.select.orderby.Order;

/**
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 30/06/2016 10:01:00
 */
public class HavingBuilder extends AbstractWhereBuilder<HavingBuilder, SelectBuilder, HavingJunctionBuilder>
    implements Orderable<OrderByBuilder> {

    public HavingBuilder(SelectBuilder root) {
        super(root, HavingJunctionBuilder::new);
    }

    @Override
    public OrderByBuilder orderBy(String field, Order order) {
        return getRoot().orderBy(field, order);
    }

    @Override
    public OrderByBuilder orderBy(String field, String... andFields) {
        return getRoot().orderBy(field, andFields);
    }
}