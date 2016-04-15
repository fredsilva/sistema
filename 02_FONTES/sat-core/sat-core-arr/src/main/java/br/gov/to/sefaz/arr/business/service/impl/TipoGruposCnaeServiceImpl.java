package br.gov.to.sefaz.arr.business.service.impl;

import br.gov.to.sefaz.arr.business.service.TipoGruposCnaeService;
import br.gov.to.sefaz.arr.persistence.entity.TipoGruposCnae;
import br.gov.to.sefaz.arr.persistence.repository.TipoGruposCnaeRepository;
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
public class TipoGruposCnaeServiceImpl
        extends AbstractPagingAndSortingService<TipoGruposCnaeRepository, TipoGruposCnae, Integer>
        implements TipoGruposCnaeService {

    @Autowired
    public TipoGruposCnaeServiceImpl(TipoGruposCnaeRepository repository, Validator validator) {
        super(repository, validator);
    }

}
