package br.gov.to.sefaz.par.gestao.business.service.impl;

import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.par.gestao.business.service.EstadoService;
import br.gov.to.sefaz.par.gestao.persistence.entity.Estado;
import br.gov.to.sefaz.par.gestao.persistence.repository.EstadoRepository;

import br.gov.to.sefaz.persistence.query.structure.select.orderby.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

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
        super(repository);
    }

    @Override
    public Collection<Estado> findAll() {
        return getRepository().find(sb -> sb.orderBy("nomeEstado", Order.ASC));
    }
}
