package br.gov.to.sefaz.util.json;

/**
 * Exceção que é lançada caso ocorra algum erro ao mapear JSON para Object ou vice versa.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 07/06/2016 16:27:00
 */
public class JasonMappingException extends RuntimeException {

    private static final long serialVersionUID = -1105351963728206208L;

    public JasonMappingException(String message) {
        super(message);
    }

    public JasonMappingException(String message, Throwable cause) {
        super(message, cause);
    }

    public JasonMappingException(Throwable cause) {
        super(cause);
    }
}
