package br.gov.to.sefaz.arr.persistence.converter;

import br.gov.to.sefaz.arr.persistence.enums.TipoLotePagosEnum;

import javax.persistence.AttributeConverter;

/**
 * Conversor para realizar o mapeamento do {@link br.gov.to.sefaz.arr.persistence.enums.TipoLotePagosEnum}
 * com seu respectivo número no banco de dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 07/07/2016 16:18:00
 */
public class TipoLotePagosArrecEnumConverter implements AttributeConverter<TipoLotePagosEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TipoLotePagosEnum tipoLotePagos) {
        return tipoLotePagos.getCode();
    }

    @Override
    public TipoLotePagosEnum convertToEntityAttribute(Integer code) {
        return TipoLotePagosEnum.getValue(code);
    }
}
