package br.gov.to.sefaz.cat.configuration;

import br.gov.to.sefaz.cat.business.CatBusinessPackageMarker;
import br.gov.to.sefaz.cat.persistence.entity.CatEntityPackageMarker;
import br.gov.to.sefaz.cat.persistence.repository.RepositoryPackageMarker;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Configurações do spring para o modulo de arrecadação do sistema.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 04/05/2016 12:01:00
 */
@Configuration
@ComponentScan(basePackageClasses = {CatBusinessPackageMarker.class, CatEntityPackageMarker.class})
@EnableJpaRepositories(basePackageClasses = {RepositoryPackageMarker.class})
public class CatCoreConfiguration {
}
