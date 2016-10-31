package br.gov.to.sefaz.arr.persistence.entity;

import br.gov.to.sefaz.persistence.converter.SituacaoEnumConverter;
import br.gov.to.sefaz.persistence.entity.AbstractEntity;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;

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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Entidade que representa os dados da tabela SEFAZ_ARR.TA_PEDIDO_RECEITA.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 24/05/2016 18:42:44
 */
@Entity
@Table(name = "TA_PEDIDO_RECEITA", schema = "SEFAZ_ARR")
@IdClass(PedidoReceitaPK.class)
public class PedidoReceita extends AbstractEntity<PedidoReceitaPK> {

    private static final String ID_RECEITA = "ID_RECEITA";
    private static final long serialVersionUID = -8910116307874043996L;

    @Id
    @Min(value = 1, message = "#{arr_msg['parametros.pedidoReceita.idTipoPedido.minimo']}")
    @Max(value = 9999, message = "#{arr_msg['parametros.pedidoReceita.idTipoPedido.maximo']}")
    @NotNull(message = "#{arr_msg['parametros.pedidoReceita.idTipoPedido.obrigatorio']}")
    @Column(name = "ID_TIPO_PEDIDO")
    private Integer idTipoPedido;

    @Id
    @Min(value = 1, message = "#{arr_msg['parametros.pedidoReceita.idReceita.minimo']}")
    @Max(value = 9999, message = "#{arr_msg['parametros.pedidoReceita.idReceita.maximo']}")
    @NotNull(message = "#{arr_msg['parametros.pedidoReceita.idReceita.obrigatorio']}")
    @Column(name = ID_RECEITA)
    private Integer idReceita;

    @Id
    @Min(value = 1, message = "#{arr_msg['parametros.pedidoReceita.idSubcodigo.minimo']}")
    @Max(value = 99999, message = "#{arr_msg['parametros.pedidoReceita.idSubcodigo.maximo']}")
    @NotNull(message = "#{arr_msg['parametros.pedidoReceita.idSubcodigo.obrigatorio']}")
    @Column(name = "ID_SUBCODIGO")
    private Integer idSubcodigo;

    @NotNull(message = "#{arr_msg['parametros.pedidoReceita.situacao.obrigatorio']}")
    @Convert(converter = SituacaoEnumConverter.class)
    @Column(name = "SITUACAO")
    private SituacaoEnum situacao;

    @JoinColumn(name = "ID_TIPO_PEDIDO", referencedColumnName = "ID_TIPO_PEDIDO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PedidoTipos pedidoTipos;

    @JoinColumn(name = ID_RECEITA, referencedColumnName = ID_RECEITA, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Receitas receitas;

    @JoinColumns({
            @JoinColumn(name = "ID_SUBCODIGO", referencedColumnName = "ID_SUBCODIGO", insertable = false,
                    updatable = false),
            @JoinColumn(name = ID_RECEITA, referencedColumnName = ID_RECEITA, insertable = false, updatable = false) })
    @ManyToOne(optional = false)
    private ReceitasTaxas receitasTaxas;

    @Override
    public PedidoReceitaPK getId() {
        return new PedidoReceitaPK(getIdTipoPedido(), getIdReceita(), getIdSubcodigo());
    }

    public Integer getIdTipoPedido() {
        return idTipoPedido;
    }

    public void setIdTipoPedido(Integer idTipoPedido) {
        this.idTipoPedido = idTipoPedido;
    }

    public Integer getIdReceita() {
        return idReceita;
    }

    public void setIdReceita(Integer idReceita) {
        this.idReceita = idReceita;
    }

    public Integer getIdSubcodigo() {
        return idSubcodigo;
    }

    public void setIdSubcodigo(Integer idSubcodigo) {
        this.idSubcodigo = idSubcodigo;
    }

    public SituacaoEnum getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoEnum situacao) {
        this.situacao = situacao;
    }

    public PedidoTipos getPedidoTipos() {
        return pedidoTipos;
    }

    public void setPedidoTipos(PedidoTipos taPedidoTipos) {
        this.pedidoTipos = taPedidoTipos;
    }

    public Receitas getReceitas() {
        return receitas;
    }

    public void setReceitas(Receitas receitas) {
        this.receitas = receitas;
    }

    public ReceitasTaxas getReceitasTaxas() {
        return receitasTaxas;
    }

    public void setReceitasTaxas(ReceitasTaxas receitasTaxas) {
        this.receitasTaxas = receitasTaxas;
    }

    public String getReceitaLabel() {
        return receitas.getIdReceita() + " - " + receitas.getDescricaoReceita();
    }

    public String getReceitaTaxaLabel() {
        return receitasTaxas.getSubcodigo() + " - " + receitasTaxas.getDescricao();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PedidoReceita that = (PedidoReceita) o;
        return Objects.equals(idTipoPedido, that.idTipoPedido)
                && Objects.equals(idReceita, that.idReceita)
                && Objects.equals(idSubcodigo, that.idSubcodigo)
                && Objects.equals(situacao, that.situacao)
                && Objects.equals(pedidoTipos, that.pedidoTipos)
                && Objects.equals(receitas, that.receitas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTipoPedido, idReceita, idSubcodigo, situacao, pedidoTipos, receitas);
    }

    @Override
    public String toString() {
        return "PedidoReceita{"
                + "idTipoPedido=" + idTipoPedido
                + ", idReceita=" + idReceita
                + ", idSubcodigo=" + idSubcodigo
                + ", situacao=" + situacao
                + ", pedidoTipos=" + pedidoTipos
                + ", receitas=" + receitas
                + '}';
    }
}
