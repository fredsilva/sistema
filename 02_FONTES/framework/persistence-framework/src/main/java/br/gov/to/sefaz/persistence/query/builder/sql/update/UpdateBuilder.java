package br.gov.to.sefaz.persistence.query.builder.sql.update;

import br.gov.to.sefaz.persistence.query.builder.QueryStructureBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.where.JunctionBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.where.WhereBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.where.WhereHandler;
import br.gov.to.sefaz.persistence.query.parser.domain.QueryLanguages;
import br.gov.to.sefaz.persistence.query.structure.domain.Alias;
import br.gov.to.sefaz.persistence.query.structure.domain.Value;
import br.gov.to.sefaz.persistence.query.structure.update.UpdateStructure;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 08/07/2016 14:09:00
 */
public class UpdateBuilder implements QueryStructureBuilder<UpdateBuilder, UpdateStructure> {

    private final Alias<String> from;
    private final Map<String, Value> sets;
    private Optional<WhereBuilder<UpdateBuilder>> whereBuilder;

    public UpdateBuilder(String from, String alias) {
        this.from = new Alias<>(from, alias);
        this.sets = new LinkedHashMap<>();
        this.whereBuilder = Optional.empty();
    }

    public UpdateBuilder(String from) {
        this.from = new Alias<>(from);
        this.sets = new LinkedHashMap<>();
        this.whereBuilder = Optional.empty();
    }

    public UpdateBuilder set(String column, Object value) {
        sets.put(column, Value.ofParam(value));
        return this;
    }

    public UpdateBuilder setOpt(String column, Object value) {
        if (!StringUtils.isEmpty(value)) {
            sets.put(column, Value.ofColumn(column));
        }
        return this;
    }

    public UpdateBuilder setToColumn(String column, String value) {
        sets.put(column, Value.ofColumn(column));
        return this;
    }

    public WhereBuilder<UpdateBuilder> where() {
        if (!whereBuilder.isPresent()) {
            whereBuilder = Optional.of(new WhereBuilder<>(this));
        }
        return whereBuilder.get();
    }

    public JunctionBuilder<UpdateBuilder> where(WhereHandler whereHandler) {
        return where().condition(whereHandler);
    }

    @Override
    public UpdateBuilder getRoot() {
        return this;
    }

    @Override
    public UpdateStructure build() {
        UpdateStructure structure = new UpdateStructure(QueryLanguages.SQL, from, sets);
        whereBuilder.ifPresent(w -> structure.setWhere(w.build()));

        return structure;
    }
}
