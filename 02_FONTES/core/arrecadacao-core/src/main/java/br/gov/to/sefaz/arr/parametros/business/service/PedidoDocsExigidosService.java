package br.gov.to.sefaz.arr.parametros.business.service;

import br.gov.to.sefaz.arr.persistence.entity.PedidoDocsExigidos;
import br.gov.to.sefaz.arr.persistence.entity.PedidoDocsExigidosPK;
import br.gov.to.sefaz.business.service.CrudService;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;

import java.util.Collection;

/**
 * Contrato de acesso do serviço de {@link br.gov.to.sefaz.arr.persistence.entity.PedidoDocsExigidos}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 24/05/2016 15:54:00
 */
public interface PedidoDocsExigidosService extends CrudService<PedidoDocsExigidos, PedidoDocsExigidosPK> {

    /**
     * Busca todos os PedidoDocsExigidos.
     * @param idTipoPedido identificação do TipoPedido.
     * @return lista de PedidoDocsExigidos.
     */
    Collection<PedidoDocsExigidos> getPedidoDocsExigidosByIdTipoPedido(Integer idTipoPedido);

    /**
     * Remove todos os DocsExigidos.
     * @param idTipoPedido identificação TipoPedido.
     */
    void deleteAllDocsExigidosByIdTipoPedido(Integer idTipoPedido);

    /**
     * Atualiza a situação.
     * @param idTipoPedido identificação do TipoPedido.
     * @param situacao nova situação.
     */
    void updateSituacaoByIdTipoPedido(Integer idTipoPedido, SituacaoEnum situacao);
}
