package br.gov.to.sefaz.arr.persistence.repository;

import br.gov.to.sefaz.arr.persistence.entity.ReceitasRepasse;
import br.gov.to.sefaz.arr.persistence.entity.ReceitasRepassePK;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório da entidade {@link br.gov.to.sefaz.arr.persistence.entity.ReceitasRepasse}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 19/05/2016 16:02:00
 */
@Repository
public class ReceitasRepasseRepository extends BaseRepository<ReceitasRepasse, ReceitasRepassePK> {

    /**
     * Remove todos os ReceitasRepasses.
     *
     * @param idReceita identificação da Receita.
     */
    public void deleteAllRepassesByIdReceita(Integer idReceita) {
        delete(delete -> delete.where().equal("idReceita", idReceita));
    }
}
