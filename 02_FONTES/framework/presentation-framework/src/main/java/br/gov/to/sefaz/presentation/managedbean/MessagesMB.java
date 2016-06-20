package br.gov.to.sefaz.presentation.managedbean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

/**
 * Managed Bean utilitario para mensagens.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 16/06/2016 14:16:00
 */
@ManagedBean(name = "messagesMB")
public class MessagesMB {

    /**
     * Verifica se a severidade da mensagem e {@link FacesMessage#SEVERITY_INFO}.
     *
     * @param message mensagem
     * @return true se é {@link FacesMessage#SEVERITY_INFO}
     */
    public boolean isInfo(FacesMessage message) {
        return message.getSeverity() == FacesMessage.SEVERITY_INFO;
    }

    /**
     * Verifica se a severidade da mensagem e {@link FacesMessage#SEVERITY_WARN}.
     *
     * @param message mensagem
     * @return true se é {@link FacesMessage#SEVERITY_WARN}
     */
    public boolean isWarn(FacesMessage message) {
        return message.getSeverity() == FacesMessage.SEVERITY_WARN;
    }

    /**
     * Verifica se a severidade da mensagem e {@link FacesMessage#SEVERITY_ERROR}
     * ou {@link FacesMessage#SEVERITY_FATAL}.
     *
     * @param message mensagem
     * @return true se é {@link FacesMessage#SEVERITY_ERROR} ou {@link FacesMessage#SEVERITY_FATAL}
     */
    public boolean isErrorOrFatal(FacesMessage message) {
        return message.getSeverity() == FacesMessage.SEVERITY_ERROR
                || message.getSeverity() == FacesMessage.SEVERITY_FATAL;
    }
}
