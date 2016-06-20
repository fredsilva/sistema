package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoAreas;
import br.gov.to.sefaz.arr.parametros.persistence.repository.PedidoAreasRepository;
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
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Teste para a classe {@link PedidoAreasParecerFinalUniqueValidator}.
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 06/06/2016 10:33:00
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(SourceBundle.class)
public class PedidoAreasParecerFinalUniqueValidatorTest {

    @Mock
    PedidoAreasRepository repository;

    @InjectMocks
    PedidoAreasParecerFinalUniqueValidator validator;

    @Before
    public void setUp() {
        mockStatic(SourceBundle.class);
    }

    @Test
    public void shouldFailSupportWhenObjectNotPedidoAreas() {
        // given
        Object pedidoAreas = new Object();

        assertFalse(validator.support(pedidoAreas.getClass(), ValidationContext.SAVE));

    }

    @Test
    public void shouldFailSupportWhenContextNotExists() {
        // given
        PedidoAreas pedidoAreas = createPedidoAreasMock();

        assertFalse(validator.support(pedidoAreas.getClass(), ""));
    }

    @Test
    public void shouldFailWhenDuplicatedFinalUnique() {
        PedidoAreas pedidoAreas = createPedidoAreasMock();

        when(pedidoAreas.getParecerFinal()).thenReturn(true);
        when(pedidoAreas.getIdPedidoArea()).thenReturn(2);
        when(repository.findIdByParecerFinalAndTipoPedido(true,pedidoAreas.getIdTipoPedido())).thenReturn(1);

        Set<CustomViolation> violationSet = validator.validate(pedidoAreas);

        assertFalse(violationSet.isEmpty());
    }

    private PedidoAreas createPedidoAreasMock() {
        return mock(PedidoAreas.class);
    }

}