package br.gov.to.sefaz.seg.business.mail.domain;

import java.util.Arrays;
import java.util.Objects;

/**
 * POJO que representa um anexo para o {@link CorreioEletronico},
 * contendo o nome do arquivo de anexo e seu conteúdo.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 03/06/2016 14:11:00
 */
public class Anexo {

    private final String fileName;
    private final byte[] fileContent;

    public Anexo(String fileName, byte[] fileContent) {
        this.fileName = fileName;
        this.fileContent = fileContent;
    }

    public String getFileName() {
        return fileName;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Anexo anexo = (Anexo) o;
        return Objects.equals(fileName, anexo.fileName)
                && Arrays.equals(fileContent, anexo.fileContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, fileContent);
    }

    @Override
    public String toString() {
        return "Anexo{"
                + "fileName='" + fileName + '\''
                + ", fileContent=" + Arrays.toString(fileContent)
                + '}';
    }
}
