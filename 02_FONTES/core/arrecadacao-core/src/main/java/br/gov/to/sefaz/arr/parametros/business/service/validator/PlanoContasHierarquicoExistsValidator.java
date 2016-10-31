package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.persistence.entity.PlanoContas;
import br.gov.to.sefaz.arr.persistence.repository.PlanoContasRepository;
import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>{@link br.gov.to.sefaz.business.service.validation.ServiceValidator} que valida se o
 * {@link br.gov.to.sefaz.arr.persistence.entity.PlanoContas#getContaHierarquica()} existe no banco.
 * Se o plano de contas hierarquico for preenchido ele deve referenciar um plano de contas previamente cadastrado.</p>
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 11/05/2016 17:24:00
 */
@Component
public class PlanoContasHierarquicoExistsValidator implements ServiceValidator<PlanoContas> {

    private final PlanoContasRepository repository;

    @Autowired
    public PlanoContasHierarquicoExistsValidator(PlanoContasRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean support(Class clazz, String context) {
        return PlanoContas.class.equals(clazz)
                && (ValidationContext.SAVE.equals(context)
                || ValidationContext.UPDATE.equals(context));
    }

    @Override
    public Set<CustomViolation> validate(PlanoContas target) {
        HashSet<CustomViolation> customViolations = new HashSet<>();

        if (StringUtils.isNotEmpty(target.getContaHierarquica())
                && !repository.existsByCodigo(target.getContaHierarquica())) {
            String codigoCadastrado = SourceBundle.getMessage(
                    MessageUtil.ARR, "parametros.planoContasHierarquicoExistsValidator.message");
            customViolations.add(new CustomViolation(codigoCadastrado));
        }

        return customViolations;
    }
}
