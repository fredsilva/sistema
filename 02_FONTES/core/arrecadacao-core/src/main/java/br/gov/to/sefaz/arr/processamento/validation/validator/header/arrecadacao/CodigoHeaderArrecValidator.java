package br.gov.to.sefaz.arr.processamento.validation.validator.header.arrecadacao;

import br.gov.to.sefaz.arr.processamento.validation.validator.header.HeaderValidator;

/**
 * Validação do {@link br.gov.to.sefaz.arr.processamento.validation.validator.header.HeaderValidator},
 * onde verifica se o Código do HEADER do arquivo é igual a "A".
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 23/06/2016 10:00:00
 */
public class CodigoHeaderArrecValidator implements HeaderValidator {

    private final String codigoHeader;

    public CodigoHeaderArrecValidator(String codigoHeader) {
        this.codigoHeader = codigoHeader;
    }

    @Override
    public boolean isValid() {
        return "A".equals(codigoHeader);
    }

    @Override
    public int getCodigoErro() {
        return 24;
    }
}
