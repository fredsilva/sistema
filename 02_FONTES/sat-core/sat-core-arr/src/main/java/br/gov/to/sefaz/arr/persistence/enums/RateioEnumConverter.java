package br.gov.to.sefaz.arr.persistence.enums;

import javax.persistence.AttributeConverter;

/**
 * Conversor para realizar o mapeamento do {@link RateioEnum} com seu respectivo
 * inteiro no banco de dados.
 *
 * @author roger.gouveia@ntconsult.com.br
 */
public class RateioEnumConverter implements AttributeConverter<RateioEnum, Integer> {
    @Override
    public Integer convertToDatabaseColumn(RateioEnum rateioEnum) {
        return rateioEnum.getCode();
    }

    @Override
    public RateioEnum convertToEntityAttribute(Integer value) {
        // this can still return null unless it throws IllegalArgumentException
        // which would be in line with enums static valueOf method
        return RateioEnum.getValue(value);
    }
}
