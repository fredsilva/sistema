package br.gov.to.sefaz.persistence.query.parser;

import br.gov.to.sefaz.persistence.query.builder.ParamsBuilder;
import br.gov.to.sefaz.persistence.query.parser.domain.ParamIdGenerator;
import br.gov.to.sefaz.persistence.query.parser.domain.ResultQuery;
import br.gov.to.sefaz.persistence.query.structure.domain.Value;

/**
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 05/07/2016 13:34:00
 */
public interface QueryStructureParser<S> {

    default ResultQuery parse(S structure) {
        return parse(structure, 0, new ParamIdGenerator());
    }

    ResultQuery parse(S structure, int indentationLvl, ParamIdGenerator paramId);

    default String parseValue(Value value, ParamsBuilder params, ParamIdGenerator paramId) {
        switch (value.getType()) {
            case COLUMN:
                return value.getColumnName();
            case PARAM:
            default:
                String paramName = paramId.generate();
                params.put(paramName, value.getValue());

                return ":" + paramName;
        }
    }
}
