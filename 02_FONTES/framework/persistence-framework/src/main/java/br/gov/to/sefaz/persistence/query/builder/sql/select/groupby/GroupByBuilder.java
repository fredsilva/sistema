package br.gov.to.sefaz.persistence.query.builder.sql.select.groupby;

import br.gov.to.sefaz.persistence.query.builder.QueryStructureBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.select.SelectBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.select.orderby.OrderByBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.select.signature.Orderable;
import br.gov.to.sefaz.persistence.query.structure.select.groupby.GroupByStructure;
import br.gov.to.sefaz.persistence.query.structure.select.orderby.Order;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Classe responsável por conter os métodos da operação Group By do QueryBuilder.
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 30/06/2016 16:52:00
 */
public class GroupByBuilder implements QueryStructureBuilder<SelectBuilder, GroupByStructure>,
        Orderable<OrderByBuilder> {

    private final SelectBuilder root;
    private final List<String> columnsList;
    private Optional<HavingBuilder> havingBuilder;

    public GroupByBuilder(SelectBuilder root, String... columns) {
        this.root = root;
        this.columnsList = Arrays.stream(columns).collect(Collectors.toList());
        this.havingBuilder = Optional.empty();
    }

    /**
     * Método responsável por executar o comando Having do HQL informa os parâmetros
     * para a operação HAVING.
     *
     * @return retornar a montagem da execução do comando HAVING do HQL.
     */
    public HavingBuilder having() {
        if (!havingBuilder.isPresent()) {
            havingBuilder = Optional.of(new HavingBuilder(getRoot()));
        }

        return havingBuilder.get();
    }

    @Override
    public OrderByBuilder orderBy(String field, Order order) {
        return root.orderBy(field, order);
    }

    @Override
    public OrderByBuilder orderBy(String field, String... andFields) {
        return getRoot().orderBy(field, andFields);
    }

    @Override
    public SelectBuilder getRoot() {
        return root.getRoot();
    }

    @Override
    public GroupByStructure build() {
        if (havingBuilder.isPresent()) {
            return new GroupByStructure(columnsList, havingBuilder.get().build());
        }

        return new GroupByStructure(columnsList);
    }
}
