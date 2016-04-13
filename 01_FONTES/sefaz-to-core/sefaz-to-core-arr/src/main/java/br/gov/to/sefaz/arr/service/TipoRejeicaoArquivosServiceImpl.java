package br.gov.to.sefaz.arr.service;

import br.gov.to.sefaz.arr.model.entity.TipoRejeicaoArquivos;
import br.gov.to.sefaz.arr.repository.TipoRejeicaoArquivosRepository;
import br.gov.to.sefaz.arr.service.iface.TipoRejeicaoArquivosService;
import br.gov.to.sefaz.common.service.AbstractPagingAndSortingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;

/**
 * @author gabriel.dias
 */
@Service
public class TipoRejeicaoArquivosServiceImpl
        extends AbstractPagingAndSortingService<TipoRejeicaoArquivosRepository, TipoRejeicaoArquivos, Integer>
        implements TipoRejeicaoArquivosService {

    @Autowired
    public TipoRejeicaoArquivosServiceImpl(TipoRejeicaoArquivosRepository repository, Validator validator) {
        super(repository, validator);
    }

}
