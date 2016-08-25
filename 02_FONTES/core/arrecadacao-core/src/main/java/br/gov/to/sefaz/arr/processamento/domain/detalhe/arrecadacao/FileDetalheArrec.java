package br.gov.to.sefaz.arr.processamento.domain.detalhe.arrecadacao;

import br.gov.to.sefaz.arr.processamento.domain.detalhe.FileDetalhe;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * POJO que contém as informações referentes ao detalhe de arquivos de arrecadação.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 22/06/2016 17:55:00
 */
public class FileDetalheArrec implements FileDetalhe {

    private String numeroSequencial;
    private String numeroAutenticacao;
    private LocalDate dataPagamento;
    private String codigoBarras;
    private BigDecimal valorAutenticado;
    private String formaArrecadacao;
    private String formaPagamento;
    private LocalDateTime dataHoraTransacao;
    private LocalDate dataCredito;
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

    public void setConteudoLinha(String conteudoLinha) {
        this.conteudoLinha = conteudoLinha;
    }

    @Override
    public String getConteudoLinha() {
        return conteudoLinha;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public BigDecimal getValorAutenticado() {
        return valorAutenticado;
    }

    public void setValorAutenticado(BigDecimal valorAutenticado) {
        this.valorAutenticado = valorAutenticado;
    }

    public String getFormaArrecadacao() {
        return formaArrecadacao;
    }

    public void setFormaArrecadacao(String formaArrecadacao) {
        this.formaArrecadacao = formaArrecadacao;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public LocalDateTime getDataTransacao() {
        return dataHoraTransacao;
    }

    public void setDataTransacao(LocalDateTime dataHoraTransacao) {
        this.dataHoraTransacao = dataHoraTransacao;
    }

    public LocalDate getDataCredito() {
        return dataCredito;
    }

    public void setDataCredito(LocalDate dataCredito) {
        this.dataCredito = dataCredito;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FileDetalheArrec that = (FileDetalheArrec) o;
        return Objects.equals(numeroSequencial, that.numeroSequencial)
                && Objects.equals(numeroAutenticacao, that.numeroAutenticacao)
                && Objects.equals(dataPagamento, that.dataPagamento)
                && Objects.equals(codigoBarras, that.codigoBarras)
                && Objects.equals(valorAutenticado, that.valorAutenticado)
                && Objects.equals(formaArrecadacao, that.formaArrecadacao)
                && Objects.equals(formaPagamento, that.formaPagamento)
                && Objects.equals(dataHoraTransacao, that.dataHoraTransacao)
                && Objects.equals(dataCredito, that.dataCredito);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroSequencial, numeroAutenticacao, dataPagamento, codigoBarras, valorAutenticado,
                formaArrecadacao, formaPagamento, dataHoraTransacao, dataCredito);
    }

    @Override
    public String toString() {
        return "FileDetalhe{"
                + "numeroSequencial='" + numeroSequencial + '\''
                + ", numeroAutenticacao='" + numeroAutenticacao + '\''
                + ", dataPagamento=" + dataPagamento
                + ", codigoBarras='" + codigoBarras + '\''
                + ", valorAutenticado='" + valorAutenticado + '\''
                + ", formaArrecadacao='" + formaArrecadacao + '\''
                + ", formaPagamento='" + formaPagamento + '\''
                + ", dataTransacao=" + dataHoraTransacao
                + ", dataCredito=" + dataCredito
                + '}';
    }
}
