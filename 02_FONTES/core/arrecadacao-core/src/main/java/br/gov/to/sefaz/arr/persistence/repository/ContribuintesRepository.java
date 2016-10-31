package br.gov.to.sefaz.arr.persistence.repository;

import br.gov.to.sefaz.arr.dare.service.filter.DareContribuinteFilter;
import br.gov.to.sefaz.arr.persistence.view.Contribuintes;
import br.gov.to.sefaz.arr.persistence.view.ContribuintesPK;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório da view {@link br.gov.to.sefaz.arr.persistence.view.Contribuintes}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 01/09/2016 15:38:00
 */
@Repository
public class ContribuintesRepository extends BaseRepository<Contribuintes, ContribuintesPK> {

    /**
     * Encontra um {@link br.gov.to.sefaz.arr.persistence.view.Contribuintes} de acordo com o filtro.
     *
     * @param contribuinteFilter filtro de contribuinte de DARE.
     * @return contribuinte de DARE filtrado.
     */
    public Contribuintes findByFilter(DareContribuinteFilter contribuinteFilter) {
        return findOne(select -> select.where()
                .equal("idPessoa", contribuinteFilter.getIdContribuinte())
                .and().equal("tipoPessoa", contribuinteFilter.getTipoPessoa())
                .and().equal("tipoContribuinte", contribuinteFilter.getTipoContribuinte()));

    }
}
