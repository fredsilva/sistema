package br.gov.to.sefaz.arr.parametros.persistence.converter;

import br.gov.to.sefaz.arr.parametros.persistence.enums.FormaPagamentoEnum;

import javax.persistence.AttributeConverter;

/**
 * Conversor para realizar o mapeamento do {@link FormaPagamentoEnum} com seu respectivo número no banco de dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 05/05/2016 17:35:22
 */
public class FormaPagamentoEnumConverter implements AttributeConverter<FormaPagamentoEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(FormaPagamentoEnum formaPagamentoEnum) {
        return formaPagamentoEnum.getCode();
    }

    @Override
    public FormaPagamentoEnum convertToEntityAttribute(Integer value) {
        return FormaPagamentoEnum.getValue(value);
    }

}
