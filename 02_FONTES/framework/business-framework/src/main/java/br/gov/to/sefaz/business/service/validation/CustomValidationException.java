package br.gov.to.sefaz.business.service.validation;

import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.exception.HandledSystemException;
import br.gov.to.sefaz.util.json.JsonMapperUtils;

import java.util.Set;
import javax.validation.ValidationException;

/**
 * Exceção extendida do {@link ValidationException} para mensagem customizada de violações convertidas para um array
 * JSON.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 28/04/2016 17:48:00
 */
public class CustomValidationException extends ValidationException implements HandledSystemException {

    private static final long serialVersionUID = 3452808864129438820L;

    private final Set<CustomViolation> customViolations;

    public CustomValidationException(Set<CustomViolation> customViolations) {
        super();
        this.customViolations = customViolations;
    }

    @Override
    public String getMessage() {
        return JsonMapperUtils.objectToJson(customViolations);
    }
}
