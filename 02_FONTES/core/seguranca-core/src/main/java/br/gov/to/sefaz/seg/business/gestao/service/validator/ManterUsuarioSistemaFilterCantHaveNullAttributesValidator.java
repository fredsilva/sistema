package br.gov.to.sefaz.seg.business.gestao.service.validator;

import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.seg.business.gestao.service.filter.ManterUsuarioSistemaFilter;
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
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 07/06/2016 10:15:40
 */
@Component
public class ManterUsuarioSistemaFilterCantHaveNullAttributesValidator implements
        ServiceValidator<ManterUsuarioSistemaFilter> {

    @Override
    public boolean support(Class<?> clazz, String context) {
        return clazz.equals(ManterUsuarioSistemaFilter.class)
                && UsuarioSistemaServiceImpl.MANTER_USUARIO_SISTEMA_FILTER_CONTEXT.equals(context);
    }

    @Override
    public Set<CustomViolation> validate(ManterUsuarioSistemaFilter manterUsuarioSistemaFilter) {
        HashSet<CustomViolation> customViolations = new HashSet<>();

        if (!Objects.isNull(manterUsuarioSistemaFilter)
                && StringUtils.isEmpty(manterUsuarioSistemaFilter.getCpfUsuario())
                && StringUtils.isEmpty(manterUsuarioSistemaFilter.getNomeCompletoUsuario())
                && Objects.isNull(manterUsuarioSistemaFilter.getDataCriacao())
                && Objects.isNull(manterUsuarioSistemaFilter.getSituacaoUsuario())
                && Objects.isNull(manterUsuarioSistemaFilter.getCodigoUnidadeOrganizacional())
                && Objects.isNull(manterUsuarioSistemaFilter.getCodigoPostoTrabalho())
                && Objects.isNull(manterUsuarioSistemaFilter.getCodigoTipoUsuario()) ) {

            String message = SourceBundle.getMessage(MessageUtil.SEG,
                    "seg.gestao.manterUsuario.filter.erro.filtrosNulos");
            customViolations.add(new CustomViolation(message));
        }

        return customViolations;
    }

}