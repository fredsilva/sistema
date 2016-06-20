package br.gov.to.sefaz.seg.business.gestao.facade.impl;

import br.gov.to.sefaz.business.facade.impl.DefaultCrudFacade;
import br.gov.to.sefaz.seg.business.gestao.facade.ModuloSistemaFacade;
import br.gov.to.sefaz.seg.business.gestao.service.ModuloSistemaService;
import br.gov.to.sefaz.seg.persistence.entity.ModuloSistema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Implementação da fachada da entidade {@link ModuloSistemaFacade}.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 14/06/2016 09:36:00
 */
@Component
public class ModuloSistemaFacadeImpl extends DefaultCrudFacade<ModuloSistema, Long>
        implements ModuloSistemaFacade {

    @Autowired
    public ModuloSistemaFacadeImpl(ModuloSistemaService service) {
        super(service);
    }
}
