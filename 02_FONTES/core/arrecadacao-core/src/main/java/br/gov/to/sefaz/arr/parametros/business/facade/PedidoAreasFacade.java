package br.gov.to.sefaz.arr.parametros.business.facade;

import br.gov.to.sefaz.arr.persistence.entity.DelegaciaAgencias;
import br.gov.to.sefaz.arr.persistence.entity.Delegacias;
import br.gov.to.sefaz.arr.persistence.entity.PedidoAreas;
import br.gov.to.sefaz.arr.persistence.entity.PedidoAreasServidores;
import br.gov.to.sefaz.arr.persistence.entity.PedidoTipos;
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

    /**
     * Busca todos os pedido tipos.
     * @return lista de Pedido tipos
     */
    Collection<PedidoTipos> findAllPedidoTipos();

    /**
     * Busca todos os Pedido Areas pelo ID do Tipo Pedido.
     * @param idTipoPedido do Tipo Pedido
     * @return lista de PedidoAreas.
     */
    Collection<PedidoAreas> findAllByTipo(Integer idTipoPedido);

    /**
     * Busca todas as delegacias.
     * @return lista de Delegacias.
     */
    Collection<Delegacias> findAllDelegacias();

    /**
     * Busca as agências pelo ID da delegacia.
     * @param idDelegacia identificação da delegacia.
     * @return lista de Delegacia Agencias.
     */
    Collection<DelegaciaAgencias> findAgenciasByDelegacia(Integer idDelegacia);

    /**
     * Busca todos os servidores pelo Id do PedidoArea.
     * @param idPedidoArea identificação do PedidoArea.
     * @return Lista de Servidores.
     */
    Collection<PedidoAreasServidores> findAllServidoresByPedido(Integer idPedidoArea);

    /**
     * Remove o servidor.
     * @param idPedidoArea identificação do PedidoArea.
     * @param idServidor identificação do Servidor.
     * @return Objeto PedidoAreasServidores.
     */
    Optional<PedidoAreasServidores> removeServidor(Integer idPedidoArea, Long idServidor);

    /**
     * Busca servidor pelo Id e nome.
     * @param idServidorSearchDto identificação do servidor.
     * @param nomeServidorSearchDto nome do servidor.
     * @return lista de usuario sistema.
     */
    Collection<UsuarioSistema> searchServidor(Long idServidorSearchDto, String nomeServidorSearchDto);

    /**
     * Valida servidores duplicados.
     * @param servidores lista de servidores para ser verificada.
     */
    void validateDuplicatedServidor(List<PedidoAreasServidores> servidores);

    /**
     * Valida o servidor chefe.
     * @param servidores lista de servidores para ser verificada.
     */
    void validateServidorChefe(List<PedidoAreasServidores> servidores);
}
