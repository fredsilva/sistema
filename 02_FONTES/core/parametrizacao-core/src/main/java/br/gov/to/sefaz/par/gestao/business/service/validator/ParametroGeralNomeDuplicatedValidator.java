package br.gov.to.sefaz.par.gestao.business.service.validator;

import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.par.gestao.persistence.entity.ParametroGeral;
import br.gov.to.sefaz.par.gestao.persistence.repository.ParametroGeralRepository;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Validação que verifica se o {@link br.gov.to.sefaz.par.gestao.persistence.entity.ParametroGeral} é estático.
 * Caso seja estático valida se {@link br.gov.to.sefaz.par.gestao.persistence.entity.ParametroGeral#conteudoValores}
 * esteja de acordo com o pattern "codigo,valor|codigo,valor|...".
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 04/07/2016 10:15:31
 */
@Component
public class ParametroGeralNomeDuplicatedValidator implements ServiceValidator<ParametroGeral> {

    private final ParametroGeralRepository repository;

    @Autowired
    public ParametroGeralNomeDuplicatedValidator(
            ParametroGeralRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean support(Class<?> clazz, String context) {
        return clazz.equals(ParametroGeral.class) && (ValidationContext.SAVE.equals(context)
                || ValidationContext.UPDATE.equals(context));
    }

    @Override
    public Set<CustomViolation> validate(ParametroGeral parametroGeral) {
        Set<CustomViolation> customViolations = new HashSet<>();
        if (repository.findExitsNome(parametroGeral.getId(), parametroGeral.getNomeParametroGeral())) {
            this.setViolation(customViolations);
        }
        return customViolations;
    }

    private void setViolation(Set<CustomViolation> customViolations) {
        String message = SourceBundle.getMessage(MessageUtil.PAR,
                "gestao.manutencaoParametros.nomeParametroGeral.duplicated.validator");
        customViolations.add(new CustomViolation(message));
    }

}
