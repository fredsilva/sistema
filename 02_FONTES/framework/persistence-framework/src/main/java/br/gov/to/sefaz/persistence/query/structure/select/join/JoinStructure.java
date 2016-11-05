package br.gov.to.sefaz.persistence.query.structure.select.join;

import br.gov.to.sefaz.persistence.query.structure.domain.Alias;
import br.gov.to.sefaz.persistence.query.structure.where.ConditionsStructure;

import java.util.Objects;
import java.util.Optional;

/**
 * Classe responsável por criar a Estrutura do Comando Join.
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 29/06/2016 09:15:00
 */
public class JoinStructure {

    private final Alias<String> table;
    private final JoinType type;
    private final Optional<ConditionsStructure> on;

    public JoinStructure(Alias<String> table, JoinType type, ConditionsStructure on) {
        this(table, type, Optional.of(on));
    }

    public JoinStructure(Alias<String> table, JoinType type, Optional<ConditionsStructure> on) {
        this.table = table;
        this.type = type;
        this.on = on;
    }

    public JoinStructure(Alias<String> table, JoinType type) {
        this.table = table;
        this.type = type;
        this.on = Optional.empty();
    }

    public Alias<String> getTable() {
        return table;
    }

    public JoinType getType() {
        return type;
    }

    public Optional<ConditionsStructure> getOn() {
        return on;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        JoinStructure that = (JoinStructure) o;
        return Objects.equals(table, that.table)
                && type == that.type
                && Objects.equals(on, that.on);
    }

    @Override
    public int hashCode() {
        return Objects.hash(table, type, on);
    }
}
