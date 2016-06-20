package br.gov.to.sefaz.arr.parametros.persistence.entity;

import br.gov.to.sefaz.persistence.converter.OneOrTwoBooleanConverter;
import br.gov.to.sefaz.persistence.converter.SituacaoEnumConverter;
import br.gov.to.sefaz.persistence.entity.AbstractEntity;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entidade que representa os dados da tabela SEFAZ_ARR.TA_PEDIDO_TIPOS.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 20/05/2016 15:18:05
 */
@Entity
@Table(name = "TA_PEDIDO_TIPOS", schema = "SEFAZ_ARR")
public class PedidoTipos extends AbstractEntity<Integer> {

    private static final long serialVersionUID = 8593609770741209977L;

    @Id
    @Min(value = 1, message = "#{arr_msg['parametros.pedidoTipos.idTipoPedido.minimo']}")
    @Max(value = 99, message = "#{arr_msg['parametros.pedidoTipos.idTipoPedido.maximo']}")
    @NotNull(message = "#{arr_msg['parametros.pedidoTipos.idTipoPedido.obrigatorio']}")
    @Column(name = "ID_TIPO_PEDIDO")
    private Integer idTipoPedido;

    @NotNull(message = "#{arr_msg['parametros.pedidoTipos.descricao.obrigatorio']}")
    @Size(min = 1, message = "#{arr_msg['parametros.pedidoTipos.descricao.obrigatorio']}")
    @Column(name = "DESCRICAO", length = 150)
    private String descricao;

    @NotNull(message = "#{arr_msg['parametros.pedidoTipos.parecerAutomatico.obrigatorio']}")
    @Convert(converter = OneOrTwoBooleanConverter.class)
    @Column(name = "PARECER_AUTOMATICO")
    private Boolean parecerAutomatico;

    @NotNull(message = "#{arr_msg['parametros.pedidoTipos.situacao.obrigatorio']}")
    @Convert(converter = SituacaoEnumConverter.class)
    @Column(name = "SITUACAO")
    private SituacaoEnum situacao;

    @NotNull(message = "#{arr_msg['parametros.pedidoTipos.valorMinimo.obrigatorio']}")
    @DecimalMin(value = "0.01", message = "#{arr_msg['parametros.pedidoTipos.valorMinimo.minimo']}")
    @DecimalMax(value = "999999999999.99", message = "#{arr_msg['parametros.pedidoTipos.valorMinimo.maximo']}")
    @Column(name = "VALOR_MINIMO")
    private BigDecimal valorMinimo;

    @NotNull(message = "#{arr_msg['parametros.pedidoTipos.quantidadeDiasAnalise.obrigatorio']}")
    @Min(value = 1, message = "#{arr_msg['parametros.pedidoTipos.quantidadeDiasAnalise.minimo']}")
    @Max(value = 99, message = "#{arr_msg['parametros.pedidoTipos.quantidadeDiasAnalise.maximo']}")
    @Column(name = "QUANTIDADE_DIAS_ANALISE")
    private Integer quantidadeDiasAnalise;

    @Transient
    private Collection<PedidoAreas> pedidoAreasCollection;

    @Transient
    private List<PedidoDocsExigidos> pedidoDocsExigidos;

    @Transient
    private List<PedidoCamposAcoes> pedidoCamposAcoes;

    @Transient
    private List<PedidoReceita> pedidoReceitas;

    public PedidoTipos() {
        pedidoCamposAcoes = new ArrayList<>();
        pedidoReceitas = new ArrayList<>();
        pedidoDocsExigidos = new ArrayList<>();
    }

    @Override
    public Integer getId() {
        return getIdTipoPedido();
    }

    public Integer getIdTipoPedido() {
        return idTipoPedido;
    }

    public void setIdTipoPedido(Integer idTipoPedido) {
        this.idTipoPedido = idTipoPedido;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getParecerAutomatico() {
        return parecerAutomatico;
    }

    public void setParecerAutomatico(Boolean parecerAutomatico) {
        this.parecerAutomatico = parecerAutomatico;
    }

    public SituacaoEnum getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoEnum situacao) {
        this.situacao = situacao;
    }

    public BigDecimal getValorMinimo() {
        return valorMinimo;
    }

    public void setValorMinimo(BigDecimal valorMinimo) {
        this.valorMinimo = valorMinimo;
    }

    public Integer getQuantidadeDiasAnalise() {
        return quantidadeDiasAnalise;
    }

    public void setQuantidadeDiasAnalise(Integer quantidadeDiasAnalise) {
        this.quantidadeDiasAnalise = quantidadeDiasAnalise;
    }

    public Collection<PedidoAreas> getPedidoAreasCollection() {
        return pedidoAreasCollection;
    }

    public void setPedidoAreasCollection(Collection<PedidoAreas> pedidoAreasCollection) {
        this.pedidoAreasCollection = pedidoAreasCollection;
    }

    public List<PedidoCamposAcoes> getPedidoCamposAcoes() {
        return pedidoCamposAcoes;
    }

    public void setPedidoCamposAcoes(List<PedidoCamposAcoes> pedidoCamposAcoes) {
        this.pedidoCamposAcoes = pedidoCamposAcoes;
    }

    public List<PedidoReceita> getPedidoReceitas() {
        return pedidoReceitas;
    }

    public void setPedidoReceitas(List<PedidoReceita> pedidoReceitas) {
        this.pedidoReceitas = pedidoReceitas;
    }

    public List<PedidoDocsExigidos> getPedidoDocsExigidos() {
        return pedidoDocsExigidos;
    }

    public void setPedidoDocsExigidos(List<PedidoDocsExigidos> pedidoDocsExigidos) {
        this.pedidoDocsExigidos = pedidoDocsExigidos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PedidoTipos that = (PedidoTipos) o;
        return Objects.equals(idTipoPedido, that.idTipoPedido)
                && Objects.equals(descricao, that.descricao)
                && Objects.equals(parecerAutomatico, that.parecerAutomatico)
                && situacao == that.situacao
                && Objects.equals(valorMinimo, that.valorMinimo)
                && Objects.equals(quantidadeDiasAnalise, that.quantidadeDiasAnalise)
                && Objects.equals(pedidoAreasCollection, that.pedidoAreasCollection)
                && Objects.equals(pedidoCamposAcoes, that.pedidoCamposAcoes)
                && Objects.equals(pedidoReceitas, that.pedidoReceitas)
                && Objects.equals(pedidoDocsExigidos, that.pedidoDocsExigidos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTipoPedido, descricao, parecerAutomatico, situacao, valorMinimo, quantidadeDiasAnalise,
                pedidoAreasCollection, pedidoCamposAcoes, pedidoReceitas, pedidoDocsExigidos);
    }

    @Override
    public String toString() {
        return "PedidoTipos{"
                + "idTipoPedido=" + idTipoPedido
                + ", descricao='" + descricao + '\''
                + ", parecerAutomatico=" + parecerAutomatico
                + ", situacao=" + situacao
                + ", valorMinimo=" + valorMinimo
                + ", quantidadeDiasAnalise=" + quantidadeDiasAnalise
                + ", pedidoAreasCollection=" + pedidoAreasCollection
                + ", pedidoCamposAcoes=" + pedidoCamposAcoes
                + ", pedidoReceitas=" + pedidoReceitas
                + ", pedidoDocsExigidos=" + pedidoDocsExigidos
                + '}';
    }
}
