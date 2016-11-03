package br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare;

import br.gov.to.sefaz.arr.persistence.enums.OrigemDebitoEnum;
import br.gov.to.sefaz.arr.persistence.enums.TipoContribuinteEnum;
import br.gov.to.sefaz.arr.persistence.enums.TipoImpostoEnum;
import br.gov.to.sefaz.arr.persistence.enums.TipoPessoaEnum;
import br.gov.to.sefaz.util.application.ApplicationUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;

/**
 * View Bean de DARE-e.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 22/08/2016 14:34:00
 */
public class DareEViewBean {

    private Integer idPagamento;

    @NotNull(message = "#{arr_msg['arr.par.dare.DareEViewBean.unidadeOrganizacional.empty']}")
    private Long unidadeOrganizacional;
    @NotNull(message = "#{arr_msg['arr.par.dare.DareEViewBean.unidadeFederacao.empty']}")
    private String unidadeFederacao;
    @NotNull(message = "#{arr_msg['arr.par.dare.DareEViewBean.municipio.empty']}")
    private Integer municipio;
    @NotNull(message = "#{arr_msg['arr.par.dare.DareEViewBean.tipoContribuinte.empty']}")
    private TipoContribuinteEnum tipoContribuinte;
    @NotNull(message = "#{arr_msg['arr.par.dare.DareEViewBean.tipoPessoa.empty']}")
    private TipoPessoaEnum tipoPessoa;
    @NotNull(message = "#{arr_msg['arr.par.dare.DareEViewBean.idContribuinteFilter.empty']}")
    private Long idContribuinteFilter;
    private Long identificacaoPessoa;
    private String nomePessoa;
    private TipoImpostoEnum tipoImposto;
    private OrigemDebitoEnum origemDebito;
    private Long nossoNumero;
    private LocalDateTime dataGeracao;

    private List<DareEPagamento> listaPagamentos;

    public DareEViewBean() {
        listaPagamentos = new ArrayList<>();
    }

    public Long getUnidadeOrganizacional() {
        return unidadeOrganizacional;
    }

    public void setUnidadeOrganizacional(Long unidadeOrganizacional) {
        this.unidadeOrganizacional = unidadeOrganizacional;
    }

    public String getUnidadeFederacao() {
        return unidadeFederacao;
    }

    public void setUnidadeFederacao(String unidadeFederacao) {
        this.unidadeFederacao = unidadeFederacao;
    }

    public Integer getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Integer municipio) {
        this.municipio = municipio;
    }

    public TipoContribuinteEnum getTipoContribuinte() {
        return tipoContribuinte;
    }

    public void setTipoContribuinte(TipoContribuinteEnum tipoContribuinte) {
        this.tipoContribuinte = tipoContribuinte;
    }

    public TipoPessoaEnum getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(TipoPessoaEnum tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public Long getIdentificacaoPessoa() {
        return identificacaoPessoa;
    }

    public void setIdentificacaoPessoa(Long identificacaoPessoa) {
        this.identificacaoPessoa = identificacaoPessoa;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public TipoImpostoEnum getTipoImposto() {
        return tipoImposto;
    }

    public void setTipoImposto(TipoImpostoEnum tipoImposto) {
        this.tipoImposto = tipoImposto;
    }

    public OrigemDebitoEnum getOrigemDebito() {
        return origemDebito;
    }

    public void setOrigemDebito(OrigemDebitoEnum origemDebito) {
        this.origemDebito = origemDebito;
    }

    public Long getNossoNumero() {
        return nossoNumero;
    }

    public void setNossoNumero(Long nossoNumero) {
        this.nossoNumero = nossoNumero;
    }

    public LocalDateTime getDataGeracao() {
        return dataGeracao;
    }

    public void setDataGeracao(LocalDateTime dataGeracao) {
        this.dataGeracao = dataGeracao;
    }

    public Long getIdContribuinteFilter() {
        return idContribuinteFilter;
    }

    public void setIdContribuinteFilter(Long idContribuinteSearch) {
        this.idContribuinteFilter = idContribuinteSearch;
    }

    public List<DareEPagamento> getListaPagamentos() {
        return listaPagamentos;
    }

    public void setListaPagamentos(List<DareEPagamento> listaPagamentos) {
        this.listaPagamentos = listaPagamentos;
    }

    public void setIdPagamento(Integer idPagamento) {
        this.idPagamento = idPagamento;
    }

    public Integer getIdPagamento() {
        return idPagamento;
    }

    /**
     * Adiciona na {@link #listaPagamentos} todos os
     * {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEPagamento} passados por parâmetro.
     *
     * @param dareEPagamentos lista de pagamentos a serem adicionados na {@link #listaPagamentos} do DARE-e.
     */
    public void addAllListaPagamentos(List<DareEPagamento> dareEPagamentos) {
        dareEPagamentos.forEach(this::addDareEPagamento);
    }

    /**
     * Adiciona um {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEPagamento} na lista de
     * {@link #listaPagamentos}.
     *
     * @param dareEPagamento {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEPagamento}
     *                       a se adiciona na {@link #listaPagamentos}
     */
    public void addDareEPagamento(DareEPagamento dareEPagamento) {
        processIdPagamento();
        dareEPagamento.setId(++idPagamento);
        getListaPagamentos().add(dareEPagamento);
    }

    private void processIdPagamento() {
        if (getListaPagamentos().isEmpty()) {
            idPagamento = 0;
        } else {
            DareEPagamento dare = getListaPagamentos().get(getListaPagamentos().size() - 1);
            idPagamento = dare.getId();
        }
    }

    /**
     * Retorna o valor monetário do valor total calculado.
     * R$ xx,xx
     *
     * @return Valor total com valor monetário.
     */
    public String calcValorTotal() {
        return NumberFormat.getCurrencyInstance(ApplicationUtil.LOCALE).format(getValorTotal());
    }

    /**
     * Soma os pagamentos da lista de pagamentos.
     *
     * @return valor total dos pagamentos.
     */
    public BigDecimal getValorTotal() {
        List<BigDecimal> valoresTotais = getListaPagamentos()
                .stream().map(DareEPagamento::getValorTotal).collect(Collectors.toList());

        return valoresTotais.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, RoundingMode.HALF_UP);
    }

}
