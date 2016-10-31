package br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.validator;

import br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEViewBean;
import br.gov.to.sefaz.arr.persistence.enums.OrigemDebitoEnum;
import br.gov.to.sefaz.arr.persistence.enums.TipoContribuinteEnum;
import br.gov.to.sefaz.arr.persistence.enums.TipoImpostoEnum;
import br.gov.to.sefaz.arr.persistence.enums.TipoPessoaEnum;
import br.gov.to.sefaz.business.service.validation.custom.CnpjValidatorHandler;
import br.gov.to.sefaz.business.service.validation.custom.CpfValidatorHandler;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Set;

import static br.gov.to.sefaz.arr.parametros.managedbean.validator.DareViewBeanValidator.VIEW_VALIDATOR;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * Teste da classe {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.validator.DareEPagamentoValidator}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 25/10/2016 15:13:00
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({SourceBundle.class, MessageUtil.class})
public class DareEViewBeanValidatorTest {

    @Mock
    @SuppressWarnings("PMD.UnusedPrivateField")
    private CpfValidatorHandler cpfValidatorHandler;
    @Mock
    @SuppressWarnings("PMD.UnusedPrivateField")
    private CnpjValidatorHandler cnpjValidatorHandler;

    @InjectMocks
    private DareEViewBeanValidator validator;

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
        Assert.assertFalse(validator.support(object.getClass(), VIEW_VALIDATOR));
    }

    @Test
    public void shouldFailSupportWhenContextNotExists() {
        // given
        DareEViewBean dareEViewBean = new DareEViewBean();

        // then
        Assert.assertFalse(validator.support(dareEViewBean.getClass(), StringUtils.EMPTY));
    }

    @Test
    public void shouldSupport() {
        // given
        DareEViewBean dareEViewBean = new DareEViewBean();

        // then
        assertTrue(validator.support(dareEViewBean.getClass(), VIEW_VALIDATOR));
    }

    @Test
    public void shouldFailWhenCpfDigitIsNotValid() {
        // given
        DareEViewBean dareEViewBean = mock(DareEViewBean.class);
        given(dareEViewBean.getTipoPessoa()).willReturn(TipoPessoaEnum.CPF);
        given(dareEViewBean.getIdentificacaoPessoa()).willReturn(12345L);
        given(dareEViewBean.getTipoContribuinte()).willReturn(TipoContribuinteEnum.NAO_CONTRIBUINTE);
        given(dareEViewBean.getTipoImposto()).willReturn(TipoImpostoEnum.ICMS);
        given(dareEViewBean.getOrigemDebito()).willReturn(OrigemDebitoEnum.ICMS_FRETE);

        // when
        Set<CustomViolation> violations = validator.validate(dareEViewBean);

        // then
        String message = SourceBundle.getMessage(MessageUtil.ARR,
                "dare.busca.contribuinte.id.digitoInvalido");
        assertEquals(violations.iterator().next().getMessage(), message);
    }

    @Test
    public void shouldPassWhenCpfDigitIsValid() {
        // given
        DareEViewBean dareEViewBean = mock(DareEViewBean.class);
        given(dareEViewBean.getTipoPessoa()).willReturn(TipoPessoaEnum.CPF);
        given(dareEViewBean.getIdentificacaoPessoa()).willReturn(11111111111L);
        given(dareEViewBean.getTipoContribuinte()).willReturn(TipoContribuinteEnum.NAO_CONTRIBUINTE);
        given(dareEViewBean.getTipoImposto()).willReturn(TipoImpostoEnum.ICMS);
        given(dareEViewBean.getOrigemDebito()).willReturn(OrigemDebitoEnum.ICMS_FRETE);

        // when
        Set<CustomViolation> violations = validator.validate(dareEViewBean);

        // then
        assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldFailWhenCnpjDigitIsNotValid() {
        // given
        DareEViewBean dareEViewBean = mock(DareEViewBean.class);
        given(dareEViewBean.getTipoPessoa()).willReturn(TipoPessoaEnum.CNPJ);
        given(dareEViewBean.getIdentificacaoPessoa()).willReturn(12345L);
        given(dareEViewBean.getTipoContribuinte()).willReturn(TipoContribuinteEnum.NAO_CONTRIBUINTE);
        given(dareEViewBean.getTipoImposto()).willReturn(TipoImpostoEnum.ICMS);
        given(dareEViewBean.getOrigemDebito()).willReturn(OrigemDebitoEnum.ICMS_FRETE);

        // when
        Set<CustomViolation> violations = validator.validate(dareEViewBean);

        // then
        String message = SourceBundle.getMessage(MessageUtil.ARR,
                "dare.busca.contribuinte.id.digitoInvalido");
        assertEquals(violations.iterator().next().getMessage(), message);

    }

    @Test
    public void shouldPassWhenCnpjDigitIsValid() {
        // given
        DareEViewBean dareEViewBean = mock(DareEViewBean.class);
        given(dareEViewBean.getTipoPessoa()).willReturn(TipoPessoaEnum.CNPJ);
        given(dareEViewBean.getIdentificacaoPessoa()).willReturn(70950571000172L);
        given(dareEViewBean.getTipoContribuinte()).willReturn(TipoContribuinteEnum.NAO_CONTRIBUINTE);
        given(dareEViewBean.getTipoImposto()).willReturn(TipoImpostoEnum.ICMS);
        given(dareEViewBean.getOrigemDebito()).willReturn(OrigemDebitoEnum.ICMS_FRETE);

        // when
        Set<CustomViolation> violations = validator.validate(dareEViewBean);

        // then
        assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldFailWhenTipoContribuinteIsNotNaoContribuinteAndNomeIsEmpty() {
        // given
        DareEViewBean dareEViewBean = mock(DareEViewBean.class);
        given(dareEViewBean.getTipoPessoa()).willReturn(TipoPessoaEnum.RENAVAM);
        given(dareEViewBean.getIdentificacaoPessoa()).willReturn(123456789L);
        given(dareEViewBean.getTipoContribuinte()).willReturn(TipoContribuinteEnum.ICMS);
        given(dareEViewBean.getTipoImposto()).willReturn(TipoImpostoEnum.ICMS);
        given(dareEViewBean.getOrigemDebito()).willReturn(OrigemDebitoEnum.ICMS_FRETE);

        // when
        Set<CustomViolation> violations = validator.validate(dareEViewBean);

        // then
        String message = SourceBundle.getMessage(MessageUtil.ARR,
                "arr.par.dare.DareEViewBean.nomePessoa.empty");
        assertEquals(violations.iterator().next().getMessage(), message);

    }

    @Test
    public void shouldFailWhenTipoImpostoIsNull() {
        // given
        DareEViewBean dareEViewBean = mock(DareEViewBean.class);
        given(dareEViewBean.getTipoPessoa()).willReturn(TipoPessoaEnum.CPF);
        given(dareEViewBean.getIdentificacaoPessoa()).willReturn(11111111111L);
        given(dareEViewBean.getTipoContribuinte()).willReturn(TipoContribuinteEnum.NAO_CONTRIBUINTE);
        given(dareEViewBean.getTipoImposto()).willReturn(null);
        given(dareEViewBean.getOrigemDebito()).willReturn(OrigemDebitoEnum.MULTAS);

        // when
        Set<CustomViolation> violations = validator.validate(dareEViewBean);

        // then
        String message = SourceBundle.getMessage(MessageUtil.ARR,
                "arr.par.dare.DareEViewBean.tipoImposto.empty");
        assertEquals(violations.iterator().next().getMessage(), message);
    }

    @Test
    public void shouldPassWhenTipoImpostoIsNotNull() {
        // given
        DareEViewBean dareEViewBean = mock(DareEViewBean.class);
        given(dareEViewBean.getTipoPessoa()).willReturn(TipoPessoaEnum.CPF);
        given(dareEViewBean.getIdentificacaoPessoa()).willReturn(11111111111L);
        given(dareEViewBean.getTipoContribuinte()).willReturn(TipoContribuinteEnum.NAO_CONTRIBUINTE);
        given(dareEViewBean.getTipoImposto()).willReturn(TipoImpostoEnum.IPVA);
        given(dareEViewBean.getOrigemDebito()).willReturn(OrigemDebitoEnum.IPVA);

        // when
        Set<CustomViolation> violations = validator.validate(dareEViewBean);

        // then
        assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldFailWhenOrigemDebitoIsNull() {
        // given
        DareEViewBean dareEViewBean = mock(DareEViewBean.class);
        given(dareEViewBean.getTipoPessoa()).willReturn(TipoPessoaEnum.CPF);
        given(dareEViewBean.getIdentificacaoPessoa()).willReturn(11111111111L);
        given(dareEViewBean.getTipoContribuinte()).willReturn(TipoContribuinteEnum.NAO_CONTRIBUINTE);
        given(dareEViewBean.getTipoImposto()).willReturn(TipoImpostoEnum.IPVA);
        given(dareEViewBean.getOrigemDebito()).willReturn(null);

        // when
        Set<CustomViolation> violations = validator.validate(dareEViewBean);

        // then
        String message = SourceBundle.getMessage(MessageUtil.ARR,
                "arr.par.dare.DareEViewBean.tipoImposto.empty");
        assertEquals(violations.iterator().next().getMessage(), message);
    }

    @Test
    public void shouldPassWhenOrigemDebitoIsNotNull() {
        // given
        DareEViewBean dareEViewBean = mock(DareEViewBean.class);
        given(dareEViewBean.getTipoPessoa()).willReturn(TipoPessoaEnum.CPF);
        given(dareEViewBean.getIdentificacaoPessoa()).willReturn(11111111111L);
        given(dareEViewBean.getTipoContribuinte()).willReturn(TipoContribuinteEnum.NAO_CONTRIBUINTE);
        given(dareEViewBean.getTipoImposto()).willReturn(TipoImpostoEnum.TAXAS_NAO_TRIBUTARIAS);
        given(dareEViewBean.getOrigemDebito()).willReturn(OrigemDebitoEnum.MULTAS);

        // when
        Set<CustomViolation> violations = validator.validate(dareEViewBean);

        // then
        assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldPassWhenTipoContribuinteIsNotNaoContribuinteAndNomeIsNotEmpty() {
        // given
        DareEViewBean dareEViewBean = mock(DareEViewBean.class);
        given(dareEViewBean.getTipoPessoa()).willReturn(TipoPessoaEnum.CPF);
        given(dareEViewBean.getIdentificacaoPessoa()).willReturn(11111111111L);
        given(dareEViewBean.getTipoContribuinte()).willReturn(TipoContribuinteEnum.ICMS);
        given(dareEViewBean.getTipoImposto()).willReturn(TipoImpostoEnum.ICMS);
        given(dareEViewBean.getOrigemDebito()).willReturn(OrigemDebitoEnum.IPVA);
        given(dareEViewBean.getNomePessoa()).willReturn("NomePessoa");

        // when
        Set<CustomViolation> violations = validator.validate(dareEViewBean);

        // then
        assertTrue(violations.isEmpty());
    }
}