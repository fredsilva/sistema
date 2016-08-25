package br.gov.to.sefaz.arr.processamento.process.content.util;

import br.gov.to.sefaz.arr.processamento.domain.detalhe.arrecadacao.FileDetalheArrec;
import br.gov.to.sefaz.arr.processamento.domain.detalhe.arrecadacao.builder.FileDetalheArrecBuilder;
import br.gov.to.sefaz.arr.processamento.domain.detalhe.simplesnacional.FileDetalheSN;
import br.gov.to.sefaz.arr.processamento.domain.detalhe.simplesnacional.builder.FileDetalheSNBuilder;
import br.gov.to.sefaz.arr.processamento.exception.ProcessFileDetalheException;
import org.springframework.stereotype.Component;

/**
 * Extrai as informações necessárias do {@link br.gov.to.sefaz.arr.processamento.domain.FileContent} para uma
 * implementação de {@link br.gov.to.sefaz.arr.processamento.domain.detalhe.FileDetalhe}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 18/07/2016 16:38:00
 */
@Component
@SuppressWarnings({"PMD.AvoidCatchingGenericException", "PMD.PreserveStackTrace"})
public class FileContentExtractor {

    /**
     * Extrai as informações da linha corrente do {@link br.gov.to.sefaz.arr.processamento.domain.FileContent} e as
     * transforma em {@link br.gov.to.sefaz.arr.processamento.domain.detalhe.simplesnacional.FileDetalheSN}.
     *
     * @param currentLineContent contéudo do arquivo, que contém a linha corrente, a qual será criado um
     *                           {@link br.gov.to.sefaz.arr.processamento.domain.detalhe.simplesnacional.FileDetalheSN}
     * @return um {@link br.gov.to.sefaz.arr.processamento.domain.detalhe.simplesnacional.FileDetalheSN} referente as
     *     informações da linha corrente do contúdo do arquivo.
     */
    public FileDetalheSN getFileDetalheSN(String currentLineContent) {
        FileDetalheSNBuilder fileDetalheBuilder = new FileDetalheSNBuilder();

        try {
            return (FileDetalheSN) fileDetalheBuilder
                    .withLine(currentLineContent)
                    .withAllParameters()
                    .build();
        } catch (RuntimeException r) {
            throw new ProcessFileDetalheException(15, fileDetalheBuilder.build());
        }
    }

    /**
     * Extrai as informações da linha corrente do {@link br.gov.to.sefaz.arr.processamento.domain.FileContent} e as
     * transforma em {@link br.gov.to.sefaz.arr.processamento.domain.detalhe.arrecadacao.FileDetalheArrec}.
     *
     * @param currentLineContent contéudo do arquivo, que contém a linha corrente, a qual será criado um
     *                    {@link br.gov.to.sefaz.arr.processamento.domain.detalhe.arrecadacao.FileDetalheArrec}
     * @return um {@link br.gov.to.sefaz.arr.processamento.domain.detalhe.arrecadacao.FileDetalheArrec} referente as
     *     informações da linha corrente do contúdo do arquivo.
     */
    public FileDetalheArrec getFileDetalheArrec(String currentLineContent) {
        FileDetalheArrecBuilder fileDetalheBuilder = new FileDetalheArrecBuilder();

        try {
            return (FileDetalheArrec) fileDetalheBuilder
                    .withLine(currentLineContent)
                    .withAllParameters()
                    .build();
        } catch (RuntimeException r) {
            throw new ProcessFileDetalheException(15, fileDetalheBuilder.build());
        }
    }

}
