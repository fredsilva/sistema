package br.gov.to.sefaz.arr.parametros.managedbean.validator;

import br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEPagamento;
import br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEViewBean;
import br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DebitoDiversoViewBean;
import br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DebitoIcmsFreteViewBean;
import br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DebitoPagamentoParcialViewBean;
import br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.validator.DareEPagamentoValidator;
import br.gov.to.sefaz.business.service.validation.ValidationSuite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Suite de validação para as Views referentes a Geração do DARE-e.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 29/09/2016 15:56:00
 */
@Component
public class DareViewBeanValidator {

    public static final String VIEW_VALIDATOR = "View";
    public static final String GERAR_DARE_VALIDATOR = "GerarDare";

    private final DareEPagamentoValidator dareEPagamentoValidator;

    @Autowired
    public DareViewBeanValidator(DareEPagamentoValidator dareEPagamentoValidator) {
        this.dareEPagamentoValidator = dareEPagamentoValidator;
    }

    /**
     * Validação referente ao painel de
     * {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DebitoPagamentoParcialViewBean}.
     * Conforme as regras de Adicionar Item na Lista de Pagamentos.
     *
     * @param debitoPagamentoParcial contém as informações as quais serão validadas.
     */
    public void debitoPagamentoParcialValidator(@ValidationSuite(context = VIEW_VALIDATOR)
            DebitoPagamentoParcialViewBean debitoPagamentoParcial) {
        //validador
    }

    /**
     * Validação referente ao paineis de
     * {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DebitoSemPagamentoParcialViewBean} e
     * {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DebitoIpvaViewBean}.
     * Conforme as regras de Adicionar Item na Lista de Pagamentos.
     *
     * @param dareEPagamentos contém as informações as quais serão validadas.
     * @return se a lista estiver vazia retorna {@code false}, caso não, retorna {@code true}.
     */
    public boolean dareEPagamentosValidator(List<DareEPagamento> dareEPagamentos) {
        return dareEPagamentoValidator.validateDareEPagamentoIsNotEmpty(dareEPagamentos) && dareEPagamentoValidator
                .validateDareEPagamentoMaxValue(dareEPagamentos);
    }

    /**
     * Validação referente ao painel de
     * {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DebitoIcmsFreteViewBean}.
     * Conforme as regras de Adicionar Item na Lista de Pagamentos.
     *
     * @param debitoIcmsFreteViewBean contém as informações as quais serão validadas.
     */
    public void debitoIcmsFreteValidator(@ValidationSuite(context = VIEW_VALIDATOR) DebitoIcmsFreteViewBean
            debitoIcmsFreteViewBean) {
        //validador
    }

    /**
     * Validação referente ao painel de
     * {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DebitoDiversoViewBean}.
     * Conforme as regras de Adicionar Item na Lista de Pagamentos.
     *
     * @param debitoDiversoViewBean contém as informações as quais serão validadas.
     */
    public void debitoDiversosValidator(@ValidationSuite(context = VIEW_VALIDATOR) DebitoDiversoViewBean
            debitoDiversoViewBean) {
        //validador
    }

    /**
     * Validação referente ao
     * {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEViewBean}.
     * Conforme as regras de Adicionar Item na Lista de Pagamentos.
     *
     * @param dareViewBean contém as informações as quais serão validadas.
     */
    public void dareEViewBeanValidator(@ValidationSuite(context = VIEW_VALIDATOR) DareEViewBean dareViewBean) {
        //validador
    }

    /**
     * Validação referente ao
     * {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEViewBean}.
     * Conforme as regras de Geração de DARE-e.
     *
     * @param dareViewBean contém as informações as quais serão validadas.
     */
    public void gerarDareValidator(@ValidationSuite(context = GERAR_DARE_VALIDATOR) DareEViewBean dareViewBean) {
        //validador
    }
}
