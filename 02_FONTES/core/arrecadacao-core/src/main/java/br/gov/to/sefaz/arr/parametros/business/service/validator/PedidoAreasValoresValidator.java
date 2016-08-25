package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.persistence.entity.PedidoAreas;
import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Valida que o valor final é maior que o inicial.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 26/05/2016 23:02:00
 */
@Component
public class PedidoAreasValoresValidator implements ServiceValidator<PedidoAreas> {

    @Override
    public boolean support(Class<?> clazz, String context) {
        return clazz.equals(PedidoAreas.class)
                && (ValidationContext.SAVE.equals(context) || ValidationContext.UPDATE.equals(context));
    }

    @Override
    public Set<CustomViolation> validate(PedidoAreas target) {
        Set<CustomViolation> customViolations = new HashSet<>();

        if (!Objects.isNull(target.getValorInicial())
                && target.getValorInicial().compareTo(target.getValorFinal()) >= 0) {
            String codigoCadastrado = SourceBundle.getMessage(
                    MessageUtil.ARR, "parametros.pedidoAreasValoresValidator.message");
            customViolations.add(new CustomViolation(codigoCadastrado));
        }

        return customViolations;
    }
}
