package br.gov.to.sefaz.arr.persistence.repository;

import br.gov.to.sefaz.arr.persistence.entity.PlanoContas;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório da entidade Bancos.
 *
 * @author <a href="mailto:roger.golveia@ntconsult.com.br">roger.golveia</a>
 * @since 19/04/2016 10:51:00
 */
@Repository
public class PlanoContasRepository extends BaseRepository<PlanoContas, Long> {

    /**
     * Valida se já existe um PlanoContas.
     *
     * @param codigo identificação do PlanoContas.
     * @return verdadeiro ou falso.
     */
    public boolean existsByCodigo(String codigo) {
        return exists(select -> select.where().equal("codigoPlanoContas", codigo));
    }

    /**
     * Busca o PlanoContas pelo código.
     *
     * @param codigo do PlanoContas.
     * @return código selecionado no banco.
     */
    public Long findIdByCodigo(String codigo) {
        return findOneColumn("idPlanocontas", select -> select.where().equal("codigoPlanoContas", codigo));
    }

    /**
     * Busca referências para o PlanoContas no banco de dados.
     *
     * @param id identificação do PlanoContas.
     * @return verdadeiro ou falso.
     */
    public boolean existsLockReference(Long id) {
        return existsNative("pl", select -> select
                .innerJoin("sefaz_arr.ta_receitas", "rec").on("rec.id_plano_contas", "pl.id_planocontas")
                .where().equal("pl.id_planocontas", id));
    }

    /**
     * Atualiza a situação.
     *
     * @param id       identificação do PlanoContas.
     * @param situacao nova.
     */
    public void updateSituacao(Long id, SituacaoEnum situacao) {
        update(update -> update.set("situacao", situacao).whereId(id));
    }
}
