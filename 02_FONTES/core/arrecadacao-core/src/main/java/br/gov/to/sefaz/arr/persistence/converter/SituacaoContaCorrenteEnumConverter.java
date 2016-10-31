package br.gov.to.sefaz.arr.persistence.converter;

import br.gov.to.sefaz.arr.dare.enums.SituacaoContaCorrenteEnum;

import java.util.Objects;
import javax.persistence.AttributeConverter;

/**
 * Conversor de {@link br.gov.to.sefaz.arr.dare.enums.SituacaoContaCorrenteEnum}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 11/10/2016 14:27:00
 */
public class SituacaoContaCorrenteEnumConverter implements AttributeConverter<SituacaoContaCorrenteEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(SituacaoContaCorrenteEnum situacaoContaCorrente) {
        return Objects.nonNull(situacaoContaCorrente) ? situacaoContaCorrente.getCode() : null;
    }

    @Override
    public SituacaoContaCorrenteEnum convertToEntityAttribute(Integer code) {
        return Objects.nonNull(code) ? SituacaoContaCorrenteEnum.getValue(code) : null;
    }
}
