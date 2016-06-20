package br.gov.to.sefaz.seg.business.authentication.provider;

import br.gov.to.sefaz.seg.business.authentication.handler.LdapHandler;
import br.gov.to.sefaz.seg.business.authentication.handler.LdapHandler.LdapProperties;
import br.gov.to.sefaz.seg.exception.SecurityException;

import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.Objects;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

/**
 * Classe que provê acesso às funções ao AD (Active Directory) via protocolo LDAP.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 15/06/2016 19:55:35
 */
@Component
public class LdapProvider {

    LdapProperties ldapProperties;

    public LdapProvider() {
        ldapProperties = LdapHandler.getLdapProperties();
    }

    /**
     * Realiza a autenticação do usuário e senha no AD (Active Directory).
     *
     * @param username login de acesso no AD
     * @param passwdord senha do usuário no AD
     * @throws SecurityException exceção de segurança no caso de login inválido.
     */
    public void authenticate(String username, String passwdord) throws SecurityException {
        LdapContext ctx = openConectionAD(username, passwdord, false);
        closeConectionAD(ctx);
    }

    /**
     * Realiza a autenticação do usuário e senha no AD (Active Directory).
     *
     * @param username login de acesso no AD
     * @param newPassword senha do usuário no AD
     * @throws SecurityException exceção de segurança no caso de erro ao resetar a senha.
     */
    public void resetPassword(String username, String newPassword) throws SecurityException {
        String newQuotedPassword = "\"" + newPassword + "\"";
        byte[] newUnicodePassword;
        try {
            newUnicodePassword = newQuotedPassword.getBytes("UTF-16LE");
        } catch (UnsupportedEncodingException e) {
            throw new SecurityException("Não foi possível alterar a senha no AD.", e);
        }
        ModificationItem itemAdd = new ModificationItem(LdapContext.REPLACE_ATTRIBUTE,
                new BasicAttribute("unicodePwd", newUnicodePassword));

        setAttribute(username, itemAdd);
    }

    /**
     * Escreve um valor em um atributo no AD (Active Directory).
     *
     * @param username login de acesso no AD
     * @param itens Itens a serem alterados no AD
     * @throws SecurityException exceção de segurança no caso de erro ao setar a senha.
     */
    public void setAttribute(String username, ModificationItem... itens) throws SecurityException {
        LdapContext ctx = openConectionAD(ldapProperties.getUserManager(), ldapProperties.getPasswdManager(), true);

        String name = getNameInNamespace(ctx, username);

        ModificationItem[] mods = new ModificationItem[itens.length];
        int j = 0;
        for (ModificationItem item : itens) {
            mods[j++] = item;
        }

        try {
            ctx.modifyAttributes(name, mods);
        } catch (NamingException e) {
            throw new SecurityException("Não foi possível excrever o atributo no AD.", e);
        } finally {
            closeConectionAD(ctx);
        }
    }

    /**
     * Consulta o valor de um atributo no AD (Active Directory).
     *
     * @param username login de acesso no AD
     * @param att atributo a ser consultado
     * @return valor do atributo consultado
     * @throws SecurityException exceção de segurança no caso de erro ao consultar atributo.
     */
    public Object getAttribute(String username, String att) throws SecurityException {
        Object obj = null;

        LdapContext ctx = openConectionAD(ldapProperties.getUserManager(), ldapProperties.getPasswdManager(), true);
        NamingEnumeration<SearchResult> results = getResults(ctx, username);

        if (Objects.isNull(results)) {
            return obj;
        }

        try {
            while (results.hasMore()) {
                SearchResult searchResult = results.next();
                Attribute attribute = searchResult.getAttributes().get(att);
                if (!Objects.isNull(attribute)) {
                    obj = attribute.get();
                }
            }
        } catch (NamingException e) {
            throw new SecurityException("Não foi possível consultar o atributo no AD.", e);
        } finally {
            closeResults(results);
            closeConectionAD(ctx);
        }

        return obj;
    }

    private String getNameInNamespace(LdapContext ctx, String username) throws SecurityException {
        String nameInNamespace = null;

        NamingEnumeration<SearchResult> results = getResults(ctx, username);
        try {
            if (results != null) {
                while (results.hasMore()) {
                    SearchResult searchResult = results.next();
                    nameInNamespace = searchResult.getNameInNamespace();
                }
            }
        } catch (NamingException e) {
            throw new SecurityException("Não foi possível consultar o nameInNamespace do usuário no AD.", e);
        } finally {
            closeResults(results);
        }

        return nameInNamespace;
    }

    private NamingEnumeration<SearchResult> getResults(LdapContext ctx, String username) {
        SearchControls controls = new SearchControls();
        controls.setSearchScope(SearchControls.SUBTREE_SCOPE);

        NamingEnumeration<SearchResult> results = null;
        try {
            results = ctx.search(ldapProperties.getBaseDomain(),
                    "(&(objectClass=User)(objectCategory=Person)(samaccountname=" + username + "))",
                    controls);
        } catch (NamingException e) {
            throw new SecurityException("Não foi possível consultar o usuário no AD.", e);
        }

        return results;
    }

    private void closeResults(NamingEnumeration<SearchResult> results) throws SecurityException {
        if (results != null) {
            try {
                results.close();
            } catch (NamingException e) {
                throw new SecurityException("Não foi possível fechar a consulta do usuário no AD.", e);
            }
        }
    }

    /**
     * Fecha a conexão com o AD (Active Directory).
     *
     * @param ctx {@link LdapContext} contexto de conexão do AD (Active Directory)
     * @throws SecurityException exceção de segurança no caso de login inválido.
     */
    private LdapContext openConectionAD(String username, String passwdord, boolean ssl) throws SecurityException {
        Hashtable<String, String> env = new Hashtable<String, String>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        if (ssl) {
            env.put(Context.PROVIDER_URL, ldapProperties.getSecurityUrl());
            env.put(Context.SECURITY_PROTOCOL, "ssl");
        } else {
            env.put(Context.PROVIDER_URL, ldapProperties.getUrl());
        }
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.REFERRAL, "follow");
        env.put(Context.SECURITY_PRINCIPAL, ldapProperties.getDomain() + "\\" + username);
        env.put(Context.SECURITY_CREDENTIALS, passwdord);
        env.put("java.naming.ldap.attributes.binary", "objectSID");
        env.put("com.sun.jndi.ldap.connect.timeout", ldapProperties.getTimeout());

        LdapContext ctx = null;
        try {
            ctx = new InitialLdapContext(env, null);
        } catch (NamingException e) {
            throw new SecurityException("Não foi possível abrir uma conexão com AD.", e);
        }

        return ctx;
    }

    /**
     * Fecha a conexão com o AD (Active Directory).
     *
     * @param ctx {@link LdapContext} contexto de conexão do AD (Active Directory)
     * @throws SecurityException exceção de segurança no caso de login inválido.
     */
    private void closeConectionAD(LdapContext ctx) throws SecurityException {
        if (ctx != null) {
            try {
                ctx.close();
            } catch (NamingException e) {
                throw new SecurityException("Não foi possível fechar a conexão com o AD.", e);
            }
        }
    }

}
