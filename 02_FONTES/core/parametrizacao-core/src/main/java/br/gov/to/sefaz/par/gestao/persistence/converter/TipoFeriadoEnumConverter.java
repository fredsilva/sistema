package br.gov.to.sefaz.par.gestao.persistence.converter;

import br.gov.to.sefaz.par.gestao.persistence.enums.TipoFeriadoEnum;

import javax.persistence.AttributeConverter;

/**
 * Conversor para realizar o mapeamento do {@link br.gov.to.sefaz.par.gestao.persistence.enums.TipoFeriadoEnum} com
 * seu respectivo caracter no banco de dados.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 21/09/2016 16:33:00
 */

public class TipoFeriadoEnumConverter implements AttributeConverter<TipoFeriadoEnum, Character> {

    @Override
    public Character convertToDatabaseColumn(TipoFeriadoEnum tipoFeriadoEnum) {
        return tipoFeriadoEnum.getCode();
    }

    @Override
    public TipoFeriadoEnum convertToEntityAttribute(Character value) {
        return TipoFeriadoEnum.getValue(value);
    }
}
