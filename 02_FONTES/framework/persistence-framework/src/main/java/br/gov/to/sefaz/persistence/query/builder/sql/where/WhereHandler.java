package br.gov.to.sefaz.persistence.query.builder.sql.where;

/**
 * Interface de assinatura resposável por Comando Where Handler.
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 30/06/2016 10:24:00
 */
public interface WhereHandler {

    /**
     * Assinatura do Método AbstractJunctionBuilder.
     *
     * @return manipula a consulta.
     */
    AbstractJunctionBuilder<?, ?> handle(AbstractWhereBuilder<?,?,?> whereBuilder);
}
