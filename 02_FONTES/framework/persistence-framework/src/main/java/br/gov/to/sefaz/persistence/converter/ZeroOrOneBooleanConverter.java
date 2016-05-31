package br.gov.to.sefaz.persistence.converter;

/**
 * Converte os valores numericos 0 e 1 em Boolean, dado que 1 = true e 0 = false.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 19/05/2016 15:43:00
 */
public class ZeroOrOneBooleanConverter extends DefaultBooleanConverter<Integer> {

    public ZeroOrOneBooleanConverter() {
        super(1, 0, false);
    }
}
