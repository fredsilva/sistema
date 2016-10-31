package br.gov.to.sefaz.util.properties;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Properties;

import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

/**
 * Componente responsável por ler o arquivo de propriedades customizadas.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 15/07/2016 18:52:00
 */
public class PropertiesReader extends Thread {

    CustomPropertiesObservable customPropertiesObservable;

    PropertiesReader(CustomPropertiesObservable customPropertiesObservable) {
        this.customPropertiesObservable = customPropertiesObservable;
    }

    @Override
    public void run() {
        try {
            WatchService watcher = FileSystems.getDefault().newWatchService();
            registerPathInWatchCreate(watcher);

            boolean valid = true;
            while (valid) {
                WatchKey key;
                try {
                    key = watcher.take();
                } catch (InterruptedException ex) {
                    break;
                }
                for (WatchEvent<?> event : key.pollEvents()) {
                    @SuppressWarnings("unchecked")
                    WatchEvent<Path> ev = (WatchEvent<Path>) event;
                    Path fileName = ev.context();
                    processFile(fileName);
                }
                valid = key.reset();
            }
        } catch (IOException e) {
            Logger.getLogger(this.getClass()).error("Erro ao registrar o arquivo de propriedades customizadas.", e);
        }
    }

    /**
     * Encaminha o arquivo para o processamento.
     *
     * @param fileName nome do arquivo
     */
    public void processFile(Path fileName) {
        if (fileName.toString().equals("custom.properties")) {
            Path filePathCustom = Paths.get(System.getProperty("jboss.server.config.dir"), "custom.properties");
            Properties defaultCustomProperties = loadDefaultProperties(filePathCustom);
            customPropertiesObservable.setCustomProperties(defaultCustomProperties);
        }
    }

    private void registerPathInWatchCreate(WatchService watcher) throws IOException {
        Path dir = Paths.get(System.getProperty("jboss.server.config.dir"));
        dir.register(watcher, ENTRY_MODIFY);
        Logger.getLogger(this.getClass()).info("Watch Service registrado para o diretório: " + dir.getFileName());
    }

    private Properties loadDefaultProperties(Path filePath) {
        try (InputStream stream = Files.newInputStream(filePath)) {
            Properties defaultProperties = new Properties();

            defaultProperties.load(stream);

            return defaultProperties;
        } catch (IOException e) {
            throw new ApplicationPropertyLoadException("Erro ao carregar os parametros padrões da aplicação!", e);
        }
    }

}
