package br.gov.to.sefaz.par.gestao.business.facade.impl;

import br.gov.to.sefaz.business.facade.impl.DefaultCrudFacade;
import br.gov.to.sefaz.par.gestao.business.facade.ParametroGeralFacade;
import br.gov.to.sefaz.par.gestao.business.service.ParametroGeralService;
import br.gov.to.sefaz.par.gestao.business.service.filter.ParametroGeralFilter;
import br.gov.to.sefaz.par.gestao.persistence.entity.ParametroGeral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Facade dos Parametros Gerais.
 *
 * @author <a href="mailto:carlos.junior@ntconsult.com.br">carlos.junior</a>
 * @since 23/06/2016 16:42:00
 */
@Component
public class ParametroGeralFacadeImpl extends DefaultCrudFacade<ParametroGeral, Integer>
        implements ParametroGeralFacade {

    private final ParametroGeralService service;

    @Autowired
    public ParametroGeralFacadeImpl(ParametroGeralService service) {
        super(service);
        this.service = service;
    }

    @Override
    public List<ParametroGeral> find(ParametroGeralFilter filter) {
        return service.find(filter);
    }
}
