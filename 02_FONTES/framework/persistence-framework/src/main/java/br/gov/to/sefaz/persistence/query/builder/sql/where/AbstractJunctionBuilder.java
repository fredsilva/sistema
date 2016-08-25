package br.gov.to.sefaz.persistence.query.builder.sql.where;

import br.gov.to.sefaz.persistence.query.builder.QueryStructureBuilder;
import br.gov.to.sefaz.persistence.query.structure.where.JunctionOperator;

import java.util.Optional;

/**
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 30/06/2016 10:02:00
 */
@SuppressWarnings("PMD.ShortMethodName")
public abstract class AbstractJunctionBuilder<W extends AbstractWhereBuilder<?, T, ?>, T>
        implements QueryStructureBuilder<T, Optional<JunctionOperator>> {

    private final W parent;
    private Optional<JunctionOperator> operator;

    public AbstractJunctionBuilder(W parent) {
        this.parent = parent;
        this.operator = Optional.empty();
    }

    public W and() {
        operator = Optional.of(JunctionOperator.AND);
        return parent;
    }

    public W or() {
        operator = Optional.of(JunctionOperator.OR);
        return parent;
    }

    @Override
    public T getRoot() {
        return parent.getRoot();
    }

    @Override
    public Optional<JunctionOperator> build() {
        return operator;
    }
}