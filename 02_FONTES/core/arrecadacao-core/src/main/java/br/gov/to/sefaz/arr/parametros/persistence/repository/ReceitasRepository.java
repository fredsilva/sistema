package br.gov.to.sefaz.arr.parametros.persistence.repository;

import br.gov.to.sefaz.arr.parametros.persistence.entity.Receitas;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositório da entidade {@link br.gov.to.sefaz.arr.parametros.persistence.entity.Receitas}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 19/05/2016 15:58:00
 */
@Repository
public interface ReceitasRepository extends BaseRepository<Receitas, Integer> {
    String FIND_RECEITAS_BY_IDCONVENIO = "SELECT * "
            + "FROM SEFAZ_ARR.TA_RECEITAS re "
            + "INNER JOIN SEFAZ_ARR.TA_RECEITAS_CONVENIOS rc  "
            + "ON re.ID_RECEITA = rc.ID_RECEITA "
            + "WHERE rc.ID_CONVENIO = :idConvenio";

    String EXISTS_LOCK_REFERENCE = "SELECT CASE WHEN (COUNT(re.ID_RECEITA) > 0) THEN 'true' ELSE 'false' END"
            + " FROM SEFAZ_ARR.TA_RECEITAS re WHERE re.ID_RECEITA = :id"
            + " AND (EXISTS(SELECT re.ID_RECEITA FROM SEFAZ_ARR.TA_PAGOS_ARREC pa"
            + " WHERE pa.ID_RECEITA = re.ID_RECEITA)"
            + " OR EXISTS(SELECT rc.ID_RECEITA FROM SEFAZ_ARR.TA_RECEITAS rc"
            + " WHERE rc.ID_RECEITA_CORRECAO_MONETARIA = re.ID_RECEITA"
            + " or rc.ID_RECEITA_JUROS = re.ID_RECEITA or rc.ID_RECEITA_MULTA = re.ID_RECEITA"
            + " or rc.ID_RECEITA_TAXAS = re.ID_RECEITA))";

    @Query(value = FIND_RECEITAS_BY_IDCONVENIO, nativeQuery = true)
    List<Receitas> findAllReceitasByIdConvenio(@Param(value = "idConvenio") Long idConvenio);

    @Query(value = EXISTS_LOCK_REFERENCE, nativeQuery = true)
    boolean existsLockReference(@Param("id") Integer idReceita);

    @Modifying
    @Query("UPDATE Receitas SET situacao = :situacao WHERE idReceita = :id")
    void updateSituacao(@Param("id") Integer id, @Param("situacao") SituacaoEnum situacao);
}
