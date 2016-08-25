package br.gov.to.sefaz.arr.processamento.validation.validator.header.simplesnacional;

import br.gov.to.sefaz.arr.parametros.business.service.BancosService;
import br.gov.to.sefaz.arr.processamento.validation.validator.header.HeaderValidator;

import java.util.Objects;

/**
 * Validação do {@link br.gov.to.sefaz.arr.processamento.validation.validator.header.HeaderValidator},
 * onde verifica se o Código do Banco não está cadastrado como banco arrecadador.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 24/06/2016 15:37:00
 */
public class CodigoBancoSNValidator implements HeaderValidator {

    private final Integer codigoBanco;
    private final BancosService bancosService;

    public CodigoBancoSNValidator(Integer codigoBanco, BancosService bancosService) {
        this.codigoBanco = codigoBanco;
        this.bancosService = bancosService;
    }

    @Override
    public boolean isValid() {
        return !Objects.isNull(bancosService.findOne(codigoBanco));
    }

    @Override
    public int getCodigoErro() {
        return 34;
    }
}
