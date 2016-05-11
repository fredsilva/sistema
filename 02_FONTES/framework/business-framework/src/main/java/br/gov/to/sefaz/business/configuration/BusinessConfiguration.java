package br.gov.to.sefaz.business.configuration;

import br.gov.to.sefaz.business.service.handler.HandlerPackageMarker;
import br.gov.to.sefaz.business.service.validation.ServiceValidationAspect;
import br.gov.to.sefaz.business.service.validation.ValidationPackageMarker;
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
@ComponentScan(basePackageClasses = {HandlerPackageMarker.class, ValidationPackageMarker.class})
@EnableAspectJAutoProxy
public class BusinessConfiguration {

    @Bean
    public ServiceValidationAspect serviceValidationAspect(Validator validator,
            ListableBeanFactory listableBeanFactory) {
        return new ServiceValidationAspect(validator, listableBeanFactory);
    }
}