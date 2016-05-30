package br.gov.to.sefaz.arr.parametros.persistence.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Entidade referente a primary key da tabela SEFAZ_ARR.TA_PEDIDO_DOCS_EXIGIDOS do Banco de Dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 24/05/2016 14:46:39
 */
public class PedidoDocsExigidosPK implements Serializable {

    private static final long serialVersionUID = 2079583220986989785L;

    private Integer idTipoPedido;
    private Integer idTipoDocs;

    public PedidoDocsExigidosPK() {
        // Construtor para inicialização por reflexão.
    }

    public PedidoDocsExigidosPK(Integer idTipoPedido, Integer idTipoDocs) {
        this.idTipoPedido = idTipoPedido;
        this.idTipoDocs = idTipoDocs;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PedidoDocsExigidosPK that = (PedidoDocsExigidosPK) o;
        return Objects.equals(idTipoPedido, that.idTipoPedido)
                && Objects.equals(idTipoDocs, that.idTipoDocs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTipoPedido, idTipoDocs);
    }

    @Override
    public String toString() {
        return "PedidoDocsExigidosPK{"
                + "idTipoPedido=" + idTipoPedido
                + ", idTipoDocs=" + idTipoDocs
                + '}';
    }
}
