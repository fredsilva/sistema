package br.gov.to.sefaz.arr.processamento.domain.header.builder;

import br.gov.to.sefaz.arr.parametros.business.service.BancosService;
import br.gov.to.sefaz.arr.parametros.business.service.ConveniosArrecService;
import br.gov.to.sefaz.arr.persistence.entity.Bancos;
import br.gov.to.sefaz.arr.persistence.entity.ConveniosArrec;
import br.gov.to.sefaz.arr.persistence.enums.TipoConvenioEnum;
import br.gov.to.sefaz.arr.processamento.domain.header.FileHeader;
import br.gov.to.sefaz.arr.processamento.domain.header.FileHeaderBuilder;
import br.gov.to.sefaz.arr.processamento.domain.header.TipoArquivoEnum;
import br.gov.to.sefaz.util.file.FileLineExtractor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Implementação do {@link br.gov.to.sefaz.arr.processamento.domain.header.FileHeaderBuilder} para arquivos que
 * possuem como identificador do HEADER o "1".
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 24/06/2016 16:09:00
 */
@SuppressWarnings("PMD")
public class FileHeaderSNBuilder implements FileHeaderBuilder {

    private final FileHeader fileHeader;
    private final FileLineExtractor fileLineExtractor;
    private final ConveniosArrecService conveniosArrecService;
    private final BancosService bancosService;
    private String header;

    public FileHeaderSNBuilder(ConveniosArrecService conveniosArrecService, BancosService bancosService) {
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
    public FileHeader build() {
        return fileHeader;
    }

    @Override
    public FileHeaderSNBuilder withAllParameters() {
        return withCodigoConvenio()
                .withSequencialArquivoNsa()
                .withBanco()
                .withAgenciaBanco()
                .withDataGeracaoArquivo()
                .withTipoArquivo();
    }

    private FileHeaderSNBuilder withCodigoConvenio() {
        String banco = fileLineExtractor.getValueFromString(header, 75, 78);
        Integer bancoId = Integer.valueOf(banco);
        ConveniosArrec conveniosArrec =
                conveniosArrecService.findByBancoAndTipoConvenio(bancoId, TipoConvenioEnum.SIMPLES_NACIONAL);
        Long idConvenio = Objects.isNull(conveniosArrec) ? null : conveniosArrec.getIdConvenio();
        fileHeader.setCodigoConvenio(idConvenio);

        return this;
    }

    private FileHeaderSNBuilder withSequencialArquivoNsa() {
        String value = fileLineExtractor.getValueFromString(header, 1, 9);
        Long sequencial = Long.valueOf(value);
        fileHeader.setSequencialArquivoNsa(sequencial);

        return this;
    }

    private FileHeaderSNBuilder withBanco() {
        String banco = fileLineExtractor.getValueFromString(header, 75, 78);
        Integer bancoId = Integer.valueOf(banco);
        Bancos bancos = bancosService.findOne(bancoId);
        Integer idBanco = Objects.isNull(bancos) ? null : bancos.getIdBanco();

        fileHeader.setBanco(idBanco);

        return this;
    }

    private FileHeaderSNBuilder withAgenciaBanco() {
        ConveniosArrec conveniosArrec = conveniosArrecService.findOne(fileHeader.getCodigoConvenio());
        Integer idAgencia = Objects.isNull(conveniosArrec) ? null : conveniosArrec.getBancoAgencias().getIdAgencia();

        fileHeader.setAgenciaBanco(idAgencia);

        return this;
    }

    private FileHeaderSNBuilder withDataGeracaoArquivo() {
        String value = fileLineExtractor.getValueFromString(header, 29, 37);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate date = LocalDate.parse(value, formatter);
        fileHeader.setDataGeracaoArquivo(date);

        return this;
    }

    private FileHeaderSNBuilder withTipoArquivo() {
        TipoArquivoEnum tipoArquivo = TipoArquivoEnum.CONSOLIDADO;
        fileHeader.setTipoArquivo(tipoArquivo);

        return this;
    }
}
