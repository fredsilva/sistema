package br.gov.to.sefaz.arr.processamento.validation.validator.header.arrecadacao.builder;

import br.gov.to.sefaz.arr.parametros.business.service.BancosService;
import br.gov.to.sefaz.arr.parametros.business.service.ConveniosArrecService;
import br.gov.to.sefaz.arr.processamento.domain.header.TipoArquivoEnum;
import br.gov.to.sefaz.arr.processamento.service.ArquivoRecepcaoService;
import br.gov.to.sefaz.arr.processamento.validation.validator.header.ArquivoConsolidadoValidator;
import br.gov.to.sefaz.arr.processamento.validation.validator.header.HeaderValidator;
import br.gov.to.sefaz.arr.processamento.validation.validator.header.HeaderValidatorBuilder;
import br.gov.to.sefaz.arr.processamento.validation.validator.header.arrecadacao.CodigoBancoArrecValidator;
import br.gov.to.sefaz.arr.processamento.validation.validator.header.arrecadacao.CodigoConvenioArrecValidator;
import br.gov.to.sefaz.arr.processamento.validation.validator.header.arrecadacao.CodigoHeaderArrecValidator;
import br.gov.to.sefaz.arr.processamento.validation.validator.header.arrecadacao.CodigoRemessaArrecValidator;
import br.gov.to.sefaz.arr.processamento.validation.validator.header.arrecadacao.DataGeracaoArquivoArrecValidator;
import br.gov.to.sefaz.arr.processamento.validation.validator.header.arrecadacao.NumeroSeqProcessadoArrecValidator;
import br.gov.to.sefaz.arr.processamento.validation.validator.header.arrecadacao.VersaoArquivoArrecValidator;
import br.gov.to.sefaz.arr.processamento.validation.validator.header.arrecadacao.VersaoArquivoTipoTransmissaoArrecValidator;
import br.gov.to.sefaz.util.file.FileLineExtractor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementação do {@link br.gov.to.sefaz.arr.processamento.validation.validator.header.HeaderValidatorBuilder}
 * para regras de validação do HEADER do arquivo de arrecadação identificado por "A".
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 23/06/2016 10:30:00
 */
public class HeaderArrecValidatorBuilder implements HeaderValidatorBuilder {

    private final List<HeaderValidator> headerValidators;
    private final FileLineExtractor fileLineExtractor;
    private final BancosService bancosService;
    private final ConveniosArrecService conveniosArrecService;
    private final ArquivoRecepcaoService arquivoRecepcaoService;
    private String header;

    public HeaderArrecValidatorBuilder(FileLineExtractor fileLineExtractor, BancosService bancosService,
            ConveniosArrecService conveniosArrecService, ArquivoRecepcaoService arquivoRecepcaoService) {
        this.fileLineExtractor = fileLineExtractor;
        this.bancosService = bancosService;
        this.conveniosArrecService = conveniosArrecService;
        this.arquivoRecepcaoService = arquivoRecepcaoService;
        headerValidators = new ArrayList<>();
    }

    @Override
    public HeaderArrecValidatorBuilder withLineHeader(String header) {
        this.header = header;

        return this;
    }

    @Override
    public HeaderArrecValidatorBuilder withAllValidators() {
        return withCodigoHeaderValidator()
                .withCodigoBancoArrecValidator()
                .withCodigoConvenioArrecValidator()
                .withCodigoRemessaValidator()
                .withDataGeracaoArquivoArrecValidator()
                .withNumeroSeqProcessadoArrecValidator()
                .withVersaoArquivoArrecValidator()
                .withVersaoArquivoTipoTransmissaoArrecValidator()
                .withArquivoConsolidadoValidator();
    }

    @Override
    public List<HeaderValidator> build() {
        return headerValidators;
    }

    private HeaderArrecValidatorBuilder withCodigoHeaderValidator() {
        String value = fileLineExtractor.getValueFromString(header, 0, 1);
        headerValidators.add(new CodigoHeaderArrecValidator(value));

        return this;
    }

    private HeaderArrecValidatorBuilder withCodigoRemessaValidator() {
        String value = fileLineExtractor.getValueFromString(header, 1, 2);
        headerValidators.add(new CodigoRemessaArrecValidator(value));

        return this;
    }

    private HeaderArrecValidatorBuilder withCodigoBancoArrecValidator() {
        String value = fileLineExtractor.getValueFromString(header, 42, 45);
        headerValidators.add(new CodigoBancoArrecValidator(Integer.valueOf(value), bancosService));

        return this;
    }

    private HeaderArrecValidatorBuilder withCodigoConvenioArrecValidator() {
        String value = fileLineExtractor.getValueFromString(header, 2, 22);
        headerValidators.add(new CodigoConvenioArrecValidator(Long.valueOf(value), conveniosArrecService));

        return this;
    }

    private HeaderArrecValidatorBuilder withDataGeracaoArquivoArrecValidator() {
        String value = fileLineExtractor.getValueFromString(header, 64, 73);
        headerValidators.add(new DataGeracaoArquivoArrecValidator(value));

        return this;
    }

    private HeaderArrecValidatorBuilder withNumeroSeqProcessadoArrecValidator() {
        String value = fileLineExtractor.getValueFromString(header, 73, 79);
        headerValidators.add(new NumeroSeqProcessadoArrecValidator(Long.valueOf(value), arquivoRecepcaoService));

        return this;
    }

    private HeaderArrecValidatorBuilder withVersaoArquivoArrecValidator() {
        String value = fileLineExtractor.getValueFromString(header, 79, 81);
        headerValidators.add(new VersaoArquivoArrecValidator(value));

        return this;
    }

    private HeaderArrecValidatorBuilder withVersaoArquivoTipoTransmissaoArrecValidator() {
        String value = fileLineExtractor.getValueFromString(header, 79, 81);
        String tipoTransmissao = fileLineExtractor.getValueFromString(header, 81, 82);
        headerValidators.add(new VersaoArquivoTipoTransmissaoArrecValidator(value, tipoTransmissao));

        return this;
    }

    private HeaderArrecValidatorBuilder withArquivoConsolidadoValidator() {
        String convenio = fileLineExtractor.getValueFromString(header, 2, 22);
        String banco = fileLineExtractor.getValueFromString(header, 42, 45);

        String dataGeracaoArquivo = fileLineExtractor.getValueFromString(header, 64, 73);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate date = LocalDate.parse(dataGeracaoArquivo, formatter);

        String tipoArquivo = fileLineExtractor.getValueFromString(header, 79, 81);
        TipoArquivoEnum tipoArquivoEnum = TipoArquivoEnum.getById(tipoArquivo);

        headerValidators.add(new ArquivoConsolidadoValidator(tipoArquivoEnum, Integer.valueOf(banco),
                Long.valueOf(convenio), date, arquivoRecepcaoService));

        return this;
    }

}
