package br.gov.to.sefaz.seg.business.gestao.service.validator;

import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.seg.business.gestao.service.impl.UsuarioSistemaServiceImpl;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;
import br.gov.to.sefaz.seg.persistence.enums.SituacaoUsuarioEnum;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Validação que verifica se o {@link UsuarioSistema} está ativo. {@link UsuarioSistema#getSituacaoUsuario()} ==
 * {@link SituacaoUsuarioEnum#ATIVO}
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 07/06/2016 10:15:40
 */
@Component
public class UsuarioSistemaCpfInativoValidator implements ServiceValidator<UsuarioSistema> {

    @Override
    public boolean support(Class<?> clazz, String context) {
        return clazz.equals(UsuarioSistema.class) && (UsuarioSistemaServiceImpl.LOGIN_CONTEXT.equals(context));
    }

    @Override
    public Set<CustomViolation> validate(UsuarioSistema usuarioSistema) {
        HashSet<CustomViolation> customViolations = new HashSet<>();

        if (!Objects.isNull(usuarioSistema)
                && SituacaoUsuarioEnum.INATIVO.equals(usuarioSistema.getSituacaoUsuario())) {
            String message = SourceBundle.getMessage(MessageUtil.SEG,
                    "login.usuario.inativo");
            customViolations.add(new CustomViolation(message));
        }

        return customViolations;
    }

}
