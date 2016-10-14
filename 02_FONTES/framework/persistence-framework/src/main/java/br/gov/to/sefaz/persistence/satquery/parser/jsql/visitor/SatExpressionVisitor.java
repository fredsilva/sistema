package br.gov.to.sefaz.persistence.satquery.parser.jsql.visitor;

import br.gov.to.sefaz.persistence.satquery.handler.SatSqlInterceptorHandler;
import net.sf.jsqlparser.expression.AllComparisonExpression;
import net.sf.jsqlparser.expression.AnalyticExpression;
import net.sf.jsqlparser.expression.AnyComparisonExpression;
import net.sf.jsqlparser.expression.CaseExpression;
import net.sf.jsqlparser.expression.CastExpression;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import net.sf.jsqlparser.expression.DateValue;
import net.sf.jsqlparser.expression.DoubleValue;
import net.sf.jsqlparser.expression.ExpressionVisitor;
import net.sf.jsqlparser.expression.ExtractExpression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.HexValue;
import net.sf.jsqlparser.expression.IntervalExpression;
import net.sf.jsqlparser.expression.JdbcNamedParameter;
import net.sf.jsqlparser.expression.JdbcParameter;
import net.sf.jsqlparser.expression.JsonExpression;
import net.sf.jsqlparser.expression.KeepExpression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.MySQLGroupConcat;
import net.sf.jsqlparser.expression.NullValue;
import net.sf.jsqlparser.expression.NumericBind;
import net.sf.jsqlparser.expression.OracleHierarchicalExpression;
import net.sf.jsqlparser.expression.OracleHint;
import net.sf.jsqlparser.expression.Parenthesis;
import net.sf.jsqlparser.expression.RowConstructor;
import net.sf.jsqlparser.expression.SignedExpression;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.TimeKeyExpression;
import net.sf.jsqlparser.expression.TimeValue;
import net.sf.jsqlparser.expression.TimestampValue;
import net.sf.jsqlparser.expression.UserVariable;
import net.sf.jsqlparser.expression.WhenClause;
import net.sf.jsqlparser.expression.WithinGroupExpression;
import net.sf.jsqlparser.expression.operators.arithmetic.Addition;
import net.sf.jsqlparser.expression.operators.arithmetic.BitwiseAnd;
import net.sf.jsqlparser.expression.operators.arithmetic.BitwiseOr;
import net.sf.jsqlparser.expression.operators.arithmetic.BitwiseXor;
import net.sf.jsqlparser.expression.operators.arithmetic.Concat;
import net.sf.jsqlparser.expression.operators.arithmetic.Division;
import net.sf.jsqlparser.expression.operators.arithmetic.Modulo;
import net.sf.jsqlparser.expression.operators.arithmetic.Multiplication;
import net.sf.jsqlparser.expression.operators.arithmetic.Subtraction;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.Between;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.ExistsExpression;
import net.sf.jsqlparser.expression.operators.relational.GreaterThan;
import net.sf.jsqlparser.expression.operators.relational.GreaterThanEquals;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.expression.operators.relational.IsNullExpression;
import net.sf.jsqlparser.expression.operators.relational.LikeExpression;
import net.sf.jsqlparser.expression.operators.relational.Matches;
import net.sf.jsqlparser.expression.operators.relational.MinorThan;
import net.sf.jsqlparser.expression.operators.relational.MinorThanEquals;
import net.sf.jsqlparser.expression.operators.relational.NotEqualsTo;
import net.sf.jsqlparser.expression.operators.relational.RegExpMatchOperator;
import net.sf.jsqlparser.expression.operators.relational.RegExpMySQLOperator;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.select.SubSelect;

/**
 * Implementação de um {@link ExpressionVisitor} para atender as regras de negocio do projeto.
 * Este componente serve para navegar em uma expressão do sql e procurar e manipular algum comando expecifico.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 05/10/2016 18:01:00
 */
@SuppressWarnings({"PMD.ExcessivePublicCount", "PMD.TooManyMethods"})
public class SatExpressionVisitor implements ExpressionVisitor {

    private final SatSqlInterceptorHandler interceptorHandler;

    public SatExpressionVisitor(SatSqlInterceptorHandler interceptorHandler) {
        this.interceptorHandler = interceptorHandler;
    }

    /**
     * {@inheritDoc}
     * Se o FROM for em uma tabela, altera o where para colocar a condição de exclusão logica.
     * Se o FROM for um subselect, aplica este visitor de maneira recursiva.
     *
     * @param subSelect estrutura do subSelect
     */
    @Override
    public void visit(SubSelect subSelect) {
        interceptorHandler.handleSubSelect(subSelect);
    }

    @Override
    public void visit(NullValue nullValue) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(Function function) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(SignedExpression signedExpression) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(JdbcParameter jdbcParameter) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(JdbcNamedParameter jdbcNamedParameter) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(DoubleValue doubleValue) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(LongValue longValue) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(HexValue hexValue) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(DateValue dateValue) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(TimeValue timeValue) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(TimestampValue timestampValue) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(Parenthesis parenthesis) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(StringValue stringValue) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(Addition addition) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(Division division) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(Multiplication multiplication) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(Subtraction subtraction) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(AndExpression andExpression) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(OrExpression orExpression) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(Between between) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(EqualsTo equalsTo) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(GreaterThan greaterThan) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(GreaterThanEquals greaterThanEquals) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(InExpression expression) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(IsNullExpression isNullExpression) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(LikeExpression likeExpression) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(MinorThan minorThan) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(MinorThanEquals minorThanEquals) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(NotEqualsTo notEqualsTo) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(Column tableColumn) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(CaseExpression caseExpression) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(WhenClause whenClause) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(ExistsExpression existsExpression) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(AllComparisonExpression allComparisonExpression) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(AnyComparisonExpression anyComparisonExpression) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(Concat concat) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(Matches matches) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(BitwiseAnd bitwiseAnd) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(BitwiseOr bitwiseOr) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(BitwiseXor bitwiseXor) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(CastExpression cast) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(Modulo modulo) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(AnalyticExpression aexpr) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(WithinGroupExpression wgexpr) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(ExtractExpression eexpr) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(IntervalExpression iexpr) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(OracleHierarchicalExpression oexpr) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(RegExpMatchOperator rexpr) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(JsonExpression jsonExpr) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(RegExpMySQLOperator operator) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(UserVariable var) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(NumericBind bind) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(KeepExpression aexpr) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(MySQLGroupConcat groupConcat) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(RowConstructor rowConstructor) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(OracleHint hint) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(TimeKeyExpression timeKeyExpression) {
        // Implementado por contrato com interface
    }

    @Override
    public void visit(DateTimeLiteralExpression literal) {
        // Implementado por contrato com interface
    }
}
