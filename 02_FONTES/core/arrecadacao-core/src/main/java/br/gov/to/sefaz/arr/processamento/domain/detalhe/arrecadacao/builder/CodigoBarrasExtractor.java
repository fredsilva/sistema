package br.gov.to.sefaz.arr.processamento.domain.detalhe.arrecadacao.builder;

import br.gov.to.sefaz.arr.persistence.enums.TipoConvenioEnum;
import br.gov.to.sefaz.util.file.FileLineExtractor;
import br.gov.to.sefaz.util.xml.ConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Extrai os valores do código de barras conforme o Layout do Arquivo de Arrecadação.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 18/07/2016 11:28:00
 */
@Component
public class CodigoBarrasExtractor {

    private final FileLineExtractor fileLineExtractor;

    @Autowired
    public CodigoBarrasExtractor(FileLineExtractor fileLineExtractor) {
        this.fileLineExtractor = fileLineExtractor;
    }

    /**
     * Extrai o tipo de convênio do código de barras fornecido.
     *
     * @param codigoBarra que será extraída a informação
     * @return o {@link br.gov.to.sefaz.arr.persistence.enums.TipoConvenioEnum} conforme a informação
     *     extraída do código de barras
     */
    public TipoConvenioEnum getTipoConvenio(String codigoBarra) {
        String tipoConvenio = fileLineExtractor.getValueFromString(codigoBarra, 15, 19);
        return TipoConvenioEnum.getValue(Integer.valueOf(tipoConvenio));
    }

    /**
     * Extrai o valor total do código de barras fornecido.
     *
     * @param codigoBarra que será extraída a informação.
     * @return o Valor Total conforme a informação extraída do código de barras
     */
    public BigDecimal getValorTotal(String codigoBarra) {
        String valorTotal = fileLineExtractor.getValueFromString(codigoBarra, 5, 15);
        return ConverterUtil.convertBigDecimal(valorTotal, 2);
    }


    /**
     * Extrai a versão do código de barras fornecido.
     *
     * @param codigoBarra que será extraída a informação.
     * @return a versão conforme a informação extraída do código de barras
     */
    public int getVersaoDare(String codigoBarra) {
        String versaoDare = fileLineExtractor.getValueFromString(codigoBarra, 19, 20);
        return Integer.valueOf(versaoDare);
    }

    /**
     * Extrai o sistema emissor do código de barras fornecido.
     *
     * @param codigoBarra que será extraída a informação.
     * @return o sistema emissor conforme a informação extraída do código de barras
     */
    public int getSistemaEmissor(String codigoBarra) {
        String sistemaEmissor = fileLineExtractor.getValueFromString(codigoBarra, 20, 21);
        return Integer.valueOf(sistemaEmissor);
    }

    /**
     * Extrai a data de vencimento do código de barras fornecido.
     *
     * @param codigoBarra que será extraída a informação.
     * @return a data de vencimento conforme a informação extraída do código de barras
     */
    public String getDataVencimento(String codigoBarra) {
        return fileLineExtractor.getValueFromString(codigoBarra, 21, 26);
    }

    /**
     * Extrai o nosso número do código de barras fornecido.
     *
     * @param codigoBarra que será extraída a informação.
     * @return o nosso número conforme a informação extraída do código de barras
     */
    public String getNossoNumero(String codigoBarra) {
        return fileLineExtractor.getValueFromString(codigoBarra, 26, 36);
    }
}
