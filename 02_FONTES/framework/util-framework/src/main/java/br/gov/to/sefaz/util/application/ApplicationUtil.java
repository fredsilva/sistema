package br.gov.to.sefaz.util.application;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Locale;
import java.util.Objects;

/**
 * Atributos que são padrão para toda a aplicação.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 15/06/2016 17:32:00
 */
public class ApplicationUtil {

    public static final Locale LOCALE = new Locale("pt", "BR");

    /**
     * Retorna um usuário autenticado, ou na ausencia deste retorna 00000000000.
     * Um usuário restrito ao sistema.
     */
    public static String getSafeNameAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!Objects.isNull(authentication) && StringUtils.isNotEmpty(authentication.getName())) {
            return authentication.getName();
        }

        return "00000000000";
    }

}
