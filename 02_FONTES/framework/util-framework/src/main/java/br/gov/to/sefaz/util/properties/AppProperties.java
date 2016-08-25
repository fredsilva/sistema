package br.gov.to.sefaz.util.properties;

import org.apache.commons.collections4.SetUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
            Path filePath = Paths.get(System.getProperty("jboss.server.config.dir"), "application.properties");

            Properties defaultProperties = loadDefaultProperties();
            createIfNotExists(filePath, defaultProperties);
            loadApplicationProperties(filePath);
            setNewProperties(filePath, defaultProperties);
        }

        private void setNewProperties(Path filePath, Properties defaultProperties) {
            SetUtils.SetView<Object> newProps = SetUtils
                    .difference(defaultProperties.stringPropertyNames(), properties.stringPropertyNames());

            if (!newProps.isEmpty()) {
                defaultProperties.forEach(properties::put);
                try (OutputStream os = Files.newOutputStream(filePath)) {
                    properties.store(os, "");
                } catch (IOException e) {
                    throw new ApplicationPropertyLoadException(
                            "Erro ao criar novas propriedades aos parametros da aplicação!", e);
                }
            }
        }

        private void loadApplicationProperties(Path filePath) {
            try (InputStream stream = Files.newInputStream(filePath)) {
                properties.load(stream);
            } catch (IOException e) {
                throw new ApplicationPropertyLoadException("Erro ao carregar os parametros da aplicação!", e);
            }
        }

        private void createIfNotExists(Path filePath, Properties defaultProperties) {
            if (!Files.exists(filePath)) {
                try (OutputStream stream = Files.newOutputStream(filePath)) {
                    defaultProperties.store(stream, "");
                } catch (IOException e) {
                    throw new ApplicationPropertyLoadException("Erro ao criar os parametros da aplicação!", e);
                }
            }
        }

        private Properties loadDefaultProperties() {
            try (InputStream stream = getClass().getResourceAsStream("/application.properties")) {
                Properties defaultProperties = new Properties();

                defaultProperties.load(stream);

                return defaultProperties;
            } catch (IOException e) {
                throw new ApplicationPropertyLoadException("Erro ao carregar os parametros padrões da aplicação!", e);
            }
        }

        public Properties getProperties() {
            return properties;
        }
    }
}
