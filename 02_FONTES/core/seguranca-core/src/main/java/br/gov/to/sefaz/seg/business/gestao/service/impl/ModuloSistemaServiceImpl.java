package br.gov.to.sefaz.seg.business.gestao.service.impl;

import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.seg.business.gestao.service.ModuloSistemaService;
import br.gov.to.sefaz.seg.persistence.entity.ModuloSistema;
import br.gov.to.sefaz.seg.persistence.repository.ModuloSistemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementação do serviço da entidade {@link br.gov.to.sefaz.seg.business.gestao.service.ModuloSistemaService}.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 13/06/2016 20:19:00
 */
@Service
public class ModuloSistemaServiceImpl extends DefaultCrudService<ModuloSistema, Long>
        implements ModuloSistemaService {

    @Autowired
    public ModuloSistemaServiceImpl(ModuloSistemaRepository repository) {
        super(repository);
    }

    @Override
    protected ModuloSistemaRepository getRepository() {
        return (ModuloSistemaRepository) super.getRepository();
    }

    @Override
    public Collection<ModuloSistema> findAll() {
        return getRepository().findAllFetched().stream().collect(Collectors.toList());
    }

    @Override
    public List<ModuloSistema> findAllSortedByAbreviacao() {
        return getRepository().findAllSortedByAbreviacao();
    }
}
