package br.gov.to.sefaz.persistence.query.parser.delete;

import br.gov.to.sefaz.persistence.query.builder.ParamsBuilder;
import br.gov.to.sefaz.persistence.query.parser.QualifyQueryParser;
import br.gov.to.sefaz.persistence.query.parser.QueryStructureParser;
import br.gov.to.sefaz.persistence.query.parser.domain.ParamIdGenerator;
import br.gov.to.sefaz.persistence.query.parser.domain.QueryAppender;
import br.gov.to.sefaz.persistence.query.parser.domain.ResultQuery;
import br.gov.to.sefaz.persistence.query.structure.delete.DeleteStructure;
import br.gov.to.sefaz.persistence.query.structure.domain.Alias;
import br.gov.to.sefaz.persistence.query.structure.where.ConditionsStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Classe responsável por gerenciar o método Parse no comando Delete.
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 04/07/2016 11:29:00
 */
@Component
@QualifyQueryParser
public class DeleteParser implements QueryStructureParser<DeleteStructure> {

    private QueryStructureParser<ConditionsStructure> conditionsParser;

    @Autowired
    @QualifyQueryParser
    public void setConditionsParser(QueryStructureParser<ConditionsStructure> conditionsParser) {
        this.conditionsParser = conditionsParser;
    }

    @Override
    public ResultQuery parse(DeleteStructure structure, int indentationLvl, ParamIdGenerator paramId) {
        QueryAppender query = new QueryAppender(indentationLvl);
        ParamsBuilder params = new ParamsBuilder();

        appendUpdate(structure.getFrom(), query);
        appendWhere(structure.getWhere(), query, params, paramId);

        return new ResultQuery(query.toString(), params.toMap());
    }

    protected void appendUpdate(Alias<String> from, QueryAppender query) {
        query.append("DELETE FROM ").append(from.getValue()).append(from.getIfAlias(a -> " " + a));
    }

    protected void appendWhere(Optional<ConditionsStructure> where, QueryAppender query, ParamsBuilder params,
                               ParamIdGenerator paramId) {
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
