package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.parametros.business.service.impl.PedidoAreasServidoresServiceImpl;
import br.gov.to.sefaz.arr.persistence.entity.PedidoAreasServidores;
import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Valida se um {@link PedidoAreasServidores}, baseado no CPF, aparece mais de uma na lista. Um CPF de servidor pode ser
 * cadastrado apenas uma vez em cada tipo de pedido por area.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 26/05/2016 17:09:00
 */
@Component
public class PedidoAreasServidoresDuplicatedValidator implements ServiceValidator<PedidoAreasServidores> {

    @Override
    public boolean support(Class<?> clazz, String context) {
        return clazz.equals(PedidoAreasServidores.class)
                && (PedidoAreasServidoresServiceImpl.DUPLICATED_SERVIDOR_CONTEXT.equals(context)
                        || ValidationContext.SAVE.equals(context)
                        || ValidationContext.UPDATE.equals(context));
    }

    @Override
    public Set<CustomViolation> validate(PedidoAreasServidores target) {
        return new HashSet<>();
    }

    @Override
    public Set<CustomViolation> validateAll(Collection<PedidoAreasServidores> target) {
        HashSet<CustomViolation> customViolations = new HashSet<>();

        Set<Long> cpfs = target.stream()
                .map(PedidoAreasServidores::getIdServidor)
                .collect(Collectors.toSet());

        if (cpfs.size() < target.size()) {
            String codigoCadastrado = SourceBundle.getMessage(
                    MessageUtil.ARR, "parametros.pedidoAreasServidoresDuplicatedValidator.message");
            customViolations.add(new CustomViolation(codigoCadastrado));
        }

        return customViolations;
    }
}
