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
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 15/07/2016 11:00:00
 */
@SuppressWarnings("PMD.TooManyMethods")
@Component
public class FindRepository {

    private static final String COUNT_ROWS = "count(*)";
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private SatQueryParser parser;

    public <E> List<E> findAll(Class<E> entityClass) {
        return find(entityClass, HqlSelectBuilder::orderById);
    }

    public <E> List<E> findNative(String tableName, Class<E> entityClass, String alias,
            Consumer<SelectBuilder> selectConsumer) {
        SelectBuilder selectBuilder = sqlSelect(tableName, alias);
        selectConsumer.accept(selectBuilder);
        return selectEntityNative(entityClass, selectBuilder);
    }

    public <E> List<E> findNative(String tableName, Class<E> entityClass, Consumer<SelectBuilder> selectConsumer) {
        SelectBuilder selectBuilder = sqlSelect(tableName);
        selectConsumer.accept(selectBuilder);
        return selectEntityNative(entityClass, selectBuilder);
    }

    public <E> List<E> find(Class<E> entityClass, String alias, Consumer<HqlSelectBuilder> selectConsumer) {
        HqlSelectBuilder hqlSelectBuilder = hqlSelect(entityClass, alias);
        selectConsumer.accept(hqlSelectBuilder);
        return selectEntity(entityClass, hqlSelectBuilder);
    }

    public <E> List<E> find(Class<E> entityClass, Consumer<HqlSelectBuilder> selectConsumer) {
        HqlSelectBuilder hqlSelectBuilder = hqlSelect(entityClass);
        selectConsumer.accept(hqlSelectBuilder);
        return selectEntity(entityClass, hqlSelectBuilder);
    }

    public <R> List<R> findColumnNative(String tableName, String alias, String column,
            Consumer<SelectBuilder> selectConsumer) {
        SelectBuilder selectBuilder = sqlSelect(tableName, alias).columns(column);
        selectConsumer.accept(selectBuilder);
        return selectColumnNative(selectBuilder);
    }

    public <R> List<R> findColumnNative(String tableName, String column, Consumer<SelectBuilder> selectConsumer) {
        SelectBuilder selectBuilder = sqlSelect(tableName).columns(column);
        selectConsumer.accept(selectBuilder);
        return selectColumnNative(selectBuilder);
    }

    public <R> List<R> findColumn(Class<?> entityClass, String alias, String column,
            Consumer<HqlSelectBuilder> selectConsumer) {
        HqlSelectBuilder selectBuilder = hqlSelect(entityClass, alias).columns(column);
        selectConsumer.accept(selectBuilder);
        return selectColumn(selectBuilder);
    }

    public <R> List<R> findColumn(Class<?> entityClass, String column, Consumer<HqlSelectBuilder> selectConsumer) {
        HqlSelectBuilder selectBuilder = hqlSelect(entityClass).columns(column);
        selectConsumer.accept(selectBuilder);
        return selectColumn(selectBuilder);
    }

    public <R> List<R> findColumn(Class<?> entityClass, String column, Object i) {
        HqlSelectBuilder hqlSelectBuilder = hqlSelect(entityClass);
        hqlSelectBuilder.columns(column).whereId(i);
        return selectColumn(hqlSelectBuilder);
    }

    public <E> E findOneNative(String tableName, Class<E> entityClass, String alias,
            Consumer<SelectBuilder> selectConsumer) {
        SelectBuilder selectBuilder = sqlSelect(tableName, alias);
        selectConsumer.accept(selectBuilder);
        return selectOneNative(entityClass, selectBuilder);
    }

    public <E> E findOneNative(String tableName, Class<E> entityClass, Consumer<SelectBuilder> selectConsumer) {
        SelectBuilder selectBuilder = sqlSelect(tableName);
        selectConsumer.accept(selectBuilder);
        return selectOneNative(entityClass, selectBuilder);
    }

    public <E> E findOne(Class<E> entityClass, String alias, Consumer<HqlSelectBuilder> selectConsumer) {
        HqlSelectBuilder hqlSelectBuilder = hqlSelect(entityClass, alias);
        selectConsumer.accept(hqlSelectBuilder);
        return selectOne(entityClass, hqlSelectBuilder);
    }

    public <E> E findOne(Class<E> entityClass, Consumer<HqlSelectBuilder> selectConsumer) {
        HqlSelectBuilder hqlSelectBuilder = hqlSelect(entityClass);
        selectConsumer.accept(hqlSelectBuilder);
        return selectOne(entityClass, hqlSelectBuilder);
    }

    public <E> E findOne(Class<E> entityClass, Object i) {
        HqlSelectBuilder hqlSelectBuilder = hqlSelect(entityClass);
        hqlSelectBuilder.whereId(i);
        return selectOne(entityClass, hqlSelectBuilder);
    }

    public <R> R findOneColumnNative(String tableName, String alias, String column,
            Consumer<SelectBuilder> selectConsumer) {
        SelectBuilder selectBuilder = sqlSelect(tableName, alias).columns(column);
        selectConsumer.accept(selectBuilder);
        return selectOneColumnNative(selectBuilder);
    }

    public <R> R findOneColumnNative(String tableName, String column, Consumer<SelectBuilder> selectConsumer) {
        SelectBuilder selectBuilder = sqlSelect(tableName).columns(column);
        selectConsumer.accept(selectBuilder);
        return selectOneColumnNative(selectBuilder);
    }

    public <R> R findOneColumn(Class<?> entityClass, String alias, String column,
            Consumer<HqlSelectBuilder> selectConsumer) {
        HqlSelectBuilder hqlSelectBuilder = hqlSelect(entityClass, alias).columns(column);
        selectConsumer.accept(hqlSelectBuilder);
        return selectOneColumn(hqlSelectBuilder);
    }

    public <R> R findOneColumn(Class<?> entityClass, String column, Consumer<HqlSelectBuilder> selectConsumer) {
        HqlSelectBuilder hqlSelectBuilder = hqlSelect(entityClass).columns(column);
        selectConsumer.accept(hqlSelectBuilder);
        return selectOneColumn(hqlSelectBuilder);
    }

    public <R> R findOneColumn(Class<?> entityClass, String column, Object i) {
        HqlSelectBuilder hqlSelectBuilder = hqlSelect(entityClass);
        hqlSelectBuilder.columns(column).whereId(i);
        return selectOneColumn(hqlSelectBuilder);
    }

    public Long countNative(String tableName, String alias, Consumer<SelectBuilder> selectHandler) {
        return ((Number) findOneColumnNative(tableName, alias, COUNT_ROWS, selectHandler)).longValue();
    }

    public Long countNative(String tableName, Consumer<SelectBuilder> selectHandler) {
        return ((Number) findOneColumnNative(tableName, COUNT_ROWS, selectHandler)).longValue();
    }

    public Long count(Class<?> entityClass, String alias, Consumer<HqlSelectBuilder> selectHandler) {
        return findOneColumn(entityClass, alias, COUNT_ROWS, selectHandler);
    }

    public Long count(Class<?> entityClass, Consumer<HqlSelectBuilder> selectHandler) {
        return findOneColumn(entityClass, COUNT_ROWS, selectHandler);
    }

    public Long count(Class<?> entityClass, Object i) {
        return findOneColumn(entityClass, COUNT_ROWS, i);
    }

    public boolean existsNative(String tableName, String alias, Consumer<SelectBuilder> selectHandler) {
        return countNative(tableName, alias, selectHandler) > 0;
    }

    public boolean existsNative(String tableName, Consumer<SelectBuilder> selectHandler) {
        return countNative(tableName, selectHandler) > 0;
    }

    public boolean exists(Class<?> entityClass, String alias, Consumer<HqlSelectBuilder> selectHandler) {
        return count(entityClass, alias, selectHandler) > 0;
    }

    public boolean exists(Class<?> entityClass, Consumer<HqlSelectBuilder> selectHandler) {
        return count(entityClass, selectHandler) > 0;
    }

    public boolean exists(Class<?> entityClass, Object i) {
        return count(entityClass, i) > 0;
    }

    // Métodos base, pense antes de utilizar, utilize apenas se for realmente necessário

    public <R> List<R> selectColumnNative(SelectBuilder selectBuilder) {
        ResultQuery resultQuery = parser.parseSelect(selectBuilder);

        Query query = entityManager.createNativeQuery(resultQuery.getQuery());
        setParams(query, resultQuery.getParams());

        return (List<R>) query.getResultList();
    }

    public <R> List<R> selectColumn(HqlSelectBuilder selectBuilder) {
        ResultQuery resultQuery = parser.parseSelect(selectBuilder);

        Query query = entityManager.createQuery(resultQuery.getQuery());
        setParams(query, resultQuery.getParams());

        return (List<R>) query.getResultList();
    }

    public <E> List<E> selectEntityNative(Class<E> entityClass, SelectBuilder selectBuilder) {
        ResultQuery resultQuery = parser.parseSelect(selectBuilder);

        Query query = entityManager.createNativeQuery(resultQuery.getQuery(), entityClass);
        setParams(query, resultQuery.getParams());

        return query.getResultList();
    }

    public <E> List<E> selectEntity(Class<E> entityClass, HqlSelectBuilder hqlSelectBuilder) {
        ResultQuery resultQuery = parser.parseSelect(hqlSelectBuilder);

        Query query = entityManager.createQuery(resultQuery.getQuery(), entityClass);
        setParams(query, resultQuery.getParams());

        return query.getResultList();
    }

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

    public void flush() {
        entityManager.flush();
    }

    private void setParams(Query query, Map<String, Object> params) {
        params.entrySet().forEach(param -> query.setParameter(param.getKey(), param.getValue()));
    }

    public <R> List<R> selectNativeQuery(String nativeQuery, ParamsBuilder params) {
        Query query =  entityManager.createNativeQuery(nativeQuery);
        setParams(query, params.toMap());
        return query.getResultList();
    }
}
