package br.gov.to.sefaz.arr.processamento.validation.validator.header.simplesnacional.builder;

import br.gov.to.sefaz.arr.parametros.business.service.BancosService;
import br.gov.to.sefaz.arr.parametros.business.service.ConveniosArrecService;
import br.gov.to.sefaz.arr.persistence.entity.ConveniosArrec;
import br.gov.to.sefaz.arr.persistence.enums.TipoConvenioEnum;
import br.gov.to.sefaz.arr.processamento.domain.header.TipoArquivoEnum;
import br.gov.to.sefaz.arr.processamento.service.ArquivoRecepcaoService;
import br.gov.to.sefaz.arr.processamento.validation.validator.header.ArquivoConsolidadoValidator;
import br.gov.to.sefaz.arr.processamento.validation.validator.header.HeaderValidator;
import br.gov.to.sefaz.arr.processamento.validation.validator.header.HeaderValidatorBuilder;
import br.gov.to.sefaz.arr.processamento.validation.validator.header.simplesnacional.CodigoBancoSNValidator;
import br.gov.to.sefaz.arr.processamento.validation.validator.header.simplesnacional.CodigoConvenioSNValidator;
import br.gov.to.sefaz.arr.processamento.validation.validator.header.simplesnacional.CodigoHeaderSNValidator;
import br.gov.to.sefaz.arr.processamento.validation.validator.header.simplesnacional.DataGeracaoArquivoSNValidator;
import br.gov.to.sefaz.arr.processamento.validation.validator.header.simplesnacional.NumeroSeqProcessadoSNValidator;
import br.gov.to.sefaz.util.file.FileLineExtractor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Implementação do {@link br.gov.to.sefaz.arr.processamento.validation.validator.header.HeaderValidatorBuilder}
 * para regras de validação do HEADER do arquivo de arrecadação identificado por "1".
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 23/06/2016 10:30:00
 */
@SuppressWarnings("PMD")
public class HeaderSNValidatorBuilder implements HeaderValidatorBuilder {

    private final List<HeaderValidator> headerValidators;
    private final FileLineExtractor fileLineExtractor;
    private final BancosService bancosService;
    private final ArquivoRecepcaoService arquivoRecepcaoService;
    private final ConveniosArrecService conveniosArrecService;
    private String header;

    public HeaderSNValidatorBuilder(FileLineExtractor fileLineExtractor, BancosService bancosService,
            ArquivoRecepcaoService arquivoRecepcaoService, ConveniosArrecService conveniosArrecService) {
        this.fileLineExtractor = fileLineExtractor;
        this.bancosService = bancosService;
        this.arquivoRecepcaoService = arquivoRecepcaoService;
        this.conveniosArrecService = conveniosArrecService;
        headerValidators = new ArrayList<>();
    }

    @Override
    public HeaderSNValidatorBuilder withLineHeader(String header) {
        this.header = header;

        return this;
    }

    @Override
    public HeaderSNValidatorBuilder withAllValidators() {
        return withCodigoHeaderSNValidator()
                .withCodigoBancoSNValidator()
                .withCodigoConvenioSNValidator()
                .withDataGeracaoArquivoSNValidator()
                .withNumeroSeqProcessadoSNValidator()
                .withArquivoConsolidadoValidator();
    }

    @Override
    public List<HeaderValidator> build() {
        return headerValidators;
    }

    private HeaderSNValidatorBuilder withCodigoHeaderSNValidator() {
        String value = fileLineExtractor.getValueFromString(header, 0, 1);
        headerValidators.add(new CodigoHeaderSNValidator(value));

        return this;
    }

    private HeaderSNValidatorBuilder withCodigoConvenioSNValidator() {
        String value = fileLineExtractor.getValueFromString(header, 9, 29);
        String banco = fileLineExtractor.getValueFromString(header, 75, 78);
        Integer codigoBanco = Integer.valueOf(banco);

        headerValidators.add(new CodigoConvenioSNValidator(value, codigoBanco, conveniosArrecService));

        return this;
    }

    private HeaderSNValidatorBuilder withCodigoBancoSNValidator() {
        String value = fileLineExtractor.getValueFromString(header, 75, 78);
        headerValidators.add(new CodigoBancoSNValidator(Integer.valueOf(value), bancosService));

        return this;
    }

    private HeaderSNValidatorBuilder withDataGeracaoArquivoSNValidator() {
        String value = fileLineExtractor.getValueFromString(header, 29, 37);
        headerValidators.add(new DataGeracaoArquivoSNValidator(value));

        return this;
    }

    private HeaderSNValidatorBuilder withNumeroSeqProcessadoSNValidator() {
        String value = fileLineExtractor.getValueFromString(header, 1, 9);
        headerValidators.add(new NumeroSeqProcessadoSNValidator(Long.valueOf(value), arquivoRecepcaoService));

        return this;
    }

    private HeaderSNValidatorBuilder withArquivoConsolidadoValidator() {
        String banco = fileLineExtractor.getValueFromString(header, 75, 78);
        Integer bancoId = Integer.valueOf(banco);
        String dataGeracaoArquivo = fileLineExtractor.getValueFromString(header, 29, 37);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate date = LocalDate.parse(dataGeracaoArquivo, formatter);
        ConveniosArrec conveniosArrec =
                conveniosArrecService.findByBancoAndTipoConvenio(bancoId, TipoConvenioEnum.SIMPLES_NACIONAL);
        Long idConvenio = Objects.isNull(conveniosArrec) ? null : conveniosArrec.getIdConvenio();

        String tipoArquivo = fileLineExtractor.getValueFromString(header, 43, 45);
        TipoArquivoEnum tipoArquivoEnum = TipoArquivoEnum.getById(tipoArquivo);

        headerValidators.add(new ArquivoConsolidadoValidator(tipoArquivoEnum, bancoId,
                idConvenio, date, arquivoRecepcaoService));

        return this;
    }

}
