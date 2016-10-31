package br.gov.to.sefaz.seg.business.authentication.service;

import br.gov.to.sefaz.seg.business.authentication.domain.SecurityErrorCodeType;
import org.springframework.security.core.AuthenticationException;

/**
 * Implementação para exceções de segurança do sistema.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 18/05/2016 18:40:40
 */
public class SecurityException extends AuthenticationException {

    private static final long serialVersionUID = -1139201823589305801L;

    private SecurityErrorCodeType errorCode;

    public SecurityException(
            String msg) {
        super(msg);
    }

    public SecurityException(
            String msg, SecurityErrorCodeType errorCode) {
        super(msg);
        this.errorCode = errorCode;
    }

    public SecurityException(
            String msg, Throwable ex) {
        super(msg, ex);
    }

    public SecurityException(
            String msg, SecurityErrorCodeType errorCode, Throwable ex) {
        super(msg, ex);
        this.errorCode = errorCode;
    }

    public SecurityErrorCodeType getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(SecurityErrorCodeType errorCode) {
        this.errorCode = errorCode;
    }

}