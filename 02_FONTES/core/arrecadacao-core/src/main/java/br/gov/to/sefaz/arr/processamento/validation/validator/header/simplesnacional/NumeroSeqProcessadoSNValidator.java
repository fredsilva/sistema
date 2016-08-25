package br.gov.to.sefaz.arr.processamento.validation.validator.header.simplesnacional;

import br.gov.to.sefaz.arr.processamento.service.ArquivoRecepcaoService;
import br.gov.to.sefaz.arr.processamento.validation.validator.header.HeaderValidator;

/**
 * Validação do {@link br.gov.to.sefaz.arr.processamento.validation.validator.header.HeaderValidator},
 * onde verifica se o Número sequencial do arquivo já foi processado.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 24/06/2016 15:37:00
 */
public class NumeroSeqProcessadoSNValidator implements HeaderValidator {

    private final Long numeroSequencial;
    private final ArquivoRecepcaoService arquivoRecepcaoService;

    public NumeroSeqProcessadoSNValidator(Long numeroSequencial, ArquivoRecepcaoService arquivoRecepcaoService) {
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
