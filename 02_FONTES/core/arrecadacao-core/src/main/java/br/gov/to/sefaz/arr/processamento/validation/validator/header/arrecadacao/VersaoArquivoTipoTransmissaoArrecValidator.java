package br.gov.to.sefaz.arr.processamento.validation.validator.header.arrecadacao;

import br.gov.to.sefaz.arr.processamento.domain.header.TipoArquivoEnum;
import br.gov.to.sefaz.arr.processamento.validation.validator.header.HeaderValidator;

/**
 * Validação do {@link br.gov.to.sefaz.arr.processamento.validation.validator.header.HeaderValidator},
 * onde verifica se a Versão do Arquivo é "01" e o Tipo de Transmissão é diferente de "1".
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 23/06/2016 10:00:00
 */
public class VersaoArquivoTipoTransmissaoArrecValidator implements HeaderValidator {

    private final String versaoArquivo;
    private final String tipoTransmissao;

    public VersaoArquivoTipoTransmissaoArrecValidator(String versaoArquivo, String tipoTransmissao) {
        this.versaoArquivo = versaoArquivo;
        this.tipoTransmissao = tipoTransmissao;
    }

    @Override
    public boolean isValid() {
        if (versaoArquivo.equals(TipoArquivoEnum.PARCIAL.getName())) {
            return "1".equals(tipoTransmissao);
        }

        return versaoArquivo.equals(TipoArquivoEnum.CONSOLIDADO.getName());
    }

    @Override
    public int getCodigoErro() {
        return 27;
    }
}
