package br.gov.to.sefaz.arr.parametros.business.service;

import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoDocsExigidos;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoDocsExigidosPK;
import br.gov.to.sefaz.business.service.CrudService;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;

import java.util.Collection;

/**
 * Contrato de acesso do serviço de {@link br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoDocsExigidos}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 24/05/2016 15:54:00
 */
public interface PedidoDocsExigidosService extends CrudService<PedidoDocsExigidos, PedidoDocsExigidosPK> {

    Collection<PedidoDocsExigidos> getPedidoDocsExigidosByIdTipoPedido(Integer idTipoPedido);

    void deleteAllDocsExigidosByIdTipoPedido(Integer idTipoPedido);

    int updateSituacaoByIdTipoPedido(Integer idTipoPedido, SituacaoEnum situacao);
}
