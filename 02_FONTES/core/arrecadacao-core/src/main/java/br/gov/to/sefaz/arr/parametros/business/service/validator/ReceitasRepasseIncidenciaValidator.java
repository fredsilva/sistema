package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.persistence.entity.ReceitasRepasse;
import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Validação para existência de alguma incidência para
 * {@link br.gov.to.sefaz.arr.persistence.entity.ReceitasRepasse}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 23/05/2016 15:45:00
 */
@Component
public class ReceitasRepasseIncidenciaValidator implements ServiceValidator<ReceitasRepasse> {

    @Override
    public boolean support(Class<?> clazz, String context) {
        return ReceitasRepasse.class.equals(clazz)
                && (ValidationContext.SAVE.equals(context)
                || ValidationContext.UPDATE.equals(context));
    }

    @Override
    public Set<CustomViolation> validate(ReceitasRepasse target) {
        HashSet<CustomViolation> customViolations = new HashSet<>();

        if (!target.getReparteCorrecao() && !target.getReparteMulta() && !target.getReparteTaxa()
                && !target.getReparteJuros() && !target.getRepartePrincipal()) {
            String message = SourceBundle.getMessage(
                    MessageUtil.ARR, "parametros.receitasRepasse.incidencia.obrigatorio");
            customViolations.add(new CustomViolation(message));
        }

        return customViolations;
    }
}
