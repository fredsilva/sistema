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
     * Classe em que a validação deverá ser executada. Através de uma {@link Class} que deverá ser adicionada no
     * {@link ServiceValidator#support(Class, String)}. Caso a classe não seja setada a classe utilizada será a do
     * objeto anotado.
     *
     * @return Classe em que a validação será executada
     */
    Class clazz() default ValidationSuite.class;

    /**
     * Sinaliza se a suite de validação deve rodar apenas os {@link ServiceValidator} e não as anotações de constraint.
     *
     * @return se a suite de validações deve rodar apenas as validações customs
     */
    boolean onlyCustom() default false;
}
