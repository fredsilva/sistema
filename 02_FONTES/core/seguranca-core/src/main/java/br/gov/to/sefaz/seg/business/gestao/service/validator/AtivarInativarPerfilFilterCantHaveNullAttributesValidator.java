package br.gov.to.sefaz.seg.business.gestao.service.validator;

import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.seg.business.gestao.service.filter.AtivarInativarPerfilFilter;
import br.gov.to.sefaz.seg.business.gestao.service.impl.UsuarioSistemaServiceImpl;
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
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 09/08/2016 11:14:00
 */
@Component
public class AtivarInativarPerfilFilterCantHaveNullAttributesValidator implements
        ServiceValidator<AtivarInativarPerfilFilter> {

    @Override
    public boolean support(Class<?> clazz, String context) {
        return clazz.equals(AtivarInativarPerfilFilter.class)
                && UsuarioSistemaServiceImpl.ATIVAR_INATIVAR_PERFIL_FILTER_CONTEXT.equals(context);
    }

    @Override
    public Set<CustomViolation> validate(AtivarInativarPerfilFilter ativarInativarPerfilFilter) {
        HashSet<CustomViolation> customViolations = new HashSet<>();

        if (!Objects.isNull(ativarInativarPerfilFilter)
             && Objects.isNull(ativarInativarPerfilFilter.getCodigoPostoTrabalho())
             && Objects.isNull(ativarInativarPerfilFilter.getCodigoUnidadeOrganizacional())
             && StringUtils.isEmpty(ativarInativarPerfilFilter.getCpfUsuario())
             && StringUtils.isEmpty(ativarInativarPerfilFilter.getNomeCompletoUsuario()) ) {

            String message = SourceBundle.getMessage(MessageUtil.SEG,
                    "seg.gestao.ativarInativarPerfil.form.erro.filtrosNulos");
            customViolations.add(new CustomViolation(message));
        }

        return customViolations;
    }

}