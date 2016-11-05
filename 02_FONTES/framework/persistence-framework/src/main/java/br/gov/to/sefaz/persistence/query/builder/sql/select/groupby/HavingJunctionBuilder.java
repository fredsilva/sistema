package br.gov.to.sefaz.persistence.query.builder.sql.select.groupby;

import br.gov.to.sefaz.persistence.query.builder.sql.select.SelectBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.select.orderby.OrderByBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.where.AbstractJunctionBuilder;
import br.gov.to.sefaz.persistence.query.structure.select.orderby.Order;

/**
 * Classe responsável por conter os métodos da operação Having do QueryBuilder.
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 30/06/2016 10:01:00
 */
public class HavingJunctionBuilder extends AbstractJunctionBuilder<HavingBuilder, SelectBuilder> {

    public HavingJunctionBuilder(HavingBuilder root) {
        super(root);
    }

    /**
     * Método responsável por executar o comando ORDER BY do HQL informa os parâmetros
     * <code>field</code> e <code>order</code> para a operação ORDER BY.
     *
     * @param field informa a campo do parâmetro.
     * @param order informa o comando de ordenação(ASC/DESC).
     *
     * @return retornar a montagem da execução do comando ORDER BY do SQL.
     */
    public OrderByBuilder orderBy(String field, Order order) {
        return getRoot().orderBy(field, order);
    }

    /**
     * Método responsável por executar o comando ORDER BY do HQL informa os parâmetros
     * <code>field</code> e <code>andFields</code> para a operação ORDER BY.
     *
     * @param field informa a campo do parâmetro.
     * @param andFields informa uma coleção de parâmetros.
     *
     * @return retornar a montagem da execução do comando ORDER BY do SQL.
     */
    public OrderByBuilder orderBy(String field, String... andFields) {
        return getRoot().orderBy(field, andFields);
    }
}