package br.gov.to.sefaz.arr.persistence.repository;

import br.gov.to.sefaz.arr.persistence.entity.PedidoCamposAcoes;
import br.gov.to.sefaz.arr.persistence.entity.PedidoTipoAcoes;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import static br.gov.to.sefaz.persistence.query.builder.QueryBuilder.hqlSelect;

/**
 * Repositorio para gerencia de dados de {@link br.gov.to.sefaz.arr.persistence.entity.PedidoCamposAcoes}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 25/05/2016 18:31:00
 */
@Repository
public class PedidoCamposAcoesRepository extends BaseRepository<PedidoCamposAcoes, Integer> {

    /**
     * Remove todos os CamposAcoes pela identificação do TipoPedido.
     *
     * @param idTipoPedido identificação do TipoPedido.
     */
    public void deleteAllCamposAcoesByIdTipoPedido(Integer idTipoPedido) {
        delete("ca", delete -> delete.where().in("ca.idAcoes", hqlSelect(PedidoTipoAcoes.class, "pt")
                .where().equal("pt.idTipoPedido", idTipoPedido)));
    }

}
