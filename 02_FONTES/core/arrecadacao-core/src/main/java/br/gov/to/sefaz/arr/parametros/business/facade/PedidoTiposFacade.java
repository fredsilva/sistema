package br.gov.to.sefaz.arr.parametros.business.facade;

import br.gov.to.sefaz.arr.persistence.entity.PedidoCamposAcoes;
import br.gov.to.sefaz.arr.persistence.entity.PedidoDocsExigidos;
import br.gov.to.sefaz.arr.persistence.entity.PedidoReceita;
import br.gov.to.sefaz.arr.persistence.entity.PedidoTipoDocs;
import br.gov.to.sefaz.arr.persistence.entity.PedidoTipos;
import br.gov.to.sefaz.arr.persistence.entity.Receitas;
import br.gov.to.sefaz.arr.persistence.entity.ReceitasTaxas;
import br.gov.to.sefaz.arr.persistence.enums.TipoPedidoAcoesEnum;
import br.gov.to.sefaz.arr.persistence.enums.TipoPedidoCampoEnum;
import br.gov.to.sefaz.business.facade.CrudFacade;

import java.util.Collection;
import java.util.List;

/**
 * Contrato de acesso a camada de serviço para {@link br.gov.to.sefaz.arr.persistence.entity.PedidoTipos}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 24/05/2016 15:45:00
 */
public interface PedidoTiposFacade extends CrudFacade<PedidoTipos, Integer> {

    /**
     * Busca todas as Receitas.
     * @return lista de Receitas.
     */
    List<Receitas> getAllReceitas();

    /**
     * Busca todas as ReceitasTaxas.
     * @param integer código do PedidoTipo.
     * @return lista de ReceitasTaxas.
     */
    List<ReceitasTaxas> getAllReceitasTaxasFromIdReceita(Integer integer);

    /**
     * Busca todos os PedidoTipoDoc.
     * @return lista de PedidoTipoDoc
     */
    List<PedidoTipoDocs> getAllPedidoTipoDoc();

    /**
     * Busca todos os PedidoDocsExigidos.
     * @param idTipoPedido identificação do Tipo Pedido.
     * @return lista de PedidoDocsExigidos.
     */
    Collection<PedidoDocsExigidos> getPedidoDocsExigidosByIdTipoPedido(Integer idTipoPedido);

    /**
     * Busca todos os PedidoReceita.
     * @param idTipoPedido identificação do TipoPedido.
     * @return lista de PedidoReceita.
     */
    Collection<PedidoReceita> getPedidoReceitaByIdTipoPedido(Integer idTipoPedido);

    /**
     * Busca todos os PedidoCamposAcoes.
     * @param idTipoPedido identificação do TipoPedido.
     * @return lista de PedidoCamposAcoes.
     */
    Collection<PedidoCamposAcoes> getPedidoCamposAcoesByIdTipoPedido(Integer idTipoPedido);

    /**
     * Busca todos os valores Enum do TipoPedidoCampo.
     * @param idTipoPedido identificação do TipoPedido.
     * @param tipoPedido tipo de Pedido.
     * @return lista de Enum de TipoPedidoCampo.
     */
    List<TipoPedidoCampoEnum> getTipoPedidoCampoEnumValues(Integer idTipoPedido, TipoPedidoAcoesEnum tipoPedido);
}
