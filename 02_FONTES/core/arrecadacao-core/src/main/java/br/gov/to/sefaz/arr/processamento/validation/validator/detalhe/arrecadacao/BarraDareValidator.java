package br.gov.to.sefaz.arr.processamento.validation.validator.detalhe.arrecadacao;

import br.gov.to.sefaz.arr.processamento.validation.validator.detalhe.DetalheValidator;

/**
 * Valida se a versão do código de barras é igual a 5.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 13/07/2016 17:13:00
 */
public class BarraDareValidator implements DetalheValidator {

    private final int versaoDare;

    public BarraDareValidator(int versaoDare) {
        this.versaoDare = versaoDare;
    }

    @Override
    public boolean isValid() {
        return versaoDare == 5;
    }

    @Override
    public int getCodigoErro() {
        return 11;
    }
}
