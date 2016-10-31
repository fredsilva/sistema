package br.gov.to.sefaz.seg.business.gestao.service.validator;

import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.seg.persistence.entity.PapelSistema;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Validação que verifica se o {@link br.gov.to.sefaz.seg.persistence.entity.PapelSistema#getPapelOpcao()} está vazio.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 15/08/2016 10:11:00
 */
@Component
public class PapelSistemaCannotHaveEmptyOptionListValidator implements
        ServiceValidator<PapelSistema> {

    @Override
    public boolean support(Class<?> clazz, String context) {
        return clazz.equals(PapelSistema.class)
                && ValidationContext.SAVE.equals(context);
    }

    @Override
    public Set<CustomViolation> validate(PapelSistema papelSistema) {
        HashSet<CustomViolation> customViolations = new HashSet<>();

        if (papelSistema.getPapelOpcao().isEmpty()) {
            String message = SourceBundle.getMessage(MessageUtil.SEG,
                    "seg.gestao.papelSistema.form.erro.opcaoAplicacao.vazio");
            customViolations.add(new CustomViolation(message));
        }

        return customViolations;

    }
}
