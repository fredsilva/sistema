package br.gov.to.sefaz.cci.configuration;

import br.gov.to.sefaz.cci.business.BusinessPackageMarker;
import br.gov.to.sefaz.cci.persistence.entity.CciEntityPackageMarker;
import br.gov.to.sefaz.cci.persistence.repository.RepositoryPackageMarker;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Configurações do spring para o módulo de cadastro de contribuintes do sistema.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 09/08/2016 10:56:59
 */
@Configuration
@ComponentScan(basePackageClasses = {BusinessPackageMarker.class, CciEntityPackageMarker.class,
        RepositoryPackageMarker.class})
@EnableJpaRepositories(basePackageClasses = {RepositoryPackageMarker.class})
public class CciCoreConfiguration {

}
