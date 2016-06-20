package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoAreas;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoAreasServidores;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Teste para a classe {@link PedidoAreasServidoresEmptyValidator}.
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 06/06/2016 11:44:00
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(SourceBundle.class)
public class PedidoAreasServidoresEmptyValidatorTest {

    @InjectMocks
    PedidoAreasServidoresEmptyValidator validator;

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
    public void shouldFailWhenNoServidorExists() {
        // given
        PedidoAreas pedidoAreas = createPedidoAreasMock();
        List<PedidoAreasServidores> areasServidoresList = createListPedidoAreasServidores();

        when(pedidoAreas.getPedidoAreasServidores()).thenReturn(areasServidoresList);

        Set<CustomViolation> violationSet = validator.validate(pedidoAreas);
        assertFalse(violationSet.isEmpty());
    }

    private List<PedidoAreasServidores> createListPedidoAreasServidores() {
        ArrayList<PedidoAreasServidores> retorno = new ArrayList<>();
        PedidoAreasServidores areasServidores = mock(PedidoAreasServidores.class);
        PedidoAreasServidores areasServidores1 = mock(PedidoAreasServidores.class);

        when(areasServidores.getSituacao()).thenReturn(SituacaoEnum.CANCELADO);
        when(areasServidores1.getSituacao()).thenReturn(SituacaoEnum.CANCELADO);

        retorno.add(areasServidores);
        retorno.add(areasServidores1);

        return retorno;
    }

    private PedidoAreas createPedidoAreasMock() {
        return mock(PedidoAreas.class);
    }

}