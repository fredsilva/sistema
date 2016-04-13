package br.gov.to.sefaz.arr.service;

import br.gov.to.sefaz.arr.model.entity.Bancos;
import br.gov.to.sefaz.arr.repository.BancosRepository;
import br.gov.to.sefaz.arr.service.iface.BancosService;
import br.gov.to.sefaz.common.service.AbstractPagingAndSortingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;

/**
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
