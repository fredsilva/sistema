package br.gov.to.sefaz.ecf.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Configurações do spring para o módulo de equipamentos emissores de cupons fiscais do sistema.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 09/08/2016 10:56:59
 */
@Configuration
@ComponentScan(basePackageClasses = {})
@EnableJpaRepositories(basePackageClasses = {})
public class EcfCoreConfiguration {
}