package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.persistence.entity.PedidoTipos;
import br.gov.to.sefaz.arr.persistence.entity.TipoRejeicaoArquivos;
import br.gov.to.sefaz.arr.persistence.repository.PedidoTiposRepository;
import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Validador customizado para adição de {@link TipoRejeicaoArquivos}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 29/04/2016 10:52:19
 */
@Component
@SuppressWarnings("PMD")
public class PedidoTiposListaAcoesVaziasValidator implements ServiceValidator<PedidoTipos> {

    private final PedidoTiposRepository repository;

    @Autowired
    public PedidoTiposListaAcoesVaziasValidator(
            PedidoTiposRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean support(Class<?> clazz, String context) {
        return clazz.equals(PedidoTipos.class)
                && (context.equals(ValidationContext.SAVE) || context.equals(ValidationContext.UPDATE));
    }

    @Override
    public Set<CustomViolation> validate(PedidoTipos pedidoTipos) {
        HashSet<CustomViolation> customViolations = new HashSet<>();

        if (!validatePedidoCamposAcoes(pedidoTipos)) {
            String codigoCadastrado = SourceBundle.getMessage(MessageUtil.ARR,
                    "parametros.pedidoTipos.pedidoCamposAcoes.vazia");
            customViolations.add(new CustomViolation(codigoCadastrado));
        }

        if (!validatePedidoReceitas(pedidoTipos)) {
            String codigoCadastrado = SourceBundle.getMessage(MessageUtil.ARR,
                    "parametros.pedidoTipos.pedidoReceitas.vazia");
            customViolations.add(new CustomViolation(codigoCadastrado));
        }

        return customViolations;
    }

    private boolean validatePedidoCamposAcoes(PedidoTipos pedidoTipos) {
        if (CollectionUtils.isEmpty(pedidoTipos.getPedidoCamposAcoes())) {
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }

    private boolean validatePedidoReceitas(PedidoTipos pedidoTipos) {
        if (CollectionUtils.isEmpty(pedidoTipos.getPedidoReceitas())) {
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }

}
