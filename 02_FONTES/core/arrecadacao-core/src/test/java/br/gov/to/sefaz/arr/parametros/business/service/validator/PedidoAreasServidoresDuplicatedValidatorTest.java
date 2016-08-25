package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.persistence.entity.PedidoAreasServidores;
import br.gov.to.sefaz.arr.persistence.entity.PedidoAreasServidoresPK;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Teste para a classe {@link PedidoAreasServidoresDuplicatedValidator}.
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 06/06/2016 11:30:00
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(SourceBundle.class)
public class PedidoAreasServidoresDuplicatedValidatorTest {

    @InjectMocks
    PedidoAreasServidoresDuplicatedValidator validator;

    @Before
    public void setUp() {
        mockStatic(SourceBundle.class);
    }

    @Test
    public void shouldFailSupportWhenObjectNotPedidoAreasServidores() {
        // given
        Object pedidoAreasServidores = new Object();
        assertFalse(validator.support(pedidoAreasServidores.getClass(), ValidationContext.SAVE));

    }

    @Test
    public void shouldFailSupportWhenContextNotExists() {
        // given
        PedidoAreasServidores pedidoAreas = createPedidoAreasServidoresMock();
        assertFalse(validator.support(pedidoAreas.getClass(), ""));
    }

    @Test
    public void shouldFailWhenDuplicatedServidoresList() {
        ArrayList<PedidoAreasServidores> servidoresArrayList = new ArrayList<>();
        PedidoAreasServidores pedidoAreasServidores = createPedidoAreasServidoresMock();
        PedidoAreasServidores pedidoAreasServidores1 = createPedidoAreasServidoresMock();

        PedidoAreasServidoresPK pedidoAreaServidoresPK = createPedidoAreaServidoresPK();
        PedidoAreasServidoresPK pedidoAreaServidoresPK1 = createPedidoAreaServidoresPK();

        when(pedidoAreaServidoresPK.getIdServidor()).thenReturn(1L);
        when(pedidoAreaServidoresPK1.getIdServidor()).thenReturn(1L);
        when(pedidoAreasServidores.getId()).thenReturn(pedidoAreaServidoresPK);
        when(pedidoAreasServidores1.getId()).thenReturn(pedidoAreaServidoresPK1);

        servidoresArrayList.add(pedidoAreasServidores);
        servidoresArrayList.add(pedidoAreasServidores1);

        Set<CustomViolation> violationSet = validator.validateAll(servidoresArrayList);
        assertFalse(violationSet.isEmpty());

    }

    @Test
    public void cannotFail() {

        PedidoAreasServidores areasServidores = createPedidoAreasServidoresMock();
        Set<CustomViolation> violationSet = validator.validate(areasServidores);

        assertTrue(violationSet.isEmpty());

    }

    private PedidoAreasServidoresPK createPedidoAreaServidoresPK() {
        return mock(PedidoAreasServidoresPK.class);
    }

    private PedidoAreasServidores createPedidoAreasServidoresMock() {
        return mock(PedidoAreasServidores.class);
    }

}