package br.gov.to.sefaz.arr.parametros.business.service;

import br.gov.to.sefaz.arr.persistence.entity.PedidoTipoAcoes;
import br.gov.to.sefaz.arr.persistence.entity.PedidoTipos;
import br.gov.to.sefaz.business.service.CrudService;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;

/**
 * Contrato de acesso do serviço de {@link br.gov.to.sefaz.arr.persistence.entity.PedidoTipoAcoes}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 24/05/2016 15:56:00
 */
public interface PedidoTipoAcoesService extends CrudService<PedidoTipoAcoes, Integer> {

    /**
     * Remove todos os registros de {@link PedidoTipoAcoes} de um determinado {@link PedidoTipos}, que corresponde ao
     * atributo {@link PedidoTipoAcoes#idTipoPedido}.
     *
     * @param idTipoPedido Código do tipo de pedido
     */
    void deleteAllTipoAcoesByIdTipoPedido(Integer idTipoPedido);

    /**
     * Atualiza a situação.
     * @param idTipoPedido identificação do TipoPedido.
     * @param situacao nova situação.
     */
    void updateSituacaoByIdTipoPedido(Integer idTipoPedido, SituacaoEnum situacao);

}
