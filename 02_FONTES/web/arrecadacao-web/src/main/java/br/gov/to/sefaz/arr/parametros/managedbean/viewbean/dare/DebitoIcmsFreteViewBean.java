package br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare;

import br.gov.to.sefaz.arr.dare.enums.ValorAliquotaEnum;
import br.gov.to.sefaz.arr.dare.enums.ValorMultaEnum;

import java.math.BigDecimal;
import java.util.Optional;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 * View Bean de Débitos ICMS Frete referente ao painel Dados do Pagamento do Gerar DARE-e.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 31/08/2016 14:51:00
 */
@SuppressWarnings("PMD.TooManyFields")
public class DebitoIcmsFreteViewBean {

    @NotNull(message = "#{arr_msg['arr.par.dare.debitoIcmsFreteViewBean.ufOrigem.empty']}")
    private String ufOrigem;
    @NotNull(message = "#{arr_msg['arr.par.dare.debitoIcmsFreteViewBean.ufDestino.empty']}")
    private String ufDestino;
    @NotNull(message = "#{arr_msg['arr.par.dare.debitoIcmsFreteViewBean.municipioOrigem.empty']}")
    private Integer municipioOrigem;
    private String nomeMunicipioOrigem;
    @NotNull(message = "#{arr_msg['arr.par.dare.debitoIcmsFreteViewBean.municipioDestino.empty']}")
    private Integer municipioDestino;
    private String nomeMunicipioDestino;
    @NotNull(message = "#{arr_msg['arr.par.dare.debitoIcmsFreteViewBean.notaFiscal.empty']}")
    private Long notaFiscal;
    @NotNull(message = "#{arr_msg['arr.par.dare.debitoIcmsFreteViewBean.distancia.empty']}")
    @DecimalMin(value = "0.001",
            message = "#{arr_msg['arr.par.dare.debitoIcmsFreteViewBean.distancia.greaterThanZero']}")
    private Double distancia;
    @NotNull(message = "#{arr_msg['arr.par.dare.debitoIcmsFreteViewBean.peso.empty']}")
    @DecimalMin(value = "0.001",
            message = "#{arr_msg['arr.par.dare.debitoIcmsFreteViewBean.peso.greaterThanZero']}")
    private Double peso;
    @NotNull(message = "#{arr_msg['arr.par.dare.debitoIcmsFreteViewBean.aliquota.empty']}")
    private ValorAliquotaEnum aliquota;
    @NotNull(message = "#{arr_msg['arr.par.dare.debitoIcmsFreteViewBean.multa.empty']}")
    private ValorMultaEnum multa;
    @NotNull(message = "#{arr_msg['arr.par.dare.debitoIcmsFreteViewBean.valorBc.empty']}")
    private BigDecimal valorBc;

    private BigDecimal valorImposto;
    private BigDecimal valorMulta;
    @DecimalMin(value = "0.01",
            message = "#{arr_msg['arr.par.dare.debitoIcmsFreteViewBean.totalRecolher.greaterThanZero']}")
    @DecimalMax(value = "999999999.99",
            message = "#{arr_msg['arr.par.dare.dareEPagamento.totalRecolher.limit']}")
    private BigDecimal valorTotalFrete;
    private String observacoes;

    public String getUfOrigem() {
        return ufOrigem;
    }

    public void setUfOrigem(String ufOrigem) {
        this.ufOrigem = ufOrigem;
    }

    public Integer getMunicipioOrigem() {
        return municipioOrigem;
    }

    public void setMunicipioOrigem(Integer municipioOrigem) {
        this.municipioOrigem = municipioOrigem;
    }

    public String getNomeMunicipioOrigem() {
        return nomeMunicipioOrigem;
    }

    public void setNomeMunicipioOrigem(String nomeMunicipioOrigem) {
        this.nomeMunicipioOrigem = nomeMunicipioOrigem;
    }

    public String getUfDestino() {
        return ufDestino;
    }

    public void setUfDestino(String ufDestino) {
        this.ufDestino = ufDestino;
    }

    public Integer getMunicipioDestino() {
        return municipioDestino;
    }

    public void setMunicipioDestino(Integer municipioDestino) {
        this.municipioDestino = municipioDestino;
    }

    public String getNomeMunicipioDestino() {
        return nomeMunicipioDestino;
    }

    public void setNomeMunicipioDestino(String nomeMunicipioDestino) {
        this.nomeMunicipioDestino = nomeMunicipioDestino;
    }

    public Double getDistancia() {
        return distancia;
    }

    public void setDistancia(Double distancia) {
        this.distancia = distancia;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Long getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(Long notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    public ValorAliquotaEnum getAliquota() {
        return aliquota;
    }

    public void setAliquota(ValorAliquotaEnum aliquota) {
        this.aliquota = aliquota;
    }

    public ValorMultaEnum getMulta() {
        return multa;
    }

    public void setMulta(ValorMultaEnum multa) {
        this.multa = multa;
    }

    public BigDecimal getValorBc() {
        return Optional.ofNullable(valorBc).orElse(BigDecimal.ZERO);
    }

    public void setValorBc(BigDecimal valorBc) {
        this.valorBc = valorBc;
    }

    public BigDecimal getValorImposto() {
        return Optional.ofNullable(valorImposto).orElse(BigDecimal.ZERO);
    }

    public void setValorImposto(BigDecimal valorImposto) {
        this.valorImposto = valorImposto;
    }

    public BigDecimal getValorMulta() {
        return Optional.ofNullable(valorMulta).orElse(BigDecimal.ZERO);
    }

    public void setValorMulta(BigDecimal valorMulta) {
        this.valorMulta = valorMulta;
    }

    public BigDecimal getValorTotalFrete() {
        return valorTotalFrete;
    }

    public void setValorTotalFrete(BigDecimal valorTotalFrete) {
        this.valorTotalFrete = valorTotalFrete;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
