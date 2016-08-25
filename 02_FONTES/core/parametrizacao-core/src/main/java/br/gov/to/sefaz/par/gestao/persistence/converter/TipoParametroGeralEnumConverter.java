package br.gov.to.sefaz.par.gestao.persistence.converter;

import br.gov.to.sefaz.par.gestao.persistence.enums.TipoParametroGeralEnum;

import javax.persistence.AttributeConverter;

/**
 * Conversor para realizar o mapeamento
 * do {@link br.gov.to.sefaz.persistence.enums.SituacaoEnum} com seu respectivo inteiro no banco de dados.
 *
 * @author <a href="mailto:roger.golveia@ntconsult.com.br">roger.golveia</a>
 * @since 19/04/2016 10:51:00
 */

public class TipoParametroGeralEnumConverter implements AttributeConverter<TipoParametroGeralEnum, Character> {

    @Override
    public Character convertToDatabaseColumn(TipoParametroGeralEnum situacaoEnum) {
        return situacaoEnum.getCode();
    }

    @Override
    public TipoParametroGeralEnum convertToEntityAttribute(Character value) {
        return TipoParametroGeralEnum.getValue(value);
    }
}
