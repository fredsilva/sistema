package br.gov.to.sefaz.seg.business.general.facade.impl;

import br.gov.to.sefaz.business.facade.impl.DefaultCrudFacade;
import br.gov.to.sefaz.cat.business.service.EstadoService;
import br.gov.to.sefaz.cat.business.service.MunicipioService;
import br.gov.to.sefaz.cat.persistence.entity.Estado;
import br.gov.to.sefaz.cat.persistence.entity.Municipio;
import br.gov.to.sefaz.seg.business.general.facade.UsuarioSistemaFacade;
import br.gov.to.sefaz.seg.business.general.service.filter.UsuarioSistemaFilter;
import br.gov.to.sefaz.seg.business.gestao.service.TipoUsuarioService;
import br.gov.to.sefaz.seg.business.gestao.service.UsuarioSistemaService;
import br.gov.to.sefaz.seg.persistence.entity.TipoUsuario;
import br.gov.to.sefaz.seg.persistence.entity.UnidadeOrganizacional;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * Implementação da fachada da entidade {@link TipoUsuario}.
 *
 *@author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 13/06/2016 11:33:00
 */
@Component
public class UsuarioSistemaFacadeImpl extends DefaultCrudFacade<UsuarioSistema, String>
        implements UsuarioSistemaFacade {

    private final TipoUsuarioService tipoUsuarioService;
    private final EstadoService estadoService;
    private final MunicipioService municipioService;

    @Autowired
    public UsuarioSistemaFacadeImpl(UsuarioSistemaService service, TipoUsuarioService tipoUsuarioService,
                                    EstadoService estadoService, MunicipioService municipioService) {
        super(service);
        this.tipoUsuarioService = tipoUsuarioService;
        this.estadoService = estadoService;
        this.municipioService = municipioService;
    }

    @Override
    protected UsuarioSistemaService getService() {
        return (UsuarioSistemaService) super.getService();
    }

    @Override
    public List<UsuarioSistema> find(UsuarioSistemaFilter filter) {
        return getService().findAllUsuarioSistema(filter);
    }

    @Override
    public Collection<TipoUsuario> findAllTipoUsuario() {
        return tipoUsuarioService.findAll();
    }

    @Override
    public Collection<Estado> findAllEstados() {
        return estadoService.findAll();
    }

    @Override
    public Collection<Municipio> findMunicipiosByUF(String uf) {
        return municipioService.findByUF(uf);
    }

    @Override
    public UnidadeOrganizacional findUnidadeOrganizacional() {
        return null;
    }
}
