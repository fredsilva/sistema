package br.gov.to.sefaz.arr.persistence.converter;

import br.gov.to.sefaz.arr.persistence.enums.SituacaoConciliacaoEnum;

import javax.persistence.AttributeConverter;

/**
 * Conversor da enum {@link br.gov.to.sefaz.arr.persistence.enums.SituacaoConciliacaoEnum}.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 13/07/2016 15:03:00
 */
public class SituacaoConciliacaoConverter implements AttributeConverter<SituacaoConciliacaoEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(SituacaoConciliacaoEnum situacaoConciliacaoEnum) {
        return situacaoConciliacaoEnum != null ? situacaoConciliacaoEnum.getCode() : null;
    }

    @Override
    public SituacaoConciliacaoEnum convertToEntityAttribute(Integer id) {
        return id != null ? SituacaoConciliacaoEnum.getValue(id) : null;
    }
}
