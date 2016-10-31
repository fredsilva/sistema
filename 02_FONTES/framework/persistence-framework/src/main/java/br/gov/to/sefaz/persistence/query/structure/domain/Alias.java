package br.gov.to.sefaz.persistence.query.structure.domain;

import java.util.Objects;
import java.util.function.Function;

/**
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 30/06/2016 09:32:00
 */
public class Alias<T> {

    private final T value;
    private final String aliasName;

    public Alias(T value) {
        this.value = value;
        this.aliasName = "";
    }

    public Alias(T value, String alias) {
        this.value = value;
        this.aliasName = alias;
    }

    public T getValue() {
        return value;
    }

    public String getAlias() {
        return aliasName;
    }

    public boolean hasAlias() {
        return !aliasName.isEmpty();
    }

    public String getIfAlias(Function<String, String> handler) {
        return hasAlias() ? handler.apply(aliasName) : "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Alias alias1 = (Alias) o;
        return Objects.equals(value, alias1.value)
                && Objects.equals(aliasName, alias1.aliasName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, aliasName);
    }
}
