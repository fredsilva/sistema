package br.gov.to.sefaz.arr.configuration;

import br.gov.to.sefaz.arr.parametros.managedbean.ManagedBeanPackageMarker;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Configurações do spring para o modulo de arrecadação do sistema.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 05/05/2016 10:56:59
 */
@Configuration
@ComponentScan(basePackageClasses = {ManagedBeanPackageMarker.class})
public class ArrWebConfiguration {
}
