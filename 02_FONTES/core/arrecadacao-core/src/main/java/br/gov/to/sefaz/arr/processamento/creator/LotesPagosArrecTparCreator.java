package br.gov.to.sefaz.arr.processamento.creator;

import br.gov.to.sefaz.arr.persistence.entity.LotesPagosArrec;
import br.gov.to.sefaz.arr.persistence.enums.EstadoLoteEnum;
import br.gov.to.sefaz.arr.persistence.enums.TipoLotePagosEnum;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Cria um {@link br.gov.to.sefaz.arr.persistence.entity.LotesPagosArrec} referente ao TPAR para
 * processamento de arquivo de arrecadação.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 07/07/2016 11:39:00
 */
@Component
public class LotesPagosArrecTparCreator {

    /**
     * Cria um {@link br.gov.to.sefaz.arr.persistence.entity.LotesPagosArrec} referente a TPAR, conforme os
     * parâmetros fornecidos.
     *
     * @param codigoConvenio  código do Convênio de Arrecadação com a agência e banco
     * @param codigoBanco     código do Banco
     * @param codigoAgencia   código da Agência Bancária
     * @param dataArrecadacao Data da arrecadação.
     * @return um {@link br.gov.to.sefaz.arr.persistence.entity.LotesPagosArrec} referente ao TPAR do
     *     arquivo.
     */
    public LotesPagosArrec createTpar(Long codigoConvenio, Integer codigoBanco, Integer codigoAgencia,
            LocalDateTime dataArrecadacao) {
        int quantidadeRecepcionado = 0;
        BigDecimal valorRecepcionado = BigDecimal.ZERO;
        int quantidadeDocs = 0;
        BigDecimal valorLote = BigDecimal.ZERO;
        int quantidadeErros = 0;
        BigDecimal valorErros = BigDecimal.ZERO;

        return new LotesPagosArrec(LocalDateTime.now(), TipoLotePagosEnum.TPAR, EstadoLoteEnum.ABERTO,
                dataArrecadacao, codigoConvenio, codigoBanco, codigoAgencia, quantidadeRecepcionado, valorRecepcionado,
                quantidadeDocs, valorLote, quantidadeErros, valorErros);
    }
}
