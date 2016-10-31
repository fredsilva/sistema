package br.gov.to.sefaz.arr.processamento.validation;

import br.gov.to.sefaz.arr.processamento.validation.validator.detalhe.DetalheValidator;
import br.gov.to.sefaz.exception.file.ProcessFileException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Suite de validação para o Detalhe do arquivo.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 13/07/2016 17:04:00
 */
@Component
public class DetalheValidationSuite {

    /**
     * Realiza a validação da lista de
     * {@link br.gov.to.sefaz.arr.processamento.validation.validator.detalhe.DetalheValidator}.
     */
    public void validate(List<DetalheValidator> detalheValidators) throws ProcessFileException {
        for (DetalheValidator detalheValidator : detalheValidators) {
            if (!detalheValidator.isValid()) {
                int codigoErro = detalheValidator.getCodigoErro();
                throw new ProcessFileException(codigoErro);
            }
        }
    }
}
