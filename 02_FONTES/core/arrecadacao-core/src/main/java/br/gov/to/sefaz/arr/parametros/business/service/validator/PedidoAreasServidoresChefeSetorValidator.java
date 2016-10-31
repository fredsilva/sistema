package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.parametros.business.service.impl.PedidoAreasServidoresServiceImpl;
import br.gov.to.sefaz.arr.persistence.entity.PedidoAreasServidores;
import br.gov.to.sefaz.arr.persistence.repository.PedidoAreasServidoresRepository;
import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Valida se existe mais de um chefe de setor em uma lista de {@link PedidoAreasServidores}. Um tipo de pedido por
 * area pode possuir apenas um chefe de setor.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 26/05/2016 17:09:00
 */
@Component
public class PedidoAreasServidoresChefeSetorValidator implements ServiceValidator<PedidoAreasServidores> {

    private final PedidoAreasServidoresRepository repository;

    @Autowired
    public PedidoAreasServidoresChefeSetorValidator(PedidoAreasServidoresRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean support(Class<?> clazz, String context) {
        return clazz.equals(PedidoAreasServidores.class)
                && (PedidoAreasServidoresServiceImpl.SERVIDOR_CHEFE_CONTEXT.equals(context)
                    || ValidationContext.SAVE.equals(context)
                    || ValidationContext.UPDATE.equals(context));
    }

    @Override
    public Set<CustomViolation> validate(PedidoAreasServidores target) {
        HashSet<CustomViolation> customViolations = new HashSet<>();

        if (!Objects.isNull(target.getIdPedidoArea())
                && target.getSupervisor()
                && repository.existsChefeSetor(target.getIdPedidoArea(), Boolean.TRUE)) {
            String codigoCadastrado = SourceBundle.getMessage(
                    MessageUtil.ARR, "parametros.pedidoAreasServidoresChefeSetorValidator.message");
            customViolations.add(new CustomViolation(codigoCadastrado));
        }

        return customViolations;
    }

    @Override
    public Set<CustomViolation> validateAll(Collection<PedidoAreasServidores> target) {
        HashSet<CustomViolation> customViolations = new HashSet<>();

        List<PedidoAreasServidores> supervisores = target.stream()
                .filter(PedidoAreasServidores::getSupervisor)
                .collect(Collectors.toList());

        boolean lessOneSupervisor = supervisores.size() > 1;

        if (lessOneSupervisor) {
            String codigoCadastrado = SourceBundle.getMessage(
                    MessageUtil.ARR, "parametros.pedidoAreasServidoresChefeSetorValidator.message");
            customViolations.add(new CustomViolation(codigoCadastrado));
        }

        target.stream()
                .filter(PedidoAreasServidores::getSupervisor)
                .findFirst()
                .ifPresent(s -> customViolations.addAll(validate(s)));

        return customViolations;
    }
}
