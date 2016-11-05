package br.gov.to.sefaz.persistence.query.builder.hql.handler;

/**
 * Classe respnsável por gerenciar as exceptions das entidades da aplicação.
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 15/07/2016 17:46:00
 */
public class EntityHandlingException extends RuntimeException {

    public EntityHandlingException(String message) {
        super(message);
    }

    public EntityHandlingException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityHandlingException(Throwable cause) {
        super(cause);
    }
}
