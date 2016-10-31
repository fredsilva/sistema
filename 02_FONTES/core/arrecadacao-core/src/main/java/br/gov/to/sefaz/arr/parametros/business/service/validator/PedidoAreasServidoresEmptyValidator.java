package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.persistence.entity.PedidoAreas;
import br.gov.to.sefaz.arr.persistence.entity.PedidoAreasServidores;
import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Valida que a lista de {@link PedidoAreasServidores} dentro de um {@link PedidoAreas}.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 26/05/2016 17:09:00
 */
@Component
public class PedidoAreasServidoresEmptyValidator implements ServiceValidator<PedidoAreas> {

    @Override
    public boolean support(Class<?> clazz, String context) {
        return clazz.equals(PedidoAreas.class)
                && (ValidationContext.SAVE.equals(context)
                    || ValidationContext.UPDATE.equals(context));
    }

    @Override
    public Set<CustomViolation> validate(PedidoAreas target) {
        Set<CustomViolation> customViolations = new HashSet<>();

        if (target.getPedidoAreasServidores().stream().noneMatch(s -> s.getSituacao().equals(SituacaoEnum.ATIVO))) {
            String codigoCadastrado = SourceBundle.getMessage(
                    MessageUtil.ARR, "parametros.pedidoAreasServidoresEmptyValidator.message");
            customViolations.add(new CustomViolation(codigoCadastrado));
        }

        return customViolations;
    }
}
