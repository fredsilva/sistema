package br.gov.to.sefaz.presentation.managedbean.composites.domain;

/**
 * Representa o tipo de exibição de um campo de uma tabela de dados.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 12/05/2016 19:46:00
 */
public enum DataTableFieldPrint {
    NONE, STRING, NUMBER, DATE, DATE_TIME, DATE_TIME_SECS, CPF, CNPJ, CNPJ_RAIZ, BOOLEAN, CPF_CNPJ;
}
