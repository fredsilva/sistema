package br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.validator;

import br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEPagamento;
import br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEViewBean;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static br.gov.to.sefaz.arr.parametros.managedbean.validator.DareViewBeanValidator.GERAR_DARE_VALIDATOR;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * Teste da classe {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.validator.GerarDareValidator}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 25/10/2016 16:11:00
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({SourceBundle.class, MessageUtil.class})
public class GerarDareValidatorTest {

    @InjectMocks
    private GerarDareValidator validator;

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
        assertFalse(validator.support(object.getClass(), GERAR_DARE_VALIDATOR));
    }

    @Test
    public void shouldFailSupportWhenContextNotExists() {
        // given
        DareEViewBean viewBean = new DareEViewBean();

        // then
        assertFalse(validator.support(viewBean.getClass(), StringUtils.EMPTY));
    }

    @Test
    public void shouldSupport() {
        // given
        DareEViewBean viewBean = new DareEViewBean();

        // then
        assertTrue(validator.support(viewBean.getClass(), GERAR_DARE_VALIDATOR));
    }

    @Test
    public void shouldFailWhenListaPagamentosIsNull() {
        // given
        DareEViewBean dareEViewBean = mock(DareEViewBean.class);
        given(dareEViewBean.getListaPagamentos()).willReturn(null);

        // when
        Set<CustomViolation> violations = validator.validate(dareEViewBean);

        // then
        String message = SourceBundle.getMessage(MessageUtil.ARR, "arr.par.dare.DareEViewBean.listaPagamentos.empty");
        assertEquals(violations.iterator().next().getMessage(), message);
    }

    @Test
    public void shouldFailWhenListaPagamentosIsEmpty() {
        // given
        DareEViewBean dareEViewBean = mock(DareEViewBean.class);
        given(dareEViewBean.getListaPagamentos()).willReturn(new ArrayList<>());

        // when
        Set<CustomViolation> violations = validator.validate(dareEViewBean);

        // then
        String message = SourceBundle.getMessage(MessageUtil.ARR, "arr.par.dare.DareEViewBean.listaPagamentos.empty");
        assertEquals(violations.iterator().next().getMessage(), message);
    }

    @Test
    public void shouldFailWhenListaPagamentosValorIsGreaterThanMaxValue() {
        //given
        DareEViewBean dareEViewBean = mock(DareEViewBean.class);
        DareEPagamento dareEPagamento = mock(DareEPagamento.class);
        List<DareEPagamento> dareEPagamentos = new ArrayList<>();
        dareEPagamentos.add(dareEPagamento);
        given(dareEViewBean.getListaPagamentos()).willReturn(dareEPagamentos);
        given(dareEViewBean.getValorTotal()).willReturn(new BigDecimal("1000000000.00"));

        // when
        Set<CustomViolation> violations = validator.validate(dareEViewBean);

        // then
        String message = SourceBundle.getMessage(MessageUtil.ARR, "arr.par.dare.dareEPagamento.totalRecolher.limit");
        assertEquals(violations.iterator().next().getMessage(), message);
    }

    @Test
    public void shouldPassWhenListaPagamentosIsNotEmpty() {
        // given
        DareEViewBean dareEViewBean = mock(DareEViewBean.class);
        DareEPagamento dareEPagamento = mock(DareEPagamento.class);
        List<DareEPagamento> dareEPagamentos = new ArrayList<>();
        dareEPagamentos.add(dareEPagamento);
        given(dareEViewBean.getListaPagamentos()).willReturn(dareEPagamentos);
        given(dareEViewBean.getValorTotal()).willReturn(new BigDecimal("999999999.99"));

        // when
        Set<CustomViolation> violations = validator.validate(dareEViewBean);

        // then
        assertTrue(violations.isEmpty());
    }
}