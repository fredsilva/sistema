package br.gov.to.sefaz.seg.business.authentication.provider;

import br.gov.to.sefaz.seg.business.authentication.domain.SecurityErrorCodeType;
import br.gov.to.sefaz.seg.business.authentication.handler.LdapHandler;
import br.gov.to.sefaz.seg.business.authentication.handler.LdapHandler.LdapProperties;
import br.gov.to.sefaz.seg.business.authentication.service.SecurityException;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.Hashtable;
import java.util.Objects;
import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import static javax.naming.Context.SECURITY_PRINCIPAL;

/**
 * Classe que provê acesso às funções ao AD (Active Directory) via protocolo LDAP.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 15/06/2016 19:55:35
 */
@Component
public class LdapProvider {

    private static final int UF_ACCOUNTDISABLE = 0x0002;

    private static final int UF_NORMAL_ACCOUNT = 0x0200;

    private final LdapProperties ldapProperties;

    public LdapProvider() {
        ldapProperties = LdapHandler.getLdapProperties();
    }

    /**
     * Realiza a autenticação do usuário e senha no AD (Active Directory).
     *
     * @param userName login de acesso no AD
     * @param passwdord senha do usuário no AD
     * @throws SecurityException exceção de segurança no caso de login inválido.
     */
    public void authenticate(String userName, String passwdord) throws SecurityException {
        LdapContext ctx = openConectionAD(userName, passwdord, false);
        closeConectionAD(ctx);
    }

    /**
     * Cria um usuário no AD (Active Directory).
     *
     * @param userName login de acesso no AD
     * @param password senha do usuário no AD
     * @throws SecurityException exceção de segurança no caso de erro ao criar usuário.
     */
    public void createUser(String userName, String password) throws SecurityException {
        Attributes attrs = new BasicAttributes(true);
        // Atributo objectClass
        Attribute oc = new BasicAttribute("objectClass");
        oc.add("top");
        oc.add("person");
        oc.add("organizationalPerson");
        oc.add("user");
        attrs.put(oc);
        // Atributos commonName e AccountName (Conta do usuário) do AD
        attrs.put("cn", userName);
        attrs.put("samaccountname", userName);
        // Atributo de controle da conta de usuário - Conta normal, desabilitada, não expira a senha
        attrs.put("userAccountControl",Integer.toString(UF_NORMAL_ACCOUNT + UF_ACCOUNTDISABLE));

        // Abre uma conexão segura com o AD
        LdapContext ctx = openConectionAD(ldapProperties.getUserManager(), ldapProperties.getPasswdManager(), true);
        try {
            // Cria o usuário no AD
            Context result = ctx.createSubcontext(MessageFormat.format(ldapProperties.getBaseDomain(),
                    userName), attrs);
            result.getNameInNamespace();
        } catch (NamingException e) {
            throw new SecurityException("Não foi possível criar o usuário " + userName + " no AD.", e);
        } finally {
            closeConectionAD(ctx);
        }

        this.resetPassword(userName, password);
    }

    /**
     * Habilitao usuário no AD (Active Directory).
     *
     * @param userName login de acesso no AD
     * @throws Exception exceção de segurança no caso de erro ao habilitar usuário.
     */
    public void enableUser(String userName) throws SecurityException {
        BasicAttribute att = new BasicAttribute("userAccountControl",
                Integer.toString(UF_NORMAL_ACCOUNT));
        ModificationItem itemAdd = new ModificationItem(LdapContext.REPLACE_ATTRIBUTE, att);
        setAttribute(userName, itemAdd);
    }

    /**
     * Desabilita o usuário no AD (Active Directory).
     *
     * @param userName login de acesso no AD
     * @throws Exception exceção de segurança no caso de erro ao deseablitar usuário.
     */
    public void disableUser(String userName) throws SecurityException {
        BasicAttribute att = new BasicAttribute("userAccountControl",
                Integer.toString(UF_NORMAL_ACCOUNT + UF_ACCOUNTDISABLE));
        ModificationItem itemAdd = new ModificationItem(LdapContext.REPLACE_ATTRIBUTE, att);
        setAttribute(userName, itemAdd);
    }

    /**
     * Realiza a autenticação do usuário e senha no AD (Active Directory).
     *
     * @param userName login de acesso no AD
     * @param newPassword senha do usuário no AD
     * @throws SecurityException exceção de segurança no caso de erro ao resetar a senha.
     */
    public void resetPassword(String userName, String newPassword) throws SecurityException {
        // Atributo nova senha
        byte[] newUnicodePassword = getUnicodeFromString(newPassword);
        ModificationItem newPasswd = new ModificationItem(LdapContext.REPLACE_ATTRIBUTE,
                new BasicAttribute("unicodePwd", newUnicodePassword));
        // Modificar atributos AD
        setAttribute(userName, newPasswd);
    }

    /**
     * Escreve um valor em um atributo no AD (Active Directory).
     *
     * @param userName login de acesso no AD
     * @param itens Itens a serem alterados no AD
     * @throws SecurityException exceção de segurança no caso de erro ao setar a senha.
     */
    public void setAttribute(String userName, ModificationItem... itens) throws SecurityException {
        // Abre uma conexão segura com o AD
        LdapContext ctx = openConectionAD(ldapProperties.getUserManager(), ldapProperties.getPasswdManager(), true);

        try {
            // Altera os atributos do usuário no AD
            ctx.modifyAttributes(MessageFormat.format(ldapProperties.getBaseDomain(), userName), itens);
        } catch (NamingException e) {
            throw new SecurityException("Não foi possível excrever o(s) atributo(s) para o usuário " + userName + " no"
                    + " AD.", e);
        } finally {
            closeConectionAD(ctx);
        }
    }

    /**
     * Consulta o valor de um atributo no AD (Active Directory).
     *
     * @param userName login de acesso no AD
     * @param att atributo a ser consultado
     * @return valor do atributo consultado
     * @throws SecurityException exceção de segurança no caso de erro ao consultar atributo.
     */
    public Object getAttribute(String userName, String att) throws SecurityException {
        Object obj = null;
        // Abre uma conexão segura com o AD
        LdapContext ctx = openConectionAD(ldapProperties.getUserManager(), ldapProperties.getPasswdManager(), true);
        // Consulta as propriedades do usuário no AD
        NamingEnumeration<SearchResult> results = getResults(ctx, userName);
        // Caso resultado da consulta seja null retorna
        if (Objects.isNull(results)) {
            return obj;
        }
        try {
            // Itera o resultado, aqui se espera apenas um resultado, pois a consulta no AD é pelo samaccountname
            // (conata do usuário)
            while (results.hasMore()) {
                SearchResult searchResult = results.next();
                // Lê o atributo desejado da consulta realizada
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

    /*
    private String getNameInNamespace(LdapContext ctx, String userName) throws SecurityException {
        String nameInNamespace = null;

        NamingEnumeration<SearchResult> results = getResults(ctx, userName);
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
    */

    private NamingEnumeration<SearchResult> getResults(LdapContext ctx, String userName) {
        SearchControls controls = new SearchControls();
        controls.setSearchScope(SearchControls.SUBTREE_SCOPE);

        NamingEnumeration<SearchResult> results = null;
        try {
            results = ctx.search(MessageFormat.format(ldapProperties.getBaseDomain(), userName),
                    MessageFormat.format(ldapProperties.getBaseFilter(), userName), controls);
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
     * Abre uma conexão com o AD (Active Directory) com um determinado usuário e senha. Se a flag ssl passada por
     * parâmetro for a conexão com AD será SSL/LDAPS.
     *
     * @param userName usuário no AD
     * @param password senha do usuário no AD
     * @param ssl flag que indica se a conexão com AD será SSL
     * @return ctx contexto de conexão do AD
     * @throws SecurityException exceção de segurança no caso de login inválido.
     */
    private LdapContext openConectionAD(String userName, String password, boolean ssl) throws SecurityException {
        Hashtable<String, String> env = new Hashtable<String, String>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        if (ssl) {
            env.put(Context.PROVIDER_URL, ldapProperties.getSecurityUrl() + "/" + ldapProperties.getDomain());
            env.put(Context.SECURITY_PROTOCOL, "ssl");
        } else {
            env.put(Context.PROVIDER_URL, ldapProperties.getUrl());
        }
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.REFERRAL, "follow");
        env.put(SECURITY_PRINCIPAL, userName);
        env.put(Context.SECURITY_CREDENTIALS, password);
        env.put("java.naming.ldap.attributes.binary", "objectSID");
        env.put("com.sun.jndi.ldap.connect.timeout", ldapProperties.getTimeout());

        LdapContext ctx = null;
        try {
            ctx = new InitialLdapContext(env, null);
        } catch (AuthenticationException e) {
            throw new SecurityException("Não foi possível abrir uma conexão com AD.", SecurityErrorCodeType
                    .AUTHENTICATION, e);
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

    private byte[] getUnicodeFromString(String str) throws SecurityException {
        String newQuotedPassword = "\"" + str + "\"";
        byte[] newUnicodePassword;
        try {
            newUnicodePassword = newQuotedPassword.getBytes("UTF-16LE");
        } catch (UnsupportedEncodingException e) {
            throw new SecurityException("Não possível transformar a String em Unicode do AD.", e);
        }
        return newUnicodePassword;
    }
}
