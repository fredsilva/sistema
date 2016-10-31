package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.persistence.entity.Receitas;
import br.gov.to.sefaz.arr.persistence.repository.ReceitasRepository;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.Set;
import java.util.function.Consumer;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Teste para a classe {@link ReceitasCodigoBarraDuplicatedValidator}.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 06/06/2016 15:32:00
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(SourceBundle.class)
public class ReceitasCodigoBarraDuplicatedValidatorTest {

    @Mock
    private ReceitasRepository repository;
    @Mock
    private Receitas receitas;

    @InjectMocks
    private ReceitasCodigoBarraDuplicatedValidator validator;

    @Before
    public void setUp() {
        mockStatic(SourceBundle.class);
    }

    @Test
    public void shouldFailSupportWhenClassIsNotReceitas() {
        //then
        assertFalse(validator.support(Object.class, ValidationContext.SAVE));
    }

    @Test
    public void shouldFailSupportWhenContextNotExists() {
        // then
        assertFalse(validator.support(Receitas.class, ""));
    }

    @Test
    public void shouldSupportWhenContextIsSave() {
        // then
        assertTrue(validator.support(Receitas.class, ValidationContext.SAVE));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void shouldFailWhenDuplicatedCodBarraReceita() {
        //given
        ArrayList<Receitas> receitasList = new ArrayList<>();
        receitasList.add(receitas);

        //when
        when(receitas.getIdBarra()).thenReturn(1);
        when(repository.find(Matchers.any(Consumer.class))).thenReturn(receitasList);

        //then
        Set<CustomViolation> violationSet = validator.validate(receitas);
        assertFalse(violationSet.isEmpty());
    }

}