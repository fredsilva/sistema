package br.gov.to.sefaz.seg.business.gestao.service.validator;

import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.seg.persistence.entity.PostoTrabalho;
import br.gov.to.sefaz.seg.persistence.repository.PostoTrabalhoRepository;
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
public class PostoTrabalhoFuncionarioAssignedValidator implements ServiceValidator<Long> {

    private final PostoTrabalhoRepository postoTrabalhoRepository;

    @Autowired
    public PostoTrabalhoFuncionarioAssignedValidator(PostoTrabalhoRepository
            postoTrabalhoRepository) {
        this.postoTrabalhoRepository = postoTrabalhoRepository;
    }

    @Override
    public boolean support(Class<?> clazz, String context) {
        return clazz.equals(PostoTrabalho.class) && ValidationContext.DELETE.equals(context);
    }

    @Override
    public Set<CustomViolation> validate(Long target) {
        HashSet<CustomViolation> customViolations = new HashSet<>();

        if (postoTrabalhoRepository.existsLockReferenceFuncionario(target)) {
            String codigoCadastrado = SourceBundle.getMessage(MessageUtil.SEG,
                    "seg.gestao.PostoTrabalho.form.falha.funcionario");
            customViolations.add(new CustomViolation(codigoCadastrado));
        }
        return customViolations;
    }
}
