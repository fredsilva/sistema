package br.gov.to.sefaz.arr.parametros.persistence.repository;

import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoAreasServidores;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoAreasServidoresPK;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para gerencia de dados de {@link PedidoAreasServidores}.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 20/05/2016 20:01:00
 */
@Repository
public interface PedidoAreasServidoresRepository
        extends BaseRepository<PedidoAreasServidores, PedidoAreasServidoresPK> {

    String HAS_LOCK_REFERENCE = "SELECT CASE WHEN (COUNT(pas.id_servidor) > 0) THEN 'true' ELSE 'false' END"
            + " FROM sefaz_arr.ta_pedido_areas_servidores pas"
            + " INNER JOIN sefaz_arr.ta_pedido_detalhe_parecer pdp"
            + " ON pdp.id_pedido_area = pas.id_pedido_area"
            + " AND (pdp.id_servidor_encaminhado = pas.id_servidor"
            + " OR pdp.id_servidor_recepcao = pas.id_servidor)"
            + " WHERE pas.id_pedido_area = :idPedidoArea AND pas.id_servidor = :idServidor";
    String ID_PEDIDO_AREA = "idPedidoArea";
    String ID_SERVIDOR = "idServidor";

    @Query(value = HAS_LOCK_REFERENCE, nativeQuery = true)
    boolean existsLockReference(@Param(ID_PEDIDO_AREA) Integer idPedidoArea, @Param(ID_SERVIDOR) Long idServidor);

    @Modifying
    @Query("UPDATE PedidoAreasServidores SET situacao = :situacao"
            + " WHERE idPedidoArea = :idPedidoArea AND idServidor = :idServidor")
    void updateSituacao(@Param(ID_PEDIDO_AREA) Integer idPedidoArea, @Param(ID_SERVIDOR) Long idServidor,
            @Param("situacao") SituacaoEnum situacao);

    @Modifying
    @Query("DELETE PedidoAreasServidores WHERE idPedidoArea = :idPedidoArea AND idServidor = :idServidor")
    void delete(@Param(ID_PEDIDO_AREA) Integer idPedidoArea, @Param(ID_SERVIDOR) Long idServidor);

    @Query(value = "SELECT CASE WHEN (COUNT(pas.id_servidor) > 0) THEN 'true' ELSE 'false' END"
            + " FROM sefaz_arr.ta_pedido_areas_servidores pas"
            + " WHERE pas.id_pedido_area = :idPedidoArea AND pas.supervisor = :supervisor",
            nativeQuery = true)
    boolean existsChefeSetor(@Param(ID_PEDIDO_AREA) Integer idPedidoArea, @Param("supervisor") Boolean supervisor);
}
