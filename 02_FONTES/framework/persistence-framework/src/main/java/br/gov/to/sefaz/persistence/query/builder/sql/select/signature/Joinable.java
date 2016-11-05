package br.gov.to.sefaz.persistence.query.builder.sql.select.signature;

import br.gov.to.sefaz.persistence.query.structure.domain.Alias;
import br.gov.to.sefaz.persistence.query.structure.select.join.JoinType;

/**
 * Interface que executa as instruções dos
 * comandos de Junções do SQL.
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 01/08/2016 11:15:00
 *
 * @param <R> entidade base consultada.
 */
public interface Joinable<R> {

    /**
     * Assinatura do método responsável por montar o comando de junção do SQL.
     *
     * @param alias informa o apelido da tabela.
     * @param type informa o tipo de instrução Join que será aplicada.
     *
     * @return retornar a montagem do comando do join do SQL.
     */
    R join(Alias<String> alias, JoinType type);

    /**
     * Assinatura do método responsável por montar o comando de INNER JOIN do SQL.
     *
     * @param table informa o nome da tabela.
     *
     * @return retornar a montagem da consulta executando o comando da instrução do INNER JOIN na Tabela informa.
     */
    R innerJoin(String table);

    /**
     * Assinatura do método responsável por montar o comando de INNER JOIN do SQL.
     *
     * @param table informa o nome da tabela.
     * @param alias informa o apelido da tabela.
     *
     * @return retornar a montagem da consulta executando o comando da instrução do INNER JOIN na Tabela informa.
     */
    R innerJoin(String table, String alias);

    /**
     * Assinatura do método responsável por montar o comando de LEFT JOIN do SQL.
     *
     * @param table informa o nome da tabela.
     *
     * @return retornar a montagem da consulta executando o comando da instrução do LEFT JOIN na Tabela informa.
     */
    R leftJoin(String table);

    /**
     * Assinatura do método responsável por montar o comando de LEFT JOIN do SQL.
     *
     * @param table informa o nome da tabela.
     * @param alias informa o apelido da tabela.
     *
     * @return retornar a montagem da consulta executando o comando da instrução do LEFT JOIN na Tabela informa.
     */
    R leftJoin(String table, String alias);

    /**
     * Assinatura do método responsável por montar o comando de RIGHT JOIN do SQL.
     *
     * @param table informa o nome da tabela.
     *
     * @return retornar a montagem da consulta executando o comando da instrução do RIGHT JOIN na Tabela informa.
     */
    R rightJoin(String table);

    /**
     * Assinatura do método responsável por montar o comando de RIGHT JOIN do SQL.
     *
     * @param table informa o nome da tabela.
     * @param alias informa o apelido da tabela.
     *
     * @return retornar a montagem da consulta executando o comando da instrução do RIGHT JOIN na Tabela informa.
     */
    R rightJoin(String table, String alias);

    /**
     * Assinatura do método responsável por montar o comando de FULL JOIN do SQL.
     *
     * @param table informa o nome da tabela.
     *
     * @return retornar a montagem da consulta executando o comando da instrução do FULL JOIN na Tabela informa.
     */
    R fullJoin(String table);

    /**
     * Assinatura do método responsável por montar o comando de FULL JOIN do SQL.
     *
     * @param table informa o nome da tabela.
     * @param alias informa o apelido da tabela.
     *
     * @return retornar a montagem da consulta executando o comando da instrução do FULL JOIN na Tabela informa.
     */
    R fullJoin(String table, String alias);
}
