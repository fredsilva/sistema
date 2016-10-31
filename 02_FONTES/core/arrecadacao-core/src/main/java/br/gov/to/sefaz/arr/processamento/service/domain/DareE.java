package br.gov.to.sefaz.arr.processamento.service.domain;

import br.gov.to.sefaz.arr.persistence.enums.TipoPessoaEnum;
import br.gov.to.sefaz.util.barcode.BarCodeHandler;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Objeto responsavel pelos campos e formatações para a geração de um Dare-e.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 22/08/2016 15:36:00
 */
public class DareE {

    private final byte[] barcodeImg;
    private final String codigoBarra;
    private final Long nossoNumero;
    private final LocalDateTime dataHoraEmissao;
    private final LocalDate dataVencimento;
    private final Integer quantidadeRecolher;
    private final BigDecimal valorTotal;
    private final String instituicao;
    private final String ufEmissora;
    private final String municipio;
    private final TipoPessoaEnum identificacaoTipo;
    private final Long idPessoa;
    private final String nomeRazaoSocial;
    private final List<DareEDetalhe> detalhes;

    public DareE(byte[] barcodeImg, String codigoBarra, Long nossoNumero, LocalDateTime dataHoraEmissao,
            LocalDate dataVencimento, Integer quantidadeRecolher, BigDecimal valorTotal, String instituicao,
            String ufEmissora, String municipio, TipoPessoaEnum identificacaoTipo, Long idPessoa,
            String nomeRazaoSocial, List<DareEDetalhe> detalhes) {
        this.barcodeImg = barcodeImg;
        this.codigoBarra = codigoBarra;
        this.nossoNumero = nossoNumero;
        this.dataHoraEmissao = dataHoraEmissao;
        this.dataVencimento = dataVencimento;
        this.quantidadeRecolher = quantidadeRecolher;
        this.valorTotal = valorTotal;
        this.instituicao = instituicao;
        this.ufEmissora = ufEmissora;
        this.municipio = municipio;
        this.identificacaoTipo = identificacaoTipo;
        this.idPessoa = idPessoa;
        this.nomeRazaoSocial = nomeRazaoSocial;
        this.detalhes = detalhes;
        Collections.sort(detalhes);
    }

    public byte[] getBarcodeImg() {
        return barcodeImg;
    }

    public String getBarcodePiece4() {
        return BarCodeHandler.formatField(codigoBarra, 4);
    }

    public String getBarcodePiece3() {
        return BarCodeHandler.formatField(codigoBarra, 3);
    }

    public String getBarcodePiece2() {
        return BarCodeHandler.formatField(codigoBarra, 2);
    }

    public String getBarcodePiece1() {
        return BarCodeHandler.formatField(codigoBarra, 1);
    }

    public Long getNossoNumero() {
        return nossoNumero;
    }

    public LocalDateTime getDataHoraEmissao() {
        return dataHoraEmissao;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public Integer getQuantidadeRecolher() {
        return quantidadeRecolher;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public String getUfEmissora() {
        return ufEmissora;
    }

    public String getMunicipio() {
        return municipio;
    }

    public String getIdentificacaoTipo() {
        return identificacaoTipo.getLabel();
    }

    public String getContribuinteId() {
        return identificacaoTipo.formatIdPessoa(idPessoa);
    }

    public String getNomeRazaoSocial() {
        return nomeRazaoSocial;
    }

    public List<DareEDetalhe> getDetalhes() {
        return detalhes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DareE dareE = (DareE) o;
        return Arrays.equals(barcodeImg, dareE.barcodeImg)
                && Objects.equals(codigoBarra, dareE.codigoBarra)
                && Objects.equals(nossoNumero, dareE.nossoNumero)
                && Objects.equals(dataHoraEmissao, dareE.dataHoraEmissao)
                && Objects.equals(dataVencimento, dareE.dataVencimento)
                && Objects.equals(quantidadeRecolher, dareE.quantidadeRecolher)
                && Objects.equals(valorTotal, dareE.valorTotal)
                && Objects.equals(instituicao, dareE.instituicao)
                && Objects.equals(ufEmissora, dareE.ufEmissora)
                && Objects.equals(municipio, dareE.municipio)
                && identificacaoTipo == dareE.identificacaoTipo
                && Objects.equals(idPessoa, dareE.idPessoa)
                && Objects.equals(nomeRazaoSocial, dareE.nomeRazaoSocial)
                && Objects.equals(detalhes, dareE.detalhes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(barcodeImg, codigoBarra, nossoNumero, dataHoraEmissao, dataVencimento,
                quantidadeRecolher, valorTotal, instituicao, ufEmissora, municipio, identificacaoTipo,
                idPessoa, nomeRazaoSocial, detalhes);
    }

    @Override
    public String toString() {
        return "DareE{"
                + "barcodeImg=" + Arrays.toString(barcodeImg)
                + ", codigoBarra='" + codigoBarra + '\''
                + ", nossoNumero=" + nossoNumero
                + ", dataHoraEmissao=" + dataHoraEmissao
                + ", dataVencimento=" + dataVencimento
                + ", quantidadeRecolher=" + quantidadeRecolher
                + ", valorTotal=" + valorTotal
                + ", instituicao='" + instituicao + '\''
                + ", ufEmissora='" + ufEmissora + '\''
                + ", municipio='" + municipio + '\''
                + ", identificacaoTipo=" + identificacaoTipo
                + ", idPessoa=" + idPessoa
                + ", nomeRazaoSocial='" + nomeRazaoSocial + '\''
                + ", detalhes=" + detalhes
                + '}';
    }
}
