package br.gov.to.sefaz.seg.business.gestao.service.validator;

import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.seg.business.gestao.service.filter.PostoTrabalhoFilter;
import br.gov.to.sefaz.seg.business.gestao.service.impl.PostoTrabalhoServiceImpl;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioPerfil;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Validação que verifica se o {@link UsuarioPerfil} é Contribuinte e não está
 * sendo inativado.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 07/06/2016 10:15:40
 */
@Component
public class PostoTrabalhoFilterCantHaveNullAttributesValidator implements
        ServiceValidator<PostoTrabalhoFilter> {

    @Override
    public boolean support(Class<?> clazz, String context) {
        return clazz.equals(PostoTrabalhoFilter.class)
                && PostoTrabalhoServiceImpl.POSTO_TRABALHO_FILTER_CONTEXT.equals(context);
    }

    @Override
    public Set<CustomViolation> validate(PostoTrabalhoFilter postoTrabalhoFilter) {
        HashSet<CustomViolation> customViolations = new HashSet<>();

        if (!Objects.isNull(postoTrabalhoFilter)
                && StringUtils.isEmpty(postoTrabalhoFilter.getNomePostoTrabalho())
                && Objects.isNull(postoTrabalhoFilter.getIdentificacaoUnidOrganizac())) {

            String message = SourceBundle.getMessage(MessageUtil.SEG,
                    "seg.gestao.PostoTrabalho.filter.erro.filtrosNulos");
            customViolations.add(new CustomViolation(message));
        }

        return customViolations;
    }

}