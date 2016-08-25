package br.gov.to.sefaz.arr.parametros.business.service;

import br.gov.to.sefaz.arr.persistence.entity.PedidoCamposAcoes;
import br.gov.to.sefaz.arr.persistence.entity.PedidoTipos;
import br.gov.to.sefaz.business.service.CrudService;

import java.util.Collection;

/**
 * Contrato de acesso do serviço de {@link br.gov.to.sefaz.arr.persistence.entity.PedidoCamposAcoes}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 25/05/2016 18:32:00
 */
public interface PedidoCamposAcoesService extends CrudService<PedidoCamposAcoes, Integer> {
    /**
     * Busca o PedidoCampoAcoes pela identificação do Tipo Pedido.
     * @param idTipoPedido identificação do TipoPedido.
     * @return Lista de PedidoCamposAcoes.
     */
    Collection<PedidoCamposAcoes> getPedidoCamposAcoesByIdTipoPedido(Integer idTipoPedido);

    /**
     * Remove todos os registros de {@link PedidoCamposAcoes} de um determinado {@link PedidoTipos}, que corresponde ao
     * Tipo de Pedido.
     *
     * @param idTipoPedido código do tipo de pedido
     */
    void deleteAllTipoAcoesByIdTipoPedido(Integer idTipoPedido);

}
