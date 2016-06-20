package br.gov.to.sefaz.seg.business.gestao.facade.impl;

import br.gov.to.sefaz.business.facade.impl.DefaultCrudFacade;
import br.gov.to.sefaz.seg.business.gestao.facade.PostoTrabalhoFacade;
import br.gov.to.sefaz.seg.business.gestao.service.PostoTrabalhoService;
import br.gov.to.sefaz.seg.business.gestao.service.UnidadeOrganizacionalService;
import br.gov.to.sefaz.seg.business.gestao.service.filter.PostoTrabalhoFilter;
import br.gov.to.sefaz.seg.persistence.entity.PostoTrabalho;
import br.gov.to.sefaz.seg.persistence.entity.UnidadeOrganizacional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * Implementação da fachada da entidade {@link PostoTrabalho}.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 13/06/2016 11:33:00
 */
@Component
public class PostoTrabalhoFacadeImpl extends DefaultCrudFacade<PostoTrabalho, Long>
        implements PostoTrabalhoFacade {


    private final UnidadeOrganizacionalService unidadeOrganizacionalService;

    @Autowired
    public PostoTrabalhoFacadeImpl(PostoTrabalhoService service,
            UnidadeOrganizacionalService unidadeOrganizacionalService) {
        super(service);
        this.unidadeOrganizacionalService = unidadeOrganizacionalService;
    }

    @Override
    protected PostoTrabalhoService getService() {
        return (PostoTrabalhoService) super.getService();
    }

    @Override
    public List<PostoTrabalho> find(PostoTrabalhoFilter filter) {
        return getService().findAll(filter);
    }

    @Override
    public Collection<UnidadeOrganizacional> findAllUnidadeOrganizacional() {
        return unidadeOrganizacionalService.findAll();
    }
}
