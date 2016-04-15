package br.gov.to.sefaz.arr.persistence.enums;

import javax.persistence.AttributeConverter;

/**
 * Conversor para realizar o mapeamento do {@link SituacaoEnum} com seu
 * respectivo inteiro no banco de dados.
 *
 * @author roger.gouveia@ntconsult.com.br
 */
public class SituacaoEnumConverter implements AttributeConverter<SituacaoEnum, Integer> {
    @Override
    public Integer convertToDatabaseColumn(SituacaoEnum situacaoEnum) {
        return situacaoEnum.getCode();
    }

    @Override
    public SituacaoEnum convertToEntityAttribute(Integer value) {
        // this can still return null unless it throws IllegalArgumentException
        // which would be in line with enums static valueOf method
        return SituacaoEnum.getValue(value);
    }
}
