package br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.validator;

import br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEPagamento;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.mockito.BDDMockito.given;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * Teste da classe {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.validator.DareEPagamentoValidator}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 25/10/2016 14:51:00
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({SourceBundle.class, MessageUtil.class})
public class DareEPagamentoValidatorTest {

    @InjectMocks
    private DareEPagamentoValidator dareEPagamentoValidator;

    @Before
    public void setUp() {
        mockStatic(SourceBundle.class);
        mockStatic(MessageUtil.class);
    }

    @Test
    public void shouldNotValidateIfListOfDareEPagamentoIsNull() {
        //when
        boolean isNotNull = dareEPagamentoValidator.validateDareEPagamentoIsNotEmpty(null);

        //then
        assertFalse(isNotNull);
    }

    @Test
    public void shouldNotValidateIfListOfDareEPagamentoIsEmpty() {
        //when
        boolean isNotEmpty = dareEPagamentoValidator.validateDareEPagamentoIsNotEmpty(new ArrayList<>());

        //then
        assertFalse(isNotEmpty);
    }

    @Test
    public void shouldValidateIfListOfDareEPagamentoIsNotEmpty() {
        //given
        DareEPagamento dareEPagamento = mock(DareEPagamento.class);
        ArrayList<DareEPagamento> dareEPagamentos = new ArrayList<>();
        dareEPagamentos.add(dareEPagamento);

        //when
        boolean isNotEmpty = dareEPagamentoValidator.validateDareEPagamentoIsNotEmpty(dareEPagamentos);

        //then
        Assert.assertTrue(isNotEmpty);
    }

    @Test
    public void shouldNotValidateDareEPagamentoGreaterThanMaxValue() {
        //given
        DareEPagamento dareEPagamento = mock(DareEPagamento.class);
        ArrayList<DareEPagamento> dareEPagamentos = new ArrayList<>();
        given(dareEPagamento.getValorTotal()).willReturn(new BigDecimal("1000000000.00"));
        dareEPagamentos.add(dareEPagamento);

        //when
        boolean isLessThanMaxValue = dareEPagamentoValidator.validateDareEPagamentoMaxValue(dareEPagamentos);

        //then
        assertFalse(isLessThanMaxValue);
    }

    @Test
    public void shouldValidateDareEPagamentoLessThanMaxValue() {
        //given
        DareEPagamento dareEPagamento = mock(DareEPagamento.class);
        ArrayList<DareEPagamento> dareEPagamentos = new ArrayList<>();
        given(dareEPagamento.getValorTotal()).willReturn(new BigDecimal("999999999.99"));
        dareEPagamentos.add(dareEPagamento);

        //when
        boolean isLessThanMaxValue = dareEPagamentoValidator.validateDareEPagamentoMaxValue(dareEPagamentos);

        //then
        Assert.assertTrue(isLessThanMaxValue);
    }

}