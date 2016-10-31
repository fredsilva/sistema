package br.gov.to.sefaz.util.reflection;

import org.apache.commons.lang3.StringUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Classe utilitaria para acessos via reflection a objetos.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 22/08/2016 13:53:00
 */
public class ReflectionUtils {

    private static final String ALIAS_SEPPARATOR = ".";

    /**
     * Converte um objeto para um mapa onde a chave será os metodo de getters, sem seus respectivos prefixos
     * (get, is ou has). Se o objeto já for um mapa será retornada uma copia dele, onde a chave será o
     * {@link Object#toString()} das chaves do mapa original.
     *
     * @see PropertyDescriptor#PropertyDescriptor(java.lang.String, java.lang.Class)
     * @param obj objeto a ser convertido em mapa
     * @return mapa de getters do objeto
     */
    public static Map<String, Object> objToMap(Object obj) {
        return objToMap(obj, StringUtils.EMPTY);
    }

    /**
     * Converte um objeto para um mapa onde a chave será os metodo de getters, sem seus respectivos prefixos
     * (get, is ou has), porém prefixados com o alias passado por parametro e um "{@value ALIAS_SEPPARATOR}".
     * Se o objeto já for um mapa será retornada uma copia dele, onde a chave será o
     * {@link Object#toString()} das chaves do mapa original, prefixados pelo alias informado.
     *
     * @see PropertyDescriptor#PropertyDescriptor(java.lang.String, java.lang.Class)
     * @param obj objeto a ser convertido em mapa
     * @return mapa de getters do objeto
     */
    public static Map<String, Object> objToMap(Object obj, String alias) {
        String prefix = (alias.isEmpty() ? StringUtils.EMPTY : alias + ALIAS_SEPPARATOR);

        if (obj instanceof Map) {
            return ((Map<?, ?>) obj).entrySet().stream()
                    .collect(Collectors.toMap(
                        e -> prefix + e.getKey().toString(),
                        Map.Entry::getValue));
        }

        try {
            Map<String, Object> result = new HashMap<>();
            BeanInfo info = Introspector.getBeanInfo(obj.getClass());

            for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
                Method reader = pd.getReadMethod();
                if (reader != null) {
                    result.put(prefix + pd.getName(), reader.invoke(obj));
                }
            }

            return result;
        } catch (IllegalAccessException | InvocationTargetException | IntrospectionException e) {
            throw new ReflectionAccessException("Não foi possível gerar um mapa com os atributos do objeto " + obj, e);
        }
    }

    /**
     * Invoca um método getter de um objeto e retorna o valor da chamada.
     *
     * @see PropertyDescriptor#PropertyDescriptor(java.lang.String, java.lang.Class)
     * @param obj objeto ao qual o getter será chamado
     * @param field nome do getter sem o prefixo get, is ou has
     * @param <R> tipo do retorno da chamada
     * @return retorno da chamada
     */
    @SuppressWarnings("unchecked") // o tipo de retorno fica sobre responsabilidade de quem chamar o método
    public static <R> R invokeGetter(Object obj, String field) {
        try {
            PropertyDescriptor propertyDescriptor = Arrays.stream(Introspector.getBeanInfo(obj.getClass())
                    .getPropertyDescriptors())
                    .filter(pd -> pd.getName().equals(field))
                    .findFirst()
                    .get();
            return (R) propertyDescriptor.getReadMethod().invoke(obj);
        } catch (IntrospectionException | IllegalAccessException | InvocationTargetException
                | NoSuchElementException e) {
            throw new ReflectionAccessException("Não foi possível invocar o metodo de leitura do atributo " + field, e);
        }
    }
}
