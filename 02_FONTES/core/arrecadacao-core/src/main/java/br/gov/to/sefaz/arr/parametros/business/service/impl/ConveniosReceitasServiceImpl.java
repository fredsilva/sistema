package br.gov.to.sefaz.arr.parametros.business.service.impl;

import br.gov.to.sefaz.arr.parametros.business.service.ConveniosReceitasService;
import br.gov.to.sefaz.arr.parametros.business.service.validator.ConveniosArrecDuplicatedReceitaValidator;
import br.gov.to.sefaz.arr.parametros.persistence.entity.ConveniosArrec;
import br.gov.to.sefaz.arr.parametros.persistence.entity.ConveniosReceitas;
import br.gov.to.sefaz.arr.parametros.persistence.entity.ConveniosReceitasPK;
import br.gov.to.sefaz.arr.parametros.persistence.entity.Receitas;
import br.gov.to.sefaz.arr.parametros.persistence.repository.ConveniosReceitasRepository;
import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.business.service.validation.CustomValidationException;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Implementação do serviço da entidade {@link br.gov.to.sefaz.arr.parametros.persistence.entity.ConveniosReceitas}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 20/05/2016 10:22:00
 */
@Service
public class ConveniosReceitasServiceImpl extends DefaultCrudService<ConveniosReceitas, ConveniosReceitasPK>
        implements ConveniosReceitasService {

    private final ConveniosArrecDuplicatedReceitaValidator duplicatedReceitaValidator;

    @Autowired
    public ConveniosReceitasServiceImpl(ConveniosReceitasRepository repository,
            ConveniosArrecDuplicatedReceitaValidator duplicatedReceitaValidator) {
        super(repository, new Sort(new Sort.Order(Sort.Direction.ASC, "idReceita"),
                new Sort.Order(Sort.Direction.ASC, "idConvenio")));
        this.duplicatedReceitaValidator = duplicatedReceitaValidator;
    }

    @Override
    protected ConveniosReceitasRepository getRepository() {
        return (ConveniosReceitasRepository) super.getRepository();
    }

    @Override
    public void deleteAllByIdConvenio(Long idConvenio) {
        getRepository().deleteAllByIdConvenio(idConvenio);
    }

    @Override
    public void validateDuplicatedReceita(ConveniosArrec conveniosArrec, Receitas receita) {
        Set<CustomViolation> customViolations = duplicatedReceitaValidator
                .validateDuplicatedReceita(conveniosArrec.getReceitas(), receita.getIdReceita());

        if (!customViolations.isEmpty()) {
            throw new CustomValidationException(customViolations);
        }
    }
}
