package br.gov.to.sefaz.arr.processamento.reader;

import br.gov.to.sefaz.util.properties.AppProperties;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Leitor de arquivos que já se encontram no diretório de depósito dos arquivos de arrecadação ao iniciar a aplicação.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 19/07/2016 17:58:00
 */
public class FileReaderExist {

    private static final String PATH = AppProperties.getCustomProperty("arrec.directory").orElse(StringUtils.EMPTY);

    private final FileReader fileReader;

    public FileReaderExist(FileReader fileReader) {
        this.fileReader = fileReader;
    }

    /**
     * Inicia o processo de arquivos que já se encontram no diretório de depósito dos arquivos de arrecadação.
     */
    public void processarArquivosExistentes() {
        try {
            Files.list(Paths.get(PATH)).forEach(file -> fileReader.processFile(file.getFileName()));
        } catch (IOException e) {
            Logger.getLogger(this.getClass()).error("Erro ao ler arquivos já existentes no diretório: " + PATH, e);
        }
    }

}
