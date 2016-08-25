package br.gov.to.sefaz.persistence.query.builder.hql.select.groupby;

import br.gov.to.sefaz.persistence.query.builder.hql.select.HqlSelectBuilder;
import br.gov.to.sefaz.persistence.query.builder.hql.select.orderby.HqlOrderByBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.where.AbstractJunctionBuilder;
import br.gov.to.sefaz.persistence.query.structure.select.orderby.Order;

/**
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 30/06/2016 10:01:00
 */
public class HqlHavingJunctionBuilder extends AbstractJunctionBuilder<HqlHavingBuilder, HqlSelectBuilder> {

    public HqlHavingJunctionBuilder(HqlHavingBuilder root) {
        super(root);
    }

    public HqlOrderByBuilder orderBy(String field, Order order) {
        return getRoot().orderBy(field, order);
    }

    public HqlOrderByBuilder orderBy(String field, String... andFields) {
        return getRoot().orderBy(field, andFields);
    }
}