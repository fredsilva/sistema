package br.gov.to.sefaz.persistence.converter;

/**
 * Converte os valores numericos 1 e 2 em Boolean, dado que 1 = true e 2 = false.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 06/05/2016 11:16:00
 */
public class OneOrNullBooleanConverter extends DefaultBooleanConverter<Integer> {

    public OneOrNullBooleanConverter() {
        super(1, null, false);
    }
}
