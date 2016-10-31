package br.gov.to.sefaz.arr.processamento.process;

import br.gov.to.sefaz.arr.persistence.entity.ArquivoRecepcao;
import br.gov.to.sefaz.arr.persistence.enums.SituacaoArquivoEnum;
import br.gov.to.sefaz.arr.processamento.ProcessArquivo;
import br.gov.to.sefaz.arr.processamento.creator.ArquivoRecepcaoCreator;
import br.gov.to.sefaz.arr.processamento.domain.FileContent;
import br.gov.to.sefaz.arr.processamento.domain.header.FileHeader;
import br.gov.to.sefaz.arr.processamento.domain.header.FileHeaderBuilder;
import br.gov.to.sefaz.arr.processamento.service.ArquivoRecepcaoService;
import br.gov.to.sefaz.arr.processamento.type.FileTypeEnum;
import br.gov.to.sefaz.arr.processamento.type.ProcessFileType;
import br.gov.to.sefaz.arr.processamento.type.factory.FileTypeFactory;
import br.gov.to.sefaz.arr.processamento.validation.HeaderValidationSuite;
import br.gov.to.sefaz.arr.processamento.validation.validator.header.HeaderValidator;
import br.gov.to.sefaz.arr.processamento.validation.validator.header.simplesnacional.NumeroSeqForaSequenciaValidator;
import br.gov.to.sefaz.exception.file.ProcessFileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Realiza o processamento de arquivos de arrecadação.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 22/06/2016 16:08:00
 */
@Component
public class ProcessArquivoArrecadacao implements ProcessArquivo {

    private final HeaderValidationSuite headerValidationSuite;
    private final FileTypeFactory fileTypeFactory;
    private final ArquivoRecepcaoCreator arquivoRecepcaoCreator;
    private final ArquivoRecepcaoService arquivoRecepcaoService;

    @Autowired
    public ProcessArquivoArrecadacao(HeaderValidationSuite headerValidationSuite, FileTypeFactory fileTypeFactory,
            ArquivoRecepcaoCreator arquivoRecepcaoCreator, ArquivoRecepcaoService arquivoRecepcaoService) {
        this.headerValidationSuite = headerValidationSuite;
        this.fileTypeFactory = fileTypeFactory;
        this.arquivoRecepcaoCreator = arquivoRecepcaoCreator;
        this.arquivoRecepcaoService = arquivoRecepcaoService;
    }

    /**
     * Processa o arquivo conforme as regras de negócio do sistema.
     *
     * @param file arquivo a ser processado
     */
    @SuppressWarnings("PMD.AvoidCatchingGenericException")
    public void processFile(FileInputStream file, String fileName) throws IOException {
        Scanner scanner = new Scanner(file);
        ArquivoRecepcao arquivoRecepcao = null;
        try {
            String lineHeader = scanner.nextLine();
            lineHeader = lineHeader.trim();
            try {
                ProcessFileType processFileType = fileTypeFactory.createProcessFileType(lineHeader);
                List<HeaderValidator> headerValidators = getHeaderValidators(lineHeader, processFileType);

                headerValidationSuite.validate(headerValidators);
                FileHeader fileHeader = getFileHeader(lineHeader, processFileType);

                arquivoRecepcao = createAndSaveArquivoRecepcao(file, fileName, lineHeader, fileHeader);
                FileContent fileContent = getFileContent(scanner, fileHeader, arquivoRecepcao);
                processFileType.processFileContent(fileContent);
            } catch (ProcessFileException e) {
                FileTypeEnum fileTypeEnum = FileTypeEnum.valueBy(lineHeader);
                FileHeader fileHeader = new FileHeader();

                if (fileTypeEnum != null) {
                    ProcessFileType processFileType = fileTypeFactory.createProcessFileType(lineHeader);
                    fileHeader = getFileHeader(lineHeader, processFileType);
                }

                createAndSaveArquivoRecepcaoWithError(file, fileName, lineHeader, fileHeader, e.getCodigoRejeicao());
            }
        } catch (RuntimeException e) {
            if (Objects.isNull(arquivoRecepcao)) {
                createAndSaveArquivoRecepcaoWithError(file, fileName, null, new FileHeader(), null);
            } else {
                ArquivoRecepcao recepcao = arquivoRecepcaoService.findOne(arquivoRecepcao.getIdArquivos());
                recepcao.setSituacao(SituacaoArquivoEnum.PROCESSADO_COM_ERROS);
                arquivoRecepcaoService.update(recepcao);
            }
        } finally {
            scanner.close();
        }
    }

    private ArquivoRecepcao createAndSaveArquivoRecepcao(FileInputStream file, String fileName, String header,
            FileHeader fileHeader) throws IOException {
        ArquivoRecepcao arquivoRecepcao = arquivoRecepcaoCreator.createArquivoRecepcao(file, fileName, header,
                fileHeader, SituacaoArquivoEnum.PROCESSADO);
        arquivoRecepcao.setDataProcessamento(LocalDateTime.now());

        NumeroSeqForaSequenciaValidator nsuSequenceValidator =
                new NumeroSeqForaSequenciaValidator(arquivoRecepcao.getSequencialNsa(),
                        arquivoRecepcao.getIdConvenio(), arquivoRecepcao.getIdBanco(),
                        arquivoRecepcao.getDataArquivo(), arquivoRecepcaoService);

        if (!nsuSequenceValidator.isValid()) {
            arquivoRecepcao.setCodigoRejeicao(nsuSequenceValidator.getCodigoErro());
        }

        return arquivoRecepcaoService.save(arquivoRecepcao);
    }

    private ArquivoRecepcao createAndSaveArquivoRecepcaoWithError(FileInputStream file, String fileName, String
            lineHeader, FileHeader fileHeader, Integer codigoRejeicao) throws IOException {
        ArquivoRecepcao arquivoRecepcao = arquivoRecepcaoCreator.createArquivoRecepcao(file, fileName, lineHeader,
                fileHeader, SituacaoArquivoEnum.NAO_PROCESSADO);
        arquivoRecepcao.setCodigoRejeicao(codigoRejeicao);
        arquivoRecepcao.setDataProcessamento(LocalDateTime.now());

        return arquivoRecepcaoService.save(arquivoRecepcao);
    }

    private FileHeader getFileHeader(String lineHeader, ProcessFileType processFileType) {
        FileHeaderBuilder fileHeaderBuilder = processFileType.getFileHeaderBuilder();

        return fileHeaderBuilder
                .withLineHeader(lineHeader)
                .withAllParameters()
                .build();
    }

    private List<HeaderValidator> getHeaderValidators(String lineHeader, ProcessFileType processFileType) {
        return processFileType
                .getHeaderValidatorBuilder()
                .withLineHeader(lineHeader)
                .withAllValidators()
                .build();
    }

    private FileContent getFileContent(Scanner scanner, FileHeader fileHeader, ArquivoRecepcao arquivoRecepcao) {
        FileContent fileContent = new FileContent(arquivoRecepcao.getId(), fileHeader);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            fileContent.addLine(line.trim());
        }

        return fileContent;
    }

}
