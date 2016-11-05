package br.gov.to.sefaz.persistence.repository;

import br.gov.to.sefaz.persistence.query.builder.hql.delete.HqlDeleteBuilder;
import br.gov.to.sefaz.persistence.query.builder.hql.handler.EntityHandler;
import br.gov.to.sefaz.persistence.query.builder.hql.update.HqlUpdateBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.delete.DeleteBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.update.UpdateBuilder;
import br.gov.to.sefaz.persistence.query.parser.domain.ResultQuery;
import br.gov.to.sefaz.persistence.satquery.parser.SatQueryParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import static br.gov.to.sefaz.persistence.query.builder.QueryBuilder.hqlDelete;
import static br.gov.to.sefaz.persistence.query.builder.QueryBuilder.hqlUpdate;
import static br.gov.to.sefaz.persistence.query.builder.QueryBuilder.sqlDelete;
import static br.gov.to.sefaz.persistence.query.builder.QueryBuilder.sqlUpdate;

/**
 * Classe que gerencia os comandos de Insert, Delete, e Update das Entidades.
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 15/07/2016 11:00:00
 */
@Component
public class PersistRepository {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private SatQueryParser parser;

    /**
     * Executa a operação de persistência da entidade
     * no banco de dados. Retorna o Objeto da Entidade persistido.
     *
     * @param entity    objeto da entidade a ser persistido.
     * @param <E>       Entidade base a ser persistida.
     * @return Retorna o Objeto da Entidade persistido.
     */
    @Transactional
    public <E> E save(E entity) {
        E merge = entityManager.merge(entity);
        entityManager.flush();
        return merge;
    }

    /**
     * Executa a operação de persistência da entidade
     * no banco de dados. Retorna uma lista de objetos da Entidade persistidos.
     *
     * @param entities lista de objeto da entidade a serem persistidos.
     * @param <E>    Entidade base a ser persistida.
     * @return retorna uma lista de objetos da Entidade persistidos.
     */
    @Transactional
    public <E> Collection<E> save(Collection<E> entities) {
        for (E entity : entities) {
            entityManager.merge(entity);
        }

        entityManager.flush();
        return entities;
    }

    /**
     * Executa a operação de update da entidade
     * no repositório através do <code>updateBuilder</code>.
     *
     * @param updateBuilder executa query que será atualizada no repositório.
     */
    public void update(HqlUpdateBuilder updateBuilder) {
        executeUpdate(parser.parseUpdate(updateBuilder));
    }

    /**
     * Executa a operação de update da entidade
     * no repositório através do <code>tableName</code> e <code>alias</code>
     * e <code>updateHandler</code>.
     *
     * @param entityClass    Tipo da entidade base a ser atualizada.
     * @param updateHandler executa query que será atualizada no repositório.
     * @param <E>  Entidade base a ser atualizada.
     */
    @Transactional
    public <E> void update(Class<E> entityClass, Consumer<HqlUpdateBuilder> updateHandler) {
        HqlUpdateBuilder updateBuilder = hqlUpdate(entityClass);
        updateHandler.accept(updateBuilder);
        update(updateBuilder);
    }

    /**
     * Executa a operação de update da entidade
     * no repositório através do <code>tableName</code> e <code>alias</code>
     * e <code>updateHandler</code>.
     *
     * @param entityClass    Tipo da entidade base a ser atualizada.
     * @param alias  informa alias da tabela.
     * @param updateHandler executa query que será atualizada no repositório.
     * @param <E>  Entidade base a ser atualizada.
     */
    @Transactional
    public <E> void update(Class<E> entityClass, String alias, Consumer<HqlUpdateBuilder> updateHandler) {
        HqlUpdateBuilder updateBuilder = hqlUpdate(entityClass, alias);
        updateHandler.accept(updateBuilder);
        update(updateBuilder);
    }

    /**
     * Executa a operação de update da entidade no Formato de SQL Nativo
     * no repositório através do <code>updateBuilder</code>.
     *
     * @param updateBuilder executa query que será atualizada no repositório.
     */
    public void updateNative(UpdateBuilder updateBuilder) {
        executeUpdateNative(parser.parseUpdate(updateBuilder));
    }

    /**
     * Executa a operação de update da entidade no Formato de SQL Nativo
     * no repositório através do <code>tableName</code> e <code>alias</code>
     * e <code>updateHandler</code>.
     *
     * @param tableName informa nome da tabela.
     * @param alias  informa alias da tabela.
     * @param updateHandler executa query que será atualizada no repositório.
     */
    @Transactional
    public void updateNative(String tableName, String alias, Consumer<UpdateBuilder> updateHandler) {
        UpdateBuilder updateBuilder = sqlUpdate(tableName, alias);
        updateHandler.accept(updateBuilder);
        updateNative(updateBuilder);
    }

    /**
     * Executa a operação de update da entidade no Formato de SQL Nativo
     * no repositório através do <code>tableName</code> e <code>updateHandler</code>.
     *
     * @param tableName informa nome da tabela.
     * @param updateHandler executa query que será atualizada no repositório.
     */
    @Transactional
    public void updateNative(String tableName, Consumer<UpdateBuilder> updateHandler) {
        UpdateBuilder updateBuilder = sqlUpdate(tableName);
        updateHandler.accept(updateBuilder);
        updateNative(updateBuilder);
    }

    /**
     * Executa a operação de deletar a entidade com formato SQL Nativo
     * no repositório através do <code>deleteBuilder</code>.
     *
     * @param deleteBuilder executa query que  excluirá o registro no repositório.
     */
    public void deleteNative(DeleteBuilder deleteBuilder) {
        executeUpdateNative(parser.parseDelete(deleteBuilder));
    }

    /**
     * Executa a operação de deletar a entidade
     * no repositório através do <code>tableName</code> e <code>alias</code>
     * e <code>deleteHandler</code>.
     *
     * @param tableName informa nome da tabela.
     * @param alias  informa alias da tabela.
     * @param deleteHandler executa query que  excluirá o registro no repositório.
     */
    @Transactional
    public void deleteNative(String tableName, String alias, Consumer<DeleteBuilder> deleteHandler) {
        DeleteBuilder deleteBuilder = sqlDelete(tableName, alias);
        deleteHandler.accept(deleteBuilder);
        deleteNative(deleteBuilder);
    }

    /**
     * Executa a operação de deletar a entidade no formato de SQL Nativo
     * no repositório através do <code>tableName</code>
     * e <code>deleteHandler</code>.
     *
     * @param tableName informa nome da tabela.
     * @param deleteHandler executa query que  excluirá o registro no repositório.
     */
    @Transactional
    public void deleteNative(String tableName, Consumer<DeleteBuilder> deleteHandler) {
        DeleteBuilder deleteBuilder = sqlDelete(tableName);
        deleteHandler.accept(deleteBuilder);
        deleteNative(deleteBuilder);
    }

    /**
     * Executa a operação de deletar a entidade no formato de SQL Nativo
     * no repositório através do <code>entityClass</code>
     * e <code>alias</code> e <code>deleteHandler</code>.
     *
     * @param entityClass    Tipo da entidade base a ser deletada.
     * @param alias  informa alias da tabela.
     * @param deleteHandler executa query que  excluirá o registro no repositório.
     * @param <E>  Entidade base a ser deletada.
     */
    @Transactional
    public <E> void delete(Class<E> entityClass, String alias, Consumer<HqlDeleteBuilder> deleteHandler) {
        HqlDeleteBuilder deleteBuilder = hqlDelete(entityClass, alias);
        deleteHandler.accept(deleteBuilder);
        delete(deleteBuilder);
    }

    /**
     * Executa a operação de deletar a entidade
     * no repositório através do <code>entityClass</code>
     * e <code>deleteHandler</code>.
     *
     * @param entityClass    Tipo da entidade base a ser deletada.
     * @param deleteHandler executa query que  excluirá o registro no repositório.
     * @param <E>  Entidade base a ser deletada.
     */
    @Transactional
    public <E> void delete(Class<E> entityClass, Consumer<HqlDeleteBuilder> deleteHandler) {
        HqlDeleteBuilder deleteBuilder = hqlDelete(entityClass);
        deleteHandler.accept(deleteBuilder);
        delete(deleteBuilder);
    }

    /**
     * Executa a operação de deletar a entidade
     * no repositório através do <code>entityClass</code>
     * e <code>id</code>.
     *
     * @param entityClass    Tipo da entidade base a ser deletada.
     * @param  id  Id da  Entidade a ser excluída(Composto ou Simples).
     * @param <E>  Entidade base a ser deletada.
     */
    @Transactional
    public <E> void delete(Class<E> entityClass, Object id) {
        HqlDeleteBuilder hqlDeleteBuilder = hqlDelete(entityClass);
        hqlDeleteBuilder.whereId(id);
        delete(hqlDeleteBuilder);
    }

    /**
     * Executa a operação de deletar a entidade
     * no repositório através do <code>entityClass</code>
     * e <code>id</code>.
     *
     * @param entityClass    Tipo da entidade base a ser deletada.
     * @param  ids  lista de Ids da  Entidade a ser excluída(Composto ou Simples).
     * @param <E>  Entidade base a ser deletada.
     */
    @Transactional
    public <E> void delete(Class<E> entityClass, Iterable<?> ids) {
        List<String> idFields = EntityHandler.getIdFields(entityClass);
        if (idFields.size() > 1) {
            for (Object id : ids) {
                ResultQuery resultQuery = parser.parseDelete(hqlDelete(entityClass).whereId(id));
                Query query = entityManager.createQuery(resultQuery.getQuery());
                setParams(query, resultQuery.getParams());

                query.executeUpdate();
            }
            entityManager.flush();
        } else {
            HqlDeleteBuilder hqlDeleteBuilder = hqlDelete(entityClass);
            hqlDeleteBuilder.where().in(idFields.get(0), ids);
            delete(hqlDeleteBuilder);
        }
    }

    // ####################################################################################
    // Métodos base, pense antes de utilizar, utilize apenas se for realmente necessário
    // ####################################################################################

    /**
     * Executa a operação de deletar a entidade
     * no repositório através do <code>deleteBuilder</code>.
     *
     * @param deleteBuilder executa query que  excluirá o registro no repositório.
     */
    public void delete(HqlDeleteBuilder deleteBuilder) {
        executeUpdate(parser.parseDelete(deleteBuilder));
    }

    private void executeUpdateNative(ResultQuery resultQuery) {
        Query query = entityManager.createNativeQuery(resultQuery.getQuery());
        setParams(query, resultQuery.getParams());
        query.executeUpdate();

        entityManager.flush();
    }

    private void executeUpdate(ResultQuery resultQuery) {
        Query query = entityManager.createQuery(resultQuery.getQuery());
        setParams(query, resultQuery.getParams());
        query.executeUpdate();

        entityManager.flush();
    }

    private void setParams(Query query, Map<String, Object> params) {
        params.entrySet().forEach(param -> query.setParameter(param.getKey(), param.getValue()));
    }
}
