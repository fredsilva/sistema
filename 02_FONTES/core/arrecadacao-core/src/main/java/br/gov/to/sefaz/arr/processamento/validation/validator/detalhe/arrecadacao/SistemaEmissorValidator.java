package br.gov.to.sefaz.arr.processamento.validation.validator.detalhe.arrecadacao;

import br.gov.to.sefaz.arr.processamento.validation.validator.detalhe.DetalheValidator;

/**
 * Valida se o código do sistema emissor extraído do código de barras é igual a 1 ou 2.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 13/07/2016 17:13:00
 */
public class SistemaEmissorValidator implements DetalheValidator {

    private final int sistemaEmissor;

    public SistemaEmissorValidator(int sistemaEmissor) {
        this.sistemaEmissor = sistemaEmissor;
    }

    @Override
    public boolean isValid() {
        return sistemaEmissor == 1 || sistemaEmissor == 2;
    }

    @Override
    public int getCodigoErro() {
        return 13;
    }
}
