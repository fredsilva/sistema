package br.gov.to.sefaz.persistence.query.builder.sql.select.signature;

/**
 * Interface de assinatura resposável por métodos do comando Select.
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 01/08/2016 11:14:00
 * @param <S> Entidade base.
 * @param <J> Entidade base.
 * @param <G> Entidade base.
 * @param <O> Entidade base.
 * @param <W> Entidade base.
 */
public interface Selectable<S, J, G, O, W> extends Joinable<J>, Groupable<G>, Orderable<O>, Conditionable<W> {


    /**
     * Assinatura responsável por executar os dados da Coluna.
     * <code>field</code> e <code>alias</code>.
     *
     * @param field informa a campo do parâmetro.
     * @param alias informa o nome da alias.
     *
     * @return retornar o campo e alias da coluna.
     */
    S column(String field, String alias);

    /**
     * Assinatura responsável por executar os dados da Coluna.
     * <code>columns</code>.
     *
     * @param columns  informa coleção de colunas.
     *
     * @return retornar o campo e alias da coluna.
     */
    S columns(String... columns);
}
