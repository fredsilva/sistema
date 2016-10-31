package br.gov.to.sefaz.persistence.satquery.handler;

import br.gov.to.sefaz.persistence.configuration.AuditableTablesIdentifier;
import br.gov.to.sefaz.persistence.satquery.parser.jsql.visitor.SatExpressionVisitor;
import br.gov.to.sefaz.persistence.satquery.parser.jsql.visitor.SatSelectVisitor;
import br.gov.to.sefaz.util.application.ApplicationUtil;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.Parenthesis;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.select.FromItem;
import net.sf.jsqlparser.statement.select.Join;
import net.sf.jsqlparser.statement.select.LateralSubSelect;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SubSelect;
import net.sf.jsqlparser.statement.update.Update;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe utilitaria para manipulação de consultas interceptadas do hibernate.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 19/07/2016 09:36:00
 */
public class SatSqlInterceptorHandler {

    private static final String REGISTRO_EXCLUIDO = "REGISTRO_EXCLUIDO";
    private static final String DATA_EXCLUSAO = "DATA_EXCLUSAO";
    private static final String USUARIO_EXCLUSAO = "USUARIO_EXCLUSAO";
    private static final String ORA_ISO_DATE_TIME_PATTERN = "yyyy-mm-dd\"T\"hh24:mi:ss.ff3\"Z\"";
    public static final String ORA_TO_TIMESTAMP_FUNCTION = "TO_TIMESTAMP";

    private final SatSelectVisitor selectVisitor;
    private final SatExpressionVisitor expressionVisitor;

    public SatSqlInterceptorHandler() {
        selectVisitor = new SatSelectVisitor(this);
        expressionVisitor = new SatExpressionVisitor(this);
    }

    /**
     * <p>Aplica as regras de negócio do sistema ao sql e o retorna atualizado.</p>
     *
     * @see #handlePlainSelect(PlainSelect)
     * @param select select original
     * @return select alterado com as regras do sistema
     */
    public Statement handleSelect(Select select) {
        select.getSelectBody().accept(selectVisitor);

        return select;
    }

    /**
     * <p>Aplica as regras de negócio do sistema ao sql.</p>
     *
     * @see #handlePlainSelect(PlainSelect)
     * @param subSelect subselect original
     */
    public void handleSubSelect(SubSelect subSelect) {
        subSelect.getSelectBody().accept(selectVisitor);
    }

    /**
     * <p>Aplica as regras de negócio do sistema ao sql.</p>
     * <p> - Aplica a todos os "where" e "on" (se tiver join) do select (incluindo sub-consultas) as regras
     * de exclusão logica, fazendo com que a consulta retorne apenas linhas cujo
     * a coluna {@value REGISTRO_EXCLUIDO} seja igual a 'N'</p>
     *
     * @param plainSelect estrutura original do select
     */
    public void handlePlainSelect(PlainSelect plainSelect) {
        FromItem fromItem = plainSelect.getFromItem();

        handleJoins(plainSelect.getJoins());

        if (fromItem instanceof Table) {
            Expression newWhere = changeWhere((Table) fromItem, plainSelect.getWhere());

            plainSelect.setWhere(newWhere);
        } else {
            handleSubSelects(fromItem);
        }
    }

    /**
     * <p>Aplica as regras de negócio do sistema ao sql e o retorna atualizado.</p>
     * <p> - Aplica a todos os "where" do update (incluindo sub-consultas) as regras de exclusão logica, fazendo
     *  com que a consulta altere apenas linhas cujo a coluna {@value REGISTRO_EXCLUIDO} seja igual a 'N'</p>
     *
     * @param update select original
     * @return update alterado com as regras do sistema
     */
    public Statement handleUpdate(Update update) {
        for (Table table : update.getTables()) {
            Expression newWhere = changeWhere(table, update.getWhere());
            update.setWhere(newWhere);
        }

        return update;
    }

    /**
     * <p>Aplica as regras de negócio do sistema ao sql e o retorna atualizado.</p>
     * <p> - Se o delete for realizado em uma tabela do sistema, transforma em um apdate setando as colunas:
     * {@value #REGISTRO_EXCLUIDO}='S', {@value #DATA_EXCLUSAO}='horario atual' e
     * {@value #USUARIO_EXCLUSAO}='usuario autenticado na sessão'.</p>
     * <p> - Se não for uma tabela do sistema aplica a todos os "where" de sub-consultas as regras
     * de exclusão logica, fazendo com que a consulta delete apenas linhas cujo a coluna {@value REGISTRO_EXCLUIDO}
     * seja igual a 'N'</p>
     *
     * @param delete select original
     * @return delete alterado com as regras do sistema
     */
    public Statement handleDelete(Delete delete) {
        if (AuditableTablesIdentifier.isAuditable(delete.getTable().getFullyQualifiedName())) {
            return createUpdateFromDelete(delete);
        }

        Expression newWhere = changeWhere(delete.getTable(), delete.getWhere());
        delete.setWhere(newWhere);

        return delete;
    }

    private void handleSubSelects(FromItem fromItem) {
        if (fromItem instanceof SubSelect) {
            SubSelect fromSubSelect = (SubSelect) fromItem;

            fromSubSelect.getSelectBody().accept(selectVisitor);
        } else if (fromItem instanceof LateralSubSelect) {
            LateralSubSelect fromSubSelect = (LateralSubSelect) fromItem;

            fromSubSelect.getSubSelect().getSelectBody().accept(selectVisitor);
        }
    }

    private void handleJoins(List<Join> joins) {
        if (Objects.nonNull(joins)) {
            for (Join join : joins) {
                Table fromTable = (Table) join.getRightItem();
                Expression newOn = changeWhere(fromTable, join.getOnExpression());

                join.setOnExpression(newOn);
            }
        }
    }

    private Expression changeWhere(Table fromTable, Expression oldOn) {
        if (AuditableTablesIdentifier.isAuditable(fromTable.getFullyQualifiedName())) {
            Expression newOn = logicalExclusionExpression(fromTable);

            if (Objects.nonNull(oldOn)) {
                oldOn.accept(expressionVisitor);
                newOn = new AndExpression(newOn, new Parenthesis(oldOn));
            }
            return newOn;
        }

        return oldOn;
    }

    private EqualsTo logicalExclusionExpression(Table table) {
        Column column = new Column(table, REGISTRO_EXCLUIDO);

        EqualsTo equalsTo = new EqualsTo();
        equalsTo.setLeftExpression(column);
        equalsTo.setRightExpression(new StringValue("'N'"));

        return equalsTo;
    }

    private Update createUpdateFromDelete(Delete delete) {
        Table table = delete.getTable();

        Update update = new Update();
        ArrayList<Table> updateTable = new ArrayList<>();

        updateTable.add(table);
        update.setTables(updateTable);

        List<Column> columns = new ArrayList<>();
        List<Expression> values = new ArrayList<>();

        String user = ApplicationUtil.getSafeNameAuthenticatedUser();
        LocalDateTime dateTime = LocalDateTime.now();

        columns.add(new Column(REGISTRO_EXCLUIDO));
        values.add(new StringValue("'S'"));

        columns.add(new Column(DATA_EXCLUSAO));
        values.add(createToTimestampFunction(dateTime));

        columns.add(new Column(USUARIO_EXCLUSAO));
        values.add(new StringValue("'" + user + "'"));

        update.setColumns(columns);
        update.setExpressions(values);

        Expression newWhere = changeWhere(table, delete.getWhere());
        update.setWhere(newWhere);

        return update;
    }

    private Function createToTimestampFunction(LocalDateTime dateTime) {
        Function toTimestampFunction = new Function();

        String dateTimeNow = DateTimeFormatter.ISO_DATE_TIME.format(dateTime);
        toTimestampFunction.setName(ORA_TO_TIMESTAMP_FUNCTION);
        ArrayList<Expression> parameters = new ArrayList<>();
        parameters.add(new StringValue("'" + dateTimeNow + "'"));
        parameters.add(new StringValue("'" + ORA_ISO_DATE_TIME_PATTERN + "'"));

        ExpressionList list = new ExpressionList(parameters);
        toTimestampFunction.setParameters(list);

        return toTimestampFunction;
    }
}
