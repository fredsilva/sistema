package br.gov.to.sefaz.arr.processamento.service.impl;

import br.gov.to.sefaz.arr.persistence.entity.Dare;
import br.gov.to.sefaz.arr.persistence.repository.DareRepository;
import br.gov.to.sefaz.arr.processamento.service.DareService;
import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementação do serviço da entidade {@link br.gov.to.sefaz.arr.persistence.entity.Dare}.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 21/07/2016 19:17:00
 */
@Service
public class DareServiceImpl extends DefaultCrudService<Dare, Long> implements DareService {

    @Autowired
    public DareServiceImpl(DareRepository repository) {
        super(repository);
    }

}
