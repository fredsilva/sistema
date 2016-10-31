package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.persistence.entity.ConveniosTarifas;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Teste usado para a classe {@link ConveniosTarifasDataFimValidator}.
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 06/06/2016 09:40:00
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(SourceBundle.class)
public class ConveniosTarifasDataFimValidatorTest {

    @InjectMocks
    ConveniosTarifasDataFimValidator validator;

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
        ConveniosTarifas conveniosTarifas = createConvenioTarifasMocked();

        assertFalse(validator.support(conveniosTarifas.getClass(), ""));
    }

    @Test
    public void shouldFailWhenDataFimBeforeDataInicio() {

        ConveniosTarifas conveniosTarifas = createConvenioTarifasMocked();

        when(conveniosTarifas.getDataFim()).thenReturn(LocalDate.of(2016,1,1));
        when(conveniosTarifas.getDataInicio()).thenReturn(LocalDate.now());

        Set<CustomViolation> violationSet = validator.validate(conveniosTarifas);

        assertFalse(violationSet.isEmpty());

    }

    private ConveniosTarifas createConvenioTarifasMocked() {
        return mock(ConveniosTarifas.class);
    }

}