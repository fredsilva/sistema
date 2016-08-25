package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.persistence.entity.PlanoContas;
import br.gov.to.sefaz.arr.persistence.repository.TipoGruposCnaesRepository;
import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * {@link br.gov.to.sefaz.business.service.validation.ServiceValidator} que valida se o
 * {@link br.gov.to.sefaz.arr.persistence.entity.PlanoContas#getGruposCnaes()} já esta com a Ativo. Um plano
 * de contas não pode utilizar um grupo cnae Cancelado.
 * </p>
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 11/05/2016 16:28:00
 */
@Component
public class PlanoContasGruposCnaesSituacaoValidator implements ServiceValidator<PlanoContas> {

    private final TipoGruposCnaesRepository repository;

    @Autowired
    public PlanoContasGruposCnaesSituacaoValidator(
            TipoGruposCnaesRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean support(Class<?> clazz, String context) {
        return PlanoContas.class.equals(clazz)
                && (ValidationContext.SAVE.equals(context) || ValidationContext.UPDATE.equals(context));
    }

    @Override
    public Set<CustomViolation> validate(PlanoContas target) {
        HashSet<CustomViolation> customViolations = new HashSet<>();

        if (repository.selectSituacao(target.getIdGruposCnaes()) == SituacaoEnum.CANCELADO) {
            String codigoCadastrado = SourceBundle.getMessage(
                    MessageUtil.ARR, "parametros.planoContasGrupoCnaeSituacaoValidator.message");
            customViolations.add(new CustomViolation(codigoCadastrado));
        }

        return customViolations;
    }
}
