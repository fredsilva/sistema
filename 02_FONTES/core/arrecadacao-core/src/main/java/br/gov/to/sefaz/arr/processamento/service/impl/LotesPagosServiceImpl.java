package br.gov.to.sefaz.arr.processamento.service.impl;

import br.gov.to.sefaz.arr.persistence.entity.LotesPagos;
import br.gov.to.sefaz.arr.persistence.entity.LotesPagosPK;
import br.gov.to.sefaz.arr.persistence.repository.LotesPagosRepository;
import br.gov.to.sefaz.arr.processamento.service.LotesPagosService;
import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Implementação do serviço da entidade {@link br.gov.to.sefaz.arr.persistence.entity.LotesPagos}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 07/07/2016 11:46:00
 */
@Component
public class LotesPagosServiceImpl extends DefaultCrudService<LotesPagos, LotesPagosPK>
        implements LotesPagosService {

    @Autowired
    public LotesPagosServiceImpl(LotesPagosRepository repository) {
        super(repository);
    }
}
