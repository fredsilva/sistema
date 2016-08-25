package br.gov.to.sefaz.arr.processamento.validation.validator.header;

import java.util.List;

/**
 * Builder para validações {@link HeaderValidator},
 * referentes a um tipo de arquivo a ser processado.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 23/06/2016 14:11:00
 */
public interface HeaderValidatorBuilder {
    /**
     * Define o HEADER para a construção das {@link HeaderValidator}.
     *
     * @param header do arquivo a ser processado
     * @return o próprio builder para dar sequência a sua construção
     */
    HeaderValidatorBuilder withLineHeader(String header);

    /**
     * Neste método deve conter todos os {@link HeaderValidator}
     * referentes ao arquivo que será processado.
     *
     * @return o próprio builder para dar sequência a sua construção
     */
    HeaderValidatorBuilder withAllValidators();

    /**
     * Constroi os {@link HeaderValidator}s que foram estabelecidos
     * através do método {@link #withAllValidators()}.
     *
     * @return uma lista com todos os validadores de HEADER de um arquivo a ser processado
     */
    List<HeaderValidator> build();
}
