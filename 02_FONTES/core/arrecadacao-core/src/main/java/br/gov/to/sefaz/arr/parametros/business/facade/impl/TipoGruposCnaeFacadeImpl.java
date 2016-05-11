package br.gov.to.sefaz.arr.parametros.business.facade.impl;

import br.gov.to.sefaz.arr.parametros.business.facade.TipoGruposCnaeFacade;
import br.gov.to.sefaz.arr.parametros.business.service.TipoGruposCnaeService;
import br.gov.to.sefaz.arr.parametros.persistence.entity.TipoGruposCnaes;
import br.gov.to.sefaz.business.facade.impl.DefaultCrudFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Implementação da fachada da entidade PlanoContas.
 *
 * @author <a href="mailto:roger.golveia@ntconsult.com.br">roger.golveia</a>
 * @since 19/04/2016 10:51:00
 */
@Component
public class TipoGruposCnaeFacadeImpl extends DefaultCrudFacade<TipoGruposCnaes, Integer>
        implements TipoGruposCnaeFacade {

    @Autowired
    public TipoGruposCnaeFacadeImpl(TipoGruposCnaeService service) {
        super(service);
    }

}
