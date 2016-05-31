package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.parametros.persistence.entity.Receitas;
import br.gov.to.sefaz.arr.parametros.persistence.entity.ReceitasRepasse;
import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Validação para somar os valores de
 * {@link br.gov.to.sefaz.arr.parametros.persistence.entity.ReceitasRepasse#percentualRepasse} na lista
 * {@link br.gov.to.sefaz.arr.parametros.persistence.entity.Receitas#receitasRepasseCollection} para verificar se o
 * valor somado será 100%.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 23/05/2016 16:12:00
 */
@Component
public class ReceitasPercentualRepassesValidator implements ServiceValidator<Receitas> {

    @Override
    public boolean support(Class<?> clazz, String context) {
        return Receitas.class.equals(clazz)
                && (ValidationContext.SAVE.equals(context)
                        || ValidationContext.UPDATE.equals(context));
    }

    @Override
    public Set<CustomViolation> validate(Receitas receitas) {
        HashSet<CustomViolation> customViolations = new HashSet<>();

        List<ReceitasRepasse> receitasRepasse = receitas.getReceitasRepasse();

        BigDecimal percentual = new BigDecimal(BigInteger.ZERO);

        for (ReceitasRepasse repasse : receitasRepasse) {
            BigDecimal percentualRepasse = repasse.getPercentualRepasse();
            percentual = percentual.add(percentualRepasse);
        }

        if (percentual.compareTo(new BigDecimal(100)) != 0) {
            String message = SourceBundle.getMessage(
                    MessageUtil.ARR, "parametros.ReceitasPercentualRepassesValidator.message");
            customViolations.add(new CustomViolation(message));
        }

        return customViolations;
    }
}
