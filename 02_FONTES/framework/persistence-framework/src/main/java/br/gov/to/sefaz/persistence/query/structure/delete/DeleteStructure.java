package br.gov.to.sefaz.persistence.query.structure.delete;

import br.gov.to.sefaz.persistence.query.structure.domain.Alias;
import br.gov.to.sefaz.persistence.query.structure.where.ConditionsStructure;

import java.util.Objects;
import java.util.Optional;

/**
 * Classe responsável por manter a estrutura do comando de Deletar.
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 08/07/2016 14:12:00
 */
public class DeleteStructure {

    private final String queryLanguage;
    private final Alias<String> from;
    private Optional<ConditionsStructure> where;

    public DeleteStructure(String queryLanguage, Alias<String> from) {
        this.queryLanguage = queryLanguage;
        this.from = from;
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
        DeleteStructure that = (DeleteStructure) o;
        return Objects.equals(queryLanguage, that.queryLanguage)
                && Objects.equals(from, that.from)
                && Objects.equals(where, that.where);
    }

    @Override
    public int hashCode() {
        return Objects.hash(queryLanguage, from, where);
    }
}
