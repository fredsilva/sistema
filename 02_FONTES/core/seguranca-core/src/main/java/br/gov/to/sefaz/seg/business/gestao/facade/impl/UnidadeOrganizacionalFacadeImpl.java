package br.gov.to.sefaz.seg.business.gestao.facade.impl;

import br.gov.to.sefaz.business.facade.impl.DefaultCrudFacade;
import br.gov.to.sefaz.seg.business.gestao.facade.UnidadeOrganizacionalFacade;
import br.gov.to.sefaz.seg.business.gestao.service.UnidadeOrganizacionalService;
import br.gov.to.sefaz.seg.business.gestao.service.filter.UnidadeOrganizacionalFilter;
import br.gov.to.sefaz.seg.persistence.entity.UnidadeOrganizacional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Implementação da fachada da entidade {@link UnidadeOrganizacional}.
 *
 *@author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 13/06/2016 11:33:00
 */
@Component
public class UnidadeOrganizacionalFacadeImpl extends DefaultCrudFacade<UnidadeOrganizacional, Long>
        implements UnidadeOrganizacionalFacade {

    @Autowired
    public UnidadeOrganizacionalFacadeImpl(UnidadeOrganizacionalService service) {
        super(service);
    }

    @Override
    protected UnidadeOrganizacionalService getService() {
        return (UnidadeOrganizacionalService) super.getService();
    }

    @Override
    public List<UnidadeOrganizacional> find(UnidadeOrganizacionalFilter filter) {
        return getService().findAll(filter);
    }
}
