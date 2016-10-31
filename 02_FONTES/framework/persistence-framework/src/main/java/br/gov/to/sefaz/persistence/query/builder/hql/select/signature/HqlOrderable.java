package br.gov.to.sefaz.persistence.query.builder.hql.select.signature;

import br.gov.to.sefaz.persistence.query.builder.sql.select.signature.Orderable;

/**
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 01/08/2016 11:15:00
 */
public interface HqlOrderable<R> extends Orderable<R> {

    R orderById();
}
