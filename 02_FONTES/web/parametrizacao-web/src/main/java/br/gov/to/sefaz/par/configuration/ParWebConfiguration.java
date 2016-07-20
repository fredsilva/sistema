package br.gov.to.sefaz.par.configuration;

import br.gov.to.sefaz.par.managebean.ManagedBeanPackageMarker;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Configurações do spring para o modulo de parametrização do sistema.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 22/06/2016 18:04:11
 */
@Configuration
@ComponentScan(basePackageClasses = { ManagedBeanPackageMarker.class })
public class ParWebConfiguration {
}
