package br.gov.to.sefaz.business.configuration;

import br.gov.to.sefaz.business.service.impl.ServicePackageMarker;
import br.gov.to.sefaz.business.service.validation.ValidationPackageMarker;
import br.gov.to.sefaz.business.service.validation.ValidationSuiteAspect;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.validation.Validator;

/**
 * Configurações do spring para o modulo.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 14/04/2016 18:30:00
 */
@Configuration
@ComponentScan(basePackageClasses = {ValidationPackageMarker.class, ServicePackageMarker.class})
@EnableAspectJAutoProxy
public class BusinessConfiguration {

    /**
     * Adiciona uma instancia de {@link ValidationSuiteAspect} ao contexto do Spring para injeção de dependencias.
     *
     * @param validator validador
     * @param listableBeanFactory factory de lista de beans do spring para um tipo de bean (superclasse)
     * @return uma instancia de {@link ValidationSuiteAspect}.
     */
    @Bean
    public ValidationSuiteAspect serviceValidationAspect(Validator validator,
            ListableBeanFactory listableBeanFactory) {
        return new ValidationSuiteAspect(validator, listableBeanFactory);
    }

}