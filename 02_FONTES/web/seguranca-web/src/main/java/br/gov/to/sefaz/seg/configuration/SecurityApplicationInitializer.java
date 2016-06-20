package br.gov.to.sefaz.seg.configuration;

import br.gov.to.sefaz.seg.filter.CorsFilter;
import br.gov.to.sefaz.seg.filter.NavegacaoFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.DelegatingFilterProxy;

import java.util.EnumSet;
import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Adiciona filtros, listeners e otras configurações de segurança ao contexto da aplicação. Similar as configurações do
 * web.xml.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 07/06/2016 18:10:00
 */
@Configuration
public class SecurityApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.addListener(new RequestContextListener());

        servletContext
                .addFilter("corsFilter", new CorsFilter())
                .addMappingForUrlPatterns(
                        EnumSet.of(DispatcherType.FORWARD, DispatcherType.REQUEST, DispatcherType.ERROR), false, "/*");
        servletContext
                .addFilter("springSecurityFilterChain", new DelegatingFilterProxy())
                .addMappingForUrlPatterns(
                        EnumSet.of(DispatcherType.FORWARD, DispatcherType.REQUEST, DispatcherType.ERROR), false, "/*");
        servletContext
                .addFilter("geoIpBlock", new GeoIpBlockFilter())
                .addMappingForUrlPatterns(null, false, "/*");
        servletContext
                .addFilter("navegacaoFilter", new NavegacaoFilter())
                .addMappingForUrlPatterns(null, false, "/protected/*");

    }
}
