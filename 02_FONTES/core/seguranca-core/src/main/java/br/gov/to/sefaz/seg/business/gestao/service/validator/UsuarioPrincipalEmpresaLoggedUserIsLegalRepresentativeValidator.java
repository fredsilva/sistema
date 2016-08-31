package br.gov.to.sefaz.seg.business.gestao.service.validator;

import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.seg.business.authentication.handler.AuthenticatedUserHandler;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioPrincipalEmpresa;
import br.gov.to.sefaz.seg.persistence.repository.UsuarioPrincipalEmpresaRepository;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe de validação que verifica se o usuário logado é o representante legal da empresa.
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 27/06/2016 11:06:00
 */
@Component
public class UsuarioPrincipalEmpresaLoggedUserIsLegalRepresentativeValidator
        implements ServiceValidator<UsuarioPrincipalEmpresa> {

    private final UsuarioPrincipalEmpresaRepository usuarioPrincipalEmpresaRepository;

    @Autowired
    public UsuarioPrincipalEmpresaLoggedUserIsLegalRepresentativeValidator(UsuarioPrincipalEmpresaRepository
            usuarioPrincipalEmpresaRepository) {
        this.usuarioPrincipalEmpresaRepository = usuarioPrincipalEmpresaRepository;
    }

    @Override
    public boolean support(Class<?> clazz, String context) {
        return clazz.equals(UsuarioPrincipalEmpresa.class) && ValidationContext.SAVE.equals(context);
    }

    @Override
    public Set<CustomViolation> validate(UsuarioPrincipalEmpresa target) {
        HashSet<CustomViolation> customViolations = new HashSet<>();

        if (StringUtils.isNotEmpty(target.getCnpjEmpresa()) && !usuarioPrincipalEmpresaRepository
                .isLegalRepresentative(target.getCnpjEmpresa().substring(0, 8), AuthenticatedUserHandler.getCpf())) {
            String violation = SourceBundle.getMessage(MessageUtil.SEG,
                    "seg.atuarUsuarioPrincipal.save.cpf.loggedUserNotLegalRepresentative");
            customViolations.add(new CustomViolation(violation));
        }

        return customViolations;
    }
}