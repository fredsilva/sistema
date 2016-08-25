package br.gov.to.sefaz.arr.persistence.converter;

import br.gov.to.sefaz.arr.persistence.enums.TipoPedidoCampoEnum;

import javax.persistence.AttributeConverter;

/**
 * Conversor para realizar o mapeamento do {@link br.gov.to.sefaz.arr.persistence.enums.TipoPedidoCampoEnum}
 * com seu respectivo número no banco de dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 25/05/2016 14:27:00
 */
public class TipoPedidoCampoEnumConverter implements AttributeConverter<TipoPedidoCampoEnum, Integer> {
    @Override
    public Integer convertToDatabaseColumn(TipoPedidoCampoEnum tipoPedidoCampo) {
        return tipoPedidoCampo.getCode();
    }

    @Override
    public TipoPedidoCampoEnum convertToEntityAttribute(Integer code) {
        return TipoPedidoCampoEnum.getValue(code);
    }
}
