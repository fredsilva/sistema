package br.gov.to.sefaz.persistence.satquery.parser.select;

import br.gov.to.sefaz.persistence.query.parser.QueryStructureParser;
import br.gov.to.sefaz.persistence.query.parser.domain.ParamIdGenerator;
import br.gov.to.sefaz.persistence.query.parser.domain.QueryLanguages;
import br.gov.to.sefaz.persistence.query.parser.domain.ResultQuery;
import br.gov.to.sefaz.persistence.query.parser.select.SelectParser;
import br.gov.to.sefaz.persistence.query.structure.domain.Alias;
import br.gov.to.sefaz.persistence.query.structure.domain.OptionalQuery;
import br.gov.to.sefaz.persistence.query.structure.domain.Value;
import br.gov.to.sefaz.persistence.query.structure.domain.ValueType;
import br.gov.to.sefaz.persistence.query.structure.select.SelectStructure;
import br.gov.to.sefaz.persistence.query.structure.select.join.JoinStructure;
import br.gov.to.sefaz.persistence.query.structure.where.Comparison;
import br.gov.to.sefaz.persistence.query.structure.where.ComparisonOperator;
import br.gov.to.sefaz.persistence.query.structure.where.ConditionStructure;
import br.gov.to.sefaz.persistence.query.structure.where.ConditionsStructure;
import br.gov.to.sefaz.persistence.satquery.parser.QualifySatQueryParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.gov.to.sefaz.persistence.query.parser.domain.QueryLanguages.SQL;
import static br.gov.to.sefaz.persistence.satquery.parser.handler.RegistroExcluidoHandler.createConditions;

/**
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
            structure.setWhere(createConditions(structure.getWhere(), tableFrom, structure.getQueryLanguage()));
        }
    }

    private void changeJoinClause(SelectStructure structure) {
        ArrayList<JoinStructure> joins = new ArrayList<>();
        int count = 0;
        for (JoinStructure join : structure.getJoins()) {
            Alias<String> joinTable = join.getTable();

            boolean ignore = false;
            if (joinTable.hasAlias()) {
                if (joinTable.getAlias().endsWith("_ignore")) {
                    Alias<String> alias = new Alias<>(joinTable.getValue(),
                            joinTable.getAlias().replace("_ignore", ""));
                    removeIgnoreFromCondition(structure, join);
                    join = new JoinStructure(alias, join.getType(), join.getOn());
                    ignore = true;
                }
            } else {
                joinTable = new Alias<>(joinTable.getValue(), "autoAliasJoin" + (++count));
            }

            if (!ignore) {
                join = changeJoinCondition(structure, join, joinTable);
            }

            joins.add(join);
        }

        structure.getJoins().clear();
        structure.addJoins(joins);
    }

    private void removeIgnoreFromCondition(SelectStructure structure, JoinStructure join) {
        if (SQL.equals(structure.getQueryLanguage()) && join.getOn().isPresent()) {
            List<ConditionStructure> conditions = join.getOn().get().getConditions();
            if (conditions.size() == 1) {
                Comparison comparision = conditions.get(0).getComparision();
                if (comparision.getOperator() == ComparisonOperator.EQUAL
                        && comparision.getRight().getValue().get(0).getType() == ValueType.COLUMN) {
                    comparision.getRight().getValue().set(0, Value.ofColumn(comparision.getRight().getValue().get(0).getColumnName().replace("_ignore", "")));
                }
            }
        }
    }

    private JoinStructure changeJoinCondition(SelectStructure structure, JoinStructure join, Alias<String> joinTable) {
        Optional<ConditionsStructure> on;

        if (QueryLanguages.HQL.equals(structure.getQueryLanguage())) {
            on = join.getOn();
        } else {
            on = Optional.of(createConditions(join.getOn(), joinTable, structure.getQueryLanguage()));
        }

        return new JoinStructure(joinTable, join.getType(), on);
    }
}
