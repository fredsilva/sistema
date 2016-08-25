package br.gov.to.sefaz.arr.persistence.entity;

import br.gov.to.sefaz.persistence.converter.OneOrTwoBooleanConverter;
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Entidade que representa os dados da tabela SEFAZ_ARR.TA_PEDIDO_DOCS_EXIGIDOS.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 24/05/2016 14:46:39
 */
@Entity
@Table(name = "TA_PEDIDO_DOCS_EXIGIDOS", schema = "SEFAZ_ARR")
@IdClass(PedidoDocsExigidosPK.class)
public class PedidoDocsExigidos extends AbstractEntity<PedidoDocsExigidosPK> {

    private static final long serialVersionUID = 4912705030100766022L;

    @Id
    @Min(value = 1, message = "#{arr_msg['parametros.PedidoDocsExigidos.idTipoPedido.minimo']}")
    @Max(value = 99, message = "#{arr_msg['parametros.PedidoDocsExigidos.idTipoPedido.maximo']}")
    @NotNull(message = "#{arr_msg['parametros.PedidoDocsExigidos.idTipoPedido.obrigatorio']}")
    @Column(name = "ID_TIPO_PEDIDO")
    private Integer idTipoPedido;

    @Id
    @Min(value = 1, message = "#{arr_msg['parametros.PedidoDocsExigidos.idTipoDocs.minimo']}")
    @Max(value = 99, message = "#{arr_msg['parametros.PedidoDocsExigidos.idTipoDocs.maximo']}")
    @NotNull(message = "#{arr_msg['parametros.PedidoDocsExigidos.idTipoDocs.obrigatorio']}")
    @Column(name = "ID_TIPO_DOCS")
    private Integer idTipoDocs;

    @NotNull(message = "#{arr_msg['parametros.PedidoDocsExigidos.idTipoDocs.docObrigatorio']}")
    @Convert(converter = OneOrTwoBooleanConverter.class)
    @Column(name = "DOC_OBRIGATORIO")
    private Boolean docObrigatorio;

    @NotNull(message = "#{arr_msg['parametros.PedidoDocsExigidos.idTipoDocs.situacao']}")
    @Convert(converter = SituacaoEnumConverter.class)
    @Column(name = "SITUACAO")
    private SituacaoEnum situacao;

    @JoinColumn(name = "ID_TIPO_DOCS", referencedColumnName = "ID_TIPO_DOCS", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PedidoTipoDocs pedidoTipoDocs;

    @JoinColumn(name = "ID_TIPO_PEDIDO", referencedColumnName = "ID_TIPO_PEDIDO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PedidoTipos pedidoTipos;

    @Override
    public PedidoDocsExigidosPK getId() {
        return new PedidoDocsExigidosPK(getIdTipoPedido(), getIdTipoDocs());
    }

    public Integer getIdTipoPedido() {
        return idTipoPedido;
    }

    public void setIdTipoPedido(Integer idTipoPedido) {
        this.idTipoPedido = idTipoPedido;
    }

    public Integer getIdTipoDocs() {
        return idTipoDocs;
    }

    public void setIdTipoDocs(Integer idTipoDocs) {
        this.idTipoDocs = idTipoDocs;
    }

    public Boolean getDocObrigatorio() {
        return docObrigatorio;
    }

    public void setDocObrigatorio(Boolean docObrigatorio) {
        this.docObrigatorio = docObrigatorio;
    }

    public SituacaoEnum getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoEnum situacao) {
        this.situacao = situacao;
    }

    public PedidoTipoDocs getPedidoTipoDocs() {
        return pedidoTipoDocs;
    }

    public void setPedidoTipoDocs(PedidoTipoDocs pedidoTipoDocs) {
        this.pedidoTipoDocs = pedidoTipoDocs;
    }

    public PedidoTipos getPedidoTipos() {
        return pedidoTipos;
    }

    public void setPedidoTipos(PedidoTipos pedidoTipos) {
        this.pedidoTipos = pedidoTipos;
    }

    public String getTipoPedidoDescricao() {
        return getPedidoTipoDocs().getDescricao();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PedidoDocsExigidos that = (PedidoDocsExigidos) o;
        return Objects.equals(idTipoPedido, that.idTipoPedido)
                && Objects.equals(idTipoDocs, that.idTipoDocs)
                && Objects.equals(docObrigatorio, that.docObrigatorio)
                && situacao == that.situacao
                && Objects.equals(pedidoTipoDocs, that.pedidoTipoDocs)
                && Objects.equals(pedidoTipos, that.pedidoTipos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTipoPedido, idTipoDocs, docObrigatorio, situacao, pedidoTipoDocs, pedidoTipos);
    }

    @Override
    public String toString() {
        return "PedidoDocsExigidos{"
                + "idTipoPedido=" + idTipoPedido
                + ", idTipoDocs=" + idTipoDocs
                + ", docObrigatorio=" + docObrigatorio
                + ", situacao=" + situacao
                + ", pedidoTipoDocs=" + pedidoTipoDocs
                + ", pedidoTipos=" + pedidoTipos
                + '}';
    }
}
