package br.gov.to.sefaz.persistence.query.parser;

import br.gov.to.sefaz.persistence.query.builder.ParamsBuilder;
import br.gov.to.sefaz.persistence.query.parser.domain.ParamIdGenerator;
import br.gov.to.sefaz.persistence.query.parser.domain.ResultQuery;
import br.gov.to.sefaz.persistence.query.structure.domain.Value;

/**
 * Interface de assinatura resposável por métodos de parse da Estrutura da Query.
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 05/07/2016 13:34:00
 * @param <S> Entidade base.
 */
public interface QueryStructureParser<S> {

    /** Método default que realiza o parse.
     *
     * @param structure informa o valor da estrutura.
     *
     * @return retornar o valor do parse.
     */
    default ResultQuery parse(S structure) {
        return parse(structure, 0, new ParamIdGenerator());
    }

    /**
     * Método responsável que realiza o parse.
     *
     * @param structure informa o valor da estrutura.
     * @param indentationLvl informa o valor da indentação.
     * @param paramId informa o id do param.
     *
     * @return retornar o valor do parse.
     */
    ResultQuery parse(S structure, int indentationLvl, ParamIdGenerator paramId);

    /**
     * Método default que realiza o parse.
     *
     * @param params informa um valor do parâmetroS.
     * @param value informa o valor do parâmetro.
     * @param paramId informa o id do param.
     *
     * @return retornar o valor do parâmetro noS parse.
     */
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
