package br.gov.to.sefaz.persistence.satquery.parser.select;

import br.gov.to.sefaz.persistence.query.parser.QueryStructureParser;
import br.gov.to.sefaz.persistence.query.parser.domain.ParamIdGenerator;
import br.gov.to.sefaz.persistence.query.parser.domain.QueryLanguages;
import br.gov.to.sefaz.persistence.query.parser.domain.ResultQuery;
import br.gov.to.sefaz.persistence.query.parser.select.SelectParser;
import br.gov.to.sefaz.persistence.query.structure.domain.Alias;
import br.gov.to.sefaz.persistence.query.structure.domain.OptionalQuery;
import br.gov.to.sefaz.persistence.query.structure.select.SelectStructure;
import br.gov.to.sefaz.persistence.query.structure.select.join.JoinStructure;
import br.gov.to.sefaz.persistence.query.structure.where.ConditionsStructure;
import br.gov.to.sefaz.persistence.satquery.parser.QualifySatQueryParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

import static br.gov.to.sefaz.persistence.satquery.parser.handler.RegistroExcluidoHandler.createConditions;

/**
 * Implementação custom de um {@link SelectParser} para regras especificas do projeto SAT,
 * visando colunas de auditoria.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 04/07/2016 11:29:00
 */
@Component
@QualifySatQueryParser
public class SatSelectParser extends SelectParser {

    @Override
    @Autowired
    @QualifySatQueryParser
    public void setConditionsParser(QueryStructureParser<ConditionsStructure> conditionsParser) {
        super.setConditionsParser(conditionsParser);
    }

    @Override
    public ResultQuery parse(SelectStructure structure, int indentationLvl, ParamIdGenerator paramId) {
        changeWhereClause(structure);
        changeJoinClause(structure);

        return super.parse(structure, indentationLvl, paramId);
    }

    private void changeWhereClause(SelectStructure structure) {
        Alias<OptionalQuery<String>> from = structure.getFrom();

        if (!from.getValue().isQuery()) {
            Alias<String> tableFrom = new Alias<>(from.getValue().getValue(), from.getAlias());
            createConditions(structure.getWhere(), tableFrom, structure.getQueryLanguage())
                    .ifPresent(structure::setWhere);
        }
    }

    private void changeJoinClause(SelectStructure structure) {
        ArrayList<JoinStructure> joins = new ArrayList<>();
        int count = 0;
        for (JoinStructure join : structure.getJoins()) {
            Alias<String> joinTable = join.getTable();

            if (!joinTable.hasAlias()) {
                joinTable = new Alias<>(joinTable.getValue(), "autoAliasJoin" + (++count));
            }

            join = changeJoinCondition(structure, join, joinTable);

            joins.add(join);
        }

        structure.getJoins().clear();
        structure.addJoins(joins);
    }

    private JoinStructure changeJoinCondition(SelectStructure structure, JoinStructure join, Alias<String> joinTable) {
        Optional<ConditionsStructure> on;

        // as clausulas ON do hql são alteradas direto no dialeto (SatParseDialect)
        if (QueryLanguages.HQL.equals(structure.getQueryLanguage())) {
            on = join.getOn();
        } else {
            on = createConditions(join.getOn(), joinTable, structure.getQueryLanguage());
        }

        return new JoinStructure(joinTable, join.getType(), on);
    }
}
