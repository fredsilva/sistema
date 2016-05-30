package br.gov.to.sefaz.arr.parametros.business.service;

import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoAreasServidores;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoAreasServidoresPK;
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

    Collection<PedidoAreasServidores> findAllByPedido(Integer idPedidoArea);

    void validateDuplicatedServidor(List<PedidoAreasServidores> servidores);

    void validateServidorChefe(List<PedidoAreasServidores> servidores);

    void deleteByIdPedidoArea(Integer idPedidoArea);
}
