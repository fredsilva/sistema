package br.gov.to.sefaz.arr.parametros.persistence.repository;

import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoCamposAcoes;
import br.gov.to.sefaz.persistence.repository.BaseRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para gerencia de dados de {@link br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoCamposAcoes}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 25/05/2016 18:31:00
 */
@Repository
public interface PedidoCamposAcoesRepository extends BaseRepository<PedidoCamposAcoes, Integer> {

    String DELETE_ALL_CAMPOS_ACOES_BY_ID_TIPO_PEDIDO = "DELETE PedidoCamposAcoes ca WHERE "
            + "ca.idAcoes IN (SELECT pt.idAcoes FROM PedidoTipoAcoes pt WHERE pt.idTipoPedido = :idTipoPedido)";

    @Query(value = DELETE_ALL_CAMPOS_ACOES_BY_ID_TIPO_PEDIDO)
    @Modifying
    void deleteAllCamposAcoesByIdTipoPedido(@Param("idTipoPedido") Integer idTipoPedido);

}
