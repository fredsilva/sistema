package br.gov.to.sefaz.arr.dare.service.validator;

import br.gov.to.sefaz.arr.dare.service.filter.DebitoIpvaFilter;
import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static br.gov.to.sefaz.arr.dare.service.impl.DebitosContaCorrenteServiceImpl.DEBITO_IPVA_FILTER_CONTEXT;


/**
 * Validador de Ano inicial e ano final referente ao Debito de IPVA do DARE.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 08/09/2016 16:58:00
 */
@Component
public class DebitoIpvaFilterValidator implements ServiceValidator<DebitoIpvaFilter> {

    @Override
    public boolean support(Class<?> clazz, String context) {
        return clazz.equals(DebitoIpvaFilter.class) && (context.equals(DEBITO_IPVA_FILTER_CONTEXT));
    }

    @Override
    public Set<CustomViolation> validate(DebitoIpvaFilter ipvaFilter) {
        Set<CustomViolation> customViolations = new HashSet<>();

        if (ipvaFilterNotContainsNullValues(ipvaFilter)
                && ipvaFilter.getAnoFinal() < ipvaFilter.getAnoInicial()) {
            String message = SourceBundle.getMessage(MessageUtil.ARR,
                    "dare.DebitoIpvaFilter.anoFinal.inferior");
            customViolations.add(new CustomViolation(message));
        }

        return customViolations;
    }

    private boolean ipvaFilterNotContainsNullValues(DebitoIpvaFilter ipvaFilter) {
        return !Objects.isNull(ipvaFilter.getAnoInicial())
                && !Objects.isNull(ipvaFilter.getAnoFinal());
    }
}
