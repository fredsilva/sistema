package br.gov.to.sefaz.arr.parametros.persistence.converter;

import br.gov.to.sefaz.arr.parametros.persistence.enums.TipoPedidoAcoesEnum;

import javax.persistence.AttributeConverter;

/**
 * Conversor para realizar o mapeamento do {@link br.gov.to.sefaz.arr.parametros.persistence.enums.TipoPedidoAcoesEnum}
 * com seu respectivo número no banco de dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 25/05/2016 09:56:00
 */
public class TipoPedidoAcoesEnumConverter implements AttributeConverter<TipoPedidoAcoesEnum, Integer> {
    @Override
    public Integer convertToDatabaseColumn(TipoPedidoAcoesEnum tipoPedidoAcoes) {
        return tipoPedidoAcoes.getCode();
    }

    @Override
    public TipoPedidoAcoesEnum convertToEntityAttribute(Integer code) {
        return TipoPedidoAcoesEnum.getValue(code);
    }
}
