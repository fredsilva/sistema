package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.parametros.persistence.entity.ConveniosArrec;
import br.gov.to.sefaz.arr.parametros.persistence.enums.TipoCodigoBarraEnum;
import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Validação que verifica se o {@link br.gov.to.sefaz.arr.parametros.persistence.entity.ConveniosArrec#tipoBarra} for
 * diferente de {@link br.gov.to.sefaz.arr.parametros.persistence.enums.TipoCodigoBarraEnum#SEM_BARRA}, pelo menos uma
 * {@link br.gov.to.sefaz.arr.parametros.persistence.entity.Receitas} é obrigatória.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 20/05/2016 11:58:00
 */
@Component
public class ConveniosArrecTipoBarraWithReceitasValidator implements ServiceValidator<ConveniosArrec> {

    @Override
    public boolean support(Class<?> clazz, String context) {
        return clazz.equals(ConveniosArrec.class) && (context.equals(ValidationContext.SAVE)
                || context.equals(ValidationContext.UPDATE));
    }

    @Override
    public Set<CustomViolation> validate(ConveniosArrec conveniosArrec) {
        HashSet<CustomViolation> customViolations = new HashSet<>();

        if (conveniosArrec.getTipoBarra() != TipoCodigoBarraEnum.SEM_BARRA
                && conveniosArrec.getReceitas().isEmpty()) {

            String message = SourceBundle.getMessage(MessageUtil.ARR,
                    "parametros.convenioArrec.tipoBarra.receita.cadastrada");
            customViolations.add(new CustomViolation(message));
        }

        return customViolations;
    }
}
