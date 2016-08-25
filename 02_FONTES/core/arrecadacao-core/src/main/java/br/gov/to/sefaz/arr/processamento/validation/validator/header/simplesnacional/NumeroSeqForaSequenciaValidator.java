package br.gov.to.sefaz.arr.processamento.validation.validator.header.simplesnacional;

import br.gov.to.sefaz.arr.processamento.service.ArquivoRecepcaoService;
import br.gov.to.sefaz.arr.processamento.validation.validator.header.HeaderValidator;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Validação do {@link br.gov.to.sefaz.arr.processamento.validation.validator.header.HeaderValidator},
 * onde verifica se o Número sequencial do arquivo está fora de sequência.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 24/06/2016 15:37:00
 */
public class NumeroSeqForaSequenciaValidator implements HeaderValidator {

    private final Long numeroSequencial;
    private final Long codigoConvenio;
    private final Integer codigoBanco;
    private final LocalDate dataRecepcao;
    private final ArquivoRecepcaoService arquivoRecepcaoService;

    public NumeroSeqForaSequenciaValidator(Long numeroSequencial, Long codigoConvenio, Integer codigoBanco,
            LocalDate dataRecepcao, ArquivoRecepcaoService arquivoRecepcaoService) {
        this.numeroSequencial = numeroSequencial;
        this.codigoConvenio = codigoConvenio;
        this.codigoBanco = codigoBanco;
        this.dataRecepcao = dataRecepcao;
        this.arquivoRecepcaoService = arquivoRecepcaoService;
    }

    @Override
    public boolean isValid() {
        Long lastNumeroSequencial = arquivoRecepcaoService.getLastNumeroSequencialBy(codigoConvenio, codigoBanco,
                dataRecepcao);

        return Objects.isNull(lastNumeroSequencial) || lastNumeroSequencial < numeroSequencial;
    }

    @Override
    public int getCodigoErro() {
        return 30;
    }
}
