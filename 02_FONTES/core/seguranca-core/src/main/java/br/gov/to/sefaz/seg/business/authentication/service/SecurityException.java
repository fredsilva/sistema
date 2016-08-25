package br.gov.to.sefaz.seg.business.authentication.service;

import org.springframework.security.core.AuthenticationException;

/**
 * Implementação para exceções de segurança do sistema.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 18/05/2016 18:40:40
 */
public class SecurityException extends AuthenticationException {

    private static final long serialVersionUID = -1139201823589305801L;

    private int errorCode;

    public SecurityException(
            String msg) {
        super(msg);
    }

    public SecurityException(
            String msg, Throwable ex) {
        super(msg, ex);
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

}