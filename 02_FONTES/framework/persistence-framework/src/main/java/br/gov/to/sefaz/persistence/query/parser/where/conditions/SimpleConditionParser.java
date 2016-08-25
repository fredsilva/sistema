package br.gov.to.sefaz.persistence.query.parser.where.conditions;

import br.gov.to.sefaz.persistence.query.builder.ParamsBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.where.ConditionModifier;
import br.gov.to.sefaz.persistence.query.parser.domain.ParamIdGenerator;
import br.gov.to.sefaz.persistence.query.structure.where.ConditionStructure;

/**
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 05/07/2016 14:39:00
 */
public class SimpleConditionParser extends AbstractConditionParser {

    private final String operator;

    public SimpleConditionParser(String operator) {
        this.operator = operator;
    }

    @Override
    public String parseCondition(ConditionStructure structure, int indentationLvl, ParamIdGenerator paramId, ParamsBuilder params) {
        String leftValue = parseValue(structure.getComparision().getLeft(), params, paramId);
        String rightValue = parseValue(structure.getComparision().getRight().getValue().get(0), params, paramId);
        String comparison = leftValue + " " + operator + " " + rightValue;

        if (structure.getModifiers().contains(ConditionModifier.NOT)) {
            comparison = "NOT(" + comparison + ")";
        }

        return comparison;
    }
}
