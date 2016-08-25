package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.persistence.entity.ConveniosArrec;
import br.gov.to.sefaz.arr.persistence.entity.ConveniosTarifas;
import br.gov.to.sefaz.arr.persistence.enums.TipoCodigoBarraEnum;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Teste para a classe {@link ConveniosArrecTipoBarraWithFormaPagamentoValidator}.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 03/06/2016 18:28:00
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(SourceBundle.class)
public class ConveniosArrecTipoBarraWithFormaPagamentoValidatorTest {

    @InjectMocks
    ConveniosArrecTipoBarraWithFormaPagamentoValidator validator;

    @Before
    public void setUp() {
        mockStatic(SourceBundle.class);
    }

    @Test
    public void shouldFailSupportWhenObjectNotConveniosArrec() {
        // given
        Object convenioTarifas = new Object();

        assertFalse(validator.support(convenioTarifas.getClass(), ValidationContext.SAVE));

    }

    @Test
    public void shouldFailSupportWhenContextNotExists() {
        // given
        ConveniosArrec conveniosTarifas = createConvenioArrecMocked();

        assertFalse(validator.support(conveniosTarifas.getClass(), ""));
    }

    @Test
    public void shouldFailWhenPagamentoWithoutCodBarras() {

        ConveniosArrec conveniosTarifas = createConvenioArrecMocked();
        when(conveniosTarifas.getTipoBarra()).thenReturn(TipoCodigoBarraEnum.GNRE);

        List<ConveniosTarifas> listConvenioTarifasVazia = createListConvenioTarifasVazia();

        when(conveniosTarifas.getConveniosTarifas()).thenReturn(listConvenioTarifasVazia);

        Set<CustomViolation> violationSet = validator.validate(conveniosTarifas);

        assertFalse(violationSet.isEmpty());

    }

    private List<ConveniosTarifas> createListConvenioTarifasVazia() {
        ArrayList<ConveniosTarifas> retorno = new ArrayList<>();
        //Retorna vazio mesmo
        return retorno;
    }

    private ConveniosArrec createConvenioArrecMocked() {
        return mock(ConveniosArrec.class);
    }
}