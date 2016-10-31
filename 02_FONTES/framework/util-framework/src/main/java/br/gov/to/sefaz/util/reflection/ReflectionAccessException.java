package br.gov.to.sefaz.util.reflection;

/**
 * Excessão lançada quando ouver algum erro de acesso via reflection a um objeto.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 22/08/2016 14:28:00
 */
public class ReflectionAccessException extends RuntimeException {

    public ReflectionAccessException(String message) {
        super(message);
    }

    public ReflectionAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReflectionAccessException(Throwable cause) {
        super(cause);
    }
}
