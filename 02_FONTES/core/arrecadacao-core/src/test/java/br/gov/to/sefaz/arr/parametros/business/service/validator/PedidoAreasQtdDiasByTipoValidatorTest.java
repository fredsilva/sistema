package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.persistence.entity.PedidoAreas;
import br.gov.to.sefaz.arr.persistence.entity.PedidoTipos;
import br.gov.to.sefaz.arr.persistence.repository.PedidoAreasRepository;
import br.gov.to.sefaz.arr.persistence.repository.PedidoTiposRepository;
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
 * Teste para a classe {@link PedidoAreasQtdDiasByTipoValidator}.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 06/06/2016 10:46:00
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(SourceBundle.class)
public class PedidoAreasQtdDiasByTipoValidatorTest {

    @Mock
    PedidoAreasRepository repository;

    @Mock
    PedidoTiposRepository tiposRepository;

    @InjectMocks
    PedidoAreasQtdDiasByTipoValidator validator;

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
    public void shouldFailWhenTipoQtdDiasLessThanPedidoAreasQtdDias() {
        PedidoAreas pedidoAreas = createPedidoAreasMock();
        PedidoTipos pedidoTipos = createPedidoTiposMock();

        when(repository.getTotalQtdDiasAnaliseByTipoAndNotId(1, 2)).thenReturn(5L);
        when(pedidoAreas.getQuantidadeDiasAnalise()).thenReturn(2);
        when(pedidoTipos.getQuantidadeDiasAnalise()).thenReturn(1);
        when(tiposRepository.findOne(pedidoAreas.getIdTipoPedido())).thenReturn(pedidoTipos);

        Set<CustomViolation> violationSet = validator.validate(pedidoAreas);

        assertFalse(violationSet.isEmpty());
    }

    private PedidoAreas createPedidoAreasMock() {
        return mock(PedidoAreas.class);
    }

    private PedidoTipos createPedidoTiposMock() {
        return mock(PedidoTipos.class);
    }

}