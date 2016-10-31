package br.gov.to.sefaz.business.service.validation;

import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import org.aspectj.lang.annotation.Aspect;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Interface a qual todos os validadores customizados de serviço devem implementar para o correto
 * funcinamento do {@link ValidationSuite} através do {@link Aspect}.
 *
 * @param <T> Tipo do objeto a ser validado
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 28/04/2016 17:48:00
 */
public interface ServiceValidator<T> {

    /**
     * Verifica se o validador é suportado para a {@link Class} a qual deverá ser a mesma do genérico passado.
     *
     * @param clazz   {@link Class} que irá ser verificada
     * @param context em que o validador será executado
     * @return true se a classe passada é suportada pelo validador, caso não, retorna false.
     */
    boolean support(Class<?> clazz, String context);

    /**
     * Valida o {@link Object} conforme critérios customizados.
     *
     * @param target {@link Object} o qual será utilizado para a validação customizada.
     * @return lista com as violações da validação, se as verificações realizadas no método passarem, retorna uma
     *     lista vazia.
     */
    Set<CustomViolation> validate(T target);

    /**
     * Valida o {@link java.util.Collection} de {@link java.lang.Object} conforme critérios customizados.
     * Nunca entrará neste método se a lista for {@link java.util.Collection#isEmpty()}.
     *
     * @param target {@link Object} o qual será utilizado para a validação customizada.
     * @return lista com as violações da validação, se as verificações realizadas no método passarem, retorna uma
     *     lista vazia.
     */
    default Set<CustomViolation> validateAll(Collection<T> target) {
        Set<CustomViolation> customViolations = new HashSet<>();

        for (T t : target) {
            customViolations.addAll(validate(t));
        }

        return customViolations;
    }
}
