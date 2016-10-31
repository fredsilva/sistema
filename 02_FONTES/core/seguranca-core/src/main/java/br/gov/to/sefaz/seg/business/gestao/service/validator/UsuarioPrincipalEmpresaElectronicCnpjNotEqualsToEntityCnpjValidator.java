package br.gov.to.sefaz.seg.business.gestao.service.validator;

import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioPrincipalEmpresa;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe de validação que verifica se o CNPJ informado é igual ao CNPJ do certificado digital.
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 31/08/2016 15:56:00
 */
@Component
public class UsuarioPrincipalEmpresaElectronicCnpjNotEqualsToEntityCnpjValidator
        implements ServiceValidator<UsuarioPrincipalEmpresa> {

    @Override
    public boolean support(Class<?> clazz, String context) {
        return clazz.equals(UsuarioPrincipalEmpresa.class) && ValidationContext.SAVE.equals(context);
    }

    @Override
    public Set<CustomViolation> validate(UsuarioPrincipalEmpresa target) {
        HashSet<CustomViolation> customViolations = new HashSet<>();

        if ((StringUtils.isNotEmpty(target.getCnpjEmpresa()) && StringUtils.isNotEmpty(target.getECnpj()))
                && !target.getCnpjEmpresa().equals(target.getECnpj())) {
            String violation = SourceBundle.getMessage(MessageUtil.SEG,
                    "seg.atuarUsuarioPrincipal.save.cnpj.notEqualToElectronicCnpj");
            customViolations.add(new CustomViolation(violation));
        }

        return customViolations;
    }
}