package br.gov.to.sefaz.presentation.configuration;

import br.gov.to.sefaz.presentation.managedbean.ManagedBeanPackageMarker;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Configurações do Spring para o modulo.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 14/04/2016 18:30:00
 */
@Configuration
@ComponentScan(basePackageClasses = ManagedBeanPackageMarker.class)
public class PresentationConfiguration {
}
