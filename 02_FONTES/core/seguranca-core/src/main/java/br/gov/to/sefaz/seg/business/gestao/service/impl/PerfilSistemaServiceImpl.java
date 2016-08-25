package br.gov.to.sefaz.seg.business.gestao.service.impl;

import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.ValidationSuite;
import br.gov.to.sefaz.seg.business.gestao.service.PerfilPapelService;
import br.gov.to.sefaz.seg.business.gestao.service.PerfilSistemaService;
import br.gov.to.sefaz.seg.business.gestao.service.filter.PerfilSistemaFilter;
import br.gov.to.sefaz.seg.persistence.entity.PerfilPapel;
import br.gov.to.sefaz.seg.persistence.entity.PerfilSistema;
import br.gov.to.sefaz.seg.persistence.repository.PerfilSistemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Implementação do serviço {@link PerfilSistemaService}.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 25/07/2016 17:10:00
 */
@Service
public class PerfilSistemaServiceImpl extends DefaultCrudService<PerfilSistema, Long>
        implements PerfilSistemaService {

    private final PerfilPapelService perfilPapelService;

    @Autowired
    public PerfilSistemaServiceImpl(PerfilSistemaRepository repository, PerfilPapelService perfilPapelService) {
        super(repository);
        this.perfilPapelService = perfilPapelService;
    }

    @Override
    protected PerfilSistemaRepository getRepository() {
        return (PerfilSistemaRepository) super.getRepository();
    }

    @Override
    public List<PerfilSistema> findAllPerfilSistemaByPapel(Long idPapel) {
        return getRepository().findAllPerfilSistemaByPapel(idPapel);
    }

    @Override
    public Collection<PerfilSistema> findAllPerfilSistema(PerfilSistemaFilter filter) {
        return getRepository().findAllPerfilSistema(filter.getNomePerfil());
    }

    @Override
    public PerfilSistema findOneComplete(Long id) {
        return getRepository().findOneComplete(id);
    }

    @Override
    @Transactional
    public PerfilSistema saveOrUpdatePerfilSistema(@ValidationSuite(clazz = PerfilSistema.class, context =
            ValidationContext.SAVE) PerfilSistema dto) {
        Set<PerfilPapel> perfilPapel = dto.getPerfilPapel();
        dto.setPerfilPapel(null);
        dto.setUsuarioPerfil(null);

        PerfilSistema save = save(dto);

        perfilPapel.forEach(pp -> pp.setIdentificacaoPerfil(save.getIdentificacaoPerfil()));

        perfilPapelService.deleteAllWithPerfilId(save.getIdentificacaoPerfil());
        perfilPapelService.save(perfilPapel);

        return save;
    }

    @Override
    public Optional<PerfilSistema> delete(@ValidationSuite(clazz = PerfilSistema.class,
            context = ValidationContext.DELETE) Long id) {
        perfilPapelService.deleteAllWithPerfilId(id);
        return super.delete(id);
    }
}
