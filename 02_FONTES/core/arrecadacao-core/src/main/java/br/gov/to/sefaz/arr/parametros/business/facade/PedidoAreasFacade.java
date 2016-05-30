package br.gov.to.sefaz.arr.parametros.business.facade;

import br.gov.to.sefaz.arr.parametros.persistence.entity.DelegaciaAgencias;
import br.gov.to.sefaz.arr.parametros.persistence.entity.Delegacias;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoAreas;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoAreasServidores;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoTipos;
import br.gov.to.sefaz.business.facade.CrudFacade;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Fachada das funcionalidades de Parametrizar Tipos de Pedidos de Áreas.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 21/05/2016 10:55:00
 */
public interface PedidoAreasFacade extends CrudFacade<PedidoAreas, Integer> {

    Collection<PedidoTipos> findAllPedidoTipos();

    Collection<PedidoAreas> findAllByTipo(Integer idTipoPedido);

    Collection<Delegacias> findAllDelegacias();

    Collection<DelegaciaAgencias> findAgenciasByDelegacia(Integer idDelegacia);

    Collection<PedidoAreasServidores> findAllServidoresByPedido(Integer idPedidoArea);

    Optional<PedidoAreasServidores> removeServidor(Integer idPedidoArea, Long idServidor);

    Collection<UsuarioSistema> searchServidor(Long idServidorSearchDto, String nomeServidorSearchDto);

    void validateDuplicatedServidor(List<PedidoAreasServidores> servidores);

    void validateServidorChefe(List<PedidoAreasServidores> servidores);
}
