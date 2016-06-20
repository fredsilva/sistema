package br.gov.to.sefaz.util.message;

import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.util.Locale;
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
        ResourceBundle resourcebundle = getResourceBundle(bundle);
        return resourcebundle.getString(key);
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
        ResourceBundle resourcebundle = getResourceBundle(bundle);
        return MessageFormat.format(resourcebundle.getString(key), params);
    }

    /**
     * Método que retorna a mensagem internacionalizada de um aruivo/bundle default.
     *
     * @param key Chave da mensagem do arquivo de internacionalização
     * @return Mensagem internacionalizada
     */
    public static String getMessage(String key) {
        return getMessage(DEFAULT_BUNDLE, key);
    }

    /**
     * Método que retorna a mensagem internacionalizada de um aruivo/bundle default substituindo os parâmetros na
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
     * Transforma uma expression language em uma mensagem tratada e cadastrada no(s) resouce(s) bungle(s).
     *
     * @param key Chave do message baundle no formato EL - Expression Language
     * @return Mensagem tratada
     */
    public static String getMessageByExpression(String key) {
        if (StringUtils.isEmpty(key)) {
            return getMessage("mensagem.nao.encontrada");
        }
        String bundle = key.substring(key.indexOf('{') + 1, key.indexOf('['));
        key = key.substring(key.indexOf('\'') + 1, key.lastIndexOf('\''));
        return getMessage(bundle, key);
        // FacesContext facesContext = FacesContext.getCurrentInstance();
        // ExpressionFactory elFactory = facesContext.getApplication().getExpressionFactory();
        // ValueExpression valueExp = elFactory.createValueExpression(facesContext.getELContext(), key, Object.class);
        // Object value = valueExp.getValue(facesContext.getELContext());
        //
        // return value == null ? key : value.toString();
    }

    private static ResourceBundle getResourceBundle(String bundle) {
        return ResourceBundle.getBundle(createNameBundle(bundle), new Locale("pt", "BR"));
        // FacesContext facesContext = FacesContext.getCurrentInstance();
        // return facesContext.getApplication().getResourceBundle(facesContext, bundle);
    }

    private static String createNameBundle(String bundle) {
        return BASE_PKG_BUNDLE + bundle.replace("msg", "message");
    }

}
