package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.parametros.persistence.entity.ConveniosTarifas;
import br.gov.to.sefaz.arr.parametros.persistence.enums.FormaPagamentoEnum;
import br.gov.to.sefaz.arr.parametros.persistence.repository.ConveniosTarifasRepository;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Teste para a classe {@link ConveniosArrecDuplicatedTarifaValidator}.
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 03/06/2016 16:56:00
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(SourceBundle.class)
public class ConveniosArrecDuplicatedTarifaValidatorTest {

    @Mock
    ConveniosTarifasRepository repository;

    @InjectMocks
    ConveniosArrecDuplicatedTarifaValidator validator;

    @Before
    public void setUp() {
        mockStatic(SourceBundle.class);
    }

    @Test
    public void shouldFailSupportWhenObjectNotReceitas() {
        // given
        Object convenioTarifas = new Object();

        assertFalse("Deveria falhar quando o objeto não é receita." , validator.support(convenioTarifas.getClass(),
                ValidationContext.SAVE));

    }

    @Test
    public void shouldFailSupportWhenContextNotExists() {
        // given
        ConveniosTarifas conveniosTarifas = createConvenioTarifasMocked();

        assertFalse("Deveria falhar quando não existe Contexto.", validator.support(conveniosTarifas.getClass(), ""));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void shouldFailWhenDuplicatedTarifaValidation() {

        ConveniosTarifas conveniosTarifas = createConvenioTarifasMocked();
        when(conveniosTarifas.getIdConveniosArrec()).thenReturn(1L);
        when(conveniosTarifas.getFormaPagamento()).thenReturn(FormaPagamentoEnum.ARRECADACAO);
        List<ConveniosTarifas> listaConvenios = createListConvenioTarifasMocked();
        when(repository.findAll(Matchers.any(Specification.class))).thenReturn(listaConvenios);

        Set<CustomViolation> violationSet = validator.validate(conveniosTarifas);

        assertFalse("Deveria falhar ao incluir registros duplicados.", violationSet.isEmpty());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void shouldFailWhenDuplicatedTarifa() {

        ConveniosTarifas conveniosTarifas = createConvenioTarifasMocked();
        when(conveniosTarifas.getIdConveniosArrec()).thenReturn(1L);
        when(conveniosTarifas.getFormaPagamento()).thenReturn(FormaPagamentoEnum.ARRECADACAO);
        List<ConveniosTarifas> listaConvenios = createListConvenioTarifasMocked();

        Set<CustomViolation> violationSet = validator.validateDuplicatedTarifa(conveniosTarifas, listaConvenios);

        assertFalse("Deveria falhar ao incluir registros duplicados.", violationSet.isEmpty());
    }

    private List<ConveniosTarifas> createListConvenioTarifasMocked() {
        List<ConveniosTarifas> listaRetorno = new ArrayList<>();

        ConveniosTarifas conveniosTarifas1 = Mockito.mock(ConveniosTarifas.class);
        ConveniosTarifas conveniosTarifas2 = Mockito.mock(ConveniosTarifas.class);
        when(conveniosTarifas1.getIdConveniosArrec()).thenReturn(1L);
        when(conveniosTarifas2.getIdConveniosArrec()).thenReturn(2L);
        when(conveniosTarifas1.getFormaPagamento()).thenReturn(FormaPagamentoEnum.ARRECADACAO);
        when(conveniosTarifas2.getFormaPagamento()).thenReturn(FormaPagamentoEnum.INTERNET);

        listaRetorno.add(conveniosTarifas1);
        listaRetorno.add(conveniosTarifas2);
        return listaRetorno;
    }

    private ConveniosTarifas createConvenioTarifasMocked() {
        return mock(ConveniosTarifas.class);
    }

}