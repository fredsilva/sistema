package br.gov.to.sefaz.persistence.query.structure.domain;

import br.gov.to.sefaz.persistence.query.structure.select.SelectStructure;

import java.util.Optional;

/**
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 29/06/2016 10:21:00
 */
public class OptionalQuery<T> {

    private final Optional<SelectStructure> query;
    private final Optional<T> value;

    public OptionalQuery(T value) {
        this.query = Optional.empty();
        this.value = Optional.of(value);
    }

    public OptionalQuery(SelectStructure query) {
        this.query = Optional.of(query);
        this.value = Optional.empty();
    }

    public boolean isQuery() {
        return query.isPresent();
    }

    public SelectStructure getQuery() {
        return query.get();
    }

    public T getValue() {
        return value.get();
    }
}
