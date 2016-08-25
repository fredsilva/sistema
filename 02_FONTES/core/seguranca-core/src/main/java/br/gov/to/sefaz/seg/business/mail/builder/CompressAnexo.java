package br.gov.to.sefaz.seg.business.mail.builder;

import br.gov.to.sefaz.seg.business.mail.domain.Anexo;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Comprime os {@link br.gov.to.sefaz.seg.business.mail.domain.Anexo} que serão armazenados no Banco
 * e enviados por email.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 03/06/2016 15:13:00
 */
@Component
public class CompressAnexo {

    /**
     * Gera os bytes referentes a um arquivo zip contendo todos os anexos paassados por parametro.
     *
     * @param anexoList lista de anexos que devem estar no .zip gerado
     * @return bytes para criação de um arquivo zip
     * @throws IOException caso ocorra algum erro de I/O
     */
    public byte[] zipBytes(List<Anexo> anexoList) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos);

        for (Anexo anexo : anexoList) {
            ZipEntry entry = new ZipEntry(anexo.getFileName());
            entry.setSize(anexo.getFileContent().length);
            zos.putNextEntry(entry);
            zos.write(anexo.getFileContent());
        }

        zos.closeEntry();
        zos.close();
        return baos.toByteArray();
    }
}
