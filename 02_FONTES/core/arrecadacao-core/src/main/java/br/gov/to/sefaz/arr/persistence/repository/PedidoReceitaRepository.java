package br.gov.to.sefaz.arr.persistence.repository;

import br.gov.to.sefaz.arr.persistence.entity.PedidoReceita;
import br.gov.to.sefaz.arr.persistence.entity.PedidoReceitaPK;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório da entidade {@link br.gov.to.sefaz.arr.persistence.entity.PedidoReceita}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 25/05/2016 20:29:00
 */
@Repository
public class PedidoReceitaRepository extends BaseRepository<PedidoReceita, PedidoReceitaPK> {

    /**
     * Remove todos os PedidoReceita.
     *
     * @param idTipoPedido identificação TipoPedido.
     */
    public void deleteAllPedidoReceitaByIdTipoPedido(Integer idTipoPedido) {
        delete(delete -> delete.where().equal("idTipoPedido", idTipoPedido));
    }

    /**
     * Atualiza a situação do PedidoReceita.
     *
     * @param idTipoPedido identificação do TipoPedido
     * @param situacao     nova situação.
     */
    public void updateSituacaoByIdTipoPedido(Integer idTipoPedido, SituacaoEnum situacao) {
        update(update -> update.set("situacao", situacao).where().equal("idTipoPedido", idTipoPedido));
    }
}
