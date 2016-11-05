package br.gov.to.sefaz.persistence.query.parser.domain;

import java.util.Map;
import java.util.Objects;

/**
 * Classe que representa as responsabilidade do ResultQuery.
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 05/07/2016 13:20:00
 */
public class ResultQuery {

    private final String query;
    private final Map<String, Object> params;

    public ResultQuery(String query, Map<String, Object> params) {
        this.query = query;
        this.params = params;
    }

    public String getQuery() {
        return query;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ResultQuery resultQuery1 = (ResultQuery) o;
        return Objects.equals(query, resultQuery1.query)
                && Objects.equals(params, resultQuery1.params);
    }

    @Override
    public int hashCode() {
        return Objects.hash(query, params);
    }

    @Override
    public String toString() {
        return "Query{\n"
                + "query='" + query + '\''
                + ",\nparams=" + params
                + '}';
    }
}
