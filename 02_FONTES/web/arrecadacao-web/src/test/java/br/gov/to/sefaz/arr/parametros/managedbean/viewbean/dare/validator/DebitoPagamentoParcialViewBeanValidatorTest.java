package br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.validator;

import br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DebitoPagamentoParcialViewBean;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.math.BigDecimal;
import java.util.Set;

import static br.gov.to.sefaz.arr.parametros.managedbean.validator.DareViewBeanValidator.VIEW_VALIDATOR;
import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * Teste da classe
 * {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.validator.DebitoPagamentoParcialViewBeanValidator}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 25/10/2016 16:00:00
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({SourceBundle.class, MessageUtil.class})
public class DebitoPagamentoParcialViewBeanValidatorTest {

    @InjectMocks
    private DebitoPagamentoParcialViewBeanValidator validator;

    @Before
    public void setUp() {
        mockStatic(SourceBundle.class);
        mockStatic(MessageUtil.class);
    }

    @Test
    public void shouldFailSupportWhenObject() {
        // given
        Object object = new Object();

        //then
        assertFalse(validator.support(object.getClass(), VIEW_VALIDATOR));
    }

    @Test
    public void shouldFailSupportWhenContextNotExists() {
        // given
        DebitoPagamentoParcialViewBean viewBean = new DebitoPagamentoParcialViewBean();

        // then
        assertFalse(validator.support(viewBean.getClass(), StringUtils.EMPTY));
    }

    @Test
    public void shouldSupport() {
        // given
        DebitoPagamentoParcialViewBean viewBean = new DebitoPagamentoParcialViewBean();

        // then
        assertTrue(validator.support(viewBean.getClass(), VIEW_VALIDATOR));
    }

    @Test
    public void shouldFailWhenValorPagarDiferenteAndValorIsZero() {
        // given
        DebitoPagamentoParcialViewBean pagamentoParcialViewBean = mock(DebitoPagamentoParcialViewBean.class);
        given(pagamentoParcialViewBean.getIsValorPagarDiferente()).willReturn(true);
        given(pagamentoParcialViewBean.getValorPagar()).willReturn(BigDecimal.ZERO);

        // when
        Set<CustomViolation> violations = validator.validate(pagamentoParcialViewBean);

        // then
        String message = SourceBundle.getMessage(MessageUtil.ARR,
                "arr.par.dareEletronicoConsolidado.dadoPagamento.valorAPagar.emptyOrZero");
        assertEquals(violations.iterator().next().getMessage(), message);

    }

    @Test
    public void shouldFailWhenValorPagarDiferenteAndValorIsNull() {
        // given
        DebitoPagamentoParcialViewBean pagamentoParcialViewBean = mock(DebitoPagamentoParcialViewBean.class);
        given(pagamentoParcialViewBean.getIsValorPagarDiferente()).willReturn(true);
        given(pagamentoParcialViewBean.getValorPagar()).willReturn(null);

        // when
        Set<CustomViolation> violations = validator.validate(pagamentoParcialViewBean);

        // then
        String message = SourceBundle.getMessage(MessageUtil.ARR,
                "arr.par.dareEletronicoConsolidado.dadoPagamento.valorAPagar.emptyOrZero");
        assertEquals(violations.iterator().next().getMessage(), message);

    }

    @Test
    public void shouldPassWhenValorPagarDiferenteAndValorIsNotZero() {
        // given
        DebitoPagamentoParcialViewBean pagamentoParcialViewBean = mock(DebitoPagamentoParcialViewBean.class);
        given(pagamentoParcialViewBean.getIsValorPagarDiferente()).willReturn(true);
        given(pagamentoParcialViewBean.getValorPagar()).willReturn(BigDecimal.TEN);

        // when
        Set<CustomViolation> violations = validator.validate(pagamentoParcialViewBean);

        // then
        assertTrue(violations.isEmpty());
    }

}