package br.gov.to.sefaz.persistence.converter;

/**
 * Conversor para realizar o mapeamento de {@link Boolean} com seu respectivo caractere no banco de dados. Sendo que 'S'
 * = true e 'N' = false
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 14/05/2016 16:34:54
 */
public class YesOrNoBooleanConverter extends DefaultBooleanConverter<Character> {

    public YesOrNoBooleanConverter() {
        super('S', 'N', false);
    }
}
