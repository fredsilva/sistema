package br.gov.to.sefaz.arr.processamento.validation.validator.header.arrecadacao;

import br.gov.to.sefaz.arr.processamento.validation.validator.header.HeaderValidator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Validação do {@link br.gov.to.sefaz.arr.processamento.validation.validator.header.HeaderValidator},
 * onde verifica se a Data da Geração do Arquivo está superior a data atual.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 23/06/2016 10:00:00
 */
public class DataGeracaoArquivoArrecValidator implements HeaderValidator {

    private final String dataGeracaoArquivo;

    public DataGeracaoArquivoArrecValidator(String dataGeracaoArquivo) {
        this.dataGeracaoArquivo = dataGeracaoArquivo;
    }

    @Override
    public boolean isValid() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate date = LocalDate.parse(dataGeracaoArquivo, formatter);

        return date.isBefore(LocalDate.now()) || date.isEqual(LocalDate.now());
    }

    @Override
    public int getCodigoErro() {
        return 25;
    }
}
