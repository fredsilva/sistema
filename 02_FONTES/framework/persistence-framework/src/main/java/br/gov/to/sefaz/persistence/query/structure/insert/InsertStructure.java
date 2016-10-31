package br.gov.to.sefaz.persistence.query.structure.insert;

import br.gov.to.sefaz.persistence.query.structure.domain.OptionalQuery;
import br.gov.to.sefaz.persistence.query.structure.domain.Value;

import java.util.List;
import java.util.Objects;

/**
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 08/07/2016 15:50:00
 */
public class InsertStructure {

    private final String queryLanguage;
    private final String into;
    private final List<String> columns;
    private final OptionalQuery<List<Value>> values;

    public InsertStructure(String queryLanguage, String into, List<String> columns, OptionalQuery<List<Value>> values) {
        this.queryLanguage = queryLanguage;
        this.into = into;
        this.columns = columns;
        this.values = values;
    }

    public String getQueryLanguage() {
        return queryLanguage;
    }

    public String getInto() {
        return into;
    }

    public List<String> getColumns() {
        return columns;
    }

    public OptionalQuery<List<Value>> getValues() {
        return values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InsertStructure that = (InsertStructure) o;
        return Objects.equals(queryLanguage, that.queryLanguage)
                && Objects.equals(into, that.into)
                && Objects.equals(columns, that.columns)
                && Objects.equals(values, that.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(queryLanguage, into, columns, values);
    }
}
