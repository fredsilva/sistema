package br.gov.to.sefaz.seg.persistence.converter;

import br.gov.to.sefaz.seg.persistence.enums.SituacaoSolicitacaoEnum;
import br.gov.to.sefaz.seg.persistence.enums.SituacaoUsuarioEnum;

import javax.persistence.AttributeConverter;

/**
 * Conversor para realizar o mapeamento do {@link SituacaoUsuarioEnum} com seu respectivo caractere no banco de dados.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 06/06/2016 18:11:52
 */
public class SituacaoSolicitacaoEnumConverter implements AttributeConverter<SituacaoSolicitacaoEnum, Character> {

    @Override
    public Character convertToDatabaseColumn(SituacaoSolicitacaoEnum situacaoSolicitacaoEnum) {
        return situacaoSolicitacaoEnum.getCode();
    }

    @Override
    public SituacaoSolicitacaoEnum convertToEntityAttribute(Character code) {
        return SituacaoSolicitacaoEnum.getValue(code);
    }
}
