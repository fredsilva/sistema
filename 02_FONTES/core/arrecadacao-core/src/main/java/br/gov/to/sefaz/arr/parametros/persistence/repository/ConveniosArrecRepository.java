package br.gov.to.sefaz.arr.parametros.persistence.repository;

import br.gov.to.sefaz.arr.parametros.persistence.entity.ConveniosArrec;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import br.gov.to.sefaz.persistence.repository.BaseRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repositório da entidade {@link ConveniosArrec}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 05/05/2016 18:20:21
 */
@Repository
public interface ConveniosArrecRepository extends BaseRepository<ConveniosArrec, Long> {

    String EXISTS_LOCK_REFERENCE = "SELECT CASE WHEN (COUNT(ca.id_convenio) > 0) THEN 'true' ELSE 'false' END"
            + " FROM sefaz_arr.ta_convenios_arrec ca WHERE ca.id_convenio = :id"
            + " AND (EXISTS(SELECT ca.id_convenio FROM sefaz_arr.ta_lotes_pagos_arrec lp"
            + " WHERE lp.id_convenio = ca.id_convenio)"
            + " OR EXISTS(SELECT rc.id_convenio FROM sefaz_arr.ta_receitas_convenios rc"
            + " WHERE rc.id_convenio = ca.id_convenio)"
            + " OR EXISTS(SELECT ar.id_convenio FROM sefaz_arr.ta_arquivo_recepcao ar"
            + " WHERE ar.id_convenio = ca.id_convenio))";

    String EXISTS_CONVENIOS_ARREC_WITH_TIPO_CONVENIO_AND_AGENCIA = "SELECT CASE WHEN (COUNT(ca.id_convenio) > 0) THEN "
            + "'true' ELSE 'false' END"
            + " FROM sefaz_arr.ta_convenios_arrec ca WHERE ca.tipo_convenio = :tipoConvenio"
            + " AND ca.id_banco = :idBanco"
            + " AND ca.id_agencia = :idAgencia";

    @Query(value = EXISTS_CONVENIOS_ARREC_WITH_TIPO_CONVENIO_AND_AGENCIA, nativeQuery = true)
    Boolean existsConvenioArrecWithTipoConvenioAndAgencia(@Param("tipoConvenio") Integer tipoConvenio,
            @Param("idBanco") Integer idBanco, @Param("idAgencia") Integer idAgencia);

    @Query(value = EXISTS_LOCK_REFERENCE, nativeQuery = true)
    Boolean existsLockReference(@Param("id") Long id);

    @Modifying
    @Query("UPDATE ConveniosArrec SET situacao = :situacao WHERE idConvenio = :id")
    int updateSituacao(@Param("id") Long id, @Param("situacao") SituacaoEnum situacao);

}
