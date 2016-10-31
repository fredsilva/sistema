package br.gov.to.sefaz.arr.persistence.repository;

import br.gov.to.sefaz.arr.persistence.entity.PedidoTipoAcoes;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório da entidade {@link br.gov.to.sefaz.arr.persistence.entity.PedidoTipoAcoes}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 24/05/2016 15:10:00
 */
@Repository
public class PedidoTipoAcoesRepository extends BaseRepository<PedidoTipoAcoes, Integer> {

    /**
     * Remove todos os CampoAcoes.
     *
     * @param idTipoPedido identificação do TipoPedido.
     */
    public void deleteAllTipoAcoesByIdTipoPedido(Integer idTipoPedido) {
        delete(delete -> delete.where().equal("idTipoPedido", idTipoPedido));
    }

    /**
     * Atualiza a situação do CampoAcoes.
     *
     * @param idTipoPedido identificação do TipoPedido.
     * @param situacao     nova situação.
     */
    public void updateSituacaoByIdTipoPedido(Integer idTipoPedido, SituacaoEnum situacao) {
        update(update -> update.set("situacao", situacao).where().equal("idTipoPedido", idTipoPedido));
    }

}
