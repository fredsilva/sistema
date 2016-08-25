package br.gov.to.sefaz.arr.processamento.creator;

import br.gov.to.sefaz.arr.persistence.entity.ArquivoRecepcao;
import br.gov.to.sefaz.arr.persistence.enums.SituacaoArquivoEnum;
import br.gov.to.sefaz.arr.processamento.domain.header.FileHeader;
import br.gov.to.sefaz.arr.processamento.type.FileTypeEnum;
import br.gov.to.sefaz.util.file.ZipByteUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Collectors;

/**
 * Cria um {@link br.gov.to.sefaz.arr.persistence.entity.ArquivoRecepcao}
 * através dos parâmetros passados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 06/07/2016 17:08:00
 */
@Component
public class ArquivoRecepcaoCreator {

    private static final Long QUANTIDADE_DOCS = 0L;
    private static final BigDecimal VALOR_TOTAL = BigDecimal.ZERO;

    private final ZipByteUtil zipByteUtil;

    @Autowired
    public ArquivoRecepcaoCreator(ZipByteUtil zipByteUtil) {
        this.zipByteUtil = zipByteUtil;
    }

    /**
     * Cria um {@link br.gov.to.sefaz.arr.persistence.entity.ArquivoRecepcao}, com os valores
     * {@link br.gov.to.sefaz.arr.persistence.entity.ArquivoRecepcao#quantidadeDocs} = 0;
     * {@link br.gov.to.sefaz.arr.persistence.entity.ArquivoRecepcao#situacao} = 2, onde a situação
     * representa Não Processado;
     * {@link br.gov.to.sefaz.arr.persistence.entity.ArquivoRecepcao#valorTotal} = 0.
     *
     * @param file       arquivo que será processado
     * @param lineHeader linha do HEADER do arquivo
     * @param fileHeader {@link br.gov.to.sefaz.arr.processamento.domain.header.FileHeader} que contém informações
     *                   necessárias para a criação do
     *                   {@link br.gov.to.sefaz.arr.persistence.entity.ArquivoRecepcao}
     * @return um {@link br.gov.to.sefaz.arr.persistence.entity.ArquivoRecepcao} com os atraibutos fornecidos
     * @throws IOException caso ocorra algum erro quando comprimir o arquivo para armazenar na base de dados
     */
    public ArquivoRecepcao createArquivoRecepcao(FileInputStream file, String fileName, String lineHeader, FileHeader
            fileHeader, SituacaoArquivoEnum situacao) throws IOException {
        Integer idBanco = null;
        Long idConvenio = null;
        Long sequencialArquivoNsa = null;
        LocalDate dataArquivo = null;

        if (fileHeader != null) {
            idBanco = fileHeader.getBanco();
            idConvenio = fileHeader.getCodigoConvenio();
            sequencialArquivoNsa = fileHeader.getSequencialArquivoNsa();
            dataArquivo = fileHeader.getDataGeracaoArquivo();
        }

        FileTypeEnum tipoArquivo = null;

        if (lineHeader != null) {
            tipoArquivo = FileTypeEnum.valueBy(lineHeader);
        }

        return new ArquivoRecepcao(fileName, tipoArquivo, fileHeader.getTipoArquivo(), dataArquivo,
                lineHeader, situacao, QUANTIDADE_DOCS, VALOR_TOTAL, createZipFile(file, fileName),
                sequencialArquivoNsa, idBanco, idConvenio);
    }

    private byte[] createZipFile(FileInputStream file, String fileName) throws IOException {
        String fileString = new BufferedReader(new InputStreamReader(file))
                .lines().collect(Collectors.joining("\n"));

        return zipByteUtil.zipBytes(fileName, fileString.getBytes());
    }

}
