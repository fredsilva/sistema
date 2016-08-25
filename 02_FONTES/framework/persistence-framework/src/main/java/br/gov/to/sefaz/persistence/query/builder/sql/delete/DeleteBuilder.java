package br.gov.to.sefaz.persistence.query.builder.sql.delete;

import br.gov.to.sefaz.persistence.query.builder.QueryStructureBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.where.JunctionBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.where.WhereBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.where.WhereHandler;
import br.gov.to.sefaz.persistence.query.parser.domain.QueryLanguages;
import br.gov.to.sefaz.persistence.query.structure.delete.DeleteStructure;
import br.gov.to.sefaz.persistence.query.structure.domain.Alias;

import java.util.Optional;

/**
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 08/07/2016 14:09:00
 */
public class DeleteBuilder implements QueryStructureBuilder<DeleteBuilder, DeleteStructure> {

    private final Alias<String> from;
    private Optional<WhereBuilder<DeleteBuilder>> whereBuilder;

    public DeleteBuilder(String from, String alias) {
        this.from = new Alias<>(from, alias);
        this.whereBuilder = Optional.empty();
    }

    public DeleteBuilder(String from) {
        this.from = new Alias<>(from);
        this.whereBuilder = Optional.empty();
    }

    public WhereBuilder<DeleteBuilder> where() {
        if (!whereBuilder.isPresent()) {
            whereBuilder = Optional.of(new WhereBuilder<>(this));
        }
        return whereBuilder.get();
    }

    public JunctionBuilder<DeleteBuilder> where(WhereHandler whereHandler) {
        return where().condition(whereHandler);
    }

    @Override
    public DeleteBuilder getRoot() {
        return this;
    }

    @Override
    public DeleteStructure build() {
        DeleteStructure structure = new DeleteStructure(QueryLanguages.SQL, from);
        whereBuilder.ifPresent(w -> structure.setWhere(w.build()));

        return structure;
    }
}
