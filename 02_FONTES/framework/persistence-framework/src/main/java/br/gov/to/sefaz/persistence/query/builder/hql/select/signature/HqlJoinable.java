package br.gov.to.sefaz.persistence.query.builder.hql.select.signature;

import br.gov.to.sefaz.persistence.query.builder.sql.select.signature.Joinable;
import br.gov.to.sefaz.persistence.query.structure.domain.Alias;
import br.gov.to.sefaz.persistence.query.structure.select.join.JoinType;

/**
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 01/08/2016 11:15:00
 */
public interface HqlJoinable<R> extends Joinable<R> {

    R joinFetch(Alias<String> alias, JoinType type);

    R innerJoinFetch(String table);

    R innerJoinFetch(String table, String alias);

    R leftJoinFetch(String field);

    R leftJoinFetch(String field, String alias);

    R rightJoinFetch(String field);

    R rightJoinFetch(String field, String alias);

    R fullJoinFetch(String field);

    R fullJoinFetch(String field, String alias);
}
