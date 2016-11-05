package br.gov.to.sefaz.persistence.query.builder;

import br.gov.to.sefaz.persistence.query.builder.hql.delete.HqlDeleteBuilder;
import br.gov.to.sefaz.persistence.query.builder.hql.insert.HqlInsertBuilder;
import br.gov.to.sefaz.persistence.query.builder.hql.select.HqlSelectBuilder;
import br.gov.to.sefaz.persistence.query.builder.hql.update.HqlUpdateBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.delete.DeleteBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.insert.InsertBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.select.SelectBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.update.UpdateBuilder;

/**
 * Classe responsável por controlar os comandos do QueryBuilder.
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 27/06/2016 17:17:00
 */
public class QueryBuilder {

    /**
     * Método responsável por montar dinamicamente um comando Select do sql nativo.
     *
     * @param table variável que informa o nome da Tabela.
     * @return retorna a montagem da consulta a qual foi informada na tabela.
     */
    public static SelectBuilder sqlSelect(String table) {
        return new SelectBuilder(table);
    }

    /**
     * Método responsável por montar dinamicamente um comando de Select no sql nativo.
     *
     * @param table variável que informa o nome da Tabela.
     * @param alias variável que informa o apelido da Tabela.
     * @return retorna a montagem da consulta a qual foi informada na tabela.
     * */
    public static SelectBuilder sqlSelect(String table, String alias) {
        return new SelectBuilder(table, alias);
    }

    /**
     * Método responsável por montar dinamicamente um comando de Select no sql nativo.
     *
     * @param select variável que informa o nome da Tabela.
     * @return retorna a montagem da consulta a qual foi informada na tabela.
     * */
    public static SelectBuilder sqlSelect(QueryStructureBuilder<SelectBuilder, ?> select) {
        return new SelectBuilder(select);
    }

    /**
     * Método responsável por montar dinamicamente um comando de Update no sql nativo.
     *
     * @param table variável que informa o nome da Tabela.
     * @return retorna a montagem do comando de atualização na tabela informada.
     * */
    public static UpdateBuilder sqlUpdate(String table) {
        return new UpdateBuilder(table);
    }

    /**
     * Método responsável por montar dinamicamente um comando de Update no sql nativo.
     *
     * @param table variável que informa o nome da Tabela.
     * @param alias variável que informa o apelido da Tabela.
     *
     * @return retorna a montagem do comando de atualização na tabela informada.
     * */
    public static UpdateBuilder sqlUpdate(String table, String alias) {
        return new UpdateBuilder(table, alias);
    }

    /**
     * Método responsável por montar dinamicamente um comando de Exclusão no sql nativo.
     *
     * @param from variável que contém a sentença de exclusão.
     *
     * @return retorna a montagem do comando que executará a exclusão do registro informado na tabela.
     * */
    public static DeleteBuilder sqlDelete(String from) {
        return new DeleteBuilder(from);
    }

    /**
     * Método responsável por montar dinamicamente um comando de Exclusão no sql nativo.
     *
     * @param from variável que contém a sentença de exclusão.
     * @param alias variável que informa o apelido da Tabela.
     *
     * @return retorna a montagem do comando que executará a exclusão do registro informado na tabela.
     * */
    public static DeleteBuilder sqlDelete(String from, String alias) {
        return new DeleteBuilder(from, alias);
    }

    /**
     * Método responsável por montar dinamicamente um comando de Inserção no sql nativo.
     *
     * @param into variável que contém a sentença de inclusão.
     *
     * @return retorna a montagem do comando que executará a inclusão dos parâmetros na tabela informada.
     * */
    public static InsertBuilder sqlInsert(String into) {
        return new InsertBuilder(into);
    }

    /**
     * Método responsável por montar dinamicamente um comando Select no HQL.
     *
     * @param entityClass Tipo da entidade base a ser consultada.
     * @return retorna a montagem da consulta a qual foi informada a tabela.
     * */
    public static HqlSelectBuilder hqlSelect(Class<?> entityClass) {
        return new HqlSelectBuilder(entityClass);
    }

    /**
     * Método responsável por montar dinamicamente um comando de Select no HQL.
     *
     * @param entityClass Tipo da entidade base a ser consultada.
     * @param alias variável que informa o apelido da Tabela.
     * @return retorna a montagem da consulta a qual foi informada a tabela.
     * */
    public static HqlSelectBuilder hqlSelect(Class<?> entityClass, String alias) {
        return new HqlSelectBuilder(entityClass, alias);
    }

    /**
     * Método responsável por montar dinamicamente um comando de Update no HQL.
     *
     * @param entityClass Tipo da entidade base a ser atualizada.
     * @return retorna a montagem do comando de atualização da tabela informada.
     * */
    public static HqlUpdateBuilder hqlUpdate(Class<?> entityClass) {
        return new HqlUpdateBuilder(entityClass);
    }

    /**
     * Método responsável por montar dinamicamente um comando de Update no HQL.
     *
     * @param entityClass Tipo da entidade base a ser atualizada.
     * @param alias variável que informa o apelido da Tabela.
     * @return retorna a montagem do comando de atualização da tabela informada.
     * */
    public static HqlUpdateBuilder hqlUpdate(Class<?> entityClass, String alias) {
        return new HqlUpdateBuilder(entityClass, alias);
    }

    /**
     * Método responsável por montar dinamicamente um comando de Exclusão no HQL.
     *
     * @param entityClass Tipo da entidade base a ser excluída.
     *
     * @return retorna a montagem do comando que executará a exclusão do registro informado na tabela.
     * */
    public static HqlDeleteBuilder hqlDelete(Class<?> entityClass) {
        return new HqlDeleteBuilder(entityClass);
    }

    /**
     * Método responsável por montar dinamicamente um comando de Exclusão no HQL.
     *
     * @param entityClass Tipo da entidade base a ser excluída.
     * @param alias variável que informa o apelido da Tabela.
     *
     * @return retorna a montagem do comando que executará a exclusão do registro informado a tabela.
     */
    public static HqlDeleteBuilder hqlDelete(Class<?> entityClass, String alias) {
        return new HqlDeleteBuilder(entityClass, alias);
    }

    /**
     * Método responsável por montar dinamicamente um comando de Inserção no HQL.
     *
     * @param entityClass Tipo da entidade base a ser inserida.
     *
     * @return retorna a montagem do comando que executará a inclusão dos parâmetros na tabela informada.
     */
    public static HqlInsertBuilder hqlInsert(Class<?> entityClass) {
        return new HqlInsertBuilder(entityClass);
    }
}
