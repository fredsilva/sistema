package br.gov.to.sefaz.arr.processamento.domain.detalhe.simplesnacional;

import br.gov.to.sefaz.arr.processamento.domain.detalhe.FileDetalhe;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * POJO que contém as informações referentes ao detalhe de arquivos Simples Nacional.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 22/06/2016 17:55:00
 */
public class FileDetalheSN implements FileDetalhe {

    private String numeroSequencial;
    private String numeroAutenticacao;
    private String cnpjContribuinte;
    private LocalDate dataArrecadacao;
    private Integer codigoBanco;
    private Integer codigoAgencia;
    private BigDecimal valorPrincipal;
    private BigDecimal valorMulta;
    private BigDecimal valorJuros;
    private BigDecimal valorAutenticacao;
    private LocalDate dataVencimento;
    private String conteudoLinha;

    @Override
    public String getNumeroSequencial() {
        return numeroSequencial;
    }

    public void setNumeroSequencial(String numeroSequencial) {
        this.numeroSequencial = numeroSequencial;
    }

    @Override
    public String getNumeroAutenticacao() {
        return numeroAutenticacao;
    }

    public void setNumeroAutenticacao(String numeroAutenticacao) {
        this.numeroAutenticacao = numeroAutenticacao;
    }

    public String getCnpjContribuinte() {
        return cnpjContribuinte;
    }

    public void setCnpjContribuinte(String cnpjContribuinte) {
        this.cnpjContribuinte = cnpjContribuinte;
    }

    public LocalDate getDataArrecadacao() {
        return dataArrecadacao;
    }

    public void setDataArrecadacao(LocalDate dataArrecadacao) {
        this.dataArrecadacao = dataArrecadacao;
    }

    public Integer getCodigoBanco() {
        return codigoBanco;
    }

    public void setCodigoBanco(Integer codigoBanco) {
        this.codigoBanco = codigoBanco;
    }

    public Integer getCodigoAgencia() {
        return codigoAgencia;
    }

    public void setCodigoAgencia(Integer codigoAgencia) {
        this.codigoAgencia = codigoAgencia;
    }

    public BigDecimal getValorPrincipal() {
        return valorPrincipal;
    }

    public void setValorPrincipal(BigDecimal valorPrincipal) {
        this.valorPrincipal = valorPrincipal;
    }

    public BigDecimal getValorMulta() {
        return valorMulta;
    }

    public void setValorMulta(BigDecimal valorMulta) {
        this.valorMulta = valorMulta;
    }

    public BigDecimal getValorJuros() {
        return valorJuros;
    }

    public void setValorJuros(BigDecimal valorJuros) {
        this.valorJuros = valorJuros;
    }

    public BigDecimal getValorAutenticacao() {
        return valorAutenticacao;
    }

    public void setValorAutenticacao(BigDecimal valorAutenticacao) {
        this.valorAutenticacao = valorAutenticacao;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public void setConteudoLinha(String conteudoLinha) {
        this.conteudoLinha = conteudoLinha;
    }

    @Override
    public String getConteudoLinha() {
        return conteudoLinha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FileDetalheSN that = (FileDetalheSN) o;
        return Objects.equals(numeroSequencial, that.numeroSequencial)
                && Objects.equals(numeroAutenticacao, that.numeroAutenticacao)
                && Objects.equals(cnpjContribuinte, that.cnpjContribuinte)
                && Objects.equals(dataArrecadacao, that.dataArrecadacao)
                && Objects.equals(codigoBanco, that.codigoBanco)
                && Objects.equals(codigoAgencia, that.codigoAgencia)
                && Objects.equals(valorPrincipal, that.valorPrincipal)
                && Objects.equals(valorMulta, that.valorMulta)
                && Objects.equals(valorJuros, that.valorJuros)
                && Objects.equals(valorAutenticacao, that.valorAutenticacao)
                && Objects.equals(dataVencimento, that.dataVencimento)
                && Objects.equals(conteudoLinha, that.conteudoLinha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroSequencial, numeroAutenticacao, cnpjContribuinte, dataArrecadacao, codigoBanco,
                codigoAgencia, valorPrincipal, valorMulta, valorJuros, valorAutenticacao, dataVencimento,
                conteudoLinha);
    }

    @Override
    public String toString() {
        return "FileDetalheSN{"
                + "numeroSequencial='" + numeroSequencial + '\''
                + ", numeroAutenticacao='" + numeroAutenticacao + '\''
                + ", cnpjContribuinte='" + cnpjContribuinte + '\''
                + ", dataArrecadacao=" + dataArrecadacao
                + ", codigoBanco='" + codigoBanco + '\''
                + ", codigoAgencia='" + codigoAgencia + '\''
                + ", valorPrincipal='" + valorPrincipal + '\''
                + ", valorMulta='" + valorMulta + '\''
                + ", valorJuros='" + valorJuros + '\''
                + ", valorAutenticacao='" + valorAutenticacao + '\''
                + ", dataVencimento=" + dataVencimento
                + ", conteudoLinha='" + conteudoLinha + '\''
                + '}';
    }
}
