package br.gov.to.sefaz.arr.parametros.business.facade;

import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoCamposAcoes;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoDocsExigidos;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoReceita;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoTipoDocs;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoTipos;
import br.gov.to.sefaz.arr.parametros.persistence.entity.Receitas;
import br.gov.to.sefaz.arr.parametros.persistence.entity.ReceitasTaxas;
import br.gov.to.sefaz.arr.parametros.persistence.enums.TipoPedidoAcoesEnum;
import br.gov.to.sefaz.arr.parametros.persistence.enums.TipoPedidoCampoEnum;
import br.gov.to.sefaz.business.facade.CrudFacade;

import java.util.Collection;
import java.util.List;

/**
 * Contrato de acesso a camada de serviço para {@link br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoTipos}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 24/05/2016 15:45:00
 */
public interface PedidoTiposFacade extends CrudFacade<PedidoTipos, Integer> {

    List<Receitas> getAllReceitas();

    List<ReceitasTaxas> getAllReceitasTaxasFromIdReceita(Integer integer);

    List<PedidoTipoDocs> getAllPedidoTipoDoc();

    Collection<PedidoDocsExigidos> getPedidoDocsExigidosByIdTipoPedido(Integer idTipoPedido);

    Collection<PedidoReceita> getPedidoReceitaByIdTipoPedido(Integer idTipoPedido);

    Collection<PedidoCamposAcoes> getPedidoCamposAcoesByIdTipoPedido(Integer idTipoPedido);

    List<TipoPedidoCampoEnum> getTipoPedidoCampoEnumValues(Integer idTipoPedido, TipoPedidoAcoesEnum tipoPedido);
}
