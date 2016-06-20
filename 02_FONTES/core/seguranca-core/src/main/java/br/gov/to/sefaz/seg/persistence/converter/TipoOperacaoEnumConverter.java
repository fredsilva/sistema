package br.gov.to.sefaz.seg.persistence.converter;

import br.gov.to.sefaz.seg.persistence.enums.TipoOperacaoEnum;

import javax.persistence.AttributeConverter;

/**
 * Conversor para realizar o mapeamento do {@link br.gov.to.sefaz.seg.persistence.enums.TipoOperacaoEnum}
 * com seu respectivo caractere no banco de dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 18/06/2016 11:56:00
 */
public class TipoOperacaoEnumConverter implements AttributeConverter<TipoOperacaoEnum, Character> {

    @Override
    public Character convertToDatabaseColumn(TipoOperacaoEnum tipoOperacaoEnum) {
        return tipoOperacaoEnum.getCode();
    }

    @Override
    public TipoOperacaoEnum convertToEntityAttribute(Character character) {
        return TipoOperacaoEnum.getValue(character);
    }
}
