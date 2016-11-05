package br.gov.to.sefaz.persistence.query.builder.hql.select.signature;

/**
 * Interface de assinatura resposável por metodos da operação Group By.
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 01/08/2016 11:15:00
 * @param <R> Entidade base.
 */
public interface HqlGroupable<R> {

    /**
     * Assinatura do método responsável por executar a operação Group By
     * <code>columns</code>.
     *
     * @param columns informa uma coleção de colunas.
     *
     * @return retorna a montagem da operação Group By.
     */
    R groupBy(String... columns);
}
