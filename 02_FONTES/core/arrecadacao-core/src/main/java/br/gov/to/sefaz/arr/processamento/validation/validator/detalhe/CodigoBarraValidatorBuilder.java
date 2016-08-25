package br.gov.to.sefaz.arr.processamento.validation.validator.detalhe;

import br.gov.to.sefaz.arr.processamento.domain.detalhe.arrecadacao.CodigoBarra;

import java.util.List;

/**
 * Builder para validações {@link br.gov.to.sefaz.arr.processamento.validation.validator.detalhe.DetalheValidator},
 * referentes a um tipo de arquivo a ser processado.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 13/07/2016 17:18:00
 */
public interface CodigoBarraValidatorBuilder {

    /**
     * Define o Detalhe do arquivo para a construção dos
     * {@link br.gov.to.sefaz.arr.processamento.validation.validator.detalhe.DetalheValidator}.
     *
     * @param codigoBarra código de barras do detalhe do arquivo
     * @return o próprio builder para dar sequência a sua construção
     */
    CodigoBarraValidatorBuilder withCodigoBarra(CodigoBarra codigoBarra);

    /**
     * Neste método deve conter todos os
     * {@link br.gov.to.sefaz.arr.processamento.validation.validator.detalhe.DetalheValidator}
     * referentes ao arquivo que será processado.
     *
     * @return o próprio builder para dar sequência a sua construção
     */
    CodigoBarraValidatorBuilder withAllValidators();

    /**
     * Constroi os {@link br.gov.to.sefaz.arr.processamento.validation.validator.detalhe.DetalheValidator}s
     * que foram estabelecidos através do método {@link #withAllValidators()}.
     *
     * @return uma lista com todos os validadores de Detalhe de um arquivo a ser processado
     */
    List<DetalheValidator> build();
}
