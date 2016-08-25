package br.gov.to.sefaz.arr.processamento.process.content.arrecadacao;

import br.gov.to.sefaz.arr.persistence.entity.ArquivoRecepcao;
import br.gov.to.sefaz.arr.processamento.domain.FileContent;
import br.gov.to.sefaz.arr.processamento.domain.header.FileHeader;
import br.gov.to.sefaz.arr.processamento.domain.str.TipoRejeicaoEnum;
import br.gov.to.sefaz.arr.processamento.process.content.ProcessFileContent;
import br.gov.to.sefaz.arr.processamento.process.content.conciliacao.ProcessConciliacao;
import br.gov.to.sefaz.arr.processamento.service.ArquivoRecepcaoService;
import br.gov.to.sefaz.util.file.FileLineExtractor;
import br.gov.to.sefaz.util.xml.ConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Implementação do {@link br.gov.to.sefaz.arr.processamento.process.content.ProcessFileContent}
 * para o tipo de arquivo de arrecadação, onde a identificação do conteúdo é representado pelo "Z".
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 23/06/2016 13:46:00
 */
@Component
public class ProcessFileTraillerArrec implements ProcessFileContent {

    private final ProcessConciliacao processConciliacao;
    private final FileLineExtractor fileLineExtractor;
    private final ArquivoRecepcaoService arquivoRecepcaoService;

    @Autowired
    public ProcessFileTraillerArrec(ProcessConciliacao processConciliacao, FileLineExtractor fileLineExtractor,
            ArquivoRecepcaoService arquivoRecepcaoService) {
        this.processConciliacao = processConciliacao;
        this.fileLineExtractor = fileLineExtractor;
        this.arquivoRecepcaoService = arquivoRecepcaoService;
    }

    @Override
    public void process(FileContent fileContent) {
        FileHeader fileHeader = fileContent.getFileHeader();

        String trailler = fileContent.getCurrentLineContent().trim();

        int totalRegistro = Integer.valueOf(fileLineExtractor.getValueFromString(trailler, 1, 7)) - 2;
        String valor = fileLineExtractor.getValueFromString(trailler, 7, 24);
        BigDecimal valorTotal = ConverterUtil.convertBigDecimal(valor, 2);

        ArquivoRecepcao arquivoRecepcao = arquivoRecepcaoService.findOne(fileContent.getIdArquivo());

        if (!(Objects.equals(arquivoRecepcao.getValorTotal(), valorTotal)
                || arquivoRecepcao.getQuantidadeDocs() == totalRegistro)) {
            arquivoRecepcao.setCodigoRejeicao(TipoRejeicaoEnum.TRAILLER_DIVERGENTE.getCode());
        }

        arquivoRecepcao.setTrailerArquivo(trailler);
        arquivoRecepcaoService.update(arquivoRecepcao);

        processConciliacao.process(fileHeader.getDataGeracaoArquivo(),
                fileHeader.getBanco(), fileHeader.getCodigoConvenio());
    }

}