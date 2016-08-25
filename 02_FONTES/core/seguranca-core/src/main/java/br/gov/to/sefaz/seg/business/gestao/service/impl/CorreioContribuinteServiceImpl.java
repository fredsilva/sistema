package br.gov.to.sefaz.seg.business.gestao.service.impl;

import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.seg.business.gestao.service.CorreioContribuinteService;
import br.gov.to.sefaz.seg.persistence.entity.CorreioContribuinte;
import br.gov.to.sefaz.seg.persistence.repository.CorreioContribuinteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementação do serviço de Correio Eletrônico.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 02/06/2016 11:53:00
 */
@Service
public class CorreioContribuinteServiceImpl extends DefaultCrudService<CorreioContribuinte, Long>
        implements CorreioContribuinteService {

    @Autowired
    public CorreioContribuinteServiceImpl(CorreioContribuinteRepository repository) {
        super(repository);
    }
}
