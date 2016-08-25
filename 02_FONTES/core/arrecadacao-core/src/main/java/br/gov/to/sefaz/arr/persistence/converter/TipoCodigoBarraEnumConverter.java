package br.gov.to.sefaz.arr.persistence.converter;

import br.gov.to.sefaz.arr.persistence.enums.TipoCodigoBarraEnum;

import javax.persistence.AttributeConverter;

/**
 * Conversor para realizar o mapeamento do {@link br.gov.to.sefaz.arr.persistence.enums.TipoCodigoBarraEnum}
 * com seu respectivo número no banco de dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 05/05/2016 17:04:41
 */
public class TipoCodigoBarraEnumConverter implements AttributeConverter<TipoCodigoBarraEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TipoCodigoBarraEnum tipoCodigoBarra) {
        return tipoCodigoBarra.getCode();
    }

    @Override
    public TipoCodigoBarraEnum convertToEntityAttribute(Integer value) {
        return TipoCodigoBarraEnum.getValue(value);
    }

}
