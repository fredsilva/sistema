package br.gov.to.sefaz.arr.processamento.type;

import br.gov.to.sefaz.arr.parametros.business.service.BancosService;
import br.gov.to.sefaz.arr.parametros.business.service.ConveniosArrecService;
import br.gov.to.sefaz.arr.persistence.entity.ArquivoDetalhePagos;
import br.gov.to.sefaz.arr.persistence.entity.ArquivoErro;
import br.gov.to.sefaz.arr.persistence.entity.ArquivoRecepcao;
import br.gov.to.sefaz.arr.persistence.enums.SituacaoArquivoEnum;
import br.gov.to.sefaz.arr.processamento.domain.FileContent;
import br.gov.to.sefaz.arr.processamento.domain.header.FileHeaderBuilder;
import br.gov.to.sefaz.arr.processamento.domain.header.builder.FileHeaderSNBuilder;
import br.gov.to.sefaz.arr.processamento.process.content.ProcessFileContent;
import br.gov.to.sefaz.arr.processamento.process.content.simplesnacional.ProcessFileContentSN;
import br.gov.to.sefaz.arr.processamento.process.content.simplesnacional.ProcessFileTraillerSN;
import br.gov.to.sefaz.arr.processamento.service.ArquivoDetalhePagosService;
import br.gov.to.sefaz.arr.processamento.service.ArquivoErroService;
import br.gov.to.sefaz.arr.processamento.service.ArquivoRecepcaoService;
import br.gov.to.sefaz.arr.processamento.validation.validator.header.HeaderValidatorBuilder;
import br.gov.to.sefaz.arr.processamento.validation.validator.header.simplesnacional.builder.HeaderSNValidatorBuilder;
import br.gov.to.sefaz.util.file.FileLineExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementação do {@link br.gov.to.sefaz.arr.processamento.type.ProcessFileType} para o tipo de arquivo de
 * arrecadação onde a identificação do HEADER é "1".
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 24/06/2016 15:29:00
 */
@Component
public class ProcessFileTypeSN implements ProcessFileType {
    private static final int CONTEM_ERRO = 1;

    private final ConveniosArrecService conveniosArrecService;
    private final FileLineExtractor fileLineExtractor;
    private final BancosService bancosService;
    private final ArquivoRecepcaoService arquivoRecepcaoService;
    private final ArquivoErroService arquivoErroService;
    private final ArquivoDetalhePagosService arquivoDetalhePagosService;
    private Map<String, ProcessFileContent> processFileContentMap;

    @Autowired
    public ProcessFileTypeSN(ProcessFileContentSN processFileContentSN, ProcessFileTraillerSN processFileTraillerSN,
            ConveniosArrecService conveniosArrecService, FileLineExtractor fileLineExtractor,
            BancosService bancosService, ArquivoRecepcaoService arquivoRecepcaoService,
            ArquivoErroService arquivoErroService, ArquivoDetalhePagosService arquivoDetalhePagosService) {
        this.conveniosArrecService = conveniosArrecService;
        this.fileLineExtractor = fileLineExtractor;
        this.bancosService = bancosService;
        this.arquivoRecepcaoService = arquivoRecepcaoService;
        this.arquivoErroService = arquivoErroService;
        this.arquivoDetalhePagosService = arquivoDetalhePagosService;
        createProcessFileContentMap(processFileContentSN, processFileTraillerSN);
    }

    @Override
    public HeaderValidatorBuilder getHeaderValidatorBuilder() {
        return new HeaderSNValidatorBuilder(fileLineExtractor, bancosService, arquivoRecepcaoService,
                conveniosArrecService);
    }

    @Override
    public FileHeaderBuilder getFileHeaderBuilder() {
        return new FileHeaderSNBuilder(conveniosArrecService, bancosService);
    }

    @Override
    public void processFileContent(FileContent fileContent) {
        for (String line : fileContent.getLines()) {
            ProcessFileContent processFileContent = getProcessFileContent(line);

            if (processFileContent != null) {
                processFileContent.process(fileContent);
            } else {
                Long idArquivo = fileContent.getIdArquivo();
                String currentLineContent = fileContent.getCurrentLineContent();
                int currentLine = fileContent.getCurrentLine();
                ArquivoDetalhePagos arquivoDetalhePagos = new ArquivoDetalhePagos(idArquivo, currentLine,
                        null, null, BigDecimal.ZERO, null, BigDecimal.ZERO, BigDecimal.ZERO, null, null);
                ArquivoDetalhePagos detalhePagos = arquivoDetalhePagosService.save(arquivoDetalhePagos);
                ArquivoRecepcao arquivoRecepcao = arquivoRecepcaoService.findOne(idArquivo);
                arquivoRecepcao.setSituacao(SituacaoArquivoEnum.PROCESSADO_COM_ERROS);
                arquivoRecepcaoService.update(arquivoRecepcao);
                lineError(currentLineContent, detalhePagos);
            }
        }
    }

    private void lineError(String currentLineContent, ArquivoDetalhePagos detalhePagos) {
        boolean existsError = arquivoErroService.existsWith(null, detalhePagos.getIdDetalheArquivo());
        if (!existsError) {
            Long idDetalheArquivo = detalhePagos.getIdDetalheArquivo();
            ArquivoErro arquivoErro = new ArquivoErro(currentLineContent, null, idDetalheArquivo);
            arquivoErroService.save(arquivoErro);
            detalhePagos.setErroLinha(CONTEM_ERRO);
            arquivoDetalhePagosService.update(detalhePagos);
        }
    }

    private void createProcessFileContentMap(ProcessFileContentSN processFileContentSN,
            ProcessFileTraillerSN processFileTraillerSN) {
        processFileContentMap = new HashMap<>();
        processFileContentMap.put("2", processFileContentSN);
        processFileContentMap.put("9", processFileTraillerSN);
    }

    private ProcessFileContent getProcessFileContent(String line) {
        return processFileContentMap.entrySet()
                .stream()
                .filter(e -> line.startsWith(e.getKey()))
                .findFirst()
                .map(Map.Entry::getValue)
                .orElse(null);
    }
}
