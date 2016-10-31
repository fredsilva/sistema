package br.gov.to.sefaz.util.message;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

/**
 * Classe para adicionar mensagens no FacesContext que serão exibidas ao usuário.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 26/04/2016 12:10:00
 */
public final class MessageUtil {

    public static final String ARR = "arr_msg";
    public static final String CAT = "cat_msg";
    public static final String PAR = "par_msg";
    public static final String SEG = "seg_msg";

    private MessageUtil() {
        // Todos os métodos são statics.
    }

    /**
     * Adiciona mensagem ao SourceBundle para ser mostrado em tela.
     * @param bundle bundle no qual está presente a mensagem.
     * @param severity severidade do erro.
     * @param key chave para ser buscada a mensagem no bundle.
     */
    public static void addMessage(String bundle, Severity severity, String key) {
        FacesMessage msg = getFacesMessage(bundle, severity, key);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * Adiciona mensagem ao SourceBundle para ser mostrado em tela.
     * @param severity severidade do erro.
     * @param key chave para ser buscada a mensagem no bundle.
     */
    public static void addMessage(Severity severity, String key) {
        addMessage(null, severity, key);
    }

    /**
     * Adiciona mensagem ao SourceBundle para ser mostrado em tela.
     * @param bundle bundle no qual está presente a mensagem.
     * @param key chave para ser buscada a mensagem no bundle.
     */
    public static void addMessage(String bundle, String key) {
        addMessage(bundle, null, key);
    }

    /**
     * Adiciona mensagem ao SourceBundle para ser mostrado em tela.
     * @param key chave para ser buscada a mensagem no bundle.
     */
    public static void addMessage(String key) {
        addMessage(null, null, key);
    }

    /**
     * Adiciona mensagem ao SourceBundle para ser mostrado em tela.
     * @param bundle bundle no qual está presente a mensagem.
     * @param severity severidade do erro.
     * @param keys chave para ser buscada a mensagem no bundle.
     */
    public static void addMessages(String bundle, Severity severity, String... keys) {
        for (String key : keys) {
            addMessage(bundle, severity, key);
        }
    }

    /**
     * Adiciona mensagem ao SourceBundle para ser mostrado em tela.
     * @param severity severidade da mensagem.
     * @param keys chave para ser buscada no bundle.
     */
    public static void addMessages(Severity severity, String... keys) {
        for (String key : keys) {
            addMessage(null, severity, key);
        }
    }

    /**
     * Adiciona mensagem ao SourceBundle para ser mostrado em tela.
     * @param keys chave para ser buscada no bundle.
     */
    public static void addMessages(String... keys) {
        for (String key : keys) {
            addMessage(null, null, key);
        }
    }

    /**
     * Adiciona mensagem de erro ao SourceBundle para ser mostrado em tela.
     * @param bundle bundle no qual está presente a mensagem.
     * @param key chave para ser buscada a mensagem no bundle.
     */
    public static void addErrorMessage(String bundle, String key) {
        addMessage(bundle, FacesMessage.SEVERITY_ERROR , key);
    }

    private static FacesMessage getFacesMessage(String bundle, Severity severity, String key) {
        String msg;
        if (bundle != null) {
            msg = SourceBundle.getMessage(bundle, key);
        } else {
            msg = SourceBundle.getMessage(key);
        }

        if (severity != null) {
            return new FacesMessage(severity, msg, null);
        } else {
            return new FacesMessage(msg);
        }
    }

}
