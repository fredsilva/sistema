package br.gov.to.sefaz.arr.processamento.validation.validator.header.arrecadacao;

import br.gov.to.sefaz.arr.processamento.domain.header.TipoArquivoEnum;
import br.gov.to.sefaz.arr.processamento.validation.validator.header.HeaderValidator;

/**
 * Validação do {@link br.gov.to.sefaz.arr.processamento.validation.validator.header.HeaderValidator},
 * onde verifica se a Versão do Arquivo é diferente de "04" e diferente de "01".
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 23/06/2016 10:00:00
 */
public class VersaoArquivoArrecValidator implements HeaderValidator {

    private final String versaoArquivo;

    public VersaoArquivoArrecValidator(String versaoArquivo) {
        this.versaoArquivo = versaoArquivo;
    }

    @Override
    public boolean isValid() {
        return versaoArquivo.equals(TipoArquivoEnum.PARCIAL.getName())
                || versaoArquivo.equals(TipoArquivoEnum.CONSOLIDADO.getName());
    }

    @Override
    public int getCodigoErro() {
        return 27;
    }
}
