package br.gov.to.sefaz.persistence.query.builder.hql.select.orderby;

import br.gov.to.sefaz.persistence.query.builder.QueryStructureBuilder;
import br.gov.to.sefaz.persistence.query.builder.hql.select.HqlSelectBuilder;
import br.gov.to.sefaz.persistence.query.structure.select.orderby.Order;
import br.gov.to.sefaz.persistence.query.structure.select.orderby.OrderByStructure;

import java.util.Map;

/**
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 30/06/2016 10:02:00
 */
public class HqlOrderByBuilder implements QueryStructureBuilder<HqlSelectBuilder, OrderByStructure> {

    private final QueryStructureBuilder<HqlSelectBuilder, ?> root;
    private final OrderByStructure structure;

    private HqlOrderByBuilder(QueryStructureBuilder<HqlSelectBuilder, ?> root) {
        this.root = root;
        this.structure = new OrderByStructure();
    }

    public HqlOrderByBuilder(QueryStructureBuilder<HqlSelectBuilder, ?> root, String field, Order order) {
        this(root);
        this.structure.putOrder(field, order);
    }

    public HqlOrderByBuilder(QueryStructureBuilder<HqlSelectBuilder, ?> root, Map<String, Order> ordersBy) {
        this(root);
        this.structure.putOrder(ordersBy);
    }

    public HqlOrderByBuilder andBy(String field, Order order) {
        structure.putOrder(field, order);
        return this;
    }

    @Override
    public HqlSelectBuilder getRoot() {
        return root.getRoot();
    }

    @Override
    public OrderByStructure build() {
        return structure;
    }
}
