package br.gov.to.sefaz.arr.persistence.repository;

import br.gov.to.sefaz.arr.persistence.entity.PedidoAreas;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para gerencia de dados de {@link PedidoAreas}.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 20/05/2016 19:53:00
 */
@Repository
public class PedidoAreasRepository extends BaseRepository<PedidoAreas, Integer> {

    private static final String ID_TIPO_PEDIDO = "idTipoPedido";

    /**
     * Verifica se existe referências.
     *
     * @param id identificação do PedidoAreas.
     * @return verdadeiro ou falso.
     */
    public boolean existsLockReference(Integer id) {
        return existsNative("pdp", select -> select.where().equal("pdp.id_pedido_area", id));
    }

    /**
     * Altera o pedido area.
     *
     * @param id       identificação do Pedido Area.
     * @param situacao nova situação do PedidoArea.
     */
    public void updateSituacao(Integer id, SituacaoEnum situacao) {
        update(update -> update.set("situacao", situacao).whereId(id));
    }

    /**
     * Busca o último ordem parecer pelo Tipo.
     *
     * @param idTipoPedido tipo de pedido.
     * @return id da ordem parecer.
     */
    public Integer getLastOrdemParecerFromTipo(Integer idTipoPedido) {
        return findOneColumn("MAX(ordemParecer)", select -> select.where().equal(ID_TIPO_PEDIDO, idTipoPedido));
    }

    /**
     * Busca pelo Id do parecer final e pelo id tipo pedido.
     *
     * @param parecerFinal id do parecer final.
     * @param idTipoPedido id do tipo pedido.
     * @return id do pedido areas encontrado.
     */
    public Integer findIdByParecerFinalAndTipoPedido(Boolean parecerFinal, Integer idTipoPedido) {
        return findOneColumn("idPedidoArea", select -> select.where()
                .equal("parecerFinal", parecerFinal)
                .and().equal(ID_TIPO_PEDIDO, idTipoPedido));
    }

    /**
     * Busca o total de dias pelo tipo.
     *
     * @param idTipoPedido tipo de pedido.
     * @return total de dias de análise.
     */
    public Long getTotalQtdDiasAnaliseByTipo(Integer idTipoPedido) {
        return findOneColumn("COALESCE(SUM(quantidadeDiasAnalise), 0)", select -> select.where()
                .equal(ID_TIPO_PEDIDO, idTipoPedido));
    }

    /**
     * Busca o total de dias de análise pelo tipo.
     *
     * @param idTipoPedido tipo de pedido.
     * @param idPedidoArea identificação do pedido áreas.
     * @return quantidade de dias de análise.
     */
    public Long getTotalQtdDiasAnaliseByTipoAndNotId(Integer idTipoPedido, Integer idPedidoArea) {
        return findOneColumn("COALESCE(SUM(quantidadeDiasAnalise), 0)", select -> select.where()
                .equal(ID_TIPO_PEDIDO, idTipoPedido)
                .and().different("idPedidoArea", idPedidoArea));
    }
}
