package br.gov.to.sefaz.presentation.managedbean;

import br.gov.to.sefaz.seg.business.authentication.handler.AuthenticatedUserHandler;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 * ManagedBean da página de erro.
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

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (statusCode == 403 && authentication instanceof AnonymousAuthenticationToken) {
            getRequestMap().put(ERROR_MESSAGE, StringUtils.EMPTY);
        } else if (statusCode == 403) {
            getRequestMap().put(ERROR_MESSAGE, SourceBundle.getMessage(MessageUtil.SEG, "geral.acesso.negado"));
        }
    }

    private Map<String, Object> getRequestMap() {
        return FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestMap();
    }
}
