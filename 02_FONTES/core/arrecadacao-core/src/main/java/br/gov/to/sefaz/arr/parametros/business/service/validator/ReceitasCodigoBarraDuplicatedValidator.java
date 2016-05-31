package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.parametros.persistence.entity.Receitas;
import br.gov.to.sefaz.arr.parametros.persistence.repository.ReceitasRepository;
import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.persistence.predicate.AndPredicateBuilder;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Validação para duplicação de {@link br.gov.to.sefaz.arr.parametros.persistence.entity.Receitas#idBarra}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 23/05/2016 15:45:00
 */
@Component
public class ReceitasCodigoBarraDuplicatedValidator implements ServiceValidator<Receitas> {

    private final ReceitasRepository receitasRepository;

    @Autowired
    public ReceitasCodigoBarraDuplicatedValidator(
            ReceitasRepository receitasRepository) {
        this.receitasRepository = receitasRepository;
    }

    @Override
    public boolean support(Class<?> clazz, String context) {
        return Receitas.class.equals(clazz)
                && (ValidationContext.SAVE.equals(context)
                        || ValidationContext.UPDATE.equals(context));
    }

    @Override
    public Set<CustomViolation> validate(Receitas receitas) {
        HashSet<CustomViolation> customViolations = new HashSet<>();
        Integer idBarra = receitas.getIdBarra();

        List<Receitas> receitasList = receitasRepository.findAll((root, query, cb) -> {
            return new AndPredicateBuilder(root, cb)
                    .equalsTo("idBarra", idBarra)
                    .build();
        });

        if (!receitasList.isEmpty() && !Objects.isNull(idBarra)) {
            String message = SourceBundle.getMessage(
                    MessageUtil.ARR, "parametros.ReceitasCodigoBarraDuplicatedValidator.duplicado");
            customViolations.add(new CustomViolation(message));
        }

        return customViolations;
    }
}
