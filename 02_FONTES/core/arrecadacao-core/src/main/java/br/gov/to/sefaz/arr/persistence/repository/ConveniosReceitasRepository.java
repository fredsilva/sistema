package br.gov.to.sefaz.arr.persistence.repository;

import br.gov.to.sefaz.arr.persistence.entity.ConveniosReceitas;
import br.gov.to.sefaz.arr.persistence.entity.ConveniosReceitasPK;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * * Repositório da entidade {@link br.gov.to.sefaz.arr.persistence.entity.ConveniosReceitas}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 20/05/2016 10:17:00
 */
@Repository
public class ConveniosReceitasRepository extends BaseRepository<ConveniosReceitas, ConveniosReceitasPK> {

    /**
     * Remove todos os convenio tarifa pelo id do convênio.
     *
     * @param idConvenio identificação do convênio.
     */
    public void deleteAllByIdConvenio(Long idConvenio) {
        delete(delete -> delete.where().equal("idConvenio", idConvenio));
    }
}
