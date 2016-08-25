package br.gov.to.sefaz.persistence.query.structure.domain;

import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 29/06/2016 10:59:00
 */
public final class Value {

    private final Object rootValue;
    private final ValueType type;

    private Value(Object rootValue, ValueType type) {
        this.rootValue = rootValue;
        this.type = type;
    }

    public static Value ofColumn(String columnName) {
        return new Value(columnName, ValueType.COLUMN);
    }

    public static Value ofParam(Object value) {
        return new Value(value, ValueType.PARAM);
    }

    public static Value ofNull() {
        return new Value("NULL", ValueType.COLUMN);
    }

    public Object getValue() {
        return rootValue;
    }

    public String getColumnName() {
        return (String) rootValue;
    }

    public ValueType getType() {
        return type;
    }

    public boolean isEmpty() {
        return getType() == ValueType.PARAM && StringUtils.isEmpty(rootValue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Value value1 = (Value) o;
        return type == value1.type
                && Objects.equals(rootValue, value1.rootValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rootValue, type);
    }

    @Override
    public String toString() {
        return "Value{"
                + "rootValue=" + rootValue
                + ", ofColumn=" + type
                + '}';
    }
}
