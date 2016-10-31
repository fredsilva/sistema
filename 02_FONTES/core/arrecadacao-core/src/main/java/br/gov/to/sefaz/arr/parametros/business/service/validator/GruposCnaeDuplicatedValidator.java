package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.parametros.business.service.impl.GruposCnaeServiceImpl;
import br.gov.to.sefaz.arr.persistence.entity.GruposCnae;
import br.gov.to.sefaz.arr.persistence.entity.TipoGruposCnaes;
import br.gov.to.sefaz.arr.persistence.repository.GruposCnaeRepository;
import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * {@link ServiceValidator} que valida se o {@link TipoGruposCnaes#getIdGrupoCnae()} já existe no banco. Não pode
 * existir mais de um tipo de grupo cnae com o mesmo ID.
 * </p>
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 18/05/2016 15:28:00
 */
@Component
public class GruposCnaeDuplicatedValidator implements ServiceValidator<GruposCnae> {

    private final GruposCnaeRepository repository;

    @Autowired
    public GruposCnaeDuplicatedValidator(
            GruposCnaeRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean support(Class<?> clazz, String context) {
        return GruposCnae.class.equals(clazz)
                && (ValidationContext.SAVE.equals(context)
                        || ValidationContext.UPDATE.equals(context)
                        || GruposCnaeServiceImpl.DUPLICATED.equals(context));
    }

    @Override
    public Set<CustomViolation> validate(GruposCnae target) {
        HashSet<CustomViolation> customViolations = new HashSet<>();

        if (repository.exists(target.getId())) {
            String codigoCadastrado = SourceBundle.getMessage(
                    MessageUtil.ARR, "parametros.gruposCnaeDuplicatedValidator.cadastrado.message");
            customViolations.add(new CustomViolation(codigoCadastrado));
        } else if (repository.existsCnaeFiscal(target.getCnaeFiscal())) {
            String codigoCadastrado = SourceBundle.getMessage(
                    MessageUtil.ARR, "parametros.gruposCnaeDuplicatedValidator.associado.message");
            customViolations.add(new CustomViolation(codigoCadastrado));
        }

        return customViolations;
    }

    @Override
    public Set<CustomViolation> validateAll(Collection<GruposCnae> targets) {
        HashSet<CustomViolation> customViolations = new HashSet<>();
        Set<String> idsSet = targets.stream()
                .map(GruposCnae::getCnaeFiscal)
                .collect(Collectors.toSet());

        if (idsSet.size() < targets.size()) {
            String codigoCadastrado = SourceBundle.getMessage(
                    MessageUtil.ARR, "parametros.gruposCnaeDuplicatedValidator.cadastrado.message");
            customViolations.add(new CustomViolation(codigoCadastrado));
        }

        for (GruposCnae target : targets) {
            customViolations.addAll(validate(target));
        }

        return customViolations;
    }
}
