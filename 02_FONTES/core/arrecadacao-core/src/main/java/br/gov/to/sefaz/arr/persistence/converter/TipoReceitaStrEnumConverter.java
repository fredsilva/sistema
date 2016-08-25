package br.gov.to.sefaz.arr.persistence.converter;

import br.gov.to.sefaz.arr.persistence.enums.TipoReceitaStrEnum;

import javax.persistence.AttributeConverter;

/**
 * Conversor para realizar o mapeamento do {@link br.gov.to.sefaz.arr.persistence.enums.TipoReceitaStrEnum}
 * com seu respectivo número no banco de dados.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 12/07/2016 10:33:00
 */
public class TipoReceitaStrEnumConverter implements AttributeConverter<TipoReceitaStrEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TipoReceitaStrEnum tipoReceitaStr) {
        return tipoReceitaStr != null ? tipoReceitaStr.getCode() : null;
    }

    @Override
    public TipoReceitaStrEnum convertToEntityAttribute(Integer id) {
        return id != null ? TipoReceitaStrEnum.getValue(id) : null;
    }
}
