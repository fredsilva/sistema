package br.gov.to.sefaz.cat.business.service.impl;

import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.cat.business.service.EstadoService;
import br.gov.to.sefaz.cat.persistence.entity.Estado;
import br.gov.to.sefaz.cat.persistence.repository.EstadoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Serviços para manipulação de {@link Estado}.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 21/05/2016 16:26:41
 */
@Service
public class EstadoServiceImpl extends DefaultCrudService<Estado, String> implements EstadoService {

    @Autowired
    public EstadoServiceImpl(EstadoRepository repository) {
        super(repository, new Sort(new Sort.Order(Sort.Direction.ASC, "nomeEstado")));
    }
}
