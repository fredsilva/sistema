package br.gov.to.sefaz.arr.persistence.repository;

import br.gov.to.sefaz.arr.persistence.entity.PedidoDocsExigidos;
import br.gov.to.sefaz.arr.persistence.entity.PedidoDocsExigidosPK;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório da entidade {@link br.gov.to.sefaz.arr.persistence.entity.PedidoDocsExigidos}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 24/05/2016 15:08:00
 */
@Repository
public class PedidoDocsExigidosRepository extends BaseRepository<PedidoDocsExigidos, PedidoDocsExigidosPK> {

    /**
     * Remove todos os documentos exigidos pela identificação do TipoPedido.
     *
     * @param idTipoPedido identificação do TipoPedido.
     */
    public void deleteAllDocsExigidosByIdTipoPedido(Integer idTipoPedido) {
        delete(delete -> delete.where().equal("idTipoPedido", idTipoPedido));
    }

    /**
     * Atualiza a situação pela identificação do TipoPedido.
     *
     * @param idTipoPedido identificação do TipoPedido.
     * @param situacao     nova situação.
     */
    public void updateSituacaoByIdTipoPedido(Integer idTipoPedido, SituacaoEnum situacao) {
        update(update -> update.set("situacao", situacao).where().equal("idTipoPedido", idTipoPedido));
    }

}
