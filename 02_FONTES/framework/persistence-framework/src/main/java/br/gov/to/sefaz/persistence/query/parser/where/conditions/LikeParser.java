package br.gov.to.sefaz.persistence.query.parser.where.conditions;

import br.gov.to.sefaz.persistence.query.builder.ParamsBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.where.ConditionModifier;
import br.gov.to.sefaz.persistence.query.parser.domain.ParamIdGenerator;
import br.gov.to.sefaz.persistence.query.structure.where.ConditionStructure;

/**
 * Classe responsável por gerenciar o comando Parse na operação Like.
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 05/07/2016 16:29:00
 */
public class LikeParser extends AbstractConditionParser {

    @Override
    public String parseCondition(ConditionStructure structure, int indentationLvl, ParamIdGenerator paramId,
                                 ParamsBuilder params) {
        String leftValue = parseValue(structure.getComparision().getLeft(), params, paramId);
        String rightValue = parseValue(structure.getComparision().getRight().getValue().get(0), params, paramId);

        String operator = " LIKE ";
        if (structure.getModifiers().contains(ConditionModifier.NOT)) {
            operator = " NOT" + operator;
        }

        return leftValue + operator + rightValue;
    }
}
