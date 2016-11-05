package br.gov.to.sefaz.persistence.query.structure.where;

import br.gov.to.sefaz.persistence.query.builder.sql.where.ConditionModifier;
import br.gov.to.sefaz.persistence.query.structure.domain.Value;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Classe responsável por métodos de condicionais do QueryBuilder.
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 01/07/2016 10:46:00
 */
public class ConditionStructure {

    private Optional<JunctionOperator> junction;
    private final Comparison comparision;
    private final Optional<ConditionsStructure> grouped;
    private final Set<ConditionModifier> modifiers;

    public ConditionStructure(Comparison comparision, Collection<ConditionModifier> modifiers) {
        this(Optional.empty(), comparision, Optional.empty(), modifiers);
    }

    public ConditionStructure(Optional<JunctionOperator> junction, Comparison comparision,
            Collection<ConditionModifier> modifiers) {
        this(junction, comparision, Optional.empty(), modifiers);
    }

    public ConditionStructure(ConditionsStructure grouped, Collection<ConditionModifier> modifiers) {
        this(Optional.empty(), null, Optional.of(grouped), modifiers);
    }

    public ConditionStructure(Optional<JunctionOperator> junction, ConditionsStructure grouped,
            Collection<ConditionModifier> modifiers) {
        this(junction, null, Optional.of(grouped), modifiers);
    }

    private ConditionStructure(Optional<JunctionOperator> junction, Comparison comparision,
            Optional<ConditionsStructure> grouped, Collection<ConditionModifier> modifiers) {
        this.junction = junction;
        this.comparision = comparision;
        this.grouped = grouped;
        this.modifiers = modifiers.stream().collect(Collectors.toSet());
    }

    public Comparison getComparision() {
        return comparision;
    }

    public boolean isGroup() {
        return grouped.isPresent();
    }

    public ConditionsStructure getGrouped() {
        return grouped.get();
    }

    public Optional<JunctionOperator> getJunction() {
        return junction;
    }

    /**
     * Método responsável por remover uma condição de Junção.
     */
    public void removeJunction() {
        junction = Optional.empty();
    }

    /**
     * Método responsável por listar os dados da Entidade ConditionModifier.
     */
    public Set<ConditionModifier> getModifiers() {
        return modifiers;
    }

    /**
     * Método responsável por verificar se existe parâmetro vazio.
     *
     * @return retorna true ou false para a condição do parâmetro.
     */
    public boolean hasEmptyParam() {
        if (isGroup()) {
            return grouped.get().getConditions().stream().anyMatch(ConditionStructure::hasEmptyParam);
        } else {
            boolean hasAbsentParam = false;

            if (!comparision.getRight().isQuery()) {
                hasAbsentParam = comparision.getRight().getValue().stream().anyMatch(Value::isEmpty);
            }

            hasAbsentParam = hasAbsentParam || comparision.getLeft().isEmpty();

            return hasAbsentParam;
        }
    }
}
