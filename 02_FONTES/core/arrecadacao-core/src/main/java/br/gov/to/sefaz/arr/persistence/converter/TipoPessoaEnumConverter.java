package br.gov.to.sefaz.arr.persistence.converter;

import br.gov.to.sefaz.arr.persistence.enums.TipoPessoaEnum;

import java.util.Objects;
import javax.persistence.AttributeConverter;

/**
 * Conversor para realizar o mapeamento do {@link br.gov.to.sefaz.arr.persistence.enums.TipoPessoaEnum}
 * com seu respectivo número no banco de dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 07/07/2016 17:57:00
 */
public class TipoPessoaEnumConverter implements AttributeConverter<TipoPessoaEnum, Integer> {
    @Override
    public Integer convertToDatabaseColumn(TipoPessoaEnum tipoPessoa) {
        return Objects.nonNull(tipoPessoa) ? tipoPessoa.getCode() : null;
    }

    @Override
    public TipoPessoaEnum convertToEntityAttribute(Integer dbData) {
        return TipoPessoaEnum.getValue(dbData);
    }
}
