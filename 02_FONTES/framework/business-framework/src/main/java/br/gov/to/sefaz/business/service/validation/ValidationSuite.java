package br.gov.to.sefaz.business.service.validation;

import org.aspectj.lang.annotation.Aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotação responsável por realizar validações no {@link Object} em questão, através do uso de {@link Aspect}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 28/04/2016 17:48:00
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface ValidationSuite {

    /**
     * Contexto em que a validação deverá ser executada. Através de uma {@link String} que deverá ser adicionada no
     * {@link ServiceValidator#support(Class, String)}. Os contextos default usados pode ser vistos em
     * {@link ValidationContext}.
     *
     * @return Contexto em que a validação será executada
     */
    String context() default "";

    /**
     * Se o objeto a ser validado está dentro de uma {@link java.util.Collection} então este atributo deve ser
     * {@link java.lang.Boolean#TRUE}. Se não o default é {@link java.lang.Boolean#FALSE}.
     *
     * @return se o objeto está contido em uma coleção.
     */
    boolean isCollection() default false;

    /**
     * {@link java.lang.Class} a qual será utilizada para validação do objeto único e coleção deste mesmo objeto.
     * Se o objeto a ser validado não é uma {@link java.util.Collection} este atributo não precisa ser adicionado,
     * pois o {@link br.gov.to.sefaz.business.service.validation.ValidationSuiteAspect} utiliza a
     * {@link java.lang.Class} do objeto.
     *
     * @return classe do objeto a ser validado
     */
    Class clazz() default Class.class;
}
