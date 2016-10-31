package br.gov.to.sefaz.arr.parametros.business.service;

import br.gov.to.sefaz.arr.persistence.entity.PedidoAreasServidores;
import br.gov.to.sefaz.arr.persistence.entity.PedidoAreasServidoresPK;
import br.gov.to.sefaz.business.service.CrudService;

import java.util.Collection;
import java.util.List;

/**
 * Serviço para gerencia de dados de {@link PedidoAreasServidores}.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 21/05/2016 10:37:00
 */
public interface PedidoAreasServidoresService extends CrudService<PedidoAreasServidores, PedidoAreasServidoresPK> {

    /**
     * Busca todos os PedidoAreaServidores pelo Id do PedidoArea.
     * @param idPedidoArea identificação do PedidoArea
     * @return Lista de PedidoAreasServidores.
     */
    Collection<PedidoAreasServidores> findAllByPedido(Integer idPedidoArea);

    /**
     * Valida Servidores duplicados na lista.
     * @param servidores lista de PedidoAreasServidores.
     */
    void validateDuplicatedServidor(List<PedidoAreasServidores> servidores);

    /**
     * Valida o servidor chefe.
     * @param servidores lista de servidores.
     */
    void validateServidorChefe(List<PedidoAreasServidores> servidores);

    /**
     * Remove pela identificação do PedidoArea.
     * @param idPedidoArea identificação do PedidoArea.
     */
    void deleteByIdPedidoArea(Integer idPedidoArea);
}
