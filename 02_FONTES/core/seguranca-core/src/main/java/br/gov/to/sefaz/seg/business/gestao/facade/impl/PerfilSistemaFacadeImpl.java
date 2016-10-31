package br.gov.to.sefaz.seg.business.gestao.facade.impl;

import br.gov.to.sefaz.business.facade.impl.DefaultCrudFacade;
import br.gov.to.sefaz.seg.business.gestao.facade.PapelSistemaFacade;
import br.gov.to.sefaz.seg.business.gestao.facade.PerfilSistemaFacade;
import br.gov.to.sefaz.seg.business.gestao.service.PapelSistemaService;
import br.gov.to.sefaz.seg.business.gestao.service.PerfilPapelService;
import br.gov.to.sefaz.seg.business.gestao.service.PerfilSistemaService;
import br.gov.to.sefaz.seg.business.gestao.service.UsuarioPerfilService;
import br.gov.to.sefaz.seg.business.gestao.service.filter.PerfilSistemaFilter;
import br.gov.to.sefaz.seg.persistence.entity.PapelSistema;
import br.gov.to.sefaz.seg.persistence.entity.PerfilPapel;
import br.gov.to.sefaz.seg.persistence.entity.PerfilSistema;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioPerfil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

/** Implementação da fachada {@link PapelSistemaFacade}.
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 22/07/2016 15:07:00
 */
@Component
public class PerfilSistemaFacadeImpl extends DefaultCrudFacade<PerfilSistema, Long> implements PerfilSistemaFacade {

    private final PapelSistemaService papelSistemaService;
    private final UsuarioPerfilService usuarioPerfilService;
    private final PerfilPapelService perfilPapelService;

    @Autowired
    public PerfilSistemaFacadeImpl(PerfilSistemaService service, PapelSistemaService papelSistemaService,
            UsuarioPerfilService usuarioPerfilService, PerfilPapelService perfilPapelService) {
        super(service);
        this.papelSistemaService = papelSistemaService;
        this.usuarioPerfilService = usuarioPerfilService;
        this.perfilPapelService = perfilPapelService;
    }

    @Override
    protected PerfilSistemaService getService() {
        return (PerfilSistemaService) super.getService();
    }

    @Override
    public Collection<PerfilSistema> findByFilter(PerfilSistemaFilter filter) {
        return getService().findAllPerfilSistema(filter);
    }

    @Override
    public Collection<PerfilSistema> findAllPerfilSistema(PerfilSistemaFilter filter) {
        return getService().findAllPerfilSistema(filter);
    }

    @Override
    public PerfilSistema saveOrUpdatePerfilSistema(PerfilSistema dto) {
        return getService().saveOrUpdatePerfilSistema(dto);
    }

    @Override
    public PerfilSistema findOneComplete(Long id) {
        return getService().findOneComplete(id);
    }

    @Override
    public Collection<PapelSistema> findAllPapelByPerfil(Long id) {
        return papelSistemaService.findAllPapeisByPerfilId(id);
    }

    @Override
    public Collection<PerfilPapel> findAllPerfilPapelByPerfil(Long id) {
        return perfilPapelService.findAllPerfilPapelByPerfil(id);
    }

    @Override
    public Collection<PapelSistema> findAllPapelSistema() {
        return papelSistemaService.findAllPapeisPerfil();
    }

    @Override
    public Collection<UsuarioPerfil> findAllUsuariosByPerfil(Long identificacaoPerfil) {
        return usuarioPerfilService.findAllUsuariosPerfilByPerfilId(identificacaoPerfil);
    }
}
