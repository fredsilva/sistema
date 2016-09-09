package br.gov.to.sefaz.seg.business.consulta.facade.impl;

import br.gov.to.sefaz.seg.business.consulta.facade.ConsultaHistoricoNavegacaoFacade;
import br.gov.to.sefaz.seg.business.consulta.service.HistoricoNavegacaoService;
import br.gov.to.sefaz.seg.business.consulta.service.filter.HistoricoNavegacaoFilter;
import br.gov.to.sefaz.seg.persistence.entity.HistoricoNavegacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Classe que implementa os métodos de fachada para o serviço {@link HistoricoNavegacaoService}.
 *
 * @author <a href="mailto:volceri.davila@ntconsult.com.br">volceri.davila</a>
 * @since 02/09/2016 10:23:25
 */
@Component
public class ConsultaHistoricoNavegacaoFacadeImpl implements ConsultaHistoricoNavegacaoFacade {

    @Autowired
    private HistoricoNavegacaoService service;


    @Override
    public List<HistoricoNavegacao> findNavigationHistory(HistoricoNavegacaoFilter filter) {
        return service.findNavigationHistory(filter);
    }
}
