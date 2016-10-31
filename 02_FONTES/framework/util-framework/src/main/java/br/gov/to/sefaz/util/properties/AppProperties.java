package br.gov.to.sefaz.util.properties;

import org.apache.commons.collections4.SetUtils;
import org.apache.commons.lang3.StringUtils;
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
 * Classe responsável pela recuperação de parametros da aplicação (contido no arquivo application.appProperties).
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
    public static Optional<String> getAppProperty(String name) {
        return Optional.ofNullable(propertyHolder.getAppProperties().getProperty(name));
    }

    /**
     * Retorna as propriedades de configuração customizadas da aplicação.
     *
     * @return propriedades customizadas.
     */
    public static Properties getCustomProperties() {
        return propertyHolder.getCustomProperties();
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
     * Retorna um parametro de configuração da aplicação dado o nome da propriedade customizada.
     *
     * @param name nome da propriedade customizada.
     * @return valor da propriedade customizada.
     */
    public static Optional<String> getCustomProperty(String name) {
        return Optional.ofNullable(propertyHolder.getCustomProperties().getProperty(name));
    }

    /**
     * Classe singleton para carregamento das propriedades do sistema, garantindo que todos que acessarem os
     * parametros da aplicação atraavés do {@link AppProperties} vão acessar as mesmas propriedades e que serão
     * carregadas apenas uma vez durante a aplicação, evitando multiplos acessoas a disco.
     */
    private static class PropertyHolder {

        private final Properties appProperties;
        private final Properties customProperties;

        public PropertyHolder() {
            appProperties = new Properties();
            Path filePathApp = Paths.get(System.getProperty("jboss.server.config.dir"), "application.properties");
            Properties defaultAppProperties = loadDefaultProperties("/application.properties");
            createIfNotExists(filePathApp, defaultAppProperties);
            loadApplicationProperties(filePathApp, appProperties);
            setNewProperties(filePathApp, defaultAppProperties, appProperties);

            customProperties = new Properties();
            Path filePathCustom = Paths.get(System.getProperty("jboss.server.config.dir"), "custom.properties");
            Properties defaultCustomProperties = loadDefaultProperties("/custom.properties");
            createIfNotExists(filePathCustom, defaultCustomProperties);
            loadApplicationProperties(filePathCustom, customProperties);
            setNewProperties(filePathApp, defaultAppProperties, customProperties);
        }

        private void setNewProperties(Path filePath, Properties defaultProperties, Properties properties) {
            SetUtils.SetView<Object> newProps = SetUtils
                    .difference(defaultProperties.stringPropertyNames(), properties.stringPropertyNames());

            if (!newProps.isEmpty()) {
                defaultProperties.forEach(properties::put);
                try (OutputStream os = Files.newOutputStream(filePath)) {
                    properties.store(os, StringUtils.EMPTY);
                } catch (IOException e) {
                    throw new ApplicationPropertyLoadException(
                            "Erro ao criar novas propriedades aos parametros da aplicação!", e);
                }
            }
        }

        private void loadApplicationProperties(Path filePath, Properties properties) {
            try (InputStream stream = Files.newInputStream(filePath)) {
                properties.load(stream);
            } catch (IOException e) {
                throw new ApplicationPropertyLoadException("Erro ao carregar os parametros da aplicação!", e);
            }
        }

        private void createIfNotExists(Path filePath, Properties defaultProperties) {
            if (Files.notExists(filePath)) {
                try (OutputStream stream = Files.newOutputStream(filePath)) {
                    defaultProperties.store(stream, StringUtils.EMPTY);
                } catch (IOException e) {
                    throw new ApplicationPropertyLoadException("Erro ao criar os parametros da aplicação!", e);
                }
            }
        }

        private Properties loadDefaultProperties(String fileName) {
            try (InputStream stream = getClass().getResourceAsStream(fileName)) {
                Properties defaultProperties = new Properties();

                defaultProperties.load(stream);

                return defaultProperties;
            } catch (IOException e) {
                throw new ApplicationPropertyLoadException("Erro ao carregar os parametros padrões da aplicação!", e);
            }
        }

        public Properties getAppProperties() {
            return appProperties;
        }

        public Properties getCustomProperties() {
            return customProperties;
        }

    }

}
