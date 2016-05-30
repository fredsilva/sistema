package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.parametros.persistence.entity.Receitas;
import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Validação para verificar se existe pelo menos um
 * {@link br.gov.to.sefaz.arr.parametros.persistence.entity.ReceitasRepasse} na lista
 * {@link br.gov.to.sefaz.arr.parametros.persistence.entity.Receitas#receitasRepasseCollection}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 23/05/2016 16:04:00
 */
@Component
public class ReceitasExistsRepassesValidator implements ServiceValidator<Receitas> {

    @Override
    public boolean support(Class<?> clazz, String context) {
        return Receitas.class.equals(clazz)
                && (ValidationContext.SAVE.equals(context)
                        || ValidationContext.UPDATE.equals(context));
    }

    @Override
    public Set<CustomViolation> validate(Receitas receitas) {
        HashSet<CustomViolation> customViolations = new HashSet<>();

        if (receitas.getReceitasRepasse().isEmpty()) {
            String message = SourceBundle.getMessage(
                    MessageUtil.ARR, "parametros.ReceitasExistsRepassesValidator.message");
            customViolations.add(new CustomViolation(message));
        }
        return customViolations;
    }
}
