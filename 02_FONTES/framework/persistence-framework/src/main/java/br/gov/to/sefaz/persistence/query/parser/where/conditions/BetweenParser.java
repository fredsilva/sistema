package br.gov.to.sefaz.persistence.query.parser.where.conditions;

import br.gov.to.sefaz.persistence.query.builder.ParamsBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.where.ConditionModifier;
import br.gov.to.sefaz.persistence.query.parser.domain.ParamIdGenerator;
import br.gov.to.sefaz.persistence.query.structure.domain.Value;
import br.gov.to.sefaz.persistence.query.structure.where.ConditionStructure;

import java.util.List;

/**
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 05/07/2016 15:23:00
 */
public class BetweenParser extends AbstractConditionParser {

    @Override
    public String parseCondition(ConditionStructure structure, int indentationLvl, ParamIdGenerator paramId,
            ParamsBuilder params) {
        String leftValue = parseValue(structure.getComparision().getLeft(), params, paramId);
        String operator = " BETWEEN ";
        if (structure.getModifiers().contains(ConditionModifier.NOT)) {
            operator = " NOT" + operator;
        }

        List<Value> rightValues = structure.getComparision().getRight().getValue();
        String rightStart = parseValue(rightValues.get(0), params, paramId);
        String rightEnd = parseValue(rightValues.get(1), params, paramId);

        return leftValue + operator + rightStart + " AND " + rightEnd;
    }
}
