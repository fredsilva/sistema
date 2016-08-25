package br.gov.to.sefaz.arr.parametros.business.facade.impl;

import br.gov.to.sefaz.arr.parametros.business.facade.ConveniosTarifasFacade;
import br.gov.to.sefaz.arr.persistence.entity.ConveniosTarifas;
import br.gov.to.sefaz.business.facade.impl.DefaultCrudFacade;
import br.gov.to.sefaz.business.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Implementação da fachada da entidade {@link ConveniosTarifas}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 06/05/2016 10:42:37
 */
@Component
public class ConveniosTarifasFacadeImpl extends DefaultCrudFacade<ConveniosTarifas, Integer> implements
        ConveniosTarifasFacade {

    @Autowired
    public ConveniosTarifasFacadeImpl(CrudService<ConveniosTarifas, Integer> service) {
        super(service);
    }

}
