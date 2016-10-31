package br.gov.to.sefaz.arr.persistence.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Entidade referente a primary key da tabela SEFAZ_ARR.TA_PEDIDO_RECEITA do Banco de Dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 24/05/2016 18:42:44
 */
public class PedidoReceitaPK implements Serializable {

    private static final long serialVersionUID = 5296371012589221179L;

    private Integer idTipoPedido;
    private Integer idReceita;
    private Integer idSubcodigo;

    public PedidoReceitaPK() {
        // Construtor para inicialização por reflexão.

    }

    public PedidoReceitaPK(Integer idTipoPedido, Integer idReceita, Integer idSubcodigo) {
        this.idTipoPedido = idTipoPedido;
        this.idReceita = idReceita;
        this.idSubcodigo = idSubcodigo;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PedidoReceitaPK that = (PedidoReceitaPK) o;
        return Objects.equals(idTipoPedido, that.idTipoPedido)
                && Objects.equals(idReceita, that.idReceita)
                && Objects.equals(idSubcodigo, that.idSubcodigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTipoPedido, idReceita, idSubcodigo);
    }

    @Override
    public String toString() {
        return "PedidoReceitaPK{"
                + "idTipoPedido=" + idTipoPedido
                + ", idReceita=" + idReceita
                + ", idSubcodigo=" + idSubcodigo
                + '}';
    }
}
