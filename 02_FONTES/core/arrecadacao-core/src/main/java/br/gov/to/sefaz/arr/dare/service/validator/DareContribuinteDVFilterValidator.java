package br.gov.to.sefaz.arr.dare.service.validator;

import br.gov.to.sefaz.arr.dare.service.filter.DareContribuinteFilter;
import br.gov.to.sefaz.arr.persistence.enums.TipoPessoaEnum;
import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.custom.CnpjValidatorHandler;
import br.gov.to.sefaz.business.service.validation.custom.CpfValidatorHandler;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static br.gov.to.sefaz.arr.dare.service.impl.ContribuitesServiceImpl.FILTER_CONTEXT;

/**
 * Validador de dígito verificador de CPF e CNPJ de um contribuinte.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 05/09/2016 11:28:00
 */
@Component
public class DareContribuinteDVFilterValidator implements ServiceValidator<DareContribuinteFilter> {

    private final CpfValidatorHandler cpfValidatorHandler;
    private final CnpjValidatorHandler cnpjValidatorHandler;

    public DareContribuinteDVFilterValidator() {
        this.cpfValidatorHandler = new CpfValidatorHandler();
        this.cnpjValidatorHandler = new CnpjValidatorHandler();
    }

    @Override
    public boolean support(Class<?> clazz, String context) {
        return clazz.equals(DareContribuinteFilter.class) && (context.equals(FILTER_CONTEXT));
    }

    @Override
    public Set<CustomViolation> validate(DareContribuinteFilter contribuinteFilter) {
        Set<CustomViolation> customViolations = new HashSet<>();
        TipoPessoaEnum tipoPessoa = contribuinteFilter.getTipoPessoa();
        Long idContribuinte = contribuinteFilter.getIdContribuinte();
        boolean isObjectsNotNull = !Objects.isNull(tipoPessoa) && !Objects.isNull(idContribuinte);

        if (isObjectsNotNull && !isCpfOrCnpjDigitValid(tipoPessoa, idContribuinte)) {
            String message = SourceBundle.getMessage(MessageUtil.ARR,
                    "dare.busca.contribuinte.id.digitoInvalido");
            customViolations.add(new CustomViolation(message));
        }

        return customViolations;
    }

    private boolean isCpfOrCnpjDigitValid(TipoPessoaEnum tipoPessoa, Long idContribuinte) {
        if (tipoPessoa.equals(TipoPessoaEnum.CPF)) {
            return cpfValidatorHandler.validateCpf(String.valueOf(idContribuinte));
        } else if (tipoPessoa.equals(TipoPessoaEnum.CNPJ)) {
            return cnpjValidatorHandler.validateCnpj(String.valueOf(idContribuinte));
        }

        return true;
    }


}
