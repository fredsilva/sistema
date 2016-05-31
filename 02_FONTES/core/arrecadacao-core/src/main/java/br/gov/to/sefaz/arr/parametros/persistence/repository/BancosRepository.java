package br.gov.to.sefaz.arr.parametros.persistence.repository;

import br.gov.to.sefaz.arr.parametros.persistence.entity.Bancos;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import br.gov.to.sefaz.persistence.repository.BaseRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repositório da entidade Bancos.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 14/04/2016 18:30:00
 */
@Repository
public interface BancosRepository extends BaseRepository<Bancos, Integer> {

    String EXISTS_LOCK_REFERENCE = "SELECT CASE WHEN (COUNT(bc.id_banco) > 0) THEN 'true' ELSE 'false' END"
            + " FROM sefaz_arr.ta_bancos bc WHERE bc.id_banco = :id"
            + " AND (EXISTS(SELECT ag.id_banco FROM sefaz_arr.ta_banco_agencias ag WHERE ag.id_banco = bc.id_banco)"
            + " OR EXISTS(SELECT lt.id_banco FROM sefaz_arr.ta_lotes_pagos_arrec lt WHERE lt.id_banco = bc.id_banco)"
            + " OR EXISTS(SELECT rc.id_banco FROM sefaz_arr.ta_arquivo_recepcao rc WHERE rc.id_banco = bc.id_banco)"
            + " OR EXISTS(SELECT ca.id_banco FROM sefaz_arr.ta_convenios_arrec ca WHERE ca.id_banco = bc.id_banco)"
            + " OR EXISTS(SELECT mn.id_banco FROM sefaz_arr.ta_municipios_contas mn WHERE mn.id_banco = bc.id_banco))";

    @Query(value = EXISTS_LOCK_REFERENCE, nativeQuery = true)
    Boolean existsLockReference(@Param(value = "id") Integer id);

    @Modifying
    @Query("UPDATE Bancos SET situacao = :situacao WHERE id_banco = :id")
    int updateSituacao(@Param("id") Integer id, @Param("situacao") SituacaoEnum situacao);

    @Query(value = "SELECT b.cnpjRaiz FROM Bancos b where b.idBanco = :idBanco")
    Integer findCnpjRaizByIdBanco(@Param(value = "idBanco") Integer idBanco);
}
