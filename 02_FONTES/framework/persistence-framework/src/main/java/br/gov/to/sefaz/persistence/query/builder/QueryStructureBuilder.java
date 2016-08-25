package br.gov.to.sefaz.persistence.query.builder;

/**
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 30/06/2016 09:58:00
 */
public interface QueryStructureBuilder<T,S> {

    T getRoot();

    S build();
}