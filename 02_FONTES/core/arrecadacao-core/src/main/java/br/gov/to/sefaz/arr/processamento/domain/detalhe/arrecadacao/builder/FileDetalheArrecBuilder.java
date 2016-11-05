package br.gov.to.sefaz.arr.processamento.domain.detalhe.arrecadacao.builder;

import br.gov.to.sefaz.arr.processamento.domain.detalhe.FileDetalhe;
import br.gov.to.sefaz.arr.processamento.domain.detalhe.FileDetalheBuilder;
import br.gov.to.sefaz.arr.processamento.domain.detalhe.arrecadacao.FileDetalheArrec;
import br.gov.to.sefaz.util.file.FileLineExtractor;
import br.gov.to.sefaz.util.xml.ConverterUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Implementação do {@link br.gov.to.sefaz.arr.processamento.domain.detalhe.FileDetalheBuilder} para arquivos que
 * possuem como identificador da Linha o valor "G".
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 23/06/2016 11:00:00
 */
public class FileDetalheArrecBuilder implements FileDetalheBuilder {

    private final FileLineExtractor fileLineExtractor;
    private final FileDetalheArrec fileDetalhe;
    private String detalhe;

    public FileDetalheArrecBuilder() {
        this.fileDetalhe = new FileDetalheArrec();
        fileLineExtractor = new FileLineExtractor();
    }

    @Override
    public FileDetalheBuilder withLine(String line) {
        this.detalhe = line;
        fileDetalhe.setConteudoLinha(line);

        return this;
    }

    @Override
    public FileDetalheBuilder withAllParameters() {
        return withNumeroSequencial()
                .withNumeroAutenticacao()
                .withDataPagamento()
                .withCodigoBarras()
                .withValorAutenticado()
                .withFormaArrecadacao()
                .withFormaPagamento()
                .withDataTransacao()
                .withDataCredito();
    }

    @Override
    public FileDetalhe build() {
        return fileDetalhe;
    }

    private FileDetalheArrecBuilder withNumeroSequencial() {
        String value = fileLineExtractor.getValueFromString(detalhe, 100, 108);
        fileDetalhe.setNumeroSequencial(value);

        return this;
    }

    private FileDetalheArrecBuilder withNumeroAutenticacao() {
        String value = fileLineExtractor.getValueFromString(detalhe, 117, 140);
        fileDetalhe.setNumeroAutenticacao(value);

        return this;
    }

    private FileDetalheArrecBuilder withDataPagamento() {
        String value = fileLineExtractor.getValueFromString(detalhe, 21, 29);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate date = LocalDate.parse(value, formatter);
        fileDetalhe.setDataPagamento(date);

        return this;
    }

    private FileDetalheArrecBuilder withCodigoBarras() {
        String value = fileLineExtractor.getValueFromString(detalhe, 37, 81);
        fileDetalhe.setCodigoBarras(value);

        return this;
    }

    private FileDetalheArrecBuilder withValorAutenticado() {
        String value = fileLineExtractor.getValueFromString(detalhe, 81, 93);
        fileDetalhe.setValorAutenticado(ConverterUtil.convertBigDecimal(value, 2));

        return this;
    }

    private FileDetalheArrecBuilder withFormaArrecadacao() {
        String value = fileLineExtractor.getValueFromString(detalhe, 116, 117);
        fileDetalhe.setFormaArrecadacao(value);

        return this;
    }

    private FileDetalheArrecBuilder withFormaPagamento() {
        String value = fileLineExtractor.getValueFromString(detalhe, 140, 141);
        fileDetalhe.setFormaPagamento(value);

        return this;
    }

    private FileDetalheArrecBuilder withDataTransacao() {
        String value = fileLineExtractor.getValueFromString(detalhe, 7, 21);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime date = LocalDateTime.parse(value, formatter);
        fileDetalhe.setDataTransacao(date);

        return this;
    }

    private FileDetalheArrecBuilder withDataCredito() {
        String value = fileLineExtractor.getValueFromString(detalhe, 29, 37);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate date = LocalDate.parse(value, formatter);
        fileDetalhe.setDataCredito(date);

        return this;
    }
}
