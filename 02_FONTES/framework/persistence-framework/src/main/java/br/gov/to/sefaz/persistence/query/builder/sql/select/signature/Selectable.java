package br.gov.to.sefaz.persistence.query.builder.sql.select.signature;

/**
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 01/08/2016 11:14:00
 */
public interface Selectable<S, J, G, O, W> extends Joinable<J>, Groupable<G>, Orderable<O>, Conditionable<W> {

    S column(String field, String alias);

    S columns(String... columns);
}
