package br.gov.to.sefaz.arr.parametros.persistence.repository;

import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoDocsExigidos;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoDocsExigidosPK;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import br.gov.to.sefaz.persistence.repository.BaseRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repositório da entidade {@link br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoDocsExigidos}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 24/05/2016 15:08:00
 */
@Repository
public interface PedidoDocsExigidosRepository extends BaseRepository<PedidoDocsExigidos, PedidoDocsExigidosPK> {

    String DELETE_ALL_DOCS_EXIGIDOS_BY_ID_TIPO_PEDIDO = "DELETE PedidoDocsExigidos pd WHERE "
            + "pd.idTipoPedido = :idTipoPedido";

    /**
     * Remove todos os documentos exigidos pela identificação do TipoPedido.
     * @param idTipoPedido identificação do TipoPedido.
     */
    @Query(value = DELETE_ALL_DOCS_EXIGIDOS_BY_ID_TIPO_PEDIDO)
    @Modifying
    void deleteAllDocsExigidosByIdTipoPedido(@Param("idTipoPedido") Integer idTipoPedido);

    /**
     * Atualiza a situação pela identificação do TipoPedido.
     * @param idTipoPedido identificação do TipoPedido.
     * @param situacao nova situação.
     * @return codigo do banco de dados.
     */
    @Modifying
    @Query("UPDATE PedidoDocsExigidos pd SET pd.situacao = :situacao WHERE pd.idTipoPedido = :idTipoPedido")
    int updateSituacaoByIdTipoPedido(@Param(value = "idTipoPedido") Integer idTipoPedido,
            @Param("situacao") SituacaoEnum situacao);

}
