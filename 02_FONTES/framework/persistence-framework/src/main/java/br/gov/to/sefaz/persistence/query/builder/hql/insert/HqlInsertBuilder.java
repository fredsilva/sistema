package br.gov.to.sefaz.persistence.query.builder.hql.insert;

import br.gov.to.sefaz.persistence.query.builder.QueryStructureBuilder;
import br.gov.to.sefaz.persistence.query.builder.hql.handler.EntityHandler;
import br.gov.to.sefaz.persistence.query.builder.hql.select.HqlSelectBuilder;
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
public class HqlInsertBuilder implements QueryStructureBuilder<HqlInsertBuilder, InsertStructure> {

    private final String into;
    private final List<String> columnsList;
    private OptionalQuery<List<Value>> valuesList;

    public HqlInsertBuilder(Class<?> entityClass) {
        this.into = EntityHandler.getName(entityClass);
        this.columnsList = new ArrayList<>();
    }

    public HqlInsertBuilder columns(String... columns) {
        this.columnsList.addAll(Arrays.stream(columns).collect(Collectors.toList()));
        return this;
    }

    public HqlInsertBuilder values(Object... values) {
        this.valuesList = new OptionalQuery<>(Arrays.stream(values)
                .map(Value::ofParam)
                .collect(Collectors.toList()));
        return this;
    }

    public HqlInsertBuilder values(HqlSelectBuilder subquery) {
        this.valuesList = new OptionalQuery<>(subquery.build());
        return this;
    }

    @Override
    public HqlInsertBuilder getRoot() {
        return this;
    }

    @Override
    public InsertStructure build() {
        return new InsertStructure(QueryLanguages.HQL, into, columnsList, valuesList);
    }
}
