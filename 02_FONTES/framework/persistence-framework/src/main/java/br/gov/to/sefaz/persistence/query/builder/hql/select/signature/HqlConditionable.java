package br.gov.to.sefaz.persistence.query.builder.hql.select.signature;

import br.gov.to.sefaz.persistence.query.builder.hql.select.where.HqlSelectJunctionBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.select.signature.Conditionable;

/**
 * Interface de assinatura resposável por métodos da cláusula Condicional.
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 01/08/2016 11:39:00
 * @param <R> Entidade base.
 */
public interface HqlConditionable<R> extends Conditionable<R> {

    /**
     * Método responsável por executar o comando Where do HQL informa o parâmetro
     * <code>id</code> para a cláusula de condição do where.
     *
     * @param id chave que consulta a entidade.
     *
     * @return retornar a montagem da execução do comando Where do HQL.
     */
    HqlSelectJunctionBuilder whereId(Object id);
}
