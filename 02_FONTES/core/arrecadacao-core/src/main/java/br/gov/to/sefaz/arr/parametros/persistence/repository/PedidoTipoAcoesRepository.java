package br.gov.to.sefaz.arr.parametros.persistence.repository;

import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoTipoAcoes;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import br.gov.to.sefaz.persistence.repository.BaseRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repositório da entidade {@link br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoTipoAcoes}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 24/05/2016 15:10:00
 */
@Repository
public interface PedidoTipoAcoesRepository extends BaseRepository<PedidoTipoAcoes, Integer> {

    String DELETE_ALL_CAMPOS_ACOES_BY_ID_TIPO_PEDIDO = "DELETE PedidoTipoAcoes ta WHERE "
            + "ta.idTipoPedido = :idTipoPedido";

    @Query(value = DELETE_ALL_CAMPOS_ACOES_BY_ID_TIPO_PEDIDO)
    @Modifying
    void deleteAllTipoAcoesByIdTipoPedido(@Param("idTipoPedido") Integer idTipoPedido);

    @Modifying
    @Query("UPDATE PedidoTipoAcoes ta SET ta.situacao = :situacao WHERE ta.idTipoPedido = :idTipoPedido")
    int updateSituacaoByIdTipoPedido(@Param(value = "idTipoPedido") Integer idTipoPedido,
            @Param("situacao") SituacaoEnum situacao);

}
