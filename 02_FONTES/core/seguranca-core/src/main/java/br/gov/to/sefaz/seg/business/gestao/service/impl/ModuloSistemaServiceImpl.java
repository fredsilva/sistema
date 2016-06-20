package br.gov.to.sefaz.seg.business.gestao.service.impl;

import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.seg.business.gestao.service.ModuloSistemaService;
import br.gov.to.sefaz.seg.persistence.entity.ModuloSistema;
import br.gov.to.sefaz.seg.persistence.repository.ModuloSistemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Implementação do serviço da entidade {@link ModuloSistemaService}.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 13/06/2016 20:19:00
 */
@Service
public class ModuloSistemaServiceImpl extends DefaultCrudService<ModuloSistema, Long>
        implements ModuloSistemaService {

    @Autowired
    public ModuloSistemaServiceImpl(ModuloSistemaRepository repository) {
        super(repository, new Sort(new Sort.Order(Sort.Direction.ASC, "descricaoModuloSistema")));
    }

    @Override
    protected ModuloSistemaRepository getRepository() {
        return (ModuloSistemaRepository) super.getRepository();
    }

    @Override
    public Collection<ModuloSistema> findAll() {
        return getRepository().findAllFetched();
    }
}
