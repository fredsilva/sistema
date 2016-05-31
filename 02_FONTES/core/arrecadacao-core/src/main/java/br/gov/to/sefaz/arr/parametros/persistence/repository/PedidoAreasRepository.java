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

    String TOTAL_DAIS_OF_TIPO = "SELECT NVL(SUM(pa.quantidade_dias_analise), 0) FROM sefaz_arr.ta_pedido_areas pa"
            + " WHERE pa.id_tipo_pedido = :idTipoPedido";
    String ID_TIPO_PEDIDO = "idTipoPedido";
    String ID = "id";

    @Query(value = HAS_LOCK_REFERENCE, nativeQuery = true)
    boolean existsLockReference(@Param(ID) Integer id);

    @Modifying
    @Query("UPDATE PedidoAreas SET situacao = :situacao WHERE idPedidoArea = :id")
    void updateSituacao(@Param(ID) Integer id, @Param("situacao") SituacaoEnum situacao);

    @Query("SELECT MAX(pa.ordemParecer) FROM PedidoAreas pa WHERE pa.idTipoPedido = :idTipoPedido")
    Integer getLastOrdemParecerFromTipo(@Param(ID_TIPO_PEDIDO) Integer idTipoPedido);

    @Query("SELECT pa.idPedidoArea FROM PedidoAreas pa"
            + " WHERE pa.parecerFinal = :parecerFinal AND pa.idTipoPedido = :idTipoPedido")
    Integer findIdByParecerFinalAndTipoPedido(@Param("parecerFinal") Boolean parecerFinal,
            @Param(ID_TIPO_PEDIDO) Integer idTipoPedido);

    @Override
    @Modifying
    @Query("DELETE PedidoAreas WHERE idPedidoArea = :id")
    void delete(@Param(ID) Integer id);

    @Query(value = TOTAL_DAIS_OF_TIPO, nativeQuery = true)
    Integer getTotalQtdDiasAnaliseByTipo(@Param(ID_TIPO_PEDIDO) Integer idTipoPedido);

    @Query(value = TOTAL_DAIS_OF_TIPO + " AND pa.id_pedido_area <> :idPedidoArea",
            nativeQuery = true)
    Integer getTotalQtdDiasAnaliseByTipoAndNotId(@Param(ID_TIPO_PEDIDO) Integer idTipoPedido,
            @Param("idPedidoArea") Integer idPedidoArea);
}
