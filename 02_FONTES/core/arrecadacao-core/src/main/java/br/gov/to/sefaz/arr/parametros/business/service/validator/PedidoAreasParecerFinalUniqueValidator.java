package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.persistence.entity.PedidoAreas;
import br.gov.to.sefaz.arr.persistence.repository.PedidoAreasRepository;
import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Valida se já existe algum {@link PedidoAreas} com parecer final. Pode existir apenas um {@link PedidoAreas} com
 * parecer final por tipo de pedido.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 26/05/2016 20:00:00
 */
@Component
public class PedidoAreasParecerFinalUniqueValidator implements ServiceValidator<PedidoAreas> {

    private final PedidoAreasRepository repository;

    @Autowired
    public PedidoAreasParecerFinalUniqueValidator(PedidoAreasRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean support(Class<?> clazz, String context) {
        return clazz == PedidoAreas.class
                && (ValidationContext.SAVE.equals(context)
                    || ValidationContext.UPDATE.equals(context));
    }

    @Override
    public Set<CustomViolation> validate(PedidoAreas target) {
        HashSet<CustomViolation> customViolations = new HashSet<>();
        Integer idPedidoAreas = repository.findIdByParecerFinalAndTipoPedido(true, target.getIdTipoPedido());

        if (target.getParecerFinal()
                && !Objects.isNull(idPedidoAreas)
                && !idPedidoAreas.equals(target.getIdPedidoArea())) {
            String codigoCadastrado = SourceBundle.getMessage(
                    MessageUtil.ARR, "parametros.pedidoAreasParecerFinalUniqueValidator.message");
            customViolations.add(new CustomViolation(codigoCadastrado));
        }

        return customViolations;
    }
}