package br.gov.to.sefaz.arr.processamento.validation.validator.header.arrecadacao;

import br.gov.to.sefaz.arr.processamento.validation.validator.header.HeaderValidator;

/**
 * Validação do {@link br.gov.to.sefaz.arr.processamento.validation.validator.header.HeaderValidator},
 * onde verifica se o Código da Remessa do arquivo é igual a "2".
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 23/06/2016 10:14:00
 */
public class CodigoRemessaArrecValidator implements HeaderValidator {

    private final String codigoRemessa;

    public CodigoRemessaArrecValidator(String codigoRemessa) {
        this.codigoRemessa = codigoRemessa;
    }

    public boolean isValid() {
        return "2".equals(codigoRemessa);
    }

    public int getCodigoErro() {
        return 33;
    }


}
