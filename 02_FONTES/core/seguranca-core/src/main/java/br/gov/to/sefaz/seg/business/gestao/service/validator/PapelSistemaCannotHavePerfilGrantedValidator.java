package br.gov.to.sefaz.seg.business.gestao.service.validator;

import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.seg.persistence.entity.PapelSistema;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioPerfil;
import br.gov.to.sefaz.seg.persistence.repository.PapelSistemaRepository;
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
public class PapelSistemaCannotHavePerfilGrantedValidator implements
        ServiceValidator<Long> {

    private final PapelSistemaRepository papelSistemaRepository;

    @Autowired
    public PapelSistemaCannotHavePerfilGrantedValidator(PapelSistemaRepository papelSistemaRepository) {
        this.papelSistemaRepository = papelSistemaRepository;
    }

    @Override
    public boolean support(Class<?> clazz, String context) {
        return clazz.equals(PapelSistema.class)
                && ValidationContext.DELETE.equals(context);
    }

    @Override
    public Set<CustomViolation> validate(Long id) {
        HashSet<CustomViolation> customViolations = new HashSet<>();

        if (papelSistemaRepository.existsPerfilByPapel(id)) {
            String message = SourceBundle.getMessage(MessageUtil.SEG,
                    "seg.gestao.papelSistema.form.erro.perfilOutorgado");
            customViolations.add(new CustomViolation(message));
        }

        return customViolations;

    }
}