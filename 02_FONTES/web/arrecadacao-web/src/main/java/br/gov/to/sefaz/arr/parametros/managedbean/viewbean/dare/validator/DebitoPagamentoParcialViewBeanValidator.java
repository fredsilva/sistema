package br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.validator;

import br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DebitoPagamentoParcialViewBean;
import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static br.gov.to.sefaz.arr.parametros.managedbean.validator.DareViewBeanValidator.VIEW_VALIDATOR;

/**
 * Validação para o {@link DebitoPagamentoParcialViewBean}.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 22/09/2016 15:22:00
 */
@Component
public class DebitoPagamentoParcialViewBeanValidator implements ServiceValidator<DebitoPagamentoParcialViewBean> {

    @Override
    public boolean support(Class<?> clazz, String context) {
        return clazz.equals(DebitoPagamentoParcialViewBean.class) && context.equals(VIEW_VALIDATOR);
    }

    @Override
    public Set<CustomViolation> validate(DebitoPagamentoParcialViewBean debitoPagamentoParcialViewBean) {
        HashSet<CustomViolation> customViolations = new HashSet<>();
        BigDecimal valorPagar = debitoPagamentoParcialViewBean.getValorPagar();

        if (debitoPagamentoParcialViewBean.getIsValorPagarDiferente()
                && (Objects.isNull(valorPagar) || valorPagar.equals(BigDecimal.ZERO))) {
            String message = SourceBundle.getMessage(MessageUtil.ARR,
                    "arr.par.dareEletronicoConsolidado.dadoPagamento.valorAPagar.emptyOrZero");
            customViolations.add(new CustomViolation(message));
        }

        return customViolations;
    }
}
