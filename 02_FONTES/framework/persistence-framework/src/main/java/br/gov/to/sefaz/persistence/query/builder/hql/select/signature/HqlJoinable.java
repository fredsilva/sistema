package br.gov.to.sefaz.persistence.query.builder.hql.select.signature;

import br.gov.to.sefaz.persistence.query.builder.sql.select.signature.Joinable;
import br.gov.to.sefaz.persistence.query.structure.domain.Alias;
import br.gov.to.sefaz.persistence.query.structure.select.join.JoinType;

/**
 * Interface que executa as instruções dos comandos de Junções do HQL.
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 01/08/2016 11:15:00
 * @param <R> Entidade base a ser consultada.
 */
public interface HqlJoinable<R> extends Joinable<R> {

    /**
     * Assinatura do método responsável por montar o comando de junção do HQL.
     *
     * @param alias informa o apelido da tabela.
     * @param type informa o tipo de instrução Join que será aplicada.
     *
     * @return retornar a montagem do comando do join do HQL.
     */
    R joinFetch(Alias<String> alias, JoinType type);

    /**
     * Assinatura do método responsável por montar o comando de INNER JOIN do HQL.
     *
     * @param table informa o nome da tabela.
     *
     * @return retornar a montagem da consulta executando o comando da instrução do INNER JOIN na Tabela informa.
     */
    R innerJoinFetch(String table);

    /**
     * Assinatura do método responsável por montar o comando de INNER JOIN do HQL.
     *
     * @param table informa o nome da tabela.
     * @param alias informa o apelido da tabela.
     *
     * @return retornar a montagem da consulta executando o comando da instrução do INNER JOIN na Tabela informa.
     */
    R innerJoinFetch(String table, String alias);

    /**
     * Assinatura do método responsável por montar o comando de LEFT JOIN do HQL.
     *
     * @param field informa o nome da campo.
     *
     * @return retornar a montagem da consulta executando o comando da instrução do LEFT JOIN na Tabela informa.
     */
    R leftJoinFetch(String field);

    /**
     * Assinatura do método responsável por montar o comando de LEFT JOIN do HQL.
     *
     * @param field informa o nome da campo.
     * @param alias informa o apelido da tabela.
     *
     * @return retornar a montagem da consulta executando o comando da instrução do LEFT JOIN na Tabela informa.
     */
    R leftJoinFetch(String field, String alias);

    /**
     * Assinatura do método responsável por montar o comando de RIGHT JOIN do HQL.
     *
     * @param field informa o nome da campo.
     *
     * @return retornar a montagem da consulta executando o comando da instrução do RIGHT JOIN na Tabela informa.
     */
    R rightJoinFetch(String field);

    /**
     * Assinatura do método responsável por montar o comando de RIGHT JOIN do HQL.
     *
     * @param field informa o nome da campo.
     * @param alias informa o apelido da tabela.
     *
     * @return retornar a montagem da consulta executando o comando da instrução do RIGHT JOIN na Tabela informa.
     */
    R rightJoinFetch(String field, String alias);

    /**
     * Assinatura do método responsável por montar o comando de FULL JOIN do HQL.
     *
     * @param field informa o nome da campo.
     *
     * @return retornar a montagem da consulta executando o comando da instrução do FULL JOIN na Tabela informa.
     */
    R fullJoinFetch(String field);

    /**
     * Assinatura do método responsável por montar o comando de FULL JOIN do HQL.
     *
     * @param field informa o nome da campo.
     * @param alias informa o apelido da tabela.
     *
     * @return retornar a montagem da consulta executando o comando da instrução do FULL JOIN na Tabela informa.
     */
    R fullJoinFetch(String field, String alias);
}
