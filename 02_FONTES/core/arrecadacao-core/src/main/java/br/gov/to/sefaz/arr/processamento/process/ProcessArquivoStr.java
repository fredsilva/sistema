package br.gov.to.sefaz.arr.processamento.process;

import br.gov.to.sefaz.arr.persistence.entity.ArquivoErrosStr;
import br.gov.to.sefaz.arr.persistence.entity.ArquivosStr;
import br.gov.to.sefaz.arr.persistence.entity.DetalheStr;
import br.gov.to.sefaz.arr.persistence.enums.TipoReceitaStrEnum;
import br.gov.to.sefaz.arr.processamento.ProcessArquivo;
import br.gov.to.sefaz.arr.processamento.converter.DomainToEntityConverter;
import br.gov.to.sefaz.arr.processamento.converter.str.STR0020ToArquivoStrConverter;
import br.gov.to.sefaz.arr.processamento.domain.str.STR0020;
import br.gov.to.sefaz.arr.processamento.domain.str.SituacaoProcessamentoStrEnum;
import br.gov.to.sefaz.arr.processamento.process.content.conciliacao.ProcessConciliacao;
import br.gov.to.sefaz.arr.processamento.process.content.str.ProcessDetalhesStr;
import br.gov.to.sefaz.arr.processamento.service.ArquivoErrosStrService;
import br.gov.to.sefaz.arr.processamento.service.ArquivosStrService;
import br.gov.to.sefaz.arr.processamento.validation.validator.str.ArquivoStrValidator;
import br.gov.to.sefaz.exception.file.ProcessFileException;
import br.gov.to.sefaz.util.file.ZipByteUtil;
import br.gov.to.sefaz.util.xml.XmlToObjectConverter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementação do serviço {@link br.gov.to.sefaz.arr.processamento.ProcessArquivo} de processamento
 * (leitura, converção, validação, persistência) de um arquivo STR0020.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 07/07/2016 09:30:00
 */
@Component
public class ProcessArquivoStr implements ProcessArquivo {

    private final XmlToObjectConverter<STR0020> xmlToObjectConverter;
    private final DomainToEntityConverter<STR0020, ArquivosStr> domainToEntityConverter;
    private final ArquivosStrService arquivosStrService;
    private final ArquivoErrosStrService arquivoErrosStrService;
    private final ArquivoStrValidator arquivoStrValidator;
    private final ProcessDetalhesStr processDetalhesStr;
    private final ZipByteUtil zipByteUtil;
    private final ProcessConciliacao processConciliacao;

    @Autowired
    ProcessArquivoStr(ArquivosStrService arquivosStrService, ZipByteUtil zipByteUtil,
            ArquivoErrosStrService arquivoErrosStrService, XmlToObjectConverter<STR0020> xmlToObjectConverter,
            STR0020ToArquivoStrConverter str0020ToArquivoStrConverter, ArquivoStrValidator arquivoStrValidator,
            ProcessDetalhesStr processDetalhesStr, ProcessConciliacao processConciliacao) {
        this.arquivosStrService = arquivosStrService;
        this.arquivoErrosStrService = arquivoErrosStrService;
        this.domainToEntityConverter = str0020ToArquivoStrConverter;
        this.xmlToObjectConverter = xmlToObjectConverter;
        this.arquivoStrValidator = arquivoStrValidator;
        this.processDetalhesStr = processDetalhesStr;
        this.zipByteUtil = zipByteUtil;
        this.processConciliacao = processConciliacao;
    }

    @Override
    @SuppressWarnings("PMD.AvoidCatchingGenericException")
    public void processFile(FileInputStream file, String fileName) {
        STR0020 arquivoStr0020Domain = new STR0020();
        ArquivosStr arquivosStrEntity = new ArquivosStr();
        List<DetalheStr> detalhesStr = new ArrayList<>();
        SituacaoProcessamentoStrEnum situacao = SituacaoProcessamentoStrEnum.PROCESSADO;
        try {
            arquivoStr0020Domain = this.xmlToObjectConverter.convert(file, STR0020.class);
            arquivosStrEntity = this.domainToEntityConverter.convert(arquivoStr0020Domain);
            arquivosStrEntity = this.arquivosStrService.save(arquivosStrEntity);
            this.arquivoStrValidator.validateArquivoJaLido(arquivosStrEntity);
            this.arquivoStrValidator.validateCodSefazInvalido(arquivoStr0020Domain);
            detalhesStr = this.processDetalhesStr.processDetalhes(arquivosStrEntity, arquivoStr0020Domain);
        } catch (ProcessFileException e) {
            registerError(arquivosStrEntity, e);
        } catch (Exception e) {
            situacao = SituacaoProcessamentoStrEnum.NAO_PROCESSADO;
            Logger.getLogger(this.getClass()).error("Erro inesperado ao processar arquivo STR20.", e);
        }
        conciliarArquivo(arquivosStrEntity, arquivoStr0020Domain, detalhesStr);
        atualizarArquivoStrWithBlob(arquivosStrEntity, file, fileName);
        atualizarArquivoStrProcessado(arquivosStrEntity, situacao);
    }

    private void conciliarArquivo(ArquivosStr arquivosStrEntity, STR0020 arquivoStr0020Domain,
            List<DetalheStr> detalheStrs) {
        TipoReceitaStrEnum tipoReceitaEnum = getTipoReceitaStr(arquivoStr0020Domain);

        if (TipoReceitaStrEnum.DEMAIS_RECEITAS_TRIBUTARIAS.equals(tipoReceitaEnum)) {
            this.processConciliacao.process(arquivosStrEntity.getDataArrecadacao(), arquivosStrEntity
                    .getIdBancoDebitado(), detalheStrs.get(0).getIdConvenio());
        } else {
            for (DetalheStr detalhe : detalheStrs) {
                this.processConciliacao.process(arquivosStrEntity.getDataArrecadacao(),
                        arquivosStrEntity.getIdBancoDebitado(), detalhe.getIdConvenio());
            }
        }
    }

    private TipoReceitaStrEnum getTipoReceitaStr(STR0020 arquivoStr0020Domain) {
        Integer tipoReceitaCode = Integer.valueOf(arquivoStr0020Domain.getTpReceita());
        return TipoReceitaStrEnum.getValue(tipoReceitaCode);
    }

    private byte[] createZipFile(FileInputStream file, String fileName) {
        String fileString = new BufferedReader(new InputStreamReader(file)).lines().collect(Collectors.joining("\n"));
        try {
            return zipByteUtil.zipBytes(fileName, fileString.getBytes());
        } catch (IOException e) {
            Logger.getLogger(this.getClass()).error("Erro ao zipar arquivo para gravação no banco de dados.", e);
        }
        return new byte[]{};
    }

    private void atualizarArquivoStrWithBlob(ArquivosStr arquivosStrEntity, FileInputStream file, String fileName) {
        arquivosStrEntity.setArquivoStr(createZipFile(file, fileName));
    }

    private void atualizarArquivoStrProcessado(ArquivosStr arquivosStrEntity, SituacaoProcessamentoStrEnum status) {
        arquivosStrEntity.setSituacao(status);
        arquivosStrEntity.setDataProcessamento(LocalDateTime.now());
        this.arquivosStrService.update(arquivosStrEntity);
    }

    private void registerError(ArquivosStr arquivosStrEntity, ProcessFileException e) {
        ArquivoErrosStr arquivoErrosStr = new ArquivoErrosStr();

        arquivoErrosStr.setIdArquivoStr(arquivosStrEntity.getIdArquivoStr());
        arquivoErrosStr.setIdCodigoRejeicao(e.getCodigoRejeicao());
        this.arquivoErrosStrService.save(arquivoErrosStr);
    }

}
