package br.gov.to.sefaz.seg.business.gestao.service.validator;

import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.seg.persistence.entity.UnidadeOrganizacional;
import br.gov.to.sefaz.seg.persistence.repository.UnidadeOrganizacionalRepository;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe de validação que não permite deleção de Unidades Pai que tem filhos em utilização.
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 10/06/2016 17:03:00
 */
@Component
public class UnidadeOrganizacionalUnidadePostoTrabalhoAssignedValidator implements ServiceValidator<Long> {

    private final UnidadeOrganizacionalRepository unidadeOrganizacionalRepository;

    @Autowired
    public UnidadeOrganizacionalUnidadePostoTrabalhoAssignedValidator(UnidadeOrganizacionalRepository
            unidadeOrganizacionalRepository) {
        this.unidadeOrganizacionalRepository = unidadeOrganizacionalRepository;
    }

    @Override
    public boolean support(Class<?> clazz, String context) {
        return clazz.equals(UnidadeOrganizacional.class) && ValidationContext.DELETE.equals(context);
    }

    @Override
    public Set<CustomViolation> validate(Long target) {
        HashSet<CustomViolation> customViolations = new HashSet<>();

        if (unidadeOrganizacionalRepository.existsLockReferencePostoTrabalho(target)) {
            String codigoCadastrado = SourceBundle.getMessage(MessageUtil.SEG,
                    "seg.gestao.unidadesOrgazinacionais.form.falha.postoTrabalho");
            customViolations.add(new CustomViolation(codigoCadastrado));
        }
        return customViolations;
    }
}
