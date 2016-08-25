package br.gov.to.sefaz.seg.business.gestao.service.validator;

import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioPerfil;
import br.gov.to.sefaz.seg.persistence.enums.SituacaoUsuarioEnum;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Validação que verifica se o {@link br.gov.to.sefaz.seg.persistence.entity.UsuarioPerfil} é Contribuinte e não está
 * sendo inativado.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 07/06/2016 10:15:40
 */
@Component
public class UsuarioPerfilContribuinteCantBeInativoValidator implements ServiceValidator<UsuarioPerfil> {

    private static final Long CODIGO_CONTRIBUINTE = 1L;

    @Override
    public boolean support(Class<?> clazz, String context) {
        return clazz.equals(UsuarioPerfil.class) && (ValidationContext.UPDATE.equals(context)
                || ValidationContext.SAVE.equals(context));
    }

    @Override
    public Set<CustomViolation> validate(UsuarioPerfil usuarioPerfil) {
        HashSet<CustomViolation> customViolations = new HashSet<>();

        if (!Objects.isNull(usuarioPerfil)
                && (CODIGO_CONTRIBUINTE.equals(usuarioPerfil.getIdentificacaoPerfil()) && SituacaoUsuarioEnum.INATIVO
                .equals(usuarioPerfil
                        .getSituacaoPerfil()))) {
            String message = SourceBundle.getMessage(MessageUtil.SEG,
                    "seg.gestao.ativarInativarPerfil.form.erro.perfilContribuinte");
            customViolations.add(new CustomViolation(message));
        }

        return customViolations;
    }

}