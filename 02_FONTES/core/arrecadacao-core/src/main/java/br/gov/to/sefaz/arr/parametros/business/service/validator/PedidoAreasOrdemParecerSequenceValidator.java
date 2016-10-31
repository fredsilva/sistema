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
 * Valida a ordem do parecer do {@link PedidoAreas}. A ordem poderá ser igual a última informada ou ser incrementada
 * de 1, com base na última ordem gravada!
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 26/05/2016 17:09:00
 */
@Component
public class PedidoAreasOrdemParecerSequenceValidator implements ServiceValidator<PedidoAreas> {

    private final PedidoAreasRepository repository;

    @Autowired
    public PedidoAreasOrdemParecerSequenceValidator(PedidoAreasRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean support(Class<?> clazz, String context) {
        return clazz.equals(PedidoAreas.class)
                && (ValidationContext.SAVE.equals(context)
                    || ValidationContext.UPDATE.equals(context));
    }

    @Override
    public Set<CustomViolation> validate(PedidoAreas target) {
        HashSet<CustomViolation> customViolations = new HashSet<>();

        Integer lastOrdemParecer = repository.getLastOrdemParecerFromTipo(target.getIdTipoPedido());

        boolean isFirstAndNotOne = Objects.isNull(lastOrdemParecer) && target.getOrdemParecer() != 1;
        boolean equalsOrOneMore = !Objects.isNull(lastOrdemParecer)
                && (target.getOrdemParecer() < lastOrdemParecer
                || target.getOrdemParecer() > (lastOrdemParecer + 1));

        if (isFirstAndNotOne || equalsOrOneMore) {
            String codigoCadastrado = SourceBundle.getMessage(
                    MessageUtil.ARR, "parametros.pedidoAreasOrdemParecerSequenceValidator.message");
            customViolations.add(new CustomViolation(codigoCadastrado));
        }

        return customViolations;
    }
}
