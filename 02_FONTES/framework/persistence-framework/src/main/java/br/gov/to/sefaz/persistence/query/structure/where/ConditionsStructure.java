package br.gov.to.sefaz.persistence.query.structure.where;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por métodos de operações condicionais do QueryBuilder.
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 29/06/2016 10:49:00
 */
public class ConditionsStructure {

    private final List<ConditionStructure> conditions;

    public ConditionsStructure() {
        conditions = new ArrayList<>();
    }

    public ConditionsStructure(ConditionStructure condition) {
        this();
        conditions.add(condition);
    }

    /**
     * Método responsável por adicionar variáveis condicionais.
     *
     * @param condition variável da entidade ConditionStructure.
     *
     */
    public void addCondition(ConditionStructure condition) {
        conditions.add(condition);
    }

    public List<ConditionStructure> getConditions() {
        return conditions;
    }
}
