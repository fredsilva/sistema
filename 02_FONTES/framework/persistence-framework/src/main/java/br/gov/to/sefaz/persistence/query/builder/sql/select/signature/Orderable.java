package br.gov.to.sefaz.persistence.query.builder.sql.select.signature;

import br.gov.to.sefaz.persistence.query.structure.select.orderby.Order;

/**
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 01/08/2016 11:15:00
 */
public interface Orderable<R> {

    R orderBy(String field, Order order);

    R orderBy(String field, String... andFields);
}
