package br.gov.to.sefaz.seg.business.gestao.service.validator;

import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.seg.business.gestao.service.impl.UsuarioSistemaServiceImpl;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Validação que verifica se a justificativa de criação está vazia ou nula.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 14/07/2016 17:05:00
 */
@Component
public class UsuarioSistemaContadorCrcEmptyOrNullValidator implements ServiceValidator<UsuarioSistema> {

    @Override
    public boolean support(Class<?> clazz, String context) {
        return clazz.equals(UsuarioSistema.class)
                && (UsuarioSistemaServiceImpl.SOLICITACAO_AUTORIZACAO_SENHA_CONTEXT.equals(context)
                || ValidationContext.SAVE.equals(context));
    }

    @Override
    public Set<CustomViolation> validate(UsuarioSistema usuarioSistema) {
        HashSet<CustomViolation> customViolations = new HashSet<>();

        if (usuarioSistema.getCodigoTipoUsuario() == 1 && StringUtils.isEmpty(usuarioSistema.getCrc())) {
            String message = SourceBundle.getMessage(MessageUtil.SEG,
                    "seg.gestao.manterUsuarioSistema.form.crc.empty");
            customViolations.add(new CustomViolation(message));
        }

        return customViolations;
    }

}
