package br.gov.to.sefaz.arr.parametros.business.service.impl;

import br.gov.to.sefaz.arr.parametros.business.service.DelegaciasService;
import br.gov.to.sefaz.arr.persistence.entity.Delegacias;
import br.gov.to.sefaz.arr.persistence.repository.DelegaciasRepository;
import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementação de um {@link Delegacias}.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 24/05/2016 17:54:00
 */
@Service
public class DelegaciasServiceImpl extends DefaultCrudService<Delegacias, Integer> implements DelegaciasService {

    @Autowired
    public DelegaciasServiceImpl(DelegaciasRepository repository) {
        super(repository);
    }
}
