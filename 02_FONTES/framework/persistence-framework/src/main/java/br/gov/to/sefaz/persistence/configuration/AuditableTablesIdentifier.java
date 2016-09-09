package br.gov.to.sefaz.persistence.configuration;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * Classe responsavel por identificar se uma tabela é auditavel ou não.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 30/08/2016 11:08:00
 */
public class AuditableTablesIdentifier {

    public static final String ENTITY_NAME_PATTERN = "(^.*?[.]\\w+?_)|(_)";
    private static NonAuditableTablesHolder nonAuditableTablesHolder = new NonAuditableTablesHolder();

    /**
     * Identifica se um tabela é auditavel ou não.
     *
     * @param tableOrEntityName nome e esquema da tabela ou nome da entidade, se for via hql
     * @return true se a tabela for auditavel
     */
    public static boolean isAuditable(String tableOrEntityName) {
        return nonAuditableTablesHolder.getNames().stream()
                .noneMatch(ignorable -> ignorable.equalsIgnoreCase(tableOrEntityName.trim())
                        || ignorable.replaceAll(ENTITY_NAME_PATTERN, EMPTY).equalsIgnoreCase(tableOrEntityName.trim()));
    }


    /**
     * Classe que contem o nome e schema de todas as tabelas e views não auditaveis do sistema.
     */
    private static class NonAuditableTablesHolder {

        private final List<String> names;

        public NonAuditableTablesHolder() {
            names = new ArrayList<>();

            // tabelas que não possuem colunas de auditoria
            names.add("SEFAZ_PAR.TA_LOGRADOURO");
            names.add("SEFAZ_SEG.VW_COMUNICACAO_CONTRIBUINTE");
            names.add("SEFAZ_SEG.VW_LISTAGEM_CPF_PROCURACAO");
        }

        public List<String> getNames() {
            return names;
        }
    }
}
