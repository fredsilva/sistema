package br.gov.to.sefaz.arr.persistence.entity;

import br.gov.to.sefaz.arr.persistence.converter.TipoPessoaEnumConverter;
import br.gov.to.sefaz.arr.persistence.enums.FormaPagamentoEnum;
import br.gov.to.sefaz.arr.persistence.enums.SituacaoDareEnum;
import br.gov.to.sefaz.arr.persistence.enums.TipoCodigoBarraEnum;
import br.gov.to.sefaz.arr.persistence.enums.TipoPessoaEnum;
import br.gov.to.sefaz.persistence.entity.AbstractEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entidade que representa os dados da tabela SEFAZ_ARR.TA_PAGOS_ARREC.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 22/06/2016 10:57:40
 */
@SuppressWarnings("PMD")
@Entity
@Table(name = "TA_PAGOS_ARREC", schema = "SEFAZ_ARR")
@IdClass(PagosArrecPK.class)
public class PagosArrec extends AbstractEntity<PagosArrecPK> {

    private static final long serialVersionUID = -7767297676555350894L;

    @Id
    @NotNull
    @Column(name = "ID_BDAR_TPAR")
    private Long idBdarTpar;

    @Id
    @NotNull
    @Column(name = "ORDEM_LOTE")
    private Integer ordemLote;

    @NotNull
    @Column(name = "ID_RECEITA")
    private Integer idReceita;

    @Column(name = "ID_REPASSE")
    private Long idRepasse;

    @NotNull
    @Column(name = "ID_DETALHE_ARQUIVO", nullable = false)
    private Long idDetalheArquivo;

    @NotNull
    @Column(name = "ID_PESSOA")
    private Long idPessoa;

    @NotNull
    @Convert(converter = TipoPessoaEnumConverter.class)
    @Column(name = "TIPO_PESSOA")
    private TipoPessoaEnum tipoPessoa;

    @NotNull
    @Column(name = "DATA_PAGAMENTO")
    private LocalDateTime dataPagamento;

    @Column(name = "PERIODO_REFERENCIA")
    private Integer periodoReferencia;

    @Column(name = "ID_SUBCODIGO")
    private Integer idSubcodigo;

    @Column(name = "DOCUMENTO")
    private Long documento;

    @Column(name = "NUMERO_PARCELA")
    private Integer numeroParcela;

    @Column(name = "DATA_VENCIMENTO_INFORMADO")
    private LocalDate dataVencimentoInformado;

    @NotNull
    @Column(name = "VALOR_TOTAL")
    private BigDecimal valorTotal;

    @NotNull
    @Column(name = "VALOR_PRINCIPAL")
    private BigDecimal valorPrincipal;

    @NotNull
    @Column(name = "VALOR_MULTA")
    private BigDecimal valorMulta;

    @NotNull
    @Column(name = "VALOR_JUROS")
    private BigDecimal valorJuros;

    @NotNull
    @Column(name = "VALOR_CORRECAO_MONETARIA")
    private BigDecimal valorCorrecaoMonetaria;

    @NotNull
    @Column(name = "VALOR_TAXA")
    private BigDecimal valorTaxa;

    @NotNull
    @Column(name = "TIPO_DARE")
    private TipoCodigoBarraEnum tipoDare;

    @NotNull
    @Column(name = "ORIGEM_DARE")
    private FormaPagamentoEnum origemDare;

    @NotNull
    @Column(name = "SITUACAO_DARE")
    private SituacaoDareEnum situacaoDare;

    @NotNull
    @Column(name = "DATA_PAGO_BANCO")
    private LocalDateTime dataPagoBanco;

    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "NSU_BARRA")
    private String nsuBarra;

    @Column(name = "ID_MUNICIPIO_DARE")
    private Integer idMunicipioDare;

    @Column(name = "TRANSFERIDO_PAGAMENTO")
    private Integer transferidoPagamento;

    @Column(name = "DARE_RESTITUIDO")
    private Integer dareRestituido;

    @JoinColumn(name = "ID_DETALHE_ARQUIVO", referencedColumnName = "ID_DETALHE_ARQUIVO", insertable = false,
            updatable = false)
    @ManyToOne
    private ArquivoDetalhePagos arquivoDetalhePagos;

    @JoinColumns({
            @JoinColumn(name = "ID_NOSSO_NUMERO_DARE", referencedColumnName = "ID_NOSSO_NUMERO_DARE"),
            @JoinColumn(name = "ID_SEQ_DARE_DETALHE", referencedColumnName = "ID_SEQ_DARE_DETALHE")})
    @ManyToOne
    private DareDetalhe dareDetalhe;

    @JoinColumn(name = "ID_BDAR_TPAR", referencedColumnName = "ID_BDAR_TPAR", insertable = false, updatable = false)
    @ManyToOne
    private LotesPagosArrec lotesPagosArrec;

    @JoinColumn(name = "ID_RECEITA", referencedColumnName = "ID_RECEITA", insertable = false, updatable = false)
    @ManyToOne
    private Receitas receitas;

    @JoinColumn(name = "ID_REPASSE", referencedColumnName = "ID_REPASSE", insertable = false, updatable = false)
    @ManyToOne
    private RtcRepasse rtcRepasse;

    public PagosArrec() {
        // Construtor para inicialização por reflexão.
    }

    public PagosArrec(Long idBdarTpar, Integer ordemLote, Integer idReceita, Long idRepasse, Long idDetalheArquivo,
            Long idPessoa, TipoPessoaEnum tipoPessoa, LocalDateTime dataPagamento, BigDecimal valorTotal,
            BigDecimal valorPrincipal, BigDecimal valorMulta, BigDecimal valorJuros, BigDecimal valorCorrecaoMonetaria,
            BigDecimal valorTaxa, TipoCodigoBarraEnum tipoDare, FormaPagamentoEnum origemDare,
            SituacaoDareEnum situacaoDare, LocalDateTime dataPagoBanco, String nsuBarra) {
        this.idBdarTpar = idBdarTpar;
        this.ordemLote = ordemLote;
        this.idReceita = idReceita;
        this.idRepasse = idRepasse;
        this.idDetalheArquivo = idDetalheArquivo;
        this.idPessoa = idPessoa;
        this.tipoPessoa = tipoPessoa;
        this.dataPagamento = dataPagamento;
        this.valorTotal = valorTotal;
        this.valorPrincipal = valorPrincipal;
        this.valorMulta = valorMulta;
        this.valorJuros = valorJuros;
        this.valorCorrecaoMonetaria = valorCorrecaoMonetaria;
        this.valorTaxa = valorTaxa;
        this.tipoDare = tipoDare;
        this.origemDare = origemDare;
        this.situacaoDare = situacaoDare;
        this.dataPagoBanco = dataPagoBanco;
        this.nsuBarra = nsuBarra;
    }

    @Override
    public PagosArrecPK getId() {
        return new PagosArrecPK();
    }

    public Long getIdBdarTpar() {
        return idBdarTpar;
    }

    public void setIdBdarTpar(Long idBdarTpar) {
        this.idBdarTpar = idBdarTpar;
    }

    public Integer getOrdemLote() {
        return ordemLote;
    }

    public void setOrdemLote(Integer ordemLote) {
        this.ordemLote = ordemLote;
    }

    public Integer getIdReceita() {
        return idReceita;
    }

    public void setIdReceita(Integer idReceita) {
        this.idReceita = idReceita;
    }

    public Long getIdRepasse() {
        return idRepasse;
    }

    public void setIdRepasse(Long idRepasse) {
        this.idRepasse = idRepasse;
    }

    public Long getIdDetalheArquivo() {
        return idDetalheArquivo;
    }

    public void setIdDetalheArquivo(Long idDetalheArquivo) {
        this.idDetalheArquivo = idDetalheArquivo;
    }

    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public TipoPessoaEnum getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(TipoPessoaEnum tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Integer getPeriodoReferencia() {
        return periodoReferencia;
    }

    public void setPeriodoReferencia(Integer periodoReferencia) {
        this.periodoReferencia = periodoReferencia;
    }

    public Integer getIdSubcodigo() {
        return idSubcodigo;
    }

    public void setIdSubcodigo(Integer idSubcodigo) {
        this.idSubcodigo = idSubcodigo;
    }

    public Long getDocumento() {
        return documento;
    }

    public void setDocumento(Long documento) {
        this.documento = documento;
    }

    public Integer getNumeroParcela() {
        return numeroParcela;
    }

    public void setNumeroParcela(Integer numeroParcela) {
        this.numeroParcela = numeroParcela;
    }

    public LocalDate getDataVencimentoInformado() {
        return dataVencimentoInformado;
    }

    public void setDataVencimentoInformado(LocalDate dataVencimentoInformado) {
        this.dataVencimentoInformado = dataVencimentoInformado;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
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

    public BigDecimal getValorCorrecaoMonetaria() {
        return valorCorrecaoMonetaria;
    }

    public void setValorCorrecaoMonetaria(BigDecimal valorCorrecaoMonetaria) {
        this.valorCorrecaoMonetaria = valorCorrecaoMonetaria;
    }

    public BigDecimal getValorTaxa() {
        return valorTaxa;
    }

    public void setValorTaxa(BigDecimal valorTaxa) {
        this.valorTaxa = valorTaxa;
    }

    public TipoCodigoBarraEnum getTipoDare() {
        return tipoDare;
    }

    public void setTipoDare(TipoCodigoBarraEnum tipoDare) {
        this.tipoDare = tipoDare;
    }

    public FormaPagamentoEnum getOrigemDare() {
        return origemDare;
    }

    public void setOrigemDare(FormaPagamentoEnum origemDare) {
        this.origemDare = origemDare;
    }

    public SituacaoDareEnum getSituacaoDare() {
        return situacaoDare;
    }

    public void setSituacaoDare(SituacaoDareEnum situacaoDare) {
        this.situacaoDare = situacaoDare;
    }

    public LocalDateTime getDataPagoBanco() {
        return dataPagoBanco;
    }

    public void setDataPagoBanco(LocalDateTime dataPagoBanco) {
        this.dataPagoBanco = dataPagoBanco;
    }

    public String getNsuBarra() {
        return nsuBarra;
    }

    public void setNsuBarra(String nsuBarra) {
        this.nsuBarra = nsuBarra;
    }

    public Integer getIdMunicipioDare() {
        return idMunicipioDare;
    }

    public void setIdMunicipioDare(Integer idMunicipioDare) {
        this.idMunicipioDare = idMunicipioDare;
    }

    public Integer getTransferidoPagamento() {
        return transferidoPagamento;
    }

    public void setTransferidoPagamento(Integer transferidoPagamento) {
        this.transferidoPagamento = transferidoPagamento;
    }

    public Integer getDareRestituido() {
        return dareRestituido;
    }

    public void setDareRestituido(Integer dareRestituido) {
        this.dareRestituido = dareRestituido;
    }

    public ArquivoDetalhePagos getArquivoDetalhePagos() {
        return arquivoDetalhePagos;
    }

    public void setArquivoDetalhePagos(ArquivoDetalhePagos arquivoDetalhePagos) {
        this.arquivoDetalhePagos = arquivoDetalhePagos;
    }

    public DareDetalhe getDareDetalhe() {
        return dareDetalhe;
    }

    public void setDareDetalhe(DareDetalhe dareDetalhe) {
        this.dareDetalhe = dareDetalhe;
    }

    public LotesPagosArrec getLotesPagosArrec() {
        return lotesPagosArrec;
    }

    public void setLotesPagosArrec(LotesPagosArrec lotesPagosArrec) {
        this.lotesPagosArrec = lotesPagosArrec;
    }

    public Receitas getReceitas() {
        return receitas;
    }

    public void setReceitas(Receitas receitas) {
        this.receitas = receitas;
    }

    public RtcRepasse getRtcRepasse() {
        return rtcRepasse;
    }

    public void setRtcRepasse(RtcRepasse rtcRepasse) {
        this.rtcRepasse = rtcRepasse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PagosArrec that = (PagosArrec) o;
        return Objects.equals(idBdarTpar, that.idBdarTpar)
                && Objects.equals(ordemLote, that.ordemLote)
                && Objects.equals(idReceita, that.idReceita)
                && Objects.equals(idRepasse, that.idRepasse)
                && Objects.equals(idDetalheArquivo, that.idDetalheArquivo)
                && Objects.equals(idPessoa, that.idPessoa)
                && Objects.equals(tipoPessoa, that.tipoPessoa)
                && Objects.equals(dataPagamento, that.dataPagamento)
                && Objects.equals(periodoReferencia, that.periodoReferencia)
                && Objects.equals(idSubcodigo, that.idSubcodigo)
                && Objects.equals(documento, that.documento)
                && Objects.equals(numeroParcela, that.numeroParcela)
                && Objects.equals(dataVencimentoInformado, that.dataVencimentoInformado)
                && Objects.equals(valorTotal, that.valorTotal)
                && Objects.equals(valorPrincipal, that.valorPrincipal)
                && Objects.equals(valorMulta, that.valorMulta)
                && Objects.equals(valorJuros, that.valorJuros)
                && Objects.equals(valorCorrecaoMonetaria, that.valorCorrecaoMonetaria)
                && Objects.equals(valorTaxa, that.valorTaxa)
                && Objects.equals(tipoDare, that.tipoDare)
                && Objects.equals(origemDare, that.origemDare)
                && Objects.equals(situacaoDare, that.situacaoDare)
                && Objects.equals(dataPagoBanco, that.dataPagoBanco)
                && Objects.equals(nsuBarra, that.nsuBarra)
                && Objects.equals(idMunicipioDare, that.idMunicipioDare)
                && Objects.equals(transferidoPagamento, that.transferidoPagamento)
                && Objects.equals(dareRestituido, that.dareRestituido)
                && Objects.equals(arquivoDetalhePagos, that.arquivoDetalhePagos)
                && Objects.equals(dareDetalhe, that.dareDetalhe)
                && Objects.equals(lotesPagosArrec, that.lotesPagosArrec)
                && Objects.equals(receitas, that.receitas)
                && Objects.equals(rtcRepasse, that.rtcRepasse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBdarTpar, ordemLote, idReceita, idRepasse, idDetalheArquivo, idPessoa, tipoPessoa,
                dataPagamento, periodoReferencia, idSubcodigo, documento, numeroParcela, dataVencimentoInformado,
                valorTotal, valorPrincipal, valorMulta, valorJuros, valorCorrecaoMonetaria, valorTaxa, tipoDare,
                origemDare, situacaoDare, dataPagoBanco, nsuBarra, idMunicipioDare, transferidoPagamento,
                dareRestituido, arquivoDetalhePagos, dareDetalhe, lotesPagosArrec, receitas, rtcRepasse);
    }

    @Override
    public String toString() {
        return "PagosArrec{"
                + "idBdarTpar=" + idBdarTpar
                + ", ordemLote=" + ordemLote
                + ", idReceita=" + idReceita
                + ", idRepasse=" + idRepasse
                + ", idDetalheArquivo=" + idDetalheArquivo
                + ", idPessoa=" + idPessoa
                + ", tipoPessoa=" + tipoPessoa
                + ", dataPagamento=" + dataPagamento
                + ", periodoReferencia=" + periodoReferencia
                + ", idSubcodigo=" + idSubcodigo
                + ", documento=" + documento
                + ", numeroParcela=" + numeroParcela
                + ", dataVencimentoInformado=" + dataVencimentoInformado
                + ", valorTotal=" + valorTotal
                + ", valorPrincipal=" + valorPrincipal
                + ", valorMulta=" + valorMulta
                + ", valorJuros=" + valorJuros
                + ", valorCorrecaoMonetaria=" + valorCorrecaoMonetaria
                + ", valorTaxa=" + valorTaxa
                + ", tipoDare=" + tipoDare
                + ", origemDare=" + origemDare
                + ", situacaoDare=" + situacaoDare
                + ", dataPagoBanco=" + dataPagoBanco
                + ", nsuBarra='" + nsuBarra + '\''
                + ", idMunicipioDare=" + idMunicipioDare
                + ", transferidoPagamento=" + transferidoPagamento
                + ", dareRestituido=" + dareRestituido
                + ", arquivoDetalhePagos=" + arquivoDetalhePagos
                + ", dareDetalhe=" + dareDetalhe
                + ", lotesPagosArrec=" + lotesPagosArrec
                + ", receitas=" + receitas
                + ", rtcRepasse=" + rtcRepasse
                + '}';
    }
}
