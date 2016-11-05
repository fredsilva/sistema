package br.gov.to.sefaz.persistence.query.builder.hql.select.where;

import br.gov.to.sefaz.persistence.query.builder.hql.select.HqlSelectBuilder;
import br.gov.to.sefaz.persistence.query.builder.hql.select.groupby.HqlGroupByBuilder;
import br.gov.to.sefaz.persistence.query.builder.hql.select.orderby.HqlOrderByBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.where.AbstractJunctionBuilder;
import br.gov.to.sefaz.persistence.query.structure.select.orderby.Order;

/**
 * Classe responsável por conter os metodos de execução
 * de Group By e Order By do QueryBuilder.
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 30/06/2016 10:01:00
 */
public class HqlSelectJunctionBuilder extends AbstractJunctionBuilder<HqlSelectWhereBuilder, HqlSelectBuilder> {

    public HqlSelectJunctionBuilder(HqlSelectWhereBuilder root) {
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
    public HqlGroupByBuilder groupBy(String... columns) {
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
    public HqlOrderByBuilder orderBy(String field, Order order) {
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
    public HqlOrderByBuilder orderBy(String field, String... andFields) {
        return getRoot().orderBy(field, andFields);
    }

    /**
     * Método responsável por executar o comando ORDER BY do SQL.
     *
     * @return retornar a montagem da execução do comando ORDER BY do SQL.
     */
    public HqlOrderByBuilder orderById() {
        return getRoot().orderById();
    }
}