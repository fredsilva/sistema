package br.gov.to.sefaz.arr.processamento.type.factory;

import br.gov.to.sefaz.arr.processamento.type.FileTypeEnum;
import br.gov.to.sefaz.arr.processamento.type.ProcessFileType;
import br.gov.to.sefaz.arr.processamento.type.ProcessFileTypeArrec;
import br.gov.to.sefaz.arr.processamento.type.ProcessFileTypeSN;
import br.gov.to.sefaz.exception.file.ProcessFileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Fábrica para construir um {@link ProcessFileType} conforme o HEADER do arquivo.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 24/06/2016 10:01:00
 */
@Component
public class FileTypeFactory {

    private final ProcessFileTypeArrec processFileTypeArrec;
    private final ProcessFileTypeSN processFileTypeSN;

    @Autowired
    public FileTypeFactory(ProcessFileTypeArrec processFileTypeArrec, ProcessFileTypeSN processFileTypeSN) {
        this.processFileTypeArrec = processFileTypeArrec;
        this.processFileTypeSN = processFileTypeSN;
    }

    /**
     * Cria um {@link br.gov.to.sefaz.arr.processamento.type.ProcessFileType} conforme o HEADER.
     *
     * @param header deve possuir o tipo de arquivo, conforme identificação em
     *               {@link br.gov.to.sefaz.arr.processamento.type.FileTypeEnum}
     * @return o {@link br.gov.to.sefaz.arr.processamento.type.ProcessFileType} por determinado tipo de arquivo
     *     definido através do HEADER
     */
    public ProcessFileType createProcessFileType(String header) {
        FileTypeEnum fileTypeEnum = FileTypeEnum.valueBy(header);

        if (fileTypeEnum != null) {
            switch (fileTypeEnum) {
                case ARRECADACAO:
                    return processFileTypeArrec;
                case SIMPLES_NACIONAL:
                    return processFileTypeSN;
                default:
                    throw new ProcessFileException(24);
            }
        }

        throw new ProcessFileException(24);
    }
}
