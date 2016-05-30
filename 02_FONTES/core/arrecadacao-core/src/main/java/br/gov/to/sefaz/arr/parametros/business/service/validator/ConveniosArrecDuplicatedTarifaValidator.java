package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.parametros.persistence.entity.ConveniosTarifas;
import br.gov.to.sefaz.arr.parametros.persistence.repository.ConveniosTarifasRepository;
import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Validação para duplicação de {@link br.gov.to.sefaz.arr.parametros.persistence.entity.ConveniosTarifas} na lista
 * de {@link br.gov.to.sefaz.arr.parametros.persistence.entity.ConveniosArrec#getConveniosTarifas()}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 14/05/2016 11:10:00
 */
@Component
public class ConveniosArrecDuplicatedTarifaValidator implements ServiceValidator<ConveniosTarifas> {

    private final ConveniosTarifasRepository conveniosTarifasRepository;

    @Autowired
    public ConveniosArrecDuplicatedTarifaValidator(ConveniosTarifasRepository conveniosTarifasRepository) {
        this.conveniosTarifasRepository = conveniosTarifasRepository;
    }

    @Override
    public boolean support(Class<?> clazz, String context) {
        return clazz.equals(ConveniosTarifas.class) && (context.equals(ValidationContext.SAVE));
    }

    @Override
    public Set<CustomViolation> validate(ConveniosTarifas target) {
        List<ConveniosTarifas> tarifas = conveniosTarifasRepository
                .getAllConveniosTarifasByIdConvenioArrec(target.getIdConveniosArrec());

        return validateDuplicatedTarifa(target, tarifas);
    }

    /**
     * Valida se o {@link br.gov.to.sefaz.arr.parametros.persistence.entity.ConveniosTarifas} já possui um registro
     * com a mesma {@link br.gov.to.sefaz.arr.parametros.persistence.entity.ConveniosTarifas#formaPagamento} e a
     * mesma {@link br.gov.to.sefaz.arr.parametros.persistence.entity.ConveniosTarifas#dataInicio}.
     *
     * @param target a tarifa a qual será validada
     * @return lista de violações encontradas
     */
    public Set<CustomViolation> validateDuplicatedTarifa(ConveniosTarifas target, List<ConveniosTarifas> tarifas) {
        HashSet<CustomViolation> customViolations = new HashSet<>();

        tarifas.stream()
                .filter(conveniosTarifas ->
                        formaPagamentoAndDataInicioAreEquals(target, conveniosTarifas))
                .findAny()
                .ifPresent(conveniosTarifas -> {
                    String tarifaCadastrado = SourceBundle.getMessage(MessageUtil.ARR,
                            "parametros.conveniosTarifa.formaPagamento.dataInicio.cadastrado");
                    customViolations.add(new CustomViolation(tarifaCadastrado));
                });

        return customViolations;
    }

    private boolean formaPagamentoAndDataInicioAreEquals(ConveniosTarifas target, ConveniosTarifas conveniosTarifas) {
        return conveniosTarifas.getFormaPagamento().equals(target.getFormaPagamento())
                && conveniosTarifas.getDataInicio().equals(target.getDataInicio());
    }
}
