package br.gov.to.sefaz.arr.parametros.persistence.repository;

import br.gov.to.sefaz.arr.parametros.persistence.entity.ConveniosReceitas;
import br.gov.to.sefaz.arr.parametros.persistence.entity.ConveniosReceitasPK;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * * Repositório da entidade {@link br.gov.to.sefaz.arr.parametros.persistence.entity.ConveniosReceitas}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 20/05/2016 10:17:00
 */
@Repository
public interface ConveniosReceitasRepository extends BaseRepository<ConveniosReceitas, ConveniosReceitasPK> {

    String DELETE_ALL_CONVENIOS_TARIFAS_BY_IDCONVENIO = "DELETE ConveniosReceitas cr WHERE "
            + "cr.idConvenio = :idConvenio";

    /**
     * Remove todos os convenio tarifa pelo id do convênio.
     * @param idConvenio identificação do convênio.
     */
    @Query(value = DELETE_ALL_CONVENIOS_TARIFAS_BY_IDCONVENIO)
    @Modifying
    void deleteAllByIdConvenio(@Param(value = "idConvenio") Long idConvenio);
}
