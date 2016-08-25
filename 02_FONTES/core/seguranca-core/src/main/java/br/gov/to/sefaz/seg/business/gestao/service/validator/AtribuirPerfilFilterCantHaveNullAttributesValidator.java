package br.gov.to.sefaz.seg.business.gestao.service.validator;

import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.seg.business.gestao.service.filter.AtribuirPerfilFilter;
import br.gov.to.sefaz.seg.business.gestao.service.impl.UsuarioSistemaServiceImpl;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Validação que verifica se o {@link AtribuirPerfilFilter} está com atributos vazios ou nulos.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 09/08/2016 11:12:00
 */
@Component
public class AtribuirPerfilFilterCantHaveNullAttributesValidator implements
        ServiceValidator<AtribuirPerfilFilter> {

    @Override
    public boolean support(Class<?> clazz, String context) {
        return clazz.equals(AtribuirPerfilFilter.class)
                && UsuarioSistemaServiceImpl.ATRIBUIR_PERFIL_FILTER_CONTEXT.equals(context);
    }

    @Override
    public Set<CustomViolation> validate(AtribuirPerfilFilter atribuirPerfilFilter) {
        HashSet<CustomViolation> customViolations = new HashSet<>();

        if (!Objects.isNull(atribuirPerfilFilter)
             && Objects.isNull(atribuirPerfilFilter.getCodigoPostoTrabalho())
             && Objects.isNull(atribuirPerfilFilter.getCodigoUnidadeOrganizacional())
             && StringUtils.isEmpty(atribuirPerfilFilter.getCpfUsuario())
             && StringUtils.isEmpty(atribuirPerfilFilter.getNomeCompletoUsuario())
             && Objects.isNull(atribuirPerfilFilter.getCodigoPerfil())
                && Objects.isNull(atribuirPerfilFilter.getCodigoTipoUsuario())) {

            String message = SourceBundle.getMessage(MessageUtil.SEG,
                    "seg.gestao.atribuirPerfil.form.erro.filtrosNulos");
            customViolations.add(new CustomViolation(message));
        }

        return customViolations;
    }

}