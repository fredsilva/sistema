package br.gov.to.sefaz.business.service.validation;

import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.util.message.SourceBundle;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

/**
 * {@link Aspect} o qual será executado sempre quando a anotação {@link ValidationSuite} for utilizada em um objeto para
 * validação da camada de serviço.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 28/04/2016 17:48:00
 */
@Aspect
@Configurable
public class ValidationSuiteAspect {

    private final Validator validator;
    @SuppressWarnings("rawtypes")
    private final Collection<ServiceValidator> serviceValidators;

    @Autowired
    @SuppressWarnings("rawtypes")
    public ValidationSuiteAspect(
            Validator validator, ListableBeanFactory listableBeanFactory) {
        this.validator = validator;
        Map<String, ServiceValidator> beansOfType = listableBeanFactory.getBeansOfType(ServiceValidator.class);
        serviceValidators = beansOfType.values();
    }

    /**
     * Realiza a operação para validar o {@link Object}, tanto validações customizadas, as quais devem implementar a
     * interface {@link ServiceValidator}, quanto validações de constraint do {@link Validator}.
     *
     * @param joinPoint contém as informações do parâmetro o qual será validado.
     * @throws NoSuchMethodException quando não encontra o método o qual o joinPoint referencia.
     */
    @Before("execution(* *(@br.gov.to.sefaz.business.service.validation.ValidationSuite (*)))")
    public void valid(JoinPoint joinPoint) throws NoSuchMethodException {
        // Lista de violações
        Set<CustomViolation> customViolations = new HashSet<>();

        Method interfaceMethod = ((MethodSignature) joinPoint.getSignature()).getMethod();
        Method implementationMethod = joinPoint.getTarget().getClass().getMethod(interfaceMethod.getName(),
                interfaceMethod.getParameterTypes());

        // Todos os objetos anotados com @ValidationSuite são validados
        Annotation[][] annotationParameters = implementationMethod.getParameterAnnotations();
        List<Annotation[]> annotationsArray = Arrays.asList(annotationParameters);

        for (Annotation[] annotations : annotationsArray) {
            for (Annotation annotation : annotations) {
                if (annotation.annotationType().equals(ValidationSuite.class)) {
                    ValidationSuite validationSuite = (ValidationSuite) annotation;
                    String context = validationSuite.context();
                    Object arg = joinPoint.getArgs()[0];
                    Class clazz = validationSuite.clazz();

                    if (clazz.equals(ValidationSuite.class) && !Objects.isNull(arg)) {
                        clazz = getArgClass(arg);
                    }

                    customViolations.addAll(validate(arg, context, clazz));
                }
            }
        }

        if (!customViolations.isEmpty()) {
            throw new CustomValidationException(customViolations);
        }
    }

    /**
     * Realiza as validações do objeto tanto com as validações de constraints através do {@link Validator}, quanto das
     * validações customizadas, através do {@link ServiceValidator}. Se o objeto passado é uma
     * {@link java.util.Collection} então ele irá realizar o
     * {@link br.gov.to.sefaz.business.service.validation.ServiceValidator#validateAll(java.util.Collection)}.
     *
     * @param arg objeto a ser validado
     * @param context contexto para execução das validações
     * @return lista de {@link CustomViolation} contendo as violações encontradas através do {@link Validator} e
     *         {@link ServiceValidator}.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private Set<CustomViolation> validate(Object arg, String context, Class clazz) {
        Set<CustomViolation> customViolations = new HashSet<>();

        customViolations.addAll(createCustomViolationsByContraints(arg));

        List<ServiceValidator> filteredServiceValidators = serviceValidators.stream()
                .filter(validatorEntry -> validatorEntry.support(clazz, context)).collect(Collectors.toList());

        // para cada validador verifica se deve utilizar o
        for (ServiceValidator filteredServiceValidator : filteredServiceValidators) {
            Set validate;

            if (arg instanceof Collection) {
                validate = filteredServiceValidator.validateAll((Collection) arg);
            } else {
                validate = filteredServiceValidator.validate(arg);
            }
            customViolations.addAll(validate);
        }

        return customViolations;
    }

    /**
     * Cria um {@link CustomViolation} para cada {@link ConstraintViolation} encontrado.
     *
     * @param arg objeto a ser validado
     * @return lista de {@link CustomViolation} encontradas através das validações do {@link Validator}.
     */
    private List<CustomViolation> createCustomViolationsByContraints(Object arg) {
        return validator.validate(arg).stream()
                .map(constraintViolation -> new CustomViolation(
                        SourceBundle.getMessageByExpression(constraintViolation.getMessage())))
                .collect(Collectors.toList());
    }

    /**
     * Verifica se o {@link java.lang.Object} passado é uma {@link java.util.Collection}. Se for retorna a classe do
     * Objeto da coleção, se não retorna a classe do objeto passado.
     *
     * @param arg Objeto a ser extraida a classe
     * @return classe do Objeto passado
     */
    private Class getArgClass(Object arg) {
        if (arg instanceof Collection) {
            Collection argCollection = (Collection) arg;
            if (!argCollection.isEmpty()) {
                return argCollection.iterator().next().getClass();
            }
        }
        return arg.getClass();
    }
}
