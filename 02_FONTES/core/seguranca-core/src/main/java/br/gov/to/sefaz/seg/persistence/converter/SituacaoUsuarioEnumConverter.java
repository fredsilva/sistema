package br.gov.to.sefaz.seg.persistence.converter;

import br.gov.to.sefaz.seg.persistence.enums.SituacaoUsuarioEnum;

import javax.persistence.AttributeConverter;

/**
 * Conversor para realizar o mapeamento do {@link br.gov.to.sefaz.seg.persistence.enums.SituacaoUsuarioEnum} com seu
 * respectivo caractere no banco de dados.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 06/06/2016 18:11:52
 */
public class SituacaoUsuarioEnumConverter implements AttributeConverter<SituacaoUsuarioEnum, Character> {

    @Override
    public Character convertToDatabaseColumn(SituacaoUsuarioEnum situacaoUsuarioEnum) {
        return situacaoUsuarioEnum.getCode();
    }

    @Override
    public SituacaoUsuarioEnum convertToEntityAttribute(Character code) {
        return SituacaoUsuarioEnum.getValue(code);
    }
}
