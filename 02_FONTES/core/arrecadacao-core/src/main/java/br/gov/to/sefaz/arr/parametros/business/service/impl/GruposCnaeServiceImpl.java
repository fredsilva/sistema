package br.gov.to.sefaz.arr.parametros.business.service.impl;

import br.gov.to.sefaz.arr.parametros.business.service.GruposCnaeService;
import br.gov.to.sefaz.arr.persistence.entity.GruposCnae;
import br.gov.to.sefaz.arr.persistence.entity.GruposCnaePK;
import br.gov.to.sefaz.arr.persistence.repository.GruposCnaeRepository;
import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.ValidationSuite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Implementação de um {@link GruposCnaeService}.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 17/05/2016 14:24:00
 */
@Service
public class GruposCnaeServiceImpl extends DefaultCrudService<GruposCnae, GruposCnaePK> implements GruposCnaeService {

    public static final String DUPLICATED = "gruposCnaeDuplicated";

    @Autowired
    public GruposCnaeServiceImpl(GruposCnaeRepository repository) {
        super(repository);
    }

    @Override
    protected GruposCnaeRepository getRepository() {
        return (GruposCnaeRepository) super.getRepository();
    }

    @Override
    public Collection<GruposCnae> save(
            @ValidationSuite(context = ValidationContext.SAVE)
            Collection<GruposCnae> list) {
        return super.save(list);
    }

    @Override
    public void validateDuplicated(
            @ValidationSuite(context = DUPLICATED)
            Collection<GruposCnae> gruposCnaes) {
        // A validação é feita através do @ValidationSuite.
    }

    @Override
    @Transactional
    public void deleteByGrupo(Integer idGrupoCnae) {
        getRepository().deleteByGrupo(idGrupoCnae);
    }
}
