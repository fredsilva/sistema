package br.gov.to.sefaz.persistence.converter;

import br.gov.to.sefaz.persistence.enums.SituacaoEnum;

import javax.persistence.AttributeConverter;

/**
 * Conversor para realizar o mapeamento do {@link SituacaoEnum} com seu respectivo inteiro no banco de dados.
 *
 * @author <a href="mailto:roger.golveia@ntconsult.com.br">roger.golveia</a>
 * @since 19/04/2016 10:51:00
 */

public class SituacaoEnumConverter implements AttributeConverter<SituacaoEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(SituacaoEnum situacaoEnum) {
        return situacaoEnum.getCode();
    }

    @Override
    public SituacaoEnum convertToEntityAttribute(Integer value) {
        return SituacaoEnum.getValue(value);
    }
}
