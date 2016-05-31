package br.gov.to.sefaz.arr.parametros.persistence.converter;

import br.gov.to.sefaz.arr.parametros.persistence.enums.TipoContaEnum;

import javax.persistence.AttributeConverter;

/**
 * Conversor para realizar o mapeamento do {@link TipoContaEnum} com seu
 * respectivo inteiro no banco de dados.
 *
 * @author <a href="mailto:roger.golveia@ntconsult.com.br">roger.golveia</a>
 * @since 19/04/2016 10:51:00
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
