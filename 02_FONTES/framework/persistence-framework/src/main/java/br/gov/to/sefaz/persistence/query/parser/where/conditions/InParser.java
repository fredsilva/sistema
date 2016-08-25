package br.gov.to.sefaz.persistence.query.parser.where.conditions;

import br.gov.to.sefaz.persistence.query.builder.ParamsBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.where.ConditionModifier;
import br.gov.to.sefaz.persistence.query.parser.QueryStructureParser;
import br.gov.to.sefaz.persistence.query.parser.domain.ParamIdGenerator;
import br.gov.to.sefaz.persistence.query.parser.domain.ResultQuery;
import br.gov.to.sefaz.persistence.query.structure.domain.OptionalQuery;
import br.gov.to.sefaz.persistence.query.structure.domain.Value;
import br.gov.to.sefaz.persistence.query.structure.select.SelectStructure;
import br.gov.to.sefaz.persistence.query.structure.where.ConditionStructure;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 05/07/2016 15:22:00
 */
public class InParser extends AbstractConditionParser {

    private final QueryStructureParser<SelectStructure> selectParser;

    public InParser(QueryStructureParser<SelectStructure> selectParser) {
        this.selectParser = selectParser;
    }

    @Override
    public String parseCondition(ConditionStructure structure, int indentationLvl, ParamIdGenerator paramId,
            ParamsBuilder params) {
        String leftValue = parseValue(structure.getComparision().getLeft(), params, paramId);

        OptionalQuery<List<Value>> rightValues = structure.getComparision().getRight();
        String queryValues;
        if (rightValues.isQuery()) {
            ResultQuery subselect = selectParser.parse(rightValues.getQuery(), indentationLvl + 2, paramId);
            params.put(subselect.getParams());

            queryValues = subselect.getQuery();
        } else {
            queryValues = rightValues.getValue().stream()
                    .map(value -> parseValue(value, params, paramId))
                    .collect(Collectors.joining(", "));
        }

        String operator = " IN (";
        if (structure.getModifiers().contains(ConditionModifier.NOT)) {
            operator = " NOT" + operator;
        }

        return leftValue + operator + queryValues + ")";
    }
}
