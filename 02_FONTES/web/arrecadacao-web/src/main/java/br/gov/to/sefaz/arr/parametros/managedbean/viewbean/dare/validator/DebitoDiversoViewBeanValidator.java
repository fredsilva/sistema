package br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.validator;

import br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DebitoDiversoViewBean;
import br.gov.to.sefaz.arr.persistence.entity.ReceitasTaxas;
import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.util.application.ApplicationUtil;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static br.gov.to.sefaz.arr.parametros.managedbean.validator.DareViewBeanValidator.VIEW_VALIDATOR;

/**
 * Validador de {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DebitoDiversoViewBean} que valida
 * caso o valor do limite definido no subcódigo da receita seja inferior ao informado e que a quantidade de TSE e o
 * valor do TSE não sejam nulos.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 04/10/2016 10:19:00
 */
@Component
public class DebitoDiversoViewBeanValidator implements ServiceValidator<DebitoDiversoViewBean> {
    @Override
    public boolean support(Class<?> clazz, String context) {
        return clazz.equals(DebitoDiversoViewBean.class) && context.equals(VIEW_VALIDATOR);
    }

    @Override
    public Set<CustomViolation> validate(DebitoDiversoViewBean debitoDiverso) {
        Set<CustomViolation> customViolations = new HashSet<>();

        if (!debitoDiverso.getReceitasTaxas().isEmpty()) {
            if (verifyContainsSelectedReceitaTaxa(debitoDiverso.getIdSubCodigo(), customViolations)) {
                validateReceitasTaxas(debitoDiverso, customViolations);
            }
        } else {
            validateEmptyReceitasTaxas(debitoDiverso, customViolations);
        }

        return customViolations;
    }

    private boolean verifyContainsSelectedReceitaTaxa(Integer idSubCodigo, Set<CustomViolation> customViolations) {
        if (Objects.isNull(idSubCodigo)) {
            String message = SourceBundle.getMessage(MessageUtil.ARR,
                    "arr.par.dare.debitoDiversoViewBean.subcodigo.empty");
            customViolations.add(new CustomViolation(message));
            return false;
        }
        return true;
    }

    private void validateEmptyReceitasTaxas(DebitoDiversoViewBean debitoDiverso,
            Set<CustomViolation> customViolations) {
        if (Objects.isNull(debitoDiverso.getValorImposto())) {
            String message = SourceBundle.getMessage(MessageUtil.ARR,
                    "arr.par.dare.debitoDiversoViewBean.valorImposto.empty");
            customViolations.add(new CustomViolation(message));
        }
        if (Objects.isNull(debitoDiverso.getValorJuros())) {
            String message = SourceBundle.getMessage(MessageUtil.ARR,
                    "arr.par.dare.debitoDiversoViewBean.valorJuros.empty");
            customViolations.add(new CustomViolation(message));
        }
        if (Objects.isNull(debitoDiverso.getValorMulta())) {
            String message = SourceBundle.getMessage(MessageUtil.ARR,
                    "arr.par.dare.debitoDiversoViewBean.valorMulta.empty");
            customViolations.add(new CustomViolation(message));
        }
    }

    private void validateReceitasTaxas(DebitoDiversoViewBean debitoDiverso,
            Set<CustomViolation> customViolations) {
        ReceitasTaxas receitasTaxas = debitoDiverso.getReceitasTaxas()
                .stream()
                .filter(receitas -> receitas.getIdSubcodigo().equals(debitoDiverso.getIdSubCodigo()))
                .findAny()
                .orElse(null);

        BigDecimal valorLimite = receitasTaxas.getValorLimite();

        if (verifyQuantidadeTseAndValorUnitarioAreNotNull(debitoDiverso, customViolations)
                && Objects.nonNull(valorLimite)
                && debitoDiverso.getValorTse().compareTo(valorLimite) == 1) {
            String message = SourceBundle.getMessage(MessageUtil.ARR,
                    "arr.par.dare.debitoDiversoViewBean.valorTse.greaterThan");
            String messageFormat = MessageFormat
                    .format(message, NumberFormat.getCurrencyInstance(ApplicationUtil.LOCALE).format(valorLimite));
            customViolations.add(new CustomViolation(messageFormat));
        }
    }

    private boolean verifyQuantidadeTseAndValorUnitarioAreNotNull(DebitoDiversoViewBean debitoDiverso,
            Set<CustomViolation> customViolations) {
        if (Objects.isNull(debitoDiverso.getQuantidadeTse())
                || Objects.isNull(debitoDiverso.getValorUnitarioTse())) {
            String message = SourceBundle.getMessage(MessageUtil.ARR,
                    "arr.par.dare.debitoDiversoViewBean.quantidadeTse.empty");
            customViolations.add(new CustomViolation(message));
            return false;
        }
        return true;
    }

}
