package br.gov.to.sefaz.persistence.converter;

import br.gov.to.sefaz.persistence.enums.YesNoEnum;

import javax.persistence.AttributeConverter;

/**
 * Conversor para realizar o mapeamento de {@link Boolean} com seu respectivo caractere no banco de dados.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 05/05/2016 16:02:32
 */
public class BooleanConverter implements AttributeConverter<Boolean, Character> {

    @Override
    public Character convertToDatabaseColumn(Boolean value) {
        return YesNoEnum.getValue(value).getCode();
    }

    @Override
    public Boolean convertToEntityAttribute(Character value) {
        return YesNoEnum.getValue(value).getBool();
    }
}
