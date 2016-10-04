package br.gov.to.sefaz.arr.persistence.repository;

import br.gov.to.sefaz.arr.persistence.entity.BancoAgencias;
import br.gov.to.sefaz.arr.persistence.entity.BancoAgenciasPK;
import br.gov.to.sefaz.arr.persistence.entity.ConveniosArrec;
import br.gov.to.sefaz.arr.persistence.entity.LotesPagosArrec;
import br.gov.to.sefaz.arr.persistence.entity.MunicipiosContas;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

import static br.gov.to.sefaz.persistence.query.builder.QueryBuilder.hqlSelect;

/**
 * Repositório da entidade Agências.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 22/04/2016 16:20:00
 */
@Repository
public class BancoAgenciasRepository extends BaseRepository<BancoAgencias, BancoAgenciasPK> {

    /**
     * Verifica se existe alguma referencia que impede um registro de ser excluido dado o id do banco e agencia.
     *
     * @param idBanco   id do banco
     * @param idAgencia id da agencia
     * @return se existe alguma referencia impeditiva
     */
    public Boolean existsLockReference(Integer idBanco, Integer idAgencia) {
        return exists("ba", select -> select.whereId(new BancoAgenciasPK(idBanco, idAgencia))
                .and().condition(where -> where
                        .exists(hqlSelect(LotesPagosArrec.class, "lt").columns("lt.idBanco")
                                .where().equalColumns("lt.idBanco", "ba.idBanco")
                                .and().equalColumns("lt.idAgencia", "ba.idAgencia"))
                        .or().exists(hqlSelect(ConveniosArrec.class, "ca").columns("ca.idBanco")
                                .where().equalColumns("ca.idBanco", "ba.idBanco")
                                .and().equalColumns("ca.idAgencia", "ba.idAgencia"))
                        .or().exists(hqlSelect(MunicipiosContas.class, "mn").columns("mn.idBanco")
                                .where().equalColumns("mn.idBanco", "ba.idBanco")
                                .and().equalColumns("mn.idAgencia", "ba.idAgencia"))
                ));
    }

    /**
     * Atualiza a situação de um {@link BancoAgencias}.
     *
     * @param idBanco   id do banco
     * @param idAgencia id da agencia
     * @param situacao  situação do registro
     */
    public void updateSituacao(Integer idBanco, Integer idAgencia, SituacaoEnum situacao) {
        update(update -> update.set("situacao", situacao).whereId(new BancoAgenciasPK(idBanco, idAgencia)));
    }

    /**
     * Altera a situação das agências do banco.
     *
     * @param idBanco       id do banco
     * @param situacao nova situação do registro
     */
    public void updateSituacaoByBanco(Integer idBanco, SituacaoEnum situacao) {
        update(update -> update.set("situacao", situacao).where().equal("idBanco", idBanco));
    }

    @Override
    public List<BancoAgencias> findAll() {
        return find("ag", select -> select.innerJoinFetch("ag.bancos").innerJoinFetch("ag.municipio"));
    }

    /**
     * Query que busca os Banco Agencias pelo ID do banco.
     *
     * @param idBanco id do banco.
     * @return Retorna lista de BancoAgencias.
     */
    public Collection<BancoAgencias> findByIdBanco(Integer idBanco) {
        return find("ag", select -> select.innerJoinFetch("ag.bancos").where().equal("ag.idBanco", idBanco));
    }

    /**
     * Verifica se existe algum registro com o cnpj informado diferente do id da agencia informado.
     *
     * @param idAgencia id da agencia
     * @param cnpj      cnpj da agencia
     * @return se existe algum registro
     */
    public boolean findExitsCnpj(Integer idAgencia, Long cnpj) {
        return exists(select -> select.where()
                .different("idAgencia", idAgencia)
                .and().equal("cnpjAgencia", cnpj));
    }

    /**
     * Verifica se exite um registro com id da Agencia e o id do Banco informado.
     *
     * @param idAgencia id da agencia
     * @param idBanco   id do banco
     * @return se existe algum registro com esses criterios
     */
    public boolean findExitsIdAgenciaAndIdBanco(Integer idAgencia, Integer idBanco) {
        return exists(new BancoAgenciasPK(idBanco, idAgencia));
    }

    /**
     * Retorna se existe alguma agencia centralizadora com o Id do banco informado.
     *
     * @param idBanco        id do banco
     * @param centralizadora se a agencia é Centralizadora
     * @return se existe registro dado o match
     */
    public boolean findExitsCentralizadoraAndIdBanco(Integer idBanco, Integer idAgencia, Boolean centralizadora) {
        return exists(select -> select.where()
                .equal("idBanco", idBanco)
                .and().different("idAgencia", idAgencia)
                .and().equal("centralizadora", centralizadora));
    }

    /**
     * Busca lista de todos os BancoAgencias pelo ID do Banco.
     *
     * @param idBanco id do banco.
     * @return retorna lista de BancoAgencias.
     */
    public Collection<BancoAgencias> getAllBancoAgenciasByIdBanco(Integer idBanco) {
        return find(select -> select.where().equal("idBanco", idBanco));
    }
}
