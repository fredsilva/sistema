package br.gov.to.sefaz.seg.business.gestao.facade.impl;

import br.gov.to.sefaz.business.facade.impl.DefaultCrudFacade;
import br.gov.to.sefaz.seg.business.gestao.facade.AtivarInativarPerfilFacade;
import br.gov.to.sefaz.seg.business.gestao.service.PostoTrabalhoService;
import br.gov.to.sefaz.seg.business.gestao.service.UnidadeOrganizacionalService;
import br.gov.to.sefaz.seg.business.gestao.service.UsuarioPerfilService;
import br.gov.to.sefaz.seg.business.gestao.service.UsuarioSistemaService;
import br.gov.to.sefaz.seg.business.gestao.service.filter.AtivarInativarPerfilFilter;
import br.gov.to.sefaz.seg.persistence.entity.PostoTrabalho;
import br.gov.to.sefaz.seg.persistence.entity.UnidadeOrganizacional;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioPerfil;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementação da fachada da entidade {@link br.gov.to.sefaz.seg.persistence.domain.TipoUsuario}.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 13/06/2016 11:33:00
 */
@Component
public class AtivarInativarPerfilFacadeImpl extends DefaultCrudFacade<UsuarioPerfil, Long>
        implements AtivarInativarPerfilFacade {

    private final UsuarioSistemaService usuarioSistemaService;
    private final UnidadeOrganizacionalService unidadeOrganizacionalService;
    private final PostoTrabalhoService postoTrabalhoService;

    @Autowired
    public AtivarInativarPerfilFacadeImpl(UsuarioPerfilService service, UsuarioSistemaService usuarioSistemaService,
            UnidadeOrganizacionalService unidadeOrganizacionalService, PostoTrabalhoService postoTrabalhoService) {
        super(service);
        this.usuarioSistemaService = usuarioSistemaService;
        this.unidadeOrganizacionalService = unidadeOrganizacionalService;
        this.postoTrabalhoService = postoTrabalhoService;
    }

    @Override
    protected UsuarioPerfilService getService() {
        return (UsuarioPerfilService) super.getService();
    }

    @Override
    public Collection<UsuarioSistema> find(AtivarInativarPerfilFilter filter) {
        Collection<UsuarioSistema> allUsuarioSistema = findAllUsuarioSistema(filter);
        buildProfileString(allUsuarioSistema);
        return allUsuarioSistema;
    }

    @Override
    public Collection<UsuarioPerfil> loadAllUsuarioPerfil(UsuarioSistema usuarioSistema) {
        return getService().getAllUsuarioPerfilByCpf(
                usuarioSistema.getCpfUsuario()).stream().collect(Collectors.toList());
    }

    @Override
    public void buildProfileString(Collection<UsuarioSistema> usuarioSistemas) {
        getService().buildProfileString(usuarioSistemas);
    }

    @Override
    public Collection<PostoTrabalho> findAllPostoTrabalho() {
        return postoTrabalhoService.findAll();
    }

    @Override
    public Collection<UnidadeOrganizacional> findAllUnidadeOrganizacional() {
        return unidadeOrganizacionalService.findAll();
    }

    @Override
    public List<UsuarioSistema> findAllUsuarioSistema(AtivarInativarPerfilFilter filter) {
        return usuarioSistemaService.findAllUsuarioSistemaPerfil(filter);
    }

    @Override
    public Collection<PostoTrabalho> findAllPostoTrabalhoByUnidadeOrganizacional(Long identificUnidOrganizac) {
        return postoTrabalhoService.findAllByUnidadeOrganizacional(identificUnidOrganizac);
    }

}
