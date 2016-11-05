package br.gov.to.sefaz.persistence.query.structure.update;

import br.gov.to.sefaz.persistence.query.structure.domain.Alias;
import br.gov.to.sefaz.persistence.query.structure.domain.Value;
import br.gov.to.sefaz.persistence.query.structure.where.ConditionsStructure;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Classe que representa a estrutura do comando de Update.
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 08/07/2016 14:12:00
 */
public class UpdateStructure {

    private final String queryLanguage;
    private final Alias<String> from;
    private final Map<String, Value> sets;
    private Optional<ConditionsStructure> where;

    public UpdateStructure(String queryLanguage, Alias<String> from, Map<String, Value> sets) {
        this.queryLanguage = queryLanguage;
        this.from = from;
        this.sets = sets;
        this.where = Optional.empty();
    }

    public String getQueryLanguage() {
        return queryLanguage;
    }

    public void setWhere(ConditionsStructure where) {
        this.where = Optional.of(where);
    }

    public Alias<String> getFrom() {
        return from;
    }

    public Map<String, Value> getSets() {
        return sets;
    }

    public Optional<ConditionsStructure> getWhere() {
        return where;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UpdateStructure that = (UpdateStructure) o;
        return Objects.equals(queryLanguage, that.queryLanguage)
                && Objects.equals(from, that.from)
                && Objects.equals(sets, that.sets)
                && Objects.equals(where, that.where);
    }

    @Override
    public int hashCode() {
        return Objects.hash(queryLanguage, from, sets, where);
    }
}
