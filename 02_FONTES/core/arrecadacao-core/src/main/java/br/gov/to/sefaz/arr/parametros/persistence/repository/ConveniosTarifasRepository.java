package br.gov.to.sefaz.arr.parametros.persistence.repository;

import br.gov.to.sefaz.arr.parametros.persistence.entity.ConveniosTarifas;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositório da entidade {@link ConveniosTarifas}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 06/05/2016 10:35:38
 */
@Repository
public interface ConveniosTarifasRepository extends BaseRepository<ConveniosTarifas, Integer> {

    String ALL_CONVENIOS_TARIFAS_BY_IDCONVENIO = "SELECT convenioTarifa FROM ConveniosTarifas convenioTarifa "
            + "WHERE convenioTarifa.idConveniosArrec = :idConvenio";

    String DELETE_ALL_CONVENIOS_TARIFAS_BY_IDCONVENIO = "DELETE ConveniosTarifas ct WHERE "
            + "ct.idConveniosArrec = :idConvenio";

    @Query(value = ALL_CONVENIOS_TARIFAS_BY_IDCONVENIO)
    List<ConveniosTarifas> getAllConveniosTarifasByIdConvenioArrec(@Param(value = "idConvenio") Long idConvenio);

    @Query(value = DELETE_ALL_CONVENIOS_TARIFAS_BY_IDCONVENIO)
    @Modifying
    void deleteAllByIdConvenio(@Param(value = "idConvenio") Long idConvenio);

}
