package br.gov.to.sefaz.arr.processamento.domain.detalhe.simplesnacional.builder;

import br.gov.to.sefaz.arr.processamento.domain.detalhe.FileDetalheBuilder;
import br.gov.to.sefaz.arr.processamento.domain.detalhe.simplesnacional.FileDetalheSN;
import br.gov.to.sefaz.util.file.FileLineExtractor;
import br.gov.to.sefaz.util.xml.ConverterUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Implementação do {@link br.gov.to.sefaz.arr.processamento.domain.detalhe.FileDetalheBuilder} para arquivos que
 * possuem como identificador da Linha o valor "G".
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 23/06/2016 11:00:00
 */
public class FileDetalheSNBuilder implements FileDetalheBuilder {

    private final FileLineExtractor fileLineExtractor;
    private final FileDetalheSN fileDetalhe;
    private String detalhe;

    public FileDetalheSNBuilder() {
        this.fileDetalhe = new FileDetalheSN();
        fileLineExtractor = new FileLineExtractor();
    }

    @Override
    public FileDetalheBuilder withLine(String lineContent) {
        this.detalhe = lineContent;
        fileDetalhe.setConteudoLinha(lineContent);
        return this;
    }

    @Override
    public FileDetalheBuilder withAllParameters() {
        return withNumeroSequencial()
                .withNumeroAutenticacao()
                .withCnpjContribuinte()
                .withCodigoBanco()
                .withCodigoAgencia()
                .withDataArrecadacao()
                .withValorPrincipal()
                .withValorMulta()
                .withValorJuros()
                .withValorAutenticacao()
                .withDataVencimento();
    }

    @Override
    public FileDetalheSN build() {
        return fileDetalhe;
    }

    private FileDetalheSNBuilder withNumeroSequencial() {
        String value = fileLineExtractor.getValueFromString(detalhe, 2, 10);
        fileDetalhe.setNumeroSequencial(value);

        return this;
    }

    private FileDetalheSNBuilder withNumeroAutenticacao() {
        String value = fileLineExtractor.getValueFromString(detalhe, 222, 245);
        fileDetalhe.setNumeroAutenticacao(value);

        return this;
    }

    private FileDetalheSNBuilder withCnpjContribuinte() {
        String value = fileLineExtractor.getValueFromString(detalhe, 75, 89);
        fileDetalhe.setCnpjContribuinte(value);

        return this;
    }

    private FileDetalheSNBuilder withCodigoBanco() {
        String value = fileLineExtractor.getValueFromString(detalhe, 245, 248);
        fileDetalhe.setCodigoBanco(Integer.valueOf(value));

        return this;
    }

    private FileDetalheSNBuilder withCodigoAgencia() {
        String value = fileLineExtractor.getValueFromString(detalhe, 248, 252);
        fileDetalhe.setCodigoAgencia(Integer.valueOf(value));

        return this;
    }

    private FileDetalheSNBuilder withDataArrecadacao() {
        String value = fileLineExtractor.getValueFromString(detalhe, 9, 17);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate date = LocalDate.parse(value, formatter);
        fileDetalhe.setDataArrecadacao(date);

        return this;
    }

    private FileDetalheSNBuilder withValorPrincipal() {
        String value = fileLineExtractor.getValueFromString(detalhe, 107, 124);
        fileDetalhe.setValorPrincipal(ConverterUtil.convertBigDecimal(value, 2));

        return this;
    }

    private FileDetalheSNBuilder withValorMulta() {
        String value = fileLineExtractor.getValueFromString(detalhe, 124, 141);
        fileDetalhe.setValorMulta(ConverterUtil.convertBigDecimal(value, 2));

        return this;
    }

    private FileDetalheSNBuilder withValorJuros() {
        String value = fileLineExtractor.getValueFromString(detalhe, 141, 158);
        fileDetalhe.setValorJuros(ConverterUtil.convertBigDecimal(value, 2));

        return this;
    }

    private FileDetalheSNBuilder withValorAutenticacao() {
        String value = fileLineExtractor.getValueFromString(detalhe, 205, 222);
        fileDetalhe.setValorAutenticacao(ConverterUtil.convertBigDecimal(value, 2));

        return this;
    }

    private FileDetalheSNBuilder withDataVencimento() {
        String value = fileLineExtractor.getValueFromString(detalhe, 17, 25);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate date = LocalDate.parse(value, formatter);
        fileDetalhe.setDataVencimento(date);

        return this;
    }
}
