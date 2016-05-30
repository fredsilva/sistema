package br.gov.to.sefaz.arr.parametros.business.service;

import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoReceita;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoReceitaPK;
import br.gov.to.sefaz.business.service.CrudService;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;

import java.util.Collection;

/**
 * Contrato de acesso do serviço de {@link br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoReceita}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 25/05/2016 20:30:00
 */
public interface PedidoReceitaService extends CrudService<PedidoReceita, PedidoReceitaPK> {

    void deleteAllPedidoReceitaByIdTipoPedido(Integer idTipoPedido);

    Collection<PedidoReceita> getPedidoReceitaByIdTipoPedido(Integer idTipoPedido);

    int updateSituacaoByIdTipoPedido(Integer idTipoPedido, SituacaoEnum situacao);
}
