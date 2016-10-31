package br.gov.to.sefaz.util.file;

import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Utilitário para auxiliar a criação de um ZIP através de um byte[].
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 06/07/2016 17:54:00
 */
@Component
public class ZipByteUtil {

    /**
     * Comprime um byte[] para formato ZIP.
     *
     * @param zipName nome do arquivo ZIP
     * @param bytes   objeto que será comprimido para o ZIP
     * @return byte[] correspondente ao ZIP criado
     * @throws IOException erro ao criar o ZIP
     */
    public byte[] zipBytes(String zipName, byte[] bytes) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos);

        ZipEntry entry = new ZipEntry(zipName);
        entry.setSize(bytes.length);
        zos.putNextEntry(entry);
        zos.write(bytes);

        zos.closeEntry();
        zos.close();

        return baos.toByteArray();
    }
}
