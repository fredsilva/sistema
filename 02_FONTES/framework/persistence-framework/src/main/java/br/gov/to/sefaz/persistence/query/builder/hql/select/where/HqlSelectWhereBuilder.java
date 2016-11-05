package br.gov.to.sefaz.persistence.query.builder.hql.select.where;

import br.gov.to.sefaz.persistence.query.builder.hql.select.HqlSelectBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.where.AbstractWhereBuilder;

/**
 * Classe que contém os métodos HQL Select.
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 30/06/2016 10:01:00
 */
public class HqlSelectWhereBuilder extends AbstractWhereBuilder<HqlSelectWhereBuilder, HqlSelectBuilder,
                                                                HqlSelectJunctionBuilder> {

    public HqlSelectWhereBuilder(HqlSelectBuilder root) {
        super(root, HqlSelectJunctionBuilder::new);
    }
}
