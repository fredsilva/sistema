package br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.validator;

import br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEViewBean;
import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static br.gov.to.sefaz.arr.parametros.managedbean.validator.DareViewBeanValidator.GERAR_DARE_VALIDATOR;

/**
 * Validador que valida se a
 * {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEViewBean#listaPagamentos} não está vazia ou
 * supera o valor limite de 999.999.999,99.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 04/10/2016 17:57:00
 */
@Component
public class GerarDareValidator implements ServiceValidator<DareEViewBean> {
    @Override
    public boolean support(Class<?> clazz, String context) {
        return clazz.equals(DareEViewBean.class) && context.equals(GERAR_DARE_VALIDATOR);
    }

    @Override
    public Set<CustomViolation> validate(DareEViewBean dareEViewBean) {
        HashSet<CustomViolation> customViolations = new HashSet<>();

        if (Objects.isNull(dareEViewBean.getListaPagamentos())
                || dareEViewBean.getListaPagamentos().isEmpty()) {
            String message = SourceBundle.getMessage(MessageUtil.ARR,
                    "arr.par.dare.DareEViewBean.listaPagamentos.empty");
            customViolations.add(new CustomViolation(message));
        } else if (dareEViewBean.getValorTotal().compareTo(new BigDecimal("999999999.99")) > 0) {
            String messageLimit = SourceBundle.getMessage(MessageUtil.ARR,
                    "arr.par.dare.dareEPagamento.totalRecolher.limit");
            customViolations.add(new CustomViolation(messageLimit));
        }

        return customViolations;
    }
}
