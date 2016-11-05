package br.gov.to.sefaz.persistence.query.structure.where;

/**
 * Classe de Enum que contêm um lista de operações SQL.
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 29/06/2016 14:28:00
 */
public enum ComparisonOperator {
    EXISTS,
    IN,
    BETWEEN,
    IS_NULL,
    LIKE,
    LESS,
    LESS_EQUAL,
    GREATER,
    GREATER_EQUAL,
    DIFFERENT,
    EQUAL;
}
