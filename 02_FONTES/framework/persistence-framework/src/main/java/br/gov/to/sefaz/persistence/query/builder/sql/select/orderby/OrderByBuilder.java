package br.gov.to.sefaz.persistence.query.builder.sql.select.orderby;

import br.gov.to.sefaz.persistence.query.builder.QueryStructureBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.select.SelectBuilder;
import br.gov.to.sefaz.persistence.query.structure.select.orderby.Order;
import br.gov.to.sefaz.persistence.query.structure.select.orderby.OrderByStructure;

import java.util.Map;

/**
 * Classe responsável por controlar os comandos de Order By no QueryBuilder.
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 30/06/2016 10:02:00
 */
public class OrderByBuilder implements QueryStructureBuilder<SelectBuilder, OrderByStructure> {

    private final QueryStructureBuilder<SelectBuilder, ?> root;
    private final OrderByStructure structure;

    public OrderByBuilder(QueryStructureBuilder<SelectBuilder, ?> root) {
        this.root = root;
        this.structure = new OrderByStructure();
    }

    public OrderByBuilder(QueryStructureBuilder<SelectBuilder, ?> root, String field, Order order) {
        this(root);
        this.structure.putOrder(field, order);
    }

    public OrderByBuilder(QueryStructureBuilder<SelectBuilder, ?> root, Map<String, Order> ordersBy) {
        this(root);
        this.structure.putOrder(ordersBy);
    }

    /**
     * Método responsável por executar o comando AND BY do SQL informa os parâmetros
     * <code>field</code> e <code>order</code> para a operação AND BY.
     *
     * @param field informa a campo do parâmetro.
     * @param order informa o comando de ordenação(ASC/DESC).
     *
     * @return retornar a montagem da execução do comando AND BY do SQL.
     */
    public OrderByBuilder andBy(String field, Order order) {
        structure.putOrder(field, order);
        return this;
    }

    @Override
    public SelectBuilder getRoot() {
        return root.getRoot();
    }

    @Override
    public OrderByStructure build() {
        return structure;
    }
}
