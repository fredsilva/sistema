package br.gov.to.sefaz.arr.business.service.impl;

import br.gov.to.sefaz.arr.business.service.BancosService;
import br.gov.to.sefaz.arr.persistence.entity.Bancos;
import br.gov.to.sefaz.arr.persistence.repository.BancosRepository;
import br.gov.to.sefaz.common.service.impl.AbstractPagingAndSortingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;

/**
 * Implementação do serviço da entidade Bancos.
 * 
 * @author roger.gouveia
 */
@Service
public class BancosServiceImpl extends AbstractPagingAndSortingService<BancosRepository, Bancos, Integer>
        implements BancosService {

    @Autowired
    public BancosServiceImpl(BancosRepository repository, Validator validator) {
        super(repository, validator);
    }

}
