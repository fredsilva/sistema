package br.gov.to.sefaz.arr.persistence.converter;

import br.gov.to.sefaz.arr.persistence.enums.EstadoLoteEnum;

import javax.persistence.AttributeConverter;

/**
 * Conversor para realizar o mapeamento da {@link br.gov.to.sefaz.arr.persistence.enums.EstadoLoteEnum}
 * com seu respectivo número no banco de dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 07/07/2016 16:14:00
 */
public class EstadoLoteEnumConverter implements AttributeConverter<EstadoLoteEnum, Integer> {
    @Override
    public Integer convertToDatabaseColumn(EstadoLoteEnum estadoLote) {
        return estadoLote.getCode();
    }

    @Override
    public EstadoLoteEnum convertToEntityAttribute(Integer code) {
        return EstadoLoteEnum.getValue(code);
    }
}
