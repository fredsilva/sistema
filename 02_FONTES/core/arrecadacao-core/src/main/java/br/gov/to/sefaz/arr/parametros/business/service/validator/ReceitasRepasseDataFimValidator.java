package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.persistence.entity.ReceitasRepasse;
import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Validação para garantir que a {@link br.gov.to.sefaz.arr.persistence.entity.ConveniosTarifas#dataFim} seja
 * superior a {@link br.gov.to.sefaz.arr.persistence.entity.ConveniosTarifas#dataInicio}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 16/05/2016 10:49:24
 */
@Component
public class ReceitasRepasseDataFimValidator implements ServiceValidator<ReceitasRepasse> {

    @Override
    public boolean support(Class<?> clazz, String context) {
        return clazz.equals(ReceitasRepasse.class)
                && (context.equals(ValidationContext.SAVE)
                        || context.equals(ValidationContext.UPDATE));
    }

    @Override
    public Set<CustomViolation> validate(ReceitasRepasse target) {
        HashSet<CustomViolation> customViolations = new HashSet<>();
        LocalDate dataFinal = target.getDataFinal();
        LocalDate dataInicio = target.getDataInicio();

        if (dataFinal != null && dataFinal.isBefore(dataInicio)) {
            String message = SourceBundle.getMessage(MessageUtil.ARR,
                    "parametros.receitasRepasse.dataFim.inferior.dataInicio");
            customViolations.add(new CustomViolation(message));
        }

        return customViolations;
    }
}
