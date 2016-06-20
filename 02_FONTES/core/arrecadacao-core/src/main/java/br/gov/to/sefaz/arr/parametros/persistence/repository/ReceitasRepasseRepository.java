package br.gov.to.sefaz.arr.parametros.persistence.repository;

import br.gov.to.sefaz.arr.parametros.persistence.entity.ReceitasRepasse;
import br.gov.to.sefaz.arr.parametros.persistence.entity.ReceitasRepassePK;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repositório da entidade {@link br.gov.to.sefaz.arr.parametros.persistence.entity.ReceitasRepasse}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 19/05/2016 16:02:00
 */
@Repository
public interface ReceitasRepasseRepository extends BaseRepository<ReceitasRepasse, ReceitasRepassePK> {

    String DELETE_ALL_RECEITAS_REPASSES_BY_IDRECEITA = "DELETE ReceitasRepasse rr WHERE "
            + "rr.idReceita = :idReceita";

    /**
     * Remove todos os ReceitasRepasses.
     * @param idReceita identificação da Receita.
     */
    @Query(value = DELETE_ALL_RECEITAS_REPASSES_BY_IDRECEITA)
    @Modifying
    void deleteAllRepassesByIdReceita(@Param("idReceita") Integer idReceita);
}
