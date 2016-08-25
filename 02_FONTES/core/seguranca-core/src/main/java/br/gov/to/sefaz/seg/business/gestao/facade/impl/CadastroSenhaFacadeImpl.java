package br.gov.to.sefaz.seg.business.gestao.facade.impl;

import br.gov.to.sefaz.business.facade.impl.DefaultCrudFacade;
import br.gov.to.sefaz.par.gestao.business.service.LogradouroService;
import br.gov.to.sefaz.par.gestao.persistence.entity.Logradouro;
import br.gov.to.sefaz.seg.business.gestao.facade.CadastroSenhaFacade;
import br.gov.to.sefaz.seg.business.gestao.service.CadastroSenhaService;
import br.gov.to.sefaz.seg.business.gestao.service.filter.CadastroSenhaFilter;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Implementação da fachada da entidade {@link br.gov.to.sefaz.seg.business.gestao.facade.CadastroSenhaFacade}.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 28/06/2016 16:07:00
 *
 */
@Component
public class CadastroSenhaFacadeImpl extends DefaultCrudFacade<UsuarioSistema, String>
        implements CadastroSenhaFacade {

    private final LogradouroService logradouroService;

    @Autowired
    public CadastroSenhaFacadeImpl(CadastroSenhaService service, LogradouroService logradouroService) {
        super(service);
        this.logradouroService = logradouroService;
    }

    @Override
    protected CadastroSenhaService getService() {
        return (CadastroSenhaService) super.getService();
    }

    @Override
    public Collection<UsuarioSistema> find(CadastroSenhaFilter filter) {
        return getService().findAll(filter);
    }

    @Override
    public void resetPassword(UsuarioSistema usuarioSistema) {
        getService().resetPassword(usuarioSistema);
    }

    @Override
    public void authorizeUser(UsuarioSistema usuarioSistema) {
        getService().authorizeUser(usuarioSistema);
    }

    @Override
    public UsuarioSistema findOneUsuarioSistema(String cpfUsuario) {
        return getService().findOneUsuarioSistema(cpfUsuario);
    }

    @Override
    public Collection<Logradouro> findAllLogradouros() {
        return logradouroService.findAll();
    }
}
