package br.gov.to.sefaz.arr.parametros.persistence.repository;

import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoTipos;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import br.gov.to.sefaz.persistence.repository.BaseRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para gerencia de dados de {@link PedidoTipos}.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 20/05/2016 19:05:00
 */
@Repository
public interface PedidoTiposRepository extends BaseRepository<PedidoTipos, Integer> {

    String EXISTS_LOCK_REFERENCE = "SELECT CASE WHEN (COUNT(pt.id_tipo_pedido) > 0) THEN 'true' ELSE 'false' END"
            + " FROM sefaz_arr.ta_pedido_tipos pt WHERE pt.id_tipo_pedido = :id_tipo_pedido"
            + " AND (EXISTS(SELECT pa.id_tipo_pedido FROM sefaz_arr.ta_pedido_areas pa"
            + " WHERE pa.id_tipo_pedido = pt.id_tipo_pedido)"
            + " OR EXISTS(SELECT ps.id_tipo_pedido FROM sefaz_arr.ta_pedido_solicitacao ps"
            + " WHERE ps.id_tipo_pedido = pt.id_tipo_pedido))";

    @Query("SELECT CASE WHEN COUNT(pt.idTipoPedido) > 0 THEN true ELSE false END "
            + "FROM PedidoTipos pt WHERE pt.idTipoPedido = :idTipoPedido")
    Boolean findExitsIdTipoPedido(@Param("idTipoPedido") Integer idTipoPedido);

    @Query(value = EXISTS_LOCK_REFERENCE, nativeQuery = true)
    Boolean existsLockReference(@Param(value = "id_tipo_pedido") Integer idTipoPedido);

    @Modifying
    @Query("UPDATE PedidoTipos pt SET pt.situacao = :situacao WHERE pt.idTipoPedido = :idTipoPedido")
    int updateSituacao(@Param(value = "idTipoPedido") Integer idTipoPedido, @Param("situacao") SituacaoEnum situacao);

}
