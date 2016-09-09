package br.gov.to.sefaz.persistence.configuration;

import br.gov.to.sefaz.persistence.query.parser.domain.QueryLanguages;
import br.gov.to.sefaz.persistence.satquery.parser.handler.RegistroExcluidoHandler;
import org.hibernate.dialect.Oracle10gDialect;
import org.hibernate.sql.ANSIJoinFragment;
import org.hibernate.sql.JoinFragment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Customização do dialeto {@link Oracle10gDialect} para suportar colunas de auditoria.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 03/08/2016 11:08:00
 */
public class SatParseDialect extends Oracle10gDialect {

    public static final String JOIN_PATTERN =
            "(\\s*)(inner|left|right|cross)(\\s+outer)?(\\s+join\\s+)([.\\w]+\\s+)([.\\w]+\\s+)?(on\\s+)";

    @Override
    public JoinFragment createOuterJoinFragment() {
        return new ANSIJoinFragment() {

            @Override
            public String toFromFragmentString() {
                String originalJoin = super.toFromFragmentString();
                StringBuilder newJoin = new StringBuilder();

                String[] pieces = originalJoin.trim().split(JOIN_PATTERN);
                Matcher matcher = Pattern.compile(JOIN_PATTERN).matcher(originalJoin);

                for (int i = 1; i < pieces.length; i++) {
                    if (matcher.find()) {
                        if (AuditableTablesIdentifier.isAuditable(matcher.group(5))) {
                            String registroExcluidoColumn = RegistroExcluidoHandler
                                    .getRegistroExcluidoColumn(QueryLanguages.SQL);
                            String condition = " on "
                                    + (matcher.group(6) == null ? matcher.group(5).trim() : matcher.group(6).trim())
                                    + "." + registroExcluidoColumn + " = 'N' and ";
                            newJoin.append(matcher.group().replace(" on ", condition));
                        } else {
                            newJoin.append(matcher.group());
                        }
                    }

                    newJoin.append(pieces[i]);
                }

                return newJoin.toString();
            }
        };
    }
}
