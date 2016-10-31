package br.gov.to.sefaz.presentation.configuration;

import br.gov.to.sefaz.util.json.JsonMapperUtils;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * Configuração para conversor de mensagens http para JSON, utilizado nos
 * {@link org.springframework.web.bind.annotation.RequestMapping}s.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 06/09/2016 13:58:00
 */
public class WebServiceMvcConfigurationSupport extends WebMvcConfigurationSupport {

    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // put the jackson converter to the front of the list so that application/json content-type strings will
        // be treated as JSON
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(JsonMapperUtils.createObjectMapper());
        converters.add(converter);

        // and probably needs a string converter too for text/plain content-type strings to be properly handled
        converters.add(new StringHttpMessageConverter());
        super.configureMessageConverters(converters);
    }
}
