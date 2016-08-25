package br.gov.to.sefaz.arr.persistence.converter;

import br.gov.to.sefaz.arr.persistence.enums.TipoRecolhimentoStrEnum;

import javax.persistence.AttributeConverter;

/**
 * Conversor para realizar o mapeamento do
 * {@link br.gov.to.sefaz.arr.persistence.enums.TipoRecolhimentoStrEnum}
 * com seu respectivo número no banco de dados.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 12/07/2016 10:36:00
 */
public class TipoRecolhimentoStrEnumConverter implements AttributeConverter<TipoRecolhimentoStrEnum, String> {

    @Override
    public String convertToDatabaseColumn(TipoRecolhimentoStrEnum tipoRecolhimentoStr) {
        return tipoRecolhimentoStr != null ? tipoRecolhimentoStr.getCode() : null;
    }

    @Override
    public TipoRecolhimentoStrEnum convertToEntityAttribute(String id) {
        return id != null ? TipoRecolhimentoStrEnum.getValue(id) : null;
    }
}
