package br.gov.to.sefaz.arr.parametros.persistence.repository;

import br.gov.to.sefaz.arr.parametros.persistence.entity.ReceitasTaxas;
import br.gov.to.sefaz.arr.parametros.persistence.entity.ReceitasTaxasPK;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repositório da entidade {@link br.gov.to.sefaz.arr.parametros.persistence.entity.ReceitasTaxas}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 19/05/2016 16:05:00
 */
@Repository
public interface ReceitasTaxasRepository extends BaseRepository<ReceitasTaxas, ReceitasTaxasPK> {

    String EXISTS_LOCK_REFERENCE = "SELECT CASE WHEN (COUNT(rt.ID_SUBCODIGO) > 0) THEN 'true' ELSE 'false' END"
            + " FROM SEFAZ_ARR.TA_RECEITAS_TAXAS rt WHERE rt.ID_SUBCODIGO = :idSubcodigo"
            + " AND rt.ID_RECEITA = :idReceita"
            + " AND (EXISTS(SELECT pa.ID_RECEITA FROM SEFAZ_ARR.TA_PAGOS_ARREC pa"
            + " WHERE pa.ID_RECEITA = rt.ID_RECEITA AND pa.ID_SUBCODIGO = rt.ID_SUBCODIGO)"
            + " OR EXISTS(SELECT dd.ID_RECEITA FROM SEFAZ_ARR.TA_DARE_DETALHE dd"
            + " WHERE dd.ID_RECEITA = rt.ID_RECEITA"
            + " and dd.ID_SUBCODIGO = rt.ID_SUBCODIGO))";

    String DELETE_ALL_RECEITAS_TAXAS_BY_IDRECEITA = "DELETE ReceitasTaxas rt WHERE "
            + "rt.idReceita = :idReceita";

    /**
     * Remove todos os ReceitasTaxas.
     * @param idReceita identificação da Receita.
     */
    @Query(value = DELETE_ALL_RECEITAS_TAXAS_BY_IDRECEITA)
    @Modifying
    void deleteAllTaxasByIdReceita(@Param("idReceita") Integer idReceita);

    /**
     * Verifica se existem referências à esse registro.
     * @param idSubcodigo identificação do subcódigo.
     * @param idReceita identificação da Receita.
     * @return verdadeiro ou falso.
     */
    @Query(value = EXISTS_LOCK_REFERENCE, nativeQuery = true)
    boolean existsLockReference(@Param("idSubcodigo") Integer idSubcodigo, @Param("idReceita") Integer idReceita);

    /**
     * Atualiza a ReceitasTaxas.
     * @param idSubcodigo identificação do subcódigo.
     * @param idReceita identificação da Receita.
     * @param situacaoEnum nova situação.
     */
    @Modifying
    @Query("UPDATE ReceitasTaxas SET situacao = :situacao WHERE idSubcodigo = :idSubcodigo and idReceita = :idReceita")
    void updateSituacao(@Param("idSubcodigo") Integer idSubcodigo, @Param("idReceita") Integer idReceita,
            @Param("situacao") SituacaoEnum situacaoEnum);
}
