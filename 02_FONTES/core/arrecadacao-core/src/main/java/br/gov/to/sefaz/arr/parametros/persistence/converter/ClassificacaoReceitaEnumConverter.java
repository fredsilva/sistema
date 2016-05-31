package br.gov.to.sefaz.arr.parametros.persistence.converter;

import br.gov.to.sefaz.arr.parametros.persistence.enums.ClassificacaoReceitaEnum;

import javax.persistence.AttributeConverter;

/**
 * * Conversor para realizar o mapeamento do {@link br.gov.to.sefaz.arr.parametros.persistence.enums.TipoReceitaEnum}
 * com seu respectivo número no banco de dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 19/05/2016 11:53:00
 */
public class ClassificacaoReceitaEnumConverter implements AttributeConverter<ClassificacaoReceitaEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ClassificacaoReceitaEnum classificacaoReceitaEnum) {
        return classificacaoReceitaEnum.getCode();
    }

    @Override
    public ClassificacaoReceitaEnum convertToEntityAttribute(Integer id) {
        return ClassificacaoReceitaEnum.getValue(id);
    }
}
