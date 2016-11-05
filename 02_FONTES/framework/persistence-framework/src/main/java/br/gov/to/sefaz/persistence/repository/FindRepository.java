package br.gov.to.sefaz.persistence.repository;

import br.gov.to.sefaz.persistence.query.builder.ParamsBuilder;
import br.gov.to.sefaz.persistence.query.builder.hql.select.HqlSelectBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.select.SelectBuilder;
import br.gov.to.sefaz.persistence.query.parser.domain.ResultQuery;
import br.gov.to.sefaz.persistence.satquery.parser.SatQueryParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import static br.gov.to.sefaz.persistence.query.builder.QueryBuilder.hqlSelect;
import static br.gov.to.sefaz.persistence.query.builder.QueryBuilder.sqlSelect;

/**
 * Classe responsável pelos métodos de consulta ao dados.
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 15/07/2016 11:00:00
 */
@SuppressWarnings({"PMD.TooManyMethods", "PMD.GodClass"})
@Component
public class FindRepository {

    private static final String COUNT_ROWS = "count(*)";
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private SatQueryParser parser;


    /**
     * Executa a operação de pesquisa no repositório para a entidade informada, realizando a consulta
     * e retorna todos registros desta entidade.
     *
     * @param entityClass    Tipo da entidade base a ser consultada.
     * @param <E>            Entidade base a ser consultada.
     * @return Lista de objetos que retorna a consulta executada.
     */
    public <E> List<E> findAll(Class<E> entityClass) {
        return find(entityClass, HqlSelectBuilder::orderById);
    }

    /**
     * Executa a operação de pesquisa no repositório para a entidade informada, realizando a consulta
     * através do <code>tableName</code> e <code>alias</code>. Retorna os
     * registros desta entidade.
     *
     * @param entityClass    Tipo da entidade base a ser consultada.
     * @param tableName      informa nome da tabela.
     * @param alias          informa alias da tabela.
     * @param selectConsumer Consulta a ser consumida pelo repositório.
     * @param <E>            Entidade base a ser consultada.
     * @return Lista de objetos que retorna a consulta executada.
     */
    public <E> List<E> findNative(String tableName, Class<E> entityClass, String alias,
            Consumer<SelectBuilder> selectConsumer) {
        SelectBuilder selectBuilder = sqlSelect(tableName, alias);
        selectConsumer.accept(selectBuilder);
        return selectEntityNative(entityClass, selectBuilder);
    }

    /**
     * Executa a operação de pesquisa no repositório para a entidade informada, realizando a consulta
     * através do <code>tableName</code>. Retorna  todos os
     * registros desta entidade.
     *
     * @param entityClass    Tipo da entidade base a ser consultada.
     * @param tableName      informa nome da tabela.
     * @param selectConsumer Consulta a ser consumida pelo repositório.
     * @param <E>            Entidade base a ser consultada.
     * @return Lista de objetos que atendam a consulta consumida.
     */
    public <E> List<E> findNative(String tableName, Class<E> entityClass, Consumer<SelectBuilder> selectConsumer) {
        SelectBuilder selectBuilder = sqlSelect(tableName);
        selectConsumer.accept(selectBuilder);
        return selectEntityNative(entityClass, selectBuilder);
    }

    /**
     * Executa a operação de pesquisa no repositório para a entidade informada, baseado na consulta informada por
     * parâmetro, informando alias na consulta retorna uma lista de objetos.
     *
     * @param entityClass    Tipo da entidade base a ser consultada.
     * @param alias          informa alias da tabela.
     * @param selectConsumer Consulta a ser consumida pelo repositório.
     * @param <E>            Entidade base a ser consultada.
     * @return Lista de objetos que atendam a consulta consumida.
     */
    public <E> List<E> find(Class<E> entityClass, String alias, Consumer<HqlSelectBuilder> selectConsumer) {
        HqlSelectBuilder hqlSelectBuilder = hqlSelect(entityClass, alias);
        selectConsumer.accept(hqlSelectBuilder);
        return selectEntity(entityClass, hqlSelectBuilder);
    }

    /**
     * Executa a operação de pesquisa no repositório para a entidade informada, baseado na consulta informada por
     * parâmetro retorna uma lista de objetos.
     *
     * @param entityClass    Tipo da entidade base a ser consultada.
     * @param selectConsumer Consulta a ser consumida pelo repositório.
     * @param <E>            Entidade base a ser consultada.
     * @return Lista de objetos que atendam a consulta consumida.
     */
    public <E> List<E> find(Class<E> entityClass, Consumer<HqlSelectBuilder> selectConsumer) {
        HqlSelectBuilder hqlSelectBuilder = hqlSelect(entityClass);
        selectConsumer.accept(hqlSelectBuilder);
        return selectEntity(entityClass, hqlSelectBuilder);
    }

    /**
     * Executa a operação de pesquisa no repositório para a entidade informada, baseado na consulta informada por
     * parâmetro e limitando a quantidade de objetos a serem retornadas de acordo com o valor definido no parâmetro
     * <code>maxResults</code>.
     *
     * @param entityClass    Tipo da entidade base a ser consultada
     * @param selectConsumer Consulta a ser consumida pelo repositório.
     * @param maxResults     Número máximo de objetos a serem retornados.
     * @param <E>            Entidade base a ser consultada.
     * @return Lista de objetos que atendam a consulta consumida, limitada ao número máximo
     *      de elementos definidos por parâmetro.
     */
    public <E> List<E> find(Class<E> entityClass, Consumer<HqlSelectBuilder> selectConsumer, int maxResults) {
        HqlSelectBuilder hqlSelectBuilder = hqlSelect(entityClass);
        selectConsumer.accept(hqlSelectBuilder);
        return selectEntity(entityClass, hqlSelectBuilder, maxResults);
    }

    /**
     * Executa a operação de pesquisa no formato SQL Nativo no repositório
     * para a Coluna informada, realizando a consulta
     * através do <code>tableName</code> e <code>alias</code> e <code>column</code>.
     * Retorna os registros desta Coluna.
     *
     * @param column         Tipo da Coluna base a ser consultada.
     * @param tableName      informa nome da tabela.
     * @param alias          informa alias da tabela.
     * @param selectConsumer Consulta a ser consumida pelo repositório.
     * @param <R>            Coluna base a ser consultada.
     * @return retorna lista de objetos da coluna consultada.
     */
    public <R> List<R> findColumnNative(String tableName, String alias, String column,
            Consumer<SelectBuilder> selectConsumer) {
        SelectBuilder selectBuilder = sqlSelect(tableName, alias).columns(column);
        selectConsumer.accept(selectBuilder);
        return selectColumnNative(selectBuilder);
    }

    /**
     * Executa a operação de pesquisa no formato SQL Nativo no repositório
     * para a Coluna informada, realizando a consulta
     * através do <code>tableName</code> e <code>column</code>. Retorna os
     * registros desta Coluna.
     *
     * @param column         Tipo da Coluna base a ser consultada.
     * @param tableName      informa nome da tabela.
     * @param selectConsumer Consulta a ser consumida pelo repositório.
     * @param <R>            Coluna base a ser consultada.
     * @return retorna lista de objetos da coluna consultada.
     */
    public <R> List<R> findColumnNative(String tableName, String column, Consumer<SelectBuilder> selectConsumer) {
        SelectBuilder selectBuilder = sqlSelect(tableName).columns(column);
        selectConsumer.accept(selectBuilder);
        return selectColumnNative(selectBuilder);
    }

    /**
     * Executa a operação de pesquisa no repositório para a Coluna informada,
     * realizando a consulta através do <code>entityClass</code> e <code>alias</code>
     * e <code>column</code>. Retorna os registros desta Coluna.
     *
     * @param entityClass    Tipo da entidade base a ser consultada.
     * @param column         Tipo da Coluna base a ser consultada.
     * @param alias          informa alias da tabela.
     * @param selectConsumer Consulta a ser consumida pelo repositório.
     * @param <R>            Coluna base a ser consultada.
     * @return retorna lista de objetos da coluna consultada.
     */
    public <R> List<R> findColumn(Class<?> entityClass, String alias, String column,
            Consumer<HqlSelectBuilder> selectConsumer) {
        HqlSelectBuilder selectBuilder = hqlSelect(entityClass, alias).columns(column);
        selectConsumer.accept(selectBuilder);
        return selectColumn(selectBuilder);
    }

    /**
     * Executa a operação de pesquisa no repositório para a Coluna informada,
     * realizando a consulta através do <code>entityClass</code> e <code>column</code>.
     * Retorna os registros desta Coluna.
     *
     * @param entityClass    Tipo da entidade base a ser consultada.
     * @param column         Tipo da Coluna base a ser consultada.
     * @param selectConsumer Consulta a ser consumida pelo repositório.
     * @param <R>            Coluna base a ser consultada.
     * @return retorna lista de objetos da coluna consultada.
     */
    public <R> List<R> findColumn(Class<?> entityClass, String column, Consumer<HqlSelectBuilder> selectConsumer) {
        HqlSelectBuilder selectBuilder = hqlSelect(entityClass).columns(column);
        selectConsumer.accept(selectBuilder);
        return selectColumn(selectBuilder);
    }

    /**
     * Executa a operação de pesquisa no repositório para a Coluna informada,
     * realizando a consulta através do <code>entityClass</code> e <code>column</code>
     * e <code>i</code>. Retorna os registros desta Coluna.
     *
     * @param entityClass    Tipo da entidade base a ser consultada.
     * @param column         Tipo da Coluna base a ser consultada.
     * @param  i             Id da  Entidade a ser consultado(Composto ou Simples).
     * @param <R>            Coluna base a ser consultada.
     * @return retorna lista de objetos da coluna consultada.
     */
    public <R> List<R> findColumn(Class<?> entityClass, String column, Object i) {
        HqlSelectBuilder hqlSelectBuilder = hqlSelect(entityClass);
        hqlSelectBuilder.columns(column).whereId(i);
        return selectColumn(hqlSelectBuilder);
    }

    /**
     * Executa a operação de pesquisa no formato SQL Nativo no repositório para
     * a Tabela informada, realizando a consulta através
     * do <code>tableName</code> e <code>alias</code>
     * e <code>i</code>. Retorna um registro da Tabela.
     *
     * @param entityClass    Tipo da entidade base a ser consultada.
     * @param tableName      informa nome da tabela.
     * @param alias          informa alias da tabela.
     * @param selectConsumer Consulta a ser consumida pelo repositório.
     * @param <E>            Entidade base a ser consultada.
     * @return retorna um registro da Tabela consultada.
     */
    public <E> E findOneNative(String tableName, Class<E> entityClass, String alias,
            Consumer<SelectBuilder> selectConsumer) {
        SelectBuilder selectBuilder = sqlSelect(tableName, alias);
        selectConsumer.accept(selectBuilder);
        return selectOneNative(entityClass, selectBuilder);
    }

    /**
     * Executa a operação de pesquisa no formato SQL Nativo no repositório para
     * a Tabela informada, realizando a consulta através do <code>tableName</code>.
     * Retorna um registro da Tabela.
     *
     * @param entityClass    Tipo da entidade base a ser consultada.
     * @param tableName      informa nome da tabela.
     * @param selectConsumer Consulta a ser consumida pelo repositório.
     * @param <E>            Entidade base a ser consultada.
     * @return retorna um registro da Tabela consultada.
     */
    public <E> E findOneNative(String tableName, Class<E> entityClass, Consumer<SelectBuilder> selectConsumer) {
        SelectBuilder selectBuilder = sqlSelect(tableName);
        selectConsumer.accept(selectBuilder);
        return selectOneNative(entityClass, selectBuilder);
    }

    /**
     * Executa a operação de pesquisa no repositório para
     * a Entidade informada, realizando a consulta através do <code>entityClass </code>
     * e <code>alias</code>. Retorna um registro da Entidade.
     *
     * @param entityClass    Tipo da entidade base a ser consultada.
     * @param alias          informa alias da tabela.
     * @param selectConsumer Consulta a ser consumida pelo repositório.
     * @param <E>            Entidade base a ser consultada
     * @return retorna um registro da Entidade consultada.
     */
    public <E> E findOne(Class<E> entityClass, String alias, Consumer<HqlSelectBuilder> selectConsumer) {
        HqlSelectBuilder hqlSelectBuilder = hqlSelect(entityClass, alias);
        selectConsumer.accept(hqlSelectBuilder);
        return selectOne(entityClass, hqlSelectBuilder);
    }

    /**
     * Executa a operação de pesquisa no repositório para
     * a Entidade informada, realizando a consulta através do <code>entityClass</code>.
     * Retorna um registro da Entidade.
     *
     * @param entityClass    Tipo da entidade base a ser consultada.
     * @param selectConsumer Consulta a ser consumida pelo repositório.
     * @param <E>            Entidade base a ser consultada.
     * @return retorna um registro da Entidade consultada.
     */
    public <E> E findOne(Class<E> entityClass, Consumer<HqlSelectBuilder> selectConsumer) {
        HqlSelectBuilder hqlSelectBuilder = hqlSelect(entityClass);
        selectConsumer.accept(hqlSelectBuilder);
        return selectOne(entityClass, hqlSelectBuilder);
    }

    /**
     * Executa a operação de pesquisa no repositório para
     * a Entidade informada, realizando a consulta através do <code>entityClass</code>
     * <code>i</code>. Retorna um registro desta Entidade.
     *
     * @param entityClass    Tipo da entidade base a ser consultada.
     * @param  i             Id da  Entidade a ser consultado(Composto ou Simples).
     * @param <E>            Entidade base a ser consultada.
     * @return retorna um registro da Entidade consultada.
     */
    public <E> E findOne(Class<E> entityClass, Object i) {
        HqlSelectBuilder hqlSelectBuilder = hqlSelect(entityClass);
        hqlSelectBuilder.whereId(i);
        return selectOne(entityClass, hqlSelectBuilder);
    }

    /**
     * Executa a operação de pesquisa no formato SQL Nativo no repositório para
     * a Entidade informada, realizando a consulta através do <code>tableName</code>
     * <code>alias</code> e <code>column</code>. Retorna um registro da Coluna desta Tabela.
     *
     * @param tableName      informa nome da tabela.
     * @param column         Nome da coluna base a ser consultada.
     * @param selectConsumer Consulta a ser consumida pelo repositório.
     * @param alias          informa alias da tabela.
     * @param <R>            Coluna base a ser consultada.
     * @return retorna um registro da Coluna consultada.
     */
    public <R> R findOneColumnNative(String tableName, String alias, String column,
            Consumer<SelectBuilder> selectConsumer) {
        SelectBuilder selectBuilder = sqlSelect(tableName, alias).columns(column);
        selectConsumer.accept(selectBuilder);
        return selectOneColumnNative(selectBuilder);
    }

    /**
     * Executa a operação de pesquisa no formato SQL Nativo no repositório para
     * a Tabela informada, realizando a consulta através do <code>tableName</code>
     * e <code>column</code>. Retorna um registro da Coluna desta Tabela.
     *
     * @param tableName      informa nome da tabela.
     * @param column         Nome da coluna base a ser consultada.
     * @param selectConsumer Consulta a ser consumida pelo repositório.
     * @param <R>            Coluna base a ser consultada.
     * @return retorna um registro da Coluna consultada.
     */
    public <R> R findOneColumnNative(String tableName, String column, Consumer<SelectBuilder> selectConsumer) {
        SelectBuilder selectBuilder = sqlSelect(tableName).columns(column);
        selectConsumer.accept(selectBuilder);
        return selectOneColumnNative(selectBuilder);
    }

    /**
     * Executa a operação de pesquisa no repositório para
     * a Entidade informada, realizando a consulta através do <code>entityClass</code>
     * e <code>alias</code>. Retorna um registro da Coluna desta Entidade.
     *
     * @param entityClass    Tipo da entidade base a ser consultada.
     * @param alias          informa alias da tabela.
     * @param column         Nome da coluna base a ser consultada.
     * @param selectConsumer Consulta a ser consumida pelo repositório.
     * @param <R>            Coluna base a ser consultada.
     * @return retorna um registro da Coluna consultada.
     */
    public <R> R findOneColumn(Class<?> entityClass, String alias, String column,
            Consumer<HqlSelectBuilder> selectConsumer) {
        HqlSelectBuilder hqlSelectBuilder = hqlSelect(entityClass, alias).columns(column);
        selectConsumer.accept(hqlSelectBuilder);
        return selectOneColumn(hqlSelectBuilder);
    }

    /**
     * Executa a operação de pesquisa no repositório para
     * a Entidade informada, realizando a consulta através do <code>entityClass</code>
     * e <code>column</code>. Retorna um registro da Coluna desta Entidade.
     *
     * @param entityClass    Tipo da entidade base a ser consultada.
     * @param column         Nome da coluna base a ser consultada.
     * @param selectConsumer Consulta a ser consumida pelo repositório.
     * @param <R>            Coluna base a ser consultada.
     * @return retorna um registro da Coluna consultada.
     */
    public <R> R findOneColumn(Class<?> entityClass, String column, Consumer<HqlSelectBuilder> selectConsumer) {
        HqlSelectBuilder hqlSelectBuilder = hqlSelect(entityClass).columns(column);
        selectConsumer.accept(hqlSelectBuilder);
        return selectOneColumn(hqlSelectBuilder);
    }

    /**
     * Executa a operação de pesquisa no repositório para
     * a Entidade informada, realizando a consulta através do <code>entityClass</code>
     * e <code>i</code>. Retorna um registro da Coluna desta Entidade.
     *
     * @param entityClass    Tipo da entidade base a ser consultada.
     * @param column         Nome da coluna base a ser consultada.
     * @param  i             Id da  Entidade a ser consultado(Composto ou Simples).
     * @param <R>            Coluna base a ser consultada.
     * @return retorna um registro da Coluna consultada.
     */
    public <R> R findOneColumn(Class<?> entityClass, String column, Object i) {
        HqlSelectBuilder hqlSelectBuilder = hqlSelect(entityClass);
        hqlSelectBuilder.columns(column).whereId(i);
        return selectOneColumn(hqlSelectBuilder);
    }

    /**
     * Consulta no formato SLQ Nativo no repositório a quantidade de
     * colunas pesquisada referente a Tabela informada
     * sendo filtrada por <code>tableName</code>
     * e <code>alias</code> e <code>selectHandler</code>.
     *
     * @param tableName      informa nome da tabela.
     * @param alias          informa alias da tabela.
     * @param selectHandler Consulta a ser consumida pelo repositório.
     * @return retorna quantidade de colunas da Tabela.
     */
    public Long countNative(String tableName, String alias, Consumer<SelectBuilder> selectHandler) {
        return ((Number) findOneColumnNative(tableName, alias, COUNT_ROWS, selectHandler)).longValue();
    }

    /**
     * Consulta no formato SLQ Nativo no repositório a quantidade de
     * colunas pesquisada referente a Tabela informada
     * sendo filtrada por <code>tableName</code>
     * e <code>selectHandler</code>.
     *
     * @param tableName      informa nome da tabela.
     * @param selectHandler Consulta a ser consumida pelo repositório.
     * @return retorna quantidade de colunas da Tabela.
     */
    public Long countNative(String tableName, Consumer<SelectBuilder> selectHandler) {
        return ((Number) findOneColumnNative(tableName, COUNT_ROWS, selectHandler)).longValue();
    }

    /**
     * Consulta no repositório a quantidade de
     * colunas pesquisada referente a Entidade informada
     * sendo filtrada por <code>entityClass</code>
     * e <code>alias</code> e <code>selectHandler</code>.
     *
     * @param entityClass    Tipo da entidade base a ser consultada.
     * @param alias          informa alias da tabela.
     * @param selectHandler Consulta a ser consumida pelo repositório.
     * @return retorna quantidade de colunas da Entidade.
     */
    public Long count(Class<?> entityClass, String alias, Consumer<HqlSelectBuilder> selectHandler) {
        return findOneColumn(entityClass, alias, COUNT_ROWS, selectHandler);
    }

    /**
     * Consulta no repositório a quantidade de
     * colunas pesquisada referente a Entidade informada
     * sendo filtrada por <code>entityClass</code>
     * e <code>selectHandler</code>.
     *
     * @param entityClass    Tipo da entidade base a ser consultada.
     * @param selectHandler Consulta a ser consumida pelo repositório.
     * @return retorna quantidade de colunas da Entidade.
     */
    public Long count(Class<?> entityClass, Consumer<HqlSelectBuilder> selectHandler) {
        return findOneColumn(entityClass, COUNT_ROWS, selectHandler);
    }

    /**
     * Consulta no repositório a quantidade de
     * colunas pesquisada referente a Entidade informada
     * sendo filtrada por <code>entityClass</code>
     * e <code>i</code>.
     *
     * @param entityClass    Tipo da entidade base a ser consultada.
     * @param  i             Id da  Entidade a ser consultado(Composto ou Simples).
     * @return retorna quantidade de colunas da Entidade.
     */
    public Long count(Class<?> entityClass, Object i) {
        return findOneColumn(entityClass, COUNT_ROWS, i);
    }

    /**
     * Consulta executada no formato SQL Nativo no repositório se existe
     * registros na Tabela  que retorne com a consulta informada
     * nos parâmetros <code>tableName</code>
     * e <code>alias</code> e <code>selectHandler</code>.
     *
     * @param tableName      informa nome da tabela.
     * @param alias          informa alias da tabela.
     * @param selectHandler Consulta a ser consumida pelo repositório.
     *
     * @return retorna o status da consulta na Tabela true ou false.
     */
    public boolean existsNative(String tableName, String alias, Consumer<SelectBuilder> selectHandler) {
        return countNative(tableName, alias, selectHandler) > 0;
    }

    /**
     * Consulta executada no formato SQL Nativo no repositório se existe
     * registros na Tabela  que retorne com a consulta informada
     * nos parâmetros <code>tableName</code>
     * e <code>selectHandler</code>.
     *
     * @param tableName      informa nome da tabela.
     * @param selectHandler Consulta a ser consumida pelo repositório.
     *
     * @return retorna o status da consulta na Tabela true ou false.
     */
    public boolean existsNative(String tableName, Consumer<SelectBuilder> selectHandler) {
        return countNative(tableName, selectHandler) > 0;
    }

    /**
     * Consulta executada no repositório se existe
     * registros na Tabela  que retorne com a consulta informada
     * nos parâmetros <code>entityClass</code>
     * e <code>alias</code> e <code>selectHandler</code>.
     *
     * @param entityClass    Tipo da entidade base a ser consultada.
     * @param alias          informa alias da tabela.
     * @param selectHandler Consulta a ser consumida pelo repositório.
     *
     * @return retorna o status da consulta na Tabela true ou false.
     */
    public boolean exists(Class<?> entityClass, String alias, Consumer<HqlSelectBuilder> selectHandler) {
        return count(entityClass, alias, selectHandler) > 0;
    }

    /**
     * Consulta executada no repositório se existe
     * registros na Tabela  que retorne com a consulta informada
     * nos parâmetros <code>entityClass</code>
     * e <code>selectHandler</code>.
     *
     * @param entityClass    Tipo da entidade base a ser consultada.
     * @param selectHandler Consulta a ser consumida pelo repositório.
     *
     * @return retorna o status da consulta na Tabela true ou false.
     */
    public boolean exists(Class<?> entityClass, Consumer<HqlSelectBuilder> selectHandler) {
        return count(entityClass, selectHandler) > 0;
    }

    /**
     * Consulta executada no repositório se existe
     * registros na Tabela  que retorne com a consulta informada
     * nos parâmetros <code>entityClass</code>
     * e <code>i</code>.
     *
     * @param entityClass    Tipo da entidade base a ser consultada.
     * @param  i  Id da  Entidade a ser consultado(Composto ou Simples).
     *
     * @return retorna o status da consulta na Tabela true ou false.
     */
    public boolean exists(Class<?> entityClass, Object i) {
        return count(entityClass, i) > 0;
    }

    // ####################################################################################
    // Métodos base, pense antes de utilizar, utilize apenas se for realmente necessário
    // ####################################################################################

    /**
     * Executa a operação de pesquisa no formato SQL Nativo no repositório
     * para a Coluna informada, realizando a consulta
     * através do <code>tableName</code> e <code>column</code>  retorna os
     * registros desta Coluna.
     *
     * @param selectBuilder Consulta a ser consumida pelo repositório.
     * @param <R>            Coluna base a ser consultada.
     * @return retorna uma lista de objetos da coluna consultada.
     */
    public <R> List<R> selectColumnNative(SelectBuilder selectBuilder) {
        ResultQuery resultQuery = parser.parseSelect(selectBuilder);

        Query query = entityManager.createNativeQuery(resultQuery.getQuery());
        setParams(query, resultQuery.getParams());

        return (List<R>) query.getResultList();
    }

    /**
     * Executa a operação de pesquisa no repositório para a Coluna informada,
     * realizando a consulta através do <code>tableName</code> e
     * retorna os registros desta Coluna.
     *
     * @param selectBuilder Consulta a ser consumida pelo repositório.
     * @param <R>            Coluna base a ser consultada.
     * @return retorna uma lista de objetos da coluna consultada.
     */
    public <R> List<R> selectColumn(HqlSelectBuilder selectBuilder) {
        ResultQuery resultQuery = parser.parseSelect(selectBuilder);

        Query query = entityManager.createQuery(resultQuery.getQuery());
        setParams(query, resultQuery.getParams());

        return (List<R>) query.getResultList();
    }

    /**
     * Executa a operação de pesquisa no repositório para a entidade informada,
     * baseado na consulta através do <code>entityClass</code> e  <code>selectBuilder</code>
     * uma lista de objetos.
     *
     * @param entityClass    Tipo da entidade base a ser consultada.
     * @param selectBuilder Consulta a ser consumida pelo repositório.
     * @param <E>            Entidade base a ser consultada.
     * @return Lista de objetos que atendam a consulta consumida.
     */
    public <E> List<E> selectEntityNative(Class<E> entityClass, SelectBuilder selectBuilder) {
        ResultQuery resultQuery = parser.parseSelect(selectBuilder);

        Query query = entityManager.createNativeQuery(resultQuery.getQuery(), entityClass);
        setParams(query, resultQuery.getParams());

        return query.getResultList();
    }

    /**
     * Executa a operação de pesquisa no repositório para a entidade informada, baseado na consulta informada por
     * parâmetro <code>entityClass</code> e <code>hqlSelectBuilder</code>.
     *
     * @param entityClass    Tipo da entidade base a ser consultada
     * @param hqlSelectBuilder Consulta a ser consumida pelo repositório.
     * @param <E>            Entidade base a ser consultada.
     * @return Lista de objetos que atendam a consulta consumida, limitada ao número máximo de elementos definidos por
     *          parâmetro.
     */
    public <E> List<E> selectEntity(Class<E> entityClass, HqlSelectBuilder hqlSelectBuilder) {
        Query query = createSelectQuery(entityClass, hqlSelectBuilder);

        return query.getResultList();
    }

    /**
     * Executa uma operação de select no repositório para a entidade informada, baseado na consulta a ser construida
     * pelo builder informado e limitando quantidade de objetos a serem retornadas de acordo com o valor definido no
     * parâmetro <code>maxResults</code>.
     *
     * @param entityClass      Tipo da entidade base a ser consultada
     * @param hqlSelectBuilder Builder responsavél por construir o hql de consulta.
     * @param maxResults       Número máximo de objetos a serem retornados.
     * @param <E>              Entidade base a ser consultada
     * @return Lista de objetos que atendam a consulta construída, limitada ao número máximo de elementos definidos por
     *         parâmetro.
     */
    public <E> List<E> selectEntity(Class<E> entityClass, HqlSelectBuilder hqlSelectBuilder, int maxResults) {
        Query query = createSelectQuery(entityClass, hqlSelectBuilder);
        query.setMaxResults(maxResults);

        return query.getResultList();
    }

    /**Executa uma consulta no formato de SQL Nativo no repositório
     * pesquisa através dos parâmetros <code>entityClass</code> e <code>selectBuilder</code>
     * retorna um objeto da consulta executada.
     *
     * @param entityClass      Tipo da entidade base a ser consultada.
     * @param selectBuilder    Consulta a ser consumida pelo repositório.
     * @param <E>              Entidade base a ser consultada.
     * @return retorna um registro consultado da entidade.
     */
    public <E> E selectOneNative(Class<E> entityClass, SelectBuilder selectBuilder) {
        ResultQuery resultQuery = parser.parseSelect(selectBuilder);

        Query query = entityManager.createNativeQuery(resultQuery.getQuery(), entityClass);
        setParams(query, resultQuery.getParams());

        try {
            return (E) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**Executa uma consulta no formato de SQL Nativo no repositório
     * pesquisa através dos parâmetros <code>entityClass</code> e <code>hqlSelectBuilder</code>
     * retorna um objeto da consulta executada.
     *
     * @param entityClass      Tipo da entidade base a ser consultada.
     * @param hqlSelectBuilder objeto que contém a query de consulta.
     * @param <E>              Entidade base a ser consultada.
     * @return retorna um registro consultado da entidade.
     */
    public <E> E selectOne(Class<E> entityClass, HqlSelectBuilder hqlSelectBuilder) {
        ResultQuery resultQuery = parser.parseSelect(hqlSelectBuilder);

        Query query = entityManager.createQuery(resultQuery.getQuery(), entityClass);
        setParams(query, resultQuery.getParams());

        try {
            return (E) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**Executa uma consulta no formato de SQL Nativo no repositório que
     * pesquisa através do parâmetro  <code>hqlSelectBuilder</code>.
     * Retorna um registro desta Coluna.
     *
     * @param hqlSelectBuilder objeto que contém a query de consulta.
     * @param <R>            Coluna base a ser consultada.
     * @return retorna um registro consultado da Coluna.
     */
    public <R> R selectOneColumn(HqlSelectBuilder hqlSelectBuilder) {
        ResultQuery resultQuery = parser.parseSelect(hqlSelectBuilder);

        Query query = entityManager.createQuery(resultQuery.getQuery());
        setParams(query, resultQuery.getParams());

        try {
            return (R) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**Executa uma consulta no formato de SQL Nativo no repositório que
     * pesquisa através do parâmetro  <code>hqlSelectBuilder</code>.
     * Retorna um registro desta Coluna.
     *
     * @param selectBuilder objeto que contém a query de consulta.
     * @param <R>            Coluna base a ser consultada.
     * @return retorna um registro consultado da Coluna.
     */
    public <R> R selectOneColumnNative(SelectBuilder selectBuilder) {
        ResultQuery resultQuery = parser.parseSelect(selectBuilder);

        Query query = entityManager.createNativeQuery(resultQuery.getQuery());
        setParams(query, resultQuery.getParams());

        try {
            return (R) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    private void setParams(Query query, Map<String, Object> params) {
        params.entrySet().forEach(param -> query.setParameter(param.getKey(), param.getValue()));
    }

    /**Executa uma consulta no repositório que
     * pesquisa através do parâmetro  <code>hqlSelectBuilder</code> e <code>params</code>.
     * Retorna uma lista de registros desta Coluna.
     *
     * @param params objeto que contém a query de consulta.
     * @param nativeQuery objeto que contém a query de consulta.
     * @param <R> Coluna base a ser consultada.
     * @return retorna uma lista de registros desta Coluna.
     */
    public <R> List<R> selectNativeQuery(String nativeQuery, ParamsBuilder params) {
        Query query =  entityManager.createNativeQuery(nativeQuery);
        setParams(query, params.toMap());
        return query.getResultList();
    }

    private <E> Query createSelectQuery(Class<E> entityClass, HqlSelectBuilder hqlSelectBuilder) {
        ResultQuery resultQuery = parser.parseSelect(hqlSelectBuilder);

        Query query = entityManager.createQuery(resultQuery.getQuery(), entityClass);
        setParams(query, resultQuery.getParams());
        return query;
    }
}
