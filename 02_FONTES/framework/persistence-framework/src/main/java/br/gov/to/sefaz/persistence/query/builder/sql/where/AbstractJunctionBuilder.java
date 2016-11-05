package br.gov.to.sefaz.persistence.query.builder.sql.where;

import br.gov.to.sefaz.persistence.query.builder.QueryStructureBuilder;
import br.gov.to.sefaz.persistence.query.structure.where.JunctionOperator;

import java.util.Optional;

/**
 * Classe abstrata responsável por métodos de condicionais AND e OR do QueryBuilder.
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 30/06/2016 10:02:00
 * @param <W> representa  uma Entidade que é uma operação.
 * @param <T> representa uma Entidade que retorna um objeto da classe.
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

    /**
     * Método responsável pela condicional AND da consulta no Query Builder.
     *
     * @return retorna a condicional  AND.
     */
    public W and() {
        operator = Optional.of(JunctionOperator.AND);
        return parent;
    }

    /**
     * Método responsável pela condicional OR da consulta no Query Builder.
     *
     * @return retorna a condicional OR.
     */
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