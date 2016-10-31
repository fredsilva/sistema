package br.gov.to.sefaz.arr.dare.service.validator;

import br.gov.to.sefaz.arr.dare.service.domain.DareEmail;
import br.gov.to.sefaz.arr.dare.service.impl.DareServiceImpl;
import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Validador que verifica se a lista de destinatários está vazia ao enviar um E-mail DARE.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 13/09/2016 11:07:00
 */
@Component
public class DareEmailReceiverNotEmptyValidator implements ServiceValidator<DareEmail> {

    @Override
    public boolean support(Class<?> clazz, String context) {
        return clazz.equals(DareEmail.class) && (context.equals(DareServiceImpl.SEND_DARE_EMAIL_CONTEXT));
    }

    @Override
    public Set<CustomViolation> validate(DareEmail dareEmail) {
        Set<CustomViolation> customViolations = new HashSet<>();

        if (Objects.nonNull(dareEmail.getDestinatarios()) && dareEmail.getDestinatarios().isEmpty()) {
            String message = SourceBundle.getMessage(MessageUtil.ARR,
                    "arr.par.dareEletronicoConsolidado.email.form.receiver.empty");
            customViolations.add(new CustomViolation(message));
        }

        return customViolations;
    }
}
