package br.gov.to.sefaz.arr.parametros.persistence.repository;

import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoAreas;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para gerencia de dados de {@link PedidoAreas}.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 20/05/2016 19:53:00
 */
@Repository
public interface PedidoAreasRepository extends BaseRepository<PedidoAreas, Integer> {

    String HAS_LOCK_REFERENCE = "SELECT CASE WHEN (COUNT(pa.id_pedido_area) > 0) THEN 'true' ELSE 'false' END"
            + " FROM sefaz_arr.ta_pedido_areas pa"
            + " INNER JOIN sefaz_arr.ta_pedido_detalhe_parecer pdp"
            + " ON pdp.id_pedido_area = pa.id_pedido_area"
            + " WHERE pa.id_pedido_area = :id";

    String TOTAL_DIAS_OF_TIPO = "SELECT NVL(SUM(pa.quantidade_dias_analise), 0) FROM sefaz_arr.ta_pedido_areas pa"
            + " WHERE pa.id_tipo_pedido = :idTipoPedido";
    String ID_TIPO_PEDIDO = "idTipoPedido";
    String ID = "id";

    /**
     * Verifica se existe referências.
     * @param id identificação do PedidoAreas.
     * @return verdadeiro ou falso.
     */
    @Query(value = HAS_LOCK_REFERENCE, nativeQuery = true)
    boolean existsLockReference(@Param(ID) Integer id);

    /**
     * Altera o pedido area.
     * @param id identificação do Pedido Area.
     * @param situacao nova situação do PedidoArea.
     */
    @Modifying
    @Query("UPDATE PedidoAreas SET situacao = :situacao WHERE idPedidoArea = :id")
    void updateSituacao(@Param(ID) Integer id, @Param("situacao") SituacaoEnum situacao);

    /**
     * Busca o último ordem parecer pelo Tipo.
     * @param idTipoPedido tipo de pedido.
     * @return id da ordem parecer.
     */
    @Query("SELECT MAX(pa.ordemParecer) FROM PedidoAreas pa WHERE pa.idTipoPedido = :idTipoPedido")
    Integer getLastOrdemParecerFromTipo(@Param(ID_TIPO_PEDIDO) Integer idTipoPedido);

    /**
     * Busca pelo Id do parecer final e pelo id tipo pedido.
     * @param parecerFinal id do parecer final.
     * @param idTipoPedido id do tipo pedido.
     * @return id do pedido areas encontrado.
     */
    @Query("SELECT pa.idPedidoArea FROM PedidoAreas pa"
            + " WHERE pa.parecerFinal = :parecerFinal AND pa.idTipoPedido = :idTipoPedido")
    Integer findIdByParecerFinalAndTipoPedido(@Param("parecerFinal") Boolean parecerFinal,
            @Param(ID_TIPO_PEDIDO) Integer idTipoPedido);

    /**
     * Remove o pedido areas pelo Id.
     * @param id identificação do pedido areas.
     */
    @Override
    @Modifying
    @Query("DELETE PedidoAreas WHERE idPedidoArea = :id")
    void delete(@Param(ID) Integer id);

    /**
     * Busca o total de dias pelo tipo.
     * @param idTipoPedido tipo de pedido.
     * @return total de dias de análise.
     */
    @Query(value = TOTAL_DIAS_OF_TIPO, nativeQuery = true)
    Integer getTotalQtdDiasAnaliseByTipo(@Param(ID_TIPO_PEDIDO) Integer idTipoPedido);

    /**
     * Busca o total de dias de análise pelo tipo.
     * @param idTipoPedido tipo de pedido.
     * @param idPedidoArea identificação do pedido áreas.
     * @return quantidade de dias de análise.
     */
    @Query(value = TOTAL_DIAS_OF_TIPO + " AND pa.id_pedido_area <> :idPedidoArea",
            nativeQuery = true)
    Integer getTotalQtdDiasAnaliseByTipoAndNotId(@Param(ID_TIPO_PEDIDO) Integer idTipoPedido,
            @Param("idPedidoArea") Integer idPedidoArea);
}
