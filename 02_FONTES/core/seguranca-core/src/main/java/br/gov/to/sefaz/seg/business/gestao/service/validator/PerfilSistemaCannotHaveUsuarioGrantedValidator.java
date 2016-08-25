package br.gov.to.sefaz.seg.business.gestao.service.validator;

import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.seg.persistence.entity.PerfilSistema;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioPerfil;
import br.gov.to.sefaz.seg.persistence.repository.PerfilSistemaRepository;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Validação que verifica se o {@link UsuarioPerfil} é Contribuinte e não está
 * sendo inativado.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 29/07/2016 10:27:00
 */
@Component
public class PerfilSistemaCannotHaveUsuarioGrantedValidator implements
        ServiceValidator<Long> {

    private final PerfilSistemaRepository perfilSistemaRepository;

    @Autowired
    public PerfilSistemaCannotHaveUsuarioGrantedValidator(PerfilSistemaRepository perfilSistemaRepository) {
        this.perfilSistemaRepository = perfilSistemaRepository;
    }

    @Override
    public boolean support(Class<?> clazz, String context) {
        return clazz.equals(PerfilSistema.class)
                && ValidationContext.DELETE.equals(context);
    }

    @Override
    public Set<CustomViolation> validate(Long id) {
        HashSet<CustomViolation> customViolations = new HashSet<>();

        if (perfilSistemaRepository.existsUsuarioByPerfil(id)) {
            String message = SourceBundle.getMessage(MessageUtil.SEG,
                    "seg.gestao.perfilSistema.form.erro.usuarioOutorgado");
            customViolations.add(new CustomViolation(message));
        }

        return customViolations;

    }
}