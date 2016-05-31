package br.gov.to.sefaz.arr.parametros.business.service;

import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoTipoDocs;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoTipos;
import br.gov.to.sefaz.arr.parametros.persistence.enums.TipoPedidoAcoesEnum;
import br.gov.to.sefaz.arr.parametros.persistence.enums.TipoPedidoCampoEnum;
import br.gov.to.sefaz.business.service.CrudService;

import java.util.List;

/**
 * Contrato de acesso do serviço de {@link br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoTipoDocs}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 24/05/2016 15:57:00
 */
public interface PedidoTipoDocsService extends CrudService<PedidoTipoDocs, Integer> {

    /**
     * Retorna a lista de Tipos de Campos conforme {@link PedidoTipos#getIdTipoPedido()} e {@link TipoPedidoAcoesEnum}.
     *
     * @param idTipoPedido Código do Tipo de Pedido
     * @param tipoPedido tipo de pedido
     * @return Lista de Tipos de Campos
     */
    List<TipoPedidoCampoEnum> getTipoPedidoCampoEnumValues(Integer idTipoPedido, TipoPedidoAcoesEnum tipoPedido);
}
