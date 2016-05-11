package br.gov.to.sefaz.exception;

/**
 * Implementação para exceções de sistema (tempo de execução). Qualquer exceção capturada na aplicação que é um problema
 * de execuçãoo ou bug, uma exceção nao esperada deve ser lançada como SystemException.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 22/04/2016 16:20:00
 */
public class SystemException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SystemException() {
        super();
    }

    /**
     * Construtor com uma mensagem e o problema.
     *
     * @param arg0 Mensagem de exceção
     * @param arg1 Exceção que original
     */
    public SystemException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    /**
     * Construtor apenas com uma mensagem.
     *
     * @param arg0 Mensagem de exceção
     */
    public SystemException(String arg0) {
        super(arg0);
    }

    /**
     * Construtor apenas com o problema.
     *
     * @param arg0 Exceção que original
     */
    public SystemException(Throwable arg0) {
        super(arg0);
    }
}
