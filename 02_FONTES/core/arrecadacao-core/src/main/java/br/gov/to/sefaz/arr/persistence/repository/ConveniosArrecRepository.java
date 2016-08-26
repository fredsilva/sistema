package br.gov.to.sefaz.arr.persistence.repository;

import br.gov.to.sefaz.arr.persistence.entity.ConveniosArrec;
import br.gov.to.sefaz.arr.persistence.enums.TipoConvenioEnum;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

import static br.gov.to.sefaz.persistence.query.builder.QueryBuilder.sqlSelect;

/**
 * Repositório da entidade {@link ConveniosArrec}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 05/05/2016 18:20:21
 */
@Repository
public class ConveniosArrecRepository extends BaseRepository<ConveniosArrec, Long> {

    private static final String CA_ID_CONVENIO = "ca.id_convenio";

    /**
     * Busca os Convenios Arrec por Tipo Convenio e Agência.
     *
     * @param tipoConvenio id do Tipo Convenio.
     * @param idBanco      Id do Banco.
     * @param idAgencia    Id da Agência.
     * @return lista de IDs.
     */
    public Collection<Long> findIdConvenioArrecByTipoConvenioAndAgencia(TipoConvenioEnum tipoConvenio, Integer idBanco,
            Integer idAgencia) {
        return findColumn("idConvenio", select -> select
                .where().equal("tipoConvenio", tipoConvenio)
                .and().equal("idBanco", idBanco)
                .and().equal("idAgencia", idAgencia));
    }

    /**
     * Valida se existem dependências.
     *
     * @param id ID do convênio.
     * @return verdadeiro ou falso.
     */
    public boolean existsLockReference(Long id) {
        return existsNative("ca", select -> select.where().equal(CA_ID_CONVENIO, id)
                .and().condition(where -> where
                        .exists(sqlSelect("sefaz_arr.ta_lotes_pagos_arrec", "lp")
                                .where().equalColumns("lp.id_convenio", CA_ID_CONVENIO))
                        .and().exists(sqlSelect("sefaz_arr.ta_receitas_convenios", "rc")
                                .where().equalColumns("rc.id_convenio", CA_ID_CONVENIO))
                        .and().exists(sqlSelect("sefaz_arr.ta_arquivo_recepcao", "ar")
                                .where().equalColumns("ar.id_convenio", CA_ID_CONVENIO))
                ));
    }

    /**
     * Query para atualizar a situação do Convênio Arrec.
     *
     * @param id       id do convênio.
     * @param situacao situação do convênio.
     */
    public void updateSituacao(Long id, SituacaoEnum situacao) {
        update(update -> update.set("situacao", situacao).whereId(id));
    }

}
