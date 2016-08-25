package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.persistence.entity.ConveniosArrec;
import br.gov.to.sefaz.arr.persistence.repository.ConveniosArrecRepository;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * Teste da classe {@link ConveniosArrecExistsValidator}.
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 03/06/2016 18:04:00
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(SourceBundle.class)
public class ConveniosArrecExistsValidatorTest {

    @Mock
    ConveniosArrecRepository repository;

    @InjectMocks
    ConveniosArrecExistsValidator validator;

    @Before
    public void setUp() {
        mockStatic(SourceBundle.class);
    }

    @Test
    public void shouldFailSupportWhenObjectNotReceitas() {
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
    public void shouldFailExists() {
        ConveniosArrec conveniosArrec = createConvenioArrecMocked();
        when(conveniosArrec.getIdConvenio()).thenReturn(1L);
        when(repository.exists(conveniosArrec.getIdConvenio())).thenReturn(true);

        Set<CustomViolation> violationSet = validator.validate(conveniosArrec);

        assertFalse(violationSet.isEmpty());
    }

    private ConveniosArrec createConvenioArrecMocked() {
        return mock(ConveniosArrec.class);
    }

}