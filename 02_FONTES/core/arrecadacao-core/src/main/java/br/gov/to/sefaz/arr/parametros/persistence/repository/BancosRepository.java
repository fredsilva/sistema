package br.gov.to.sefaz.arr.parametros.persistence.repository;

import br.gov.to.sefaz.arr.parametros.persistence.entity.BancoAgencias;
import br.gov.to.sefaz.arr.parametros.persistence.entity.Bancos;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositório da entidade Bancos.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 14/04/2016 18:30:00
 */
@Repository
public interface BancosRepository extends CrudRepository<Bancos, Integer> {

    String ALL_BANCO_AGENCIAS = "SELECT agencia FROM BancoAgencias agencia WHERE agencia.idBanco = :id";

    String CODIGO_BANCO_EXISTS_IN_OTHERS_TABLES = "SELECT case when (count(*) > 0) then 'true' else 'false' end "
            + "FROM SEFAZ_ARR.TA_BANCO_AGENCIAS agencias LEFT JOIN SEFAZ_ARR.TA_LOTES_PAGOS_ARREC lp "
            + "on lp.ID_BANCO = agencias.ID_BANCO LEFT JOIN SEFAZ_ARR.TA_ARQUIVO_RECEPCAO recepcao "
            + "on recepcao.ID_BANCO = agencias.ID_BANCO LEFT JOIN SEFAZ_ARR.TA_CONVENIOS_ARREC conveniosArrec "
            + "on conveniosArrec.ID_BANCO = agencias.ID_BANCO LEFT JOIN SEFAZ_ARR.TA_MUNICIPIOS_CONTAS municipios "
            + "on municipios.ID_BANCO = agencias.ID_BANCO WHERE agencias.ID_BANCO = :id";

    @Query(value = CODIGO_BANCO_EXISTS_IN_OTHERS_TABLES, nativeQuery = true)
    Boolean existsInAnotherTable(@Param(value = "id") Integer id);

    @Query(value = ALL_BANCO_AGENCIAS)
    List<BancoAgencias> getAllBancoAgenciasFromIdBanco(@Param(value = "id") Integer id);
}
