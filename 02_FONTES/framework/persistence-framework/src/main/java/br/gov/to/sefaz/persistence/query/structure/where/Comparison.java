package br.gov.to.sefaz.persistence.query.structure.where;

import br.gov.to.sefaz.persistence.query.structure.domain.OptionalQuery;
import br.gov.to.sefaz.persistence.query.structure.domain.Value;
import br.gov.to.sefaz.persistence.query.structure.select.SelectStructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Classe responsável por Comparar os valores das operações.
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 01/08/2016 10:44:00
 */
public class Comparison {

    private final Value left;
    private final ComparisonOperator operator;
    private final OptionalQuery<List<Value>> right;

    public Comparison(Value left, ComparisonOperator operator, Value... right) {
        this.left = left;
        this.operator = operator;
        this.right = new OptionalQuery<>(Arrays.stream(right).collect(Collectors.toList()));
    }

    public Comparison(Value left, ComparisonOperator operator, Collection<Value> right) {
        this.left = left;
        this.operator = operator;
        this.right = new OptionalQuery<>(right.stream().collect(Collectors.toList()));
    }

    public Comparison(Value left, ComparisonOperator operator, SelectStructure right) {
        this.left = left;
        this.operator = operator;
        this.right = new OptionalQuery<>(right);
    }

    public Comparison(Value left, ComparisonOperator operator, Value right) {
        this.left = left;
        this.operator = operator;
        ArrayList<Value> values = new ArrayList<>();
        values.add(right);
        this.right = new OptionalQuery<>(values);
    }

    public Value getLeft() {
        return left;
    }

    public ComparisonOperator getOperator() {
        return operator;
    }

    public OptionalQuery<List<Value>> getRight() {
        return right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Comparison that = (Comparison) o;
        return Objects.equals(left, that.left)
                && operator == that.operator
                && Objects.equals(right, that.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, operator, right);
    }

    @Override
    public String toString() {
        return "Comparison{"
                + "left=" + left
                + ", operator=" + operator
                + ", right=" + right
                + '}';
    }
}