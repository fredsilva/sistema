package br.gov.to.sefaz.persistence.satquery.parser.handler;

import br.gov.to.sefaz.persistence.configuration.AuditableTablesIdentifier;
import br.gov.to.sefaz.persistence.entity.AbstractEntity;
import br.gov.to.sefaz.persistence.query.parser.domain.QueryLanguages;
import br.gov.to.sefaz.persistence.query.structure.domain.Alias;
import br.gov.to.sefaz.persistence.query.structure.domain.Value;
import br.gov.to.sefaz.persistence.query.structure.where.Comparison;
import br.gov.to.sefaz.persistence.query.structure.where.ComparisonOperator;
import br.gov.to.sefaz.persistence.query.structure.where.ConditionStructure;
import br.gov.to.sefaz.persistence.query.structure.where.ConditionsStructure;
import br.gov.to.sefaz.persistence.query.structure.where.JunctionOperator;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Classe utilitaria para manipulação de consultas que envolvam a coluna de auditoria, registro_excluido.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 19/07/2016 09:36:00
 */
public class RegistroExcluidoHandler {

    /**
     * Cria uma condição where que contenha as condições originais (se tiver) e de "registro_excluido = N". Caso a
     * tabela não seja uma tabela com colunas de auditoria ele irá retornar as condições originais passadas por
     * parametro.
     *
     * @see AuditableTablesIdentifier
     * @param original condições originais na consulta
     * @param table nome e alias da tabela envolvida
     * @param queryLanguage tipo de consulta
     * @return Condição where alterada para colunas de auditoria, ou a original caso não seja de auditoria
     */
    public static Optional<ConditionsStructure> createConditions(Optional<ConditionsStructure> original,
            Alias<String> table, String queryLanguage) {
        if (AuditableTablesIdentifier.isAuditable(table.getValue())) {
            ConditionsStructure conditions = new ConditionsStructure();

            if (original.isPresent()) {
                conditions.addCondition(new ConditionStructure(original.get(), new ArrayList<>()));
            }

            conditions.addCondition(createCondition(table, queryLanguage));

            return Optional.of(conditions);
        }

        return original;
    }

    public static String getRegistroExcluidoColumn(String queryLanguage) {
        if (QueryLanguages.HQL.equals(queryLanguage)) {
            return "registroExcluido";
        }

        return "registro_excluido";
    }

    public static String getDataExclusaoColumn(String queryLanguage) {
        if (QueryLanguages.HQL.equals(queryLanguage)) {
            return "dataExclusao";
        }

        return "data_exclusao";
    }

    public static String getUsuarioExclusaoColumn(String queryLanguage) {
        if (QueryLanguages.HQL.equals(queryLanguage)) {
            return "usuarioExclusao";
        }

        return "usuario_exclusao";
    }

    private static ConditionStructure createCondition(Alias<String> table, String queryLanguage) {
        Optional<JunctionOperator> and = Optional.of(JunctionOperator.AND);
        ConditionsStructure conditionsStructure = new ConditionsStructure();

        String column = getRegistroExcluidoColumn(queryLanguage);

        Value registroExcluido;
        if (table.hasAlias()) {
            registroExcluido = Value.ofColumn(table.getAlias() + "." + column);
        } else {
            registroExcluido = Value.ofColumn(column);
        }

        conditionsStructure.addCondition(new ConditionStructure(Optional.of(JunctionOperator.OR),
                new Comparison(registroExcluido, ComparisonOperator.EQUAL, Value.ofParam(AbstractEntity.NAO)),
                new ArrayList<>()));

        return new ConditionStructure(and, conditionsStructure, new ArrayList<>());
    }
}
