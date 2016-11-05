package br.gov.to.sefaz.persistence.repository;

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
@SuppressWarnings({"PMD.TooManyMethods", "PMD.AbstractNaming", "PMD.AbstractClassWithoutAbstractMethod",
        "PMD.ExcessivePublicCount"})
public abstract class BaseRepository<E, I extends Serializable> {

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

    /**
     * Executa a operação de pesquisa no repositório para a entidade informada,
     * retorna todos registros desta entidade.
     *
     * @return retorna uma lista de objetos da Entidade consultada.
     */
    public List<E> findAll() {
        return findRepository.findAll(entityClass);
    }

    /**
     * Executa a operação de pesquisa no formato SQL Nativo no repositório para a entidade informada,
     * realizando a consulta através do <code>alias</code> e <code>selectConsumer</code>.
     * Retorna os registros desta entidade.
     *
     * @param alias          informa alias da tabela.
     * @param selectConsumer Consulta a ser consumida pelo repositório.
     * @return retorna uma lista de objetos da Entidade consultada.
     */
    public List<E> findNative(String alias, Consumer<SelectBuilder> selectConsumer) {
        return findRepository.findNative(tableName, entityClass, alias, selectConsumer);
    }

    /**
     * Executa a operação de pesquisa no formato SQL Nativo no repositório para a entidade informada,
     * realizando a consulta através do <code>alias</code> e <code>selectConsumer</code>.
     * Retorna os registros desta entidade.
     *
     * @param selectConsumer Consulta a ser consumida pelo repositório.
     * @return retorna uma lista de objetos da Entidade consultada.
     */
    public List<E> findNative(Consumer<SelectBuilder> selectConsumer) {
        return findRepository.findNative(tableName, entityClass, selectConsumer);
    }

    /**
     * Executa a operação de pesquisa no repositório para a entidade informada,
     * baseada na consulta informada por parâmetros, <code>alias</code>
     * <code>selectConsumer</code> que retorna uma lista de objetos da Entidade consultada.
     *
     * @param alias          informa alias da tabela.
     * @param selectConsumer Consulta a ser consumida pelo repositório.
     * @return retorna uma lista de objetos da Entidade consultada.
     */
    public List<E> find(String alias, Consumer<HqlSelectBuilder> selectConsumer) {
        return findRepository.find(entityClass, alias, selectConsumer);
    }

    /**
     * Executa a operação de pesquisa no repositório para a entidade informada,
     * realizando a consulta através do <code>selectConsumer</code>.
     * Retorna os registros desta entidade.
     *
     * @param selectConsumer Consulta a ser consumida pelo repositório.
     * @return retorna uma lista de objetos da Entidade consultada.
     */
    public List<E> find(Consumer<HqlSelectBuilder> selectConsumer) {
        return findRepository.find(entityClass, selectConsumer);
    }

    /**
     * Executa a operação de pesquisa no repositório basedo na consulta informada por parâmetro limitando a
     * quantidade de objetos retornados. A quantidade será de acordo com o valor definido no parâmetro
     * <code>maxResults</code>.
     *
     * @param selectConsumer Consulta a ser consumida pelo repositório.
     * @param maxResults Número máximo de objetos a serem retornados.
     * @return Lista de objetos que atendam a consulta consumida, limitada ao número máximo de elementos definidos por
     *         parâmetro.
     */
    public List<E> find(Consumer<HqlSelectBuilder> selectConsumer, int maxResults) {
        return findRepository.find(entityClass, selectConsumer, maxResults);
    }

    /**
     * Executa a operação de pesquisa no formato SQL Nativo no repositório
     * para a Coluna informada, realizando a consulta
     * através do  <code>alias</code> e <code>column</code> e <code>selectConsumer</code> .
     * Retorna os registros desta Coluna.
     *
     * @param column         Tipo da Coluna base a ser consultada.
     * @param alias          informa alias da tabela.
     * @param selectConsumer Consulta a ser consumida pelo repositório.
     * @param <R>            Coluna base a ser consultada.
     * @return retorna lista de objetos da coluna consultada.
     */
    public <R> List<R> findColumnNative(String alias, String column, Consumer<SelectBuilder> selectConsumer) {
        return findRepository.findColumnNative(tableName, alias, column, selectConsumer);
    }

    /**
     * Executa a operação de pesquisa no formato SQL Nativo no repositório
     * para a Coluna informada, realizando a consulta
     * através do <code>selectConsumer</code> e <code>column</code>. Retorna os
     * registros desta Coluna.
     *
     * @param column         Tipo da Coluna base a ser consultada.
     * @param selectConsumer Consulta a ser consumida pelo repositório.
     * @param <R>            Coluna base a ser consultada.
     * @return retorna lista de objetos da coluna consultada.
     */
    public <R> List<R> findColumnNative(String column, Consumer<SelectBuilder> selectConsumer) {
        return findRepository.findColumnNative(tableName, column, selectConsumer);
    }

    /**
     * Executa a operação de pesquisa no repositório para a Coluna informada,
     * realizando a consulta através do <code>selectConsumer</code> e <code>alias</code>
     * e <code>column</code>.Retorna os registros desta Coluna.
     *
     * @param column         Tipo da Coluna base a ser consultada.
     * @param alias          informa alias da tabela.
     * @param selectConsumer Consulta a ser consumida pelo repositório.
     * @param <R>            Coluna base a ser consultada.
     * @return retorna lista de objetos da coluna consultada.
     */
    public <R> List<R> findColumn(String alias, String column, Consumer<HqlSelectBuilder> selectConsumer) {
        return findRepository.findColumn(entityClass, alias, column, selectConsumer);
    }

    /**
     * Executa a operação de pesquisa no repositório para a Coluna informada,
     * realizando a consulta através do <code>selectConsumer</code> e <code>column</code>.
     * Retorna os registros desta Coluna.
     *
     * @param column         Tipo da Coluna base a ser consultada.
     * @param selectConsumer Consulta a ser consumida pelo repositório.
     * @param <R>            Coluna base a ser consultada.
     * @return retorna lista de objetos da coluna consultada.
     */
    public <R> List<R> findColumn(String column, Consumer<HqlSelectBuilder> selectConsumer) {
        return findRepository.findColumn(entityClass, column, selectConsumer);
    }

    /**
     * Executa a operação de pesquisa no repositório para a Coluna informada,
     * realizando a consulta através do <code>column</code>
     * e <code>i</code>. Retorna os registros desta Coluna.
     *
     * @param column         Tipo da Coluna base a ser consultada.
     * @param  i             Id da  Entidade a ser consultado(Composto ou Simples).
     * @param <R>            Coluna base a ser consultada.
     * @return retorna lista de objetos da coluna consultada.
     */
    public <R> List<R> findColumn(String column, I i) {
        return findRepository.findColumn(entityClass, column, i);
    }

    /**
     * Executa a operação de pesquisa no formato SQL Nativo no repositório para
     * a Entidade informada, realizando a consulta através do <code>tableName</code>
     * <code>alias</code> e <code>selectConsumer</code> e <code>column</code>.
     * Retorna um registro da Coluna desta Tabela.
     *
     * @param tableName      informa nome da tabela.
     * @param column         Nome da coluna base a ser consultada.
     * @param selectConsumer Consulta a ser consumida pelo repositório.
     * @param alias          informa alias da tabela.
     * @param <R>            Coluna base a ser consultada.
     * @return retorna um registro da Coluna consultada.
     */
    public <R> R findOneColumnNative(String tableName, String alias, String column, Consumer<SelectBuilder>
            selectConsumer) {
        return findRepository.findOneColumnNative(tableName, alias, column, selectConsumer);
    }

    /**
     * Executa a operação de pesquisa no formato SQL Nativo no repositório para
     * a Entidade informada, realizando a consulta através do <code>selectConsumer</code>
     * <code>alias</code> e <code>column</code>.
     * Retorna um registro da Coluna desta Tabela.
     *
     * @param column         Nome da coluna base a ser consultada.
     * @param selectConsumer Consulta a ser consumida pelo repositório.
     * @param alias          informa alias da tabela.
     * @param <R>            Coluna base a ser consultada.
     * @return retorna um registro da Coluna consultada.
     */
    public <R> R findOneColumnNative(String alias, String column, Consumer<SelectBuilder> selectConsumer) {
        return findRepository.findOneColumnNative(tableName, alias, column, selectConsumer);
    }

    /**
     * Executa a operação de pesquisa no formato SQL Nativo no repositório para
     * a Tabela informada, realizando a consulta através do <code>selectConsumer</code>
     * e <code>column</code>. Retorna um registro da Coluna desta Tabela.
     *
     * @param column         Nome da coluna base a ser consultada.
     * @param selectConsumer Consulta a ser consumida pelo repositório.
     * @param <R>            Coluna base a ser consultada.
     * @return retorna um registro da Coluna consultada.
     */
    public <R> R findOneColumnNative(String column, Consumer<SelectBuilder> selectConsumer) {
        return findRepository.findOneColumnNative(tableName, column, selectConsumer);
    }

    /**
     * Executa a operação de pesquisa no repositório para
     * a Entidade informada, realizando a consulta através do <code>column</code>
     * e <code>alias</code> e <code>selectConsumer</code>. Retorna um registro da Coluna desta Entidade.
     *
     * @param alias          informa alias da tabela.
     * @param column         Nome da coluna base a ser consultada.
     * @param selectConsumer Consulta a ser consumida pelo repositório.
     * @param <R>            Coluna base a ser consultada.
     * @return retorna um registro da Coluna consultada.
     */
    public <R> R findOneColumn(String alias, String column, Consumer<HqlSelectBuilder> selectConsumer) {
        return findRepository.findOneColumn(entityClass, alias, column, selectConsumer);
    }

    /**
     * Executa a operação de pesquisa no repositório para a Entidade informada,
     * realizando a consulta através do <code>column</code> e <code>i</code>.
     * Retorna um registro da Coluna desta Entidade.
     *
     * @param column  Nome da coluna base a ser consultada.
     * @param  i  Id da  Entidade a ser consultado(Composto ou Simples).
     * @param <R>  Coluna base a ser consultada.
     * @return retorna um registro da Coluna consultada.
     */
    public <R> R findOneColumn(String column, I i) {
        return findRepository.findOneColumn(entityClass, column, i);
    }

    /**
     * Executa a operação de pesquisa no repositório para
     * a Entidade informada, realizando a consulta através do <code>selectConsumer</code>
     * e <code>column</code>.
     * Retorna um registro da Coluna desta Entidade.
     *
     * @param column         Nome da coluna base a ser consultada.
     * @param selectConsumer Consulta a ser consumida pelo repositório.
     * @param <R>            Coluna base a ser consultada.
     * @return retorna um registro da Coluna consultada.
     */
    public <R> R findOneColumn(String column, Consumer<HqlSelectBuilder> selectConsumer) {
        return findRepository.findOneColumn(entityClass, column, selectConsumer);
    }

    /**
     * Executa a operação de pesquisa no formato SQL Nativo no repositório para
     * a Tabela informada, realizando a consulta através
     * do <code>selectConsumer</code> e <code>alias</code>
     * e <code>i</code>. Retorna um registro da Tabela.
     *
     * @param alias          informa alias da tabela.
     * @param selectConsumer Consulta a ser consumida pelo repositório.
     * @return retorna um registro da Tabela consultada.
     */
    public E findOneNative(String alias, Consumer<SelectBuilder> selectConsumer) {
        return findRepository.findOneNative(tableName, entityClass, alias, selectConsumer);
    }

    /**
     * Executa a operação de pesquisa no formato SQL Nativo no repositório para
     * a Tabela informada, realizando a consulta através do <code>selectConsumer</code>.
     * Retorna um registro da Tabela.
     *
     * @param selectConsumer Consulta a ser consumida pelo repositório.
     * @return retorna um registro da Tabela consultada.
     */
    public E findOneNative(Consumer<SelectBuilder> selectConsumer) {
        return findRepository.findOneNative(tableName, entityClass, selectConsumer);
    }

    /**
     * Executa a operação de pesquisa no repositório para
     * a Entidade informada, realizando a consulta através do <code>selectConsumer</code>
     * e <code>alias</code>.
     * Retorna um registro da Entidade.
     *
     * @param alias          informa alias da tabela.
     * @param selectConsumer Consulta a ser consumida pelo repositório.
     * @return retorna um registro da Entidade consultada.
     */
    public E findOne(String alias, Consumer<HqlSelectBuilder> selectConsumer) {
        return findRepository.findOne(entityClass, alias, selectConsumer);
    }

    /**
     * Executa a operação de pesquisa no repositório para
     * a Entidade informada, realizando a consulta através do <code>selectConsumer</code>.
     * Retorna um registro da Entidade.
     *
     * @param selectConsumer Consulta a ser consumida pelo repositório.
     * @return retorna um registro da Entidade consultada.
     */
    public E findOne(Consumer<HqlSelectBuilder> selectConsumer) {
        return findRepository.findOne(entityClass, selectConsumer);
    }

    /**
     * Executa a operação de pesquisa no repositório para
     * a Entidade informada, realizando a consulta através do
     * <code>i</code>. Retorna um registro desta Entidade.
     *
     * @param  i Id da  Entidade a ser consultado(Composto ou Simples).
     * @return retorna um registro da Entidade consultada.
     */
    public E findOne(I i) {
        return findRepository.findOne(entityClass, i);
    }

    /**
     * Consulta no formato SLQ Nativo no repositório a quantidade de
     * colunas pesquisada referente a Tabela informada
     * sendo filtrada por <code>alias</code> e <code>selectHandler</code>.
     *
     * @param alias  informa alias da tabela.
     * @param selectHandler Consulta a ser consumida pelo repositório.
     * @return retorna quantidade de colunas da Tabela.
     */
    public Long countNative(String alias, Consumer<SelectBuilder> selectHandler) {
        return findRepository.countNative(tableName, alias, selectHandler);
    }

    /**
     * Consulta no formato SLQ Nativo no repositório a quantidade de
     * colunas pesquisada referente a Tabela informada
     * sendo filtrada por <code>selectHandler</code>.
     *
     * @param selectHandler Consulta a ser consumida pelo repositório.
     * @return retorna quantidade de colunas da Tabela.
     */
    public Long countNative(Consumer<SelectBuilder> selectHandler) {
        return findRepository.countNative(tableName, selectHandler);
    }

    /**
     * Consulta no repositório a quantidade de
     * colunas pesquisada referente a Entidade informada
     * sendo filtrada por <code>alias</code> e <code>selectHandler</code>.
     *
     * @param alias          informa alias da tabela.
     * @param selectHandler Consulta a ser consumida pelo repositório.
     * @return retorna quantidade de colunas da Entidade.
     */
    public Long count(String alias, Consumer<HqlSelectBuilder> selectHandler) {
        return findRepository.count(entityClass, alias, selectHandler);
    }

    /**
     * Consulta no repositório a quantidade de
     * colunas pesquisada referente a Entidade informada
     * sendo filtrada por <code>selectHandler</code>.
     *
     * @param selectHandler Consulta a ser consumida pelo repositório.
     * @return retorna quantidade de colunas da Entidade.
     */
    public Long count(Consumer<HqlSelectBuilder> selectHandler) {
        return findRepository.count(entityClass, selectHandler);
    }

    /**
     * Consulta no repositório a quantidade de
     * colunas pesquisada referente a Entidade informada
     * sendo filtrada por <code>i</code>.
     *
     * @param  i  Id da  Entidade a ser consultado(Composto ou Simples).
     * @return retorna quantidade de colunas da Entidade.
     */
    public Long count(I i) {
        return findRepository.count(entityClass, i);
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
        return findRepository.existsNative(tableName, alias, selectHandler);
    }

    /**
     * Consulta executada no formato SQL Nativo no repositório se existe
     * registros na Tabela  que retorne com a consulta informada
     * nos parâmetros <code>tableName</code>
     * e <code>alias</code> e <code>selectHandler</code>.
     *
     * @param alias          informa alias da tabela.
     * @param selectHandler Consulta a ser consumida pelo repositório.
     *
     * @return retorna o status da consulta na Tabela true ou false.
     */
    public boolean existsNative(String alias, Consumer<SelectBuilder> selectHandler) {
        return findRepository.existsNative(tableName, alias, selectHandler);
    }

    /**
     * Consulta executada no formato SQL Nativo no repositório se existe
     * registros na Tabela  que retorne com a consulta informada
     * nos parâmetros <code>selectHandler</code>.
     *
     * @param selectHandler Consulta a ser consumida pelo repositório.
     *
     * @return retorna o status da consulta na Tabela true ou false.
     */
    public boolean existsNative(Consumer<SelectBuilder> selectHandler) {
        return findRepository.existsNative(tableName, selectHandler);
    }

    /**
     * Consulta executada no repositório se existe
     * registros na Entidade  que retorne com a consulta informada
     * nos parâmetros <code>alias</code> e <code>selectHandler</code>.
     *
     * @param alias          informa alias da tabela.
     * @param selectHandler Consulta a ser consumida pelo repositório.
     *
     * @return retorna o status da consulta na Tabela true ou false.
     */
    public boolean exists(String alias, Consumer<HqlSelectBuilder> selectHandler) {
        return findRepository.exists(entityClass, alias, selectHandler);
    }

    /**
     * Consulta executada no repositório se existe
     * registros na Tabela  que retorne com a consulta informada
     * no parâmetro <code>selectHandler</code>.
     *
     * @param selectHandler Consulta a ser consumida pelo repositório.
     *
     * @return retorna o status da consulta na Tabela true ou false.
     */
    public boolean exists(Consumer<HqlSelectBuilder> selectHandler) {
        return findRepository.exists(entityClass, selectHandler);
    }

    /**
     * Consulta executada no repositório se existe
     * registros na Entidade  que retorne com a consulta informada
     * nos parâmetros <code>entityClass</code>.
     *
     * @param  i Id da  Entidade a ser consultado(Composto ou Simples).
     *
     * @return retorna o status da consulta na Entidade true ou false.
     */
    public boolean exists(I i) {
        return findRepository.exists(entityClass, i);
    }

    /**
     * Executa a operação de persistência da entidade
     * no banco de dados. Retorna o Objeto da Entidade persistido.
     *
     * @param entity    objeto da entidade a ser persistido.
     * @return Retorna o objeto da Entidade persistido.
     */
    public E save(E entity) {
        return persistRepository.save(entity);
    }

    /**
     * Executa a operação de persistência da entidade
     * no banco de dados. Retorna uma lista de objetos da Entidade persistidos.
     *
     * @param entities lista de objeto da entidade a serem persistidos.
     * @return retorna uma lista de objetos da Entidade persistidos.
     */
    public Collection<E> save(Collection<E> entities) {
        return persistRepository.save(entities);
    }

    /**
     * Executa a operação de update da entidade no Formato de SQL Nativo
     * no repositório através do <code>alias</code> e <code>updateHandler</code>.
     *
     * @param alias  informa alias da tabela.
     * @param updateHandler executa query que será atualizada no repositório.
     */
    public void updateNative(String alias, Consumer<UpdateBuilder> updateHandler) {
        persistRepository.updateNative(tableName, alias, updateHandler);
    }

    /**
     * Executa a operação de update da entidade no Formato de SQL Nativo
     * no repositório através do  <code>updateHandler</code>.
     *
     * @param updateHandler executa query que será atualizada no repositório.
     */
    public void updateNative(Consumer<UpdateBuilder> updateHandler) {
        persistRepository.updateNative(tableName, updateHandler);
    }

    /**
     * Executa a operação de update da entidade
     * no repositório através do <code>alias</code> e <code>updateHandler</code>.
     *
     * @param alias  informa alias da tabela.
     * @param updateHandler executa query que será atualizada no repositório.
     */
    public void update(String alias, Consumer<HqlUpdateBuilder> updateHandler) {
        persistRepository.update(entityClass, alias, updateHandler);
    }

    /**
     * Executa a operação de update da entidade
     * no repositório através do <code>updateHandler</code>.
     *
     * @param updateHandler executa query que será atualizada no repositório.
     */
    public void update(Consumer<HqlUpdateBuilder> updateHandler) {
        persistRepository.update(entityClass, updateHandler);
    }

    /**
     * Executa a operação de deletar a entidade
     * no repositório através do <code>alias</code> e <code>deleteHandler</code>.
     *
     * @param alias  informa alias da tabela.
     * @param deleteHandler executa query que excluirá o registro no repositório.
     */
    public void deleteNative(String alias, Consumer<DeleteBuilder> deleteHandler) {
        persistRepository.deleteNative(tableName, alias, deleteHandler);
    }

    /**
     * Executa a operação de deletar a entidade no formato de SQL Nativo
     * no repositório através do <code>tableName</code>
     * e <code>deleteHandler</code>.
     *
     * @param deleteHandler executa query que excluirá o registro no repositório.
     */
    public void deleteNative(Consumer<DeleteBuilder> deleteHandler) {
        persistRepository.deleteNative(tableName, deleteHandler);
    }

    /**
     * Executa a operação de deletar a entidade no formato de SQL Nativo
     * no repositório através do <code>alias</code> e <code>deleteHandler</code>.
     *
     * @param alias  informa alias da tabela.
     * @param deleteHandler executa query que  excluirá o registro no repositório.
     */
    public void delete(String alias, Consumer<HqlDeleteBuilder> deleteHandler) {
        persistRepository.delete(entityClass, alias, deleteHandler);
    }

    /**
     * Executa a operação de deletar a entidade.
     * no repositório através do <code>deleteHandler</code>.
     *
     * @param deleteHandler executa query que  excluirá o registro no repositório.
     */
    public void delete(Consumer<HqlDeleteBuilder> deleteHandler) {
        persistRepository.delete(entityClass, deleteHandler);
    }

    /**
     * Executa a operação de deletar a entidade
     * no repositório através do <code>id</code>.
     *
     * @param  id  Id da  Entidade a ser excluída(Composto ou Simples).
     */
    public void delete(I id) {
        persistRepository.delete(entityClass, id);
    }

    /**
     * Executa a operação de deletar a entidade
     * no repositório através do <code>id</code>.
     *
     * @param  ids  lista de Ids da  Entidade a ser excluída(Composto ou Simples).
     */
    public void delete(Iterable<I> ids) {
        persistRepository.delete(entityClass, ids);
    }

    /**
     * Método responsável por retornar uma Entidade.
     *
     * @return retorna a Entidade base.
     */
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