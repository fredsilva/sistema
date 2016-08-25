package br.gov.to.sefaz.arr.persistence.converter;

import br.gov.to.sefaz.arr.processamento.domain.str.SituacaoProcessamentoStrEnum;

import javax.persistence.AttributeConverter;

/**
 * Conversor para realizar o mapeamento do
 * {@link br.gov.to.sefaz.arr.processamento.domain.str.SituacaoProcessamentoStrEnum}
 * com seu respectivo número no banco de dados.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 12/07/2016 10:40:00
 */
public class SituacaoProcessamentoStrEnumConverter implements
        AttributeConverter<SituacaoProcessamentoStrEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(SituacaoProcessamentoStrEnum situacaoProcessamentoStrEnum) {
        return situacaoProcessamentoStrEnum != null ? situacaoProcessamentoStrEnum.getCode() : null;
    }

    @Override
    public SituacaoProcessamentoStrEnum convertToEntityAttribute(Integer id) {
        return id != null ? SituacaoProcessamentoStrEnum.getValue(id) : null;
    }
}
