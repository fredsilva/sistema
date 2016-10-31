package br.gov.to.sefaz.arr.persistence.entity;

import br.gov.to.sefaz.arr.persistence.converter.TipoPedidoAcoesEnumConverter;
import br.gov.to.sefaz.arr.persistence.enums.TipoPedidoAcoesEnum;
import br.gov.to.sefaz.persistence.converter.SituacaoEnumConverter;
import br.gov.to.sefaz.persistence.entity.AbstractEntity;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Entidade que representa os dados da tabela SEFAZ_ARR.TA_PEDIDO_TIPO_ACOES.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 24/05/2016 14:46:38
 */
@Entity
@Table(name = "TA_PEDIDO_TIPO_ACOES", schema = "SEFAZ_ARR")
public class PedidoTipoAcoes extends AbstractEntity<Integer> {

    private static final long serialVersionUID = 1428689078772321814L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PEDIDO_TIPO_ACOES")
    @SequenceGenerator(name = "SQ_PEDIDO_TIPO_ACOES", schema = "SEFAZ_ARR", sequenceName = "SQ_PEDIDO_TIPO_ACOES",
            allocationSize = 1)
    @Column(name = "ID_ACOES")
    private Integer idAcoes;

    @NotNull(message = "#{arr_msg['parametros.pedidoTipoAcoes.tipoAcao.obrigatorio']}")
    @Convert(converter = TipoPedidoAcoesEnumConverter.class)
    @Column(name = "TIPO_ACAO")
    private TipoPedidoAcoesEnum tipoAcao;

    @NotNull(message = "#{arr_msg['parametros.pedidoTipoAcoes.situacao.obrigatorio']}")
    @Convert(converter = SituacaoEnumConverter.class)
    @Column(name = "SITUACAO")
    private SituacaoEnum situacao;

    @Min(value = 1, message = "#{arr_msg['parametros.pedidoTipoAcoes.idTipoPedido.minimo']}")
    @Max(value = 99, message = "#{arr_msg['parametros.pedidoTipoAcoes.idTipoPedido.maximo']}")
    @NotNull(message = "#{arr_msg['parametros.pedidoTipoAcoes.idTipoPedido.obrigatorio']}")
    @Column(name = "ID_TIPO_PEDIDO")
    private Integer idTipoPedido;

    @JoinColumn(name = "ID_TIPO_PEDIDO", referencedColumnName = "ID_TIPO_PEDIDO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PedidoTipos pedidoTipos;

    @Override
    public Integer getId() {
        return getIdAcoes();
    }

    public Integer getIdAcoes() {
        return idAcoes;
    }

    public void setIdAcoes(Integer idAcoes) {
        this.idAcoes = idAcoes;
    }

    public TipoPedidoAcoesEnum getTipoAcao() {
        return tipoAcao;
    }

    public void setTipoAcao(TipoPedidoAcoesEnum tipoAcao) {
        this.tipoAcao = tipoAcao;
    }

    public SituacaoEnum getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoEnum situacao) {
        this.situacao = situacao;
    }

    @Override
    public String getUsuarioInsercao() {
        return usuarioInsercao;
    }

    @Override
    public void setUsuarioInsercao(String usuarioInsercao) {
        this.usuarioInsercao = usuarioInsercao;
    }

    public Integer getIdTipoPedido() {
        return idTipoPedido;
    }

    public void setIdTipoPedido(Integer idTipoPedido) {
        this.idTipoPedido = idTipoPedido;
    }

    public PedidoTipos getPedidoTipos() {
        return pedidoTipos;
    }

    public void setPedidoTipos(PedidoTipos pedidoTipos) {
        this.pedidoTipos = pedidoTipos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PedidoTipoAcoes that = (PedidoTipoAcoes) o;
        return Objects.equals(idAcoes, that.idAcoes)
                && Objects.equals(tipoAcao, that.tipoAcao)
                && situacao == that.situacao
                && Objects.equals(idTipoPedido, that.idTipoPedido)
                && Objects.equals(pedidoTipos, that.pedidoTipos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAcoes, tipoAcao, situacao, idTipoPedido, pedidoTipos);
    }

    @Override
    public String toString() {
        return "PedidoTipoAcoes{"
                + "idAcoes=" + idAcoes
                + ", tipoAcao=" + tipoAcao
                + ", situacao=" + situacao
                + ", idTipoPedido=" + idTipoPedido
                + ", pedidoTipos=" + pedidoTipos
                + '}';
    }
}
