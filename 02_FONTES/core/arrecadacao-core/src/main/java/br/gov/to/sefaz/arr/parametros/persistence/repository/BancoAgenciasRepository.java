package br.gov.to.sefaz.arr.parametros.persistence.repository;

import br.gov.to.sefaz.arr.parametros.persistence.entity.BancoAgencias;
import br.gov.to.sefaz.arr.parametros.persistence.entity.BancoAgenciasPK;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import br.gov.to.sefaz.persistence.repository.BaseRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Repositório da entidade Agências.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 22/04/2016 16:20:00
 */
@Repository
public interface BancoAgenciasRepository extends BaseRepository<BancoAgencias, BancoAgenciasPK> {

    String EXISTS_LOCK_REFERENCE = "SELECT CASE WHEN (COUNT(ba.id_agencia) > 0) THEN 'true' ELSE 'false' END"
            + " FROM sefaz_arr.ta_banco_agencias ba WHERE ba.id_banco = :id_banco AND ba.id_agencia = :id_agencia"
            + " AND (EXISTS(SELECT lt.id_banco FROM sefaz_arr.ta_lotes_pagos_arrec lt "
            + "             WHERE lt.id_banco = ba.id_banco AND lt.id_agencia = ba.id_agencia)"
            + " OR EXISTS(SELECT ca.id_banco FROM sefaz_arr.ta_convenios_arrec ca "
            + "           WHERE ca.id_banco = ba.id_banco AND ca.id_agencia = ba.id_agencia)"
            + " OR EXISTS(SELECT mn.id_banco FROM sefaz_arr.ta_municipios_contas mn "
            + "           WHERE mn.id_banco = ba.id_banco AND mn.id_agencia = ba.id_agencia))";

    @Query(value = EXISTS_LOCK_REFERENCE, nativeQuery = true)
    Boolean existsLockReference(@Param(value = "id_banco") Integer idBanco,
            @Param(value = "id_agencia") Integer idAgencia);

    @Modifying
    @Query("UPDATE BancoAgencias SET situacao = :situacao WHERE id_banco = :id_banco AND id_agencia = :id_agencia")
    int updateSituacao(@Param(value = "id_banco") Integer idBanco, @Param(value = "id_agencia") Integer idAgencia,
            @Param("situacao") SituacaoEnum situacao);

    @Override
    @Query("SELECT ag FROM BancoAgencias ag JOIN FETCH ag.bancos JOIN FETCH ag.municipio")
    Iterable<BancoAgencias> findAll();

    @Query("SELECT ag FROM BancoAgencias ag JOIN FETCH ag.bancos WHERE ag.idBanco = :idBanco")
    Collection<BancoAgencias> findByIdBanco(@Param("idBanco") Integer idBanco);

    @Query("SELECT CASE WHEN COUNT(ag.idAgencia) > 0 THEN true ELSE false END "
            + "FROM BancoAgencias ag WHERE ag.idAgencia <> :idAgencia AND ag.cnpjAgencia = :cnpj")
    Boolean findExitsCnpj(@Param("idAgencia") Integer idAgencia, @Param("cnpj") Long cnpj);

    @Query("SELECT CASE WHEN COUNT(ag.idAgencia) > 0 THEN true ELSE false END "
            + "FROM BancoAgencias ag WHERE ag.idAgencia = :idAgencia AND ag.idBanco = :idBanco")
    Boolean findExitsIdAgenciaAndIdBanco(@Param("idAgencia") Integer idAgencia, @Param("idBanco") Integer idBanco);

    @Query("SELECT CASE WHEN COUNT(ag.idAgencia) > 0 THEN true ELSE false END "
            + "FROM BancoAgencias ag WHERE ag.idBanco = :idBanco AND ag.centralizadora = :centralizadora")
    Boolean findExitsCentralizadoraAndIdBanco(@Param("idBanco") Integer idBanco,
            @Param("centralizadora") Boolean centralizadora);

}
