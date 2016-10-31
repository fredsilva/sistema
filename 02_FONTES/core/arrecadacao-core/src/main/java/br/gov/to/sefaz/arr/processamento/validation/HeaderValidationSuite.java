package br.gov.to.sefaz.arr.processamento.validation;

import br.gov.to.sefaz.arr.processamento.validation.validator.header.HeaderValidator;
import br.gov.to.sefaz.exception.file.ProcessFileException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Suite de validação para o HEADER do arquivo.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 22/06/2016 16:29:00
 */
@Component
public class HeaderValidationSuite {

    /**
     * Realiza a validação da lista de
     * {@link br.gov.to.sefaz.arr.processamento.validation.validator.header.HeaderValidator} conforme
     * o {@link br.gov.to.sefaz.arr.processamento.type.ProcessFileType#getHeaderValidatorBuilder()}.
     */
    public void validate(List<HeaderValidator> headerValidators) throws ProcessFileException {
        for (HeaderValidator headerValidator : headerValidators) {
            if (!headerValidator.isValid()) {
                int codigoErro = headerValidator.getCodigoErro();
                throw new ProcessFileException(codigoErro);
            }
        }
    }
}
