package br.gov.to.sefaz.seg.business.consulta.facade.impl;

import br.gov.to.sefaz.seg.business.authentication.handler.AuthenticatedUserHandler;
import br.gov.to.sefaz.seg.business.consulta.facade.ConsultaComunicacaoSistemaFacade;
import br.gov.to.sefaz.seg.business.consulta.service.ComunicacaoContribuinteService;
import br.gov.to.sefaz.seg.business.consulta.service.filter.ConsultaComunicacaoSistemaFilter;
import br.gov.to.sefaz.seg.persistence.entity.ComunicacaoContribuinte;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Classe que implementa os métodos de fachada para consultas a entidade {@link ComunicacaoContribuinte}.
 *
 * @author <a href="mailto:volceri.davila@ntconsult.com.br">volceri.davila</a>
 * @since 29/08/2016 14:08:00
 */
@Component
public class ConsultaComunicacaoSistemaFacadeImpl implements ConsultaComunicacaoSistemaFacade {

    @Autowired
    private ComunicacaoContribuinteService comunicacaoContribuinteService;

    @Override
    public UsuarioSistema getUsuarioSistema() {
        return AuthenticatedUserHandler.getUsuarioSistema();
    }

    @Override
    public List<ComunicacaoContribuinte> findSystemCommunicationsForUser(UsuarioSistema usuarioSistema,
                                                                         ConsultaComunicacaoSistemaFilter filter) {

        return comunicacaoContribuinteService.findSystemCommunicationsForUser(filter, usuarioSistema);
    }
}
