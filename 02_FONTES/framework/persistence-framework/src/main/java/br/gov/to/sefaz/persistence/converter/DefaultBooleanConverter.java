package br.gov.to.sefaz.persistence.converter;

import java.util.Objects;

import javax.persistence.AttributeConverter;

/**
 * Conversor para realizar o mapeamento do {@link Boolean} com seu respectivo inteiro no banco de dados.
 *
 * @param <T> Tipo do objeto que representara os estados do boolean
 *
 * @author <a href="mailto:roger.golveia@ntconsult.com.br">roger.golveia</a>
 * @since 19/04/2016 10:51:00
 */
public class DefaultBooleanConverter<T> implements AttributeConverter<Boolean, T> {

    private final T whenTrue;
    private final T whenFalse;
    private final boolean otherwise;

    public DefaultBooleanConverter(
            T whenTrue, T whenFalse, Boolean otherwise) {
        this.whenTrue = whenTrue;
        this.whenFalse = whenFalse;
        this.otherwise = otherwise;
    }

    @Override
    public T convertToDatabaseColumn(Boolean attribute) {
        if (Objects.isNull(attribute)) {
            if (otherwise) {
                return whenTrue;
            }

            return whenFalse;
        } else if (attribute) {
            return whenTrue;
        }

        return whenFalse;
    }

    @Override
    public Boolean convertToEntityAttribute(T dbData) {

        if (Objects.isNull(dbData)) {
            return Boolean.FALSE;
        } else if (whenTrue.equals(dbData)) {
            return Boolean.TRUE;
        } else if (whenFalse.equals(dbData)) {
            return Boolean.FALSE;
        }

        return otherwise;
    }
}
