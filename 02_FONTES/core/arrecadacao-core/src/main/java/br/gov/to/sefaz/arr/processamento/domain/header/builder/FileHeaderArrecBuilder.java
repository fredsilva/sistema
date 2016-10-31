package br.gov.to.sefaz.arr.processamento.domain.header.builder;

import br.gov.to.sefaz.arr.parametros.business.service.BancosService;
import br.gov.to.sefaz.arr.parametros.business.service.ConveniosArrecService;
import br.gov.to.sefaz.arr.persistence.entity.Bancos;
import br.gov.to.sefaz.arr.persistence.entity.ConveniosArrec;
import br.gov.to.sefaz.arr.processamento.domain.header.FileHeader;
import br.gov.to.sefaz.arr.processamento.domain.header.FileHeaderBuilder;
import br.gov.to.sefaz.arr.processamento.domain.header.TipoArquivoEnum;
import br.gov.to.sefaz.util.file.FileLineExtractor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Implementação do {@link br.gov.to.sefaz.arr.processamento.domain.header.FileHeaderBuilder} para arquivos que
 * possuem como identificador do HEADER o "A".
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 23/06/2016 10:40:00
 */
@SuppressWarnings("PMD")
public class FileHeaderArrecBuilder implements FileHeaderBuilder {

    private final FileHeader fileHeader;
    private final FileLineExtractor fileLineExtractor;
    private final ConveniosArrecService conveniosArrecService;
    private final BancosService bancosService;
    private String header;

    public FileHeaderArrecBuilder(ConveniosArrecService conveniosArrecService, BancosService bancosService) {
        this.conveniosArrecService = conveniosArrecService;
        this.bancosService = bancosService;
        this.fileHeader = new FileHeader();
        fileLineExtractor = new FileLineExtractor();
    }

    @Override
    public FileHeaderBuilder withLineHeader(String header) {
        this.header = header;

        return this;
    }

    @Override
    public FileHeaderBuilder withAllParameters() {
        return withCodigoConvenio()
                .withSequencialArquivoNsa()
                .withBanco()
                .withAgenciaBanco()
                .withDataGeracaoArquivo()
                .withTipoArquivo();
    }

    @Override
    public FileHeader build() {
        return fileHeader;
    }

    private FileHeaderArrecBuilder withCodigoConvenio() {
        String convenio = fileLineExtractor.getValueFromString(header, 2, 22);
        ConveniosArrec conveniosArrec = conveniosArrecService.findOne(Long.valueOf(convenio));
        Long idConvenio = Objects.isNull(conveniosArrec) ? null : conveniosArrec.getIdConvenio();
        fileHeader.setCodigoConvenio(idConvenio);

        return this;
    }

    private FileHeaderArrecBuilder withSequencialArquivoNsa() {
        String value = fileLineExtractor.getValueFromString(header, 73, 79);
        Long sequencial = Long.valueOf(value);
        fileHeader.setSequencialArquivoNsa(sequencial);

        return this;
    }

    private FileHeaderArrecBuilder withBanco() {
        String banco = fileLineExtractor.getValueFromString(header, 42, 45);
        Integer bancoId = Integer.valueOf(banco);
        Bancos bancos = bancosService.findOne(bancoId);
        Integer idBanco = Objects.isNull(bancos) ? null : bancos.getIdBanco();

        fileHeader.setBanco(idBanco);

        return this;
    }

    private FileHeaderArrecBuilder withAgenciaBanco() {
        ConveniosArrec conveniosArrec = conveniosArrecService.findOne(fileHeader.getCodigoConvenio());
        Integer idAgencia = Objects.isNull(conveniosArrec) ? null : conveniosArrec.getBancoAgencias().getIdAgencia();

        fileHeader.setAgenciaBanco(idAgencia);

        return this;
    }

    private FileHeaderArrecBuilder withDataGeracaoArquivo() {
        String value = fileLineExtractor.getValueFromString(header, 64, 73);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate date = LocalDate.parse(value, formatter);
        fileHeader.setDataGeracaoArquivo(date);

        return this;
    }

    private FileHeaderArrecBuilder withTipoArquivo() {
        String value = fileLineExtractor.getValueFromString(header, 79, 81);
        TipoArquivoEnum tipoArquivo = TipoArquivoEnum.getById(value);
        fileHeader.setTipoArquivo(tipoArquivo);

        return this;
    }
}
