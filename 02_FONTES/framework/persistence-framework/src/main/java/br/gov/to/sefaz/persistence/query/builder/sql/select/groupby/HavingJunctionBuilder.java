package br.gov.to.sefaz.persistence.query.builder.sql.select.groupby;

import br.gov.to.sefaz.persistence.query.builder.sql.select.SelectBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.select.orderby.OrderByBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.where.AbstractJunctionBuilder;
import br.gov.to.sefaz.persistence.query.structure.select.orderby.Order;

/**
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 30/06/2016 10:01:00
 */
public class HavingJunctionBuilder extends AbstractJunctionBuilder<HavingBuilder, SelectBuilder> {

    public HavingJunctionBuilder(HavingBuilder root) {
        super(root);
    }

    public OrderByBuilder orderBy(String field, Order order) {
        return getRoot().orderBy(field, order);
    }

    public OrderByBuilder orderBy(String field, String... andFields) {
        return getRoot().orderBy(field, andFields);
    }
}