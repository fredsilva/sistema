package br.gov.to.sefaz.arr.persistence.enums;

import javax.persistence.AttributeConverter;

/**
 * Conversor para realizar o mapeamento do {@link TipoContaEnum} com seu
 * respectivo inteiro no banco de dados.
 *
 * @author roger.gouveia@ntconsult.com.br
 */
public class TipoContaEnumConverter implements AttributeConverter<TipoContaEnum, Integer> {
    @Override
    public Integer convertToDatabaseColumn(TipoContaEnum tipoContaEnum) {
        return tipoContaEnum.getCode();
    }

    @Override
    public TipoContaEnum convertToEntityAttribute(Integer value) {
        // this can still return null unless it throws IllegalArgumentException
        // which would be in line with enums static valueOf method
        return TipoContaEnum.getValue(value);
    }
}
