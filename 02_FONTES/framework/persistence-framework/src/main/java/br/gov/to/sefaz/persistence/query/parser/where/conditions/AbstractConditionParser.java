package br.gov.to.sefaz.persistence.query.parser.where.conditions;

import br.gov.to.sefaz.persistence.query.builder.ParamsBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.where.ConditionModifier;
import br.gov.to.sefaz.persistence.query.parser.QueryStructureParser;
import br.gov.to.sefaz.persistence.query.parser.domain.ParamIdGenerator;
import br.gov.to.sefaz.persistence.query.parser.domain.QueryAppender;
import br.gov.to.sefaz.persistence.query.parser.domain.ResultQuery;
import br.gov.to.sefaz.persistence.query.structure.where.ConditionStructure;
import br.gov.to.sefaz.persistence.query.structure.where.JunctionOperator;

import java.util.Optional;

/**
 * Classe abstrata responsável por métodos que estrutura uma Query.
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 05/07/2016 18:07:00
 */
public abstract class AbstractConditionParser implements QueryStructureParser<ConditionStructure> {

    @Override
    public ResultQuery parse(ConditionStructure structure, int indentationLvl, ParamIdGenerator paramId) {
        if (structure.getModifiers().contains(ConditionModifier.OPTIONAL) && structure.hasEmptyParam()) {
            return new ResultQuery("", ParamsBuilder.empty().toMap());
        }

        ParamsBuilder params = ParamsBuilder.empty();
        QueryAppender query = new QueryAppender(indentationLvl);
        Optional<JunctionOperator> junction = structure.getJunction();
        if (junction.isPresent()) {
            query.appendln(1, junction.get().name()).append(" ");
        }
        query.append(parseCondition(structure, indentationLvl, paramId, params));

        return new ResultQuery(query.toString(), params.toMap());
    }

    /**
     * Método abstrato responsável por realizar Parse Condition contêm parâmetros
     * <code>structure</code> e <code>order</code> e <code>order</code> e <code>order</code>.
     *
     * @param structure informa a estrutura da condicional.
     * @param indentationLvl configura a indentação da sentença.
     * @param paramId informa a chave do Parâmetro.
     * @param params informa o valor dos Parâmetros.
     *
     * @return adiciona uma coluna e o sentido da ordenação.
     */
    public abstract String parseCondition(ConditionStructure structure, int indentationLvl, ParamIdGenerator paramId,
            ParamsBuilder params);
}
