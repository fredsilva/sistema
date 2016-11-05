package br.gov.to.sefaz.persistence.query.parser.select;

import br.gov.to.sefaz.persistence.query.builder.ParamsBuilder;
import br.gov.to.sefaz.persistence.query.parser.QualifyQueryParser;
import br.gov.to.sefaz.persistence.query.parser.QueryStructureParser;
import br.gov.to.sefaz.persistence.query.parser.domain.ParamIdGenerator;
import br.gov.to.sefaz.persistence.query.parser.domain.QueryAppender;
import br.gov.to.sefaz.persistence.query.parser.domain.ResultQuery;
import br.gov.to.sefaz.persistence.query.structure.domain.Alias;
import br.gov.to.sefaz.persistence.query.structure.domain.OptionalQuery;
import br.gov.to.sefaz.persistence.query.structure.select.SelectStructure;
import br.gov.to.sefaz.persistence.query.structure.select.groupby.GroupByStructure;
import br.gov.to.sefaz.persistence.query.structure.select.join.JoinStructure;
import br.gov.to.sefaz.persistence.query.structure.select.orderby.OrderByStructure;
import br.gov.to.sefaz.persistence.query.structure.where.ConditionsStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Classe que representa a estrutura do componente Parse para o comando Select.
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 04/07/2016 11:29:00
 */
@Component
@QualifyQueryParser
public class SelectParser implements QueryStructureParser<SelectStructure> {

    private QueryStructureParser<ConditionsStructure> conditionsParser;

    @Autowired
    @QualifyQueryParser
    public void setConditionsParser(QueryStructureParser<ConditionsStructure> conditionsParser) {
        this.conditionsParser = conditionsParser;
    }

    @Override
    public ResultQuery parse(SelectStructure structure, int indentationLvl, ParamIdGenerator paramId) {
        QueryAppender query = new QueryAppender(indentationLvl);
        ParamsBuilder params = new ParamsBuilder();

        appendColumns(structure.getColumns(), query);
        appendFrom(structure.getFrom(), query, params, paramId);
        appendJoins(structure.getJoins(), query, params, paramId);
        appendWhere(structure.getWhere(), query, params, paramId);
        appendGroupBy(structure.getGroupBy(), structure.getColumns(), query, params, paramId);
        appendOrderBy(structure.getOrderBy(), query, params, paramId);

        return new ResultQuery(query.toString(), params.toMap());
    }

    protected void appendColumns(List<Alias<String>> columns, QueryAppender query) {
        if (!columns.isEmpty()) {
            query.append("SELECT ")
                    .appendln(1, columns.stream()
                    .map(c -> c.getValue() + c.getIfAlias(a -> " AS " + a))
                    .collect(Collectors.toList()), ",");
        }
    }

    protected void appendFrom(Alias<OptionalQuery<String>> from, QueryAppender query,
                              ParamsBuilder params, ParamIdGenerator paramId) {
        query.appendln("FROM ");

        if (from.getValue().isQuery()) {
            ResultQuery subselect = parse(from.getValue().getQuery(), 1, paramId);

            query.append("(").appendln(1, subselect.getQuery()).appendln(")");

            params.put(subselect.getParams());
        } else {
            query.append(from.getValue().getValue());
        }

        query.append(from.getIfAlias(s -> " " + s));
    }

    protected void appendJoins(List<JoinStructure> joins, QueryAppender query, ParamsBuilder params,
                               ParamIdGenerator paramId) {
        joins.forEach(join -> {
            switch (join.getType()) {
                case LEFT:
                    query.appendln("LEFT ");
                    break;
                case RIGHT:
                    query.appendln("RIGHT ");
                    break;
                case FULL:
                    query.appendln("FULL ");
                    break;
                case INNER:
                    query.appendln("INNER ");
                    break;
                default:
                    throw new IllegalArgumentException("Unsuported join type");
            }

            Alias<String> table = join.getTable();
            query.append("JOIN ")
                    .append(table.getValue())
                    .append(table.getIfAlias(s -> " " + s));
            Optional<ConditionsStructure> on = join.getOn();

            if (on.isPresent()) {
                ResultQuery onCondition = conditionsParser.parse(on.get(), query.getDefaultPad() + 1, paramId);
                params.put(onCondition.getParams());

                query.appendln(1, "ON ").append(onCondition.getQuery());
            }
        });
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

    protected void appendGroupBy(Optional<GroupByStructure> groupBy, List<Alias<String>> selectColumns,
                                 QueryAppender query, ParamsBuilder params, ParamIdGenerator paramId) {
        if (groupBy.isPresent()) {
            GroupByStructure structure = groupBy.get();
            query.appendln("GROUP BY ");
            if (structure.getColumns().isEmpty() && !selectColumns.isEmpty()) {
                query.appendln(1, selectColumns.stream()
                        .map(Alias::getValue)
                        .filter(s -> s.matches("[a-zA-Z][\\w\\$#]*"))
                        .collect(Collectors.toList()), ",");
            } else {
                query.appendln(1, structure.getColumns().stream()
                        .collect(Collectors.joining(", ")));
            }

            Optional<ConditionsStructure> having = structure.getHaving();
            if (having.isPresent()) {
                ResultQuery conditions = conditionsParser.parse(having.get(), query.getDefaultPad(), paramId);
                params.put(conditions.getParams());

                if (!conditions.getQuery().isEmpty()) {
                    query.appendln("HAVING ")
                            .append(conditions.getQuery());
                }
            }
        }
    }

    protected void appendOrderBy(Optional<OrderByStructure> orderBy, QueryAppender query,
                                 ParamsBuilder params, ParamIdGenerator paramId) {
        if (orderBy.isPresent()) {
            query.appendln("ORDER BY ");

            String orders = orderBy.get().getOrders().entrySet().stream()
                    .map(e -> e.getKey() + " " + e.getValue().name())
                    .collect(Collectors.joining(", "));

            query.append(orders);
        }
    }
}
