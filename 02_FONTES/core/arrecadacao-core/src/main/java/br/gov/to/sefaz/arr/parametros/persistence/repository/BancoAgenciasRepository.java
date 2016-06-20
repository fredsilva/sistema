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

    String ALL_BANCO_AGENCIAS_BY_IDBANCO = "SELECT agencia FROM BancoAgencias agencia WHERE agencia.idBanco = :id";

    /**
     * Verifica se existe alguma referencia que impede um registro de ser excluido dado o id do banco e agencia.
     *
     * @param idBanco id do banco
     * @param idAgencia id da agencia
     * @return se existe alguma referencia impeditiva
     */
    @Query(value = EXISTS_LOCK_REFERENCE, nativeQuery = true)
    Boolean existsLockReference(@Param(value = "id_banco") Integer idBanco,
            @Param(value = "id_agencia") Integer idAgencia);

    /**
     * Atualiza a situação de um {@link BancoAgencias}.
     *
     * @param idBanco id do banco
     * @param idAgencia id da agencia
     * @param situacao situação do registro
     */
    @Modifying
    @Query("UPDATE BancoAgencias SET situacao = :situacao WHERE id_banco = :id_banco AND id_agencia = :id_agencia")
    void updateSituacao(@Param(value = "id_banco") Integer idBanco, @Param(value = "id_agencia") Integer idAgencia,
            @Param("situacao") SituacaoEnum situacao);

    @Override
    @Query("SELECT ag FROM BancoAgencias ag JOIN FETCH ag.bancos JOIN FETCH ag.municipio")
    Iterable<BancoAgencias> findAll();

    /**
     * Query que busca os Banco Agencias pelo ID do banco.
     * @param idBanco id do banco.
     * @return Retorna lista de BancoAgencias.
     */
    @Query("SELECT ag FROM BancoAgencias ag JOIN FETCH ag.bancos WHERE ag.idBanco = :idBanco")
    Collection<BancoAgencias> findByIdBanco(@Param("idBanco") Integer idBanco);

    /**
     * Verifica se existe algum registro com o cnpj informado que seje diferente do id da agencia informado.
     *
     * @param idAgencia id da agencia
     * @param cnpj cnpj da agencia
     * @return se existe algum registro
     */
    @Query("SELECT CASE WHEN COUNT(ag.idAgencia) > 0 THEN true ELSE false END "
            + "FROM BancoAgencias ag WHERE ag.idAgencia <> :idAgencia AND ag.cnpjAgencia = :cnpj")
    Boolean findExitsCnpj(@Param("idAgencia") Integer idAgencia, @Param("cnpj") Long cnpj);

    /**
     * Verifica se exite um registro com id da Agencia e o id do Banco informado.
     *
     * @param idAgencia id da agencia
     * @param idBanco id do banco
     * @return se existe algum registro com esses criterios
     */
    @Query("SELECT CASE WHEN COUNT(ag.idAgencia) > 0 THEN true ELSE false END "
            + "FROM BancoAgencias ag WHERE ag.idAgencia = :idAgencia AND ag.idBanco = :idBanco")
    Boolean findExitsIdAgenciaAndIdBanco(@Param("idAgencia") Integer idAgencia, @Param("idBanco") Integer idBanco);

    /**
     * Retorna se existe alguma agencia centralizadora com o Id do banco informado.
     *
     * @param idBanco id do banco
     * @param centralizadora se a agencia é sentralizadora
     * @return se existe registro dado o match
     */
    @Query("SELECT CASE WHEN COUNT(ag.idAgencia) > 0 THEN true ELSE false END "
            + "FROM BancoAgencias ag WHERE ag.idBanco = :idBanco AND ag.centralizadora = :centralizadora")
    Boolean findExitsCentralizadoraAndIdBanco(@Param("idBanco") Integer idBanco,
            @Param("centralizadora") Boolean centralizadora);

    /**
     * Busca lista de todos os BancoAgencias pelo ID do Banco.
     * @param idBanco id do banco.
     * @return retorna lista de BancoAgencias.
     */
    @Query(value = ALL_BANCO_AGENCIAS_BY_IDBANCO)
    Collection<BancoAgencias> getAllBancoAgenciasByIdBanco(@Param(value = "id") Integer idBanco);

}
