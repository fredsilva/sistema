package br.gov.to.sefaz.persistence.query.parser.update;

import br.gov.to.sefaz.persistence.query.builder.ParamsBuilder;
import br.gov.to.sefaz.persistence.query.parser.QualifyQueryParser;
import br.gov.to.sefaz.persistence.query.parser.QueryStructureParser;
import br.gov.to.sefaz.persistence.query.parser.domain.ParamIdGenerator;
import br.gov.to.sefaz.persistence.query.parser.domain.QueryAppender;
import br.gov.to.sefaz.persistence.query.parser.domain.ResultQuery;
import br.gov.to.sefaz.persistence.query.structure.domain.Alias;
import br.gov.to.sefaz.persistence.query.structure.domain.Value;
import br.gov.to.sefaz.persistence.query.structure.update.UpdateStructure;
import br.gov.to.sefaz.persistence.query.structure.where.ConditionsStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 04/07/2016 11:29:00
 */
@Component
@QualifyQueryParser
public class UpdateParser implements QueryStructureParser<UpdateStructure> {

    private QueryStructureParser<ConditionsStructure> conditionsParser;

    @Autowired
    @QualifyQueryParser
    public void setConditionsParser(QueryStructureParser<ConditionsStructure> conditionsParser) {
        this.conditionsParser = conditionsParser;
    }

    @Override
    public ResultQuery parse(UpdateStructure structure, int indentationLvl, ParamIdGenerator paramId) {
        QueryAppender query = new QueryAppender(indentationLvl);
        ParamsBuilder params = new ParamsBuilder();

        appendUpdate(structure.getFrom(), query);
        appendColumns(structure.getSets(), query, params, paramId);
        appendWhere(structure.getWhere(), query, params, paramId);

        return new ResultQuery(query.toString(), params.toMap());
    }

    protected void appendUpdate(Alias<String> from, QueryAppender query) {
        query.append("UPDATE ").append(from.getValue()).append(from.getIfAlias(a -> " " + a));
    }

    protected void appendColumns(Map<String, Value> sets, QueryAppender query, ParamsBuilder params, ParamIdGenerator paramId) {
        query.appendln("SET ");
        query.appendln(1, sets.entrySet().stream()
                .map(e -> e.getKey() + "=" + parseValue(e.getValue(), params, paramId))
                .collect(Collectors.toList()), ",");
    }

    protected void appendWhere(Optional<ConditionsStructure> where, QueryAppender query, ParamsBuilder params, ParamIdGenerator paramId) {
        if (where.isPresent()) {
            ResultQuery conditions = conditionsParser.parse(where.get(), query.getDefaultPad(), paramId);
            params.put(conditions.getParams());

            String conditionsQuery = conditions.getQuery();
            if (!conditionsQuery.isEmpty()) {
                query.appendln("WHERE ")
                        .append(conditionsQuery);
            }
        }
    }
}
