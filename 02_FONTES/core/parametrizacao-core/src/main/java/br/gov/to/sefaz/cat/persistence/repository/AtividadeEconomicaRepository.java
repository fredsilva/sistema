package br.gov.to.sefaz.cat.persistence.repository;

import br.gov.to.sefaz.cat.persistence.entity.AtividadeEconomica;
import br.gov.to.sefaz.persistence.repository.BaseRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Repositorio para manipulação de {@link AtividadeEconomica}.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 14/05/2016 10:37:00
 */
@Repository
public interface AtividadeEconomicaRepository extends BaseRepository<AtividadeEconomica, String> {

    /**
     * Retorna todos os CNAE's {@link AtividadeEconomica} vinculados ao grupo informado.
     *
     * @param idGrupoCnae id do grupo ao qual os CNAE's estão vonculados
     * @return os CNAE's vinculados ao grupo
     */
    @Query(value = "SELECT cf.* FROM SEFAZ_PAR.TA_ATIVIDADE_ECONOMICA cf"
            + " INNER JOIN SEFAZ_ARR.TA_GRUPOS_CNAE gc ON gc.cnae_fiscal = cf.codigo_cnae"
            + " WHERE gc.id_grupo_cnae = :idGrupo", nativeQuery = true)
    Collection<AtividadeEconomica> findAllCnaesByGrupo(@Param("idGrupo") Integer idGrupoCnae);
}
