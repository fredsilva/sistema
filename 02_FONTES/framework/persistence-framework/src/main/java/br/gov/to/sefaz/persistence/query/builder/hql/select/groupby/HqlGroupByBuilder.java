package br.gov.to.sefaz.persistence.query.builder.hql.select.groupby;

import br.gov.to.sefaz.persistence.query.builder.QueryStructureBuilder;
import br.gov.to.sefaz.persistence.query.builder.hql.select.HqlSelectBuilder;
import br.gov.to.sefaz.persistence.query.builder.hql.select.orderby.HqlOrderByBuilder;
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
public class HqlGroupByBuilder implements QueryStructureBuilder<HqlSelectBuilder, GroupByStructure> {

    private final HqlSelectBuilder root;
    private final List<String> columnsList;
    private Optional<HqlHavingBuilder> havingBuilder;

    public HqlGroupByBuilder(HqlSelectBuilder root, String... columns) {
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
    public HqlHavingBuilder having() {
        if (!havingBuilder.isPresent()) {
            havingBuilder = Optional.of(new HqlHavingBuilder(getRoot()));
        }

        return havingBuilder.get();
    }

    /**
     * Método responsável por executar o comando ORDER BY do HQL informa os parâmetros
     * <code>field</code> e <code>order</code> para a operação ORDER BY.
     *
     * @param field informa a campo do parâmetro.
     * @param order informa o comando de ordenação(ASC/DESC).
     *
     * @return retornar a montagem da execução do comando ORDER BY do HQL.
     */
    public HqlOrderByBuilder orderBy(String field, Order order) {
        return getRoot().orderBy(field, order);
    }

    /**
     * Método responsável por executar o comando ORDER BY do HQL informa os parâmetros
     * <code>field</code> e <code>andFields</code> para a operação ORDER BY.
     *
     * @param field informa a campo do parâmetro.
     * @param andFields informa uma coleção de parâmetros.
     *
     * @return retornar a montagem da execução do comando ORDER BY do HQL.
     */
    public HqlOrderByBuilder orderBy(String field, String... andFields) {
        return getRoot().orderBy(field, andFields);
    }

    @Override
    public HqlSelectBuilder getRoot() {
        return root;
    }

    @Override
    public GroupByStructure build() {
        if (havingBuilder.isPresent()) {
            return new GroupByStructure(columnsList, havingBuilder.get().build());
        }

        return new GroupByStructure(columnsList);
    }
}
