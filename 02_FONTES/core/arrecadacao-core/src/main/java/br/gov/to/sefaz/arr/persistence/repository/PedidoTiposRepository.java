package br.gov.to.sefaz.arr.persistence.repository;

import br.gov.to.sefaz.arr.persistence.entity.PedidoTipos;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import static br.gov.to.sefaz.persistence.query.builder.QueryBuilder.sqlSelect;

/**
 * Repositorio para gerencia de dados de {@link PedidoTipos}.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 20/05/2016 19:05:00
 */
@Repository
public class PedidoTiposRepository extends BaseRepository<PedidoTipos, Integer> {

    /**
     * Busca um TipoPedido já existente.
     *
     * @param idTipoPedido identificação do TipoPedido.
     * @return verdadeiro ou falso.
     */
    public Boolean findExitsIdTipoPedido(Integer idTipoPedido) {
        return exists(select -> select.where().equal("idTipoPedido", idTipoPedido));
    }

    /**
     * Busca no banco de dados se existe uma referência para esse registro.
     *
     * @param idTipoPedido identificação do TipoPedido.
     * @return verdadeiro ou falso.
     */
    public boolean existsLockReference(Integer idTipoPedido) {
        return existsNative("pt", select -> select.where().equal("pt.id_tipo_pedido", idTipoPedido)
                .and().condition(where -> where
                        .exists(sqlSelect("sefaz_arr.ta_pedido_areas", "pa").columns("pa.id_tipo_pedido")
                                .where().equalColumns("pa.id_tipo_pedido", "pt.id_tipo_pedido"))
                        .or().exists(sqlSelect("sefaz_arr.ta_pedido_solicitacao", "ps").columns("ps.id_tipo_pedido")
                                .where().equalColumns("ps.id_tipo_pedido", "pt.id_tipo_pedido"))
                ));
    }

    /**
     * Atualiza a situação.
     *
     * @param idTipoPedido identificação do TipoPedido.
     * @param situacao     nova situação.
     */
    public void updateSituacao(Integer idTipoPedido, SituacaoEnum situacao) {
        update(update -> update.set("situacao", situacao).whereId(idTipoPedido));
    }

}
