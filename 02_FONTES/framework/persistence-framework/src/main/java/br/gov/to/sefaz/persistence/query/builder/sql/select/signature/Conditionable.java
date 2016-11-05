package br.gov.to.sefaz.persistence.query.builder.sql.select.signature;

/**
 * Interface de assinatura resposável por métodos do componente Conditionable.
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 01/08/2016 11:39:00
 * @param <R> Entidade base.
 */
public interface Conditionable<R> {

    /**
     * Assinatura do Método responsável por executar o comando Where do SQL.
     *
     * @return retornar a montagem do comando de execução Where do SQL.
     */
    R where();
}
