package br.gov.to.sefaz.arr.parametros.persistence.converter;

import br.gov.to.sefaz.arr.parametros.persistence.enums.TipoConvenioEnum;

import javax.persistence.AttributeConverter;

/**
 * Conversor para realizar o mapeamento do {@link TipoConvenioEnum} com seu respectivo número no banco de dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 05/05/2016 17:21:39
 */
public class TipoConvenioEnumConverter implements AttributeConverter<TipoConvenioEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TipoConvenioEnum tipoConvenioEnum) {
        return tipoConvenioEnum.getCode();
    }

    @Override
    public TipoConvenioEnum convertToEntityAttribute(Integer value) {
        return TipoConvenioEnum.getValue(value);
    }

}
