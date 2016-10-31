package br.gov.to.sefaz.persistence.satquery.parser.jsql.visitor;

import br.gov.to.sefaz.persistence.satquery.handler.SatSqlInterceptorHandler;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.SelectVisitor;
import net.sf.jsqlparser.statement.select.SetOperationList;
import net.sf.jsqlparser.statement.select.WithItem;

/**
 * Implementação de um {@link SelectVisitor} para atender as regras de negocio do projeto.
 * Este componente serve para visitar diferentes partes de um select e altera-las se necessário
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 05/10/2016 17:55:00
 */
public class SatSelectVisitor implements SelectVisitor {

    private final SatSqlInterceptorHandler interceptorHandler;

    public SatSelectVisitor(SatSqlInterceptorHandler interceptorHandler) {
        this.interceptorHandler = interceptorHandler;
    }

    /**
     * {@inheritDoc}
     * Se o FROM for em uma tabela, altera o where para colocar a condição de exclusão logica.
     * Se o FROM for um subselect, aplica este visitor de maneira recursiva.
     *
     * @param plainSelect estrutura do select
     */
    @Override
    public void visit(PlainSelect plainSelect) {
        interceptorHandler.handlePlainSelect(plainSelect);
    }

    @Override
    public void visit(SetOperationList setOpList) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(WithItem withItem) {
        // Implementado por contrato com interface
    }
}
