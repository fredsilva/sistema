package br.gov.to.sefaz.arr.dare.service.impl;

import br.gov.to.sefaz.arr.dare.service.ContribuitesService;
import br.gov.to.sefaz.arr.dare.service.filter.DareContribuinteFilter;
import br.gov.to.sefaz.arr.persistence.enums.TipoPessoaEnum;
import br.gov.to.sefaz.arr.persistence.repository.ContribuintesRepository;
import br.gov.to.sefaz.arr.persistence.view.Contribuintes;
import br.gov.to.sefaz.arr.persistence.view.ContribuintesPK;
import br.gov.to.sefaz.business.service.validation.ValidationSuite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Serviço que implementa {@link br.gov.to.sefaz.arr.dare.service.ContribuitesService}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 01/09/2016 15:37:00
 */
@Service
public class ContribuitesServiceImpl implements ContribuitesService {
    public static final String FILTER_CONTEXT = "filter";
    private final ContribuintesRepository repository;

    @Autowired
    public ContribuitesServiceImpl(ContribuintesRepository repository) {
        this.repository = repository;
    }

    public ContribuintesRepository getRepository() {
        return repository;
    }

    @Override
    public Contribuintes findByFilter(@ValidationSuite(context = FILTER_CONTEXT, clazz = DareContribuinteFilter.class)
            DareContribuinteFilter contribuinteFilter) {
        Long idContribuinte = contribuinteFilter.getIdContribuinte();

        if (contribuinteFilter.getTipoPessoa().equals(TipoPessoaEnum.RENAVAM)) {
            return repository.findOne(select -> select
                    .where()
                    .equal("renavam", idContribuinte));
        } else {
            Integer tipoContribuinte = contribuinteFilter.getTipoContribuinte().getCode();
            Integer tipoPessoa = contribuinteFilter.getTipoPessoa().getCode();
            ContribuintesPK contribuintesPK = new ContribuintesPK(tipoContribuinte, tipoPessoa,
                    idContribuinte);
            return repository.findOne(contribuintesPK);
        }
    }
}
