package br.gov.to.sefaz.persistence.query.builder.sql.where;

/**
 * Classe responsável por criar a estrutura
 * de construção da operação de Junção.
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 30/06/2016 10:01:00
 * @param <T> Entidade base.
 */
public class JunctionBuilder<T> extends AbstractJunctionBuilder<WhereBuilder<T>, T> {

    public JunctionBuilder(WhereBuilder<T> root) {
        super(root);
    }
}