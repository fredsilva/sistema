package br.gov.to.sefaz.arr.persistence.converter;

import br.gov.to.sefaz.arr.persistence.enums.FormaPagamentoEnum;

import java.util.Objects;
import javax.persistence.AttributeConverter;

/**
 * Conversor para realizar o mapeamento do {@link FormaPagamentoEnum} com seu respectivo número no banco de dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 05/05/2016 17:35:22
 */
@SuppressWarnings("PMD")
public class FormaPagamentoEnumConverter implements AttributeConverter<FormaPagamentoEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(FormaPagamentoEnum formaPagamentoEnum) {
        return Objects.isNull(formaPagamentoEnum) ? null : formaPagamentoEnum.getCode();
    }

    @Override
    public FormaPagamentoEnum convertToEntityAttribute(Integer value) {
        try {
            return FormaPagamentoEnum.getValue(value);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

}
