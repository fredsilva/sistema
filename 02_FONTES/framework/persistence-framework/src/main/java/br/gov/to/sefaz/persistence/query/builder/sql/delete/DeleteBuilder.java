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
 * Classe responsável por métodos para Deletar do QueryBuilder.
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

    /**
     * Método responsável por executar o comando Where do SQL.
     *
     * @return deve retornar o where da operação de Deletar.
     */
    public WhereBuilder<DeleteBuilder> where() {
        if (!whereBuilder.isPresent()) {
            whereBuilder = Optional.of(new WhereBuilder<>(this));
        }
        return whereBuilder.get();
    }

    /**
     * Método responsável por executar o comando Where informa o parâmetro
     * <code>whereHandler</code> para a cláusula de condição do where.
     *
     * @param whereHandler informa uma sentença da consulta a entidade.
     *
     * @return retornar a montagem da execução do comando Where.
     */
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
