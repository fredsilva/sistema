package br.gov.to.sefaz.arr.processamento.process.content.simplesnacional;

import br.gov.to.sefaz.arr.persistence.entity.ArquivoDetalhePagos;
import br.gov.to.sefaz.arr.processamento.creator.ArquivoDetalhePagosSNCreator;
import br.gov.to.sefaz.arr.processamento.domain.FileContent;
import br.gov.to.sefaz.arr.processamento.domain.detalhe.simplesnacional.FileDetalheSN;
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
 * para o tipo de arquivo de arrecadação, onde a identificação do conteúdo é representado pelo "2".
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 24/06/2016 16:17:00
 */
@SuppressWarnings("PMD")
@Component
public class ProcessFileContentSN implements ProcessFileContent {

    private final FileContentExtractor fileContentExtractor;
    private final ArquivoDetalhePagosSNCreator arquivoDetalhePagosSNCreator;
    private final ProcessPagamentoLinhaSN processPagamentoLinhaSN;
    private final ArquivoDetalhePagosService arquivoDetalhePagosService;
    private final DetalheValidationSuite validationSuite;
    private final FileContentUtil fileContentUtil;
    private final ProcessFileLineError processFileLineError;

    @Autowired
    public ProcessFileContentSN(FileContentExtractor fileContentExtractor,
            ArquivoDetalhePagosSNCreator arquivoDetalhePagosSNCreator,
            ProcessPagamentoLinhaSN processPagamentoLinhaSN, ArquivoDetalhePagosService arquivoDetalhePagosService,
            DetalheValidationSuite validationSuite, FileContentUtil fileContentUtil,
            ProcessFileLineError processFileLineError) {
        this.fileContentExtractor = fileContentExtractor;
        this.arquivoDetalhePagosSNCreator = arquivoDetalhePagosSNCreator;
        this.processPagamentoLinhaSN = processPagamentoLinhaSN;
        this.arquivoDetalhePagosService = arquivoDetalhePagosService;
        this.validationSuite = validationSuite;
        this.fileContentUtil = fileContentUtil;
        this.processFileLineError = processFileLineError;
    }

    @Override
    public void process(FileContent fileContent) {
        String currentLineContent = fileContent.getCurrentLineContent();
        ArquivoDetalhePagos arquivoDetalhePagos = null;

        try {
            FileDetalheSN fileDetalheSN = fileContentExtractor.getFileDetalheSN(currentLineContent);
            arquivoDetalhePagos = createAndSaveArquivoDetalhePagos(fileContent, fileDetalheSN);
            validateDetalhe(arquivoDetalhePagos);
            processPagamentoLinhaSN.processPagamento(arquivoDetalhePagos, fileDetalheSN);
        } catch (ProcessFileDetalheException e) {
            arquivoDetalhePagos = Objects.isNull(arquivoDetalhePagos) ? createAndSaveArquivoDetalhePagos(fileContent,
                    (FileDetalheSN) e.getFileDetalhe()) : arquivoDetalhePagos;
            fileLineError(arquivoDetalhePagos, currentLineContent, e.getCodigoRejeicao());
        } catch (RuntimeException e) {
            arquivoDetalhePagos = Objects.isNull(arquivoDetalhePagos) ? createAndSaveArquivoDetalhePagos(fileContent,
                    new FileDetalheSN()) : arquivoDetalhePagos;
            fileLineError(arquivoDetalhePagos, currentLineContent, null);
        }
    }

    private ArquivoDetalhePagos createAndSaveArquivoDetalhePagos(FileContent fileContent, FileDetalheSN fileDetalheSN) {
        ArquivoDetalhePagos arquivoDetalhePagos = arquivoDetalhePagosSNCreator.create(fileContent, fileDetalheSN);

        return arquivoDetalhePagosService.save(arquivoDetalhePagos);
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

}
