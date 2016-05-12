package br.gov.to.sefaz.arr.configuration;

import br.gov.to.sefaz.arr.parametros.business.BusinessPackageMarker;
import br.gov.to.sefaz.arr.parametros.persistence.entity.ArrEntityPackageMarker;
import br.gov.to.sefaz.arr.parametros.persistence.repository.RepositoryPackageMarker;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Configurações do spring para o modulo de arrecadação do sistema.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 14/04/2016 18:30:00
 */
@Configuration
@ComponentScan(basePackageClasses = {
        BusinessPackageMarker.class,
        ArrEntityPackageMarker.class})
@EnableJpaRepositories(basePackageClasses = {RepositoryPackageMarker.class})
public class ArrCoreConfiguration {
}