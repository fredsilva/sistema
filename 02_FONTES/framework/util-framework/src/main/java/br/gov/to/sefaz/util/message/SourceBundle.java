package br.gov.to.sefaz.util.message;

import br.gov.to.sefaz.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Classe para internacionalização de mensagens.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 22/04/2016 16:20:00
 */
public final class SourceBundle {

    private static final String BASE_PKG_BUNDLE = "br.gov.to.sefaz.";

    public static final String DEFAULT_BUNDLE = "msg";

    private SourceBundle() {
        // Todos os métodos são statics.
    }

    /**
     * Método que retorna a mensagem internacionalizada de arquivo/bundle específico.
     *
     * @param bundle Arquivo que contém a mensagem de internacionalização
     * @param key Chave da mensagem do arquivo de internacionalização
     * @return Mensagem internacionalizada
     */
    public static String getMessage(String bundle, String key) {
        try {
            ResourceBundle resourcebundle = getResourceBundle(bundle);
            return resourcebundle.getString(key);
        } catch (MissingResourceException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    /**
     * Método que retorna a mensagem internacionalizada de arquivo/bundle específico substituindo os parâmetros na
     * mensagem.
     *
     * @param bundle Arquivo que contém a mensagem de internacionalização
     * @param key Chave da mensagem do arquivo de internacionalização
     * @param params Paramêtros da mensagem
     * @return Mensagem internacionalizada
     */
    public static String getMessage(String bundle, String key, Object... params) {
        try {
            ResourceBundle resourcebundle = getResourceBundle(bundle);
            return MessageFormat.format(resourcebundle.getString(key), params);
        } catch (MissingResourceException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    /**
     * Método que retorna a mensagem internacionalizada de um arquivo/bundle default.
     *
     * @param key Chave da mensagem do arquivo de internacionalização
     * @return Mensagem internacionalizada
     */
    public static String getMessage(String key) {
        return getMessage(DEFAULT_BUNDLE, key);
    }

    /**
     * Método que retorna a mensagem internacionalizada de um arquivo/bundle default substituindo os parâmetros na
     * mensagem.
     *
     * @param key Chave da mensagem do arquivo de internacionalização
     * @param params Paramêtros da mensagem
     * @return Mensagem internacionalizada
     */
    public static String getMessage(String key, Object... params) {
        return MessageFormat.format(getMessage(DEFAULT_BUNDLE, key), params);
    }

    /**
     * Transforma uma expression language em uma mensagem tratada e cadastrada no(s) resource(s) bundle(s).
     *
     * @param key Chave do message bundle no formato EL - Expression Language
     * @return Mensagem tratada
     */
    public static String getMessageByExpression(String key) {

        if (StringUtils.isEmpty(key)) {
            return getMessage("mensagem.nao.encontrada");
        }

        try {
            String bundle = key.substring(key.indexOf('{') + 1, key.indexOf('['));
            key = key.substring(key.indexOf('\'') + 1, key.lastIndexOf('\''));
            return getMessage(bundle, key);
        } catch (StringIndexOutOfBoundsException e) {
            Object param = key;
            String message = getMessage("mensagem.inconsistente", param);
            throw new BusinessException(message, e);
        }
    }

    private static ResourceBundle getResourceBundle(String bundle) {
        return ResourceBundle.getBundle(createNameBundle(bundle), new Locale("pt", "BR"));
    }

    private static String createNameBundle(String bundle) {
        return BASE_PKG_BUNDLE + bundle.replace("msg", "message");
    }

}
