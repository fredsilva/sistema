package br.gov.to.sefaz.seg.business.gestao.facade.impl;

import br.gov.to.sefaz.business.facade.impl.DefaultCrudFacade;
import br.gov.to.sefaz.seg.business.gestao.facade.UsuarioPrincipalEmpresaFacade;
import br.gov.to.sefaz.seg.business.gestao.service.UsuarioPrincipalEmpresaService;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioPrincipalEmpresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Implementação da fachada da entidade {@link br.gov.to.sefaz.seg.persistence.entity.UsuarioPrincipalEmpresa}.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 26/08/2016 16:12:00
 */
@Component
public class UsuarioPrincipalEmpresaFacadeImpl extends DefaultCrudFacade<UsuarioPrincipalEmpresa, Long>
        implements UsuarioPrincipalEmpresaFacade {

    @Autowired
    public UsuarioPrincipalEmpresaFacadeImpl(UsuarioPrincipalEmpresaService service) {
        super(service);
    }

    @Override
    protected UsuarioPrincipalEmpresaService getService() {
        return (UsuarioPrincipalEmpresaService) super.getService();
    }
}
