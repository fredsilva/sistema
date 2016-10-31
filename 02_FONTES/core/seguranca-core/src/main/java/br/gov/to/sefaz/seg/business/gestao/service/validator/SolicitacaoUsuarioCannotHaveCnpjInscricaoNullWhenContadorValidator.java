package br.gov.to.sefaz.seg.business.gestao.service.validator;

import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.seg.business.gestao.service.filter.AtivarInativarPerfilFilter;
import br.gov.to.sefaz.seg.persistence.entity.SolicitacaoUsuario;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioPerfil;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Validação que verifica se o {@link UsuarioPerfil} é Contribuinte e não está
 * sendo inativado.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 07/06/2016 10:15:40
 */
@Component
public class SolicitacaoUsuarioCannotHaveCnpjInscricaoNullWhenContadorValidator implements
        ServiceValidator<SolicitacaoUsuario> {

    @Override
    public boolean support(Class<?> clazz, String context) {
        return clazz.equals(AtivarInativarPerfilFilter.class)
                && ValidationContext.SAVE.equals(context);
    }

    @Override
    public Set<CustomViolation> validate(SolicitacaoUsuario solicitacaoUsuario) {
        HashSet<CustomViolation> customViolations = new HashSet<>();

        if (StringUtils.isEmpty(solicitacaoUsuario.getCnpjNegocio())) {
            String message = SourceBundle.getMessage(MessageUtil.SEG,
                    "seg.gestao.solicitacaoUsuario.form.erro.cnpjVazio");
            customViolations.add(new CustomViolation(message));
        }

        if (StringUtils.isEmpty(solicitacaoUsuario.getInscricaoEstadualNegocio())) {
            String message = SourceBundle.getMessage(MessageUtil.SEG,
                    "seg.gestao.solicitacaoUsuario.form.erro.inscricaoEstadual");
            customViolations.add(new CustomViolation(message));
        }
        return customViolations;

    }
}