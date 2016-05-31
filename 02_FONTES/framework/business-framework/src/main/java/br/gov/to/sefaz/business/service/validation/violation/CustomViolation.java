package br.gov.to.sefaz.business.service.validation.violation;

/**
 * Representa uma violação de uma validação, com sua respectiva mensagem descrevendo tal violação.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 28/04/2016 17:48:00
 */
public class CustomViolation {

    private final String message;

    public CustomViolation(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
