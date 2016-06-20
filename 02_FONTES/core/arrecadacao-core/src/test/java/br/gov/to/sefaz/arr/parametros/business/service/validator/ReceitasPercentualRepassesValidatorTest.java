package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.parametros.persistence.entity.Receitas;
import br.gov.to.sefaz.arr.parametros.persistence.entity.ReceitasRepasse;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * Teste da classe {@link ReceitasPercentualRepassesValidator}.
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 06/06/2016 16:33:00
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(SourceBundle.class)
public class ReceitasPercentualRepassesValidatorTest {

    @InjectMocks
    ReceitasPercentualRepassesValidator validator;

    @Before
    public void setUp() {
        mockStatic(SourceBundle.class);
    }

    @Test
    public void shouldFailSupportWhenObjectNotReceitas() {
        // given
        Object receitas = new Object();

        assertFalse(validator.support(receitas.getClass(), ValidationContext.SAVE));

    }

    @Test
    public void shouldFailSupportWhenContextNotExists() {
        // given
        Receitas receitas = createReceitasMocked();

        assertFalse(validator.support(receitas.getClass(), ""));
    }

    @Test
    public void shouldFailWhenPercentualDifferentFromOneHundred() {
        //given
        Receitas receitas = createReceitasMocked();
        ArrayList<ReceitasRepasse> repasseArrayList = new ArrayList<>();
        ReceitasRepasse receitasRepasse = createReceitasRepasseMocked();
        ReceitasRepasse receitasRepasse1 = createReceitasRepasseMocked();
        repasseArrayList.add(receitasRepasse);
        repasseArrayList.add(receitasRepasse1);
        //when
        when(receitasRepasse.getPercentualRepasse()).thenReturn(new BigDecimal(49));
        when(receitasRepasse1.getPercentualRepasse()).thenReturn(new BigDecimal(50));
        when(receitas.getReceitasRepasse()).thenReturn(repasseArrayList);
        //then
        Set<CustomViolation> violationSet = validator.validate(receitas);
        assertFalse(violationSet.isEmpty());
    }

    private Receitas createReceitasMocked() {
        return mock(Receitas.class);
    }

    private ReceitasRepasse createReceitasRepasseMocked() {
        return mock(ReceitasRepasse.class);
    }

}