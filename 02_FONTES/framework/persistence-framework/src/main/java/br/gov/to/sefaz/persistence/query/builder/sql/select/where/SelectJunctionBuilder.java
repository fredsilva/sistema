package br.gov.to.sefaz.persistence.query.builder.sql.select.where;

import br.gov.to.sefaz.persistence.query.builder.sql.select.SelectBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.select.groupby.GroupByBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.select.orderby.OrderByBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.where.AbstractJunctionBuilder;
import br.gov.to.sefaz.persistence.query.structure.select.orderby.Order;

/**
 * Classe resposável por métodos da operação Order By e Group By.
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 30/06/2016 10:01:00
 */
public class SelectJunctionBuilder extends AbstractJunctionBuilder<SelectWhereBuilder, SelectBuilder> {

    public SelectJunctionBuilder(SelectWhereBuilder root) {
        super(root);
    }

    /**
     * Método responsável por executar o comando GROUPBY do SQL informa o parâmetro
     * <code>columns</code> para a operação GROUPBY.
     *
     * @param columns informa as colunas da entidade.
     *
     * @return retornar a montagem da execução do comando Group By do SQL.
     */
    public GroupByBuilder groupBy(String... columns) {
        return getRoot().groupBy(columns);
    }

    /**
     * Método responsável por executar o comando ORDER BY do SQL informa os parâmetros
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
     * Método responsável por executar o comando ORDER BY do SQL informa os parâmetros
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