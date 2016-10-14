package br.gov.to.sefaz.util.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Classe que provê {@link br.gov.to.sefaz.util.properties.PropertiesReader}.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 11/10/2016 10:49:00
 */
@Component
public class PropertiesReaderProvider {

    @Autowired
    public PropertiesReaderProvider(CustomPropertiesObservable customPropertiesObservable) {
        PropertiesReader propertiesReader = new PropertiesReader(customPropertiesObservable);

        propertiesReader.start();
    }
}
