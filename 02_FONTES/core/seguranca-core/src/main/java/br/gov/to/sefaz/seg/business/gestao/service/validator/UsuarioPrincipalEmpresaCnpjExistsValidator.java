package br.gov.to.sefaz.seg.business.gestao.service.validator;

import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
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
 * Classe de validação que verifica se existe o CNPJ informado em tela.
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 31/08/2016 15:55:00
 */
@Component
public class UsuarioPrincipalEmpresaCnpjExistsValidator implements ServiceValidator<UsuarioPrincipalEmpresa> {

    private final UsuarioPrincipalEmpresaRepository usuarioPrincipalEmpresaRepository;

    @Autowired
    public UsuarioPrincipalEmpresaCnpjExistsValidator(UsuarioPrincipalEmpresaRepository
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

        if (StringUtils.isNotEmpty(target.getCnpjEmpresa())
                && !usuarioPrincipalEmpresaRepository.cnpjEmpresaExists(target.getCnpjEmpresa().substring(0, 8))) {
            String violation = SourceBundle.getMessage(MessageUtil.SEG,
                    "seg.atuarUsuarioPrincipal.save.cnpj.doesNotExists");
            customViolations.add(new CustomViolation(violation));
        }
        return customViolations;
    }
}