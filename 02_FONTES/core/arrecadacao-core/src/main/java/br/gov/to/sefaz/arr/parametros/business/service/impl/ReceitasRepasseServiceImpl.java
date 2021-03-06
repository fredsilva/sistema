package br.gov.to.sefaz.arr.parametros.business.service.impl;

import br.gov.to.sefaz.arr.parametros.business.service.ReceitasRepasseService;
import br.gov.to.sefaz.arr.persistence.entity.ReceitasRepasse;
import br.gov.to.sefaz.arr.persistence.entity.ReceitasRepassePK;
import br.gov.to.sefaz.arr.persistence.repository.ReceitasRepasseRepository;
import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.ValidationSuite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Implementação do serviço da entidade {@link br.gov.to.sefaz.arr.persistence.entity.ReceitasRepasse}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 19/05/2016 17:21:00
 */
@Service
@Transactional
public class ReceitasRepasseServiceImpl extends DefaultCrudService<ReceitasRepasse, ReceitasRepassePK>
        implements ReceitasRepasseService {

    @Autowired
    public ReceitasRepasseServiceImpl(
            ReceitasRepasseRepository repository) {
        super(repository);
    }

    @Override
    protected ReceitasRepasseRepository getRepository() {
        return (ReceitasRepasseRepository) super.getRepository();
    }

    @Override
    public void deleteAllRepassesByIdReceita(Integer idReceita) {
        getRepository().deleteAllRepassesByIdReceita(idReceita);
    }

    @Override
    public Collection<ReceitasRepasse> getReceitasRepasseByIdReceita(Integer idReceita) {
        return getRepository().find(sb -> sb.where().equal("idReceita", idReceita).orderById());
    }

    @Override
    public void validateReceitasRepasse(
            @ValidationSuite(context = ValidationContext.SAVE) ReceitasRepasse receitasRepasse) {
        // Método que valida a Lista de Receitas Repasse por anotação
    }
}
