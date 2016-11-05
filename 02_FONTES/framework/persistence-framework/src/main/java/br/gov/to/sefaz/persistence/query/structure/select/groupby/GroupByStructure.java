package br.gov.to.sefaz.persistence.query.structure.select.groupby;

import br.gov.to.sefaz.persistence.query.structure.where.ConditionsStructure;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Classe responsável por manter a estrutura do comando de Group By.
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 30/06/2016 18:19:00
 */
public class GroupByStructure {

    private final List<String> columns;
    private final Optional<ConditionsStructure> having;

    public GroupByStructure(List<String> columns) {
        this.columns = columns;
        this.having = Optional.empty();
    }

    public GroupByStructure(List<String> columns, ConditionsStructure having) {
        this.columns = columns;
        this.having = Optional.of(having);
    }

    public List<String> getColumns() {
        return columns;
    }

    public Optional<ConditionsStructure> getHaving() {
        return having;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GroupByStructure that = (GroupByStructure) o;
        return Objects.equals(columns, that.columns)
                && Objects.equals(having, that.having);
    }

    @Override
    public int hashCode() {
        return Objects.hash(columns, having);
    }

    @Override
    public String toString() {
        return "GroupByStructure{"
                + "columns=" + columns
                + ", having=" + having
                + '}';
    }
}
