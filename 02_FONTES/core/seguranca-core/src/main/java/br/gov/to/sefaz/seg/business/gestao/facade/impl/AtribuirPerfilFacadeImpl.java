package br.gov.to.sefaz.seg.business.gestao.facade.impl;

import br.gov.to.sefaz.business.facade.impl.DefaultCrudFacade;
import br.gov.to.sefaz.seg.business.gestao.facade.AtribuirPerfilFacade;
import br.gov.to.sefaz.seg.business.gestao.service.PerfilSistemaService;
import br.gov.to.sefaz.seg.business.gestao.service.PostoTrabalhoService;
import br.gov.to.sefaz.seg.business.gestao.service.TipoUsuarioService;
import br.gov.to.sefaz.seg.business.gestao.service.UnidadeOrganizacionalService;
import br.gov.to.sefaz.seg.business.gestao.service.UsuarioSistemaService;
import br.gov.to.sefaz.seg.business.gestao.service.filter.AtribuirPerfilFilter;
import br.gov.to.sefaz.seg.persistence.domain.TipoUsuario;
import br.gov.to.sefaz.seg.persistence.entity.PerfilSistema;
import br.gov.to.sefaz.seg.persistence.entity.PostoTrabalho;
import br.gov.to.sefaz.seg.persistence.entity.UnidadeOrganizacional;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Implementação da fachada de atribuição de perfil.
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 02/08/2016 17:04:00
 */
@Component
public class AtribuirPerfilFacadeImpl extends DefaultCrudFacade<UsuarioSistema, String>
        implements AtribuirPerfilFacade {

    private final PerfilSistemaService perfilSistemaService;
    private final UnidadeOrganizacionalService unidadeOrganizacionalService;
    private final PostoTrabalhoService postoTrabalhoService;
    private final TipoUsuarioService tipoUsuarioService;

    @Autowired
    public AtribuirPerfilFacadeImpl(UsuarioSistemaService usuarioSistemaService,
            PerfilSistemaService perfilSistemaService, UnidadeOrganizacionalService unidadeOrganizacionalService,
            PostoTrabalhoService postoTrabalhoService, TipoUsuarioService tipoUsuarioService) {
        super(usuarioSistemaService);
        this.perfilSistemaService = perfilSistemaService;
        this.unidadeOrganizacionalService = unidadeOrganizacionalService;
        this.postoTrabalhoService = postoTrabalhoService;
        this.tipoUsuarioService = tipoUsuarioService;
    }

    @Override
    protected UsuarioSistemaService getService() {
        return (UsuarioSistemaService) super.getService();
    }

    @Override
    public Collection<UsuarioSistema> find(AtribuirPerfilFilter filter) {
        return getService().findAllByFilter(filter);
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
    public Collection<TipoUsuario> loadTipoUsuario() {
        return tipoUsuarioService.findAll();
    }

    @Override
    public UsuarioSistema updateUsuarioPerfil(UsuarioSistema dto) {
        getService().updateAtribuirUsuarioPerfil(dto);
        AtribuirPerfilFilter filter = new AtribuirPerfilFilter();
        filter.setCpfUsuario(dto.getCpfUsuario());

        Collection<UsuarioSistema> find = find(filter);
        return find.stream().findFirst().get();
    }

    @Override
    public Collection<PostoTrabalho> findAllPostoTrabalhoByUnidadeOrganizacional(Long identificUnidOrganizac) {
        return postoTrabalhoService.findAllByUnidadeOrganizacional(identificUnidOrganizac);
    }

    @Override
    public Collection<PerfilSistema> findAllPerfilSistema() {
        return perfilSistemaService.findAll();
    }

}
