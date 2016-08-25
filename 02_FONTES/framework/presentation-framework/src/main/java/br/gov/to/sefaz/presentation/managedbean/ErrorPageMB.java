package br.gov.to.sefaz.presentation.managedbean;

import br.gov.to.sefaz.seg.business.authentication.handler.AuthenticatedUserHandler;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 * ManagedBean da pagina de erro.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 21/06/2016 09:42:00
 */
@ManagedBean(name = "errorPageMB")
public class ErrorPageMB {

    public static final String ERROR_MESSAGE = "javax.servlet.error.message";
    public static final String STATUS_CODE = "javax.servlet.error.status_code";

    public boolean isAuthenticated() {
        return AuthenticatedUserHandler.isAuthenticated();
    }

    /**
     * Verifica se a requisição veio com o parametro "jquery" na url.
     *
     * @return true se veio parametro "jquery"
     */
    public boolean isJquery() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

        return attr.getRequest().getParameter("jquery") != null;
    }

    public String getErrorMessage() {
        return (String) getRequestMap().get(ERROR_MESSAGE);
    }

    /**
     * Trata as mensagens de erro de acordo com o status da requisição.
     */
    public void handleStatusMessage() {
        Integer statusCode = (Integer) getRequestMap().get(STATUS_CODE);

        if (statusCode == 403) {
            setErrorMessage("geral.acesso.negado");
        }
    }

    private void setErrorMessage(String message) {
        getRequestMap().put(ERROR_MESSAGE, SourceBundle.getMessage(MessageUtil.SEG, message));
    }

    private Map<String, Object> getRequestMap() {
        return FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestMap();
    }
}
