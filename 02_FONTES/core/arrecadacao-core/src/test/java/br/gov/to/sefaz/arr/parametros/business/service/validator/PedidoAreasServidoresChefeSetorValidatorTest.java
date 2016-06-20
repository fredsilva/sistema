package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoAreasServidores;
import br.gov.to.sefaz.arr.parametros.persistence.repository.PedidoAreasServidoresRepository;
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

import java.util.ArrayList;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Teste para a classe {@link PedidoAreasServidoresChefeSetorValidator}.
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 06/06/2016 11:12:00
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(SourceBundle.class)
public class PedidoAreasServidoresChefeSetorValidatorTest {

    @Mock
    PedidoAreasServidoresRepository repository;

    @InjectMocks
    PedidoAreasServidoresChefeSetorValidator validator;

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
    public void shouldFailDuplicatedServidorChefe() {

        PedidoAreasServidores pedidoAreasServidores = createPedidoAreasServidoresMock();
        when(pedidoAreasServidores.getSupervisor()).thenReturn(true);
        when(pedidoAreasServidores.getIdPedidoArea()).thenReturn(1);
        when(repository.existsChefeSetor(pedidoAreasServidores.getIdPedidoArea(),true)).thenReturn(true);

        Set<CustomViolation> violationSet = validator.validate(pedidoAreasServidores);

        assertFalse(violationSet.isEmpty());
    }

    @Test
    public void shouldFailDuplicatedServidorChefeList() {
        ArrayList<PedidoAreasServidores> pedidoAreasServidoresList = new ArrayList<>();

        PedidoAreasServidores pedidoAreasServidores = createPedidoAreasServidoresMock();
        when(pedidoAreasServidores.getSupervisor()).thenReturn(true);
        when(pedidoAreasServidores.getIdPedidoArea()).thenReturn(1);
        PedidoAreasServidores pedidoAreasServidores2 = createPedidoAreasServidoresMock();
        when(pedidoAreasServidores2.getSupervisor()).thenReturn(true);
        when(pedidoAreasServidores2.getIdPedidoArea()).thenReturn(1);

        pedidoAreasServidoresList.add(pedidoAreasServidores);
        pedidoAreasServidoresList.add(pedidoAreasServidores2);

        Set<CustomViolation> violationSet = validator.validateAll(pedidoAreasServidoresList);

        assertFalse(violationSet.isEmpty());
    }

    private PedidoAreasServidores createPedidoAreasServidoresMock() {
        return mock(PedidoAreasServidores.class);
    }
}