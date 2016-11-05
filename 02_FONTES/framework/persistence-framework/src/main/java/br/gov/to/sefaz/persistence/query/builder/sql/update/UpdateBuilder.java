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
 * Classe responsável por conter os métodos de execução
 * de update em SQL do QueryBuilder.
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

    /**
     * Método responsável por setar os parametros para atualização.
     *
     * @param column  nome da coluna base a ser consultada.
     * @param value informa o valor do parâmetro.
     *
     * @return retornar a montagem dos parâmetros que serão atualizados.
     */
    public UpdateBuilder set(String column, Object value) {
        sets.put(column, Value.ofParam(value));
        return this;
    }

    /**
     * Método responsável por setar os valores nulos e vazios
     * para os campos que não serão setados na atualização.
     *
     * @param column  nome da coluna base a ser consultada.
     * @param value informa o valor do parâmetro.
     *
     * @return retornar a montagem dos parâmetros nulos e vazios que serão informados nos parâmetros.
     */
    public UpdateBuilder setOpt(String column, Object value) {
        if (!StringUtils.isEmpty(value)) {
            sets.put(column, Value.ofColumn(column));
        }
        return this;
    }

    /**
     * Método responsável por setar as colunas que serão atualizados.
     *
     * @param column  nome da coluna base a ser consultada.
     * @param value informa o valor do parâmetro.
     *
     * @return retornar a montagem das colunas que serão atualizadas.
     */
    public UpdateBuilder setToColumn(String column, String value) {
        sets.put(column, Value.ofColumn(column));
        return this;
    }

    /**
     * Método responsável por executar o comando Where do SQL.
     *
     * @return retornar a montagem do comando de execução Where do SQL.
     */
    public WhereBuilder<UpdateBuilder> where() {
        if (!whereBuilder.isPresent()) {
            whereBuilder = Optional.of(new WhereBuilder<>(this));
        }
        return whereBuilder.get();
    }

    /**
     * Método responsável por executar o comando Where do SQL informa o parâmetro
     * <code>whereHandler</code> para a clausula do where.
     *
     * @param whereHandler informa a sentença da consulta.
     *
     * @return retornar a montagem da execução do comando Where do SQL.
     */
    public JunctionBuilder<UpdateBuilder> where(WhereHandler whereHandler) {
        return where().condition(whereHandler);
    }

    /**
     * Método responsável por retornar.
     * getRoot.
     *
     * @return retorna a Entidade.
     */
    @Override
    public UpdateBuilder getRoot() {
        return this;
    }

    /**
     * Método responsável por retornar a construção
     * da sentença do update no SQL.
     *
     * @return retorna a query da Entidade.
     */
    @Override
    public UpdateStructure build() {
        UpdateStructure structure = new UpdateStructure(QueryLanguages.SQL, from, sets);
        whereBuilder.ifPresent(w -> structure.setWhere(w.build()));

        return structure;
    }
}
