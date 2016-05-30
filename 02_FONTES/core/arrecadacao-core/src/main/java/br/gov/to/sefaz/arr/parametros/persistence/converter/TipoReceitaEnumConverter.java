package br.gov.to.sefaz.arr.parametros.persistence.converter;

import br.gov.to.sefaz.arr.parametros.persistence.enums.TipoReceitaEnum;

import javax.persistence.AttributeConverter;

/**
 * Conversor para realizar o mapeamento do
 * {@link br.gov.to.sefaz.arr.parametros.persistence.enums.ClassificacaoReceitaEnum}
 * com seu respectivo número no banco de dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 19/05/2016 11:42:00
 */
public class TipoReceitaEnumConverter implements AttributeConverter<TipoReceitaEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TipoReceitaEnum tipoReceitaEnum) {
        return tipoReceitaEnum.getCode();
    }

    @Override
    public TipoReceitaEnum convertToEntityAttribute(Integer id) {
        return TipoReceitaEnum.getValue(id);
    }
}
