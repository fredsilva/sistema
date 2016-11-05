package br.gov.to.sefaz.persistence.query.builder.sql.where;

import br.gov.to.sefaz.persistence.query.builder.QueryStructureBuilder;

/**
 * Classe responsável por controlar os comandos de Where no QueryBuilder.
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 30/06/2016 10:01:00
 * @param <T> representa Entidade base.
 */
public class WhereBuilder<T> extends AbstractWhereBuilder<WhereBuilder<T>, T, JunctionBuilder<T>> {

    public WhereBuilder(QueryStructureBuilder<T, ?> root) {
        super(root, JunctionBuilder::new);
    }
}