package br.gov.to.sefaz.arr.processamento.service.impl;

import br.gov.to.sefaz.arr.persistence.entity.ArquivoDetalhePagos;
import br.gov.to.sefaz.arr.persistence.repository.ArquivoDetalhePagosRepository;
import br.gov.to.sefaz.arr.processamento.service.ArquivoDetalhePagosService;
import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementação do serviço da entidade {@link br.gov.to.sefaz.arr.persistence.entity.ArquivoDetalhePagos}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 08/07/2016 10:33:00
 */
@Service
public class ArquivoDetalhePagosServiceImpl extends DefaultCrudService<ArquivoDetalhePagos, Long>
        implements ArquivoDetalhePagosService {

    @Autowired
    public ArquivoDetalhePagosServiceImpl(ArquivoDetalhePagosRepository repository) {
        super(repository);
    }
}
