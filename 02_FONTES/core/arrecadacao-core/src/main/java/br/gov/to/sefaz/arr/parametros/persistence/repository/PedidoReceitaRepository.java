package br.gov.to.sefaz.arr.parametros.persistence.repository;

import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoReceita;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoReceitaPK;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import br.gov.to.sefaz.persistence.repository.BaseRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repositório da entidade {@link br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoReceita}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 25/05/2016 20:29:00
 */
@Repository
public interface PedidoReceitaRepository extends BaseRepository<PedidoReceita, PedidoReceitaPK> {

    String DELETE_ALL_PEDIDOS_RECEITA_BY_ID_TIPO_PEDIDO = "DELETE PedidoReceita pr WHERE "
            + "pr.idTipoPedido = :idTipoPedido";

    /**
     * Remove todos os PedidoReceita.
     * @param idTipoPedido identificação TipoPedido.
     */
    @Query(value = DELETE_ALL_PEDIDOS_RECEITA_BY_ID_TIPO_PEDIDO)
    @Modifying
    void deleteAllPedidoReceitaByIdTipoPedido(@Param("idTipoPedido") Integer idTipoPedido);

    /**
     * Atualiza a situação do PedidoReceita.
     * @param idTipoPedido identificação do TipoPedido
     * @param situacao nova situação.
     * @return código do banco de dados.
     */
    @Modifying
    @Query("UPDATE PedidoReceita pr SET pr.situacao = :situacao WHERE pr.idTipoPedido = :idTipoPedido")
    int updateSituacaoByIdTipoPedido(@Param(value = "idTipoPedido") Integer idTipoPedido,
            @Param("situacao") SituacaoEnum situacao);
}
