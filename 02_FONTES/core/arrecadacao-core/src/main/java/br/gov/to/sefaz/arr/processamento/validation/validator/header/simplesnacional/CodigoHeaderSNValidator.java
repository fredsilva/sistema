package br.gov.to.sefaz.arr.processamento.validation.validator.header.simplesnacional;

import br.gov.to.sefaz.arr.processamento.validation.validator.header.HeaderValidator;

/**
 * Validação do {@link br.gov.to.sefaz.arr.processamento.validation.validator.header.HeaderValidator},
 * onde verifica se o Código do HEADER do arquivo é igual a "1".
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 24/06/2016 15:37:00
 */
public class CodigoHeaderSNValidator implements HeaderValidator {

    private final String codigoHeader;

    public CodigoHeaderSNValidator(String codigoHeader) {
        this.codigoHeader = codigoHeader;
    }

    @Override
    public boolean isValid() {
        return "1".equals(codigoHeader);
    }

    @Override
    public int getCodigoErro() {
        return 24;
    }
}
