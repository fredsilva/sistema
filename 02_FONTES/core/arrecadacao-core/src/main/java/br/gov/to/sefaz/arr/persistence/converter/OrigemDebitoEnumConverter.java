package br.gov.to.sefaz.arr.persistence.converter;

import br.gov.to.sefaz.arr.persistence.enums.OrigemDebitoEnum;

import javax.persistence.AttributeConverter;

/**
 * Conversor para realizar o mapeamento da {@link br.gov.to.sefaz.arr.persistence.enums.OrigemDebitoEnum}
 * com seu respectivo número no banco de dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 01/09/2016 15:57:00
 */
public class OrigemDebitoEnumConverter implements AttributeConverter<OrigemDebitoEnum, Integer> {
    @Override
    public Integer convertToDatabaseColumn(OrigemDebitoEnum origemDebitoEnum) {
        return origemDebitoEnum.getCode();
    }

    @Override
    public OrigemDebitoEnum convertToEntityAttribute(Integer id) {
        return OrigemDebitoEnum.getValue(id);
    }
}
