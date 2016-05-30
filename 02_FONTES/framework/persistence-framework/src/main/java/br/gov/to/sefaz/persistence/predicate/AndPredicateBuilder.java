package br.gov.to.sefaz.persistence.predicate;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * <p>Builder para construir {@link javax.persistence.criteria.Predicate} para construir filtros de AND.</p>
 * <p>Exemplo: ... WHERE x == y AND w LIKE %z% AND ...</p>
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 11/05/2016 11:25:00
 */
public class AndPredicateBuilder {

    private static final String ANY_SENTENCE = "%";
    private final Root<?> root;
    private final CriteriaBuilder cb;
    private final List<Predicate> predicates;

    public AndPredicateBuilder(Root<?> root, CriteriaBuilder cb) {
        this.root = root;
        this.cb = cb;
        this.predicates = new ArrayList<>();
    }

    public AndPredicateBuilder equalsTo(String fieldName, String value) {
        if (StringUtils.isNotEmpty(value)) {
            addEquals(fieldName, value);
        }

        return this;
    }

    public AndPredicateBuilder equalsTo(String fieldName, Object value) {
        if (!Objects.isNull(value)) {
            addEquals(fieldName, value);
        }

        return this;
    }

    public AndPredicateBuilder like(String fieldName, String value) {
        if (StringUtils.isNotEmpty(value)) {
            addLike(fieldName, value);
        }

        return this;
    }

    public AndPredicateBuilder like(String fieldName, Object value) {
        if (!Objects.isNull(value)) {
            addLike(fieldName, value);
        }

        return this;
    }

    public AndPredicateBuilder fetch(String fieldName) {
        root.fetch(fieldName, JoinType.INNER);
        return this;
    }

    public Predicate build() {
        return cb.and(predicates.stream().toArray(Predicate[]::new));
    }

    private boolean addLike(String fieldName, Object value) {
        return predicates.add(cb.like(getField(fieldName).as(String.class), ANY_SENTENCE + value + ANY_SENTENCE));
    }

    private boolean addEquals(String fieldName, Object value) {
        return predicates.add(cb.equal(getField(fieldName), value));
    }

    private Expression<String> getField(String fieldPath) {
        LinkedList<String> fieldNames = new LinkedList<>();
        fieldNames.addAll(Arrays.asList(fieldPath.split("\\.")));

        String first = fieldNames.removeFirst();

        if (fieldNames.isEmpty()) {
            return root.get(first);
        }

        Join<Object, Object> join = root.join(first);
        String last = fieldNames.removeLast();

        for (String fieldName : fieldNames) {
            join = join.join(fieldName);
        }

        return join.get(last);
    }
}
