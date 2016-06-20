package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoCamposAcoes;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoReceita;
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

import java.util.ArrayList;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * Teste para a classe {@link PedidoTiposListaAcoesVaziasValidator}.
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 06/06/2016 13:59:00
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(SourceBundle.class)
public class PedidoTiposListaAcoesVaziasValidatorTest {

    @Mock
    PedidoTiposRepository repository;

    @InjectMocks
    PedidoTiposListaAcoesVaziasValidator validator;

    @Before
    public void setUp() {
        mockStatic(SourceBundle.class);
    }

    @Test
    public void shouldFailSupportWhenObjectNotPedidoAreas() {
        // given
        Object tipoPedido = new Object();
        assertFalse(validator.support(tipoPedido.getClass(), ValidationContext.SAVE));

    }

    @Test
    public void shouldFailSupportWhenContextNotExists() {
        // given
        PedidoTipos pedidoTipos = createPedidosTipoMock();
        assertFalse(validator.support(pedidoTipos.getClass(), ""));
    }

    @Test
    public void shouldFailWhenFieldAcoesIsEmpty() {
        PedidoTipos pedidoTipos = createPedidosTipoMock();
        ArrayList<PedidoCamposAcoes> pedidoCamposAcoesList = new ArrayList<>();

        when(pedidoTipos.getPedidoCamposAcoes()).thenReturn(pedidoCamposAcoesList);
        Set<CustomViolation> violationSet = validator.validate(pedidoTipos);

        assertFalse(violationSet.isEmpty());

    }

    @Test
    public void shouldFailWhenFieldPedidoReceitasIsEmpty() {
        PedidoTipos pedidoTipos = createPedidosTipoMock();

        ArrayList<PedidoCamposAcoes> pedidoCamposAcoesList = new ArrayList<>();
        PedidoCamposAcoes pedidoCamposAcoes = createPedidoCamposAcoesMock();
        pedidoCamposAcoesList.add(pedidoCamposAcoes);

        ArrayList<PedidoReceita> pedidoReceitas = new ArrayList<>();

        when(pedidoTipos.getPedidoCamposAcoes()).thenReturn(pedidoCamposAcoesList);
        when(pedidoTipos.getPedidoReceitas()).thenReturn(pedidoReceitas);
        Set<CustomViolation> violationSet = validator.validate(pedidoTipos);

        assertFalse(violationSet.isEmpty());

    }

    private PedidoCamposAcoes createPedidoCamposAcoesMock() {
        return mock(PedidoCamposAcoes.class);
    }

    private PedidoTipos createPedidosTipoMock() {
        return mock(PedidoTipos.class);
    }

}