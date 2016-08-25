package br.gov.to.sefaz.arr.processamento.creator;

import br.gov.to.sefaz.arr.processamento.reader.FileFactory;
import br.gov.to.sefaz.arr.processamento.reader.FileReader;
import br.gov.to.sefaz.arr.processamento.reader.FileReaderExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Classe que cria os {@link br.gov.to.sefaz.arr.processamento.reader.FileReader} para inciar a leitura dos arquivos de
 * arrecadação.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 19/07/2016 10:18:00
 */
@Component
public class FileReaderCreator {

    @Autowired
    public FileReaderCreator(FileFactory fileFactory) {
        FileReader fileReader = new FileReader(fileFactory);
        FileReaderExist fileReaderExist = new FileReaderExist(fileReader);

        fileReaderExist.processarArquivosExistentes();
        fileReader.start();
    }

}
