package br.gov.to.sefaz.persistence.query.builder.sql.select.signature;

/**
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 01/08/2016 11:39:00
 */
public interface Conditionable<R> {

    R where();
}
