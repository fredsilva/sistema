package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.parametros.persistence.entity.PlanoContas;
import br.gov.to.sefaz.arr.parametros.persistence.repository.PlanoContasRepository;
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
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * Teste para a classe {@link PlanoContasHierarquicoExistsValidator}.
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 06/06/2016 15:20:00
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(SourceBundle.class)
public class PlanoContasHierarquicoExistsValidatorTest {

    @Mock
    PlanoContasRepository repository;

    @InjectMocks
    PlanoContasHierarquicoExistsValidator validator;

    @Before
    public void setUp() {
        mockStatic(SourceBundle.class);
    }

    @Test
    public void shouldFailSupportWhenObjectNotPedidoAreas() {
        // given
        Object planoContas = new Object();
        assertFalse(validator.support(planoContas.getClass(), ValidationContext.SAVE));

    }

    @Test
    public void shouldFailSupportWhenContextNotExists() {
        // given
        PlanoContas planoContas = createPlanoContasMock();
        assertFalse(validator.support(planoContas.getClass(), ""));
    }

    @Test
    public void shouldFailWhenContaHierarquicaIsEmpty() {
        PlanoContas planoContas = createPlanoContasMock();

        when(planoContas.getContaHierarquica()).thenReturn("Qualquercoisa");
        when(repository.existsByCodigo(planoContas.getCodigoPlanoContas())).thenReturn(false);

        Set<CustomViolation> violationSet = validator.validate(planoContas);
        assertFalse(violationSet.isEmpty());

    }

    private PlanoContas createPlanoContasMock() {
        return mock(PlanoContas.class);
    }
}