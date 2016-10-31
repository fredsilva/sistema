package br.gov.to.sefaz.arr.processamento.validation.validator.header.arrecadacao;

import br.gov.to.sefaz.arr.parametros.business.service.ConveniosArrecService;
import br.gov.to.sefaz.arr.processamento.domain.str.TipoRejeicaoEnum;
import br.gov.to.sefaz.arr.processamento.validation.validator.header.HeaderValidator;

import java.util.Objects;

/**
 * Validação do {@link br.gov.to.sefaz.arr.processamento.validation.validator.header.HeaderValidator},
 * onde verifica se o Código do Convênio não encontra-se cadastrado para o Banco Informado.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 23/06/2016 10:00:00
 */
public class CodigoConvenioArrecValidator implements HeaderValidator {

    private final Long codigoConvenio;
    private final ConveniosArrecService conveniosArrecService;

    public CodigoConvenioArrecValidator(Long codigoConvenio, ConveniosArrecService conveniosArrecService) {
        this.codigoConvenio = codigoConvenio;
        this.conveniosArrecService = conveniosArrecService;
    }

    @Override
    public boolean isValid() {
        return !Objects.isNull(conveniosArrecService.findOne(codigoConvenio));
    }

    @Override
    public int getCodigoErro() {
        return TipoRejeicaoEnum.COD_CONV_BANC_N_LOCALIZADO.getCode();
    }
}
