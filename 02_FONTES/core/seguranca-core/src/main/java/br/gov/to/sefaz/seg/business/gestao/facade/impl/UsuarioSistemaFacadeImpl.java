package br.gov.to.sefaz.seg.business.gestao.facade.impl;

import br.gov.to.sefaz.business.facade.impl.DefaultCrudFacade;
import br.gov.to.sefaz.par.gestao.business.service.EstadoService;
import br.gov.to.sefaz.par.gestao.business.service.LogradouroService;
import br.gov.to.sefaz.par.gestao.business.service.MunicipioService;
import br.gov.to.sefaz.par.gestao.persistence.entity.Estado;
import br.gov.to.sefaz.par.gestao.persistence.entity.Logradouro;
import br.gov.to.sefaz.par.gestao.persistence.entity.Municipio;
import br.gov.to.sefaz.seg.business.authentication.domain.ChangePasswordDto;
import br.gov.to.sefaz.seg.business.gestao.facade.UsuarioSistemaFacade;
import br.gov.to.sefaz.seg.business.gestao.service.TipoUsuarioService;
import br.gov.to.sefaz.seg.business.gestao.service.UsuarioSistemaService;
import br.gov.to.sefaz.seg.business.gestao.service.filter.UsuarioSistemaFilter;
import br.gov.to.sefaz.seg.persistence.domain.TipoUsuario;
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
    private final LogradouroService logradouroService;

    @Autowired
    public UsuarioSistemaFacadeImpl(UsuarioSistemaService service, TipoUsuarioService tipoUsuarioService,
            EstadoService estadoService, MunicipioService municipioService, LogradouroService logradouroService) {
        super(service);
        this.tipoUsuarioService = tipoUsuarioService;
        this.estadoService = estadoService;
        this.municipioService = municipioService;
        this.logradouroService = logradouroService;
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
    public void saveNewUsuarioSistemaSolicitacaoSenha(UsuarioSistema usuarioSistema) {
        getService().saveNewUsuarioSistemaSolicitacaoSenha(usuarioSistema);
    }

    @Override
    public void changePassword(ChangePasswordDto dto) {
        getService().changePassword(dto);
    }

    @Override
    public Collection<Logradouro> findAllLogradouros() {
        return logradouroService.findAll();
    }
}
