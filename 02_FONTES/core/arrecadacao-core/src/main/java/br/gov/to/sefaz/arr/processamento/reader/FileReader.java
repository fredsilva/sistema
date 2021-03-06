package br.gov.to.sefaz.arr.processamento.reader;

import br.gov.to.sefaz.arr.processamento.ProcessArquivo;
import br.gov.to.sefaz.util.properties.CustomPropertiesObserver;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Observable;
import java.util.Observer;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

/**
 * Componente responsável por ler os arquivos de arrecadação.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 15/07/2016 18:52:00
 */
public class FileReader extends Thread implements Observer {

    private final FileFactory fileFactory;
    private final CustomPropertiesObserver customPropertiesObserver;
    private String path;
    private WatchService watcher;

    public FileReader(FileFactory fileFactory, CustomPropertiesObserver customPropertiesObserver) {
        this.fileFactory = fileFactory;
        this.customPropertiesObserver = customPropertiesObserver;
        this.path = this.customPropertiesObserver.getCustomProperty("arrec.directory").orElse(null);

        this.customPropertiesObserver.addObserver(this);
    }

    @Override
    public void run() {
        try {
            watcher = FileSystems.getDefault().newWatchService();
            registerPathInWatchCreate(watcher, path);

            boolean valid = true;
            while (valid) {
                WatchKey key;
                try {
                    key = watcher.take();
                } catch (InterruptedException ex) {
                    break;
                }
                for (WatchEvent<?> event : key.pollEvents()) {
                    sleep();
                    @SuppressWarnings("unchecked")
                    WatchEvent<Path> ev = (WatchEvent<Path>) event;
                    Path fileName = ev.context();
                    processFile(fileName);
                }
                valid = key.reset();
            }
        } catch (IOException e) {
            Logger.getLogger(this.getClass()).error("Erro ao registrar o diretório de processamento de arquivos:"
                    + path, e);
        }
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Logger.getLogger(this.getClass()).error("Erro ao pedir sleep:" + path, e);
        }
    }

    /**
     * Encaminha o arquivo para o processamento.
     *
     * @param fileName nome do arquivo
     */
    @SuppressWarnings("PMD.AvoidCatchingGenericException")
    public void processFile(Path fileName) {
        String filePath = path + "/" + fileName;
        ProcessArquivo processFile = this.fileFactory.createProcessFile(fileName.toString());
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            processFile.processFile(fileInputStream, fileName.toString());
        } catch (Exception e) {
            Logger.getLogger(this.getClass()).error("Erro ao ler o arquivo: " + filePath, e);
        }

        delete(filePath);
    }

    private void registerPathInWatchCreate(WatchService watcher, String path) throws IOException {
        Path dir = Paths.get(path);
        dir.register(watcher, ENTRY_CREATE);
        Logger.getLogger(this.getClass()).info("Watch Service registrado para o diretório: " + path);
    }

    private void delete(String filePath) {
        Path path = Paths.get(filePath);
        try {
            Files.delete(path);
        } catch (IOException e) {
            Logger.getLogger(this.getClass()).error("Não foi possível deletar o arquivo : " + filePath, e);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        path = this.customPropertiesObserver.getCustomProperty("arrec.directory").orElse(null);

        try {
            registerPathInWatchCreate(watcher, path);
        } catch (IOException e) {
            Logger.getLogger(this.getClass()).error("Erro ao registrar o diretório de processamento de arquivos:"
                    + path, e);
        }
    }
}
