package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.parametros.persistence.entity.PlanoContas;
import br.gov.to.sefaz.arr.parametros.persistence.entity.TipoGruposCnaes;
import br.gov.to.sefaz.arr.parametros.persistence.repository.TipoGruposCnaesRepository;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
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
 * Teste para a classe {@link PlanoContasGruposCnaesSituacaoValidator}.
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 06/06/2016 15:09:00
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(SourceBundle.class)
public class PlanoContasGruposCnaesSituacaoValidatorTest {

    @Mock
    TipoGruposCnaesRepository repository;

    @InjectMocks
    PlanoContasGruposCnaesSituacaoValidator validator;

    @Before
    public void setUp() {
        mockStatic(SourceBundle.class);
    }

    @Test
    public void shouldFailSupportWhenObjectNotPedidoAreas() {
        // given
        Object tipoGruposCnaes = new Object();
        assertFalse(validator.support(tipoGruposCnaes.getClass(), ValidationContext.SAVE));

    }

    @Test
    public void shouldFailSupportWhenContextNotExists() {
        // given
        TipoGruposCnaes tipoGruposCnaes = createTipoGrupoCnaesMock();
        assertFalse(validator.support(tipoGruposCnaes.getClass(), ""));
    }

    @Test
    public void shouldFailWhenSituacaoIsCancelado() {
        PlanoContas planoContas = createPlanoContasMock();

        when(planoContas.getIdGruposCnaes()).thenReturn(SituacaoEnum.CANCELADO.getCode());
        when(repository.selectSituacao(SituacaoEnum.CANCELADO.getCode())).thenReturn(SituacaoEnum.CANCELADO);

        Set<CustomViolation> violationSet = validator.validate(planoContas);
        assertFalse(violationSet.isEmpty());
    }

    private PlanoContas createPlanoContasMock() {
        return mock(PlanoContas.class);
    }

    private TipoGruposCnaes createTipoGrupoCnaesMock() {
        return mock(TipoGruposCnaes.class);
    }
}