package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.parametros.persistence.entity.ReceitasRepasse;
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
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * Teste da classe {@link br.gov.to.sefaz.arr.parametros.business.service.validator.ReceitasRepasseIncidenciaValidator}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 15/06/2016 14:04:00
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(SourceBundle.class)
public class ReceitasRepasseIncidenciaValidatorTest {

    @Mock
    private ReceitasRepasse receitasRepasse;

    @InjectMocks
    private ReceitasRepasseIncidenciaValidator validator;

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
    public void shouldFailWhenIncidenciaIsNotPresent() {
        //when
        when(receitasRepasse.getReparteCorrecao()).thenReturn(false);
        when(receitasRepasse.getReparteMulta()).thenReturn(false);
        when(receitasRepasse.getReparteTaxa()).thenReturn(false);
        when(receitasRepasse.getReparteJuros()).thenReturn(false);
        when(receitasRepasse.getRepartePrincipal()).thenReturn(false);

        //then
        Set<CustomViolation> violationSet = validator.validate(receitasRepasse);
        assertFalse(violationSet.isEmpty());
    }

    @Test
    public void shouldPassWhenAnyIncidenciaIsPresent() {
        //when
        when(receitasRepasse.getReparteCorrecao()).thenReturn(true);
        when(receitasRepasse.getReparteMulta()).thenReturn(false);
        when(receitasRepasse.getReparteTaxa()).thenReturn(false);
        when(receitasRepasse.getReparteJuros()).thenReturn(false);
        when(receitasRepasse.getRepartePrincipal()).thenReturn(false);

        //then
        Set<CustomViolation> violationSet = validator.validate(receitasRepasse);
        assertTrue(violationSet.isEmpty());
    }
}