package br.gov.to.sefaz.seg.business.authentication.handler;

import br.gov.to.sefaz.util.properties.AppProperties;
import org.apache.commons.lang3.StringUtils;

import java.util.Random;

/**
 * Componente para manipulação e tratamento de lógicas de acesso ao AD (Active Directory) utilizando protocolo LDAP.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 19/05/2016 14:02:49
 */
public class LdapHandler {

    private static final Random RANDOM = new Random();

    /**
     * Consulta as propriedades configuráveis da conexão com o AD do arquivo ldap-parameters.properties.
     *
     * @return propriedades configuráveis da conexão com o AD
     */
    public static LdapProperties getLdapProperties() {
        // Cria LdapProperties com os respectivos valores do ldap-parameters.properties
        LdapProperties ldapProperties = new LdapProperties();
        ldapProperties.setUrl(AppProperties.getAppProperty("ldap.provider.url").orElse(StringUtils.EMPTY));
        ldapProperties.setSecurityUrl(AppProperties.getAppProperty("ldap.provider.url.ssl").orElse(StringUtils.EMPTY));
        ldapProperties.setDomain(AppProperties.getAppProperty("ldap.provider.domain").orElse(StringUtils.EMPTY));
        ldapProperties.setBaseDomain(AppProperties.getAppProperty("ldap.provider.baseDomain")
                .orElse(StringUtils.EMPTY));
        ldapProperties.setBaseFilter(AppProperties.getAppProperty("ldap.provider.baseFilter")
                .orElse(StringUtils.EMPTY));
        ldapProperties.setTimeout(AppProperties.getAppProperty("ldap.connect.timeout").orElse(StringUtils.EMPTY));
        ldapProperties.setUserManager(AppProperties.getAppProperty("ldap.provider.userManager")
                .orElse(StringUtils.EMPTY));
        ldapProperties.setPasswdManager(AppProperties.getAppProperty("ldap.provider.passwdManager")
                .orElse(StringUtils.EMPTY));
        return ldapProperties;
    }

    /**
     * Gera uma senha randômico com uma determinada quantidade de caracteres.
     *
     * @param length quantidade de caracteres da senha
     * @return senha gerada randomicamente
     */
    public static String getRandomPassword(final int length) {
        final char[] result = new char[length];
        for (int i = 0; i < length; i++) {
            result[i] = getRandomCharactere();
        }
        return new String(result);
    }

    private static char getRandomCharactere() {
        char charactere = (char) RANDOM.nextInt(122);
        if (Character.isLetterOrDigit(charactere)) {
            return charactere;
        } else {
            return getRandomCharactere();
        }
    }

    /**
     * Classe que representa as propriedades configuráveis da conexão com o AD (Active Directory).
     *
     * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
     * @since 19/05/2016 14:11:59
     */
    public static class LdapProperties {

        private String url;

        private String securityUrl;

        private String domain;

        private String baseDomain;

        private String baseFilter;

        private String userManager;

        private String passwdManager;

        private String timeout;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getSecurityUrl() {
            return securityUrl;
        }

        public void setSecurityUrl(String securityUrl) {
            this.securityUrl = securityUrl;
        }

        public String getDomain() {
            return domain;
        }

        public void setDomain(String domain) {
            this.domain = domain;
        }

        public String getBaseDomain() {
            return baseDomain;
        }

        public void setBaseDomain(String baseDomain) {
            this.baseDomain = baseDomain;
        }

        public String getBaseFilter() {
            return baseFilter;
        }

        public void setBaseFilter(String baseFilter) {
            this.baseFilter = baseFilter;
        }

        public String getUserManager() {
            return userManager;
        }

        public void setUserManager(String userManager) {
            this.userManager = userManager;
        }

        public String getPasswdManager() {
            return passwdManager;
        }

        public void setPasswdManager(String passwdManager) {
            this.passwdManager = passwdManager;
        }

        public String getTimeout() {
            return timeout;
        }

        public void setTimeout(String timeout) {
            this.timeout = timeout;
        }

    }

}
