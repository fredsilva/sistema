package br.gov.to.sefaz.persistence.query.structure.select.orderby;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Classe responsável por conter os métodos da operação Order By do QueryBuilder.
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 29/06/2016 09:49:00
 */
public class OrderByStructure {

    private final Map<String, Order> orders;

    public OrderByStructure() {
        orders = new LinkedHashMap<>();
    }

    /**
     * Método responsável inserir uma coluna e o sentido que será ordenada
     * <code>column</code> e <code>order</code>.
     *
     * @param column informa a campo da coluna.
     * @param order informa o comando de ordenação(ASC/DESC).
     */
    public void putOrder(String column, Order order) {
        orders.put(column, order);
    }

    /**
     * Método responsável por inserir um Map informando o sentido da ordenação
     * <code>ordersBy</code>.
     *
     * @param ordersBy informa o comando de ordenação(ASC/DESC).
     */
    public void putOrder(Map<String, Order> ordersBy) {
        orders.putAll(ordersBy);
    }

    public Map<String, Order> getOrders() {
        return orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrderByStructure that = (OrderByStructure) o;
        return Objects.equals(orders, that.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orders);
    }

    @Override
    public String toString() {
        return "OrderByStructure{"
                + "orders=" + orders
                + '}';
    }
}
