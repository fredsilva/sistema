package br.gov.to.sefaz.util.properties;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

/**
 * Classe responsável pela recuperação de parametros da aplicação (contido no arquivo application.properties).
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 03/06/2016 15:38:00
 */
public class AppProperties {

    private static PropertyHolder propertyHolder = new PropertyHolder();

    /**
     * Retorna um parametro de configuração da aplicação dado o nome da propriedade.
     *
     * @param name nome da propriedade
     * @return valor da propriedade
     */
    public static Optional<String> getProperty(String name) {
        return Optional.ofNullable(propertyHolder.getProperties().getProperty(name));
    }

    /**
     * Retorna informações referentes ao servidor de aplicação ao qual a aplicação rodado.
     *
     * @return informações sobre o servidor de aplicação
     */
    public static String getServerInfo() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getServletContext().getServerInfo();
    }

    /**
     * Classe singleton para carregamento das propriedades do sistema, garantindo que todos que acessarem os
     * parametros da aplicação atraavés do {@link AppProperties} vão acessar as mesmas propriedades e que serão
     * carregadas apenas uma vez durante a aplicação, evitando multiplos acessoas a disco.
     */
    private static class PropertyHolder {

        private final Properties properties;

        public PropertyHolder() {
            properties = new Properties();
            try {
                properties.load(getClass().getResourceAsStream("/application.properties"));
            } catch (IOException e) {
                throw new ApplicationPropertyLoadException("Erro ao carregar os parametros da aplicação!", e);
            }
        }

        public Properties getProperties() {
            return properties;
        }
    }
}
