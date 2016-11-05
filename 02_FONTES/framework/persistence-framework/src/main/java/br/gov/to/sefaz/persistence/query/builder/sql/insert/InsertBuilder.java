package br.gov.to.sefaz.persistence.query.builder.sql.insert;

import br.gov.to.sefaz.persistence.query.builder.QueryStructureBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.select.SelectBuilder;
import br.gov.to.sefaz.persistence.query.parser.domain.QueryLanguages;
import br.gov.to.sefaz.persistence.query.structure.domain.OptionalQuery;
import br.gov.to.sefaz.persistence.query.structure.domain.Value;
import br.gov.to.sefaz.persistence.query.structure.insert.InsertStructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe responsável por conter os métodos da operação Insert do QueryBuilder.
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 08/07/2016 15:18:00
 */
public class InsertBuilder implements QueryStructureBuilder<InsertBuilder, InsertStructure> {

    private final String into;
    private final List<String> columnsList;
    private OptionalQuery<List<Value>> valuesList;

    public InsertBuilder(String into) {
        this.into = into;
        this.columnsList = new ArrayList<>();
    }

    /**
     * Método responsável por adicionar uma coleção de colunas <code>columns</code>.
     *
     * @param columns informa a campo do parâmetro.
     */
    public InsertBuilder columns(String... columns) {
        this.columnsList.addAll(Arrays.stream(columns).collect(Collectors.toList()));
        return this;
    }

    /**
     * Método responsável por adicionar uma coleção de valores <code>values</code>.
     *
     * @param values informa a campo do parâmetro.
     *
     * @return retornar os valores adicionados.
     */
    public InsertBuilder values(Object... values) {
        this.valuesList = new OptionalQuery<>(Arrays.stream(values)
                .map(Value::ofParam)
                .collect(Collectors.toList()));
        return this;
    }

    /**
     * Método responsável por listar os valores na consulta <code>subquery</code>.
     *
     * @param subquery informa uma sentença da consulta.
     *
     * @return retornar a montagem da coleção de values na consulta.
     */
    public InsertBuilder values(SelectBuilder subquery) {
        this.valuesList = new OptionalQuery<>(subquery.build());
        return this;
    }

    @Override
    public InsertBuilder getRoot() {
        return this;
    }

    @Override
    public InsertStructure build() {
        return new InsertStructure(QueryLanguages.SQL, into, columnsList, valuesList);
    }
}
