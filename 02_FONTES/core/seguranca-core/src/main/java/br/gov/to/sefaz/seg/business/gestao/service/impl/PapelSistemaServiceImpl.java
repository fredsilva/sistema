package br.gov.to.sefaz.seg.business.gestao.service.impl;

import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.ValidationSuite;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import br.gov.to.sefaz.seg.business.gestao.service.PapelOpcaoService;
import br.gov.to.sefaz.seg.business.gestao.service.PapelSistemaService;
import br.gov.to.sefaz.seg.business.gestao.service.filter.PapelSistemaFilter;
import br.gov.to.sefaz.seg.persistence.entity.PapelOpcao;
import br.gov.to.sefaz.seg.persistence.entity.PapelSistema;
import br.gov.to.sefaz.seg.persistence.repository.PapelSistemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Implementação do contrato de acesso do serviço de Perfis de Usuários.
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 22/07/2016 14:57:00
 */
@Service
public class PapelSistemaServiceImpl extends DefaultCrudService<PapelSistema, Long>
        implements PapelSistemaService {

    private final PapelOpcaoService papelOpcaoService;

    @Autowired
    public PapelSistemaServiceImpl(BaseRepository<PapelSistema, Long> repository, PapelOpcaoService papelOpcaoService) {
        super(repository);
        this.papelOpcaoService = papelOpcaoService;
    }

    @Override
    protected PapelSistemaRepository getRepository() {
        return (PapelSistemaRepository) super.getRepository();
    }

    @Override
    public List<PapelSistema> findAllPerfilPapelOpcao(PapelSistemaFilter filter) {
        return getRepository().findAllCounted(filter.getNomePapel());
    }

    @Override
    public PapelSistema saveOrUpdatePapelSistema(@ValidationSuite(clazz = PapelSistema.class,
            context = ValidationContext.SAVE) PapelSistema dto) {
        Set<PapelOpcao> papelOpcao = dto.getPapelOpcao();
        dto.setPapelOpcao(null);

        // Salva Papel sistema
        PapelSistema papelSistema = save(dto);

        //Atualiza o identificador do papel sistema nos papéis opção
        papelOpcao.forEach(po -> po.setIdentificacaoPapel(papelSistema.getIdentificacaoPapel()));

        // Salva a lista de paéis opção
        papelOpcaoService.removeAllPapelOpcaoByPapelId(dto.getId());
        papelOpcaoService.save(papelOpcao);

        return papelSistema;
    }

    @Override
    public PapelSistema findOneCounted(Long id) {
        return getRepository().findOneCounted(id);
    }

    @Override
    public Collection<PapelSistema> findAllPapeisPerfil() {
        return getRepository().findAllWithPapeis();
    }

    @Override
    public Collection<PapelSistema> findAllPapeisByPerfilId(Long id) {
        return getRepository().findAllByPerfilId(id);
    }

    @Override
    public Optional<PapelSistema> delete(@ValidationSuite(clazz = PapelSistema.class,
            context = ValidationContext.DELETE) Long id) {
        return super.delete(id);
    }

}
