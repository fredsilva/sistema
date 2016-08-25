package br.gov.to.sefaz.persistence.query.builder.sql.select.signature;

import br.gov.to.sefaz.persistence.query.structure.domain.Alias;
import br.gov.to.sefaz.persistence.query.structure.select.join.JoinType;

/**
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 01/08/2016 11:15:00
 */
public interface Joinable<R> {

    R join(Alias<String> alias, JoinType type);

    R innerJoin(String table);

    R innerJoin(String table, String alias);

    R leftJoin(String table);

    R leftJoin(String table, String alias);

    R rightJoin(String table);

    R rightJoin(String table, String alias);

    R fullJoin(String table);

    R fullJoin(String table, String alias);
}
