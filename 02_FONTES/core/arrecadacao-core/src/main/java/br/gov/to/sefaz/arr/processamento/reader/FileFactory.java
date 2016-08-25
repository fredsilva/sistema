package br.gov.to.sefaz.arr.processamento.reader;

import br.gov.to.sefaz.arr.processamento.ProcessArquivo;
import br.gov.to.sefaz.arr.processamento.process.ProcessArquivoArrecadacao;
import br.gov.to.sefaz.arr.processamento.process.ProcessArquivoStr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Fábrica para construir um {@link br.gov.to.sefaz.arr.processamento.type.ProcessFileType} conforme o HEADER do
 * arquivo.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 24/06/2016 10:01:00
 */
@Component
public class FileFactory {

    private final ProcessArquivoArrecadacao processFileTypeArrec;
    private final ProcessArquivoStr processArquivoStr;

    @Autowired
    public FileFactory(ProcessArquivoArrecadacao processFileTypeArrec, ProcessArquivoStr processArquivoStr) {
        this.processFileTypeArrec = processFileTypeArrec;
        this.processArquivoStr = processArquivoStr;
    }

    /**
     * Cria o {@link br.gov.to.sefaz.arr.processamento.ProcessArquivo} correspondente.
     *
     * @param fileName nome do arquivo a ser processado.
     * @return a implementação correspondente de {@link br.gov.to.sefaz.arr.processamento.ProcessArquivo}.
     */
    public ProcessArquivo createProcessFile(String fileName) {
        if (fileName.endsWith(".xml")) {
            return processArquivoStr;
        } else {
            return processFileTypeArrec;
        }
    }
}
