package br.gov.to.sefaz.util.properties;

/**
 * Exceção lançada quando não for possível ler os parametros da aplicação.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 07/06/2016 16:57:00
 */
public class ApplicationPropertyLoadException extends RuntimeException {

    private static final long serialVersionUID = -1759346555811983944L;

    public ApplicationPropertyLoadException(String message) {
        super(message);
    }

    public ApplicationPropertyLoadException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationPropertyLoadException(Throwable cause) {
        super(cause);
    }
}
