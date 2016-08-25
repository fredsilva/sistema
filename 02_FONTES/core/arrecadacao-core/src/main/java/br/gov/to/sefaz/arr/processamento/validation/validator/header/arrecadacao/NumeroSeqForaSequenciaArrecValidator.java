package br.gov.to.sefaz.arr.processamento.validation.validator.header.arrecadacao;

import br.gov.to.sefaz.arr.processamento.service.ArquivoRecepcaoService;
import br.gov.to.sefaz.arr.processamento.validation.validator.header.HeaderValidator;

import java.time.LocalDate;

/**
 * Validação do {@link br.gov.to.sefaz.arr.processamento.validation.validator.header.HeaderValidator},
 * onde verifica se o Número sequencial do arquivo está fora de sequência.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 23/06/2016 10:00:00
 */
public class NumeroSeqForaSequenciaArrecValidator implements HeaderValidator {

    private final Long numeroSequencial;
    private final Long codigoConvenio;
    private final Integer codigoBanco;
    private final LocalDate dataRecepcao;
    private final ArquivoRecepcaoService arquivoRecepcaoService;

    public NumeroSeqForaSequenciaArrecValidator(Long numeroSequencial, Long codigoConvenio, Integer codigoBanco,
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

        return lastNumeroSequencial < numeroSequencial;
    }

    @Override
    public int getCodigoErro() {
        return 30;
    }
}
