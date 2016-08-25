package br.gov.to.sefaz.persistence.query.builder.hql.update;

import br.gov.to.sefaz.persistence.query.builder.ParamsBuilder;
import br.gov.to.sefaz.persistence.query.builder.QueryStructureBuilder;
import br.gov.to.sefaz.persistence.query.builder.hql.handler.EntityHandler;
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
public class HqlUpdateBuilder implements QueryStructureBuilder<HqlUpdateBuilder, UpdateStructure> {

    private final Alias<String> from;
    private final Map<String, Value> sets;
    private final Class<?> entityClass;
    private Optional<WhereBuilder<HqlUpdateBuilder>> whereBuilder;

    public HqlUpdateBuilder(Class<?> entityClass, String alias) {
        this.from = new Alias<>(EntityHandler.getName(entityClass), alias);
        this.entityClass = entityClass;
        this.sets = new LinkedHashMap<>();
        this.whereBuilder = Optional.empty();
    }

    public HqlUpdateBuilder(Class<?> entityClass) {
        this.from = new Alias<>(EntityHandler.getName(entityClass));
        this.entityClass = entityClass;
        this.sets = new LinkedHashMap<>();
        this.whereBuilder = Optional.empty();
    }

    public HqlUpdateBuilder set(String column, Object value) {
        sets.put(column, Value.ofParam(value));
        return this;
    }

    public HqlUpdateBuilder setOpt(String column, Object value) {
        if (!StringUtils.isEmpty(value)) {
            sets.put(column, Value.ofColumn(column));
        }
        return this;
    }

    public HqlUpdateBuilder setToColumn(String column, String value) {
        sets.put(column, Value.ofColumn(column));
        return this;
    }

    public WhereBuilder<HqlUpdateBuilder> where() {
        if (!whereBuilder.isPresent()) {
            whereBuilder = Optional.of(new WhereBuilder<>(this));
        }
        return whereBuilder.get();
    }

    public JunctionBuilder<HqlUpdateBuilder> where(WhereHandler whereHandler) {
        return where().condition(whereHandler);
    }

    public JunctionBuilder<HqlUpdateBuilder> whereId(Object id) {
        WhereBuilder<HqlUpdateBuilder> where = where();
        ParamsBuilder idParams = EntityHandler.getIdParams(entityClass, id);
        JunctionBuilder<HqlUpdateBuilder> junctionBuilder = null;

        for (Map.Entry<String, Object> entry : idParams.toMap().entrySet()) {
            if (junctionBuilder == null) {
                junctionBuilder = where.equal(entry.getKey(), entry.getValue());
            } else {
                junctionBuilder = junctionBuilder.and().equal(entry.getKey(), entry.getValue());
            }
        }

        return junctionBuilder;
    }

    @Override
    public HqlUpdateBuilder getRoot() {
        return this;
    }

    @Override
    public UpdateStructure build() {
        UpdateStructure structure = new UpdateStructure(QueryLanguages.HQL, from, sets);
        whereBuilder.ifPresent(w -> structure.setWhere(w.build()));

        return structure;
    }
}
