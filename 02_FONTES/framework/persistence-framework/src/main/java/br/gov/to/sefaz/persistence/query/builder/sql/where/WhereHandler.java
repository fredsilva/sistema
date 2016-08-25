package br.gov.to.sefaz.persistence.query.builder.sql.where;

/**
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 30/06/2016 10:24:00
 */
public interface WhereHandler {

    AbstractJunctionBuilder<?, ?> handle(AbstractWhereBuilder<?,?,?> whereBuilder);
}
