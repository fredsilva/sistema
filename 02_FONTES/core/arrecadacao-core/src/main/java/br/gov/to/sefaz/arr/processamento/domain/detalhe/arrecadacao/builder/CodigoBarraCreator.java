package br.gov.to.sefaz.arr.processamento.domain.detalhe.arrecadacao.builder;

import br.gov.to.sefaz.arr.persistence.enums.TipoConvenioEnum;
import br.gov.to.sefaz.arr.processamento.domain.detalhe.arrecadacao.CodigoBarra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Cria um {@link br.gov.to.sefaz.arr.processamento.domain.detalhe.arrecadacao.CodigoBarra} conforme tipo DARE ou
 * GNRE através da linha de código de barra da linha do arquivo de arrecadação.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 12/07/2016 17:18:00
 */
@Component
public class CodigoBarraCreator {

    private final CodigoBarrasExtractor extractor;

    @Autowired
    public CodigoBarraCreator(CodigoBarrasExtractor extractor) {
        this.extractor = extractor;
    }

    /**
     * Cria um {@link br.gov.to.sefaz.arr.processamento.domain.detalhe.arrecadacao.CodigoBarra} conforme os valores
     * extraídos da linha de código de barra do arquivo de arrecadação.
     *
     * @param codigoBarra código de barras da linha do detalhe do arquivo
     * @return {@link br.gov.to.sefaz.arr.processamento.domain.detalhe.arrecadacao.CodigoBarra} conforme os valores
     *     extraídos do codigoBarra
     */
    public CodigoBarra createDare(String codigoBarra) {
        TipoConvenioEnum tipoConvenio = extractor.getTipoConvenio(codigoBarra);
        BigDecimal valorTotal = extractor.getValorTotal(codigoBarra);
        int versaoDare = extractor.getVersaoDare(codigoBarra);
        int sistemaEmissor = extractor.getSistemaEmissor(codigoBarra);
        String dataVencimento = extractor.getDataVencimento(codigoBarra);
        String nossoNumero = extractor.getNossoNumero(codigoBarra);

        return new CodigoBarra(tipoConvenio, valorTotal, versaoDare, sistemaEmissor, dataVencimento, nossoNumero);
    }

    /**
     * Cria um {@link br.gov.to.sefaz.arr.processamento.domain.detalhe.arrecadacao.CodigoBarra} conforme os valores
     * extraídos da linha de código de barra do arquivo de arrecadação. Quando este for do tipo GNRE.
     *
     * @param codigoBarra código de barras da linha do detalhe do arquivo
     * @return {@link br.gov.to.sefaz.arr.processamento.domain.detalhe.arrecadacao.CodigoBarra} conforme os valores
     *     extraídos do codigoBarra
     */
    public CodigoBarra createGnre(String codigoBarra) {
        TipoConvenioEnum tipoConvenio = extractor.getTipoConvenio(codigoBarra);
        String nossoNumero = extractor.getNossoNumero(codigoBarra);

        return new CodigoBarra(tipoConvenio, null, null, null, null, nossoNumero);
    }
}
