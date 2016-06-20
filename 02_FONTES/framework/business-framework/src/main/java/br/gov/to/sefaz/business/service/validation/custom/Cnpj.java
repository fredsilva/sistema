package br.gov.to.sefaz.business.service.validation.custom;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Interface de Constraint Validator para CNPJ.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 25/05/2016 08:28:24
 */
@Target({ ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.METHOD, ElementType.CONSTRUCTOR,
        ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CnpjValidator.class)
@Documented
public @interface Cnpj {

    String message() default "#{msg['business.validation.cnpj.not.valid']}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * Interface de Constraint Validator para CNPJ.List.
     *
     * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
     * @since 25/05/2016 08:29:24
     */
    @Target({ ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.METHOD, ElementType.CONSTRUCTOR,
            ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
    }

}
