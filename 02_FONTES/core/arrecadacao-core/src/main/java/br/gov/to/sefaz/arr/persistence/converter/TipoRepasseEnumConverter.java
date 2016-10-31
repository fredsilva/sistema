package br.gov.to.sefaz.arr.persistence.converter;

import br.gov.to.sefaz.arr.persistence.enums.TipoRepasseEnum;

import javax.persistence.AttributeConverter;

/**
 * Conversor para realizar o mapeamento do
 * {@link br.gov.to.sefaz.arr.persistence.enums.TipoRepasseEnum}
 * com seu respectivo número no banco de dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 21/05/2016 16:17:00
 */
public class TipoRepasseEnumConverter implements AttributeConverter<TipoRepasseEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TipoRepasseEnum tipoRepasse) {
        return tipoRepasse.getCode();
    }

    @Override
    public TipoRepasseEnum convertToEntityAttribute(Integer id) {
        return TipoRepasseEnum.getValue(id);
    }
}
