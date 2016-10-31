package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.persistence.entity.Receitas;
import br.gov.to.sefaz.arr.persistence.repository.ReceitasRepository;
import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Validação para duplicação de {@link br.gov.to.sefaz.arr.persistence.entity.Receitas} no banco de dados,
 * através do {@link br.gov.to.sefaz.arr.persistence.entity.Receitas#idReceita}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 23/05/2016 14:53:00
 */
@Component
public class ReceitasDuplicatedCodigoValidator implements ServiceValidator<Receitas> {

    private final ReceitasRepository receitasRepository;

    @Autowired
    public ReceitasDuplicatedCodigoValidator(
            ReceitasRepository receitasRepository) {
        this.receitasRepository = receitasRepository;
    }

    @Override
    public boolean support(Class<?> clazz, String context) {
        return Receitas.class.equals(clazz)
                && ValidationContext.SAVE.equals(context);
    }

    @Override
    public Set<CustomViolation> validate(Receitas target) {
        HashSet<CustomViolation> customViolations = new HashSet<>();

        if (receitasRepository.exists(target.getIdReceita())) {
            String message = SourceBundle.getMessage(
                    MessageUtil.ARR, "parametros.ReceitasDuplicatedCodigoValidator.duplicado");
            customViolations.add(new CustomViolation(message));
        }

        return customViolations;
    }
}
