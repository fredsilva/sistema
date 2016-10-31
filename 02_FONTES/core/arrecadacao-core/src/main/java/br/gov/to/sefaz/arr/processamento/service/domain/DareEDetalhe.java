package br.gov.to.sefaz.arr.processamento.service.domain;

import br.gov.to.sefaz.util.formatter.FormatterUtil;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Objeto responsavel pelos campos e formatações para a geração da lista de detalhes de um Dare-e.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 19/08/2016 14:15:00
 */
public class DareEDetalhe implements Comparable<DareEDetalhe> {

    private final Integer item;
    private final Long documentoParcela;
    private final Integer periodo;
    private final LocalDate dataVencimento;
    private final BigDecimal vlPrincipal;
    private final BigDecimal vlAtlzMonet;
    private final BigDecimal vlMulta;
    private final BigDecimal vlJuros;
    private final BigDecimal vlReducoes;
    private final BigDecimal vlTse;
    private final BigDecimal vlTotal;
    private final Boolean informadoContribuinte;
    private final String receita;
    private final String subCodigo;
    private final String observacao;

    public DareEDetalhe(Integer item, Long documentoParcela, Integer periodo, LocalDate dataVencimento,
            BigDecimal vlPrincipal, BigDecimal vlAtlzMonet, BigDecimal vlMulta, BigDecimal vlJuros,
            BigDecimal vlReducoes, BigDecimal vlTse, BigDecimal vlTotal, String receita, String subCodigo,
            String observacao, Boolean informadoContribuinte) {
        this.item = item;
        this.documentoParcela = documentoParcela;
        this.periodo = periodo;
        this.dataVencimento = dataVencimento;
        this.vlPrincipal = vlPrincipal;
        this.vlAtlzMonet = vlAtlzMonet;
        this.vlMulta = vlMulta;
        this.vlJuros = vlJuros;
        this.vlReducoes = vlReducoes;
        this.vlTse = vlTse;
        this.vlTotal = vlTotal;
        this.receita = receita;
        this.subCodigo = subCodigo;
        this.observacao = observacao;
        this.informadoContribuinte = informadoContribuinte;
    }

    public Integer getItem() {
        return item;
    }

    public Long getDocumentoParcela() {
        return documentoParcela;
    }

    public String getPeriodo() {
        return FormatterUtil.formatPeriodoMesAno(periodo);
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public BigDecimal getVlPrincipal() {
        return vlPrincipal;
    }

    public BigDecimal getVlAtlzMonet() {
        return vlAtlzMonet;
    }

    public BigDecimal getVlMulta() {
        return vlMulta;
    }

    public BigDecimal getVlJuros() {
        return vlJuros;
    }

    public BigDecimal getVlReducoes() {
        return vlReducoes;
    }

    public BigDecimal getVlTse() {
        return vlTse;
    }

    public BigDecimal getVlTotal() {
        return vlTotal;
    }

    public String getReceitaSubCodigo() {
        return Stream.of(receita, subCodigo)
                .filter(StringUtils::isNotEmpty)
                .collect(Collectors.joining("/"));
    }

    public String getInformacaoComplementar() {
        return Stream.of(observacao, informadoContribuinte ? SourceBundle.getMessage(MessageUtil.ARR,
                "arr.par.dareDetalhe.informacaoComplementar.informadoContribuinte") : StringUtils.EMPTY)
                .filter(StringUtils::isNotEmpty)
                .collect(Collectors.joining(" "));
    }

    public String getObservacao() {
        return observacao;
    }

    public boolean isObcervacaoEmpty() {
        return StringUtils.isEmpty(observacao) && !informadoContribuinte;
    }

    public String getInformadoContribuinte() {
        return informadoContribuinte ? "(*)" : null;
    }

    @Override
    public int compareTo(DareEDetalhe other) {
        return Integer.compare(getItem(), other.getItem());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DareEDetalhe that = (DareEDetalhe) o;
        return Objects.equals(item, that.item)
                && Objects.equals(documentoParcela, that.documentoParcela)
                && Objects.equals(periodo, that.periodo)
                && Objects.equals(dataVencimento, that.dataVencimento)
                && Objects.equals(vlPrincipal, that.vlPrincipal)
                && Objects.equals(vlAtlzMonet, that.vlAtlzMonet)
                && Objects.equals(vlMulta, that.vlMulta)
                && Objects.equals(vlJuros, that.vlJuros)
                && Objects.equals(vlReducoes, that.vlReducoes)
                && Objects.equals(vlTse, that.vlTse)
                && Objects.equals(vlTotal, that.vlTotal)
                && Objects.equals(receita, that.receita)
                && Objects.equals(subCodigo, that.subCodigo)
                && Objects.equals(observacao, that.observacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item, documentoParcela, periodo, dataVencimento, vlPrincipal, vlAtlzMonet, vlMulta,
                vlJuros, vlReducoes, vlTse, vlTotal, receita, subCodigo, observacao);
    }

    @Override
    public String toString() {
        return "DareEDetalhe{"
                + "item=" + item
                + ", documentoParcela=" + documentoParcela
                + ", periodo=" + periodo
                + ", dataVencimento=" + dataVencimento
                + ", vlPrincipal=" + vlPrincipal
                + ", vlAtlzMonet=" + vlAtlzMonet
                + ", vlMulta=" + vlMulta
                + ", vlJuros=" + vlJuros
                + ", vlReducoes=" + vlReducoes
                + ", vlTse=" + vlTse
                + ", vlTotal=" + vlTotal
                + ", receita='" + receita + '\''
                + ", subCodigo='" + subCodigo + '\''
                + ", observacao='" + observacao + '\''
                + '}';
    }
}
