package br.gov.to.sefaz.persistence.query.builder.hql.select.signature;

import br.gov.to.sefaz.persistence.query.builder.sql.select.signature.Selectable;

/**
 * Interface de assinatura responsável por métodos do comando Select.
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 01/08/2016 11:14:00
 * @param <S> Entidade base.
 * @param <J> Entidade base.
 * @param <G> Entidade base.
 * @param <O> Entidade base.
 * @param <W> Entidade base.
 */
public interface HqlSelectable<S, J, G, O, W> extends Selectable<S, J, G, O, W>,
        HqlJoinable<J>, HqlGroupable<G>, HqlOrderable<O>, HqlConditionable<W> {
}
