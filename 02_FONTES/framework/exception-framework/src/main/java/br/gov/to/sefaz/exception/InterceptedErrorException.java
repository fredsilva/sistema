package br.gov.to.sefaz.exception;

/**
 * Exceção lançada no lugar de uma exceção do sistema que não foi tratada mas foi interceptada.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 02/06/2016 12:45:00
 */
public class InterceptedErrorException extends RuntimeException implements HandledSystemException {

    private static final long serialVersionUID = 776022527331077007L;

    public InterceptedErrorException(String message) {
        super(message);
    }

    public InterceptedErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public InterceptedErrorException(Throwable cause) {
        super(cause);
    }
}
