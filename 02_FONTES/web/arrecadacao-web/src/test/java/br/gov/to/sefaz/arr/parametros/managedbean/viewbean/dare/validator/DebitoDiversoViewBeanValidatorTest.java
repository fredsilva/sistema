package br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.validator;

import br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DebitoDiversoViewBean;
import br.gov.to.sefaz.arr.persistence.entity.ReceitasTaxas;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.util.application.ApplicationUtil;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
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
 * {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.validator.DebitoDiversoViewBeanValidator}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 25/10/2016 15:57:00
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({SourceBundle.class, MessageUtil.class, MessageFormat.class})
public class DebitoDiversoViewBeanValidatorTest {

    @InjectMocks
    private DebitoDiversoViewBeanValidator validator;

    @Before
    public void setUp() {
        mockStatic(SourceBundle.class);
        mockStatic(MessageUtil.class);
        mockStatic(MessageFormat.class);
        String message = "O valor da TSE supera o valor máximo de {0} cadastrado para o Subcódigo Informado!";
        PowerMockito.when(SourceBundle.getMessage(MessageUtil.ARR,
                "arr.par.dare.debitoDiversoViewBean.valorTse.greaterThan")).thenReturn(message);
        String messageFormat = MessageFormat
                .format(message, NumberFormat.getCurrencyInstance(ApplicationUtil.LOCALE).format(BigDecimal.ONE));
        PowerMockito.when(MessageFormat.format(message, NumberFormat.getCurrencyInstance(ApplicationUtil.LOCALE)
                .format(BigDecimal.ONE))).thenReturn(messageFormat);
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
        DebitoDiversoViewBean debitoDiversoViewBean = new DebitoDiversoViewBean();

        // then
        assertFalse(validator.support(debitoDiversoViewBean.getClass(), StringUtils.EMPTY));
    }

    @Test
    public void shouldSupport() {
        // given
        DebitoDiversoViewBean debitoDiversoViewBean = new DebitoDiversoViewBean();

        // then
        assertTrue(validator.support(debitoDiversoViewBean.getClass(), VIEW_VALIDATOR));
    }

    @Test
    public void shouldFailWhenReceitaNotHasSubcodigoAndValorImpostoIsNull() {
        // given
        DebitoDiversoViewBean diversoViewBean = mock(DebitoDiversoViewBean.class);
        given(diversoViewBean.getReceitasTaxas()).willReturn(new ArrayList<>());
        given(diversoViewBean.getValorMulta()).willReturn(BigDecimal.TEN);
        given(diversoViewBean.getValorJuros()).willReturn(BigDecimal.TEN);

        // when
        Set<CustomViolation> violations = validator.validate(diversoViewBean);

        // then
        String message = SourceBundle.getMessage(MessageUtil.ARR,
                "arr.par.dare.debitoDiversoViewBean.valorImposto.empty");
        assertEquals(violations.iterator().next().getMessage(), message);
    }

    @Test
    public void shouldFailWhenReceitaNotHasSubcodigoAndValorJurosIsNull() {
        // given
        DebitoDiversoViewBean diversoViewBean = mock(DebitoDiversoViewBean.class);
        given(diversoViewBean.getReceitasTaxas()).willReturn(new ArrayList<>());
        given(diversoViewBean.getValorMulta()).willReturn(BigDecimal.TEN);
        given(diversoViewBean.getValorImposto()).willReturn(BigDecimal.TEN);

        // when
        Set<CustomViolation> violations = validator.validate(diversoViewBean);

        // then
        String message = SourceBundle.getMessage(MessageUtil.ARR,
                "arr.par.dare.debitoDiversoViewBean.valorJuros.empty");
        assertEquals(violations.iterator().next().getMessage(), message);
    }

    @Test
    public void shouldFailWhenReceitaNotHasSubcodigoAndValorMultaIsNull() {
        // given
        DebitoDiversoViewBean diversoViewBean = mock(DebitoDiversoViewBean.class);
        given(diversoViewBean.getReceitasTaxas()).willReturn(new ArrayList<>());
        given(diversoViewBean.getValorJuros()).willReturn(BigDecimal.TEN);
        given(diversoViewBean.getValorImposto()).willReturn(BigDecimal.TEN);

        // when
        Set<CustomViolation> violations = validator.validate(diversoViewBean);

        // then
        String message = SourceBundle.getMessage(MessageUtil.ARR,
                "arr.par.dare.debitoDiversoViewBean.valorMulta.empty");
        assertEquals(violations.iterator().next().getMessage(), message);
    }

    @Test
    public void shouldPassWhenReceitaNotHasSubcodigoAndValoresAreNotNull() {
        // given
        DebitoDiversoViewBean diversoViewBean = mock(DebitoDiversoViewBean.class);
        given(diversoViewBean.getReceitasTaxas()).willReturn(new ArrayList<>());
        given(diversoViewBean.getValorJuros()).willReturn(BigDecimal.TEN);
        given(diversoViewBean.getValorImposto()).willReturn(BigDecimal.TEN);
        given(diversoViewBean.getValorMulta()).willReturn(BigDecimal.TEN);

        // when
        Set<CustomViolation> violations = validator.validate(diversoViewBean);

        // then
        assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldFailWhenReceitaHasSubcodigoAndIsNull() {
        // given
        DebitoDiversoViewBean diversoViewBean = mock(DebitoDiversoViewBean.class);
        List<ReceitasTaxas> receitasTaxas = new ArrayList<>();
        receitasTaxas.add(mock(ReceitasTaxas.class));
        given(diversoViewBean.getReceitasTaxas()).willReturn(receitasTaxas);
        given(diversoViewBean.getIdSubCodigo()).willReturn(null);

        // when
        Set<CustomViolation> violations = validator.validate(diversoViewBean);

        // then
        String message = SourceBundle.getMessage(MessageUtil.ARR,
                "arr.par.dare.debitoDiversoViewBean.subcodigo.empty");
        assertEquals(violations.iterator().next().getMessage(), message);
    }

    @Test
    public void shouldFailWhenReceitaHasSubcodigoAndQuantidadeOrValorTseIsNull() {
        // given
        DebitoDiversoViewBean diversoViewBean = mock(DebitoDiversoViewBean.class);
        List<ReceitasTaxas> receitasTaxas = new ArrayList<>();
        ReceitasTaxas subcodigo = mock(ReceitasTaxas.class);
        given(subcodigo.getIdSubcodigo()).willReturn(1);
        given(subcodigo.getValorLimite()).willReturn(BigDecimal.ONE);
        receitasTaxas.add(subcodigo);
        given(diversoViewBean.getReceitasTaxas()).willReturn(receitasTaxas);
        given(diversoViewBean.getIdSubCodigo()).willReturn(1);

        // when
        Set<CustomViolation> violations = validator.validate(diversoViewBean);

        // then
        String message = SourceBundle.getMessage(MessageUtil.ARR,
                "arr.par.dare.debitoDiversoViewBean.quantidadeTse.empty");
        assertEquals(violations.iterator().next().getMessage(), message);
    }

    @Test
    public void shouldFailWhenReceitaHasSubcodigoAndValorTseIsGreaterThanValorLimiteSubcodigo() {
        // given
        DebitoDiversoViewBean diversoViewBean = mock(DebitoDiversoViewBean.class);
        List<ReceitasTaxas> receitasTaxas = new ArrayList<>();
        ReceitasTaxas subcodigo = mock(ReceitasTaxas.class);
        given(subcodigo.getIdSubcodigo()).willReturn(1);
        given(subcodigo.getValorLimite()).willReturn(BigDecimal.ONE);
        receitasTaxas.add(subcodigo);
        given(diversoViewBean.getReceitasTaxas()).willReturn(receitasTaxas);
        given(diversoViewBean.getIdSubCodigo()).willReturn(1);
        given(diversoViewBean.getQuantidadeTse()).willReturn(1);
        given(diversoViewBean.getValorUnitarioTse()).willReturn(BigDecimal.TEN);
        given(diversoViewBean.getValorTse()).willReturn(BigDecimal.TEN);

        // when
        Set<CustomViolation> violations = validator.validate(diversoViewBean);

        // then
        String messageFormat = "O valor da TSE supera o valor máximo de R$ 1,00 cadastrado para o Subcódigo Informado!";
        assertEquals(violations.iterator().next().getMessage(), messageFormat);
    }

    @Test
    public void shouldPassWhenReceitaHasSubcodigoAndValorTseIsLessThanValorLimiteSubcodigo() {
        // given
        DebitoDiversoViewBean diversoViewBean = mock(DebitoDiversoViewBean.class);
        List<ReceitasTaxas> receitasTaxas = new ArrayList<>();
        ReceitasTaxas subcodigo = mock(ReceitasTaxas.class);
        given(subcodigo.getIdSubcodigo()).willReturn(1);
        given(subcodigo.getValorLimite()).willReturn(BigDecimal.TEN);
        receitasTaxas.add(subcodigo);
        given(diversoViewBean.getReceitasTaxas()).willReturn(receitasTaxas);
        given(diversoViewBean.getIdSubCodigo()).willReturn(1);
        given(diversoViewBean.getQuantidadeTse()).willReturn(1);
        given(diversoViewBean.getValorUnitarioTse()).willReturn(BigDecimal.ONE);
        given(diversoViewBean.getValorTse()).willReturn(BigDecimal.ONE);

        // when
        Set<CustomViolation> violations = validator.validate(diversoViewBean);

        // then
        assertTrue(violations.isEmpty());
    }

}