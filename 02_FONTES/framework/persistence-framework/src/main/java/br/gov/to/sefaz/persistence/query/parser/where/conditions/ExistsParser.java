package br.gov.to.sefaz.persistence.query.parser.where.conditions;

import br.gov.to.sefaz.persistence.query.builder.ParamsBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.where.ConditionModifier;
import br.gov.to.sefaz.persistence.query.parser.QueryStructureParser;
import br.gov.to.sefaz.persistence.query.parser.domain.ParamIdGenerator;
import br.gov.to.sefaz.persistence.query.parser.domain.ResultQuery;
import br.gov.to.sefaz.persistence.query.structure.select.SelectStructure;
import br.gov.to.sefaz.persistence.query.structure.where.ConditionStructure;

/**
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 05/07/2016 15:21:00
 */
public class ExistsParser extends AbstractConditionParser {

    private final QueryStructureParser<SelectStructure> selectParser;

    public ExistsParser(QueryStructureParser<SelectStructure> selectParser) {
        this.selectParser = selectParser;
    }

    @Override
    public String parseCondition(ConditionStructure structure, int indentationLvl, ParamIdGenerator paramId,
            ParamsBuilder params) {
        String operator = "EXISTS(";
        if (structure.getModifiers().contains(ConditionModifier.NOT)) {
            operator = "NOT " + operator;
        }

        SelectStructure subselectStructure = structure.getComparision().getRight().getQuery();
        ResultQuery subselect = selectParser.parse(subselectStructure, indentationLvl + 2, paramId);
        params.put(subselect.getParams());

        return operator + subselect.getQuery() + ")";
    }
}
