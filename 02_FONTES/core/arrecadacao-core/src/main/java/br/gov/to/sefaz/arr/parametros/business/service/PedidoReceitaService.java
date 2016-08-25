package br.gov.to.sefaz.arr.parametros.business.service;

import br.gov.to.sefaz.arr.persistence.entity.PedidoReceita;
import br.gov.to.sefaz.arr.persistence.entity.PedidoReceitaPK;
import br.gov.to.sefaz.business.service.CrudService;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;

import java.util.Collection;

/**
 * Contrato de acesso do serviço de {@link br.gov.to.sefaz.arr.persistence.entity.PedidoReceita}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 25/05/2016 20:30:00
 */
public interface PedidoReceitaService extends CrudService<PedidoReceita, PedidoReceitaPK> {

    /**
     * Remove todos os PedidoReceita.
     * @param idTipoPedido identificação do TipoPedido.
     */
    void deleteAllPedidoReceitaByIdTipoPedido(Integer idTipoPedido);

    /**
     * Busca os PedidoReceita.
     * @param idTipoPedido identificação do TipoPedido.
     * @return lista de PedidoReceita.
     */
    Collection<PedidoReceita> getPedidoReceitaByIdTipoPedido(Integer idTipoPedido);

    /**
     * Atualiza situação do TipoPedido.
     * @param idTipoPedido identificação do TipoPedido.
     * @param situacao nova situação.
     */
    void updateSituacaoByIdTipoPedido(Integer idTipoPedido, SituacaoEnum situacao);
}
