package br.gov.to.sefaz.persistence.query.builder.hql.delete;

import br.gov.to.sefaz.persistence.query.builder.ParamsBuilder;
import br.gov.to.sefaz.persistence.query.builder.QueryStructureBuilder;
import br.gov.to.sefaz.persistence.query.builder.hql.handler.EntityHandler;
import br.gov.to.sefaz.persistence.query.builder.sql.where.JunctionBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.where.WhereBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.where.WhereHandler;
import br.gov.to.sefaz.persistence.query.parser.domain.QueryLanguages;
import br.gov.to.sefaz.persistence.query.structure.delete.DeleteStructure;
import br.gov.to.sefaz.persistence.query.structure.domain.Alias;

import java.util.Map;
import java.util.Optional;

/**
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 08/07/2016 14:09:00
 */
public class HqlDeleteBuilder implements QueryStructureBuilder<HqlDeleteBuilder, DeleteStructure> {

    private final Alias<String> from;
    private final Class<?> entityClass;
    private Optional<WhereBuilder<HqlDeleteBuilder>> whereBuilder;

    public HqlDeleteBuilder(Class<?> entityClass, String alias) {
        this.from = new Alias<>(EntityHandler.getName(entityClass), alias);
        this.entityClass = entityClass;
        this.whereBuilder = Optional.empty();
    }

    public HqlDeleteBuilder(Class<?> entityClass) {
        this.from = new Alias<>(EntityHandler.getName(entityClass));
        this.entityClass = entityClass;
        this.whereBuilder = Optional.empty();
    }

    public WhereBuilder<HqlDeleteBuilder> where() {
        if (!whereBuilder.isPresent()) {
            whereBuilder = Optional.of(new WhereBuilder<>(this));
        }
        return whereBuilder.get();
    }

    public JunctionBuilder<HqlDeleteBuilder> where(WhereHandler whereHandler) {
        return where().condition(whereHandler);
    }

    public JunctionBuilder<HqlDeleteBuilder> whereId(Object id) {
        WhereBuilder<HqlDeleteBuilder> where = where();
        ParamsBuilder idParams = EntityHandler.getIdParams(entityClass, id);
        JunctionBuilder<HqlDeleteBuilder> junctionBuilder = null;

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
    public HqlDeleteBuilder getRoot() {
        return this;
    }

    @Override
    public DeleteStructure build() {
        DeleteStructure structure = new DeleteStructure(QueryLanguages.HQL, from);
        whereBuilder.ifPresent(w -> structure.setWhere(w.build()));

        return structure;
    }
}
