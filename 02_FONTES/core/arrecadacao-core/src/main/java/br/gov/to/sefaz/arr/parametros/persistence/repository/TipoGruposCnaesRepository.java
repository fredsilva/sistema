package br.gov.to.sefaz.arr.parametros.persistence.repository;

import br.gov.to.sefaz.arr.parametros.persistence.entity.TipoGruposCnaes;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repositório da entidade Bancos.
 *
 * @author <a href="mailto:roger.golveia@ntconsult.com.br">roger.golveia</a>
 * @since 19/04/2016 10:51:00
 */
@Repository
public interface TipoGruposCnaesRepository extends BaseRepository<TipoGruposCnaes, Integer> {

    String EXISTS_LOCK_REFERENCE = "SELECT CASE WHEN (COUNT(tgc.id_grupo_cnae) > 0) THEN 'true' ELSE 'false' END"
            + " FROM sefaz_arr.ta_tipo_grupos_cnaes tgc"
            + " INNER JOIN sefaz_arr.ta_plano_contas pl ON pl.id_grupo_cnae = tgc.id_grupo_cnae"
            + " WHERE tgc.id_grupo_cnae = :id";

    @Query("SELECT tgc.situacao FROM TipoGruposCnaes tgc WHERE tgc.idGrupoCnae = :id")
    SituacaoEnum selectSituacao(@Param("id") Integer id);

    @Query(value = EXISTS_LOCK_REFERENCE, nativeQuery = true)
    boolean existsLockReference(@Param("id") Integer id);

    @Modifying
    @Query("UPDATE TipoGruposCnaes SET situacao = :situacao WHERE idGrupoCnae = :id")
    int updateSituacao(@Param("id") Integer id, @Param("situacao") SituacaoEnum situacao);
}