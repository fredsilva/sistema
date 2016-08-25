package br.gov.to.sefaz.par.gestao.business.service.validator;

import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.par.gestao.persistence.entity.ParametroGeral;
import br.gov.to.sefaz.par.gestao.persistence.enums.TipoParametroGeralEnum;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Validação que verifica se o {@link ParametroGeral} é estático. Caso seja estático valida se
 * {@link ParametroGeral#conteudoValores} esteja de acordo com o pattern "codigo,valor|codigo,
 * valor|...".
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 04/07/2016 10:15:31
 */
@Component
public class ParametroGeralTipoParametroValidator implements ServiceValidator<ParametroGeral> {

    private static final String CONTEUDO_VALORES_PATTERN = "[-\\.\\w]+,[-\\.\\w]+(\\|[-\\.\\w]+,[-\\.\\w]+)*";

    @Override
    public boolean support(Class<?> clazz, String context) {
        return clazz.equals(ParametroGeral.class) && (ValidationContext.SAVE.equals(context)
                || ValidationContext.UPDATE.equals(context));
    }

    @Override
    public Set<CustomViolation> validate(ParametroGeral parametroGeral) {
        HashSet<CustomViolation> customViolations = new HashSet<>();

        if (TipoParametroGeralEnum.ESTATICO.equals(parametroGeral.getTipoParametroGeral())
                && !validateConteudoValoresPattern(parametroGeral.getConteudoValores())) {
            String message = SourceBundle.getMessage(MessageUtil.PAR,
                    "gestao.manutencaoParametros.tipoParametroGeral.estatico.validator");
            customViolations.add(new CustomViolation(message));
        }

        return customViolations;
    }

    private Boolean validateConteudoValoresPattern(String conteudoValores) {
        return conteudoValores.matches(CONTEUDO_VALORES_PATTERN);
    }

}
