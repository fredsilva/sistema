package br.gov.to.sefaz.arr.persistence.repository;

import br.gov.to.sefaz.arr.persistence.entity.Dare;
import br.gov.to.sefaz.persistence.query.builder.ParamsBuilder;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositório da entidade {@link br.gov.to.sefaz.arr.persistence.entity.Dare}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 28/04/2016 16:48:40
 */
@Repository
public class DareRepository extends BaseRepository<Dare, Long> {

    /**
     * Obtém o próximo valor da sequence SQ_DARE.
     *
     * @return próximo valor da sequence SQ_DARE.
     */
    public Long getNextValSeq() {
        String nativeQuery = "select SEFAZ_ARR.SQ_DARE.NEXTVAL from DUAL";
        List<Number> result = findNativeQuery(nativeQuery, new ParamsBuilder());

        return result.get(0).longValue();
    }

    /**
     * Busca um {@link br.gov.to.sefaz.arr.persistence.entity.Dare} conforme o
     * {@link br.gov.to.sefaz.arr.persistence.entity.Dare#idNossoNumeroDare}.
     *
     * @param nossoNumero nosso número do DARE.
     * @return {@link br.gov.to.sefaz.arr.persistence.entity.Dare} conforme o nosso número.
     */
    public Dare findDareByNossoNumero(Long nossoNumero) {
        return findOne("dare", select -> select
                .innerJoinFetch("dare.unidadeOrganizacional", "unor")
                .innerJoinFetch("dare.municipio", "muni")
                .innerJoinFetch("dare.dareDetalheCollection", "dade")
                .innerJoinFetch("dade.receitas", "rece")
                .leftJoinFetch("dade.receitasTaxas", "reta")
                .leftJoinFetch("reta.receitas", "tare")
                .where().equal("dare.idNossoNumeroDare", nossoNumero));
    }

}
