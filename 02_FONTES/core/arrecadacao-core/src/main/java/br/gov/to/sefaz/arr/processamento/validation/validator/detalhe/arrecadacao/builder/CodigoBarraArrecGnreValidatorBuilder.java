package br.gov.to.sefaz.arr.processamento.validation.validator.detalhe.arrecadacao.builder;

import br.gov.to.sefaz.arr.dare.service.DareService;
import br.gov.to.sefaz.arr.persistence.entity.ArquivoRecepcao;
import br.gov.to.sefaz.arr.processamento.domain.detalhe.arrecadacao.CodigoBarra;
import br.gov.to.sefaz.arr.processamento.validation.validator.detalhe.CodigoBarraValidatorBuilder;
import br.gov.to.sefaz.arr.processamento.validation.validator.detalhe.DetalheValidator;
import br.gov.to.sefaz.arr.processamento.validation.validator.detalhe.arrecadacao.DataJulianaValidator;
import br.gov.to.sefaz.arr.processamento.validation.validator.detalhe.arrecadacao.NossoNumeroValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder para construir os validadores para
 * {@link br.gov.to.sefaz.arr.processamento.domain.detalhe.arrecadacao.CodigoBarra} em que o tipo do convênio é igual
 * a GNRE.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 13/07/2016 17:35:00
 */
public class CodigoBarraArrecGnreValidatorBuilder implements CodigoBarraValidatorBuilder {

    private final List<DetalheValidator> detalheValidators;
    private final ArquivoRecepcao arquivoRecepcao;
    private final DareService dareService;
    private CodigoBarra codigoBarra;

    public CodigoBarraArrecGnreValidatorBuilder(ArquivoRecepcao arquivoRecepcao, DareService dareService) {
        this.arquivoRecepcao = arquivoRecepcao;
        this.dareService = dareService;
        detalheValidators = new ArrayList<>();
    }

    @Override
    public CodigoBarraValidatorBuilder withCodigoBarra(CodigoBarra codigoBarra) {
        this.codigoBarra = codigoBarra;
        return null;
    }

    @Override
    public CodigoBarraValidatorBuilder withAllValidators() {
        withDataJulianaValidator();
        withNossoNumeroValidator();

        return this;
    }

    @Override
    public List<DetalheValidator> build() {
        return detalheValidators;
    }

    private void withDataJulianaValidator() {
        detalheValidators.add(new DataJulianaValidator(codigoBarra.getDataVencimento(), arquivoRecepcao));
    }

    private void withNossoNumeroValidator() {
        detalheValidators.add(new NossoNumeroValidator(Long.valueOf(codigoBarra.getNossoNumero()), dareService));
    }
}
