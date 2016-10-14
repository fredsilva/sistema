package br.gov.to.sefaz.persistence.configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsavel por identificar se uma tabela é auditavel ou não.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 30/08/2016 11:08:00
 */
public class AuditableTablesIdentifier {

    public static final String VIEW_NAME_PATTERN = "(?i).+?\\.VW_.+";
    private static NonAuditableTablesHolder nonAuditableTablesHolder = new NonAuditableTablesHolder();

    /**
     * Identifica se um tabela é auditavel ou não.
     *
     * @param tableOrEntityName nome e esquema da tabela ou nome da entidade, se for via hql
     * @return true se a tabela for auditavel
     */
    public static boolean isAuditable(String tableOrEntityName) {
        return !isView(tableOrEntityName) && nonAuditableTablesHolder.getNames().stream()
                .noneMatch(ignorable -> ignorable.equalsIgnoreCase(tableOrEntityName.trim()));
    }

    private static boolean isView(String tableOrEntityName) {
        return tableOrEntityName.matches(VIEW_NAME_PATTERN);
    }

    /**
     * Classe que contém o nome e schema de todas as tabelas e views não auditáveis do sistema.
     */
    private static class NonAuditableTablesHolder {

        private final List<String> names;

        public NonAuditableTablesHolder() {
            names = new ArrayList<>();

            // tabelas/views que não possuem colunas de auditoria
            names.add("DUAL");
            names.add("SEFAZ_PAR.TA_LOGRADOURO");
        }

        public List<String> getNames() {
            return names;
        }
    }
}
