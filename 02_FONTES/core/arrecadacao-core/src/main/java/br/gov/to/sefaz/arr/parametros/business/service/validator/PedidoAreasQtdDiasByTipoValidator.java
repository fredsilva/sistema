package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.persistence.entity.PedidoAreas;
import br.gov.to.sefaz.arr.persistence.entity.PedidoTipos;
import br.gov.to.sefaz.arr.persistence.repository.PedidoAreasRepository;
import br.gov.to.sefaz.arr.persistence.repository.PedidoTiposRepository;
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
 * Valida se a qtd de dias para analise somada aos tipos de pedidos por area do tipo de pedido já existentes não passa
 * da quantidade de dias maximo para analise do tipo de pedido.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 26/05/2016 23:36:00
 */
@Component
public class PedidoAreasQtdDiasByTipoValidator implements ServiceValidator<PedidoAreas> {

    private final PedidoAreasRepository repository;
    private final PedidoTiposRepository tiposRepository;

    @Autowired
    public PedidoAreasQtdDiasByTipoValidator(PedidoAreasRepository repository, PedidoTiposRepository tiposRepository) {
        this.repository = repository;
        this.tiposRepository = tiposRepository;
    }

    @Override
    public boolean support(Class<?> clazz, String context) {
        return clazz.equals(PedidoAreas.class)
                && (ValidationContext.SAVE.equals(context)
                || ValidationContext.UPDATE.equals(context));
    }

    @Override
    public Set<CustomViolation> validate(PedidoAreas target) {
        Set<CustomViolation> customViolations = new HashSet<>();

        PedidoTipos tipo = tiposRepository.findOne(target.getIdTipoPedido());

        Long totalDias;
        if (Objects.isNull(target.getIdPedidoArea())) {
            totalDias = repository.getTotalQtdDiasAnaliseByTipo(target.getIdTipoPedido());
        } else {
            totalDias = repository
                    .getTotalQtdDiasAnaliseByTipoAndNotId(target.getIdTipoPedido(), target.getIdPedidoArea());
        }

        totalDias += target.getQuantidadeDiasAnalise();

        if (tipo.getQuantidadeDiasAnalise() < totalDias) {
            String codigoCadastrado = SourceBundle.getMessage(
                    MessageUtil.ARR, "parametros.pedidoAreasQtdDiasByTipoValidator.message");
            customViolations.add(new CustomViolation(codigoCadastrado));
        }

        return customViolations;
    }
}