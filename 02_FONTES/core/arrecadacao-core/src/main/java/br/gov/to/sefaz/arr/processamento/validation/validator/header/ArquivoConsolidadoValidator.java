package br.gov.to.sefaz.arr.processamento.validation.validator.header;

import br.gov.to.sefaz.arr.processamento.domain.header.TipoArquivoEnum;
import br.gov.to.sefaz.arr.processamento.domain.str.TipoRejeicaoEnum;
import br.gov.to.sefaz.arr.processamento.service.ArquivoRecepcaoService;

import java.time.LocalDate;

/**
 * Validador que verifica se já existe um arquivo processado com o tipo consolidado com o mesmo banco, convenio e data
 * de geração de arquivo.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 21/07/2016 18:11:00
 */
public class ArquivoConsolidadoValidator implements HeaderValidator {

    private final TipoArquivoEnum tipoArquivo;
    private final Integer bancoId;
    private final Long codigoConvenio;
    private final LocalDate dataGeracaoArquivo;
    private final ArquivoRecepcaoService arquivoRecepcaoService;

    public ArquivoConsolidadoValidator(TipoArquivoEnum tipoArquivo, Integer bancoId, Long codigoConvenio,
            LocalDate dataGeracaoArquivo, ArquivoRecepcaoService arquivoRecepcaoService) {
        this.tipoArquivo = tipoArquivo;
        this.bancoId = bancoId;
        this.codigoConvenio = codigoConvenio;
        this.dataGeracaoArquivo = dataGeracaoArquivo;
        this.arquivoRecepcaoService = arquivoRecepcaoService;
    }

    @Override
    public boolean isValid() {
        return !tipoArquivo.equals(TipoArquivoEnum.CONSOLIDADO)
                || !existsArquivoConsolidado(bancoId, codigoConvenio, dataGeracaoArquivo);
    }

    @Override
    public int getCodigoErro() {
        return TipoRejeicaoEnum.COD_CONV_BANC_N_LOCALIZADO.getCode();
    }

    private boolean existsArquivoConsolidado(Integer bancoId, Long codigoConvenio, LocalDate dataGeracaoArquivo) {
        return arquivoRecepcaoService.existsArquivoConsolidadoWith(bancoId, dataGeracaoArquivo, codigoConvenio);
    }

}
