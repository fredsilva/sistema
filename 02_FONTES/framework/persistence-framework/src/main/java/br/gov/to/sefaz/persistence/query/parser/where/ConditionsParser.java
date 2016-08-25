package br.gov.to.sefaz.persistence.query.parser.where;

import br.gov.to.sefaz.persistence.query.builder.ParamsBuilder;
import br.gov.to.sefaz.persistence.query.parser.QualifyQueryParser;
import br.gov.to.sefaz.persistence.query.parser.QueryStructureParser;
import br.gov.to.sefaz.persistence.query.parser.domain.ParamIdGenerator;
import br.gov.to.sefaz.persistence.query.parser.domain.QueryAppender;
import br.gov.to.sefaz.persistence.query.parser.domain.ResultQuery;
import br.gov.to.sefaz.persistence.query.parser.where.conditions.AbstractConditionParser;
import br.gov.to.sefaz.persistence.query.parser.where.conditions.BetweenParser;
import br.gov.to.sefaz.persistence.query.parser.where.conditions.ExistsParser;
import br.gov.to.sefaz.persistence.query.parser.where.conditions.GroupedCondictionParser;
import br.gov.to.sefaz.persistence.query.parser.where.conditions.InParser;
import br.gov.to.sefaz.persistence.query.parser.where.conditions.IsNullParser;
import br.gov.to.sefaz.persistence.query.parser.where.conditions.LikeParser;
import br.gov.to.sefaz.persistence.query.parser.where.conditions.SimpleConditionParser;
import br.gov.to.sefaz.persistence.query.structure.select.SelectStructure;
import br.gov.to.sefaz.persistence.query.structure.where.ComparisonOperator;
import br.gov.to.sefaz.persistence.query.structure.where.ConditionStructure;
import br.gov.to.sefaz.persistence.query.structure.where.ConditionsStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static br.gov.to.sefaz.persistence.query.structure.where.ComparisonOperator.BETWEEN;
import static br.gov.to.sefaz.persistence.query.structure.where.ComparisonOperator.DIFFERENT;
import static br.gov.to.sefaz.persistence.query.structure.where.ComparisonOperator.EQUAL;
import static br.gov.to.sefaz.persistence.query.structure.where.ComparisonOperator.EXISTS;
import static br.gov.to.sefaz.persistence.query.structure.where.ComparisonOperator.GREATER;
import static br.gov.to.sefaz.persistence.query.structure.where.ComparisonOperator.GREATER_EQUAL;
import static br.gov.to.sefaz.persistence.query.structure.where.ComparisonOperator.IN;
import static br.gov.to.sefaz.persistence.query.structure.where.ComparisonOperator.IS_NULL;
import static br.gov.to.sefaz.persistence.query.structure.where.ComparisonOperator.LESS;
import static br.gov.to.sefaz.persistence.query.structure.where.ComparisonOperator.LESS_EQUAL;
import static br.gov.to.sefaz.persistence.query.structure.where.ComparisonOperator.LIKE;

/**
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 04/07/2016 16:35:00
 */
@Component
@QualifyQueryParser
public class ConditionsParser implements QueryStructureParser<ConditionsStructure> {

    private final Map<ComparisonOperator, AbstractConditionParser> conditionsParsers;
    private final GroupedCondictionParser groupedParser;

    public ConditionsParser() {
        this.groupedParser = new GroupedCondictionParser(this);
        this.conditionsParsers = new HashMap<>();
    }

    @Autowired
    @QualifyQueryParser
    public void setSelectParser(QueryStructureParser<SelectStructure> selectParser) {
        this.conditionsParsers.put(EXISTS, new ExistsParser(selectParser));
        this.conditionsParsers.put(IN, new InParser(selectParser));
        this.conditionsParsers.put(BETWEEN, new BetweenParser());
        this.conditionsParsers.put(IS_NULL, new IsNullParser());
        this.conditionsParsers.put(LIKE, new LikeParser());
        this.conditionsParsers.put(LESS, new SimpleConditionParser("<"));
        this.conditionsParsers.put(LESS_EQUAL, new SimpleConditionParser("<="));
        this.conditionsParsers.put(GREATER, new SimpleConditionParser(">"));
        this.conditionsParsers.put(GREATER_EQUAL, new SimpleConditionParser(">="));
        this.conditionsParsers.put(DIFFERENT, new SimpleConditionParser("<>"));
        this.conditionsParsers.put(EQUAL, new SimpleConditionParser("="));
    }

    @Override
    public ResultQuery parse(ConditionsStructure structure, int pad, ParamIdGenerator paramId) {
        QueryAppender query = new QueryAppender(pad);
        ParamsBuilder params = new ParamsBuilder();

        boolean isFirst = true;
        for (ConditionStructure condition : structure.getConditions()) {
            if (isFirst) {
                condition.removeJunction();
            }

            if (appendCondiction(condition, query, params, paramId)) {
                isFirst = false;
            }
        }

        return new ResultQuery(query.toString(), params.toMap());
    }

    private boolean appendCondiction(ConditionStructure condition, QueryAppender query, ParamsBuilder params,
            ParamIdGenerator idGenerator) {
        ResultQuery conditionResultQuery;

        if (condition.isGroup()) {
            conditionResultQuery = groupedParser.parse(condition, query.getDefaultPad(), idGenerator);
        } else {
            AbstractConditionParser abstractConditionParser = conditionsParsers
                    .get(condition.getComparision().getOperator());
            conditionResultQuery = abstractConditionParser.parse(condition, query.getDefaultPad(), idGenerator);
        }

        query.append(conditionResultQuery.getQuery());
        params.put(conditionResultQuery.getParams());

        return !conditionResultQuery.getQuery().isEmpty();
    }
}
