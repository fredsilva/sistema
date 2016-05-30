package br.gov.to.sefaz.arr.parametros.persistence.repository;

import br.gov.to.sefaz.arr.parametros.persistence.entity.GruposCnae;
import br.gov.to.sefaz.arr.parametros.persistence.entity.GruposCnaePK;
import br.gov.to.sefaz.persistence.repository.BaseRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repositorio responsavel pelas operações da entidade {@link GruposCnae}.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 17/05/2016 14:20:00
 */
@Repository
public interface GruposCnaeRepository extends BaseRepository<GruposCnae, GruposCnaePK> {

    @Query("SELECT CASE WHEN COUNT(gc.cnaeFiscal) > 0 THEN true ELSE false END"
            + " FROM GruposCnae gc WHERE gc.cnaeFiscal = :cnaeFiscal")
    boolean existsCnaeFiscal(@Param("cnaeFiscal") String cnaeFiscal);

    @Modifying
    @Query("DELETE GruposCnae WHERE idGrupoCnae = :idGrupoCnae")
    void deleteByGrupo(@Param("idGrupoCnae") Integer idGrupoCnae);
}
