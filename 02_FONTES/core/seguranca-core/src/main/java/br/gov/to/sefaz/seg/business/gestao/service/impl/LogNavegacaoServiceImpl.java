package br.gov.to.sefaz.seg.business.gestao.service.impl;

import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.seg.business.gestao.service.LogNavegacaoService;
import br.gov.to.sefaz.seg.persistence.entity.LogNavegacao;
import br.gov.to.sefaz.seg.persistence.repository.LogNavegacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementação do serviço da entidade {@link br.gov.to.sefaz.seg.persistence.entity.LogNavegacao}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 17/06/2016 19:08:00
 */
@Service
public class LogNavegacaoServiceImpl extends DefaultCrudService<LogNavegacao, Long>
        implements LogNavegacaoService {

    @Autowired
    public LogNavegacaoServiceImpl(LogNavegacaoRepository repository) {
        super(repository);
    }
}
