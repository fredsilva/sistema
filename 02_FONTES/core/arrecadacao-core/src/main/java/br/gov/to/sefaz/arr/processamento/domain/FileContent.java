package br.gov.to.sefaz.arr.processamento.domain;

import br.gov.to.sefaz.arr.processamento.domain.header.FileHeader;

import java.util.ArrayList;
import java.util.List;

/**
 * POJO que contém as linhas de conteúdo de um arquivo que será processado pelo sistema conforme suas regras de negócio.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 23/06/2016 14:58:00
 */
public class FileContent {

    private final Long idArquivo;
    private final FileHeader fileHeader;
    private List<String> lines;
    private int currentLine;

    public FileContent(Long idArquivo, FileHeader fileHeader) {
        this.idArquivo = idArquivo;
        this.fileHeader = fileHeader;
        lines = new ArrayList<>();
        currentLine = 0;
    }

    /**
     * Adiciona uma linha do arquivo na lista {@link br.gov.to.sefaz.arr.processamento.domain.FileContent#lines}.
     */
    public void addLine(String line) {
        getLines().add(line);
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    public String getCurrentLineContent() {
        return lines.get(currentLine++);
    }

    public int getCurrentLine() {
        return currentLine;
    }

    public Long getIdArquivo() {
        return idArquivo;
    }

    public FileHeader getFileHeader() {
        return fileHeader;
    }
}
