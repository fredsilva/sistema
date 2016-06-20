package br.gov.to.sefaz.arr.parametros.business.service;

import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoAreas;
import br.gov.to.sefaz.business.service.CrudService;

import java.util.Collection;

/**
 * Serviço para gerencia de dados de {@link PedidoAreas}.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 21/05/2016 10:43:00
 */
public interface PedidoAreasService extends CrudService<PedidoAreas, Integer> {
    /**
     * Busca todos os PedidoAreas pelo tipoPedido.
     * @param idTipoPedido identificação do Tipo Pedido.
     * @return Lista de Pedido Areas.
     */
    Collection<PedidoAreas> findAllByTipo(Integer idTipoPedido);
}
