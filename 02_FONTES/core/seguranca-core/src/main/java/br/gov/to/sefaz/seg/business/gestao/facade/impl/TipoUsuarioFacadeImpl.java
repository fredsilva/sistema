package br.gov.to.sefaz.seg.business.gestao.facade.impl;

import br.gov.to.sefaz.seg.business.gestao.facade.TipoUsuarioFacade;
import br.gov.to.sefaz.seg.business.gestao.service.TipoUsuarioService;
import br.gov.to.sefaz.seg.business.gestao.service.filter.TipoUsuarioFilter;
import br.gov.to.sefaz.seg.persistence.domain.TipoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Implementação da fachada da entidade {@link TipoUsuario}.
 *
 *@author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 13/06/2016 11:33:00
 */
@Component
public class TipoUsuarioFacadeImpl implements TipoUsuarioFacade {

    TipoUsuarioService service;

    @Autowired
    public TipoUsuarioFacadeImpl(TipoUsuarioService service) {
        this.service = service;
    }

    @Override
    public List<TipoUsuario> find(TipoUsuarioFilter filter) {
        return service.findByFilter(filter);
    }

    @Override
    public List<TipoUsuario> findAllTipoUsuario() {
        return service.findAllCountUsers();
    }
}
