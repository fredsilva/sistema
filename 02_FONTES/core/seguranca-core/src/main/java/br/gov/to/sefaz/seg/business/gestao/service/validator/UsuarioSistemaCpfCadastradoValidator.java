package br.gov.to.sefaz.seg.business.gestao.service.validator;

import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.seg.business.gestao.service.impl.UsuarioSistemaServiceImpl;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Validação que verifica se o {@link UsuarioSistema#getCpfUsuario()} está cadastrado na base de dados.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 07/06/2016 10:15:23
 */
@Component
public class UsuarioSistemaCpfCadastradoValidator implements ServiceValidator<UsuarioSistema> {

    @Override
    public boolean support(Class<?> clazz, String context) {
        return clazz.equals(UsuarioSistema.class) && (UsuarioSistemaServiceImpl.LOGIN_CONTEXT.equals(context));
    }

    @Override
    public Set<CustomViolation> validate(UsuarioSistema usuarioSistema) {
        HashSet<CustomViolation> customViolations = new HashSet<>();

        if (Objects.isNull(usuarioSistema) || Objects.isNull(usuarioSistema.getCpfUsuario())) {
            String message = SourceBundle.getMessage(MessageUtil.SEG,
                    "login.cpf.inexixtente");
            customViolations.add(new CustomViolation(message));
        }

        return customViolations;
    }

}
