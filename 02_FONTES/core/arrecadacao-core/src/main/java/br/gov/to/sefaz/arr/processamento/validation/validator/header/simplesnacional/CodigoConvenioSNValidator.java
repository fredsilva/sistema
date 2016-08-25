package br.gov.to.sefaz.arr.processamento.validation.validator.header.simplesnacional;

import br.gov.to.sefaz.arr.parametros.business.service.ConveniosArrecService;
import br.gov.to.sefaz.arr.persistence.enums.TipoConvenioEnum;
import br.gov.to.sefaz.arr.processamento.validation.validator.header.HeaderValidator;

import java.util.Objects;

/**
 * Validação do {@link br.gov.to.sefaz.arr.processamento.validation.validator.header.HeaderValidator},
 * onde verifica se o Código do Convênio é igual a "DAF607SIMPLESNACION".
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 24/06/2016 15:37:00
 */
public class CodigoConvenioSNValidator implements HeaderValidator {

    private final String codigoConvenio;
    private final Integer bancoId;
    private final ConveniosArrecService conveniosArrecService;

    public CodigoConvenioSNValidator(String codigoConvenio, Integer bancoId,
            ConveniosArrecService conveniosArrecService) {
        this.codigoConvenio = codigoConvenio;
        this.bancoId = bancoId;
        this.conveniosArrecService = conveniosArrecService;
    }

    @Override
    public boolean isValid() {
        return "DAF607 SIMPLESNACION".equals(codigoConvenio)
                && !Objects.isNull(conveniosArrecService.findByBancoAndTipoConvenio(bancoId,
                TipoConvenioEnum.SIMPLES_NACIONAL));
    }

    @Override
    public int getCodigoErro() {
        return 34;
    }
}
