package br.gov.to.sefaz.persistence.configuration;

import br.gov.to.sefaz.exception.UnhandledException;
import br.gov.to.sefaz.persistence.satquery.handler.SatSqlInterceptorHandler;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.update.Update;
import org.hibernate.resource.jdbc.spi.StatementInspector;

/**
 * Classe responsavel pela interceptação de SQLs gerados pelo hibernate e alteração de acordo com as regras
 * de negocio do sistema.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 04/10/2016 10:44:00
 */
@SuppressWarnings("PMD.CloseResource")
public class HibernateSqlInterceptor implements StatementInspector {

    private final SatSqlInterceptorHandler interceptorHandler;

    public HibernateSqlInterceptor() {
        interceptorHandler = new SatSqlInterceptorHandler();
    }

    /**
     * {@inheritDoc}
     * Exlusão ligica:
     *  - Se for um select, delega o statement para {@link SatSqlInterceptorHandler#handleSelect}.
     *  - Se for um update, delega o statement para {@link SatSqlInterceptorHandler#handleUpdate}.
     *  - Se for um delete, delega o statement para {@link SatSqlInterceptorHandler#handleDelete}.
     *  - Caso não se aplique a nenhuma regra anterior não faz nada e retorna o SQL exatamente como veio.
     *
     * @param sql gerado pelo hibernate
     * @return sql tratado de acordo com as regras de negocio do sistema
     */
    @Override
    public String inspect(String sql) {
        try {
            Statement statement = CCJSqlParserUtil.parse(sql);

            if (statement instanceof Select) {
                statement = interceptorHandler.handleSelect((Select) statement);
            } else if (statement instanceof Update) {
                statement = interceptorHandler.handleUpdate((Update) statement);
            } else if (statement instanceof Delete) {
                statement = interceptorHandler.handleDelete((Delete) statement);
            } else {
                return sql;
            }

            return statement.toString();
        } catch (JSQLParserException e) {
            throw new UnhandledException("Erro ao fazer parse de SQL", e);
        }
    }
}