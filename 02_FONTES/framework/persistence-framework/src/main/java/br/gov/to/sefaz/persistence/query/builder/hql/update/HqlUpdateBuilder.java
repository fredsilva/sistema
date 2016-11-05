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
 * Classe responsável por conter os métodos de execução de update em HQL do QueryBuilder.
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


    /**
     * Método responsável por setar os parametros para atualização.
     *
     * @param column  nome da coluna base a ser consultada.
     * @param value informa o valor do parâmetro.
     *
     * @return retornar a montagem dos parâmetros que serão atualizados.
     */
    public HqlUpdateBuilder set(String column, Object value) {
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
    public HqlUpdateBuilder setOpt(String column, Object value) {
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
    public HqlUpdateBuilder setToColumn(String column, String value) {
        sets.put(column, Value.ofColumn(column));
        return this;
    }

    /**
     * Método responsável por executar o comando Where do HQL.
     *
     * @return retornar a montagem do comando de execução Where do HQL.
     */
    public WhereBuilder<HqlUpdateBuilder> where() {
        if (!whereBuilder.isPresent()) {
            whereBuilder = Optional.of(new WhereBuilder<>(this));
        }
        return whereBuilder.get();
    }

    /**
     * Método responsável por executar o comando Where do HQL informa o parâmetro
     * <code>whereHandler</code> para a clausula do where.
     *
     * @param whereHandler informa a sentença da consulta.
     *
     * @return retornar a montagem da execução do comando Where do HQL.
     */
    public JunctionBuilder<HqlUpdateBuilder> where(WhereHandler whereHandler) {
        return where().condition(whereHandler);
    }

    /**
     * Método responsável por executar o comando Where do HQL informa o parâmetro
     * <code>id</code> para a clausula de condição do where.
     *
     * @param id chave que consulta a entidade.
     *
     * @return retornar a montagem da execução do comando Where do HQL.
     */
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

    /**
     * Método responsável por retornar.
     * getRoot.
     *
     * @return retorna a Entidade.
     */
    @Override
    public HqlUpdateBuilder getRoot() {
        return this;
    }

    /**
     * Método responsável por retornar a construção
     * da sentença da atualização.
     *
     * @return retorna a Entidade.
     */
    @Override
    public UpdateStructure build() {
        UpdateStructure structure = new UpdateStructure(QueryLanguages.HQL, from, sets);
        whereBuilder.ifPresent(w -> structure.setWhere(w.build()));

        return structure;
    }
}
