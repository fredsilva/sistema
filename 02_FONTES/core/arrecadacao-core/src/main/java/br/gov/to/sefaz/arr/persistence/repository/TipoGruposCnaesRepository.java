package br.gov.to.sefaz.arr.persistence.repository;

import br.gov.to.sefaz.arr.persistence.entity.TipoGruposCnaes;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório da entidade Bancos.
 *
 * @author <a href="mailto:roger.golveia@ntconsult.com.br">roger.golveia</a>
 * @since 19/04/2016 10:51:00
 */
@Repository
public class TipoGruposCnaesRepository extends BaseRepository<TipoGruposCnaes, Integer> {

    /**
     * Método para buscar a situação do Tipo de Grupos CNAEs.
     *
     * @param id do Tipo de Grupos CNAEs.
     * @return a situação do Tipo de Grupos CNAEs.
     */
    public SituacaoEnum selectSituacao(Integer id) {
        return findOneColumn("situacao", id);
    }

    /**
     * Método para buscar registros que têm como referência o Tipo de Grupos CNAEs selecionado para deleção.
     *
     * @param id do Tipo de Grupos CNAEs
     * @return verdadeiro, se existirem referências, falso se não existirem.
     */
    public Boolean existsLockReference(Integer id) {
        return existsNative("tgc", select -> select.innerJoin("sefaz_arr.ta_plano_contas", "pl").on("id_grupo_cnae")
                .where().equal("tgc.id_grupo_cnae", id));
    }

    /**
     * Método para atualizar a situação do Tipo de Grupos CNAEs.
     *
     * @param id       do Tipo de Grupos CNAEs.
     * @param situacao do Tipo de Grupos CNAEs.
     */
    public void updateSituacao(Integer id, SituacaoEnum situacao) {
        update(update -> update.set("situacao", situacao).whereId(id));
    }
}
