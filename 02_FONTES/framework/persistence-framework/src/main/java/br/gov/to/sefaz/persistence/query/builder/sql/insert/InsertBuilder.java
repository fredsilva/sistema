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

    public InsertBuilder columns(String... columns) {
        this.columnsList.addAll(Arrays.stream(columns).collect(Collectors.toList()));
        return this;
    }

    public InsertBuilder values(Object... values) {
        this.valuesList = new OptionalQuery<>(Arrays.stream(values)
                .map(Value::ofParam)
                .collect(Collectors.toList()));
        return this;
    }

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
