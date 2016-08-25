package br.gov.to.sefaz.arr.persistence.repository;

import br.gov.to.sefaz.arr.persistence.entity.ConveniosTarifas;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório da entidade {@link ConveniosTarifas}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 06/05/2016 10:35:38
 */
@Repository
public class ConveniosTarifasRepository extends BaseRepository<ConveniosTarifas, Integer> {

    /**
     * Remove todos os convênio tarifas pela identificação do convênio.
     *
     * @param idConvenio identificação do convênio.
     */
    public void deleteAllByIdConvenio(Long idConvenio) {
        delete(delete -> delete.where().equal("idConveniosArrec", idConvenio));
    }

}
