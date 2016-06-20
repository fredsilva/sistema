package br.gov.to.sefaz.seg.business.gestao.service.validator;

import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.seg.persistence.entity.TipoUsuario;
import br.gov.to.sefaz.seg.persistence.repository.TipoUsuarioRepository;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe de validação que não permite deleção de Tipos de Usuario que tem Usuarios Sistema em utilização.
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 10/06/2016 17:03:00
 */
@Component
public class TipoUsuarioUsuarioSistemaAssignedValidator implements ServiceValidator<Integer> {

    private final TipoUsuarioRepository tipoUsuarioRepository;

    @Autowired
    public TipoUsuarioUsuarioSistemaAssignedValidator(TipoUsuarioRepository
            tipoUsuarioRepository) {
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    @Override
    public boolean support(Class<?> clazz, String context) {
        return clazz.equals(TipoUsuario.class) && ValidationContext.DELETE.equals(context);
    }

    @Override
    public Set<CustomViolation> validate(Integer target) {
        HashSet<CustomViolation> customViolations = new HashSet<>();

        if (tipoUsuarioRepository.existsLockReferenceUsuario(target)) {
            String codigoCadastrado = SourceBundle.getMessage(MessageUtil.SEG,
                    "seg.gestao.tipoUsuario.form.falha.usuarioSistema");
            customViolations.add(new CustomViolation(codigoCadastrado));
        }
        return customViolations;
    }
}