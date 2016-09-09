package br.gov.to.sefaz.seg.persistence.converter;

import br.gov.to.sefaz.seg.persistence.enums.TipoComunicacaoEnum;

import javax.persistence.AttributeConverter;

/**
 * Conversor para realizar o mapeamento do {@link br.gov.to.sefaz.seg.persistence.enums.TipoComunicacaoEnum}
 * com seu respectivo caractere no banco de dados.
 *
 * @author <a href="mailto:volceri.davila@ntconsult.com.br">volceri.davila</a>
 * @since 22/08/2016 14:54:00
 */
public class TipoComunicacaoEnumConverter implements AttributeConverter<TipoComunicacaoEnum, String> {

    @Override
    public String convertToDatabaseColumn(TipoComunicacaoEnum tipoComunicacaoEnum) {
        return tipoComunicacaoEnum.getCode();
    }

    @Override
    public TipoComunicacaoEnum convertToEntityAttribute(String value) {
        return TipoComunicacaoEnum.getValue(value);
    }
}
