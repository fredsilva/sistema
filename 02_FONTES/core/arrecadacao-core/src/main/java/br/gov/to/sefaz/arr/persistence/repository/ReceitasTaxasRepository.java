package br.gov.to.sefaz.arr.persistence.repository;

import br.gov.to.sefaz.arr.persistence.entity.ReceitasTaxas;
import br.gov.to.sefaz.arr.persistence.entity.ReceitasTaxasPK;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import static br.gov.to.sefaz.persistence.query.builder.QueryBuilder.sqlSelect;

/**
 * Repositório da entidade {@link br.gov.to.sefaz.arr.persistence.entity.ReceitasTaxas}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 19/05/2016 16:05:00
 */
@Repository
public class ReceitasTaxasRepository extends BaseRepository<ReceitasTaxas, ReceitasTaxasPK> {

    /**
     * Remove todos os ReceitasTaxas.
     *
     * @param idReceita identificação da Receita.
     */
    public void deleteAllTaxasByIdReceita(Integer idReceita) {
        delete(delete -> delete.where().equal("idReceita", idReceita));
    }

    /**
     * Verifica se existem referências à esse registro.
     *
     * @param idSubcodigo identificação do subcódigo.
     * @param idReceita   identificação da Receita.
     * @return verdadeiro ou falso.
     */
    public Boolean existsLockReference(Integer idSubcodigo, Integer idReceita) {
        return existsNative("rt", select -> select.where()
                .equal("rt.id_subcodigo", idSubcodigo).and().equal("rt.id_receita", idReceita)
                .and().condition(where -> where
                        .exists(sqlSelect("sefaz_arr.ta_pagos_arrec", "pa").columns("pa.id_receita")
                                .where().equalColumns("pa.id_receita", "rt.id_receita")
                                .and().equalColumns("pa.id_subcodigo", "rt.id_subcodigo"))
                        .or().exists(sqlSelect("sefaz_arr.ta_dare_detalhe", "dd").columns("dd.id_receita")
                                .where().equalColumns("dd.id_receita", "rt.id_receita")
                                .and().equalColumns("dd.id_subcodigo", "rt.id_subcodigo"))
                ));
    }

    /**
     * Atualiza a ReceitasTaxas.
     *
     * @param idSubcodigo  identificação do subcódigo.
     * @param idReceita    identificação da Receita.
     * @param situacaoEnum nova situação.
     */
    public void updateSituacao(Integer idSubcodigo, Integer idReceita, SituacaoEnum situacaoEnum) {
        update(update -> update.set("situacao", situacaoEnum)
                .whereId(new ReceitasTaxasPK(idSubcodigo, idReceita)));
    }
}
