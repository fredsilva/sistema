package br.gov.to.sefaz.arr.processamento.process.content.arrecadacao;

import br.gov.to.sefaz.arr.persistence.entity.ArquivoDetalhePagos;
import br.gov.to.sefaz.arr.processamento.creator.ArquivoDetalhePagosArrecCreator;
import br.gov.to.sefaz.arr.processamento.domain.FileContent;
import br.gov.to.sefaz.arr.processamento.domain.detalhe.arrecadacao.FileDetalheArrec;
import br.gov.to.sefaz.arr.processamento.exception.ProcessFileDetalheException;
import br.gov.to.sefaz.arr.processamento.process.ProcessFileLineError;
import br.gov.to.sefaz.arr.processamento.process.content.ProcessFileContent;
import br.gov.to.sefaz.arr.processamento.process.content.util.FileContentExtractor;
import br.gov.to.sefaz.arr.processamento.process.content.util.FileContentUtil;
import br.gov.to.sefaz.arr.processamento.service.ArquivoDetalhePagosService;
import br.gov.to.sefaz.arr.processamento.validation.DetalheValidationSuite;
import br.gov.to.sefaz.arr.processamento.validation.validator.detalhe.DetalheValidator;
import br.gov.to.sefaz.arr.processamento.validation.validator.detalhe.NsuExistenteValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Implementação do {@link br.gov.to.sefaz.arr.processamento.process.content.ProcessFileContent}
 * para o tipo de arquivo de arrecadação, onde a identificação do conteúdo é representado pelo "G".
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 23/06/2016 13:46:00
 */
@SuppressWarnings("PMD")
@Component
public class ProcessFileContentArrec implements ProcessFileContent {

    private final FileContentExtractor fileContentExtractor;
    private final FileContentUtil fileContentUtil;
    private final ProcessFileLineError processFileLineError;
    private final ArquivoDetalhePagosArrecCreator arquivoDetalhePagosArrecCreator;
    private final ArquivoDetalhePagosService arquivoDetalhePagosService;
    private final DetalheValidationSuite validationSuite;
    private final ProcessPagamentoLinhaArrec processPagamentoLinha;

    @Autowired
    public ProcessFileContentArrec(ProcessFileLineError processFileLineError,
            ArquivoDetalhePagosArrecCreator arquivoDetalhePagosArrecCreator,
            ArquivoDetalhePagosService arquivoDetalhePagosService,
            FileContentExtractor fileContentExtractor, FileContentUtil fileContentUtil,
            DetalheValidationSuite validationSuite,
            ProcessPagamentoLinhaArrec processPagamentoLinha) {
        this.processFileLineError = processFileLineError;
        this.arquivoDetalhePagosArrecCreator = arquivoDetalhePagosArrecCreator;
        this.arquivoDetalhePagosService = arquivoDetalhePagosService;
        this.fileContentExtractor = fileContentExtractor;
        this.fileContentUtil = fileContentUtil;
        this.validationSuite = validationSuite;
        this.processPagamentoLinha = processPagamentoLinha;
    }

    @Override
    public void process(FileContent fileContent) {
        String currentLineContent = fileContent.getCurrentLineContent();
        ArquivoDetalhePagos arquivoDetalhePagos = null;

        try {
            FileDetalheArrec fileDetalheArrec = fileContentExtractor.getFileDetalheArrec(currentLineContent);
            arquivoDetalhePagos = createAndSaveArquivoDetalhePagos(fileContent, fileDetalheArrec);
            validateDetalhe(arquivoDetalhePagos);
            processPagamentoLinha.processPagamento(arquivoDetalhePagos, fileDetalheArrec);
        } catch (ProcessFileDetalheException e) {
            arquivoDetalhePagos = Objects.isNull(arquivoDetalhePagos) ? createAndSaveArquivoDetalhePagos(fileContent,
                    (FileDetalheArrec) e.getFileDetalhe()) : arquivoDetalhePagos;
            fileLineError(arquivoDetalhePagos, currentLineContent, e.getCodigoRejeicao());
        } catch (RuntimeException e) {
            arquivoDetalhePagos = Objects.isNull(arquivoDetalhePagos) ? createAndSaveArquivoDetalhePagos(fileContent,
                    new FileDetalheArrec()) : arquivoDetalhePagos;
            fileLineError(arquivoDetalhePagos, currentLineContent, null);
        }

    }

    private void fileLineError(ArquivoDetalhePagos arquivoDetalhePagos, String conteudoLinha, Integer codigoRejeicao) {
        processFileLineError.processErroArquivo(conteudoLinha, codigoRejeicao, arquivoDetalhePagos);
    }

    private void validateDetalhe(ArquivoDetalhePagos arquivoDetalhePagos) {
        NsuExistenteValidator nsuExistenteValidator = new NsuExistenteValidator(arquivoDetalhePagos, fileContentUtil);
        List<DetalheValidator> detalheValidators = new ArrayList<>();
        detalheValidators.add(nsuExistenteValidator);

        validationSuite.validate(detalheValidators);
    }

    private ArquivoDetalhePagos createAndSaveArquivoDetalhePagos(FileContent fileContent,
            FileDetalheArrec fileDetalheArrec) {
        ArquivoDetalhePagos arquivoDetalhePagos = arquivoDetalhePagosArrecCreator.create(fileContent, fileDetalheArrec);

        return arquivoDetalhePagosService.save(arquivoDetalhePagos);
    }
}
