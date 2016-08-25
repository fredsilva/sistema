package br.gov.to.sefaz.par.gestao.persistence.repository;

import br.gov.to.sefaz.par.gestao.persistence.entity.AtividadeEconomica;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Repositorio para manipulação de {@link AtividadeEconomica}.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 14/05/2016 10:37:00
 */
@Repository
public class AtividadeEconomicaRepository extends BaseRepository<AtividadeEconomica, String> {

    /**
     * Retorna todos os CNAE's {@link AtividadeEconomica} vinculados ao grupo informado.
     *
     * @param idGrupoCnae id do grupo ao qual os CNAE's estão vonculados
     * @return os CNAE's vinculados ao grupo
     */
    public Collection<AtividadeEconomica> findAllCnaesByGrupo(Integer idGrupoCnae) {
        return findNative("cf", select -> select
                .innerJoin("sefaz_arr.ta_grupos_cnae", "gc").on("gc.cnae_fiscal", "cf.codigo_cnae")
                .where().equal("gc.id_grupo_cnae", idGrupoCnae));
    }
}
