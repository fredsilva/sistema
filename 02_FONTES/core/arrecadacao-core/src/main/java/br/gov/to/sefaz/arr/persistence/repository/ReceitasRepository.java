package br.gov.to.sefaz.arr.persistence.repository;

import br.gov.to.sefaz.arr.persistence.entity.Receitas;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import static br.gov.to.sefaz.persistence.query.builder.QueryBuilder.sqlSelect;

/**
 * Repositório da entidade {@link br.gov.to.sefaz.arr.persistence.entity.Receitas}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 19/05/2016 15:58:00
 */
@Repository
public class ReceitasRepository extends BaseRepository<Receitas, Integer> {

    private static final String RE_ID_RECEITA = "re.id_receita";

    /**
     * Busca todos os Receitas.
     *
     * @param idConvenio identificação convênio.
     * @return lista de Receitas.
     */
    public List<Receitas> findAllReceitasByIdConvenio(Long idConvenio) {
        return findNative("re", select -> select
                .innerJoin("sefaz_arr.ta_receitas_convenios", "rc").on("id_receita")
                .where().equal("rc.id_convenio", idConvenio));
    }

    /**
     * Busca referências à este registro.
     *
     * @param idReceita identificação Receita.
     * @return verdadeiro ou falso.
     */
    public Boolean existsLockReference(Integer idReceita) {
        return existsNative("re", select -> select.where().equal(RE_ID_RECEITA, idReceita)
                .and().condition(where -> where
                        .exists(sqlSelect("sefaz_arr.ta_pagos_arrec", "pa").columns("pa.id_receita")
                                .where().equalColumns("pa.id_receita", RE_ID_RECEITA))
                        .or().exists(sqlSelect("sefaz_arr.ta_receitas", "rc").columns("rc.id_receita")
                                .where().equalColumns("rc.id_receita_correcao_monetaria", RE_ID_RECEITA)
                                .or().equalColumns("rc.id_receita_juros", RE_ID_RECEITA)
                                .or().equalColumns("rc.id_receita_multa", RE_ID_RECEITA)
                                .or().equalColumns("rc.id_receita_taxas", RE_ID_RECEITA))
                ));
    }

    /**
     * Atualiza a situação.
     *
     * @param id       identificação da Receita.
     * @param situacao nova.
     */
    public void updateSituacao(Integer id, SituacaoEnum situacao) {
        update(update -> update.set("situacao", situacao).whereId(id));
    }
}
