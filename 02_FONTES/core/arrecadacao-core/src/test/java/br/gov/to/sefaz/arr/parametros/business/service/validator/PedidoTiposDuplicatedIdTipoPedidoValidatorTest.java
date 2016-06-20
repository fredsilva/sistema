package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoTipos;
import br.gov.to.sefaz.arr.parametros.persistence.repository.PedidoTiposRepository;
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
 * Teste para a classe {@link PedidoTiposDuplicatedIdTipoPedidoValidator}.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 06/06/2016 14:26:00
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(SourceBundle.class)
public class PedidoTiposDuplicatedIdTipoPedidoValidatorTest {

    @Mock
    PedidoTiposRepository repository;

    @InjectMocks
    PedidoTiposDuplicatedIdTipoPedidoValidator validator;

    @Before
    public void setUp() {
        mockStatic(SourceBundle.class);
    }

    @Test
    public void shouldFailSupportWhenObjectNotPedidoAreas() {
        // given
        Object pedidoTipos = new Object();
        assertFalse(validator.support(pedidoTipos.getClass(), ValidationContext.SAVE));

    }

    @Test
    public void shouldFailSupportWhenContextNotExists() {
        // given
        PedidoTipos pedidoTipos = createPedidoTiposMock();
        assertFalse(validator.support(pedidoTipos.getClass(), ""));
    }

    @Test
    public void shouldFailDuplicatedTipoPedido() {
        PedidoTipos pedidoTipos = createPedidoTiposMock();

        when(pedidoTipos.getIdTipoPedido()).thenReturn(1);
        when(repository.findExitsIdTipoPedido(pedidoTipos.getIdTipoPedido())).thenReturn(true);

        Set<CustomViolation> violationSet = validator.validate(pedidoTipos);
        assertFalse(violationSet.isEmpty());

    }

    private PedidoTipos createPedidoTiposMock() {
        return mock(PedidoTipos.class);
    }
}