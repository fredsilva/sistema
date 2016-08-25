package br.gov.to.sefaz.persistence.query.parser.where.conditions;

import br.gov.to.sefaz.persistence.query.builder.ParamsBuilder;
import br.gov.to.sefaz.persistence.query.parser.domain.ParamIdGenerator;
import br.gov.to.sefaz.persistence.query.parser.domain.QueryAppender;
import br.gov.to.sefaz.persistence.query.parser.domain.ResultQuery;
import br.gov.to.sefaz.persistence.query.parser.where.ConditionsParser;
import br.gov.to.sefaz.persistence.query.structure.where.ConditionStructure;

/**
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 05/07/2016 16:52:00
 */
public class GroupedCondictionParser extends AbstractConditionParser {

    private final ConditionsParser whereParser;

    public GroupedCondictionParser(ConditionsParser whereParser) {
        this.whereParser = whereParser;
    }

    @Override
    public String parseCondition(ConditionStructure structure, int indentationLvl, ParamIdGenerator paramId,
            ParamsBuilder params) {
        ResultQuery grouped = whereParser.parse(structure.getGrouped(), indentationLvl + 1, paramId);
        params.put(grouped.getParams());

        if (grouped.getQuery().isEmpty()) {
            return "";
        }

        QueryAppender appender = new QueryAppender(indentationLvl + 1);
        appender.append("(")
                .appendln(1, grouped.getQuery())
                .appendln(")");

        return appender.toString();
    }
}
