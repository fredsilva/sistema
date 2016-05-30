package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.parametros.persistence.entity.TipoGruposCnaes;
import br.gov.to.sefaz.arr.parametros.persistence.repository.TipoGruposCnaesRepository;
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
 * <p>{@link ServiceValidator} que valida se o {@link TipoGruposCnaes#getIdGrupoCnae()} já existe no banco.
 * Não pode existir mais de um tipo de grupo cnae com o mesmo ID.</p>
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 18/05/2016 15:28:00
 */
@Component
public class TipoGruposCnaeDuplicatedValidator implements ServiceValidator<TipoGruposCnaes> {

    private final TipoGruposCnaesRepository repository;

    @Autowired
    public TipoGruposCnaeDuplicatedValidator(TipoGruposCnaesRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean support(Class<?> clazz, String context) {
        return TipoGruposCnaes.class.equals(clazz)
                && ValidationContext.SAVE.equals(context);
    }

    @Override
    public Set<CustomViolation> validate(TipoGruposCnaes target) {
        HashSet<CustomViolation> customViolations = new HashSet<>();

        if (repository.exists(target.getIdGrupoCnae())) {
            String codigoCadastrado = SourceBundle.getMessage(
                    MessageUtil.ARR, "parametros.tipoGrupoCnaeDuplicatedValidator.message");
            customViolations.add(new CustomViolation(codigoCadastrado));
        }

        return customViolations;
    }
}
