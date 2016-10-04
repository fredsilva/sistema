package br.gov.to.sefaz.seg.business.gestao.facade.impl;

import br.gov.to.sefaz.business.facade.impl.DefaultCrudFacade;
import br.gov.to.sefaz.par.gestao.business.service.EstadoService;
import br.gov.to.sefaz.par.gestao.business.service.LogradouroService;
import br.gov.to.sefaz.par.gestao.business.service.MunicipioService;
import br.gov.to.sefaz.par.gestao.persistence.entity.Estado;
import br.gov.to.sefaz.par.gestao.persistence.entity.Logradouro;
import br.gov.to.sefaz.par.gestao.persistence.entity.Municipio;
import br.gov.to.sefaz.seg.business.gestao.facade.ManterUsuarioSistemaFacade;
import br.gov.to.sefaz.seg.business.gestao.service.PostoTrabalhoService;
import br.gov.to.sefaz.seg.business.gestao.service.TipoUsuarioService;
import br.gov.to.sefaz.seg.business.gestao.service.UnidadeOrganizacionalService;
import br.gov.to.sefaz.seg.business.gestao.service.UsuarioSistemaService;
import br.gov.to.sefaz.seg.business.gestao.service.filter.ManterUsuarioSistemaFilter;
import br.gov.to.sefaz.seg.persistence.domain.TipoUsuario;
import br.gov.to.sefaz.seg.persistence.entity.PostoTrabalho;
import br.gov.to.sefaz.seg.persistence.entity.UnidadeOrganizacional;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Implementação da fachada da entidade {@link br.gov.to.sefaz.seg.business.gestao.facade.ManterUsuarioSistemaFacade}.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 28/06/2016 16:07:00
 *
 */
@Component
public class ManterUsuarioSistemaFacadeImpl extends DefaultCrudFacade<UsuarioSistema, String>
        implements ManterUsuarioSistemaFacade {

    private final UnidadeOrganizacionalService unidadeOrganizacionalService;
    private final PostoTrabalhoService postoTrabalhoService;
    private final EstadoService estadoService;
    private final MunicipioService municipioService;
    private final TipoUsuarioService tipoUsuarioService;
    private final LogradouroService logradouroService;

    @Autowired
    public ManterUsuarioSistemaFacadeImpl(UsuarioSistemaService service, UnidadeOrganizacionalService
            unidadeOrganizacionalService, PostoTrabalhoService postoTrabalhoService, EstadoService estadoService,
            MunicipioService municipioService, TipoUsuarioService tipoUsuarioService,
            LogradouroService logradouroService) {
        super(service);
        this.unidadeOrganizacionalService = unidadeOrganizacionalService;
        this.postoTrabalhoService = postoTrabalhoService;
        this.estadoService = estadoService;
        this.municipioService = municipioService;
        this.tipoUsuarioService = tipoUsuarioService;
        this.logradouroService = logradouroService;
    }

    @Override
    protected UsuarioSistemaService getService() {
        return (UsuarioSistemaService) super.getService();
    }

    @Override
    public Collection<UsuarioSistema> find(ManterUsuarioSistemaFilter filter) {
        return getService().findAllUsuarioSistemaManutencao(filter);
    }

    @Override
    public void resetPassword(UsuarioSistema usuarioSistema) {
        getService().resetPassword(usuarioSistema);
    }

    @Override
    public UsuarioSistema toggleUserStatus(UsuarioSistema usuarioSistema) {
        return getService().updateStatusUsuario(usuarioSistema);
    }

    @Override
    public UsuarioSistema findOneUsuarioSistema(String cpfUsuario) {
        return getService().findOne(cpfUsuario);
    }

    @Override
    public Collection<UnidadeOrganizacional> findAllUnidadeOrganizacional() {
        return unidadeOrganizacionalService.findAll();
    }

    @Override
    public Collection<PostoTrabalho> findAllPostoTrabalho() {
        return postoTrabalhoService.findAll();
    }

    @Override
    public Collection<Estado> findAllEstados() {
        return estadoService.findAll();
    }

    @Override
    public Collection<Municipio> findMunicipiosByUF(String s) {
        return municipioService.findByUF(s);
    }

    @Override
    public Collection<TipoUsuario> loadTipoUsuario() {
        return tipoUsuarioService.findAll();
    }

    @Override
    public Collection<PostoTrabalho> findAllPostoTrabalhoByUnidadeOrganizacional(Long identificUnidOrganizac) {
        return postoTrabalhoService.findAllByUnidadeOrganizacional(identificUnidOrganizac);
    }

    @Override
    public Collection<Logradouro> findAllLogradouros() {
        return logradouroService.findAll();
    }
}
