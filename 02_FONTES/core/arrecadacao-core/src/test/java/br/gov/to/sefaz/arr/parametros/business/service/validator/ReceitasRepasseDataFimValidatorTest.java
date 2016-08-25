package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.persistence.entity.ReceitasRepasse;
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

import java.time.LocalDate;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * Teste da classe {@link br.gov.to.sefaz.arr.parametros.business.service.validator.ReceitasRepasseDataFimValidator}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 15/06/2016 14:03:00
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(SourceBundle.class)
public class ReceitasRepasseDataFimValidatorTest {

    @Mock
    private ReceitasRepasse receitasRepasse;

    @InjectMocks
    private ReceitasRepasseDataFimValidator validator;

    @Before
    public void setUp() {
        mockStatic(SourceBundle.class);
    }

    @Test
    public void shouldFailSupportWhenClassIsNotReceitasRepasse() {
        //then
        assertFalse(validator.support(Object.class, ValidationContext.SAVE));
    }

    @Test
    public void shouldFailSupportWhenContextNotExists() {
        // then
        assertFalse(validator.support(ReceitasRepasse.class, ""));
    }

    @Test
    public void shouldSupportWhenContextIsSave() {
        // then
        assertTrue(validator.support(ReceitasRepasse.class, ValidationContext.SAVE));
    }

    @Test
    public void shouldSupportWhenContextIsUpdate() {
        // then
        assertTrue(validator.support(ReceitasRepasse.class, ValidationContext.UPDATE));
    }

    @Test
    public void shouldFailWhenDataFinalIsBeforeDataInicio() {
        //when
        when(receitasRepasse.getDataFinal()).thenReturn(LocalDate.of(2016, 1, 1));
        when(receitasRepasse.getDataInicio()).thenReturn(LocalDate.of(2016, 1, 2));

        //then
        Set<CustomViolation> violationSet = validator.validate(receitasRepasse);
        assertFalse(violationSet.isEmpty());
    }

    @Test
    public void shouldPassWhenDataInicioIsBeforeDataFinal() {
        //when
        when(receitasRepasse.getDataFinal()).thenReturn(LocalDate.of(2016, 1, 2));
        when(receitasRepasse.getDataInicio()).thenReturn(LocalDate.of(2016, 1, 1));

        //then
        Set<CustomViolation> violationSet = validator.validate(receitasRepasse);
        assertTrue(violationSet.isEmpty());
    }

    @Test
    public void shouldPassWhenDataFinalIsNull() {
        //when
        when(receitasRepasse.getDataFinal()).thenReturn(null);

        //then
        Set<CustomViolation> violationSet = validator.validate(receitasRepasse);
        assertTrue(violationSet.isEmpty());
    }
}