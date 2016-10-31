package br.gov.to.sefaz.arr.processamento.domain.detalhe.arrecadacao;

import br.gov.to.sefaz.arr.persistence.enums.TipoConvenioEnum;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * POJO que representa os atributos contidos no código de barras para o DARE.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 12/07/2016 16:49:00
 */
public class CodigoBarra {
    private TipoConvenioEnum tipoConvenio;
    private BigDecimal valorTotal;
    private Integer versaoDare;
    private Integer sistemaEmissor;
    private String dataVencimento;
    private String nossoNumero;

    public CodigoBarra(TipoConvenioEnum tipoConvenio, BigDecimal valorTotal, Integer versaoDare, Integer sistemaEmissor,
            String dataVencimento, String nossoNumero) {
        this.tipoConvenio = tipoConvenio;
        this.valorTotal = valorTotal;
        this.versaoDare = versaoDare;
        this.sistemaEmissor = sistemaEmissor;
        this.dataVencimento = dataVencimento;
        this.nossoNumero = nossoNumero;
    }

    public TipoConvenioEnum getTipoConvenio() {
        return tipoConvenio;
    }

    public void setTipoConvenio(TipoConvenioEnum tipoConvenio) {
        this.tipoConvenio = tipoConvenio;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getVersaoDare() {
        return versaoDare;
    }

    public void setVersaoDare(int versaoDare) {
        this.versaoDare = versaoDare;
    }

    public int getSistemaEmissor() {
        return sistemaEmissor;
    }

    public void setSistemaEmissor(int sistemaEmissor) {
        this.sistemaEmissor = sistemaEmissor;
    }

    public String getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getNossoNumero() {
        return nossoNumero;
    }

    public void setNossoNumero(String nossoNumero) {
        this.nossoNumero = nossoNumero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CodigoBarra that = (CodigoBarra) o;
        return Objects.equals(versaoDare, that.versaoDare)
                && Objects.equals(sistemaEmissor, that.sistemaEmissor)
                && tipoConvenio == that.tipoConvenio
                && Objects.equals(valorTotal, that.valorTotal)
                && Objects.equals(dataVencimento, that.dataVencimento)
                && Objects.equals(nossoNumero, that.nossoNumero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipoConvenio, valorTotal, versaoDare, sistemaEmissor, dataVencimento, nossoNumero);
    }

    @Override
    public String toString() {
        return "CodigoBarra{"
                + "tipoConvenio=" + tipoConvenio
                + ", valorTotal=" + valorTotal
                + ", versaoDare=" + versaoDare
                + ", sistemaEmissor=" + sistemaEmissor
                + ", dataVencimento='" + dataVencimento + '\''
                + ", nossoNumero='" + nossoNumero + '\''
                + '}';
    }
}
