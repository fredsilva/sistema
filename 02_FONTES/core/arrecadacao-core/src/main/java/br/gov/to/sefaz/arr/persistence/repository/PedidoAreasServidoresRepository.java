package br.gov.to.sefaz.arr.persistence.repository;

import br.gov.to.sefaz.arr.persistence.entity.PedidoAreasServidores;
import br.gov.to.sefaz.arr.persistence.entity.PedidoAreasServidoresPK;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para gerencia de dados de {@link PedidoAreasServidores}.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 20/05/2016 20:01:00
 */
@Repository
public class PedidoAreasServidoresRepository
        extends BaseRepository<PedidoAreasServidores, PedidoAreasServidoresPK> {

    /**
     * Verifica se existe referências ao PedidoAreasServidores.
     *
     * @param idPedidoArea identificação do PedidoArea.
     * @param idServidor   Identificação do servidor.
     * @return verdadeiro ou falso.
     */
    public boolean existsLockReference(Integer idPedidoArea, Long idServidor) {
        return existsNative("pas", select -> select
                .innerJoin("sefaz_arr.ta_pedido_detalhe_parecer", "pdp")
                .on(on -> on.equal("pdp.id_pedido_area", "pas.id_pedido_area")
                        .and().condition(where -> where
                                .equalColumns("pdp.id_servidor_encaminhado", "pas.id_servidor")
                                .or().equalColumns("pdp.id_servidor_recepcao", "pas.id_servidor")
                        ))
                .where().equal("pas.id_pedido_area", idPedidoArea)
                .and().equal("pas.id_servidor", idServidor));
    }

    /**
     * Atualiza a situação do PedidoAreasServidores.
     *
     * @param idPedidoArea identificação do PedidoArea.
     * @param idServidor   identificação do Servidor.
     * @param situacao     situação para ser atualizada.
     */
    public void updateSituacao(Integer idPedidoArea, Long idServidor, SituacaoEnum situacao) {
        update(update -> update.set("situacao", situacao)
                .whereId(new PedidoAreasServidoresPK(idPedidoArea, idServidor)));
    }

    /**
     * Verifica se existe chefe de setor.
     *
     * @param idPedidoArea identificação do PedidoArea.
     * @param supervisor   identificação do supervisor.
     * @return verdadeiro ou falso.
     */
    public boolean existsChefeSetor(Integer idPedidoArea, Boolean supervisor) {
        return exists(select -> select.where()
                .equal("idPedidoArea", idPedidoArea)
                .and().equal("supervisor", supervisor));
    }
}
