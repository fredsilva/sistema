package br.gov.to.sefaz.business.service.validation;

import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;

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
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

/**
 * {@link Aspect} o qual será executado sempre quando a anotação {@link ServiceValidation} for utilizada em um objeto
 * para validação da camada de serviço.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 28/04/2016 17:48:00
 */
@Aspect
@Configurable
public class ServiceValidationAspect {

    private final Validator validator;
    @SuppressWarnings("rawtypes")
    private final Collection<ServiceValidator> serviceValidators;

    @Autowired
    @SuppressWarnings("rawtypes")
    public ServiceValidationAspect(Validator validator, ListableBeanFactory listableBeanFactory) {
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
    @Before("execution(* *(@br.gov.to.sefaz.business.service.validation.ServiceValidation (*)))")
    public void valid(JoinPoint joinPoint) throws NoSuchMethodException {
        // Lista de violações
        Set<CustomViolation> customViolations = new HashSet<>();

        Method interfaceMethod = ((MethodSignature) joinPoint.getSignature()).getMethod();
        Method implementationMethod = joinPoint.getTarget().getClass().getMethod(interfaceMethod.getName(),
                interfaceMethod.getParameterTypes());

        // Todos os objetos anotados com @ServiceValidation são validados
        Annotation[][] annotationParameters = implementationMethod.getParameterAnnotations();
        List<Annotation[]> annotationsArray = Arrays.asList(annotationParameters);

        for (Annotation[] annotations : annotationsArray) {
            for (Annotation annotation : annotations) {
                if (annotation.annotationType().equals(ServiceValidation.class)) {
                    ServiceValidation serviceValidation = (ServiceValidation) annotation;
                    String context = serviceValidation.context();
                    Object arg = joinPoint.getArgs()[0];

                    customViolations.addAll(validate(arg, context));
                }
            }
        }

        if (!customViolations.isEmpty()) {
            throw new CustomValidationException(customViolations);
        }
    }

    /**
     * Realiza as validações do objeto tanto com as validações de constraints através do {@link Validator}, quanto das
     * validações customizadas, através do {@link ServiceValidator}.
     *
     * @param arg objeto a ser validado
     * @param context contexto para execução das validações
     * @return lista de {@link CustomViolation} contendo as violações encontradas através do {@link Validator} e
     *         {@link ServiceValidator}.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private Set<CustomViolation> validate(Object arg, String context) {
        Set<CustomViolation> customViolations = new HashSet<>();

        customViolations.addAll(createCustomViolationsByContraints(arg));

        List<ServiceValidator> filteredServiceValidators = serviceValidators.stream()
                .filter(validatorEntry -> validatorEntry.support(arg.getClass(), context)).collect(Collectors.toList());

        filteredServiceValidators.stream()
                .forEach(serviceValidator -> customViolations.addAll(serviceValidator.validate(arg)));

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
                .map(constraintViolation -> new CustomViolation(constraintViolation.getMessage()))
                .collect(Collectors.toList());
    }
}
