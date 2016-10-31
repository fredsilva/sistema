package br.gov.to.sefaz.arr.processamento.validation.validator.header.arrecadacao;

import br.gov.to.sefaz.arr.processamento.service.ArquivoRecepcaoService;
import br.gov.to.sefaz.arr.processamento.validation.validator.header.HeaderValidator;

/**
 * Validação do {@link br.gov.to.sefaz.arr.processamento.validation.validator.header.HeaderValidator},
 * onde verifica se o Número sequencial do arquivo já foi processado.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 23/06/2016 10:00:00
 */
public class NumeroSeqProcessadoArrecValidator implements HeaderValidator {

    private final Long numeroSequencial;
    private final ArquivoRecepcaoService arquivoRecepcaoService;

    public NumeroSeqProcessadoArrecValidator(Long numeroSequencial, ArquivoRecepcaoService arquivoRecepcaoService) {
        this.numeroSequencial = numeroSequencial;
        this.arquivoRecepcaoService = arquivoRecepcaoService;
    }

    @Override
    public boolean isValid() {
        return !arquivoRecepcaoService.existsNumeroSequencial(numeroSequencial);
    }

    @Override
    public int getCodigoErro() {
        return 29;
    }
}
