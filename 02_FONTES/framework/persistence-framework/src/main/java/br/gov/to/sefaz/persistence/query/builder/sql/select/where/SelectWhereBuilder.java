package br.gov.to.sefaz.persistence.query.builder.sql.select.where;

import br.gov.to.sefaz.persistence.query.builder.sql.select.SelectBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.where.AbstractWhereBuilder;

/**
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 30/06/2016 10:01:00
 */
public class SelectWhereBuilder extends AbstractWhereBuilder<SelectWhereBuilder, SelectBuilder, SelectJunctionBuilder> {

    public SelectWhereBuilder(SelectBuilder root) {
        super(root, SelectJunctionBuilder::new);
    }
}