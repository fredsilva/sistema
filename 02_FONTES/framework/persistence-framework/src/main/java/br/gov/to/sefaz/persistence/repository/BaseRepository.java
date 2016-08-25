package br.gov.to.sefaz.persistence.repository;

import br.gov.to.sefaz.persistence.entity.AbstractEntity;
import br.gov.to.sefaz.persistence.query.builder.ParamsBuilder;
import br.gov.to.sefaz.persistence.query.builder.hql.delete.HqlDeleteBuilder;
import br.gov.to.sefaz.persistence.query.builder.hql.handler.EntityHandler;
import br.gov.to.sefaz.persistence.query.builder.hql.select.HqlSelectBuilder;
import br.gov.to.sefaz.persistence.query.builder.hql.update.HqlUpdateBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.delete.DeleteBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.select.SelectBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.update.UpdateBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

/**
 * Classe base para repositorios, possui todos os básicos necessários para o sistema.
 *
 * @param <E> tipo da entidade que o repositório gerência
 * @param <I> o tipo de ID da entidade que o repositório gerência
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 12/05/2016 15:14:00
 */
@SuppressWarnings({"PMD.TooManyMethods", "PMD.AbstractNaming", "PMD.AbstractClassWithoutAbstractMethod"})
public abstract class BaseRepository<E extends AbstractEntity<I>, I extends Serializable> {

    protected final Class<E> entityClass;
    protected final String tableName;
    @Autowired
    private FindRepository findRepository;
    @Autowired
    private PersistRepository persistRepository;

    public BaseRepository() {
        this.entityClass = extractEntityCLassFromGenerics();
        this.tableName = EntityHandler.getTable(entityClass);
    }

    public List<E> findAll() {
        return findRepository.findAll(entityClass);
    }

    public List<E> findNative(String alias, Consumer<SelectBuilder> selectConsumer) {
        return findRepository.findNative(tableName, entityClass, alias, selectConsumer);
    }

    public List<E> findNative(Consumer<SelectBuilder> selectConsumer) {
        return findRepository.findNative(tableName, entityClass, selectConsumer);
    }

    public List<E> find(String alias, Consumer<HqlSelectBuilder> selectConsumer) {
        return findRepository.find(entityClass, alias, selectConsumer);
    }

    public List<E> find(Consumer<HqlSelectBuilder> selectConsumer) {
        return findRepository.find(entityClass, selectConsumer);
    }

    public <R> List<R> findColumnNative(String alias, String column, Consumer<SelectBuilder> selectConsumer) {
        return findRepository.findColumnNative(tableName, alias, column, selectConsumer);
    }

    public <R> List<R> findColumnNative(String column, Consumer<SelectBuilder> selectConsumer) {
        return findRepository.findColumnNative(tableName, column, selectConsumer);
    }

    public <R> List<R> findColumn(String alias, String column, Consumer<HqlSelectBuilder> selectConsumer) {
        return findRepository.findColumn(entityClass, alias, column, selectConsumer);
    }

    public <R> List<R> findColumn(String column, Consumer<HqlSelectBuilder> selectConsumer) {
        return findRepository.findColumn(entityClass, column, selectConsumer);
    }

    public <R> List<R> findColumn(String column, I i) {
        return findRepository.findColumn(entityClass, column, i);
    }

    public <R> R findOneColumnNative(String alias, String column, Consumer<SelectBuilder> selectConsumer) {
        return findRepository.findOneColumnNative(tableName, alias, column, selectConsumer);
    }

    public <R> R findOneColumnNative(String column, Consumer<SelectBuilder> selectConsumer) {
        return findRepository.findOneColumnNative(tableName, column, selectConsumer);
    }

    public <R> R findOneColumn(String alias, String column, Consumer<HqlSelectBuilder> selectConsumer) {
        return findRepository.findOneColumn(entityClass, alias, column, selectConsumer);
    }

    public <R> R findOneColumn(String column, Consumer<HqlSelectBuilder> selectConsumer) {
        return findRepository.findOneColumn(entityClass, column, selectConsumer);
    }

    public E findOneNative(String alias, Consumer<SelectBuilder> selectConsumer) {
        return findRepository.findOneNative(tableName, entityClass, alias, selectConsumer);
    }

    public E findOneNative(Consumer<SelectBuilder> selectConsumer) {
        return findRepository.findOneNative(tableName, entityClass, selectConsumer);
    }

    public E findOne(String alias, Consumer<HqlSelectBuilder> selectConsumer) {
        return findRepository.findOne(entityClass, alias, selectConsumer);
    }

    public E findOne(Consumer<HqlSelectBuilder> selectConsumer) {
        return findRepository.findOne(entityClass, selectConsumer);
    }

    public E findOne(I i) {
        return findRepository.findOne(entityClass, i);
    }

    public <R> R findOneColumn(String column, I i) {
        return findRepository.findOneColumn(entityClass, column, i);
    }

    public Long countNative(String alias, Consumer<SelectBuilder> selectHandler) {
        return findRepository.countNative(tableName, alias, selectHandler);
    }

    public Long countNative(Consumer<SelectBuilder> selectHandler) {
        return findRepository.countNative(tableName, selectHandler);
    }

    public Long count(String alias, Consumer<HqlSelectBuilder> selectHandler) {
        return findRepository.count(entityClass, alias, selectHandler);
    }

    public Long count(Consumer<HqlSelectBuilder> selectHandler) {
        return findRepository.count(entityClass, selectHandler);
    }

    public Long count(I i) {
        return findRepository.count(entityClass, i);
    }

    public boolean existsNative(String alias, Consumer<SelectBuilder> selectHandler) {
        return findRepository.existsNative(tableName, alias, selectHandler);
    }

    public boolean existsNative(Consumer<SelectBuilder> selectHandler) {
        return findRepository.existsNative(tableName, selectHandler);
    }

    public boolean exists(String alias, Consumer<HqlSelectBuilder> selectHandler) {
        return findRepository.exists(entityClass, alias, selectHandler);
    }

    public boolean exists(Consumer<HqlSelectBuilder> selectHandler) {
        return findRepository.exists(entityClass, selectHandler);
    }

    public boolean exists(I i) {
        return findRepository.exists(entityClass, i);
    }

    public E save(E entity) {
        return persistRepository.save(entity);
    }

    public Collection<E> save(Collection<E> entities) {
        return persistRepository.save(entities);
    }

    public void updateNative(String alias, Consumer<UpdateBuilder> updateHandler) {
        persistRepository.updateNative(tableName, alias, updateHandler);
    }

    public void updateNative(Consumer<UpdateBuilder> updateHandler) {
        persistRepository.updateNative(tableName, updateHandler);
    }

    public void update(String alias, Consumer<HqlUpdateBuilder> updateHandler) {
        persistRepository.update(entityClass, alias, updateHandler);
    }

    public void update(Consumer<HqlUpdateBuilder> updateHandler) {
        persistRepository.update(entityClass, updateHandler);
    }

    public void deleteNative(String alias, Consumer<DeleteBuilder> deleteHandler) {
        persistRepository.deleteNative(tableName, alias, deleteHandler);
    }

    public void deleteNative(Consumer<DeleteBuilder> deleteHandler) {
        persistRepository.deleteNative(tableName, deleteHandler);
    }

    public void delete(String alias, Consumer<HqlDeleteBuilder> deleteHandler) {
        persistRepository.delete(entityClass, alias, deleteHandler);
    }

    public void delete(Consumer<HqlDeleteBuilder> deleteHandler) {
        persistRepository.delete(entityClass, deleteHandler);
    }

    public void delete(I id) {
        persistRepository.delete(entityClass, id);
    }

    public void delete(Iterable<I> ids) {
        persistRepository.delete(entityClass, ids);
    }

    @SuppressWarnings("unchecked")
    public Class<E> extractEntityCLassFromGenerics() {
        Type type = getClass().getGenericSuperclass();

        while (!(type instanceof ParameterizedType)
                || ((ParameterizedType) type).getRawType() != BaseRepository.class) {
            if (type instanceof ParameterizedType) {
                type = ((Class<?>) ((ParameterizedType) type).getRawType()).getGenericSuperclass();
            } else {
                type = ((Class<?>) type).getGenericSuperclass();
            }
        }

        return (Class<E>) ((ParameterizedType) type).getActualTypeArguments()[0];
    }

    public void flush() {
        findRepository.flush();
    }

    /**
     * Executa uma query nativa com os parametros passados.
     * Utilize se for apenas REALMENTE necessário caso contrario de preferencia para os outro metodos de findNative.
     *
     * @param nativeQuery sql
     * @param params parametros
     * @return retorno da consulta, provavelmente será uma List&lt;object[]&gt;
     */
    protected <R> List<R> findNativeQuery(String nativeQuery, ParamsBuilder params) {
        return findRepository.selectNativeQuery(nativeQuery, params);
    }
}