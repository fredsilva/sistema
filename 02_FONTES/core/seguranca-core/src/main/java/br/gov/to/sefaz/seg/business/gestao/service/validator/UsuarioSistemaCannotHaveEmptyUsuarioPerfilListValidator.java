package br.gov.to.sefaz.seg.business.gestao.service.validator;

import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.seg.business.gestao.service.impl.UsuarioSistemaServiceImpl;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Validação que verifica se o {@link br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema#getUsuarioPerfil()} está
 * vazio.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 17/08/2016 11:07:00
 */
@Component
public class UsuarioSistemaCannotHaveEmptyUsuarioPerfilListValidator implements
        ServiceValidator<UsuarioSistema> {

    @Override
    public boolean support(Class<?> clazz, String context) {
        return clazz.equals(UsuarioSistema.class)
                && UsuarioSistemaServiceImpl.ATRIBUIR_PERFIL_USUARIO_CONTEXT.equals(context);
    }

    @Override
    public Set<CustomViolation> validate(UsuarioSistema usuarioSistema) {
        HashSet<CustomViolation> customViolations = new HashSet<>();

        if (usuarioSistema.getUsuarioPerfil().isEmpty()) {
            String message = SourceBundle.getMessage(MessageUtil.SEG,
                    "seg.gestao.usuarioSistema.form.erro.usuarioPerfil.vazio");
            customViolations.add(new CustomViolation(message));
        }

        return customViolations;

    }
}
