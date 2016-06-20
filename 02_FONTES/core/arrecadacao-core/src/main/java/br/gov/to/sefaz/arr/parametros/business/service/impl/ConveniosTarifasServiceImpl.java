package br.gov.to.sefaz.arr.parametros.business.service.impl;

import br.gov.to.sefaz.arr.parametros.business.service.ConveniosTarifasService;
import br.gov.to.sefaz.arr.parametros.business.service.validator.ConveniosArrecDuplicatedTarifaValidator;
import br.gov.to.sefaz.arr.parametros.persistence.entity.ConveniosArrec;
import br.gov.to.sefaz.arr.parametros.persistence.entity.ConveniosTarifas;
import br.gov.to.sefaz.arr.parametros.persistence.repository.ConveniosTarifasRepository;
import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.business.service.validation.CustomValidationException;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.ValidationSuite;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.persistence.predicate.AndPredicateBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Set;

/**
 * Implementação do serviço da entidade {@link ConveniosTarifas}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 06/05/2016 10:49:24
 */
@Service
@Transactional
public class ConveniosTarifasServiceImpl extends DefaultCrudService<ConveniosTarifas, Integer>
        implements ConveniosTarifasService {

    private static final String ADD_IN_CONVENIOS_LIST = "ADD_IN_CONVENIOS_LIST";

    private final ConveniosArrecDuplicatedTarifaValidator duplicatedTarifaValidator;

    @Autowired
    public ConveniosTarifasServiceImpl(ConveniosTarifasRepository repository,
            ConveniosArrecDuplicatedTarifaValidator duplicatedTarifaValidator) {
        super(repository, new Sort(new Sort.Order(Sort.Direction.ASC, "idTarifa")));
        this.duplicatedTarifaValidator = duplicatedTarifaValidator;
    }

    @Override
    protected ConveniosTarifasRepository getRepository() {
        return (ConveniosTarifasRepository) super.getRepository();
    }

    @Override
    public Collection<ConveniosTarifas> getAllConveniosTarifasByIdConvenioArrec(Long idConvenio) {
        return getRepository().findAll((root, query, cb) -> new AndPredicateBuilder(root, cb)
                .equalsTo("idConveniosArrec", idConvenio)
                .build());
    }

    @Override
    public void deleteAllByIdConvenio(Long idConvenio) {
        getRepository().deleteAllByIdConvenio(idConvenio);
    }

    @Override
    public void validateDuplicatedTarifa(ConveniosArrec conveniosArrec, ConveniosTarifas conveniosTarifas) {
        Set<CustomViolation> customViolations = duplicatedTarifaValidator
                .validateDuplicatedTarifa(conveniosTarifas, conveniosArrec.getConveniosTarifas());

        if (!customViolations.isEmpty()) {
            throw new CustomValidationException(customViolations);
        }
    }

    @Override
    public void validateDataFimTarifa(@ValidationSuite(context = ADD_IN_CONVENIOS_LIST) ConveniosTarifas
            conveniosTarifas) {
        // Realiza a validação pelo contexto.
    }

    @Override
    public Collection<ConveniosTarifas> save(@ValidationSuite(context = ValidationContext.SAVE)
            Collection<ConveniosTarifas> list) {
        return super.save(list);
    }

}
