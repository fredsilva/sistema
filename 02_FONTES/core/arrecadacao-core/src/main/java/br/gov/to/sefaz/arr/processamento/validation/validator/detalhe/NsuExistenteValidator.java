package br.gov.to.sefaz.arr.processamento.validation.validator.detalhe;

import br.gov.to.sefaz.arr.persistence.entity.ArquivoDetalhePagos;
import br.gov.to.sefaz.arr.processamento.domain.str.TipoRejeicaoEnum;
import br.gov.to.sefaz.arr.processamento.process.content.util.FileContentUtil;

/**
 * Validador para verificar se uma linha do arquivo já havia sido processada.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 19/07/2016 15:22:00
 */
public class NsuExistenteValidator implements DetalheValidator {

    private final ArquivoDetalhePagos arquivoDetalhePagos;
    private final FileContentUtil fileContentUtil;

    public NsuExistenteValidator(ArquivoDetalhePagos arquivoDetalhePagos, FileContentUtil fileContentUtil) {
        this.arquivoDetalhePagos = arquivoDetalhePagos;
        this.fileContentUtil = fileContentUtil;
    }

    @Override
    public boolean isValid() {
        return !fileContentUtil.isLineAlreadyProcessed(arquivoDetalhePagos);
    }

    @Override
    public int getCodigoErro() {
        return TipoRejeicaoEnum.NSU_DUPLICADO.getCode();
    }
}
