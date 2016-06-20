package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.parametros.persistence.entity.Receitas;
import br.gov.to.sefaz.arr.parametros.persistence.repository.ReceitasRepository;
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
import static org.mockito.Mockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Teste da classe {@link ReceitasDuplicatedCodigoValidator}.
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 06/06/2016 16:23:00
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(SourceBundle.class)
public class ReceitasDuplicatedCodigoValidatorTest {

    @Mock
    ReceitasRepository repository;

    @InjectMocks
    ReceitasDuplicatedCodigoValidator validator;

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
    public void shouldFailWhenDuplicatedCodigoReceitas() {
        Receitas receitas = createReceitasMocked();

        when(receitas.getIdReceita()).thenReturn(1);
        when(repository.exists(receitas.getIdReceita())).thenReturn(true);

        Set<CustomViolation> violationSet = validator.validate(receitas);
        assertFalse(violationSet.isEmpty());
    }

    private Receitas createReceitasMocked() {
        return mock(Receitas.class);
    }
}