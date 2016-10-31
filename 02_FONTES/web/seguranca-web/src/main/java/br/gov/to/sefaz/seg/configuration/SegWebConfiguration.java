package br.gov.to.sefaz.seg.configuration;

import br.gov.to.sefaz.seg.filter.FilterPackageMarker;
import br.gov.to.sefaz.seg.managedbean.ManagedBeanPackageMarker;
import br.gov.to.sefaz.seg.template.managedbean.TemplateMBPackageMarker;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Configurações do spring para o modulo de arrecadação do sistema.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 05/05/2016 10:56:59
 */
@Configuration
@ComponentScan(basePackageClasses = {ManagedBeanPackageMarker.class,
        TemplateMBPackageMarker.class, FilterPackageMarker.class})
public class SegWebConfiguration {
}
