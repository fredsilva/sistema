package br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.validator;

import br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEViewBean;
import br.gov.to.sefaz.arr.persistence.enums.TipoContribuinteEnum;
import br.gov.to.sefaz.arr.persistence.enums.TipoPessoaEnum;
import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.custom.CnpjValidatorHandler;
import br.gov.to.sefaz.business.service.validation.custom.CpfValidatorHandler;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static br.gov.to.sefaz.arr.parametros.managedbean.validator.DareViewBeanValidator.VIEW_VALIDATOR;

/**
 * Validador de {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEViewBean} para adicionar na
 * lista de pagamentos do DARE-e.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 04/10/2016 16:21:00
 */
@Component
public class DareEViewBeanValidator implements ServiceValidator<DareEViewBean> {

    private final CpfValidatorHandler cpfValidatorHandler;
    private final CnpjValidatorHandler cnpjValidatorHandler;

    public DareEViewBeanValidator() {
        this.cpfValidatorHandler = new CpfValidatorHandler();
        this.cnpjValidatorHandler = new CnpjValidatorHandler();
    }

    @Override
    public boolean support(Class<?> clazz, String context) {
        return clazz.equals(DareEViewBean.class) && context.equals(VIEW_VALIDATOR);
    }

    @Override
    public Set<CustomViolation> validate(DareEViewBean dareEViewBean) {
        HashSet<CustomViolation> customViolations = new HashSet<>();
        Long identificacaoPessoa = dareEViewBean.getIdentificacaoPessoa();

        if (!isCpfOrCnpjDigitValid(dareEViewBean.getTipoPessoa(), identificacaoPessoa)) {
            String message = SourceBundle.getMessage(MessageUtil.ARR,
                    "dare.busca.contribuinte.id.digitoInvalido");
            customViolations.add(new CustomViolation(message));
        }

        if (!TipoContribuinteEnum.NAO_CONTRIBUINTE.equals(dareEViewBean.getTipoContribuinte())
                && Objects.isNull(dareEViewBean.getNomePessoa())) {
            String message = SourceBundle.getMessage(MessageUtil.ARR,
                    "arr.par.dare.DareEViewBean.nomePessoa.empty");
            customViolations.add(new CustomViolation(message));
        }

        if (Objects.isNull(dareEViewBean.getTipoImposto())) {
            String message = SourceBundle.getMessage(MessageUtil.ARR,
                    "arr.par.dare.DareEViewBean.tipoImposto.empty");
            customViolations.add(new CustomViolation(message));
        }

        if (Objects.isNull(dareEViewBean.getOrigemDebito())) {
            String message = SourceBundle.getMessage(MessageUtil.ARR,
                    "arr.par.dare.DareEViewBean.origemDebito.empty");
            customViolations.add(new CustomViolation(message));
        }

        return customViolations;
    }

    private boolean isCpfOrCnpjDigitValid(TipoPessoaEnum tipoPessoa, Long idContribuinte) {
        if (TipoPessoaEnum.CPF.equals(tipoPessoa)) {
            return cpfValidatorHandler.validateCpf(String.valueOf(idContribuinte));
        } else if (TipoPessoaEnum.CNPJ.equals(tipoPessoa)) {
            return cnpjValidatorHandler.validateCnpj(String.valueOf(idContribuinte));
        }

        return true;
    }

}
