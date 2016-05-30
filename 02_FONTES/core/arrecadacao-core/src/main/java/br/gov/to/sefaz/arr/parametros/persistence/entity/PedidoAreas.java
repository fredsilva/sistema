package br.gov.to.sefaz.arr.parametros.persistence.entity;

import br.gov.to.sefaz.persistence.converter.OneOrTwoBooleanConverter;
import br.gov.to.sefaz.persistence.converter.SituacaoEnumConverter;
import br.gov.to.sefaz.persistence.entity.AbstractEntity;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Entidade que representa os dados da tabela SEFAZ_ARR.TA_PEDIDO_AREAS.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 20/05/2016 15:18:05
 */
@Entity
@Table(name = "TA_PEDIDO_AREAS", schema = "SEFAZ_ARR")
public class PedidoAreas extends AbstractEntity<Integer> {

    private static final long serialVersionUID = 825818904471813292L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_pedido_areas")
    @SequenceGenerator(name = "sq_pedido_areas", schema = "SEFAZ_ARR", sequenceName = "sq_pedido_areas",
            allocationSize = 1)
    @Column(name = "ID_PEDIDO_AREA")
    private Integer idPedidoArea;

    @NotNull(message = "#{arr_msg['parametros.pedidoAreas.situacao.obrigatorio']}")
    @Column(name = "SITUACAO")
    @Convert(converter = SituacaoEnumConverter.class)
    private SituacaoEnum situacao;

    @Max(value = 99, message = "#{arr_msg['parametros.pedidoAreas.ordemParecer.maximo']}")
    @Min(value = 1, message = "#{arr_msg['parametros.pedidoAreas.ordemParecer.minimo']}")
    @NotNull(message = "#{arr_msg['parametros.pedidoAreas.ordemParecer.obrigatorio']}")
    @Column(name = "ORDEM_PARECER")
    private Integer ordemParecer;

    @Max(value = 99, message = "#{arr_msg['parametros.pedidoAreas.quantidadeDiasAnalise.maximo']}")
    @Min(value = 1, message = "#{arr_msg['parametros.pedidoAreas.quantidadeDiasAnalise.minimo']}")
    @NotNull(message = "#{arr_msg['parametros.pedidoAreas.quantidadeDiasAnalise.obrigatorio']}")
    @Column(name = "QUANTIDADE_DIAS_ANALISE")
    private Integer quantidadeDiasAnalise;

    @NotNull(message = "#{arr_msg['parametros.pedidoAreas.idTipoPedido.obrigatorio']}")
    @Column(name = "ID_TIPO_PEDIDO")
    private Integer idTipoPedido;

    @NotNull(message = "#{arr_msg['parametros.pedidoAreas.idUnidadeDelegacia.obrigatorio']}")
    @Column(name = "ID_UNIDADE_DELEGACIA")
    private Integer idUnidadeDelegacia;

    @NotNull(message = "#{arr_msg['parametros.pedidoAreas.idDelegacia.obrigatorio']}")
    @Column(name = "ID_DELEGACIA")
    private Integer idDelegacia;

    @Column(name = "PARECER_FINAL")
    @Convert(converter = OneOrTwoBooleanConverter.class)
    private Boolean parecerFinal;

    @Column(name = "EXIGE_PARECER")
    @Convert(converter = OneOrTwoBooleanConverter.class)
    private Boolean exigeParecer;

    @Column(name = "PERMITE_ENCAMINHAMENTO")
    @Convert(converter = OneOrTwoBooleanConverter.class)
    private Boolean permiteEncaminhamento;

    @Column(name = "EXIGE_SUPERVISOR")
    @Convert(converter = OneOrTwoBooleanConverter.class)
    private Boolean exigeSupervisor;

    @JoinColumn(name = "ID_PEDIDO_AREA", referencedColumnName = "ID_PEDIDO_AREA", insertable = false, updatable = false)
    @OneToOne(optional = false)
    @Fetch(FetchMode.JOIN)
    private PedidoAreasFaixaValor faixaValor;

    @JoinColumns({
            @JoinColumn(name = "ID_UNIDADE_DELEGACIA", referencedColumnName = "ID_UNIDADE_DELEGACIA",
                    insertable = false, updatable = false),
            @JoinColumn(name = "ID_DELEGACIA", referencedColumnName = "ID_DELEGACIA",
                    insertable = false, updatable = false) })
    @ManyToOne(optional = false)
    @Fetch(FetchMode.JOIN)
    private DelegaciaAgencias delegaciaAgencias;

    @JoinColumn(name = "ID_TIPO_PEDIDO", referencedColumnName = "ID_TIPO_PEDIDO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    @Fetch(FetchMode.JOIN)
    private PedidoTipos tipoPedido;

    @Transient
    private Collection<PedidoAreasServidores> pedidoAreasServidores;

    public PedidoAreas() {
        delegaciaAgencias = new DelegaciaAgencias();
        tipoPedido = new PedidoTipos();
        faixaValor = new PedidoAreasFaixaValor();
        pedidoAreasServidores = new ArrayList<>();
        // Construtor para inicialização por reflexão.
    }

    public PedidoAreas(
            Integer idPedidoArea, Boolean parecerFinal, SituacaoEnum situacao, Integer ordemParecer,
            Boolean exigeParecer, Integer quantidadeDiasAnalise, Boolean permiteEncaminhamento, Boolean exigeSupervisor,
            DelegaciaAgencias delegaciaAgencias, PedidoTipos tipoPedido, PedidoAreasFaixaValor faixaValor,
            Collection<PedidoAreasServidores> pedidoAreasServidores) {
        this.idPedidoArea = idPedidoArea;
        this.parecerFinal = parecerFinal;
        this.situacao = situacao;
        this.ordemParecer = ordemParecer;
        this.exigeParecer = exigeParecer;
        this.quantidadeDiasAnalise = quantidadeDiasAnalise;
        this.permiteEncaminhamento = permiteEncaminhamento;
        this.exigeSupervisor = exigeSupervisor;
        this.delegaciaAgencias = delegaciaAgencias;
        this.tipoPedido = tipoPedido;
        this.faixaValor = faixaValor;
        this.pedidoAreasServidores = pedidoAreasServidores;
    }

    @Override
    public Integer getId() {
        return getIdPedidoArea();
    }

    public Integer getIdPedidoArea() {
        return idPedidoArea;
    }

    public void setIdPedidoArea(Integer idPedidoArea) {
        this.idPedidoArea = idPedidoArea;
        this.faixaValor.setIdPedidoArea(idPedidoArea);
    }

    public Boolean getParecerFinal() {
        return parecerFinal;
    }

    public void setParecerFinal(Boolean parecerFinal) {
        this.parecerFinal = parecerFinal;
    }

    public SituacaoEnum getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoEnum situacao) {
        this.situacao = situacao;
    }

    public Integer getOrdemParecer() {
        return ordemParecer;
    }

    public void setOrdemParecer(Integer ordemParecer) {
        this.ordemParecer = ordemParecer;
    }

    public Boolean getExigeParecer() {
        return exigeParecer;
    }

    public void setExigeParecer(Boolean exigeParecer) {
        this.exigeParecer = exigeParecer;
    }

    public Integer getQuantidadeDiasAnalise() {
        return quantidadeDiasAnalise;
    }

    public void setQuantidadeDiasAnalise(Integer quantidadeDiasAnalise) {
        this.quantidadeDiasAnalise = quantidadeDiasAnalise;
    }

    public Boolean getPermiteEncaminhamento() {
        return permiteEncaminhamento;
    }

    public void setPermiteEncaminhamento(Boolean permiteEncaminhamento) {
        this.permiteEncaminhamento = permiteEncaminhamento;
    }

    public Boolean getExigeSupervisor() {
        return exigeSupervisor;
    }

    public void setExigeSupervisor(Boolean exigeSupervisor) {
        this.exigeSupervisor = exigeSupervisor;
    }

    public DelegaciaAgencias getDelegaciaAgencias() {
        return delegaciaAgencias;
    }

    public void setDelegaciaAgencias(DelegaciaAgencias delegaciaAgencias) {
        this.delegaciaAgencias = delegaciaAgencias;
    }

    public PedidoTipos getTipoPedido() {
        return tipoPedido;
    }

    public void setTipoPedido(PedidoTipos tipoPedido) {
        this.tipoPedido = tipoPedido;
    }

    public PedidoAreasFaixaValor getFaixaValor() {
        return faixaValor;
    }

    public void setFaixaValor(PedidoAreasFaixaValor faixaValor) {
        this.faixaValor = faixaValor;
    }

    public Collection<PedidoAreasServidores> getPedidoAreasServidores() {
        return pedidoAreasServidores;
    }

    public void setPedidoAreasServidores(Collection<PedidoAreasServidores> pedidoAreasServidores) {
        this.pedidoAreasServidores = pedidoAreasServidores;
    }

    public String getDelegaciaDescricao() {
        return getDelegaciaAgencias().getDelegacias().getDescricao();
    }

    public String getDelegaciaAgenciaDescricao() {
        return getDelegaciaAgencias().getDescricao();
    }

    public Integer getIdTipoPedido() {
        return idTipoPedido;
    }

    public void setIdTipoPedido(Integer idTipoPedido) {
        this.idTipoPedido = idTipoPedido;
        this.tipoPedido.setIdTipoPedido(idTipoPedido);
    }

    public Integer getIdUnidadeDelegacia() {
        return idUnidadeDelegacia;
    }

    public void setIdUnidadeDelegacia(Integer idUnidadeDelegacia) {
        this.idUnidadeDelegacia = idUnidadeDelegacia;
        this.delegaciaAgencias.setIdUnidadeDelegacia(idUnidadeDelegacia);
    }

    public Integer getIdDelegacia() {
        return idDelegacia;
    }

    public void setIdDelegacia(Integer idDelegacia) {
        this.idDelegacia = idDelegacia;
        this.delegaciaAgencias.setIdDelegacia(idDelegacia);
    }

    public BigDecimal getValorInicial() {
        return getFaixaValor().getValorInicial();
    }

    public BigDecimal getValorFinal() {
        return getFaixaValor().getValorFinal();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PedidoAreas that = (PedidoAreas) o;
        return Objects.equals(idPedidoArea, that.idPedidoArea)
                && situacao == that.situacao
                && Objects.equals(ordemParecer, that.ordemParecer)
                && Objects.equals(quantidadeDiasAnalise, that.quantidadeDiasAnalise)
                && Objects.equals(idTipoPedido, that.idTipoPedido)
                && Objects.equals(idUnidadeDelegacia, that.idUnidadeDelegacia)
                && Objects.equals(idDelegacia, that.idDelegacia)
                && Objects.equals(parecerFinal, that.parecerFinal)
                && Objects.equals(exigeParecer, that.exigeParecer)
                && Objects.equals(permiteEncaminhamento, that.permiteEncaminhamento)
                && Objects.equals(exigeSupervisor, that.exigeSupervisor)
                && Objects.equals(delegaciaAgencias, that.delegaciaAgencias)
                && Objects.equals(tipoPedido, that.tipoPedido)
                && Objects.equals(faixaValor, that.faixaValor)
                && Objects.equals(pedidoAreasServidores, that.pedidoAreasServidores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPedidoArea, situacao, ordemParecer, quantidadeDiasAnalise, idTipoPedido,
                idUnidadeDelegacia, idDelegacia, parecerFinal, exigeParecer, permiteEncaminhamento,
                exigeSupervisor, delegaciaAgencias, tipoPedido, faixaValor, pedidoAreasServidores);
    }

    @Override
    public String toString() {
        return "PedidoAreas{"
                + "idPedidoArea=" + idPedidoArea
                + ", situacao=" + situacao
                + ", ordemParecer=" + ordemParecer
                + ", quantidadeDiasAnalise=" + quantidadeDiasAnalise
                + ", idTipoPedido=" + idTipoPedido
                + ", idUnidadeDelegacia=" + idUnidadeDelegacia
                + ", idDelegacia=" + idDelegacia
                + ", parecerFinal=" + parecerFinal
                + ", exigeParecer=" + exigeParecer
                + ", permiteEncaminhamento=" + permiteEncaminhamento
                + ", exigeSupervisor=" + exigeSupervisor
                + ", delegaciaAgencias=" + delegaciaAgencias
                + ", tipoPedido=" + tipoPedido
                + ", faixaValor=" + faixaValor
                + ", pedidoAreasServidores=" + pedidoAreasServidores
                + '}';
    }
}