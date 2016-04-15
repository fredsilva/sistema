package br.gov.to.sefaz.arr.configuration;

import br.gov.to.sefaz.arr.business.BusinessPackageMarker;
import br.gov.to.sefaz.arr.managedbean.ManagedBeanPackageMarker;
import br.gov.to.sefaz.arr.persistence.entity.ArrEntityPackageMarker;
import br.gov.to.sefaz.arr.persistence.repository.RepositoryPackageMarker;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Configurações do spring para o modulo de arrecadação do sistema.
 *
 * @author gabriel.dias
 */
@Configuration
@ComponentScan(basePackageClasses = {
        BusinessPackageMarker.class,
        ManagedBeanPackageMarker.class,
        ArrEntityPackageMarker.class})
@EnableJpaRepositories(basePackageClasses = {RepositoryPackageMarker.class})
public class ArrCoreSpringConfig {
}
