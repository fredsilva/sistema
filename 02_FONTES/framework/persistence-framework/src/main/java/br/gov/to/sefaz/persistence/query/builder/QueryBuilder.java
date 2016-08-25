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
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 27/06/2016 17:17:00
 */
public class QueryBuilder {

    public static SelectBuilder sqlSelect(String table) {
        return new SelectBuilder(table);
    }

    public static SelectBuilder sqlSelect(String table, String alias) {
        return new SelectBuilder(table, alias);
    }

    public static SelectBuilder sqlSelect(QueryStructureBuilder<SelectBuilder, ?> select) {
        return new SelectBuilder(select);
    }

    public static UpdateBuilder sqlUpdate(String table) {
        return new UpdateBuilder(table);
    }

    public static UpdateBuilder sqlUpdate(String table, String alias) {
        return new UpdateBuilder(table, alias);
    }

    public static DeleteBuilder sqlDelete(String from) {
        return new DeleteBuilder(from);
    }

    public static DeleteBuilder sqlDelete(String from, String alias) {
        return new DeleteBuilder(from, alias);
    }

    public static InsertBuilder sqlInsert(String into) {
        return new InsertBuilder(into);
    }

    public static HqlSelectBuilder hqlSelect(Class<?> entityClass) {
        return new HqlSelectBuilder(entityClass);
    }

    public static HqlSelectBuilder hqlSelect(Class<?> entityClass, String alias) {
        return new HqlSelectBuilder(entityClass, alias);
    }

    public static HqlUpdateBuilder hqlUpdate(Class<?> entityClass) {
        return new HqlUpdateBuilder(entityClass);
    }

    public static HqlUpdateBuilder hqlUpdate(Class<?> entityClass, String alias) {
        return new HqlUpdateBuilder(entityClass, alias);
    }

    public static HqlDeleteBuilder hqlDelete(Class<?> entityClass) {
        return new HqlDeleteBuilder(entityClass);
    }

    public static HqlDeleteBuilder hqlDelete(Class<?> entityClass, String alias) {
        return new HqlDeleteBuilder(entityClass, alias);
    }

    public static HqlInsertBuilder hqlInsert(Class<?> entityClass) {
        return new HqlInsertBuilder(entityClass);
    }
}
