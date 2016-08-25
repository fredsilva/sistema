package br.gov.to.sefaz.persistence.query.builder.hql.select.groupby;

import br.gov.to.sefaz.persistence.query.builder.hql.select.HqlSelectBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.where.AbstractWhereBuilder;

/**
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 30/06/2016 10:01:00
 */
public class HqlHavingBuilder extends AbstractWhereBuilder<HqlHavingBuilder, HqlSelectBuilder, HqlHavingJunctionBuilder> {

    public HqlHavingBuilder(HqlSelectBuilder root) {
        super(root, HqlHavingJunctionBuilder::new);
    }
}