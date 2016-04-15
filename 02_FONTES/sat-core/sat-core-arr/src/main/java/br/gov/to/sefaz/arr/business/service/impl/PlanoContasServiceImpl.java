package br.gov.to.sefaz.arr.business.service.impl;

import br.gov.to.sefaz.arr.business.service.PlanoContasService;
import br.gov.to.sefaz.arr.persistence.entity.PlanoContas;
import br.gov.to.sefaz.arr.persistence.repository.PlanoContasRepository;
import br.gov.to.sefaz.common.service.impl.AbstractPagingAndSortingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;

/**
 * Implementação do serviço da entidade PlanoContas.
 *
 * @author roger.gouveia
 */
@Service
public class PlanoContasServiceImpl extends AbstractPagingAndSortingService<PlanoContasRepository, PlanoContas, String>
        implements PlanoContasService {

    @Autowired
    public PlanoContasServiceImpl(PlanoContasRepository repository, Validator validator) {
        super(repository, validator);
    }

}
