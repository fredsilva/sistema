package br.gov.to.sefaz.arr.processamento.validation.validator.header.simplesnacional;

import br.gov.to.sefaz.arr.processamento.validation.validator.header.HeaderValidator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Validação do {@link br.gov.to.sefaz.arr.processamento.validation.validator.header.HeaderValidator},
 * onde verifica se a Data da Geração do Arquivo está superior a data atual.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 24/06/2016 15:37:00
 */
public class DataGeracaoArquivoSNValidator implements HeaderValidator {

    private final String dataGeracaoArquivo;

    public DataGeracaoArquivoSNValidator(String dataGeracaoArquivo) {
        this.dataGeracaoArquivo = dataGeracaoArquivo;
    }

    @Override
    public boolean isValid() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate date = LocalDate.parse(dataGeracaoArquivo, formatter);

        return date.isBefore(LocalDate.now());
    }

    @Override
    public int getCodigoErro() {
        return 25;
    }
}
