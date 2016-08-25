package br.gov.to.sefaz.seg.business.gestao.service.validator;

import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;
import br.gov.to.sefaz.seg.persistence.repository.UsuarioSistemaRepository;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Classe de validação que não permite salvar dois CPFs de {@link br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema}
 * iguais.
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 27/06/2016 11:06:00
 */
@Component
public class UsuarioSistemaCpfExistsValidator implements ServiceValidator<UsuarioSistema> {

    private final UsuarioSistemaRepository usuarioSistemaRepository;

    @Autowired
    public UsuarioSistemaCpfExistsValidator(UsuarioSistemaRepository
            usuarioSistemaRepository) {
        this.usuarioSistemaRepository = usuarioSistemaRepository;
    }

    @Override
    public boolean support(Class<?> clazz, String context) {
        return clazz.equals(UsuarioSistema.class) && ValidationContext.SAVE.equals(context);
    }

    @Override
    public Set<CustomViolation> validate(UsuarioSistema target) {
        HashSet<CustomViolation> customViolations = new HashSet<>();

        if (!Objects.isNull(usuarioSistemaRepository.findOne(target.getCpfUsuario()))) {
            String codigoCadastrado = SourceBundle.getMessage(MessageUtil.SEG,
                    "seg.usuarioSistema.save.existe");
            customViolations.add(new CustomViolation(codigoCadastrado));
        }
        return customViolations;
    }
}