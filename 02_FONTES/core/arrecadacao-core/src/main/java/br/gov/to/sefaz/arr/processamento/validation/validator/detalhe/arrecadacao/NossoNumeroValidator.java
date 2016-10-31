package br.gov.to.sefaz.arr.processamento.validation.validator.detalhe.arrecadacao;

import br.gov.to.sefaz.arr.dare.service.DareService;
import br.gov.to.sefaz.arr.processamento.validation.validator.detalhe.DetalheValidator;

import java.util.Objects;

/**
 * Validador para identificar se o nosso número fornecido está presente na entidade
 * {@link br.gov.to.sefaz.arr.persistence.entity.Dare}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 13/07/2016 17:14:00
 */
public class NossoNumeroValidator implements DetalheValidator {

    private final Long nossoNumero;
    private final DareService dareService;

    public NossoNumeroValidator(Long nossoNumero, DareService dareService) {
        this.nossoNumero = nossoNumero;
        this.dareService = dareService;
    }

    @Override
    public boolean isValid() {
        return !Objects.isNull(dareService.findOne(nossoNumero));
    }

    @Override
    public int getCodigoErro() {
        return 2;
    }
}
