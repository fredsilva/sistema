package br.gov.to.sefaz.persistence.query.builder.hql.select.signature;

import br.gov.to.sefaz.persistence.query.builder.sql.select.signature.Orderable;

/**
 * Interface de assinatura resposável por métodos da operação Order By.
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 01/08/2016 11:15:00
 * @param <R> Entidade base.
 */
public interface HqlOrderable<R> extends Orderable<R> {

    /**
     * Assinatura do método responsável por executar a operação Order By.
     *
     * @return retorna a montagem da operação Order By.
     */
    R orderById();
}
