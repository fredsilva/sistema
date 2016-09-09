package br.gov.to.sefaz.seg.business.consulta.facade.impl;

import br.gov.to.sefaz.seg.business.consulta.facade.ComunicacaoContribuinteFacade;
import br.gov.to.sefaz.seg.business.consulta.service.ComunicacaoContribuinteService;
import br.gov.to.sefaz.seg.business.consulta.service.filter.ComunicacaoContribuinteFilter;
import br.gov.to.sefaz.seg.persistence.entity.ComunicacaoContribuinte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Classe que implementa os métodos de fachada para a entidade {@link ComunicacaoContribuinte}.
 *
 * @author <a href="mailto:volceri.davila@ntconsult.com.br">volceri.davila</a>
 * @since 22/08/2016 15:35:00
 */
@Component
public class ComunicacaoContribuinteFacadeImpl implements ComunicacaoContribuinteFacade {

    @Autowired
    private ComunicacaoContribuinteService service;

    @Override
    public List<ComunicacaoContribuinte> findByFilter(final ComunicacaoContribuinteFilter filter) {
        return service.findByFilter(filter);
    }
}
