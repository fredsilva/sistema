package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.persistence.entity.TipoGruposCnaes;
import br.gov.to.sefaz.arr.persistence.repository.TipoGruposCnaesRepository;
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
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * Teste para a classe {@link TipoGruposCnaeDuplicatedValidator}.
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 06/06/2016 16:45:00
*/
@RunWith(PowerMockRunner.class)
@PrepareForTest(SourceBundle.class)
public class TipoGruposCnaeDuplicatedValidatorTest {

    @Mock
    TipoGruposCnaesRepository repository;

    @InjectMocks
    TipoGruposCnaeDuplicatedValidator validator;

    @Before
    public void setUp() {
        mockStatic(SourceBundle.class);
    }

    @Test
    public void shouldFailSupportWhenObjectNotTipoGruposCnaes() {
        // given
        Object tipoGruposCnaes = new Object();

        assertFalse(validator.support(tipoGruposCnaes.getClass(), ValidationContext.SAVE));
    }

    @Test
    public void shouldFailSupportWhenContextNotExists() {
        // given
        TipoGruposCnaes tipoGruposCnaes = createTipoGruposCnaesMock();

        assertFalse(validator.support(tipoGruposCnaes.getClass(), ""));
    }

    @Test
    public void shouldFailWhenGrupoDoesNotExists() {
        // given
        TipoGruposCnaes tipoGruposCnaes = createTipoGruposCnaesMock();
        // when
        when(repository.exists(tipoGruposCnaes.getIdGrupoCnae())).thenReturn(true);
        // then
        Set<CustomViolation> violationSet = validator.validate(tipoGruposCnaes);
        assertFalse(violationSet.isEmpty());
    }

    private TipoGruposCnaes createTipoGruposCnaesMock() {
        return mock(TipoGruposCnaes.class);
    }
}