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

    public static void addMesage(String bundle, Severity severity, String key) {
        FacesMessage msg = getFacesMessage(bundle, severity, key);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public static void addMesage(Severity severity, String key) {
        addMesage(null, severity, key);
    }

    public static void addMesage(String bundle, String key) {
        addMesage(bundle, null, key);
    }

    public static void addMesage(String key) {
        addMesage(null, null, key);
    }

    public static void addMessages(String bundle, Severity severity, String... keys) {
        for (String key : keys) {
            addMesage(bundle, severity, key);
        }
    }

    public static void addMessages(Severity severity, String... keys) {
        for (String key : keys) {
            addMesage(null, severity, key);
        }
    }

    public static void addMessages(String... keys) {
        for (String key : keys) {
            addMesage(null, null, key);
        }
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
