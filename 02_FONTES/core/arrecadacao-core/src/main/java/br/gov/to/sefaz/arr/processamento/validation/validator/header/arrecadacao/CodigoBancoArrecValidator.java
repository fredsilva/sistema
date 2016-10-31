package br.gov.to.sefaz.arr.processamento.validation.validator.header.arrecadacao;

import br.gov.to.sefaz.arr.parametros.business.service.BancosService;
import br.gov.to.sefaz.arr.processamento.validation.validator.header.HeaderValidator;

import java.util.Objects;

/**
 * Validação do {@link br.gov.to.sefaz.arr.processamento.validation.validator.header.HeaderValidator},
 * onde verifica se o Código do Banco não está cadastrado como banco arrecadador.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 23/06/2016 10:00:00
 */
public class CodigoBancoArrecValidator implements HeaderValidator {

    private final Integer codigoBanco;
    private final BancosService bancosService;

    public CodigoBancoArrecValidator(Integer codigoBanco, BancosService bancosService) {
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
