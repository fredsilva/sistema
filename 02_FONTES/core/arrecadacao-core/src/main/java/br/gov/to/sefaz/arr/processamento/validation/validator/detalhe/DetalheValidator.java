package br.gov.to.sefaz.arr.processamento.validation.validator.detalhe;

/**
 * Validador para o Detalhe do arquivo que será processado.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 13/07/2016 17:14:00
 */
public interface DetalheValidator {

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
