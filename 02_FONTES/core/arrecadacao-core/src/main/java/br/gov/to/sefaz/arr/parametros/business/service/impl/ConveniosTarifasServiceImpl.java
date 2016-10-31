package br.gov.to.sefaz.arr.parametros.business.service.impl;

import br.gov.to.sefaz.arr.parametros.business.service.ConveniosTarifasService;
import br.gov.to.sefaz.arr.parametros.business.service.validator.ConveniosArrecDuplicatedTarifaValidator;
import br.gov.to.sefaz.arr.persistence.entity.ConveniosArrec;
import br.gov.to.sefaz.arr.persistence.entity.ConveniosTarifas;
import br.gov.to.sefaz.arr.persistence.enums.FormaPagamentoEnum;
import br.gov.to.sefaz.arr.persistence.repository.ConveniosTarifasRepository;
import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.business.service.validation.CustomValidationException;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.ValidationSuite;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;
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
        super(repository);
        this.duplicatedTarifaValidator = duplicatedTarifaValidator;
    }

    @Override
    protected ConveniosTarifasRepository getRepository() {
        return (ConveniosTarifasRepository) super.getRepository();
    }

    @Override
    public Collection<ConveniosTarifas> getAllConveniosTarifasByIdConvenioArrec(Long idConvenio) {
        return getRepository().find(sb -> sb.where().equal("idConveniosArrec", idConvenio));
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
    public BigDecimal getValorTarifaBy(Long codigoConvenio, FormaPagamentoEnum formaPagamento,
            LocalDate dataArrecadacao) {
        ConveniosTarifas conveniosTarifas = getRepository().findOne(select -> select
                .where()
                .equal("idConveniosArrec", codigoConvenio)
                .and()
                .equal("formaPagamento", formaPagamento)
                .and()
                .greaterEqualThan("dataInicio", dataArrecadacao)
                .and()
                .lessEqualThan("dataFim", dataArrecadacao));

        return Objects.isNull(conveniosTarifas) ? BigDecimal.ZERO : conveniosTarifas.getValor();
    }

    @Override
    public Collection<ConveniosTarifas> save(@ValidationSuite(context = ValidationContext.SAVE)
            Collection<ConveniosTarifas> list) {
        return super.save(list);
    }

}
