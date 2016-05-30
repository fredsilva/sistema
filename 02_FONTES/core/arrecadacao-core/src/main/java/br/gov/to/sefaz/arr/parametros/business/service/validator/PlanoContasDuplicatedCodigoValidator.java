package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.parametros.persistence.entity.PlanoContas;
import br.gov.to.sefaz.arr.parametros.persistence.repository.PlanoContasRepository;
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
 * <p>{@link br.gov.to.sefaz.business.service.validation.ServiceValidator} que valida se o
 * {@link br.gov.to.sefaz.arr.parametros.persistence.entity.PlanoContas#getCodigoPlanoContas()} já existe no banco.
 * Não pode existir mais de um plano de contas com o mesmo Codigo.</p>
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 11/05/2016 16:28:00
 */
@Component
public class PlanoContasDuplicatedCodigoValidator implements ServiceValidator<PlanoContas> {

    private final PlanoContasRepository repository;

    @Autowired
    public PlanoContasDuplicatedCodigoValidator(PlanoContasRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean support(Class<?> clazz, String context) {
        return PlanoContas.class.equals(clazz)
                && (ValidationContext.SAVE.equals(context)
                || ValidationContext.UPDATE.equals(context));
    }

    @Override
    public Set<CustomViolation> validate(PlanoContas target) {
        HashSet<CustomViolation> customViolations = new HashSet<>();
        Long idByCodigo = repository.findIdByCodigo(target.getCodigoPlanoContas());

        if (!Objects.isNull(idByCodigo) && !idByCodigo.equals(target.getIdPlanocontas())) {
            String codigoCadastrado = SourceBundle.getMessage(
                    MessageUtil.ARR, "parametros.planoContasDuplicatedCodigoValidator.message");
            customViolations.add(new CustomViolation(codigoCadastrado));
        }

        return customViolations;
    }
}
