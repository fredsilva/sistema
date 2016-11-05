package br.gov.to.sefaz.persistence.query.builder.sql.select.signature;

import br.gov.to.sefaz.persistence.query.structure.select.orderby.Order;

/**
 * Interface de assinatura resposável por métodos da operação Order By.
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 01/08/2016 11:15:00
 * @param <R> Entidade base.
 */
public interface Orderable<R> {

    /**
     * Método responsável por executar o comando ORDER BY do SQL informa os parâmetros
     * <code>field</code> e <code>order</code> para a operação ORDER BY.
     *
     * @param field informa a campo do parâmetro.
     * @param order informa o comando de ordenação(ASC/DESC).
     *
     * @return retornar a montagem da execução do comando ORDER BY do SQL.
     */
    R orderBy(String field, Order order);

    /**
     * Método responsável por executar o comando ORDER BY do SQL informa os parâmetros
     * <code>field</code> e <code>andFields</code> para a operação ORDER BY.
     *
     * @param field informa a campo do parâmetro.
     * @param andFields informa uma coleção de parâmetros.
     *
     * @return retornar a montagem da execução do comando ORDER BY do SQL.
     */
    R orderBy(String field, String... andFields);
}
