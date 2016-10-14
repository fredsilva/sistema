package br.gov.to.sefaz.exception;

/**
 * Implementação para exceções de negócio do sistema. Qualquer exceção capturada na aplicação que é um problema de
 * regras de negócio ou validação, uma exceção BusinessException.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 18/05/2016 18:34:57
 */
public class BusinessException extends RuntimeException implements HandledSystemException {

    private static final long serialVersionUID = 8043841465872266679L;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

}
