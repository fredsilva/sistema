package br.gov.to.sefaz.arr.processamento.validation.validator.header;

/**
 * Validador para o HEADER do arquivo que será processado.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 23/06/2016 10:23:00
 */
public interface HeaderValidator {

    /**
     * Verifica se o validador possui alguma violação.
     *
     * @return true se não ocorrer nenhuma violação, false se ocorrer
     */
    boolean isValid();

    /**
     * Codigo do erro, caso ocorra alguma violação, para ser tratada conforme regra de negócio.
     *
     * @return o número que represente o erro conforme a violação da validação
     */
    int getCodigoErro();
}
