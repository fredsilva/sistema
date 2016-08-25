package br.gov.to.sefaz.persistence.query.parser.insert;

import br.gov.to.sefaz.persistence.query.builder.ParamsBuilder;
import br.gov.to.sefaz.persistence.query.parser.QualifyQueryParser;
import br.gov.to.sefaz.persistence.query.parser.QueryStructureParser;
import br.gov.to.sefaz.persistence.query.parser.domain.ParamIdGenerator;
import br.gov.to.sefaz.persistence.query.parser.domain.QueryAppender;
import br.gov.to.sefaz.persistence.query.parser.domain.ResultQuery;
import br.gov.to.sefaz.persistence.query.structure.domain.OptionalQuery;
import br.gov.to.sefaz.persistence.query.structure.domain.Value;
import br.gov.to.sefaz.persistence.query.structure.insert.InsertStructure;
import br.gov.to.sefaz.persistence.query.structure.select.SelectStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 04/07/2016 11:29:00
 */
@Component
@QualifyQueryParser
public class InsertParser implements QueryStructureParser<InsertStructure> {

    private QueryStructureParser<SelectStructure> selectParser;

    @Autowired
    public void setSelectParser(QueryStructureParser<SelectStructure> selectParser) {
        this.selectParser = selectParser;
    }

    @Override
    public ResultQuery parse(InsertStructure structure, int indentationLvl, ParamIdGenerator paramId) {
        QueryAppender query = new QueryAppender(indentationLvl);
        ParamsBuilder params = new ParamsBuilder();

        appendInto(structure.getInto(), query);
        appendColumns(structure.getColumns(), query);
        appendValues(structure.getValues(), query, params, paramId);

        return new ResultQuery(query.toString(), params.toMap());
    }

    protected void appendInto(String into, QueryAppender query) {
        query.append("INSERT INTO ").append(into);
    }

    protected void appendColumns(List<String> columns, QueryAppender query) {
        query.appendln(1, "(")
                .append(columns.stream().collect(Collectors.joining(", ")))
                .append(")");
    }

    protected void appendValues(OptionalQuery<List<Value>> values, QueryAppender query, ParamsBuilder params, ParamIdGenerator paramId) {
        if (values.isQuery()) {
            ResultQuery subselect = selectParser.parse(values.getQuery(), query.getDefaultPad() + 1, paramId);
            query.appendln(subselect.getQuery());
            params.put(subselect.getParams());
        } else {
            query.appendln("VALUES (")
                    .append(values.getValue().stream()
                    .map(value -> parseValue(value, params, paramId))
                    .collect(Collectors.joining(", ")))
                    .append(")");
        }
    }
}
