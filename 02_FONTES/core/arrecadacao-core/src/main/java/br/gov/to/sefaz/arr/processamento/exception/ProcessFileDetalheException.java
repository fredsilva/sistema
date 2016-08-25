package br.gov.to.sefaz.arr.processamento.exception;

import br.gov.to.sefaz.arr.processamento.domain.detalhe.FileDetalhe;
import br.gov.to.sefaz.exception.file.ProcessFileException;

/**
 * Exceção para Detalhe do arquivo processado.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 25/07/2016 15:44:00
 */
public class ProcessFileDetalheException extends ProcessFileException {

    private final FileDetalhe fileDetalhe;

    public ProcessFileDetalheException(Integer codigoRejeicao, FileDetalhe fileDetalhe) {
        super(codigoRejeicao);
        this.fileDetalhe = fileDetalhe;
    }

    public FileDetalhe getFileDetalhe() {
        return fileDetalhe;
    }
}
