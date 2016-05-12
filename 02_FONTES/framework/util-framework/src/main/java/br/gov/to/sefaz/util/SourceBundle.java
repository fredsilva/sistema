package br.gov.to.sefaz.util;

import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

/**
 * Classe para internacionalização de mensagens.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 22/04/2016 16:20:00
 */
public final class SourceBundle {

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
     * Método que retorna a mensagem internacionalizada de um aruivo/bundle default.
     *
     * @param key Chave da mensagem do arquivo de internacionalização
     * @return Mensagem internacionalizada
     */
    public static String getMessage(String key) {
        return getMessage(DEFAULT_BUNDLE, key);
    }

    private static ResourceBundle getResourceBundle(String bundle) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        return facesContext.getApplication().getResourceBundle(facesContext, bundle);
    }

}