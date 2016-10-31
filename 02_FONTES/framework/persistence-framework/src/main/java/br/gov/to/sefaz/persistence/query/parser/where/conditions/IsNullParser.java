package br.gov.to.sefaz.persistence.query.parser.where.conditions;

import br.gov.to.sefaz.persistence.query.builder.ParamsBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.where.ConditionModifier;
import br.gov.to.sefaz.persistence.query.parser.domain.ParamIdGenerator;
import br.gov.to.sefaz.persistence.query.structure.where.ConditionStructure;

/**
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 05/07/2016 15:24:00
 */
public class IsNullParser extends AbstractConditionParser {

    @Override
    public String parseCondition(ConditionStructure structure, int indentationLvl, ParamIdGenerator paramId,
            ParamsBuilder params) {
        String leftValue = parseValue(structure.getComparision().getLeft(), params, paramId);

        String operator = " IS";
        if (structure.getModifiers().contains(ConditionModifier.NOT)) {
            operator += " NOT";
        }
        operator += " NULL";

        return leftValue + operator;
    }
}
