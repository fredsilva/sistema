package br.gov.to.sefaz.arr.persistence.repository;

import br.gov.to.sefaz.arr.persistence.entity.ArquivoRecepcao;
import br.gov.to.sefaz.arr.persistence.entity.BancoAgencias;
import br.gov.to.sefaz.arr.persistence.entity.Bancos;
import br.gov.to.sefaz.arr.persistence.entity.ConveniosArrec;
import br.gov.to.sefaz.arr.persistence.entity.LotesPagosArrec;
import br.gov.to.sefaz.arr.persistence.entity.MunicipiosContas;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import static br.gov.to.sefaz.persistence.query.builder.QueryBuilder.hqlSelect;

/**
 * Repositório da entidade Bancos.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 14/04/2016 18:30:00
 */
@Repository
public class BancosRepository extends BaseRepository<Bancos, Integer> {

    private static final String BC_ID_BANCO = "bc.idBanco";

    /**
     * Se existe alguma referencia que impede este registro de ser removido.
     *
     * @param id id do registro
     * @return se existe alguma referencia impeditiva
     */
    public Boolean existsLockReference(Integer id) {
        return exists("bc", select -> select.whereId(id)
                .and().condition(where -> where
                        .exists(hqlSelect(BancoAgencias.class, "ag").columns("ag.idBanco")
                                .where().equalColumns("ag.idBanco", BC_ID_BANCO))
                        .or().exists(hqlSelect(LotesPagosArrec.class, "lt").columns("lt.idBanco")
                                .where().equalColumns("lt.idBanco", BC_ID_BANCO))
                        .or().exists(hqlSelect(ArquivoRecepcao.class, "rc").columns("rc.idBanco")
                                .where().equalColumns("rc.idBanco", BC_ID_BANCO))
                        .or().exists(hqlSelect(ConveniosArrec.class, "ca").columns("ca.idBanco")
                                .where().equalColumns("ca.idBanco", BC_ID_BANCO))
                        .or().exists(hqlSelect(MunicipiosContas.class, "mn").columns("mn.idBanco")
                                .where().equalColumns("mn.idBanco", BC_ID_BANCO))
                ));
    }

    /**
     * Altera a situação de um registro.
     *
     * @param id       id do registro
     * @param situacao nova situação do registro
     */
    public void updateSituacao(Integer id, SituacaoEnum situacao) {
        update(update -> update.set("situacao", situacao).whereId(id));
    }

    /**
     * Busca o CNPJ raiz e um Banco.
     *
     * @param idBanco id do banco
     * @return CNPJ raiz do banco
     */
    public Integer findCnpjRaizByIdBanco(Integer idBanco) {
        return findOneColumn("cnpjRaiz", select -> select.whereId(idBanco));
    }
}
