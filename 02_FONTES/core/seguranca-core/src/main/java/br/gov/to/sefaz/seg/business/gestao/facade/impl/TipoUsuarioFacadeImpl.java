package br.gov.to.sefaz.seg.business.gestao.facade.impl;

import br.gov.to.sefaz.business.facade.impl.DefaultCrudFacade;
import br.gov.to.sefaz.seg.business.gestao.facade.TipoUsuarioFacade;
import br.gov.to.sefaz.seg.business.gestao.service.TipoUsuarioService;
import br.gov.to.sefaz.seg.business.gestao.service.filter.TipoUsuarioFilter;
import br.gov.to.sefaz.seg.persistence.entity.TipoUsuario;
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
public class TipoUsuarioFacadeImpl extends DefaultCrudFacade<TipoUsuario, Integer>
        implements TipoUsuarioFacade {

    @Autowired
    public TipoUsuarioFacadeImpl(TipoUsuarioService service) {
        super(service);
    }

    @Override
    protected TipoUsuarioService getService() {
        return (TipoUsuarioService) super.getService();
    }

    @Override
    public List<TipoUsuario> find(TipoUsuarioFilter filter) {
        return getService().findAllByDescricao(filter);
    }
}
