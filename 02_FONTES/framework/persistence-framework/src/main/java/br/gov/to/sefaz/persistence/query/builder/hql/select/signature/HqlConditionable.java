package br.gov.to.sefaz.persistence.query.builder.hql.select.signature;

import br.gov.to.sefaz.persistence.query.builder.hql.select.where.HqlSelectJunctionBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.select.signature.Conditionable;

/**
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 01/08/2016 11:39:00
 */
public interface HqlConditionable<R> extends Conditionable<R> {

    HqlSelectJunctionBuilder whereId(Object id);
}
