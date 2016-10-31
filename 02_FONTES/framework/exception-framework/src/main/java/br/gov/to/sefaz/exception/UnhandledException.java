package br.gov.to.sefaz.exception;

/**
 * Exceção para lançar no lugar de exceções inexperadas que interrompem o fluxo de negocio (IO exceptions por exemplo).
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 02/06/2016 12:45:00
 */
public class UnhandledException extends RuntimeException {

    private static final long serialVersionUID = 776022527331077007L;

    public UnhandledException(String message) {
        super(message);
    }

    public UnhandledException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnhandledException(Throwable cause) {
        super(cause);
    }
}
