package br.gov.to.sefaz.persistence.query.builder.hql.handler;

import br.gov.to.sefaz.persistence.query.builder.ParamsBuilder;
import br.gov.to.sefaz.util.application.ApplicationUtil;
import br.gov.to.sefaz.util.reflection.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * Classe responsável por controlar os eventos dos atributos de uma entidade.
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 07/07/2016 13:29:00
 */
public class EntityHandler {

    /**
     * Método responsável por retornar o nome da Classe consultada.
     *
     * @param eClazz  Tipo da entidade base a ser consultada.
     *
     * @return retornar o nome da Classe consultada.
     */
    public static String getName(Class<?> eClazz) {
        return eClazz.getSimpleName();
    }

    /**
     * Método responsável por consultar os parâmetros da Entidade.
     *
     * @param eClazz Tipo da entidade base a ser consultada.
     * @param id chave que consulta a entidade.
     *
     * @return retornar os atributos da Entidade consultada.
     */
    public static ParamsBuilder getIdParams(Class<?> eClazz, Object id) {
        List<Field> ids = Arrays.stream(eClazz.getDeclaredFields())
                .filter(field -> field.getDeclaredAnnotation(Id.class) != null)
                .collect(Collectors.toList());

        if (ids.size() == 1) {
            return ParamsBuilder.create(ids.get(0).getName(), id);
        } else {
            IdClass annotation = eClazz.getDeclaredAnnotation(IdClass.class);
            Class idClass = annotation.value();

            if (idClass.isInstance(id)) {
                ParamsBuilder params = ParamsBuilder.empty();
                ids.forEach(field -> params.put(field.getName(), ReflectionUtils.invokeGetter(id, field.getName())));
                return params;
            }

            throw new EntityHandlingException("The type of param 'id' needs to be the same of IdClass annotation ("
                    + idClass.getName() + ")");
        }
    }

    /**
     * Método responsável por pesquisar os parâmetros da Entidade.
     *
     * @param eClazz Tipo da entidade base a ser consultada.
     *
     * @return retornar todos atributos da Entidade consultada.
     */
    public static List<String> getIdFields(Class<?> eClazz) {
        return Arrays.stream(eClazz.getDeclaredFields())
                .filter(field -> field.getDeclaredAnnotation(Id.class) != null)
                .map(Field::getName)
                .collect(Collectors.toList());
    }

    /**
     * Método responsável por consultar a Tabela referente a Entidade informada no parâmetro.
     *
     * @param eClazz Tipo da entidade base a ser consultada.
     *
     * @return retornar o schema e o nome da Tabela.
     */
    public static String getTable(Class<?> eClazz) {
        Table table = eClazz.getDeclaredAnnotation(Table.class);
        String schema = table.schema().toLowerCase();
        String name = table.name().toLowerCase();

        return (schema.isEmpty() ? "" : schema + ".") + name;
    }

    /**
     * Método responsável por consultar o nome da Alias da Entidade.
     *
     * @param eClazz Tipo da entidade base a ser consultada.
     *
     * @return retornar o nome da Alias.
     */
    public static String getAlias(Class<?> eClazz) {
        String name = getName(eClazz);
        return name.replaceAll("[^A-Z]", "").toLowerCase();
    }

    /**
     * Método responsável por consultar a Alias da Entidade.
     *
     * @param property informa o nome da alias.
     *
     * @return retornar o nome da Alias.
     */
    public static String getAlias(String property) {
        property = property.replaceAll(".*\\.", "");
        String name = property.substring(1);
        return property.toLowerCase(ApplicationUtil.LOCALE).charAt(0) + name.replaceAll("[^A-Z]", "").toLowerCase();
    }
}
