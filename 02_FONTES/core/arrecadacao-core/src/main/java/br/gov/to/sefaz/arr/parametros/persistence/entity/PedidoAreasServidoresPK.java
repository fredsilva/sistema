package br.gov.to.sefaz.arr.parametros.persistence.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Chave composta da entidade {@link PedidoAreasServidores}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 20/05/2016 15:18:05
 */
public class PedidoAreasServidoresPK implements Serializable {

    private static final Long serialVersionUID = 4364731474783277921L;
    private Integer idPedidoArea;
    private Long idServidor;

    public PedidoAreasServidoresPK() {
        // Construtor para inicialização por reflexão.
    }

    public PedidoAreasServidoresPK(Integer idPedidoArea, Long idServidor) {
        this.idPedidoArea = idPedidoArea;
        this.idServidor = idServidor;
    }

    public Integer getIdPedidoArea() {
        return idPedidoArea;
    }

    public void setIdPedidoArea(Integer idPedidoArea) {
        this.idPedidoArea = idPedidoArea;
    }

    public Long getIdServidor() {
        return idServidor;
    }

    public void setIdServidor(Long idServidor) {
        this.idServidor = idServidor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PedidoAreasServidoresPK that = (PedidoAreasServidoresPK) o;
        return Objects.equals(idPedidoArea, that.idPedidoArea)
                && Objects.equals(idServidor, that.idServidor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPedidoArea, idServidor);
    }

    @Override
    public String toString() {
        return "PedidoAreasServidoresPK{"
                + "idPedidoArea=" + idPedidoArea
                + ", idServidor=" + idServidor
                + '}';
    }
}
