package br.gov.to.sefaz.arr.business.service.impl;

import br.gov.to.sefaz.arr.business.service.TipoRejeicaoArquivosService;
import br.gov.to.sefaz.arr.persistence.entity.TipoRejeicaoArquivos;
import br.gov.to.sefaz.arr.persistence.repository.TipoRejeicaoArquivosRepository;
import br.gov.to.sefaz.common.service.impl.AbstractPagingAndSortingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;

/**
 * Serviço para operações referentes a gerência de tipos de rejeição de arquivos.
 *
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
